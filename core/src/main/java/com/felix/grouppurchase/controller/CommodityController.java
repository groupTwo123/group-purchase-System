package com.felix.grouppurchase.controller;

import com.felix.grouppurchase.model.CommodityPicture;
import com.felix.grouppurchase.service.ICommodityService;
import com.felix.grouppurchase.util.ErrorCodeDesc;
import com.felix.grouppurchase.util.JsonTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     *
     * @Author: huangchuwen
     * @date: 2018/12/17 14:45
     * @Description: 通过仓库id获取商品信息和对应图片
     * @params: volumeIds,callback
     */
    @RequestMapping(value = "/getCommodityAndPicByVolumeId",method = RequestMethod.GET)
    public String getCommodityAndPicByVolumeId(String volumeId, String callback){
        return commodityService.getCommodityAndPicByVolumeId(volumeId, callback);
    }

    /**
     * @Author fangyong
     * @Description 增加商品到仓库中
     * @Date 2018/12/3 16:26
     * @Param volumeIds,commodityName,commodityNumber,commodityDescription,commodityPrice,commodityPic, commodityDetailPic,callback
     **/
    @RequestMapping(value = "/addCommodityById",method = RequestMethod.GET)
    public String addCommodityById(String volumeIds, String commodityName, String commodityNumber,
                                 String commodityDescription, String commodityPrice,String commodityType, String callback){
      return commodityService.addCommodityById(volumeIds,commodityName,commodityNumber,commodityDescription,commodityPrice,commodityType,callback);
    }

    /**
     * @Author fangyong
     * @Description 根据商品id删除仓库中的商品
     * @Date 2018/12/4 11:00 
     * @Param commodityIds, callback
     **/
    @RequestMapping(value = "/delCommodityById",method = RequestMethod.GET)
    public String delCommodityById(String commodityIds, String callback){
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
                                  String commodityDescription, String commodityPrice,String commodityTypeId, String callback){
       return commodityService.updateCommodityById(commodityId,commodityName, commodityNumber,commodityDescription,commodityPrice,commodityTypeId, callback);
    }

    /**
     * @Author fangyong
     * @Description 高级搜索--根据名称模糊查询
     * @Date 2018/12/4 15:53 
     * @Param:commodityName
     **/
    @RequestMapping(value = "/getCommodityByName",method = RequestMethod.GET)
    public String getCommodityByName(String commodityName, String callback){
       return commodityService.getCommodityByName(commodityName, callback);
    }

    /**
     * @Author fangyong
     * @Description 上传商品图片
     * @Date 2018/12/5 8:54
     * @Param
     * @return
     **/
    @RequestMapping(value = "/addCommodityPicture", method = RequestMethod.GET)
    public String addCommodityPicture(String picId, String picBase64, Integer picType, String callback) throws IOException {
        return commodityService.addCommodityPicture(picId,picBase64.replaceAll(" ","+"),picType,ErrorCodeDesc.priority,callback);
    }

    /**
     * @Author fangyong
     * @Description 获取商品图片
     * @Date 2018/12/5 10:57
     * @Param
     * @return
     **/
    @RequestMapping(value = "/getCommodityPicture", method = RequestMethod.GET)
    public List<CommodityPicture> getCommodityPicture(String callback){
        List<CommodityPicture> pictureList = commodityService.getCommodityPicture(callback);
        return pictureList;
    }


    /**
     * @Author fangyong
     * @Description 查看商品详情
     * @Date 2018/12/6 15:44
     * @Param commodityId,callback
     * @return
     **/
    @RequestMapping(value = "/getCommodityDetail", method = RequestMethod.GET)
    public String getCommodityDetail(String commodityId, String callback){
        return commodityService.getCommodityDetail(commodityId,callback);
    }

    /**
     * @Author fangyong
     * @Description 加入购物车
     * @Date 2018/12/6 16:22
     * @Param commodityId,commodityNumber,callback
     * @return
     **/
    @RequestMapping(value = "/addCommodityToShopCar", method = RequestMethod.GET)
    public String addCommodityToShopCar(String commodityId,String commodityNumber,String callback){
        return commodityService.addCommodityToShopCar(commodityId,commodityNumber,callback);
    }
    /**
     * @Author huangchuwen
     * @Description 根据类型id查找中文描述
     * @Date 2018/12/11 11：23
     * @Param commodityTypeId,callback
     * @return commodityTypeObj
     **/
    @RequestMapping(value = "/getCommodityTypeById", method = RequestMethod.GET)
    public String getCommodityTypeById(String commodityTypeId,String callback){
        return commodityService.getCommodityTypeById(commodityTypeId,callback);
    }
    /**
     * @Author huangchuwen
     * @Description 根据picId查找图片
     * @Date 2018/12/14 10：23
     * @Param commodityId,callback
     * @return
     **/
    @RequestMapping(value = "/getCommodityPicById", method = RequestMethod.GET)
    public String getCommodityPicById(String picId,String callback){
        return commodityService.getCommodityPicById(picId,callback);
    }
    /**
     * @Author huangchuwen
     * @Description 根据picId删除所有图片
     * @Date 2018/12/14 16：40
     * @Param picId,callback
     * @return
     **/
    @RequestMapping(value = "/delPicByPicId", method = RequestMethod.GET)
    public String delPicByPicId(String picId,String callback){
        return commodityService.delPicByPicId(picId,callback);
    }

    /**
     * @Author huangchuwen
     * @Description 加入类型
     * @Date 2018/12/18 8：40
     * @Param typename,callback
     * @return
     **/
    @RequestMapping(value = "/addType", method = RequestMethod.GET)
    public String addType(String typename,String callback){
        return commodityService.addType(typename,callback);
    }
    /**
     * @Author huangchuwen
     * @Description 删除类型
     * @Date 2018/12/18 9：40
     * @Param typeId,callback
     * @return
     **/
    @RequestMapping(value = "/delTypeById", method = RequestMethod.GET)
    public String delTypeById(String typeId,String callback){
        return commodityService.delTypeById(typeId,callback);
    }
    /**
     * @Author huangchuwen
     * @Description 更新类型
     * @Date 2018/12/18 10：40
     * @Param id,name,callback
     * @return
     **/
    @RequestMapping(value = "/updateTypeById", method = RequestMethod.GET)
    public String updateTypeById(String id, String name,String callback){
        return commodityService.updateTypeById(id ,name ,callback);
    }

    /**
     * @Author fangyong
     * @Description 增加文章
     * @Date 2018/12/18 16:11
     * @Param id,commodityId,article,type,callback
     * @return
     **/
    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    public String addArticle(String id, String commodityId, String article, Integer type,String commentType, String callback){
        return commodityService.addArticle(id,commodityId,article,type,commentType,callback);
    }

    /**
     * @Author fangyong
     * @Description 修改文章
     * @Date 2018/12/18 16:19
     * @Param id,commodityId,article,type,callback
     * @return
     **/
    @RequestMapping(value = "/updateArticle", method = RequestMethod.GET)
    public String updateArticle(String id, String commodityId, String article, Integer type, String callback){
        return commodityService.updateArticle(id,commodityId,article,type,callback);
    }

    /**
     * @Author fangyong
     * @Description 删除文章
     * @Date 2018/12/18 16:27
     * @Param id,commodityId,callback
     * @return
     **/
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
    public String deleteArticle(String id, String commodityId,String article,String type, String callback){
        return commodityService.deleteArticle(id,commodityId,article,type,callback);
    }

    /**
     * @Author fangyong
     * @Description 根据type和state查询文章
     * @Date 2018/12/18 16:50
     * @Param type, ErrorCodeDesc.state,callback
     * @return
     **/
    @RequestMapping(value = "/getArticleByTypeAndState", method = RequestMethod.GET)
    public String getArticleByTypeAndState(Integer type, String callback){
      return commodityService.getArticleByTypeAndState(type, ErrorCodeDesc.state,callback);
    }

    /**
     * @Author fangyong
     * @Description 修改文章启用状态
     * @Date 2018/12/18 17:00
     * @Param
     * @return
     **/
    @RequestMapping(value = "/changeArticleState", method = RequestMethod.GET)
    public String changeArticleState(String id, String commodityId,String article, Integer type, Integer state, String callback){
        return commodityService.changeArticleState(id,commodityId,article,type,state,callback);
    }

    /**
     * @Author huangchuwen
     * @Description 根据id查找文章列表
     * @Date 2018/12/20 9:00
     * @Param id,idType
     * @return
     **/
    @RequestMapping(value = "/getArticleById", method = RequestMethod.GET)
    public String getArticleById(String id,String idType, String callback){
        return commodityService.getArticleById(id,idType,callback);
    }
    /**
     * @Author huangchuwen
     * @Description 根据commodityId和type查找文章列表
     * @Date 2018/12/20 9:00
     * @Param commodityId,type
     * @return
     **/
    @RequestMapping(value = "/getArticleByCommodityId", method = RequestMethod.GET)
    public String getArticleByCommodityId(String commodityId,String type, String callback){
        return commodityService.getArticleByCommodityId(commodityId,type,callback);
    }

    /**
     * @Author huangchuwen
     * @Description 根据商品订单id获取退货信息
     * @Date 2018/12/22 10:00
     * @Param
     * @return
     **/
    @RequestMapping(value = "/getBackReasonByOrderId", method = RequestMethod.GET)
    public String getArticgetBackReasonByOrderIdleById(String orderiId, String callback){
        return commodityService.getBackReasonByOrderId(orderiId,callback);
    }

}
