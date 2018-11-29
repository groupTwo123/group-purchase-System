package com.felix.grouppurchase.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date: 2018/11/28 21:47
 * @Author: fangyong
 */
public class SendNumUtil {

    /**
     * 获取时间戳工具
     *
     * @return
     */
    public static String getTimeStamp() {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String timeStamp = format.format(date);
        System.out.println("时间戳："+timeStamp);
        return timeStamp;

    }

    /**
     * 发送验证码
     *
     * @param ACCOUNT_SID
     * @param AUTH_TOKEN
     * @param smsContent
     * @param to
     * @return
     */
    public static String queryArgs(String ACCOUNT_SID, String AUTH_TOKEN, String smsContent, String to) {

        String timeStamp = getTimeStamp();
        String sig=MD5(ACCOUNT_SID,AUTH_TOKEN,timeStamp);
        String str = "accountSid="+ACCOUNT_SID+"&smsContent="+
                smsContent+"&to="+to+"&timestamp="+timeStamp+"&sig="+sig;
        System.out.println("参数："+str);
        return str;

    }

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

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        System.out.println("加密后：\t"+result.toString());
        return result.toString();
    }
}