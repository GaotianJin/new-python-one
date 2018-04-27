package com.eighth.entity;

import java.util.Date;

public class Dynamic {
	private Integer id;
	private String dyaskerId;
	private String dyaskerName;
	private String dystuId;
	private String dystuName;
	private String dynamicContext;
	private Date dycreateTime;
	private Integer dyisLook;
	private int page;
	private int rows;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDyaskerId() {
		return dyaskerId;
	}


	public void setDyaskerId(String dyaskerId) {
		this.dyaskerId = dyaskerId;
	}


	public String getDyaskerName() {
		return dyaskerName;
	}


	public void setDyaskerName(String dyaskerName) {
		this.dyaskerName = dyaskerName;
	}


	public String getDystuId() {
		return dystuId;
	}


	public void setDystuId(String dystuId) {
		this.dystuId = dystuId;
	}


	public String getDystuName() {
		return dystuName;
	}


	public void setDystuName(String dystuName) {
		this.dystuName = dystuName;
	}


	public String getDynamicContext() {
		return dynamicContext;
	}


	public void setDynamicContext(String dynamicContext) {
		this.dynamicContext = dynamicContext;
	}


	public Date getDycreateTime() {
		return dycreateTime;
	}


	public void setDycreateTime(Date dycreateTime) {
		this.dycreateTime = dycreateTime;
	}


	public Integer getDyisLook() {
		return dyisLook;
	}


	public void setDyisLook(Integer dyisLook) {
		this.dyisLook = dyisLook;
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
	
	
	

	public Dynamic(String dyaskerId, int page, int rows) {
		super();
		this.dyaskerId = dyaskerId;
		this.page = page;
		this.rows = rows;
	}


	public Dynamic(){};
	
}
