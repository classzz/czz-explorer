package io.czz.explorer.route;

import com.google.inject.Inject;
import io.czz.explorer.dto.ListModel;
import io.czz.explorer.dto.block.BlockChainDto;
import io.czz.explorer.dto.block.BlockCriteriaDTO;
import io.czz.explorer.dto.block.BlockDTO;
import io.czz.explorer.dto.block.BlockDiffDTO;
import io.czz.explorer.service.BlockService;
import org.jooby.Request;
import org.jooby.Response;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.jooq.types.ULong;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class BlockRoutes {

	private BlockService blockService;

	@Inject
	public BlockRoutes(BlockService blockService) {
		this.blockService = blockService;
	}
	
	/**
	 * Get the latest available block
	 * @param req
	 * @param res
	 * @return
	 * @throws Throwable
	 */
	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_LATEST)
	public BlockDTO broadcastTransaction(Request req, Response res) throws Throwable {
		req.param("toto");
	    
		return this.blockService.getLastBlock();
		
	}

	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_LATEST_HEIGHT)
	public Integer latestHeight() throws Throwable {
	    return this.blockService.getLastBlockHeight();

	}

	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_LATEST_HEIGHT_M)
	public Map latestHeightM() throws Throwable {
	    Map map = new HashMap(1);
	    map.put("height",this.blockService.getLastBlockHeight());
		return map;

	}

	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_LIST)
	public ListModel<BlockDTO, BlockCriteriaDTO> listBlocks(Optional<Integer> page, Optional<Integer> limit) throws Throwable {

		BlockCriteriaDTO criteria = new BlockCriteriaDTO();

		criteria.setLimit(limit.orElse(20));
		criteria.setPage(page.orElse(1));

		return this.blockService.listBlocks(criteria);

	}


	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_SEARCH_DIFFICULTY)
	public ListModel<BlockDiffDTO, BlockCriteriaDTO> listBlockDiff(Optional<Integer> page, Optional<Integer> limit) throws Throwable {

		BlockCriteriaDTO criteria = new BlockCriteriaDTO();

		criteria.setLimit(limit.orElse(20));
		criteria.setPage(page.orElse(1));

		return this.blockService.listBlockDiff(criteria);

	}

//
	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_SEARCH)
	public BlockDTO searchBlock(Optional<Integer> height, Optional<String> hash){

        BlockCriteriaDTO criteria = new BlockCriteriaDTO();
        criteria.setHash(hash.orElse(null));
        criteria.setHeight(height.orElse(null));
		return this.blockService.getBlockByNum(criteria);
	}


	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_INFO)
	public BlockChainDto blockInfo(){
		return this.blockService.getBlockChainInfo();
	}


	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_ERROR_)
	public Map<ULong,String> listErrorBlock(){
		return this.blockService.getErrorBlock();
	}



//	@GET
//	@Path(ApiAppRoutePaths.V1.NODE_INFO)
//	public ListModel<Node, NodeCriteriaDTO> NodeInfo(Optional<Integer> page, Optional<Integer> limit){
//		NodeCriteriaDTO criteria = new NodeCriteriaDTO();
//		criteria.setLimit(limit.orElse(200));
//		criteria.setPage(page.orElse(1));
//		return this.blockService.getNodeInfo(criteria);
//	}
//
//
//	@GET
//	@Path(ApiAppRoutePaths.V1.ACCOUNT_NODE_INFO_)
//	public Node getNodeInfoWithAddress(String address){
//
//		return this.blockService.getNodeInfoByAccount(address);
//	}
//
//	@GET
//	@Path(ApiAppRoutePaths.V1.BLOCK_SEARCH_TRANSFERS)
//	public BlockDTO searchBlockTransfers(Integer blockNumber){
//		BLockCriteria bLockCriteria = new BLockCriteria();
//		bLockCriteria.setBlockNum(blockNumber);
//		return this.blockService.getBlock(bLockCriteria);
//	}
}
