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
    public void registerBusiness(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type, String stage) {
        userMapper.insertRegisterBusinessMessage(id, userName, gender, birth, phone, email, password, area, type, stage);
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

}