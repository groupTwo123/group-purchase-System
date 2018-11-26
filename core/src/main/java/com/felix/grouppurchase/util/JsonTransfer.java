package com.felix.grouppurchase.util;

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
    public HashMap<String, Object> result(Integer stage, String msg, Object data){
        HashMap<String, Object> map = new HashMap<>();
        map.put("stage",stage);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }
}