package com.felix.grouppurchase.mapper;


import com.felix.grouppurchase.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date: 2018/11/27 23:01
 * @Author: huangchuwen
 */
@Mapper
public interface AdminMapper {
    //获取所有用户类别
    @Select("select * from tb_admin_type")
    List<User> getAllAdminType();
}
