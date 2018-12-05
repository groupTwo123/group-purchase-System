package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.service.ISellerService;
import com.felix.grouppurchase.service.IUserService;
import com.felix.grouppurchase.util.GetSMS;
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

    @Autowired
    ISellerService sellerService;
    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 11:43
    * @Description: 用户注册手机号码验证，通过发送短信的方式
    * @params: phone, callback
    */
    @RequestMapping(value = "/sendMessage/register")
    public String sendMessageUserRegister(String phone,String callback) {
        return userService.checkPhoneRegister(phone, callback);
    }

    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/3 17:18
     * @Description: 忘记密码，发送短信
     * @params: phone, callback
     * @return:code
     */
    @RequestMapping(value = "/sendMessage/forgetPassword")
    public String sendMessageUserForgetPassword(String phone,String callback) {
        return GetSMS.getMssage(phone,callback);
    }
    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/4 9:32
     * @Description: 发送短信共有方法
     * @params: phone, callback
     * @return:code
     */
    @RequestMapping(value = "/sendMessage/sendCode")
    public String sendCode(String phone,String callback) {
        return GetSMS.getMssage(phone,callback);
    }

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 11:43
    * @Description: 用户登录手机号码验证，通过发送短信的方式
    * @params: phone, callback
    */
    @RequestMapping(value = "/sendMessage/login")
    public String sendMessageUserLogin(String phone,String callback) {
        return userService.checkPhoneLogin(phone, callback);
    }
    
    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 11:44
    * @Description: 商家注册验证手机号码是否已经注册
    * @params: phone, callback
    */
    @RequestMapping(value = "/sendMessage/sellerRegister")
    public String sendMessageSellerRegister(String sellerPhone, String callback){
        return sellerService.checkPhoneRegister(sellerPhone, callback);
    }

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 14:54
    * @Description: 商家通过手机号码获取验证码登录
    * @params:sellerPhone
     * @return:id,username
    */
    @RequestMapping(value = "/sendMessage/sellerLogin")
    public String sendMessageSellerLogin(String sellerPhone,String callback) {
        return sellerService.checkPhoneLogin(sellerPhone, callback);
    }
}