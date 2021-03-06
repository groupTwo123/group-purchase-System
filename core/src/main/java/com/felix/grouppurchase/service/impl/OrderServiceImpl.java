package com.felix.grouppurchase.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.felix.grouppurchase.mapper.*;
import com.felix.grouppurchase.model.*;
import com.felix.grouppurchase.service.IOrderService;
import com.felix.grouppurchase.util.GetUUID;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.alipay.api.AlipayConstants.CHARSET_UTF8;

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
    @Autowired
    SellerMapper sellerMapper;

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
        String stage="";
        if(state.equals("3")||state.equals("4")){
             stage="7";
        }
        else{
             stage="5";
        }
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

    @Override
    public String getOrderByVolumeId(String volumeId, String callback) {
        List<VolumeManage> volumeManages=commodityMapper.getAllCommodityInfoById(volumeId);
        List list= new ArrayList();
        for(VolumeManage volumeManagePo:volumeManages){
            List<Order> list1 = orderMapper.getOrderByCommodityId(volumeManagePo.getCommodityId());
            if(!list1.isEmpty()){
                for(Order orderPo:list1){
                    list.add(orderPo);
                }
            }
        }
        JsonTransfer s=new JsonTransfer();
        String result=s.result(1,"添加成功",list,callback);
        return result;
    }

    @Override
    public String updateStateByOrderId(String orderId, String state,String beforeState, String money, String userId, String sellerId, String callback) {
        if(state.equals("6")){
            if(!(beforeState.equals("0"))){
                userMapper.updateAccountData(userId,Float.parseFloat(money));
                sellerMapper.updateAccountData(sellerId,Float.parseFloat(money));
            }
        }
        else if(state.equals("9")){
            userMapper.updateAccountData(userId,Float.parseFloat(money));
            sellerMapper.updateAccountData(sellerId,Float.parseFloat(money));
            Order order=orderMapper.getCommodityByOrderId(orderId);
            commodityMapper.addCommodityNumberByCommodityId(order.getCommodityId(),Integer.parseInt(order.getCommodityNumber()) );
        }
        orderMapper.updateOrderStage(orderId,state);
        JsonTransfer s=new JsonTransfer();
        String result=s.result(1,"修改成功","",callback);
        return result;
    }

    @Override
    public void alipayToOrder(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String money)throws AlipayApiException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016092400584934",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCLCEdoUJis2BmuZlbmRaeebtP2e2hcPh4S/CKxsiJFCGueiJDRvB4xalKYyHp7rwzaxJ3TLBYQ1jg+muPY/1W0ldx3Le/cqCXVlEjpdhA8ZT1ug2yxN8WMKpWw9hilN2vqZbWs8XHGyVPqFN9TxCXFh9yXeDes3rYCV+qd5DmyYYrainSViVa5BYVV7+H3eIeb7Yn5ccqWeXQFQ+wSTztzSYwoHB4x7C+3kp95ZetBKmlLtydt3I4pXyDszwisLgnDWJZu6q/A6GMU0korJ3HIvWysJXVg/fhMZyNmMEartWrU+nVBpUV64oWdghWah1fuWaorMN2F3J0R80npwDntAgMBAAECggEASL+IPbkOakKZDXxXNB52Wpw7JNnl/X4zVlPAA6Ll86E4Tre+rz/zvH+ZwIAGNvmdjEsdGgYTdbPhskBxwHW6bseLmIDc5WTGhz8T97TjQfbnchAqq4A3SeRudy4OL0UUno2hxmB/3Dg0RGOvh7E24mj4r/kjiLzwF8HQ7dUhq4hAPm1nywiDF1wQ7RMizl/rsHvfd95rltHaXs/1apIffRJ4TU/EpyHM1aqURJnAhYrNiggu7rbnqOPFCOx5KRYRDqJqqAl10sU3xgIdzVTIYBvVY2A4cLjaa69I1O4GbqxOAof/om12Ws6n2m00YOj2fNCb3zja66U8gBR5xbo4AQKBgQDPi+HrygdefsXK0GR7rasr0JyruEIJws8rU1Vu3bwnp01l0JdZCr6epdK7N76T0vtyu5WPaoyb4SwRe/R05Uz3RQALjlsjvgBiHM6XFNU2AKvNn5pnixC3Vj9fmDNt9IDA//9XrnfjLgarZlTihUJQxiqOzqci62n8XLsOL3ZPbQKBgQCrfZ4gRwqJ83v03KYvmeY/VV4RMyM+1PA9Zw1dJTqz44A+1GXKFp3YUnaRZdSSpoG3IbER0zEzlwmJ9MTxM24uI8finvRvGp+KonpW6Om05FDiz1h4doK3JeumgluZ0qCyM6fgT74V4ig/UURwwrGEpnlG5n3EuPb4svFjhCaEgQKBgBE33KVQHG5lfAlIYp20GbhH1UeVwLzIkaFZh4PsF1j+0zf4JyY28vt8bFAl3sSqwzuQfmbogwPHmX1V2ED+aPU2nztWMw9zCtsY4ra7/rg9NAOaExg1/EwJdrZ6jQG5QBlC468lhUKupdUAZ1hnj8ndttgkIs+N0jwigU4698i9AoGAPHLKEzyXZWsTLYu9dJf+BiMURmHNMa+jgzFZD5U47QHrSbpaqvJv7zmaG8RWOt9AaAoTPWJBPJsnvJ4v8Ymcc90e7y3yoVs0E40ULKBrCN1V+az21XeIugA+4XGf0kfhZw84RKQ3L8WJRFBkHI80Pbrkku5aNMayH5YL6nVBkoECgYAYYA3LUGbDyf8ZF4BFolX9ZtlC15snBOo9A1M6Qbrez0HoBoRvxqiBblOxO6FP1AUWPudifBZx4MBKGiGpTMMQGFWJB9k8HOYxv+7SZO79QrSdB/jZyO6eDqVUqHKnREjx1wQrnL+AwwfkKq7TZCqxyxO9JayVtBZWQoUSuLPHEA==",
                "json",
                CHARSET_UTF8,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiwhHaFCYrNgZrmZW5kWnnm7T9ntoXD4eEvwisbIiRQhrnoiQ0bweMWpSmMh6e68M2sSd0ywWENY4Pprj2P9VtJXcdy3v3Kgl1ZRI6XYQPGU9boNssTfFjCqVsPYYpTdr6mW1rPFxxslT6hTfU8QlxYfcl3g3rN62AlfqneQ5smGK2op0lYlWuQWFVe/h93iHm+2J+XHKlnl0BUPsEk87c0mMKBweMewvt5KfeWXrQSppS7cnbdyOKV8g7M8IrC4Jw1iWbuqvwOhjFNJKKydxyL1srCV1YP34TGcjZjBGq7Vq1Pp1QaVFeuKFnYIVmodX7lmqKzDdhdydEfNJ6cA57QIDAQAB",
                "RSA2");
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:4200/payReturn");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":"+GetUUID.getOrderIdByTime()+"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+money+"," +
                "    \"subject\":"+GetUUID.getOrderIdByTime()+"," +
                "    \"body\":"+GetUUID.getOrderIdByTime()+"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET_UTF8);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }



}
