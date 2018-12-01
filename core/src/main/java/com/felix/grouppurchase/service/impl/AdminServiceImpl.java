package com.felix.grouppurchase.service.impl;


import com.felix.grouppurchase.mapper.AdminMapper;
import com.felix.grouppurchase.model.Admin;
import com.felix.grouppurchase.model.Seller;
import com.felix.grouppurchase.model.User;
import com.felix.grouppurchase.service.IAdminService;
import com.felix.grouppurchase.util.ErrorCodeDesc;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2018/11/27 22:58
 * @Author: huangchuwen
 */
@Service
public class AdminServiceImpl implements IAdminService {

    private final static transient Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;

    @Override
    public String adminLogin(String adminId, String adminPassword, String callback) {
        Admin admin = adminMapper.adminLogin(adminId, adminPassword);
        JsonTransfer s = new JsonTransfer();
        if (admin == null) {
            String result1 = s.result(0, ErrorCodeDesc.ADMIN_NOEXIST, "", callback);
            return result1;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("adminName", admin.getAdminName());
        String result2 = s.result(1, ErrorCodeDesc.ADMIN_EXIST, map, callback);
        return result2;
    }
}


