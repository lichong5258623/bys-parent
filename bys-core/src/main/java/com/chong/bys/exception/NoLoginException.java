package com.chong.bys.exception;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/6 0:46
 * @since 1.0
 */
public class NoLoginException extends RuntimeException {

    public NoLoginException(String errorMessage) {
        super(errorMessage);
    }
}
