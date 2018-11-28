package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.CommodityMapper;
import com.felix.grouppurchase.model.CommodityType;
import com.felix.grouppurchase.service.CommodityService;
import com.felix.grouppurchase.util.ErrorCodeDesc;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2018/11/27 22:58
 * @Author: huangchuwen
 */
@Service
public class CommodityServiceImpl  implements CommodityService{
    private final static transient Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);
    @Autowired
    CommodityMapper commodityMapper;


    @Override
    public String getAllCommodityType(String callback){
        List<CommodityType> commodityType= commodityMapper.getAllCommodityType();
        System.out.print("************");System.out.print(commodityType);
        JsonTransfer s = new JsonTransfer();
        String result1 = s.result(1, "",commodityType,callback);
        return result1;

    }

}
