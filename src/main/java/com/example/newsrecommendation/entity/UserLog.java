package com.example.newsrecommendation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLog {
    private long id;
    private String username;
    private String operation;
    private Date gmtModified;
}
