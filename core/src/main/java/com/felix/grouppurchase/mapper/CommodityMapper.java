package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.CommodityType;
import com.felix.grouppurchase.model.VolumeManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Date: 2018/11/27 23:01
 * @Author: huangchuwen
 */
@Mapper
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
    @Select("select * from tb_volume_manage where commodity_name like '%'")
    VolumeManage getCommodityByName(@Param("commodityName") String commodityName);
}
