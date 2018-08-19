package com.chong.bys.base;

import lombok.Data;

/**
 * @author lichong
 * @date 2018/08/16
 */
@Data
public class Result<T> {


    private Integer code;

    private String message;

    private T data;


    private Result() {
    }


    public static <T> Result<T> success() {

        return getInstance(200, "操作成功", null);
    }

    public static <T> Result<T> success(T data) {

        return getInstance(200, "操作成功", data);
    }

    public static <T> Result<T> success(String message,T data) {

        return getInstance(200, message, data);
    }

    public static <T> Result<T> error(Integer code, String message) {

        return getInstance(code, message, null);
    }

    private static <T> Result<T> getInstance(Integer code, String message, T data) {

        Result<T> result = new Result<T>();
        result.code = code;
        result.message = message;
        result.data = data;
        return result;
    }

}
