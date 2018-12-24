package com.felix.grouppurchase.util;



import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * @Date: 2018/11/28 21:49
 * @Author: fangyong
 */
public class GetSMS {

    /**
     * 用户ID
     */
    public static final String ACCOUNT_SID = "21d5894def2d43879df30586e00e5906";//这里填写你在平台里的ACOUNT_SID

    /**
     * 密钥
     */
    public static final String AUTH_TOKEN = "4f8f8cb991bc4602bba40082a04ba585";

    /**
     * 请求地址前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//请求地址是固定的不用改
    /**
     * 随机码
     */
    public static String randNum = RandUtil.getRandomNum();
    public static String smsContent = "【美购网】尊敬的用户，您的验证码为：" + randNum ;

    /**
     * 获取验证码
     *
     * @param to
     * @return
     */
    public static String getMssage(String to,String callback) {
        randNum = RandUtil.getRandomNum();
        smsContent = "【美购网】尊敬的用户，您的验证码为：" + randNum ;
        String args = SendNumUtil.queryArgs(ACCOUNT_SID, AUTH_TOKEN, smsContent, to);
        OutputStreamWriter out = null;
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(BASE_URL);
            URLConnection connection = url.openConnection();//打开连接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);  //设置链接超时
            connection.setReadTimeout(10000);    //设置读取超时
            out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            out.write(args);
            out.flush();
            //读取返回数据
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonTransfer jsonObject = new JsonTransfer();
        System.out.println(jsonObject);
        String data = sb.toString();
        HashMap<String,String> hashMap= new HashMap<String,String>();
        hashMap.put("code",randNum);
        String result1 = jsonObject.result(1,"respCode",hashMap,callback);
        System.out.println("状态码：" + data + "验证码：" + randNum);
        System.out.println(!data.equals("00000"));
        return result1;
    }
}