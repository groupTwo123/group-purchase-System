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

    /**
     * @Author huangchuwen
     * @Description 退货申请
     * @Date 2018/12/10 15：50
     * @Param back_order_id,user_id,commodity_id,commodity_number,money,back_reason,state,callback
     * @return
     **/

    @RequestMapping(value = "/addBackCommodity", method = RequestMethod.GET)
    public String addBackCommodity(String back_order_id, String user_id, String commodity_id, String commodity_number, String money, String back_reason, String state, String callback){
        return orderService.addBackCommodity(back_order_id,user_id,commodity_id,commodity_number,money,back_reason,state,callback);
    }

    /**
     * @Author huangchuwen
     * @Description 取消退货
     * @Date 2018/12/10 16：48
     * @Param order_id,callback
     * @return
     **/

    @RequestMapping(value = "/cancelBackCommodity", method = RequestMethod.GET)
    public String cancelBackCommodity(String order_id, String callback){
        return orderService.cancelBackCommodity(order_id,callback);
    }



}
