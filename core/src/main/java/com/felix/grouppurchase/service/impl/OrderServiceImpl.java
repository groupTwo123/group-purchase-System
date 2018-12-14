package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.CommodityMapper;
import com.felix.grouppurchase.mapper.OrderMapper;
import com.felix.grouppurchase.mapper.ShopcarMapper;
import com.felix.grouppurchase.mapper.UserMapper;
import com.felix.grouppurchase.model.BackCommodity;
import com.felix.grouppurchase.model.Order;
import com.felix.grouppurchase.model.ShopCar;
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

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    ShopcarMapper shopcarMapper;

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

    @Override
    public void userPayToSeller(String id, String sellerId, String money, String callback) {
        orderMapper.moveOut(id,money);
        orderMapper.moveIn(sellerId,money);
    }

    @Override
    public void changeOrderAndShopcar(String[] ids, String orderId,int state, String callback) {
        List<ShopCar> shopCarList = shopcarMapper.getShopcarById(ids);
        for (ShopCar shopCar : shopCarList) {
            //根据传入的id找到购物车对应的userId
            String userId = shopCar.getUserId();
            //拿到购物车主键id
            int id = shopCar.getId();
            String commodityId = shopCar.getCommodityId();
            VolumeManage volumeManage = commodityMapper.getCommodityDetail(commodityId);
            String orderInfoPrice = volumeManage.getCommodityPrice();
            String orderInfoNumber = shopCar.getCommodityNumber();
            //该参数为  （购物车里面要被结算的商品的数量*该商品的单价=要放入订单内的商品总价）
            Double SumPrice = Double.valueOf(orderInfoPrice) * Integer.parseInt(orderInfoNumber);
            String orderSumPrice = SumPrice.toString();
            //生成订单（被结算的商品）
            orderMapper.addOrder(orderId,userId,commodityId,orderInfoNumber,orderSumPrice,state);
            //购物车结算后，商品仓库中商品的数量应该减少对应的结算商品数量
            commodityMapper.updateCommodityNumber(orderInfoNumber);
            //根据主键id删除购物车记录
            shopcarMapper.delShopcarInfoById(id);
        }
    }

}
