/**
 * 
 */
package com.chong.bys.bystest.java.test;

import java.util.Date;

/**
 * @author newli
 * @ClassName: User 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @date 2018年1月18日 下午2:43:51 
 */
public class User {
	
	
	private Long id;
	
	private String name;
	
	private String address;

	private Date creatTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
