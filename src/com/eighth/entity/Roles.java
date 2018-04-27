package com.eighth.entity;

public class Roles {
	private Integer roleId;//用户组编号
	private String roleName;//角色(用户组)名称
	private String roleIsDel;//是否删除，0：有效；1：删除；
	private int page;
	private int rows;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleIsDel() {
		return roleIsDel;
	}
	public void setRoleIsDel(String roleIsDel) {
		this.roleIsDel = roleIsDel;
	}
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
	public Roles(String roleName, int page, int rows) {
		super();
		this.roleName = roleName;
		this.page = page;
		this.rows = rows;
	}
	public Roles() {
		super();
	}
	
}
