package com.example.newsrecommendation.dao;

import com.example.newsrecommendation.entity.UserLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/25
 */
@Mapper
public interface UserLogDao {
    int insertUserLog(UserLog userLog);
}
