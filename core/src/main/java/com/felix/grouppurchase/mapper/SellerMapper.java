package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Date: 2018/11/30 9:21
 * @Author: fangyong
 */
@Mapper
public interface SellerMapper {

    //商家注册
    @Insert("insert into tb_seller(seller_id,seller_nickname,seller_name,seller_password,seller_identity_id,seller_phone,seller_email,store_name,store_area,volume_id)\n" +
            " values(#{sellerId}, #{sellerNickName}, #{sellerName}, #{sellerPassword}, #{sellerIdentityId}, #{sellerPhone}, #{sellerEmail}, #{storeName}, #{storeArea}, #{volumeId} )")
    void sellerRegister(@Param("sellerId") String sellerId, @Param("sellerNickName") String sellerNickName, @Param("sellerName") String sellerName,
                        @Param("sellerPassword") String sellerPassword,@Param("sellerIdentityId") String sellerIdentityId, @Param("sellerPhone") String sellerPhone,
                        @Param("sellerEmail") String sellerEmail, @Param("storeName") String storeName, @Param("storeArea") String storeArea, @Param("volumeId") String volumeId);

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

    //通过商家id查找商家信息
    @Select("select * from tb_seller where seller_id=#{id}")
    Seller getSellerInfoById(@Param("id") String sellerId);

    //商家修改账号信息
    @Update("update tb_seller set seller_nickname=#{sellerNickname}, seller_name=#{sellerName},seller_identity_id=#{sellerIdentityId},seller_email=#{sellerEmail},store_name=#{storeName},store_area=#{storeArea} where seller_id=#{sellerId}")
    void updateSellerInfo(@Param("sellerId") String sellerId, @Param("storeName") String storeName, @Param("sellerNickname") String sellerNickname, @Param("sellerName") String sellerName, @Param("sellerIdentityId") String sellerIdentityId, @Param("storeArea") String storeArea, @Param("sellerEmail") String sellerEmail);

    //修改商家账户余额（退货时候）
    @Update("update tb_seller set seller_account=seller_account - #{money} where seller_id=#{userId}")
    void updateAccountData(@Param("userId") String userId,@Param("money") Float money);

    //通过仓库id获取商家信息
    @Select("select * from tb_seller where volume_id=#{volumeId}")
    Seller getSellerInfoByVolumeId(@Param("volumeId") String volumeId);

    //获取所有商家信息
    @Select("select * from tb_seller")
    List<Seller> getAllSeller();

    //修改商家账号状态
    @Update("update tb_seller set state=#{state} where seller_id=#{sellerId}")
    void updateSellerState(@Param("state") String state, @Param("sellerId") String sellerId);
}
