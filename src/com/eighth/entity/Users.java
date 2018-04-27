package com.eighth.entity;

import java.util.Date;

public class Users{
	private String userId;
	private String userName;
	private String userPASSWORD;
	private String userEmail;
	private String userPhone;
	private Date userCreateTime;
	private Date userLastLoginTime;
	private Integer userPsdWrongTime;
	private String userIsLockout;
	private Date userLockTime;
	private String userIsDel;
	private int page;
	private int rows;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPASSWORD() {
		return userPASSWORD;
	}
	public void setUserPASSWORD(String userPASSWORD) {
		this.userPASSWORD = userPASSWORD;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Date getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	public Date getUserLastLoginTime() {
		return userLastLoginTime;
	}
	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}
	public Integer getUserPsdWrongTime() {
		return userPsdWrongTime;
	}
	public void setUserPsdWrongTime(Integer userPsdWrongTime) {
		this.userPsdWrongTime = userPsdWrongTime;
	}
	public String getUserIsLockout() {
		return userIsLockout;
	}
	public void setUserIsLockout(String userIsLockout) {
		this.userIsLockout = userIsLockout;
	}
	public Date getUserLockTime() {
		return userLockTime;
	}
	public void setUserLockTime(Date userLockTime) {
		this.userLockTime = userLockTime;
	}
	public String getUserIsDel() {
		return userIsDel;
	}
	public void setUserIsDel(String userIsDel) {
		this.userIsDel = userIsDel;
	}
	public Users(String userName, String userEmail, String userPhone,int page,int rows) {
		super();
		this.page=page;
		this.rows=rows;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
	}
	public Users() {
		super();
	}
	
	
}
