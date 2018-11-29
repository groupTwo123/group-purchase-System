
package com.felix.grouppurchase.util;


import java.security.MessageDigest;



/**
 * @Date: 2018/11/28 20:30
 * @Author: fangyong
 */

public class MD5Util {
    //MD5算法
    public static String MD5(String... args){ //动态参数
        StringBuffer result = new StringBuffer();
        if (args == null || args.length == 0) {
            return "";
        } else {
            StringBuffer str = new StringBuffer();
            for (String string : args) {
                str.append(string);
            }
            System.out.println("加密前：\t"+str.toString());

            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(str.toString().getBytes());
                for (byte b : bytes) {
                    String hex = Integer.toHexString(b&0xff);  //转化十六进制
                    if (hex.length() == 1) {
                        result.append("0"+hex);
                    }else{
                        result.append(hex);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("加密后：\t"+result.toString());
        return result.toString();
    }

}
