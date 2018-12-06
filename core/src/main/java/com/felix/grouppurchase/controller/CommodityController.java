package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2018/11/27 22:46
 * @Author: huangchuwen
 */
@RestController
@RequestMapping("/gpsys/commodity")
public class CommodityController {

    @Autowired
    ICommodityService commodityService;

    /**
     * @Author: huangchuwen
     * @date: 2018/11/27 22:46
     * @Description: 获取所有商品类型
     * @params: callback
     */
    @RequestMapping(value = "/getCommodityType", method = RequestMethod.GET)
    public String getCommodityType( String callback){
        return commodityService.getAllCommodityType(callback);
    }

    /**
     * @Author: huangchuwen
     * @date: 2018/12/5 10:24
     * @Description: 获取所有商品
     * @params: callback
     */
    @RequestMapping(value = "/getAllCommodity", method = RequestMethod.GET)
    public String getAllCommodity( String callback){
        return commodityService.getAllCommodity(callback);
    }

    /**
    *
    * @Author: fangyong
    * @date: 2018/11/29 14:45
    * @Description: 获取仓库中所有商品信息
    * @params: volumeIds,callback
    */
    @RequestMapping(value = "/getCommodityInfo",method = RequestMethod.GET)
    public String getCommodityInfo(String volumeIds, String callback){
        return commodityService.getAllCommodityInfo(volumeIds.split(","), callback);
    }

    /**
     * @Author fangyong
     * @Description 增加商品到仓库中
     * @Date 2018/12/3 16:26
     * @Param volumeIds,commodityName,commodityNumber,commodityDescription,commodityPrice,callback
     **/
    @RequestMapping(value = "/addCommodityById",method = RequestMethod.GET)
    public String addCommodityById(String[] volumeIds, String commodityName, String commodityNumber,
                                 String commodityDescription, String commodityPrice, String callback){
      return commodityService.addCommodityById(volumeIds,commodityName,commodityNumber,commodityDescription,commodityPrice,callback);
    }

    /**
     * @Author fangyong
     * @Description 根据商品id删除仓库中的商品
     * @Date 2018/12/4 11:00 
     * @Param commodityIds, callback
     **/
    @RequestMapping(value = "/delCommodityById",method = RequestMethod.GET)
    public String delCommodityById(String[] commodityIds, String callback){
        return commodityService.delCommodityById(commodityIds, callback);
    }
    
    /**
     * @Author fangyong
     * @Description 更新商品
     * @Date 2018/12/4 14:51 
     * @Param commodityId,commodityName, commodityNumber,commodityDescription,commodityPrice, callback
     **/
    @RequestMapping(value = "/updateCommodity",method = RequestMethod.GET)
    public String updateCommodityById(String commodityId,String commodityName, String commodityNumber,
                                  String commodityDescription, String commodityPrice, String callback){
       return commodityService.updateCommodityById(commodityId,commodityName, commodityNumber,commodityDescription,commodityPrice, callback);
    }

    /**
     * @Author fangyong
     * @Description 高级搜索--根据名称模糊查询
     * @Date 2018/12/4 15:53 
     * @Param
     **/
    @RequestMapping(value = "/getCommodityByName",method = RequestMethod.GET)
    public String getCommodityByName(String commodityName, String callback){
       return commodityService.getCommodityByName(commodityName, callback);
    }
    
    
}
