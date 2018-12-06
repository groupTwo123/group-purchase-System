package com.felix.grouppurchase.service;

/**
 * @Date: 2018/11/28 20:38
 * @Author: fangyong
 */
public interface IOrderService {

    //根据用户id获取所有订单
    String getOrderByUserId(String userId, String callback);

    //根据订单id删除订单
    String delOrderByOrderId(String[] orderIds, String callback);
}
