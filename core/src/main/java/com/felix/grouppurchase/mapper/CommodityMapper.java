package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2018/11/27 23:01
 * @Author: huangchuwen
 */
@Mapper
@Component
public interface CommodityMapper {
    //获取所有商品类别
    @Select("select * from tb_commodity_type")
    List<CommodityType> getAllCommodityType();

    //获取所有商品信息
    @Select({
            "<script>"
                    + "SELECT "
                    + "* "
                    + "FROM tb_volume_manage "
                    + "<if test='volumeIds != null'>"
                    + "where volume_id IN "
                    + "<foreach item='item' index='index' collection='volumeIds' open='(' separator=',' close=')'>"
                    + "#{item} "
                    + "</foreach>"
                    + "</if>"
                    + "</script>"
    })
    List<VolumeManage> getAllCommodityInfo(@Param("volumeIds") String[] volumeIds);


    //获取仓库中所有商品信息
    @Select("select *  from tb_volume_manage where volume_id=#{volumeIds}")
    List<VolumeManage> getAllCommodityInfoById(@Param("volumeIds") String volumeIds);


    //通过仓库id增加商品
    @Insert("insert into tb_volume_manage(volume_id,commodity_id,commodity_name,commodity_number,commodity_description,commodity_price,commodity_type_id)\n "+
            "values(#{volumeId},#{commodityId},#{commodityName},#{commodityNumber},#{commodityDescription},#{commodityPrice},#{commodityType})")
    void addCommodityById(@Param("volumeId") String volumeId, @Param("commodityId") String commodityId, @Param("commodityName")String commodityName,
                          @Param("commodityNumber")String commodityNumber, @Param("commodityDescription")String commodityDescription,
                          @Param("commodityPrice")String commodityPrice, @Param("commodityType") String commodityType);

    //更新商品
    @Update("update tb_volume_manage set commodity_number=#{newCommodityNumberStr} where volume_id=#{volumeIds} and commodity_id=#{commodityId}")
    void updateCommodityAddNumber(@Param("volumeIds")String volumeIds,@Param("commodityId") String commodityId, @Param("newCommodityNumberStr") String newCommodityNumberStr);

    //根据商品id删除商品
    @Delete("delete from tb_volume_manage where commodity_id=#{commodityIds}")
    void delCommodityById(@Param("commodityIds") String commodityIds);

    //更新商品
    @Update("update tb_volume_manage set commodity_name = #{commodityName}, commodity_number = #{commodityNumber}, commodity_description = #{commodityDescription}, commodity_price = #{commodityPrice}, commodity_type_id=#{commodityTypeId}\n "+
            "where commodity_id = #{commodityId}")
    void updateCommodityById(@Param("commodityId") String commodityId,@Param("commodityName")String commodityName, @Param("commodityNumber")String commodityNumber,
                         @Param("commodityDescription")String commodityDescription, @Param("commodityPrice")String commodityPrice,@Param("commodityTypeId") String commodityTypeId);

    //根据商品名称模糊查询商品
    @Select("select * from tb_volume_manage,tb_seller where tb_volume_manage.volume_id=tb_seller.volume_id and commodity_name like CONCAT('%' ,#{commodityName},'%')")
    List<VolumeManage> getCommodityByName(@Param("commodityName") String commodityName);

    //存图片到数据库
    @Insert("insert into tb_commodity_picture(picId,picBase64,picType,priority) values(#{picId},#{picBase64},#{picType},#{priority})")
    void addCommodityPicture(@Param("picId") String picId, @Param("picBase64") String picBase64, @Param("picType") Integer picType,@Param("priority") Integer priority);

    //获取商品图片
    @Select("select * from tb_commodity_picture")
    List<CommodityPicture> getCommodityPicture();

    //查询所有商品
    @Select("select * from tb_volume_manage")
    List<VolumeManage> getAllCommodity();

    //查询商品详情
    @Select("select * from tb_volume_manage where commodity_id = #{commodityId}")
    VolumeManage getCommodityDetail(@Param("commodityId") String commodityId);

    //添加商品到购物车
    @Insert("insert into tb_shopping_car(commodity_id,commodity_number) values(#{commodityId},#{commodityNumber})")
    void addCommodityToShopCar(@Param("commodityId") String commodityId,@Param("commodityNumber")String commodityNumber);

    //根据商品id查找该用户购物车是否已存在该商品
    @Select("select * from tb_shopping_car where commodity_id = #{commodityId}")
    ShopCar getShopCarMsg(@Param("commodityId") String commodityId);

    //查询商家信息seller_id,store_name根据volume_id
    @Select("select seller_id, store_name from tb_seller where volume_id= #{volumeId} ")
    Seller getSellerInfoByVolumeId( @Param("volumeId") String volumeId);

    //查询类型中文字段通过类型id
    @Select("select * from tb_commodity_type where id=#{id}")
    List<CommodityType> getCommodityTypeById(@Param("id") String commodityTypeId);

    //查询优先级最高的图片priority
    @Select("select max(priority) as priority from tb_commodity_picture where picId = #{picId} and picType = #{picType}")
    CommodityPicture getCommodityPictureByPriority(@Param("picId") String picId,@Param("picType")int picType);

