package com.chong.bystest.java.test;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author lichong
 * 2018/10/12 15:44
 * @version 1
 * @since 1.0
 */
@Data
@Builder
public class User2 implements Cloneable{

    private String id;

    private String name;

    private String address;

    private Date creatTime;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
