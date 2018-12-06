package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/11/28 20:38
 * @Author: fangyong
 */
@RestController
@RequestMapping("/gpsys/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * @Author fangyong
     * @Description 获取所有订单
     * @Date 2018/12/5 15:25 
     * @Param userId, callback
     * @return 
     **/
    @RequestMapping(value = "/getOrderByUserId", method = RequestMethod.GET)
    public String getOrderByUserId(String userId, String callback){
        return orderService.getOrderByUserId(userId, callback);
    }

    /**
     * @Author fangyong
     * @Description 删除订单
     * @Date 2018/12/5 16:25
     * @Param orderIds,callback
     * @return
     **/

    @RequestMapping(value = "/delOrderByOrderId", method = RequestMethod.GET)
    public String delOrderByOrderId(String orderIds, String callback){
        return orderService.delOrderByOrderId(orderIds.split(","), callback);
    }



}
