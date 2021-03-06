package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.service.IShopcarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShopcarController
 * @Description 购物车接口
 * @Author fangyong
 * @Date 2018/12/5 16:47
 **/
@RestController
@RequestMapping("/gpsys/shopcar")
public class ShopcarController {

    @Autowired
    IShopcarService shopcarService;

    /**
     * @Author fangyong
     * @Description 查看购物车详情
     * @Date 2018/12/5 17:21
     * @Param userId,callback
     * @return
     **/

    @RequestMapping(value = "/getShopcarInfo",method = RequestMethod.GET)
    public String getShopcarInfo(String userId, String callback){
        return shopcarService.getShopcarInfo(userId,callback);
    }

    /**
     * @Author fangyong
     * @Description 根据商品id删除购物车商品
     * @Date 2018/12/5 17:22
     * @Param
     * @return
     **/
    @RequestMapping(value = "/delShopcarInfo",method = RequestMethod.GET)
    public String delShopcarInfo(String commodityIds, String userId,String callback){
        return shopcarService.delShopcarInfo(commodityIds.split(","), userId, callback);
    }
	/**
     * @Author huangchuwen
     * @Description 通过volume_id改变购物车数量
     * @Date 2018/12/7 15:28
     * @Param volume_id,changeNum,callback
     * @return
     **/

    @RequestMapping(value = "/changeShoppingCarVolumeNumById",method = RequestMethod.GET)
    public String changeShoppingCarVolumeNumById(String commodityId, String changeNum, String userId,String callback){
        return shopcarService.changeShoppingCarVolumeNumById(commodityId, changeNum,userId,callback);
    }

    /**
     * @Author huangchuwen
     * @Description 增加购物车
     * @Date 2018/12/11 15：27
     * @Param commodityId，commodityNumber, volume_id, user_id, callback
     * @return
     **/

    @RequestMapping(value = "/addShoppingCar",method = RequestMethod.GET)
    public String addShoppingCar(String commodityId, String commodityNumber, String volume_id, String user_id, String callback){
        return shopcarService.addShoppingCar(commodityId, commodityNumber,volume_id, user_id,callback);
    }

}
