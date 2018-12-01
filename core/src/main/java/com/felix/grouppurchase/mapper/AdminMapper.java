package com.felix.grouppurchase.mapper;


import com.felix.grouppurchase.model.Admin;
import com.felix.grouppurchase.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date: 2018/11/27 23:01
 * @Author: huangchuwen
 */
@Mapper
public interface AdminMapper {

    @Select("select * from tb_admin where admin_id = #{adminId} and admin_password = #{adminPassword} ")
    Admin adminLogin(@Param("adminId") String adminId, @Param("adminPassword") String adminPassword);
}
