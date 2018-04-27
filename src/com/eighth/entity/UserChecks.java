package com.eighth.entity;

import java.util.Date;

public class UserChecks {
	private Integer checkId;//签到Id
	private String userId;//用户Id
	private String UserName;//用户名称
	private Date checkInTime;//签到时间
	private String checkState;//签到状态
	private Date CheckOutTime;//取消签到时间
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
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public Date getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public Date getCheckOutTime() {
		return CheckOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		CheckOutTime = checkOutTime;
	}
	public UserChecks(String userName, Date checkInTime, String checkState,
			Date checkOutTime, int page, int rows) {
		super();
		UserName = userName;
		this.checkInTime = checkInTime;
		this.checkState = checkState;
		CheckOutTime = checkOutTime;
		this.page = page;
		this.rows = rows;
	}
	public UserChecks() {
		super();
	}
	
}
