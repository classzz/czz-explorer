package io.czz.explorer.chain;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.czz.explorer.constants.InbConstants;
import io.czz.explorer.dto.JsonParam;
import io.czz.explorer.dto.block.BlockDTO;
import io.czz.explorer.dto.transaction.DhVo;
import io.czz.explorer.dto.transaction.TransactionDTO;
import io.czz.explorer.service.TransactionService;
import io.czz.explorer.utils.HttpUtil;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;


@Singleton
public class CzzChainService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private DSLContext dslContext;

    @Inject
    public CzzChainService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    public JSONObject getResult(List<Object> params, String methodName){

        JsonParam jsonParam = new JsonParam();
        jsonParam.setJsonrpc("2.0");
        jsonParam.setMethod(methodName);
        jsonParam.setParams(params);
        jsonParam.setId(67L);
        String param = JSONObject.toJSONString(jsonParam);
        String result = HttpUtil.doPost(InbConstants.URL, param);
        JSONObject object = (JSONObject)JSONObject.parse(result);
        return object;

    }



    /**
     * 获取区块高度
     * @return
     */
    public Integer getBlockHeight(){
        List<Object> params = new ArrayList<>();
        JSONObject object = getResult(params,"getblockcount");
        Integer result = object.getInteger("result");
        return result;
    }

    /**
     * 获取区块hash
     * @return
     */
    public String getBlockHash(Integer blockHeight){
        List<Object> params = new ArrayList<>();
        params.add(blockHeight);
        JSONObject object = getResult(params,"getblockhash");
        String result = object.getString("result");
        return result;
    }

    /**
     * 获取区块信息
     * @return
     */
    public BlockDTO getBlock(String blockHash){
        long start = System.currentTimeMillis();
        List<Object> params = new ArrayList<>();
        params.add(blockHash);
        JSONObject object = getResult(params,"getblock");
        BlockDTO blockDTO = JSON.toJavaObject(object.getJSONObject("result"),BlockDTO.class) ;
        logger.info("getBlock times:"+(System.currentTimeMillis()-start));
        return blockDTO;
    }

    /**
     * 获取交易信息
     * @return
     */
    public TransactionDTO getTransaction(String transHash){
        long start = System.currentTimeMillis();
        List<Object> params = new ArrayList<>();
        params.add(transHash);
        params.add(1);
        JSONObject object = getResult(params,"getrawtransaction");
        TransactionDTO transactionDTO = JSON.toJavaObject(object.getJSONObject("result"),TransactionDTO.class) ;
        logger.info("getTransaction times:"+(System.currentTimeMillis()-start));
        return transactionDTO;
    }

    public List<DhVo> getconvertitems(){
        long start = System.currentTimeMillis();
        List<Object> params = new ArrayList<>();
        JSONObject object = getResult(params,"getconvertitems");
        JSONArray ja = object.getJSONArray("result");
        List<DhVo> result = ja.toJavaList(DhVo.class);
        logger.info("getconvertitems times:"+(System.currentTimeMillis()-start));
        return result;
    }

    public List<DhVo> getconvertconfirmitems(){
        long start = System.currentTimeMillis();
        List<Object> params = new ArrayList<>();
        JSONObject object = getResult(params,"getconvertconfirmitems");
        JSONArray ja = object.getJSONArray("result");
        List<DhVo> result = ja.toJavaList(DhVo.class);
        logger.info("getconvertconfirmitems times:"+(System.currentTimeMillis()-start));
        return result;
    }
}
