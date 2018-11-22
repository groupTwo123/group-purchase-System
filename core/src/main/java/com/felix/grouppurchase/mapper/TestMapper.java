package com.felix.grouppurchase.mapper;

import com.felix.grouppurchase.model.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date: 2018/11/22 9:58
 * @Author: fangyong
 */
@Mapper
public interface TestMapper {

    @Select("select * from tb_test where id = #{id}")
    public Test getNameById(Integer id);
}
