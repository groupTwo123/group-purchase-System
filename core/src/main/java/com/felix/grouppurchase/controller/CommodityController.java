package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.service.ICommodityService;
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
    ICommodityService commodityService;

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

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/29 14:45
    * @Description: 获取仓库中所有商品信息
    * @params:
    */
    @RequestMapping(value = "/getCommodityInfo",method = RequestMethod.GET)
    public String getCommodityInfo(String volumeIds, String callback){
        return commodityService.getAllCommodityInfo(volumeIds.split(","), callback);
    }
}
