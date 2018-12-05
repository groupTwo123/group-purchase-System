package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.Seller;
import org.apache.ibatis.annotations.*;

/**
 * @Date: 2018/11/30 9:21
 * @Author: fangyong
 */
@Mapper
public interface SellerMapper {

    //商家注册
    @Insert("insert into tb_seller(seller_id,seller_nickname,seller_name,seller_password,seller_identity_id,seller_phone,seller_email,store_name,store_area)\n" +
            " values(#{sellerId}, #{sellerNickName}, #{sellerName}, #{sellerPassword}, #{sellerIdentityId}, #{sellerPhone}, #{sellerEmail}, #{storeName}, #{storeArea} )")
    void sellerRegister(@Param("sellerId") String sellerId, @Param("sellerNickName") String sellerNickName, @Param("sellerName") String sellerName,
                        @Param("sellerPassword") String sellerPassword,@Param("sellerIdentityId") String sellerIdentityId, @Param("sellerPhone") String sellerPhone,
                        @Param("sellerEmail") String sellerEmail, @Param("storeName") String storeName, @Param("storeArea") String storeArea);

    //商家登录
    @Select("select * from tb_seller where seller_id = #{sellerId} and seller_password = #{sellerPassword}")
    Seller sellerLogin(@Param("sellerId") String sellerId, @Param("sellerPassword") String sellerPassword);

    //检测数据库中是否存在该手机号码的商家
    @Select("select * from tb_seller where seller_phone = #{sellerPhone}")
    Seller checkPhone(@Param("sellerPhone") String sellerPhone);

    //检测数据库中是否有该商家的id与手机号码相符合
    @Select("select count(1) from tb_seller where seller_id = #{sellerId} and seller_phone = #{sellerPhone}")
    String checkIdWithPhone(@Param("sellerId") String sellerId, @Param("sellerPhone") String sellerPhone);

    //重置密码
    @Update("update tb_seller set seller_password = #{sellerPassword} where seller_id = #{sellerId}")
    void resetPassword(@Param("sellerId") String sellerId, @Param("sellerPassword") String sellerPassword);

    @Select("select seller_id from tb_seller where seller_phone = #{phone}")
    Seller checkPhoneExist(@Param("phone") String phone);
}