    //结算后修改商品仓库中商品的数量
    @Update("update tb_volume_manage set commodity_number = commodity_number - #{orderInfoNumber}")
    void updateCommodityNumber(@Param("orderInfoNumber") String orderInfoNumber);

    //根据商品id删除图片
    @Delete("delete from tb_commodity_picture  where picId=#{commodityIds}")
    void delCommodityPicById(@Param("commodityIds") String commodityIds);

    //根据pic查找图片
    @Select("select * from tb_commodity_picture where picId=#{picId} order by priority")
    List<CommodityPicture> getCommodityPicById(@Param("picId") String picId);

    //根据仓库id获取商品信息
    @Select("select * from tb_volume_manage where volume_id=#{volumeId}")
    List<VolumeManage> getCommodityAndPicByVolumeId(@Param("volumeId") String volumeId);

    //加入类型
    @Insert("insert into tb_commodity_type (name) values(#{typename})")
    void addType(@Param("typename") String typename);

    //删除类型
    @Delete("delete from tb_commodity_type where id=#{typeId}")
    void delTypeById(@Param("typeId") String typeId);

    //更新类型
    @Update("update tb_commodity_type set name=#{name} where id=#{id}")
    void updateTypeById(@Param("id") String id,@Param("name") String name);

    //增加文章
    @Insert("insert into tb_article(id,commodity_id,article,type,commentType, state) values(#{id},#{commodityId},#{article},#{type},#{commentType},#{state})")
    void addArticle(@Param("id") String id, @Param("commodityId") String commodityId,
                    @Param("article") String article, @Param("type") Integer type,@Param("commentType") String commentType,@Param("state") String state);

    //修改文章
    @Update("update tb_article set article = #{article} where id = #{id} and commodity_id = #{commodityId} and type = #{type}")
    void updateArticle(@Param("id") String id, @Param("commodityId") String commodityId,
                       @Param("article") String article, @Param("type") Integer type);

    //删除文章
    @Delete("delete from tb_article where id = #{id} and commodity_id = #{commodityId} and article=#{article} and type=#{type}")
    void deleteArticle(@Param("id") String id, @Param("commodityId") String commodityId,@Param("article") String article,@Param("type") String type);

    //根据类型和启用状态查询文章
    @Select("select * from tb_article where type = #{type} and state = #{state}")
    List<Article> getArticleByTypeAndState(@Param("type") Integer type, @Param("state") int state);

    //修改文章启用状态
    @Update("update tb_article set state = #{state} where id = #{id} and commodity_id = #{commodityId} and type = #{type} and article=#{article}")
    void changeArticleState(@Param("id") String id, @Param("commodityId") String commodityId,@Param("article") String article, @Param("type") Integer type,
                            @Param("state") Integer state);

    //根据id获取文章列表
    @Select("select * from tb_article where id=#{id}")
    List<Article> getArticleById(@Param("id") String id);

    //查找所有文章
    @Select("select * from tb_article ")
    List<Article> getAllArticle();

    //获取图片通过id和type
    @Select("select * from tb_commodity_picture where picId=#{picId} and picType=#{picType} ")
    List<User> getCommodityPicByIdAndType(@Param("picId") String picId,@Param("picType") int picType);

    //更新用户头像
    @Update("update  tb_commodity_picture set picBase64=#{picBase64} where  picId=#{picId} and picType=#{picType} ")
    void updateCommodityPicture(@Param("picId") String picId,@Param("picBase64") String picBase64, @Param("picType") int picType);

    //退货增加商品
    @Update("update tb_volume_manage set commodity_number=commodity_number+#{commodityNumber} where commodity_id=#{commodityId}")
    void addCommodityNumberByCommodityId(@Param("commodityId") String commodityId, @Param("commodityNumber") Integer commodityNumber);

    //根据订单id获取退货信息
    @Select("select * from tb_back_commodity where back_order_id=#{orderiId}")
    BackCommodity getBackReasonByOrderId(@Param("orderiId") String orderiId);

    //根据商品Id获取文章
    @Select("select * from tb_article where commodity_id=#{commodityId}")
    List<Article> getAllArticleByCommodityId(@Param("commodityId") String commodityId);

    //通过商品id和类型获取文章列表
    @Select("select * from tb_article where commodity_id=#{commodityId} and type=#{type}")
    List<Article> getArticleByCommodityId(@Param("commodityId") String commodityId, @Param("type") String type);

    //通过评论类型和id获取评论 数量
    @Select("select count(commentType) as commentType from tb_article where  commodity_id=#{commodityId} and commentType=#{c}")
    Article getArticleCountByPicIdAndCommentType(@Param("commodityId") String commodityId,@Param("c") String c);

    //设置商品分数
    @Update("update tb_volume_manage set pink=#{totalScore} where commodity_id=#{commodityId}")
    void setCommodityScore(@Param("totalScore") double totalScore,@Param("commodityId") String commodityId);

    //查询排行榜数据
    @Select("select * from tb_volume_manage  ORDER BY pink DESC limit #{index}")
    List<VolumeManage> getCommodityByLimit(@Param("index") int index);
}
