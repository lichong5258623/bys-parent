package com.chong.bys.core.exception;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/6 0:46
 * @since 1.0
 */
public class BysException extends RuntimeException {

    public BysException(String errorMessage) {
        super(errorMessage);
    }
    @Override
    public void printStackTrace() {
    }
}
