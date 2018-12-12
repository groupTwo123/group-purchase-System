package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.CommodityPicture;
import com.felix.grouppurchase.model.CommodityType;
import com.felix.grouppurchase.model.ShopCar;
import com.felix.grouppurchase.model.Seller;
import com.felix.grouppurchase.model.VolumeManage;
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

    //获取仓库中所有商品信息
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

    //通过仓库id增加商品
    @Insert("insert into tb_volume_manage(volume_id,commodity_id,commodity_name,commodity_number,commodity_description,commodity_price)\n "+
            "values(#{volumeId},#{commodityId},#{commodityName},#{commodityNumber},#{commodityDescription},#{commodityPrice})")
    void addCommodityById(@Param("volumeId") String volumeId, @Param("commodityId") String commodityId, @Param("commodityName")String commodityName,
                          @Param("commodityNumber")String commodityNumber, @Param("commodityDescription")String commodityDescription,
                          @Param("commodityPrice")String commodityPrice);

    //更新商品
    @Update({
            "<script>"
                    + "update "
                    + "tb_volume_manage "
                    + "set commodity_number = #{newCommodityNumberStr}"
                    + "<if test='volumeIds != null'>"
                    + "where volume_id IN "
                    + "<foreach item='item' index='index' collection='volumeIds' open='(' separator=',' close=')'>"
                    + "#{item} "
                    + "</foreach>"
                    + "</if>"
                    + "</script>"
    })
    void updateCommodityAddNumber(@Param("volumeIds")String[] volumeIds, @Param("newCommodityNumberStr") String newCommodityNumberStr);

    //根据商品id删除商品
    @Delete({
            "<script>"
                    + "delete "
                    + "from "
                    + "tb_volume_manage "
                    + "where commodity_id IN "
                    + "<foreach item='item' index='index' collection='commodityIds' open='(' separator=',' close=')'>"
                    + "#{item} "
                    + "</foreach>"
                    + "</script>"
    })
    void delCommodityById(@Param("commodityIds") String[] commodityIds);

    //更新商品
    @Update("update tb_volume_manage set commodity_name = #{commodityName}, commodity_number = #{commodityNumber}, commodity_description = #{commodityDescription}, commodity_price = #{commodityPrice}\n "+
            "where commodity_id = #{commodityId}")
    void updateCommodityById(@Param("commodityId") String commodityId,@Param("commodityName")String commodityName, @Param("commodityNumber")String commodityNumber,
                         @Param("commodityDescription")String commodityDescription, @Param("commodityPrice")String commodityPrice);

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
}
