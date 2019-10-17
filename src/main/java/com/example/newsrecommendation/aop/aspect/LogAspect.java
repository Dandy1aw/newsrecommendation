package com.example.newsrecommendation.aop.aspect;

import ch.qos.logback.core.util.TimeUtil;
import com.example.newsrecommendation.aop.annotation.Log;
import com.example.newsrecommendation.config.UserContext;
import com.example.newsrecommendation.dao.UserLogDao;
import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.entity.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/9/25
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private UserLogDao userLogDao;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.example.newsrecommendation.aop.annotation.Log)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
            log.info(result.toString());
        }catch (Throwable e){
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - beginTime;
        saveLog(proceedingJoinPoint,time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        //根据切点获取方法签名（方法名）
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //根据方法名获取到方法
        Method method = methodSignature.getMethod();
        UserLog userLog = new UserLog();
        //获取该方法上的注解
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation!=null){
            //如果注解不为空，将注解中的String 保存到对象中
            userLog.setOperation(logAnnotation.value());
        }
        User user = UserContext.getUserThreadLocal();
        userLog.setUsername(user.getUsername());
        userLog.setGmtModified(new Date());
        userLogDao.insertUserLog(userLog);
    }
}
