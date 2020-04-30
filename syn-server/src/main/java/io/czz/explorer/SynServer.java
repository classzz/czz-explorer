package io.czz.explorer;



import io.czz.explorer.job.AccountSynJob;
import io.czz.explorer.job.BlockSynJob;
import io.czz.explorer.job.SynNodeSynJob;
import org.jooby.Jooby;
import org.jooby.flyway.Flywaydb;
import org.jooby.jdbc.Jdbc;
import org.jooby.jooq.jOOQ;
import org.jooby.json.Jackson;
import org.jooby.quartz.Quartz;
import org.jooq.DSLContext;
import org.jooq.conf.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynServer extends Jooby{
    private static final Logger logger = LoggerFactory.getLogger(SynServer.class);

    {
        use(new SynServerModule());


        use(new Jdbc());
        use(new jOOQ());

        use(new Jackson());
        use(new Flywaydb());

//        AccountSynJob.class, SynNodeSynJob.class,/
        use(new Quartz(BlockSynJob.class));


        onStart(registry -> {

            SynServerConfig config = registry.require(SynServerConfig.class);

            logger.info("=> Sync node up [id = {}]",config.getNodeId());

            DSLContext dslContext = registry.require(DSLContext.class);

            Settings settings = new Settings();
            settings.setRenderSchema(false);

            dslContext.configuration().set(settings);
            System.out.println("hello");
        });

        onStop((registry)->{

        });


        get("/", (req, res) -> {

            res.send("");
        });

    }

    public static void main(final String[] args) {
        run(SynServer::new, args);
    }
}
