package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.IUserService;
import com.felix.grouppurchase.util.JsonTransfer;
import com.felix.grouppurchase.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Date: 2018/11/22 16:01
 * @Author: fangyong
 */
@RestController
@RequestMapping("/gpsys/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * @Author: fangyong
     * @date: 2018/11/25 22:20
     * @Description: 会员用户注册
     * @params: id, userName, gender, birth, phone, email, password, area
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String userRegister(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type, String callback) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setGender(gender);
        user.setBirth(birth);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setArea(area);
        user.setType(type);
        if (type.equals(UserUtil.USER_VIP)) {
            userService.registerUser(id, userName, gender, birth, phone, email, password, area, UserUtil.USER_VIP);
        }
        if (type.equals(UserUtil.USER_BUSINESS)){
            userService.registerBusiness(id, userName, gender, birth, phone, email, password, area,UserUtil.USER_BUSINESS, UserUtil.BUSINESS_STAGE1);
        }
        JsonTransfer s = new JsonTransfer();
        try {
            String result1 = s.result(1, "", user , callback);
            return result1;
        } catch (Exception e) {
            e.printStackTrace();
            String result2 = s.result(0, "系统错误", user, callback);
            return result2;
        }
    }
    
    /**
    *
    * @Author: fangyong
    * @date: 2018/11/27 15:56
    * @Description: 用户登录
    * @params: id，password
    */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String id, String password, String callback){
        return userService.login(id, password,callback);
    }
}