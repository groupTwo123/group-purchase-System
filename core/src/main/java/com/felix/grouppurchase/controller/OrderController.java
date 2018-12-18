package com.felix.grouppurchase.controller;

import com.alipay.api.AlipayApiException;
import com.felix.grouppurchase.service.IOrderService;
import com.felix.grouppurchase.util.GetUUID;
import com.felix.grouppurchase.util.JsonTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

    /**
     * @Author fangyong
     * @Description 通过支付宝接口支付订单
     * @Date 2018/12/10 16:36
     * @Param
     * @return
     **/
    @RequestMapping(value = "/alipayToOrder", method = RequestMethod.GET)
    public void alipayToOrder(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                              @RequestParam(value = "money", required = false) String money) throws AlipayApiException, IOException {
        orderService.alipayToOrder(httpRequest, httpResponse, money);
    }

    /**
     * @Author fangyong
     * @Description 用户付款给商家
     * @Date 2018/12/13 14:49 
     * @Param id, sellerId, money, callback
     * @return 
     **/
    @RequestMapping(value = "/userPayToSeller", method = RequestMethod.GET)
    public String userPayToSeller(String id, String sellerId, String money, String callback){
        JsonTransfer s = new JsonTransfer();
        try {
            orderService.userPayToSeller(id, sellerId, money, callback);
            String result = s.result(1,"付款成功","",callback);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            String result = s.result(0,"付款失败","",callback);
            return result;
        }
    }

    /**
     * @Author fangyong
     * @Description 购物车结算后生成订单，购物车记录删除
     * @Date 2018/12/13 15:49
     * @Param
     * @return
     **/
    @RequestMapping(value = "/changeOrderAndShopcar",method = RequestMethod.GET)
    public String changeOrderAndShopcar(String ids,Integer state, String callback){
        String orderId = GetUUID.getUUID();
        JsonTransfer s = new JsonTransfer();
        try{
            orderService.changeOrderAndShopcar(ids.split(","),orderId,state,callback);
            String result = s.result(1,"修改成功","",callback);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            String result = s.result(0,"修改失败","",callback);
            return result;
        }
    }

    /**
     * @Author huangchuwen
     * @Description 根据仓库id获取所有订单
     * @Date 2018/12/14 15:49
     * @Param volumeId
     * @return
     **/
    @RequestMapping(value = "/getOrderByVolumeId",method = RequestMethod.GET)
    public String getOrderByVolumeId(String volumeId, String callback){
        return orderService.getOrderByVolumeId(volumeId,callback);
    }

    /**
     * @Author huangchuwen
     * @Description 根据orderId修改订单状态
     * @Date 2018/12/15 10:47
     * @Param orderId,state,beforeState,money,userId,sellerId
     * @return
     **/
    @RequestMapping(value = "/updateStateByOrderId",method = RequestMethod.GET)
    public String updateStateByOrderId(String orderId, String state,String beforeState, String money, String userId, String sellerId,String callback){
        return orderService.updateStateByOrderId(orderId,state,beforeState,money,userId,sellerId,callback);
    }
}
