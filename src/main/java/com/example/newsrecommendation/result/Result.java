package com.example.newsrecommendation.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description 用一个Result类封装json数据 返回
 * @Author 11103882
 * @Date 2019/8/29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private String message;
    private T data;
    /*错误码：0 表示 成功 其他类型 标注在CodeMsg中*/
    private int code;

    private Result(T data) {
        this.message="success!";
        this.code = 0;
        this.data = data;
    }

    private Result(CodeMessage codeMessage) {
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMsg();
    }

    /**
     *        成功返回的话，直接返回 success 信息，code 为0 且 返回该对象
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }

    /**
     *        出Bug 返回对应的错误码
     * @param codeMessage
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMessage codeMessage){
        return new Result<>(codeMessage);
    }
}
