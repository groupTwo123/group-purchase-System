package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName RoleMapper
 * @Description TODO
 * @Author fangyong
 * @Date 2018/12/6 10:05
 **/
@Mapper
public interface RoleMapper {

    @Select("select * from tb_user where userName = #{username}")
    User findByUserName(@Param("username") String username);
}
