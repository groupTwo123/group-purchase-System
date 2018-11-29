package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.util.GetSMS;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Date: 2018/11/28 20:38
 * @Author: fangyong
 */
@RestController
@RequestMapping("/gpsys/sendSMS")
public class SendSms {

    @RequestMapping(value = "/sendMessage")
    public String sendMessage(String to,String callback) {
        return GetSMS.getMssage(to,callback);
    }
}