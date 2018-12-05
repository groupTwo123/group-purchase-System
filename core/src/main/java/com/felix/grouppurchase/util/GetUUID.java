package com.felix.grouppurchase.util;

import java.util.UUID;

/**
 * @ClassName GetUUID
 * @Description 生成uuid
 * @Author fangyong
 * @Date 2018/12/4 10:42
 **/
public class GetUUID {
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] retArray = new String[number];
        for(int i=0;i<number;i++){
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
