package com.felix.grouppurchase.service;


/**
 * @Date: 2018/11/27 22:50
 * @Author: huangchuwen
 */
public interface IAdminService {

    //管理员登录
    String adminLogin(String adminId, String adminPassword, String callback);
}
