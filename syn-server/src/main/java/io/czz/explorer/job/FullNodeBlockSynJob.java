package io.czz.explorer.job;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.czz.explorer.SynServerConfig;
import io.czz.explorer.exception.ServiceException;
import io.czz.explorer.service.BlockService;
import io.czz.explorer.syn.SynBlock;
import io.czz.explorer.syn.SynNodeBlock;
import org.jooby.quartz.Scheduled;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@DisallowConcurrentExecution
public class FullNodeBlockSynJob {
    private SynNodeBlock synNodeBlock;
    private BlockService blockService;
    private SynServerConfig config;
    private static final Logger logger = LoggerFactory.getLogger(BlockSynJob.class);

    @Inject
    public FullNodeBlockSynJob(SynNodeBlock synNodeBlock, BlockService blockService, SynServerConfig config) {
        this.synNodeBlock = synNodeBlock;
        this.blockService = blockService;
        this.config = config;
    }

    @Scheduled("10000ms")
    public void syncFullNodeBlocks() throws ServiceException {

        if (!this.config.isBlockJobEnabled()) {
            return;
        }

        Long lastBlockNum = blockService.getlastNumber();
        logger.info("current full node block:"+lastBlockNum);

        if (this.synNodeBlock.isInitialSync()) {
            logger.info("Initial import ... that might take a moment, grab a coffe ...");
        }

        this.synNodeBlock.syncNodeFull(lastBlockNum);

    }


}
