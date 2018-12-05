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

    //增加商品到仓库
    String addCommodityById(String[] volumeIds, String commodityName, String commodityNumber,
                            String commodityDescription, String commodityPrice, String callback);

    //根据商品id删除商品
    String delCommodityById(String[] commodityIds, String callback);

    //更新商品
    String updateCommodityById(String commodityId,String commodityName, String commodityNumber,
                         String commodityDescription, String commodityPrice, String callback);

    //根据商品名称模糊查询
    String getCommodityByName(String commodityName, String callback);
}
