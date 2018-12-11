package com.felix.grouppurchase.service;


/**
 * @Date: 2018/11/22 16:02
 * @Author: fangyong
 */
public interface IUserService {

    //会员用户注册
    void registerUser(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type);

    //商家用户注册
    void registerNormal(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type);

    //用户登录
    String login(String id, String password, String callback);

    //登录时检测是否存在该手机号码的用户
    String checkPhoneLogin(String phone, String callback);

    //注册第一步检测该手机号码是否第一次注册
    String checkPhoneRegister(String phone, String callback);

    //修改用户信息
    String updateUserMessage(String id, String userName, String gender, String birth, String phone, String email,  String area, String callback);

    //重置密码
    String resetPassword(String id, String password, String callback);

    //检测输入的商家id是否与输入的手机号码相匹配
    String checkIdWithPhone( String phone, String callback);

    //用户通过手机号码登录获取用户名
    String getUsernameByPhone(String phone, String callback);

    //获取用户信息通过用户Id
    String getUserInfoById(String userId, String callback);
}
