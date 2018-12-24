package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @Date: 2018/11/22 16:04
 * @Author: fangyong
 */
@Mapper
public interface UserMapper {

    //会员用户注册
    @Insert(" insert into tb_user(id, userName, password, gender, birth, phone, email, area, type)\n" +
            " values(#{id}, #{userName}, #{password}, #{gender}, #{birth}, #{phone}, #{email}, #{area}, #{type})")
    void insertRegisterMessage(@Param("id") String id, @Param("userName") String userName, @Param("gender") String gender,
                               @Param("birth") String birth, @Param("phone") String phone, @Param("email") String email,
                               @Param("password") String password, @Param("area") String area, @Param("type")String type);

    //游客注册
    @Insert(" insert into tb_user(id, userName, password, gender, birth, phone, email, area, type, stage)\n" +
            " values(#{id}, #{userName}, #{password}, #{gender}, #{birth}, #{phone}, #{email}, #{area}, #{type})")
    void insertRegisterNormalMessage(@Param("id") String id, @Param("userName") String userName, @Param("gender") String gender,
                                       @Param("birth") String birth, @Param("phone") String phone, @Param("email") String email,
                                       @Param("password") String password, @Param("area") String area, @Param("type") String type);

    //用户登录
    @Select("select * from tb_user where id = #{id} and password = #{password}")
    User userLogin(@Param("id") String id, @Param("password") String password);

    //检测数据库中是否存在该手机号码的用户
    @Select("select * from tb_user where phone = #{phone}")
    User checkPhone(@Param("phone") String phone);

    //修改用户信息
    @Update("update tb_user set userName =#{userName},  gender=#{gender}, birth=#{birth},email=#{email},\n"+
            " phone=#{phone}, area=#{area} where id=#{id}")
    void updateUserMessage(@Param("id") String id, @Param("userName") String userName, @Param("gender") String gender,
                           @Param("birth") String birth, @Param("phone") String phone, @Param("email") String email,
                            @Param("area") String area);

    //会员用户重置密码
    @Update("update tb_user set password = #{password} where id = #{id} ")
    void resetPassword(@Param("id") String id, @Param("password") String password);

    //检测数据库中是否有该商家的id与手机号码相符合
    @Select("select id from tb_user where  phone = #{phone}")
    User checkIdWithPhone( String phone);

    //用户登录根据手机号码获取用户信息
    @Select("select * from tb_user where phone = #{phone} ")
    User getUsernameByPhone(@Param("phone") String phone);

    //查找用户信息通过id
    @Select("select * from tb_user where id=#{userId}")
    User getUserInfoById(@Param("userId") String userId);

    //修改用户账户余额
    @Update("update tb_user set vacancy=vacancy+#{money} where id=#{userId}")
    void updateAccountData(@Param("userId") String userId,@Param("money") Float money);

    //查询所有user信息
    @Select("select * from tb_user")
    List<User> getAllUserInfo();

    //检测是否id被检测
    @Select("select * from tb_user where id=#{id}")
    User checkIdHasRegist(@Param("id") String id);
}
