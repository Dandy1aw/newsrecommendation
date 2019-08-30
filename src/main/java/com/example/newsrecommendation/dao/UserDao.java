package com.example.newsrecommendation.dao;

import com.example.newsrecommendation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    User getById(@Param("id") Integer id);

    @Select("select * from user where username = #{username}")
    User getByUsername(@Param("username") String username);

}
