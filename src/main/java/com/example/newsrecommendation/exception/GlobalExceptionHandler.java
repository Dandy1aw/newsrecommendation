package com.example.newsrecommendation.exception;

import com.example.newsrecommendation.result.CodeMessage;
import com.example.newsrecommendation.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description  全局异常处理器
 * @Author 11103882
 * @Date 2019/9/24
 * @Version 1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler  {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        e.printStackTrace();
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException)e;
            return Result.error(globalException.getCodeMessage());
        }else {
            return Result.error(CodeMessage.SERVER_ERROR);
        }
    }
}
