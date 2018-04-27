package com.eighth.entity;

import java.util.List;

public class Modules {
	private Integer moduleId;//模块编号
	private String moduleName;//模块名称
	private Integer moduleParentId;//父模块编号
	private String modulePath;//模块路径
	private String moduleWeight;//权重
	private String moduleIsDel;//是否删除，0：有效；1：删除；
	private List<Modules> Children;
	private boolean checked;//是否选中
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<Modules> getChildren() {
		return Children;
	}
	public void setChildren(List<Modules> children) {
		Children = children;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Integer getModuleParentId() {
		return moduleParentId;
	}
	public void setModuleParentId(Integer moduleParentId) {
		this.moduleParentId = moduleParentId;
	}
	public String getModulePath() {
		return modulePath;
	}
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}
	public String getModuleWeight() {
		return moduleWeight;
	}
	public void setModuleWeight(String moduleWeight) {
		this.moduleWeight = moduleWeight;
	}
	public String getModuleIsDel() {
		return moduleIsDel;
	}
	public void setModuleIsDel(String moduleIsDel) {
		this.moduleIsDel = moduleIsDel;
	}

}
