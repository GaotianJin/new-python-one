package com.eighth.entity;

public class UserRoles {
	private Integer id;//用户角色表ID
	private String userId;//外键，用户ID
	private String roleId;//外键，角色ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
