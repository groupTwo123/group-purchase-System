package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date: 2018/11/28 20:38
 * @Author: fangyong
 */
@Mapper
public interface OrderMapper {
    //根据用户id获取该用户所有订单
    @Select("select * from tb_order where user_id = #{userId}")
    List<Order> getOrderByUserId(@Param("userId") String userId);

    //根据订单id批量删除
    @Delete({
            "<script>"
                    + "delete "
                    + "from "
                    + "tb_order "
                    + "where order_id IN "
                    + "<foreach item='item' index='index' collection='orderIds' open='(' separator=',' close=')'>"
                    + "#{item} "
                    + "</foreach>"
                    + "</script>"
    })
    void delOrderByOrderId(@Param("orderIds") String[] orderIds);
}
