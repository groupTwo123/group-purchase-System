package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/11/30 9:19
 * @Author: fangyong
 */
@RequestMapping("/gpsys/seller")
@RestController
public class SellerController {

    @Autowired
    ISellerService sellerService;

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 9:55
    * @Description: 商家注册
    * @params: sellerId,sellerNickName,sellerName,sellerIdentityId,sellerPhone,sellerEmail,storeName,storeArea,callback
    */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String sellerRegister(String sellerId, String sellerNickName, String sellerName, String sellerPassword, String sellerIdentityId, String sellerPhone,
                                 String sellerEmail, String storeName, String storeArea, String callback ){
       return sellerService.sellerRegister(sellerId,sellerNickName,sellerName,sellerPassword,sellerIdentityId,sellerPhone,sellerEmail,storeName,storeArea,callback);
    }


    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 10:08
    * @Description: 商家登录
    * @params: sellerId, sellerPassword, callback
    */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String sellerId, String sellerPassword, String callback) {
        return sellerService.sellerLogin(sellerId, sellerPassword, callback);
    }

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 15:33
    * @Description: 验证手机号码与id是否对应
    * @params: sellerId, sellerPhone, callback
    */
    @RequestMapping(value = "/confirmMessage", method = RequestMethod.GET)
    public String confirmMessage(String sellerId, String sellerPhone, String callback){
        return sellerService.checkIdWithPhone(sellerId, sellerPhone, callback);
    }
    
    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 17:10
    * @Description: 重置密码
    * @params: sellerPassword
    */
    @RequestMapping(value = "/resetPassword",method = RequestMethod.GET)
    public String resetPassword(String sellerId, String sellerPassword, String callback){
        return sellerService.resetPassword(sellerId, sellerPassword, callback);
    }

    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/4 15:52
     * @Description: 商家重置密码前获取手机验证
     * @params: phone
     * @return:id
     */
    @RequestMapping(value = "/checkPhoneExist",method = RequestMethod.GET)
    public String checkPhoneExist(String sellerPhone, String callback){
        return sellerService.checkPhoneExist(sellerPhone, callback);
    }
}