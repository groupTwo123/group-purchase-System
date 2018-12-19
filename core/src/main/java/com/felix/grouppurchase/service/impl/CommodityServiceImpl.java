package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.CommodityMapper;
import com.felix.grouppurchase.model.*;
import com.felix.grouppurchase.service.ICommodityService;
import com.felix.grouppurchase.util.GetUUID;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2018/11/27 22:58
 * @Author: fangyong
 */
@Service
@Component
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
        HashMap<Integer,Object> mapObj=new HashMap<>();

        int index=0;
        for(VolumeManage manage : volumeManages){
            Seller sellers=commodityMapper.getSellerInfoByVolumeId(manage.getVolumeId());
            HashMap<String,Object> map=new HashMap<>();
            map.put("volumeData",manage);
            map.put("seller_id",sellers.getSellerId());
            map.put("store_name",sellers.getStoreName());
            mapObj.put(index,map);
            index++;
        }
        JsonTransfer s = new JsonTransfer();
        String result1 = s.result(1, "",mapObj,callback);
        return result1;
    }

    @Override
    public String getCommodityDetail(String commodityId, String callback) {
        VolumeManage volumeDetail =  commodityMapper.getCommodityDetail(commodityId);
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"查看成功",volumeDetail,callback);
        return result;
    }

    @Override
    public String addCommodityToShopCar(String commodityId,String commodityNumber,String callback) {
        ShopCar shopCarMsg = commodityMapper.getShopCarMsg(commodityId);
        JsonTransfer s = new JsonTransfer();
        if (!commodityId.equals(shopCarMsg.getCommodityId())) {
            commodityMapper.addCommodityToShopCar(commodityId, commodityNumber);
            String result1 = s.result(1, "添加成功", "", callback);
            return result1;
        }
        String result2 = s.result(0,"购物车已存在该商品","",callback);
        return result2;
    }

    @Override
    public String getAllCommodityInfo(String[] volumeIds, String callback) {
        List<VolumeManage> commodityInfo = commodityMapper.getAllCommodityInfo(volumeIds);
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"",commodityInfo,callback);
        return result;
    }

    @Override
    public String addCommodityById(String volumeIds, String commodityName, String commodityNumber,
                                   String commodityDescription, String commodityPrice,String commodityType, String callback) {
        JsonTransfer s = new JsonTransfer();
        String result="";
        int index=0;
        //查询所有商品信息
        List<VolumeManage> commodityInfo = commodityMapper.getAllCommodityInfoById(volumeIds);
        //遍历所有商品
        if (commodityInfo.isEmpty()) {
            String commodityId=GetUUID.getUUID();
            commodityMapper.addCommodityById(volumeIds, commodityId, commodityName, commodityNumber, commodityDescription, commodityPrice,commodityType);
            result= s.result(1, "添加成功", commodityId, callback);
        }else{
            for (VolumeManage commodityInfoPO : commodityInfo) {
                //判断传入的商品名是否与已存在的商品相同
                //各属性相同的话，则认为是同一种商品，则只是数量增加
                String test=commodityInfoPO.getCommodityPrice();
                System.out.print(test);
                System.out.print(commodityPrice);
                if (
                        (volumeIds.equals(commodityInfoPO.getVolumeId()) )
                        && (commodityName.equals(commodityInfoPO.getCommodityName()) )
                        && (commodityDescription.equals(commodityInfoPO.getCommodityDescription()))
                        && (Float.parseFloat(commodityPrice)==Float.parseFloat((commodityInfoPO.getCommodityPrice())))
                        ) {
                    Integer newCommodityNumberInt = (commodityInfoPO.getCommodityNumber()).intValue() + Integer.parseInt(commodityNumber);
                    String newCommodityNumberStr = String.valueOf(newCommodityNumberInt);
                    //修改数量：原先的数量+传入的数量
                    commodityMapper.updateCommodityAddNumber(volumeIds,commodityInfoPO.getCommodityId(), newCommodityNumberStr);
                    result= s.result(2, "由于数据库中存在该商品，图片请在商品列表中进行图片修改", "", callback);
                    index++;
                }
            }
            if(index==0){
                String commodityId=GetUUID.getUUID();
                commodityMapper.addCommodityById(volumeIds,commodityId, commodityName, commodityNumber, commodityDescription, commodityPrice,commodityType);
                result= s.result(1, "添加成功",commodityId , callback);
            }
        }
        return result;
    }

    @Override
    public String delCommodityById(String commodityIds, String callback) {
        JsonTransfer s = new JsonTransfer();
        if (commodityIds == null){
            String result2 = s.result(0,"请选择存在的商品进行删除","",callback);
            return result2;
        }
        commodityMapper.delCommodityById(commodityIds);
        commodityMapper.delCommodityPicById(commodityIds);
        String result1 = s.result(1,"删除成功","",callback);
        return result1;
    }

    @Override
    public String updateCommodityById(String commodityId,String commodityName, String commodityNumber,
                                String commodityDescription, String commodityPrice,String commodityTypeId, String callback) {
        commodityMapper.updateCommodityById(commodityId,commodityName, commodityNumber,commodityDescription,commodityPrice,commodityTypeId);
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
        List<VolumeManage> volumeManage = commodityMapper.getCommodityByName(commodityName);
        HashMap<Integer,Object> mapObj=new HashMap<>();

        int index=0;
        for(VolumeManage manage : volumeManage){
            Seller sellers=commodityMapper.getSellerInfoByVolumeId(manage.getVolumeId());
            HashMap<String,Object> map=new HashMap<>();
            map.put("volumeData",manage);
            map.put("seller_id",sellers.getSellerId());
            map.put("store_name",sellers.getStoreName());
            mapObj.put(index,map);
            index++;
        }
        JsonTransfer s = new JsonTransfer();
        String result1 = s.result(1, "",mapObj,callback);
        return result1;

    }

    @Override
    public List<CommodityPicture> getCommodityPicture(String callback) {
        List<CommodityPicture> commodityPictureList = commodityMapper.getCommodityPicture();
        return commodityPictureList;
    }

    @Override
    public  String getCommodityTypeById(String commodityTypeId, String callback){
        List<CommodityType> commodityTypes=commodityMapper.getCommodityTypeById(commodityTypeId);
        JsonTransfer s = new JsonTransfer();
        String result1 = s.result(1, "",commodityTypes,callback);
        return result1;
    }

    @Override
    public String addCommodityPicture(String picId, String picBase64, int picType, int priority, String callback) {
        JsonTransfer s = new JsonTransfer();
        List<CommodityPicture> commodityPictureList = commodityMapper.getCommodityPicture();
        if (commodityPictureList == null) {
            commodityMapper.addCommodityPicture(picId, picBase64, picType, priority);
        } else {
            int sort = 0;
            for (CommodityPicture commodityPicture : commodityPictureList) {
                if (picId.equals(commodityPicture.getPicId())&& picType==commodityPicture.getPicType()) {
                    CommodityPicture priorityMax = commodityMapper.getCommodityPictureByPriority(picId, picType);
                    if (sort < priorityMax.getPriority()) {
                        sort = priorityMax.getPriority();
                    }
                }
            }
            if (sort == 0) {
                commodityMapper.addCommodityPicture(picId, picBase64, picType, priority);
            } else {
                sort++;
                commodityMapper.addCommodityPicture(picId, picBase64, picType, sort);
            }
        }
        String result = s.result(1, "上传成功", "", callback);
        return result;
    }

    @Override
    public String getCommodityPicById(String picId, String callback) {
        List<CommodityPicture> commodityPictures=commodityMapper.getCommodityPicById(picId);
        JsonTransfer s=new JsonTransfer();
        String result = s.result(1, "上传成功", commodityPictures, callback);
        return result;
    }

    @Override
    public String delPicByPicId(String picId, String callback) {
        JsonTransfer s= new JsonTransfer();
        commodityMapper.delCommodityPicById(picId);
        String result1 = s.result(1,"删除成功","",callback);
        return result1;
    }

    @Override
    public String getCommodityAndPicByVolumeId(String volumeId, String callback) {
        String result="";
        JsonTransfer s = new JsonTransfer();
        try {
            List<VolumeManage> commodityInfo = commodityMapper.getCommodityAndPicByVolumeId(volumeId);
            HashMap<Integer,Object> map= new HashMap<>();
            int index=0;
            for(VolumeManage volumeManagePo:commodityInfo){
                HashMap<String,Object> map1=new HashMap<>();
                List<CommodityPicture> commodityPictures=commodityMapper.getCommodityPicById(volumeManagePo.getCommodityId());
                map1.put("picData",commodityPictures);
                map1.put("commodityData",volumeManagePo);
                map.put(index,map1);
                index++;
            }
            result = s.result(1,"",map,callback);
        }catch (Exception e){
            result = s.result(1,"",e,callback);
        }
        return result;
    }

    @Override
    public String addType(String typename, String callback) {
        String result="";
        JsonTransfer s = new JsonTransfer();
        try{
            commodityMapper.addType(typename);
            result=s.result(1,"","",callback);
        }catch (Exception e){
            result=s.result(0,e.toString(),"",callback);
        }
        return result;
    }

    @Override
    public String delTypeById(String typeId, String callback) {
        String result="";
        JsonTransfer s = new JsonTransfer();
        try{
            commodityMapper.delTypeById(typeId);
            result=s.result(1,"","",callback);
        }catch (Exception e){
            result=s.result(0,e.toString(),"",callback);
        }
        return result;
    }

    @Override
    public String updateTypeById(String id, String name, String callback) {
        String result="";
        JsonTransfer s = new JsonTransfer();
        try{
            commodityMapper.updateTypeById(id,name);
            result=s.result(1,"","",callback);
        }catch (Exception e){
            result=s.result(0,e.toString(),"",callback);
        }
        return result;
    }

    @Override
    public String addArticle(String id, String commodityId, String article, Integer type, String callback) {
        JsonTransfer s =new JsonTransfer();
        try {
            commodityMapper.addArticle(id, commodityId, article, type);
            String result = s.result(1,"添加成功","",callback);
            return result;
        }catch (Exception e){
            String result = s.result(0,"添加失败","",callback);
            return result;
        }
    }

    @Override
    public String updateArticle(String id, String commodityId, String article, Integer type, String callback) {
        String result = "";
        JsonTransfer s = new JsonTransfer();
        try {
            commodityMapper.updateArticle(id, commodityId, article, type);
            result = s.result(1,"更新成功","",callback);
        }catch (Exception e){
            result = s.result(0,"更新失败","",callback);
        }
        return result;
    }

    @Override
    public String deleteArticle(String id, String commodityId, String callback) {
        String result = "";
        JsonTransfer s = new JsonTransfer();
        try{
            commodityMapper.deleteArticle(id,commodityId);
            result = s.result(1,"删除成功","",callback);
        }catch(Exception e){
            result = s.result(0,"删除失败","",callback);
        }
        return result;
    }

    @Override
    public String getArticleByTypeAndState(Integer type, int state, String callback) {
        String result = "";
        JsonTransfer s = new JsonTransfer();
        try {
            List<Article> articleList = commodityMapper.getArticleByTypeAndState(type,state);
            result = s.result(1,"查询成功",articleList,callback);
        }catch (Exception e){
            result = s.result(0,"查询失败","",callback);
        }
        return result;
    }

    @Override
    public String changeArticleState(String id, String commodityId, Integer type, Integer state, String callback) {
        String result = "";
        JsonTransfer s = new JsonTransfer();
        try{
            commodityMapper.changeArticleState(id,commodityId,type,state);
            result = s.result(1,"状态修改成功","",callback);
        }catch (Exception e){
            result = s.result(0,"状态修改失败","",callback);
        }
        return result;
    }

}
