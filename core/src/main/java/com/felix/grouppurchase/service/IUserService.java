package com.felix.grouppurchase.service;


import com.felix.grouppurchase.model.User;

/**
 * @Date: 2018/11/22 16:02
 * @Author: fangyong
 */
public interface IUserService {

    //会员用户注册
    void registerUser(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type);

    //商家用户注册
    void registerBusiness(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type, String stage);

    //用户登录
    String login(String id, String password, String callback);

    //登录时检测是否存在该手机号码的用户
    String checkPhoneLogin(String phone, String callback);

    //注册第一步检测该手机号码是否第一次注册
    String checkPhoneRegister(String phone, String callback);

    //忘记密码，修改用户信息
    String updateUserMessage(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String callback);
}
