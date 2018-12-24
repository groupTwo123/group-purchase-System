package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.BackCommodity;
import com.felix.grouppurchase.model.Order;
import com.felix.grouppurchase.model.VolumeManage;
import org.apache.ibatis.annotations.*;

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

    //通过id获取商品信息
    @Select("select * from tb_volume_manage where commodity_id=#{conmmodity_id}")
    List<VolumeManage> getCommodityById(@Param("conmmodity_id") String commodityId);

    //新加退货订单
    @Insert("insert into tb_back_commodity(back_order_id, user_id, commodity_id, commodity_number, money, back_reason, state) values(#{back_order_id},#{user_id},#{commodity_id},#{commodity_number},#{money},#{back_reason},#{state})")
    void addBackCommodity(@Param("back_order_id") String back_order_id, @Param("user_id") String user_id, @Param("commodity_id") String commodity_id, @Param("commodity_number") String commodity_number, @Param("money") String money, @Param("back_reason") String back_reason,@Param("state") String state);

    //修改订单状态
    @Update("update tb_order set state=#{stage} where order_id=#{order_id}")
    void updateOrderStage(@Param("order_id") String order_id, @Param("stage") String stage);

    //获取退货前状态
    @Select("select * from tb_back_commodity where back_order_id=#{order_id}")
    BackCommodity getStageByOrderId(@Param("order_id") String order_id);

    //删除退货订单
    @Delete("delete from tb_back_commodity where back_order_id=#{order_id} ")
    void deleteBackOrder(@Param("order_id") String order_id);

    //用户付款支出
    @Update("update tb_user set vacancy = vacancy - #{money} where id = #{id}")
    void moveOut(@Param("id") String id, @Param("money") String money);

    //商家收款收入
    @Update("update tb_seller set seller_account = seller_account + #{money} where seller_id = #{sellerId}")
    void moveIn(@Param("sellerId") String sellerId, @Param("money") String money);

    //增加订单
    @Insert("insert into tb_order(order_id,user_id,commodity_id,commodity_number,money,state) values(#{orderId},#{userId},#{commodityId}," +
            " #{orderInfoNumber},#{orderSumPrice},#{state})")
    void addOrder(@Param("orderId") String orderId, @Param("userId") String userId,
                  @Param("commodityId") String commodityId, @Param("orderInfoNumber") String orderInfoNumber,
                  @Param("orderSumPrice") String orderSumPrice, @Param("state") int state);

    //根据商品id查找订单
    @Select("select * from tb_order where commodity_id=#{commodityId}")
    List<Order> getOrderByCommodityId(@Param("commodityId") String commodityId);

    //根据商品id查找订单
    @Select("select * from tb_order where order_id=#{orderId}")
    Order getCommodityByOrderId(@Param("orderId") String orderId);
}
