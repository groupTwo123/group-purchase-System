package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


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

}
