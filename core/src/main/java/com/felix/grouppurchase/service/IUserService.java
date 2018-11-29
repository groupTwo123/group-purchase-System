package com.felix.grouppurchase.service;



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

}
