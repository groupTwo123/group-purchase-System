package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.OrderMapper;
import com.felix.grouppurchase.mapper.UserMapper;
import com.felix.grouppurchase.model.Order;
import com.felix.grouppurchase.service.IOrderService;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2018/11/28 20:38
 * @Author: fangyong
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private final static transient Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public String getOrderByUserId(String userId, String callback) {
        List<Order> orderList = orderMapper.getOrderByUserId(userId);
        List<Object> list = new ArrayList<>();
        for (Order orderListPO : orderList){
            orderListPO.getOrderId();
            orderListPO.getCommodityId();
            orderListPO.getMoney();
            orderListPO.getCommodityNumber();
            orderListPO.getRebatePrice();
            orderListPO.getState();
            list.add(orderListPO);
        }
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"查询成功",list,callback);
        return result;
    }

    @Override
    public String delOrderByOrderId(String[] orderIds, String callback) {
        JsonTransfer s = new JsonTransfer();
        try {
            orderMapper.delOrderByOrderId(orderIds);
            String result1 = s.result(1,"删除成功","",callback);
            return result1;
        }catch (Exception e){
            e.getMessage();
            String result2 = s.result(0,"删除失败",e,callback);
            return result2;
        }
    }

}
