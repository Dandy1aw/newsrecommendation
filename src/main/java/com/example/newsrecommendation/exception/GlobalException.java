package com.example.newsrecommendation.exception;

import com.example.newsrecommendation.result.CodeMessage;

/**
 * @Description   自定义全局异常
 * @Author 11103882
 * @Date 2019/9/24
 * @Version 1.0
 */
public class GlobalException extends RuntimeException{

    private CodeMessage codeMessage;
    public GlobalException(CodeMessage codeMessage){
        super(codeMessage.toString());
        this.codeMessage = codeMessage;
    }
    public CodeMessage getCodeMessage(){
        return codeMessage;
    }
}
