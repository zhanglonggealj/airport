package com.esop.airport.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program: airport
 * @description: 参数签名 参数签名验证工具
 * @author: Mr.Li
 * @create: 2019-08-12 15:08
 **/
public class ParamsSignatureUtil {


    /**
     * 给定JSONObject 和 MD5KEY 进行签名
     * @param object
     * @param md5Key
     * @return
     */
    public static JSONObject signature(JSONObject object, String md5Key) {

        List<String> keys = new ArrayList<>();

        object.put("noncestr", UUID.getUUID()); //加入随机字符串

        for (Map.Entry<String, Object> entry : object.entrySet()) {
            keys.add(entry.getKey());
        }

        Collections.sort(keys);

        StringBuffer sb = new StringBuffer();

        for (String key : keys){
            sb.append(key + "=" + object.get(key) + "&");
        }

        sb.deleteCharAt(sb.length() - 1); //删除最有一个&

        String signature = MD5Utils.getMd5(sb.toString() + md5Key);

        object.put("signature", signature);

        return object;
    }

    /**
     * 对JSONOBJECT 进行签名验证 验证成功返回true 否则 false
     * @param object
     * @param md5Key
     * @return
     */
    public static boolean verifySignature(JSONObject object, String md5Key) {

        List<String> keys = new ArrayList<>();

        for (Map.Entry<String, Object> entry : object.entrySet()) {
            if (entry.getKey().equals("signature")) { //排除signature
                continue;
            }
            keys.add(entry.getKey());
        }

        Collections.sort(keys);

        StringBuffer sb = new StringBuffer();

        for (String key : keys){
            sb.append(key + "=" + object.get(key) + "&");
        }

        sb.deleteCharAt(sb.length() - 1); //删除最有一个&

        String signature = MD5Utils.getMd5(sb.toString() + md5Key);

        String objSignature = object.getString("signature");

        if (objSignature.equals(signature)) {
            return true;
        }

        return false;

    }

    public static void main(String[] args) {


        String md5Key = "abcabc123"; //签名钥匙



        JSONObject json = new JSONObject(); // 模拟JSON 数据

        json.put("eaaa", "adfea");
        json.put("ebbb", "adfea");
        json.put("e", "adfea");
        json.put("a", "一二三");
        json.put("b", "123");
        json.put("c", "aaa");

        JSONObject retJson = signature(json, md5Key); //得到签名后的JSON数据

        System.out.println(retJson.toJSONString());

        System.out.println("数据签名验证结果 >>> " +  verifySignature(retJson, md5Key));

        retJson.put("c", "bbb"); //模拟篡改数据

        System.out.println("模拟篡改数据 - 数据签名验证结果 >>> " +  verifySignature(retJson, md5Key));
    }
}
