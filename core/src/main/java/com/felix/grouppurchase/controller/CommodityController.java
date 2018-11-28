package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.model.CommodityType;
import com.felix.grouppurchase.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/11/27 22:46
 * @Author: huangchuwen
 */
@RestController
@RequestMapping("/gpsys/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    /**
     * @Author: huangchuwen
     * @date: 2018/11/27 22:46
     * @Description: 获取所有商品类型
     * @params: callback
     */
    @RequestMapping(value = "/getCommodityType", method = RequestMethod.GET)
    public String getCommodityType( String callback){

        return commodityService.getAllCommodityType(callback);
    }
}
