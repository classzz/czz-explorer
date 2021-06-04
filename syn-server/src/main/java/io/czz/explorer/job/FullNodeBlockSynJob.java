package io.czz.explorer.job;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.czz.explorer.SynServerConfig;
import io.czz.explorer.exception.ServiceException;
import io.czz.explorer.service.BlockService;
import io.czz.explorer.syn.SynNodeBlock;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@DisallowConcurrentExecution
public class FullNodeBlockSynJob {
    private static boolean IS_STATE;

    private SynNodeBlock synNodeBlock;
    private BlockService blockService;
    private SynServerConfig config;
    private static final Logger logger = LoggerFactory.getLogger(FullNodeBlockSynJob.class);

    @Inject
    public FullNodeBlockSynJob(SynNodeBlock synNodeBlock, BlockService blockService, SynServerConfig config) {
        this.synNodeBlock = synNodeBlock;
        this.blockService = blockService;
        this.config = config;
    }

    @Scheduled("5000ms")
    public void syncFullNodeBlocks() throws ServiceException {

        if (!this.config.isBlockJobEnabled() || IS_STATE) {
            return;
        }
        IS_STATE = true;
        try {
            Long lastBlockNum = blockService.getlastNumber();
            logger.info("current full node block:" + lastBlockNum);

            if (this.synNodeBlock.isInitialSync()) {
                logger.info("Initial import ... that might take a moment, grab a coffe ...");
            }
            this.synNodeBlock.syncNodeFull();

        } catch (RuntimeException e) {
            logger.error(e.toString());
        }
        IS_STATE = false;
    }


}
