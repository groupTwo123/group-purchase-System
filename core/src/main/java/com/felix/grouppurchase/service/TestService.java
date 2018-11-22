package com.felix.grouppurchase.service;

import com.felix.grouppurchase.mapper.TestMapper;
import com.felix.grouppurchase.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2018/11/22 10:11
 * @Author: fangyong
 */
@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public Test getTest(Integer id){
        Test test = testMapper.getNameById(id);
        return test;
    }
}