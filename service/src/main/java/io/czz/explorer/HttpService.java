package io.czz.explorer;

import com.alibaba.fastjson.JSONObject;
import io.czz.explorer.constants.InbConstants;
import io.czz.explorer.dto.JsonParam;
import io.czz.explorer.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HttpService {




//    public static JSONObject getResult(List<Object> params, String methodName){
//
//        JsonParam jsonParam = new JsonParam();
//        jsonParam.setJsonrpc("2.0");
//        jsonParam.setMethod(methodName);
//        jsonParam.setParams(params);
//        jsonParam.setId(67L);
//        String param = JSONObject.toJSONString(jsonParam);
//        String result = HttpUtil.doPost(InbConstants.URL, param);
//        JSONObject object = (JSONObject)JSONObject.parse(result);
//        return object;
//
//    }


    public static void main(String[] args) throws Exception {

//        System.out.println(Numeric.decodeQuantity("0x8a0e"));
//
//        //获取不可逆块高度
//        List<Object> params = new ArrayList<>();
//        JSONObject object = getResult(params,"eth_confirmedBlockNumber");
//        System.out.println(object.getString("result"));
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Integer a = Integer.parseInt("4112dceb305cf4b20",16);
//        BigInteger bigInteger = Numeric.decodeQuantity("0x5d19eac1");
//
//        System.out.println(bigInteger.toString());
//
//        String  d  = simpleDateFormat.format(new Date(Long.valueOf(bigInteger.toString()+"000")));
//        System.out.println(d);
//
////        String hex = .toString();
//        System.out.println(new String(Hex.decode("496e7465726e6574204e65656420426c6f636b636861696e2c20494e4220697320636f6d696e67206e6f772e")));


//        System.out.println(fromHexString("0x4112dceb305cf4b20"));
        //获取交易信息
//        String param = "{\"jsonrpc\":\"2.0\", \"method\":\"eth_getTransactionReceipt\",\"params\":[\"0x520d3264ec3b1b5f91ba0efc2b1a08e36b10949dab31aac67eca72e02ef883f7\"],\"id\":67}";
//        String result = doPost(InbConstants.URL, param);
//        JSONObject object = (JSONObject)JSONObject.parse(result);
//        String blockNumber = object.getJSONObject("result").getString("blockNumber");
//        System.out.println(object);


//        List<Object> params = new ArrayList<>();
//        params.add("0x64cf70fcdf06ceb18dafd15fc9807d905cdaa903d7c7111875ede92fb1a0ab4c");
//        JsonParam jsonParam = new JsonParam();
//        jsonParam.setJsonrpc("2.0");
//        jsonParam.setMethod("eth_getTransactionReceipt");
//        jsonParam.setParams(params);
//        jsonParam.setId(67L);
//        String param = JSONObject.toJSONString(jsonParam);
//        String result = HttpService.doPost("http://192.168.1.183:6003/", param);
//        System.out.println(result);

//
        //获取账号信息
//         String param = "{\"jsonrpc\":\"2.0\", \"method\":\"getblockhash\",\"params\":[0],\"id\":67}";
//         String param = "{\"jsonrpc\":\"2.0\", \"method\":\"getblock\",\"params\":[\"161a023252ac8389f35c1141e0662f387921e7c67641ed4dc4aa91851ab40535\"],\"id\":67}";
         String param = "{\"jsonrpc\":\"2.0\", \"method\":\"getrawtransaction\",\"params\":[\"29cc95cfb6e5cba7eff045654b64a5bb85239dc0e07f18d54aca153ceea1095b\",1],\"id\":67}";
//         String param = "{\"jsonrpc\":\"2.0\", \"method\":\"getrawtransaction\",\"params\":[\"7f3dc96d40480b5ba6b469e19e92886ff9f4759f37dc75f068f0f8e1d3bd389b\",1],\"id\":67}";
//         String param = "{\"jsonrpc\":\"2.0\", \"method\":\"getblockcount\",\"params\":[],\"id\":67}";
//         String param = "{\"jsonrpc\":\"2.0\", \"method\":\"searchrawtransactions\",\"params\":[\"cq82h4d6h5544axwkq30lv03aegg3073fuucp5t543\",1,0,5000],\"id\":67}";
         String result = HttpUtil.doPost(InbConstants.URL, param);
         JSONObject object = (JSONObject)JSONObject.parse(result);
         Integer nonce = Integer.valueOf(object.getJSONObject("result").get("Nonce").toString());
         String mortgagteINB = object.getJSONObject("result").getJSONObject("Resources").getJSONObject("NET").get("MortgagteINB").toString();
         String binwith =  object.getJSONObject("result").getJSONObject("Resources").getJSONObject("NET").get("Used").toString();
         BigInteger balance  = object.getJSONObject("result").getBigInteger("Balance");
         Double balance2 = balance.doubleValue();
         System.out.println(balance);

//
//        String param1 = "{\"jsonrpc\":\"2.0\", \"method\":\"eth_getBlockByNumber\",\"params\":[\"0x187\",true],\"id\":67}";
//        String result1 = doPost("http://192.168.1.182:6001/", param1);
//        JSONObject object1 = (JSONObject)JSONObject.parse(result1);
//        BigInteger a = new BigInteger(object1.getJSONObject("result").get("reward").toString());
//        System.out.println("a"+a);

//        object1.getJSONObject("result");
//        List<Object> blockParams = new ArrayList<>();
//        blockParams.add("0x520d3264ec3b1b5f91ba0efc2b1a08e36b10949dab31aac67eca72e02ef883f7");
//        JsonParam jsonParamBlock = new JsonParam();
//        jsonParamBlock.setJsonrpc("2.0");
//        jsonParamBlock.setMethod("eth_getTransactionByHash");
//        jsonParamBlock.setParams(blockParams);
//        jsonParamBlock.setId(67L);
//        String blockParam = JSONObject.toJSONString(jsonParamBlock);
//        String block = HttpUtil.doPost(InbConstants.URL, blockParam);
//        System.out.println("a"+block);


//        double d = Double.parseDouble(object1.getJSONObject("result").get("reward").toString());
////        long s = 38051750380517503805l;
////        String s = String.valueOf(Math.pow(10,16));
//        double s = Math.pow(10,16);
//        double sfasf =d/s;
////        BigInteger b = new BigInteger(s);
////        System.out.println("10^16"+a.divide(b));
//        System.out.println(result1);
//        System.out.println();
//        System.out.println(receipt);
//
//
//        List<Object> params = new ArrayList<>();
//        params.add("0x5");
//        params.add(true);
//        JsonParam jsonParam = new JsonParam();
//        jsonParam.setJsonrpc("2.0");
//        jsonParam.setMethod("eth_getBlockByNumber");
//        jsonParam.setParams(params);
//        jsonParam.setId(67L);
//        String param3 = JSONObject.toJSONString(jsonParam);
//        String result3 = doPost(InbConstants.URL, param3);
//        System.out.println("result3"+result3);

//        List<Object> params = new ArrayList<>();
//        params.add(true);
//        JsonParam jsonParam = new JsonParam();
//        jsonParam.setJsonrpc("2.0");
//        jsonParam.setMethod("getblockcount");
//        jsonParam.setParams(params);
//        jsonParam.setId(67L);
//        String param3 = JSONObject.toJSONString(jsonParam);
//        String result3 = HttpUtil.doPost(InbConstants.URL, param3);
//        System.out.println("result3"+result3);


//        List<Object> params = new ArrayList<>();
//        params.add("0x69b4f4fa05b4748d200c0aae5f9d56af6a1066652b483a3fb4426af0cc94e02c");
//        params.add(true);
//        JsonParam jsonParam = new JsonParam();
//        jsonParam.setJsonrpc("2.0");
//        jsonParam.setMethod("eth_getBlockByHash");
//        jsonParam.setParams(params);
//        jsonParam.setId(67L);
//        String param3 = JSONObject.toJSONString(jsonParam);
//        String result3 = HttpUtil.doPost("http://192.168.1.183:6003", param3);
//        JSONObject object = (JSONObject)JSONObject.parse(result3);
//        String timestamp = object.getJSONObject("result").getString("timestamp");
//        System.out.println("result3"+result3);



        File f  = new File("/home/xingkong/block/inb-explorer-web");
        String type = ".java";//查找什么类型的代码，如".java"就是查找以java开发的代码量，".php"就是查找以PHP开发的代码量
        HttpService.treeFile(f,type);
        System.out.println("zonghangshu" + allLines);


    }

    static long classcount = 0; // Java类的数量
    static long normalLines = 0; // 空行
    static long commentLines = 0; // 注释行
    static long writeLines = 0; // 代码行
    static long allLines = 0;

    public static void treeFile(File f,String type) throws Exception {
        File[] childs = f.listFiles();
        for (int i = 0; i < childs.length; i++) {
            File file = childs[i];
            if (!file.isDirectory()) {
                if (file.getName().endsWith(type)) {
                    classcount++;
                    BufferedReader br = null;
                    boolean comment = false;
                    br = new BufferedReader(new FileReader(file));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        allLines++;
                        line = line.trim();
                        if (line.matches("^[//s&&[^//n]]*$")) {//这一行匹配以空格开头，但不是以回车符开头，但以回车符结尾
                            normalLines++;
                        } else if (line.startsWith("/*")
                                && !line.endsWith("*/")) {//匹配以/*......*/括住的多行注释
                            commentLines++;
                            comment = true;
                        } else if (true == comment) {
                            commentLines++;
                            if (line.endsWith("*/")) {
                                comment = false;
                            }//匹配以//开头的单行注释，及以/*......*/括住的单行注释
                        } else if (line.startsWith("//") || (line.startsWith("/*")&&line.endsWith("*/"))) {
                            commentLines++;
                        } else {//其他的就是代码行
                            writeLines++;
                        }
                    }
                    if (br != null) {
                        br.close();
                        br = null;
                    }
                }
            } else {
                treeFile(childs[i],type);
            }
        }
    }

}




