package net.trans.common.domain;

import java.util.Date;

/**
 *	为了方便后期获取token中的用户信息，将token中载荷部分单独封装成一个对象
 * @author Cx
 * @Date 2021-02-03
 * @Version 1.0
 */
public class Payload<T> {
	private String id;
	private T userInfo;
	private Date expiration;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public T getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(T userInfo) {
		this.userInfo = userInfo;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	
}

