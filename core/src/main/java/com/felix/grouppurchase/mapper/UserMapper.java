package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.User;
import org.apache.ibatis.annotations.*;


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

    //商家注册
    @Insert(" insert into tb_user(id, userName, password, gender, birth, phone, email, area, type, stage)\n" +
            " values(#{id}, #{userName}, #{password}, #{gender}, #{birth}, #{phone}, #{email}, #{area}, #{type}, #{stage})")
    void insertRegisterBusinessMessage(@Param("id") String id, @Param("userName") String userName, @Param("gender") String gender,
                                       @Param("birth") String birth, @Param("phone") String phone, @Param("email") String email,
                                       @Param("password") String password, @Param("area") String area, @Param("type") String type,
                                       @Param("stage") String stage);

    //用户登录
    @Select("select * from tb_user where id = #{id} and password = #{password}")
    User userLogin(@Param("id") String id, @Param("password") String password);

    //检测数据库中是否存在该手机号码的用户
    @Select("select * from tb_user where phone = #{phone}")
    User checkPhone(@Param("phone") String phone);

    //修改用户信息
    @Update("update tb_user set userName =#{userName}, password=#{password}, gender=#{gender}, birth=#{birth},email=#{email},\n"+
            " phone=#{phone}, area=#{area} where id=#{id}")
    void updateUserMessage(@Param("id") String id, @Param("userName") String userName, @Param("gender") String gender,
                           @Param("birth") String birth, @Param("phone") String phone, @Param("email") String email,
                           @Param("password") String password, @Param("area") String area);
}
