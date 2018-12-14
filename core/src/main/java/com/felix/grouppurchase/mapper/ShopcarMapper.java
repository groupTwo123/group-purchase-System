package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.ShopCar;
import org.apache.ibatis.annotations.*;
import com.felix.grouppurchase.model.VolumeManage;
import net.sf.json.JSON;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
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
                    +" and user_id=#{userId}"
                    + "</script>"
    })
    void delShopcarInfo(@Param("commodityIds") String[] commodityIds,@Param("userId") String userId);

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


    @Select("select * from tb_volume_manage where volume_id=#{volumeId} and commodity_id=#{commodityId} ")
    List<VolumeManage> getCommodityData(@Param("volumeId") String volumeId, @Param("commodityId") String commodityId);

    //修改购物车商品数量
    @Select("update  tb_shopping_car set commodity_number=#{commodity_number} where commodity_id=#{commodity_id} and user_id=#{userId}")
    void changeShoppingCarVolumeNumById(@Param("commodity_id") String commodityId, @Param("commodity_number") String changeNum ,@Param("userId") String userId ) throws SQLException;

    //增加购物车
    @Insert("insert into tb_shopping_car ( commodity_id, commodity_number, volume_id, user_id) VALUES ( #{commodityId},#{commodityNumber}, #{volume_id}, #{user_id})")
    void addShoppingCar(@Param("commodityId") String commodityId, @Param("commodityNumber") String commodityNumber,@Param("volume_id") String volume_id,@Param("user_id") String user_id)throws SQLException;

    //根据id查询购物车信息
    @Select({
            "<script>"
                    + "SELECT "
                    + "* "
                    + "FROM tb_shopping_car "
                    + "where id IN "
                    + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
                    + "#{item} "
                    + "</foreach>"
                    + "</script>"
    })
    List<ShopCar> getShopcarById(@Param("ids") String[] ids);

    //删除购物车记录
    @Delete("delete from tb_shopping_car where id = #{id}")
    void delShopcarInfoById(@Param("id") int id);
}
