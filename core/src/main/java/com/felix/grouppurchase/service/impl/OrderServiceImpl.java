package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.OrderMapper;
import com.felix.grouppurchase.mapper.UserMapper;
import com.felix.grouppurchase.model.BackCommodity;
import com.felix.grouppurchase.model.Order;
import com.felix.grouppurchase.model.VolumeManage;
import com.felix.grouppurchase.service.IOrderService;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<Integer,Object>map=new HashMap<>();
        int index=0;
        for (Order orderListPO : orderList){
            HashMap<String,Object> map1=new HashMap<>();
            List<VolumeManage> volumeManages=orderMapper.getCommodityById(orderListPO.getCommodityId());
            map1.put("orderData",orderListPO);
            map1.put("CommodityData",volumeManages.get(0));
            map.put(index,map1);
            index++;
        }
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"查询成功",map,callback);
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

    @Override
    public String addBackCommodity(String back_order_id, String user_id, String commodity_id, String commodity_number, String money, String back_reason, String state,String callback){
        JsonTransfer s = new JsonTransfer();
        String stage="7";
        orderMapper.addBackCommodity(back_order_id,user_id,commodity_id,commodity_number,money,back_reason,state);
        orderMapper.updateOrderStage(back_order_id,stage);
        String result = s.result(1,"添加成功","",callback);
        return result;
    }

    @Override
    public String cancelBackCommodity(String order_id, String callback){
        JsonTransfer s = new JsonTransfer();
        BackCommodity backCommodity=orderMapper.getStageByOrderId(order_id);
        orderMapper.updateOrderStage(order_id,backCommodity.getState().toString());
        orderMapper.deleteBackOrder(order_id);
        String result = s.result(1,"添加成功","",callback);
        return result;
    }

}
