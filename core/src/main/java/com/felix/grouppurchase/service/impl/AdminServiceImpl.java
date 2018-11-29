package com.felix.grouppurchase.service.impl;


import com.felix.grouppurchase.mapper.AdminMapper;
import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.AdminService;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2018/11/27 22:58
 * @Author: huangchuwen
 */
@Service
public class AdminServiceImpl  implements AdminService {
    private final static transient Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    AdminMapper adminMapper;


    @Override
    public String getAllAdminType(String callback){
        List<User> adminType= adminMapper.getAllAdminType();
        System.out.print("************");System.out.print(adminType);
        JsonTransfer s = new JsonTransfer();
        String result1 = s.result(1, "",adminType,callback);
        return result1;

    }

}
