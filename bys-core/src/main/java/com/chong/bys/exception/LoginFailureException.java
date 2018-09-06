package com.chong.bys.exception;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/6 22:35
 * @since 1.0
 */
public class LoginFailureException extends RuntimeException {

    public LoginFailureException(String errorMessage) {
        super(errorMessage);
    }
}
