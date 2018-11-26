package com.felix.grouppurchase.service;

import com.felix.grouppurchase.model.User;


/**
 * @Date: 2018/11/22 16:02
 * @Author: fangyong
 */
public interface IUserService {

    //用户注册
    void registerUser(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type);
}
