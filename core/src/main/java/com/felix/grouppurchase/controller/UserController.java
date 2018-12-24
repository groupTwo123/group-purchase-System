package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.IUserService;
import com.felix.grouppurchase.util.JsonTransfer;
import com.felix.grouppurchase.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


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
//      user.setType(type);
        JsonTransfer s = new JsonTransfer();
        if (user.getId() == null){
            String result1 = s.result(0,"id不能为空","",callback);
            return result1;
        }
        userService.registerUser(id, userName, gender, birth, phone, email, password, area, UserUtil.USER_VIP);
        try {
            String result2 = s.result(1, "注册成功", user, callback);
            return result2;
        } catch (Exception e) {
            e.printStackTrace();
            String result3 = s.result(0, "注册失败", user, callback);
            return result3;
        }
    }

    /**
     * @Author: fangyong
	 * @EditBy: huangchuwen
     * @date: 2018/11/27 15:56
     * @Description: 用户登录
     * @params: id，password
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request,String id, String password, String isOut,String callback) {
        return userService.login(request,id, password,  isOut, callback);
    }



    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 17:44
    * @Description: 验证手机号码与id是否对应
    * @params: sellerId, sellerPhone, callback
    */
    @RequestMapping(value = "/confirmMessage", method = RequestMethod.GET)
    public String confirmMessage(String phone, String callback){
        return userService.checkIdWithPhone( phone, callback);
    }

    /**
     * @Author fangyong
     * @Description 用户通过手机号码登录
     * @Date 2018/12/3 15:36
     * @Param phone,callback
     **/
    @RequestMapping(value = "/getUsernameByPhone", method = RequestMethod.GET)
    public String getUsernameByPhone(String phone, String callback){
        return userService.getUsernameByPhone(phone,callback);
    }

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/30 17:38
    * @Description: 会员用户重置密码
    * @params: id, password, callback
    */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPassword(String id, String password, String callback){
        return userService.resetPassword(id, password, callback);
    }

    /**
     *
     * @Author: fangyong
     * @date: 2018/11/29 10:47
     * @Description: 修改用户信息
     * @params: id,userName,gender,birth,phone,email,area,callback
     */
    @RequestMapping("/updateUserMessage")
    public String  updateUserMessage(String id, String userName, String gender, String birth, String phone, String email,  String area, String callback){
        return userService.updateUserMessage(id,userName,gender,birth,phone,email,area,callback);
    }

    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/11 16:04
     * @Description: 获取用户信息通过用户Id
     * @params: userId, callback
     */
    @RequestMapping("/getUserInfoById")
    public String  getUserInfoById(String userId, String callback){
        return userService.getUserInfoById(userId,callback);
    }

    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/18 11:04
     * @Description: 获取所有用户信息
     * @params:  callback
     */
    @RequestMapping("/getAllUserInfo")
    public String  getAllUserInfo( String callback){
        return userService.getAllUserInfo(callback);
    }

    /**
     *
     * @Author: huangchuwen
     * @date: 2018/12/21 16:04
     * @Description: 增加用户余额
     * @params:  user,money，callback
     */
    @RequestMapping("/addVancy")
    public String  addVancy(String user,Float money, String callback){
        return userService.addVancy(user,money,callback);
    }


}

