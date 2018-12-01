package com.felix.grouppurchase.service.impl;

import com.felix.grouppurchase.mapper.CommodityMapper;
import com.felix.grouppurchase.model.CommodityType;
import com.felix.grouppurchase.model.VolumeManage;
import com.felix.grouppurchase.service.ICommodityService;
import com.felix.grouppurchase.util.JsonTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String getAllCommodityInfo(String[] volumeIds, String callback) {
        List<VolumeManage> commodityInfo = commodityMapper.getAllCommodityInfo(volumeIds, callback);
        JsonTransfer s = new JsonTransfer();
        String result = s.result(1,"",commodityInfo,callback);
        return result;
    }

}
