package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Date: 2018/11/28 20:38
 * @Author: fangyong
 */
@RestController
@RequestMapping("/gpsys/sendSMS")
public class SendSms {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/sendMessage/register")
    public String sendMessageRegister(String phone,String callback) {
        return userService.checkPhoneRegister(phone, callback);
    }

    @RequestMapping(value = "/sendMessage/login")
    public String sendMessageLogin(String phone,String callback) {
        return userService.checkPhoneLogin(phone, callback);
    }
}