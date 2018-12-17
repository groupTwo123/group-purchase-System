package com.felix.grouppurchase.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 2018/11/28 20:38
 * @Author: fangyong
 */
public interface IOrderService {

    //根据用户id获取所有订单
    String getOrderByUserId(String userId, String callback);

    //根据订单id删除订单
    String delOrderByOrderId(String[] orderIds, String callback);

    String addBackCommodity(String back_order_id, String user_id, String commodity_id, String commodity_number, String money, String back_reason, String state,String callback);

    String cancelBackCommodity(String order_id, String callback);

    //用户付款给商家
    @Transactional
    void userPayToSeller(String id, String sellerId, String money, String callback);

    //购物车结算后生成订单，购物车记录删除
    @Transactional
    void changeOrderAndShopcar(String[] ids,String orderId,int state, String callback);

    //根据仓库id获取所有订单
    String getOrderByVolumeId(String volumeId, String callback);

    //根据orderId修改订单状态
    String updateStateByOrderId(String orderId, String state,String beforeState, String money, String userId, String sellerId, String callback);
}
