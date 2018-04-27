package com.eighth.entity;

import java.util.Date;

public class Netfollows {
	private Integer id;//跟踪Id
	private String studentId;//学生Id
	private String studentName;//学生名称
	private Date followTime;//跟踪时间
	private Date nextFollowTime;//下次跟踪时间
	private String content;//备注
	private String userId;//用户Id
	private String followType;//跟踪类型
	private Date createTime;//创建时间
	private String followState;//跟踪状态
	private String folIsDel;//是否删除，0：有效；1：删除；
	
	private int page;
	private int rows;
	private Date dateA;
	private Date dateB;
	
	public Date getDateA() {
		return dateA;
	}
	public void setDateA(Date dateA) {
		this.dateA = dateA;
	}
	public Date getDateB() {
		return dateB;
	}
	public void setDateB(Date dateB) {
		this.dateB = dateB;
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
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Date getFollowTime() {
		return followTime;
	}
	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}
	public Date getNextFollowTime() {
		return nextFollowTime;
	}
	public void setNextFollowTime(Date nextFollowTime) {
		this.nextFollowTime = nextFollowTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFollowType() {
		return followType;
	}
	public void setFollowType(String followType) {
		this.followType = followType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFollowState() {
		return followState;
	}
	public void setFollowState(String followState) {
		this.followState = followState;
	}
	public String getFolIsDel() {
		return folIsDel;
	}
	public void setFolIsDel(String folIsDel) {
		this.folIsDel = folIsDel;
	}
	public Netfollows(String userId,String studentName,String followType,
			String followState, int page, int rows, Date dateA, Date dateB) {
		super();
		this.userId = userId;
		this.studentName = studentName;
		this.followType = followType;
		this.followState = followState;
		this.page = page;
		this.rows = rows;
		this.dateA = dateA;
		this.dateB = dateB;
	}
	public Netfollows() {
		super();
	}
	public Netfollows(String studentId, int page, int rows) {
		super();
		this.studentId = studentId;
		this.page = page;
		this.rows = rows;
	}
	
	
}
