package com.felix.grouppurchase.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @Date: 2018/11/22 16:04
 * @Author: fangyong
 */
@Mapper
public interface UserMapper {

    //用户注册
    @Insert(" insert into tb_user(id, userName, password, gender, birth, phone, email, area, type)\n" +
            " values(#{id}, #{userName}, #{password}, #{gender}, #{birth}, #{phone}, #{email}, #{area}, #{type})")
    void insertRegisterMessage(@Param("id") String id, @Param("userName") String userName, @Param("gender") String gender, @Param("birth") String birth, @Param("phone") String phone, @Param("email") String email, @Param("password") String password, @Param("area") String area, @Param("type")String type);
}
