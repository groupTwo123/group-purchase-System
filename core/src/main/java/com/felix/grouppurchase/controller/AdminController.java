package com.felix.grouppurchase.controller;


import com.felix.grouppurchase.service.AdminService;
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
    AdminService adminService;

    /**
     * @Author: huangchuwen
     * @date: 2018/11/27 22:46
     * @Description: 获取所有商品类型
     * @params: callback
     */
    @RequestMapping(value = "/getAdminType", method = RequestMethod.GET)
    public String getAdminType( String callback){

        return adminService.getAllAdminType(callback);
    }
}
