package com.felix.grouppurchase.service;


import com.felix.grouppurchase.model.CommodityPicture;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @Date: 2018/11/27 22:50
 * @Author: huangchuwen
 */
@Component
public interface ICommodityService {

    //获取所有商品类别
    String getAllCommodityType(String callback);

    //获取仓库中所有商品的信息
    String getAllCommodityInfo(String[] volumeIds, String callback);

    //增加商品到仓库
    String addCommodityById(String volumeIds, String commodityName, String commodityNumber,
                            String commodityDescription, String commodityPrice , String commodityType,String callback);

    //根据商品id删除商品
    String delCommodityById(String commodityIds, String callback);

    //更新商品
    String updateCommodityById(String commodityId,String commodityName, String commodityNumber,
                         String commodityDescription, String commodityPrice,String commodityTypeId, String callback);

    //根据商品名称模糊查询
    String getCommodityByName(String commodityName, String callback);

    //获取商品图片
    String getCommodityPicture(String callback);

    //查询所有商品
    String getAllCommodity(String callback);

    //查询商品详情
    String getCommodityDetail(String commodityId, String callback);

    //添加商品到购物车
    String addCommodityToShopCar(String commodityId,String commodityNumber,String callback);

    //通过类型id查找类型中文描述
    String getCommodityTypeById(String commodityTypeId, String callback);

    //上传图片
    String addCommodityPicture(String picId, String picBase64, int picType,int priority, String callback);

    //根据picId查找图片
    String getCommodityPicById(String picId, String callback);

    //删除图片根据图片id
    String delPicByPicId(String picId, String callback);

    //通过仓库id获取商品信息和对应图片
    String getCommodityAndPicByVolumeId(String volumeId, String callback);

    //添加类型
    String addType(String typename, String callback);

    //删除类型
    String delTypeById(String typeId, String callback);

    //更新类型
    String updateTypeById(String id, String name, String callback);

    //增加文章
    String addArticle(String id, String commodityId, String article, Integer type,String commentType, String callback);

    //修改文章
    String updateArticle(String id, String commodityId, String article, Integer type, String callback);

    //删除文章
    String deleteArticle(String id, String commodityId, String article,String type,String callback);

    //根据类型和启用状态查询文章
    String getArticleByTypeAndState(Integer type, int state, String callback);

    //修改文章启用状态
    String changeArticleState(String id, String commodityId, String article,Integer type, Integer state, String callback);

    //根据id查找文章
    String getArticleById(String id, String idType,String callback);

    //根据订单id获取退货信息
    String getBackReasonByOrderId(String orderiId, String callback);

    //通过商品id和类型获取文章列表
    String getArticleByCommodityId(String commodityId, String type, String callback);

    //获取排行榜信息
    String getRankData(String limit,String callback);
}
