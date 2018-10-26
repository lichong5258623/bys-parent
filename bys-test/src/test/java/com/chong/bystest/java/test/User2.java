package com.chong.bystest.java.test;

import lombok.Data;

import java.util.Date;

/**
 * @author lichong
 * 2018/10/12 15:44
 * @version 1
 * @since 1.0
 */
@Data
public class User2 {

    private String id;

    private String name;

    private String address;

    private Date creatTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
