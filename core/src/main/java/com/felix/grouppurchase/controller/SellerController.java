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

    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/11 14:47
     * @Description: 通过登录名获取商家信息
     * @params: sellerId
     * @return:
     */
    @RequestMapping(value = "/getSellerInfoById",method = RequestMethod.GET)
    public String getSellerInfoById(String sellerId, String callback){
        return sellerService.getSellerInfoById(sellerId, callback);
    }
    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/13 9:12
     * @Description: 商家修改账号信息
     * @params: sellerId, storeName, sellerNickname, sellerName, sellerIdentityId, storeArea, sellerPhone
     * @return:
     */
    @RequestMapping(value = "/updateSellerInfo",method = RequestMethod.GET)
    public String updateSellerInfo(String sellerId, String storeName,String sellerNickname,String sellerName, String sellerIdentityId, String storeArea, String sellerEmail, String callback){
        return sellerService.updateSellerInfo(sellerId,storeName, sellerNickname, sellerName, sellerIdentityId, storeArea, sellerEmail, callback);
    }

    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/15 15:12
     * @Description: 通过仓库id获取商家信息
     * @params: volumeId,callback
     * @return:
     */
    @RequestMapping(value = "/getSellerInfoByVolumeId",method = RequestMethod.GET)
    public String getSellerInfoByVolumeId(String volumeId, String callback){
        return sellerService.getSellerInfoByVolumeId(volumeId, callback);
    }
    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/17 9:31
     * @Description: 获取所有商家信息
     * @params: callback
     * @return:SellerObj
     */
    @RequestMapping(value = "/getAllSeller",method = RequestMethod.GET)
    public String getAllSeller(String callback){
        return sellerService.getAllSeller(callback);
    }
    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/17 11:14
     * @Description: 修改账号状态
     * @params:state,sellerId, callback
     * @return:
     */
    @RequestMapping(value = "/updateSellerState",method = RequestMethod.GET)
    public String updateSellerState(String state,String sellerId, String callback){
        return sellerService.updateSellerState(state,sellerId,callback);
    }


}