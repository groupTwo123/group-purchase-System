package com.felix.grouppurchase.service;

public interface IShopcarService {

    //查看购物车详情
    String getShopcarInfo(String userId,String callback);

    //删除购物车商品
    String delShopcarInfo(String[] commodityIds, String callback);
}
