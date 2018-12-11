package com.felix.grouppurchase.service;

public interface IShopcarService {

    //查看购物车详情
    String getShopcarInfo(String userId,String callback);

    //删除购物车商品
    String delShopcarInfo(String[] commodityIds,String userId, String callback);
    //通过volume_id改变购物车数量
    String changeShoppingCarVolumeNumById(String commodityId, String changeNum, String userId,String callback);
    //增加购物车
    String addShoppingCar(String commodityId, String commodityNumber, String volume_id, String user_id, String callback);
}
