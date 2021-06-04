package io.czz.explorer;

import io.czz.explorer.job.FullNodeBlockSynJob;
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

import java.sql.Connection;
import java.sql.DriverManager;

public class SynServer extends Jooby {
    private static final Logger logger = LoggerFactory.getLogger(SynServer.class);

    {
        use(new SynServerModule());

        use(new Jdbc());
        use(new jOOQ());

        use(new Jackson());
        use(new Flywaydb());

//        AccountSynJob.class, SynNodeSynJob.class,/
        use(new Quartz(FullNodeBlockSynJob.class, SynNodeSynJob.class));

        onStart(registry -> {

            SynServerConfig config = registry.require(SynServerConfig.class);
            logger.info("=> Sync node up [id = {}]", config.getNodeId());

            DSLContext dslContext = registry.require(DSLContext.class);

            Settings settings = new Settings();
            settings.setRenderSchema(false);

            //用户名
            String userName = "root";
            //密码
            String password = "QGTDS4uDjyvQ4Rbc";
            //mysql链接url
            String url = "jdbc:mysql://172.27.62.110:3306/czz_tmp";
            //String url = "jdbc:mysql://47.243.41.104:3306/czz_tmp";

            //用户名
            //String userName = "czz_tmp";
            //密码
            //String password = "CDjrZCzd7GmeGnWM";
            //mysql链接url
            //String url = "jdbc:mysql://172.27.62.110:3306/czz_tmp";
            //String url = "jdbc:mysql://121.89.188.239:51006/czz_tmp";

            //这是JDBC Mysql连接
            Connection conn = DriverManager.getConnection(url, userName, password);
            //基于JOOQ实现的简单查询
            //传入Connection连接对象、数据方言得到一个DSLContext的实例，然后使用DSL对象查询得到一个Result对象。

            dslContext.configuration().set(settings);
            dslContext.configuration().set(conn);
        });

        onStop((registry) -> {

        });


        get("/", (req, res) -> {
            res.send("");
        });

    }

    public static void main(final String[] args) {
        run(SynServer::new, args);
    }
}
