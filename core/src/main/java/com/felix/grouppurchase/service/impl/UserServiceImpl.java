package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.UserMapper;
import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.IUserService;
import com.felix.grouppurchase.util.ErrorCodeDesc;
import com.felix.grouppurchase.util.GetSMS;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Date: 2018/11/22 16:03
 * @Author: fangyong
 */
@Service
public class UserServiceImpl implements IUserService{

    private final static transient Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public void registerUser(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type) {
        userMapper.insertRegisterMessage(id, userName, gender, birth, phone, email, password, area, type);
    }

    @Override
    public void registerNormal(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type) {
        userMapper.insertRegisterNormalMessage(id, userName, gender, birth, phone, email, password, area, type);
    }

    @Override
    public String login(String id, String password, String callback) {
        User user =  userMapper.userLogin(id, password);
        JsonTransfer s = new JsonTransfer();
        if (user == null){
            String result1 = s.result(0,ErrorCodeDesc.USER_NOEXIST,"",callback);
            return result1;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUserName());
        map.put("type",user.getType());
        String result2 = s.result(1,ErrorCodeDesc.USER_EXIST,map,callback);
        return result2;
    }

    @Override
    public String checkPhoneLogin(String phone, String callback) {
        User user =  userMapper.checkPhone(phone);
        JsonTransfer s = new JsonTransfer();
        if (user == null){
            String result1 = s.result(0,ErrorCodeDesc.USER_NOEXIST,"",callback);
            return result1;
        }
        return GetSMS.getMssage(phone,callback);
    }

    @Override
    public String checkPhoneRegister(String phone, String callback) {
        User user =  userMapper.checkPhone(phone);
        JsonTransfer s = new JsonTransfer();
        if (user == null){
            return GetSMS.getMssage(phone,callback);
        }
        String result1 = s.result(0,ErrorCodeDesc.USER_EXIST,"",callback);
        return result1;
    }

    @Override
    public String updateUserMessage(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String callback) {
        userMapper.updateUserMessage(id,userName,gender,birth,phone,email,password,area);
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"修改成功","",callback);
        return result;
    }

    @Override
    public String checkIdWithPhone(String id, String phone, String callback) {
        String user = userMapper.checkIdWithPhone(id, phone);
        JsonTransfer s = new JsonTransfer();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id",id);
        if (user.equals("0")){
            String result1 = s.result(0,"找不到该手机号匹配的ID，请重新输入","",callback);
            return result1;
        }
        String result2 = s.result(1,"匹配成功",map,callback);
        return result2;
    }

    @Override
    public String getUsernameByPhone(String phone, String callback) {
        User user = userMapper.getUsernameByPhone(phone);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("userName",user.getUserName());
        JsonTransfer s = new JsonTransfer();
        if (user.getPhone() == null){
            String result1 = s.result(0,ErrorCodeDesc.USER_NOEXIST,"",callback);
            return result1;
        }
        String result2 = s.result(1,"登录成功",map,callback);
        return result2;

    }

    @Override
    public String resetPassword(String id, String password, String callback) {
        userMapper.resetPassword(id, password);
        JsonTransfer s = new JsonTransfer();
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("password",password);
        try {
            String result1 = s.result(1,"修改成功","map",callback);
            return result1;
        }catch (Exception e){
            e.printStackTrace();
            String result2 = s.result(0,"系统异常，修改失败",map,callback);
            return result2;
        }
    }

}