package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.SellerMapper;
import com.felix.grouppurchase.model.Seller;
import com.felix.grouppurchase.service.ISellerService;
import com.felix.grouppurchase.util.ErrorCodeDesc;
import com.felix.grouppurchase.util.GetSMS;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Date: 2018/11/30 9:20
 * @Author: fangyong
 */
@Service
public class SellerServiceImpl implements ISellerService {

    private final static transient Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

    @Autowired
    SellerMapper sellerMapper;

    @Override
    public String sellerRegister(String sellerId, String sellerNickName, String sellerName, String sellerPassword, String sellerIdentityId, String sellerPhone, String sellerEmail, String storeName, String storeArea, String callback) {
        sellerMapper.sellerRegister(sellerId,sellerNickName,sellerName,sellerPassword,sellerIdentityId,sellerPhone,sellerEmail,storeName,storeArea);
        JsonTransfer s = new JsonTransfer();
        try {
            String result1 = s.result(1, "注册成功", "", callback);
            return result1;
        }catch ( Exception e){
            e.printStackTrace();
            String result2 = s.result(0,"系统异常，注册失败","",callback);
            return result2;
        }
    }

    @Override
    public String sellerLogin(String sellerId, String sellerPassword, String callback) {
        Seller seller =  sellerMapper.sellerLogin(sellerId, sellerPassword);
        JsonTransfer s = new JsonTransfer();
        if (seller == null){
            String result1 = s.result(0,ErrorCodeDesc.USER_NOEXIST,"",callback);
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

}