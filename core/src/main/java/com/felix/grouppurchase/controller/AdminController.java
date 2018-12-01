package com.felix.grouppurchase.controller;


import com.felix.grouppurchase.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/11/27 22:46
 * @Author: huangchuwen
 */
@RestController
@RequestMapping("/gpsys/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 11:36
    * @Description: 管理员登录
    * @params:
    */
    @RequestMapping(value = "/adminLogin",method = RequestMethod.GET)
    public String adminLogin(String adminId, String adminPassword, String callback){
        return adminService.adminLogin(adminId,adminPassword,callback);
    }
}
