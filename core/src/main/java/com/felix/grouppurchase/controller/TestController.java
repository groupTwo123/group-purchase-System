package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.model.Test;
import com.felix.grouppurchase.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date: 2018/11/22 10:11
 * @Author: fangyong
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test/{id}")
    public Test getName(@PathVariable("id") Integer id){
        Test test = testService.getTest(id);
        return test;
    }

}