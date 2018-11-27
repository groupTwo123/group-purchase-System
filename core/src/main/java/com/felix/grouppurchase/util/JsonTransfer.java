package com.felix.grouppurchase.util;

import net.minidev.json.JSONObject;

import java.util.HashMap;

/**
 * @Date: 2018/11/26 11:24
 * @Author: fangyong
 */
public class JsonTransfer {

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/26 14:52
    * @Description: 用户注册json格式方法
    * @params:
    */
    public String result(Integer stage, String msg, Object data, String callback){
        HashMap<String, Object> map = new HashMap<>();
        map.put("stage",stage);
        map.put("msg",msg);
        map.put("data",data);
        JSONObject jsonObj = new JSONObject(map);
        String result=callback+"("+jsonObj.toString()+")";
        return result;
    }
}