package com.felix.grouppurchase.service;


/**
 * @Date: 2018/11/27 22:50
 * @Author: huangchuwen
 */
public interface ICommodityService {

    //获取所有商品类别
    String getAllCommodityType(String callback);

    //获取仓库中所有商品的信息
    String getAllCommodityInfo(String[] volumeIds, String callback);
}
