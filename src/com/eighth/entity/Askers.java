package com.eighth.entity;

import java.util.Date;

public class Askers {
	private String askerId;//咨询师Id
	private String askerName;//咨询师名称
	private String checkState;//签到状态
	private Date checkInTime;//签到时间
	private String changeState;//是否分配学生：是，否
	private String weight;//权重
	private String roleName;//角色名称
	private String bakContent;//备注信息
	private String askerIsDel;//是否删除，0：有效；1：删除；
	public String getAskerId() {
		return askerId;
	}
	public void setAskerId(String askerId) {
		this.askerId = askerId;
	}
	public String getAskerName() {
		return askerName;
	}
	public void setAskerName(String askerName) {
		this.askerName = askerName;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public Date getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getBakContent() {
		return bakContent;
	}
	public void setBakContent(String bakContent) {
		this.bakContent = bakContent;
	}
	public String getAskerIsDel() {
		return askerIsDel;
	}
	public void setAskerIsDel(String askerIsDel) {
		this.askerIsDel = askerIsDel;
	}
}
