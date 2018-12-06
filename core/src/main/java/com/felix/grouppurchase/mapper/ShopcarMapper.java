package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.ShopCar;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopcarMapper {

    //查询购物车详情
    @Select("select * from tb_shopping_car where user_id = #{userId}")
    List<ShopCar> getShopcarInfo(@Param("userId") String userId);

    //批量删除购物车中的商品
    @Delete({
            "<script>"
                    + "delete "
                    + "from "
                    + "tb_shopping_car "
                    + "where commodity_id IN "
                    + "<foreach item='item' index='index' collection='commodityIds' open='(' separator=',' close=')'>"
                    + "#{item} "
                    + "</foreach>"
                    + "</script>"
    })
    void delShopcarInfo(@Param("commodityIds") String[] commodityIds);

    //查询购物车所有信息
    @Select({
            "<script>"
                    + "SELECT "
                    + "count(1)"
                    + "FROM tb_shopping_car "
                    + "where commodity_id IN "
                    + "<foreach item='item' index='index' collection='commodityIds' open='(' separator=',' close=')'>"
                    + "#{item} "
                    + "</foreach>"
                    + "</script>"
    })
    int getAllShopcarInfo(@Param("commodityIds") String[] commodityIds);
}
