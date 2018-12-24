package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.CommodityMapper;
import com.felix.grouppurchase.mapper.SellerMapper;
import com.felix.grouppurchase.model.Article;
import com.felix.grouppurchase.model.Seller;
import com.felix.grouppurchase.model.VolumeManage;
import com.felix.grouppurchase.service.ISellerService;
import com.felix.grouppurchase.util.ErrorCodeDesc;
import com.felix.grouppurchase.util.GetSMS;
import com.felix.grouppurchase.util.GetUUID;
import com.felix.grouppurchase.util.JsonTransfer;
import net.sf.json.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2018/11/30 9:20
 * @Author: fangyong
 */
@Service
public class SellerServiceImpl implements ISellerService {

    private final static transient Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

    @Autowired
    SellerMapper sellerMapper;
    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public String sellerRegister(String sellerId, String sellerNickName, String sellerName, String sellerPassword, String sellerIdentityId, String sellerPhone, String sellerEmail, String storeName, String storeArea, String callback) {
        String volumeId= GetUUID.getUUID();
        JsonTransfer s = new JsonTransfer();
        String result="";
        try {
            Seller seller=sellerMapper.checkIdIsRegist(sellerId);
            if(seller==null){
                sellerMapper.sellerRegister(sellerId,sellerNickName,sellerName,sellerPassword,sellerIdentityId,sellerPhone,sellerEmail,storeName,storeArea,volumeId);
                result = s.result(1, "注册成功", "", callback);
            }
            else{
                result = s.result(1, "登录名已被注册", "", callback);
            }

        }catch ( Exception e){
            String result2 = s.result(0,e.toString(),"",callback);
        }
        return result;
    }

