package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.CommodityType;
import com.felix.grouppurchase.model.VolumeManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    List<VolumeManage> getAllCommodityInfo(@Param("volumeIds") String[] volumeIds, String callback);
}
