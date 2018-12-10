package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.ShopcarMapper;
import com.felix.grouppurchase.model.ShopCar;
import com.felix.grouppurchase.service.IShopcarService;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ObjectStreamClass;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ShopcarServiceImpl
 * @Description 购物车service实现类
 * @Author fangyong
 * @Date 2018/12/5 16:50
 **/
@Service
public class ShopcarServiceImpl implements IShopcarService {

    private final static transient Logger logger = LoggerFactory.getLogger(ShopcarServiceImpl.class);

    @Autowired
    ShopcarMapper shopcarMapper;

    @Override
    public String getShopcarInfo(String userId, String callback) {
        List<ShopCar> shopCarList = shopcarMapper.getShopcarInfo(userId);
        HashMap<Integer,Object> mapObj = new HashMap<>();
        int index=0;
        for (ShopCar shopCarListPO : shopCarList){
            HashMap<String,Object> map = new HashMap<>();
            map.put("commodityNumber",shopCarListPO.getCommodityNumber());
            map.put("commodityData",(shopcarMapper.getCommodityData(shopCarListPO.getVolumeId(),shopCarListPO.getCommodityId())).get(0));
            mapObj.put(index,map);
            index++;
        }
        JsonTransfer s = new JsonTransfer();
        if (shopCarList.isEmpty()){
            String result = s.result(1,"查询失败，无此用户","",callback);
            return result;
        }else {
            String result = s.result(1, "查询成功", mapObj, callback);
            return result;
        }
    }

    @Override
    public String delShopcarInfo(String[] commodityIds, String userId,String callback) {
//        int shopCarList = shopcarMapper.getAllShopcarInfo(commodityIds);
        JsonTransfer s = new JsonTransfer();
        try {
//            if (shopCarList == 0) {
//                String result2 = s.result(0, "删除失败", "", callback);
//                return result2;
//            }
            shopcarMapper.delShopcarInfo(commodityIds,userId);
            String result1 = s.result(1, "删除成功", "", callback);
            return result1;
        } catch (Exception e) {
            e.getMessage();
            String result2 = s.result(0, "删除失败", e, callback);
            return result2;
        }
    }
    @Override
    public  String changeShoppingCarVolumeNumById(String commodityId, String changeNum, String userId, String callback) {
        String result="";
        JsonTransfer s = new JsonTransfer();
        try{
            shopcarMapper.changeShoppingCarVolumeNumById(commodityId,changeNum,userId);
            result=s.result(1,"修改成功","",callback);
        }
        catch (SQLException e){
            result=s.result(0,e.toString(),"",callback);
        }
        return result;
    }
}
