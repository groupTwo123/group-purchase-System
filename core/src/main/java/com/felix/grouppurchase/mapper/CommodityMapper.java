package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.CommodityType;
import org.apache.ibatis.annotations.Mapper;
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
}
