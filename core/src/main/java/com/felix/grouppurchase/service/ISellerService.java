package com.felix.grouppurchase.service;

/**
 * @Date: 2018/11/30 9:20
 * @Author: fangyong
 */
public interface ISellerService {

    //商家注册
    String sellerRegister(String sellerId, String sellerNickName, String sellerName, String sellerPassword, String sellerIdentityId, String sellerPhone, String sellerEmail, String storeName, String storeArea, String callback);

    //商家登录
    String sellerLogin(String sellerId, String sellerPassword, String callback);

    //检测该手机号码是否已注册
    String checkPhoneRegister(String seller_phone, String callback);

    //手机获取验证码登录检测是否存在该商家
    String checkPhoneLogin(String sellerPhone, String callback);

    //检测输入的商家id是否与输入的手机号码相匹配
    String checkIdWithPhone(String sellerId, String sellerPhone, String callback);

    //重置密码
    String resetPassword(String sellerId, String sellerPassword, String callback);

    //检测手机是否存在
    String checkPhoneExist(String phone, String callback);

    //查找商家信息通过商家Id
    String getSellerInfoById(String sellerId, String callback);
}