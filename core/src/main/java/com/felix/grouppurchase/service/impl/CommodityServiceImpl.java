package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.CommodityMapper;
import com.felix.grouppurchase.model.CommodityType;
import com.felix.grouppurchase.model.VolumeManage;
import com.felix.grouppurchase.service.ICommodityService;
import com.felix.grouppurchase.util.GetUUID;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.CommandInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2018/11/27 22:58
 * @Author: huangchuwen
 */
@Service
public class CommodityServiceImpl  implements ICommodityService {

    private final static transient Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Autowired
    CommodityMapper commodityMapper;


    @Override
    public String getAllCommodityType(String callback){
        List<CommodityType> commodityType= commodityMapper.getAllCommodityType();
        JsonTransfer s = new JsonTransfer();
        String result1 = s.result(1, "",commodityType,callback);
        return result1;

    }

    @Override
    public  String getAllCommodity( String callback){
        List<VolumeManage> volumeManages=commodityMapper.getAllCommodity();
        JsonTransfer s = new JsonTransfer();
        String result1 = s.result(1, "",volumeManages,callback);
        return result1;
    }

    @Override
    public String getAllCommodityInfo(String[] volumeIds, String callback) {
        List<VolumeManage> commodityInfo = commodityMapper.getAllCommodityInfo(volumeIds);
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"",commodityInfo,callback);
        return result;
    }

    @Override
    public String addCommodityById(String[] volumeIds, String commodityName, String commodityNumber,
                                   String commodityDescription, String commodityPrice, String callback) {
        //查询所有商品信息
        List<VolumeManage> commodityInfo = commodityMapper.getAllCommodityInfo(volumeIds);
        //遍历所有商品
        for (VolumeManage commodityInfoPO : commodityInfo) {
            //判断传入的商品名是否与已存在的商品相同
            //各属性相同的话，则认为是同一种商品，则只是数量增加
            if ((commodityName.equals(commodityInfoPO.getCommodityName()) && commodityDescription.equals(commodityInfoPO.getCommodityDescription())
                    && commodityPrice.equals(commodityInfoPO.getCommodityPrice()))) {
                Integer newCommodityNumberInt = (commodityInfoPO.getCommodityNumber()).intValue() + Integer.parseInt(commodityNumber);
                String newCommodityNumberStr = String.valueOf(newCommodityNumberInt);
                //修改数量：原先的数量+传入的数量
                commodityMapper.updateCommodityAddNumber(volumeIds, newCommodityNumberStr);
            } else {
                for (String volumeId : volumeIds) {
                    commodityMapper.addCommodityById(volumeId, GetUUID.getUUID(), commodityName, commodityNumber, commodityDescription, commodityPrice);
                }
            }
        }
        if (commodityInfo.isEmpty()) {
            for (String volumeId : volumeIds) {
                commodityMapper.addCommodityById(volumeId, GetUUID.getUUID(), commodityName, commodityNumber, commodityDescription, commodityPrice);
            }
        }

        JsonTransfer s = new JsonTransfer();
        try {
            String result1 = s.result(1, "添加成功", "", callback);
            return result1;
        } catch (Exception e) {
            e.getMessage();
            String result2 = s.result(0, "添加失败", e, callback);
            return result2;
        }
    }

    @Override
    public String delCommodityById(String[] commodityIds, String callback) {
        JsonTransfer s = new JsonTransfer();
        if (commodityIds == null){
            String result2 = s.result(0,"请选择存在的商品进行删除","",callback);
            return result2;
        }
        commodityMapper.delCommodityById(commodityIds);
        String result1 = s.result(1,"删除成功","",callback);
        return result1;
    }

    @Override
    public String updateCommodityById(String commodityId,String commodityName, String commodityNumber,
                                String commodityDescription, String commodityPrice, String callback) {
        commodityMapper.updateCommodityById(commodityId,commodityName, commodityNumber,commodityDescription,commodityPrice);
        JsonTransfer s = new JsonTransfer();
        try{
            String result1 = s.result(1,"更新成功","",callback);
            return result1;
        }catch (Exception e){
            e.getMessage();
            String result2 = s.result(0,"更新失败",e,callback);
            return result2;
        }
    }

    @Override
    public String getCommodityByName(String commodityName, String callback) {
        VolumeManage volumeManage = commodityMapper.getCommodityByName(commodityName);
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(volumeManage);
        JsonTransfer s = new JsonTransfer();
        if (volumeManage == null){
            String result1 = s.result(0,"查询失败","",callback);
            return result1;
        }else {
            String result2 = s.result(1,"查询成功",arrayList,callback);
            return result2;
        }

    }

}