    @Override
    public String sellerLogin(String sellerId, String sellerPassword, String callback) {
        Seller seller =  sellerMapper.sellerLogin(sellerId, sellerPassword);
        JsonTransfer s = new JsonTransfer();
        if (seller == null){
            String result1 = s.result(0,"用户不存在或者账号密码输入错误","",callback);
            return result1;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("sellerNickname",seller.getSellerNickname());
        String result2 = s.result(1,ErrorCodeDesc.USER_EXIST,map,callback);
        return result2;
    }

    @Override
    public String checkPhoneRegister(String sellerPhone, String callback) {
        Seller seller =  sellerMapper.checkPhone(sellerPhone);
        JsonTransfer s = new JsonTransfer();
        if (seller == null){
            return GetSMS.getMssage(sellerPhone,callback);
        }
        String result1 = s.result(0,ErrorCodeDesc.USER_EXIST,"",callback);
        return result1;
    }

    @Override
    public String checkPhoneLogin(String sellerPhone, String callback) {
        Seller seller =  sellerMapper.checkPhone(sellerPhone);
        JsonTransfer s = new JsonTransfer();
        if (seller == null){
            String result1 = s.result(0,ErrorCodeDesc.USER_NOEXIST,"",callback);
            return result1;
        }
        HashMap<String , Object> map=new HashMap<>();
        map.put("id",seller.getSellerId());
        map.put("username",seller.getSellerNickname());
        return  s.result(1,"",map,callback);
    }

    @Override
    public String checkIdWithPhone(String sellerId, String sellerPhone, String callback) {
        String seller =  sellerMapper.checkIdWithPhone(sellerId, sellerPhone);
        JsonTransfer s = new JsonTransfer();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sellerId",sellerId);
        if (seller.equals("0")){
            String result1 = s.result(0,"找不到该手机号匹配的ID，请重新输入","",callback);
            return result1;
        }
        String result2 = s.result(1,"匹配成功",map,callback);
        return result2;
    }

    @Override
    public String resetPassword(String sellerId, String sellerPassword, String callback) {
        sellerMapper.resetPassword(sellerId,sellerPassword);
        JsonTransfer s = new JsonTransfer();
        HashMap<String, String> map = new HashMap<>();
        map.put("sellerId",sellerId);
        map.put("sellerPassword",sellerPassword);
        try {
            String result1 = s.result(1,"修改成功","map",callback);
            return result1;
        }catch (Exception e){
            e.printStackTrace();
            String result2 = s.result(0,"系统异常，修改失败",map,callback);
            return result2;
        }
    }

    @Override
    public  String checkPhoneExist(String phone,String callback){
        Seller seller=sellerMapper.checkPhoneExist(phone);
        JsonTransfer s=new JsonTransfer();
        HashMap<String ,Object> map=new HashMap<>();
        String result1="";
        if(seller.getSellerId()==null){
             result1 = s.result(0,"请先注册手机","",callback);
        }
        else{
            map.put("id",seller.getSellerId());
            result1 = s.result(1,"",map,callback);
        }
        return result1;
    }

    @Override
    public String getSellerInfoById(String sellerId, String callback){
        Seller seller=sellerMapper.getSellerInfoById(sellerId);
        JsonTransfer s=new JsonTransfer();
        String result=s.result(1,"查找成功",seller,callback);
        return result;
    }

    @Override
    public String updateSellerInfo( String sellerId,String storeName, String sellerNickname, String sellerName, String sellerIdentityId, String storeArea, String sellerEmail, String callback) {
        sellerMapper.updateSellerInfo( sellerId,storeName,  sellerNickname,  sellerName,  sellerIdentityId,  storeArea,  sellerEmail);
        JsonTransfer s=new JsonTransfer();
        String result=s.result(1,"更新成功","",callback);
        return result;
    }

    @Override
    public String getSellerInfoByVolumeId(String volumeId, String callback) {
        Seller seller=sellerMapper.getSellerInfoByVolumeId(volumeId);
        JsonTransfer s=new JsonTransfer();
        String result=s.result(1,"更新成功",seller,callback);
        return result;
    }

    @Override
    public String getAllSeller(String callback) {
        List<Seller> sellers=sellerMapper.getAllSeller();
        JsonTransfer s=new JsonTransfer();
        String result=s.result(1,"查询成功",sellers,callback);
        return result;
    }

    @Override
    public String updateSellerState(String state, String sellerId, String callback) {
       String result="";
       JsonTransfer s=new JsonTransfer();
        try {
           sellerMapper.updateSellerState(state,sellerId);
            result=s.result(1,"更新成功","",callback);
       }catch (Exception e){
            result=s.result(1,e.toString(),"",callback);
       }
        return result;
    }

    @Override
    public String updateSellerPink(String volumeId, String callback) {
        JsonTransfer s= new JsonTransfer();
        String result="";
        int haoping=0;
        int zhongping=0;
        int chaping=0;
        String score="";
        try {
            Seller seller=sellerMapper.getSellerInfoByVolumeId(volumeId);
            List<VolumeManage> volumeManages= commodityMapper.getAllCommodityInfoById(volumeId);
            for(VolumeManage volumeManagePo:volumeManages){
                List<Article> articles= commodityMapper.getAllArticleByCommodityId(volumeManagePo.getCommodityId());
                for(Article articlePo:articles){
                    Article article=commodityMapper.getArticleCountByPicIdAndCommentType(volumeManagePo.getCommodityId(),"3");
                    haoping=haoping+Integer.parseInt(article.getCommentType());
                    Article article1=commodityMapper.getArticleCountByPicIdAndCommentType(volumeManagePo.getCommodityId(),"2");
                    zhongping=zhongping+Integer.parseInt(article1.getCommentType());
                    Article article2=commodityMapper.getArticleCountByPicIdAndCommentType(volumeManagePo.getCommodityId(),"1");
                    chaping=chaping+Integer.parseInt(article2.getCommentType());
                }
            }
            if( haoping==0&&zhongping==0&&chaping==0){
                 score="10";
            }
            else{
                score=String.valueOf(haoping/(haoping+zhongping+chaping)*100);
            }
            System.out.print(score);
            sellerMapper.updateSellerPink(seller.getSellerId(),score);
            result=s.result(1,"","",callback);
        }catch (Exception e){
            result=s.result(1,e.toString(),"",callback);
        }
        return  result;
    }
}