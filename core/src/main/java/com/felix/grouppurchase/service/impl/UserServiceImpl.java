package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.UserMapper;
import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date: 2018/11/22 16:03
 * @Author: fangyong
 */
@Service
public class UserServiceImpl implements IUserService {

    private final static transient Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public void registerUser(String id, String userName, String gender, String birth, String phone, String email, String password, String area, String type){
        userMapper.insertRegisterMessage(id, userName, gender, birth, phone, email,password, area, type);
    }
}