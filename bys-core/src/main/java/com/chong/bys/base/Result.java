package com.chong.bys.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lichong
 * @date 2018/08/16
 */
@Data
public class Result<T> {

    private static int CustomErrorCode = 900;

    private Integer status;

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

    public static <T> Result<T> error(Integer status, String message) {

        return getInstance(status, message, null);
    }

    public static <T> Result<T> error(String message) {

        return getInstance(CustomErrorCode, message, null);
    }

    private static <T> Result<T> getInstance(Integer status, String message, T data) {

        Result<T> result = new Result<T>();
        result.status = status;
        result.message = message;
        result.data = data;
        return result;
    }
}
