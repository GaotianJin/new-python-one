package com.eighth.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Students {
	private String stuId;//学生编号
	private String stuName;//学员姓名
	private Integer stuAge;//年龄
	private String stuSex;//性别
	private String stuPhone;//学员电话
	private String stuStatus;//学历
	private String perState;//个人状态
	private String msgSource;//来源渠道
	private String sourceUrl;//来源网址
	private String sourceKeyWord;//来源关键词
	private String stuAddress;//所在区域
	private String netPusherId;//网络咨询Id
	private String askerId;//咨询师Id
	private String askerName;
	private String stuQQ;//学员QQ
	private String stuWeiXin;//学员微信
	private String content;//备注
	private Date createTime;//创建时间
	private String learnForward;//课程方向
	private String isValid;//是否有效
	private String record;//打分
	private String isReturnVist;//是否回访
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date firstVisitTime;//首次回访时间
	private String isHome;//是否上门
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date homeTime;//上门时间
	private String lostValid;//无效原因
	private String isPay;//是否付款
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date PayTime;//付款时间
	private Float Money;//付款金额
	private String isReturnMoney;//是否退费
	private String isInClass;//是否进班
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date inClassTime;//进班时间
	private String inClassContent;//进班备注
	private String askerContent;//咨询师备注
	private String FromPart;//来源部门
	private String stuConcern;//学员关注
	private String IsBaoBei;//是否报备
	private String ziXunName;//咨询师填写的姓名
	private String createUser;//录入人姓名
	private String returnMoneyReason;//退费原因
	private Float preMoney;//预付定金
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date preMoneyTime;//预付定金时间
	private String stuIsDel;//是否删除，0：有效；1：删除；
	
	
	public String getAskerName() {
		return askerName;
	}
	public void setAskerName(String askerName) {
		this.askerName = askerName;
	}
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

	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Integer getStuAge() {
		return stuAge;
	}
	public void setStuAge(Integer stuAge) {
		this.stuAge = stuAge;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	public String getStuStatus() {
		return stuStatus;
	}
	public void setStuStatus(String stuStatus) {
		this.stuStatus = stuStatus;
	}
	public String getPerState() {
		return perState;
	}
	public void setPerState(String perState) {
		this.perState = perState;
	}
	public String getMsgSource() {
		return msgSource;
	}
	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getSourceKeyWord() {
		return sourceKeyWord;
	}
	public void setSourceKeyWord(String sourceKeyWord) {
		this.sourceKeyWord = sourceKeyWord;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	public String getNetPusherId() {
		return netPusherId;
	}
	public void setNetPusherId(String netPusherId) {
		this.netPusherId = netPusherId;
	}
	public String getAskerId() {
		return askerId;
	}
	public void setAskerId(String askerId) {
		this.askerId = askerId;
	}
	public String getStuQQ() {
		return stuQQ;
	}
	public void setStuQQ(String stuQQ) {
		this.stuQQ = stuQQ;
	}
	public String getStuWeiXin() {
		return stuWeiXin;
	}
	public void setStuWeiXin(String stuWeiXin) {
		this.stuWeiXin = stuWeiXin;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLearnForward() {
		return learnForward;
	}
	public void setLearnForward(String learnForward) {
		this.learnForward = learnForward;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getIsReturnVist() {
		return isReturnVist;
	}
	public void setIsReturnVist(String isReturnVist) {
		this.isReturnVist = isReturnVist;
	}
	public Date getFirstVisitTime() {
		return firstVisitTime;
	}
	public void setFirstVisitTime(Date firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}
	public String getIsHome() {
		return isHome;
	}
	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}
	public Date getHomeTime() {
		return homeTime;
	}
	public void setHomeTime(Date homeTime) {
		this.homeTime = homeTime;
	}
	public String getLostValid() {
		return lostValid;
	}
	public void setLostValid(String lostValid) {
		this.lostValid = lostValid;
	}
	public String getIsPay() {
		return isPay;
	}
	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}
	public Date getPayTime() {
		return PayTime;
	}
	public void setPayTime(Date payTime) {
		PayTime = payTime;
	}
	public Float getMoney() {
		return Money;
	}
	public void setMoney(Float money) {
		Money = money;
	}
	public String getIsReturnMoney() {
		return isReturnMoney;
	}
	public void setIsReturnMoney(String isReturnMoney) {
		this.isReturnMoney = isReturnMoney;
	}
	public String getIsInClass() {
		return isInClass;
	}
	public void setIsInClass(String isInClass) {
		this.isInClass = isInClass;
	}
	public Date getInClassTime() {
		return inClassTime;
	}
	public void setInClassTime(Date inClassTime) {
		this.inClassTime = inClassTime;
	}
	public String getInClassContent() {
		return inClassContent;
	}
	public void setInClassContent(String inClassContent) {
		this.inClassContent = inClassContent;
	}
	public String getAskerContent() {
		return askerContent;
	}
	public void setAskerContent(String askerContent) {
		this.askerContent = askerContent;
	}
	public String getFromPart() {
		return FromPart;
	}
	public void setFromPart(String fromPart) {
		FromPart = fromPart;
	}
	public String getStuConcern() {
		return stuConcern;
	}
	public void setStuConcern(String stuConcern) {
		this.stuConcern = stuConcern;
	}
	public String getIsBaoBei() {
		return IsBaoBei;
	}
	public void setIsBaoBei(String isBaoBei) {
		IsBaoBei = isBaoBei;
	}
	public String getZiXunName() {
		return ziXunName;
	}
	public void setZiXunName(String ziXunName) {
		this.ziXunName = ziXunName;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getReturnMoneyReason() {
		return returnMoneyReason;
	}
	public void setReturnMoneyReason(String returnMoneyReason) {
		this.returnMoneyReason = returnMoneyReason;
	}
	public Float getPreMoney() {
		return preMoney;
	}
	public void setPreMoney(Float preMoney) {
		this.preMoney = preMoney;
	}
	public Date getPreMoneyTime() {
		return preMoneyTime;
	}
	public void setPreMoneyTime(Date preMoneyTime) {
		this.preMoneyTime = preMoneyTime;
	}
	public String getStuIsDel() {
		return stuIsDel;
	}
	public void setStuIsDel(String stuIsDel) {
		this.stuIsDel = stuIsDel;
	}

	public Students(String stuName, String stuPhone, String stuQQ,
			String isValid, String isReturnVist, String isPay, int page,
			int rows, String askerName, String netPusherId, String askerId, Date dateA, Date dateB) {
		super();
		this.stuName = stuName;
		this.stuPhone = stuPhone;
		this.stuQQ = stuQQ;
		this.isValid = isValid;
		this.isReturnVist = isReturnVist;
		this.isPay = isPay;
		this.page = page;
		this.rows = rows;
		this.askerName = askerName;
		this.netPusherId = netPusherId;
		this.askerId = askerId;
		this.dateA = dateA;
		this.dateB = dateB;
		
	}
	public Students() {
		super();
	}
	@Override
	public String toString() {
		return "Students [stuId=" + stuId + ", stuName=" + stuName
				+ ", stuAge=" + stuAge + ", stuSex=" + stuSex + ", stuPhone="
				+ stuPhone + ", stuStatus=" + stuStatus + ", perState="
				+ perState + ", msgSource=" + msgSource + ", sourceUrl="
				+ sourceUrl + ", sourceKeyWord=" + sourceKeyWord
				+ ", stuAddress=" + stuAddress + ", netPusherId=" + netPusherId
				+ ", askerId=" + askerId + ",askerName="+askerName+", stuQQ=" + stuQQ + ", stuWeiXin="
				+ stuWeiXin + ", content=" + content + ", createTime="
				+ createTime + ", learnForward=" + learnForward + ", isValid="
				+ isValid + ", record=" + record + ", isReturnVist="
				+ isReturnVist + ", firstVisitTime=" + firstVisitTime
				+ ", isHome=" + isHome + ", homeTime=" + homeTime
				+ ", lostValid=" + lostValid + ", isPay=" + isPay
				+ ", PayTime=" + PayTime + ", Money=" + Money
				+ ", isReturnMoney=" + isReturnMoney + ", isInClass="
				+ isInClass + ", inClassTime=" + inClassTime
				+ ", inClassContent=" + inClassContent + ", askerContent="
				+ askerContent + ", FromPart=" + FromPart + ", stuConcern="
				+ stuConcern + ", IsBaoBei=" + IsBaoBei + ", ziXunName="
				+ ziXunName + ", createUser=" + createUser
				+ ", returnMoneyReason=" + returnMoneyReason + ", preMoney="
				+ preMoney + ", preMoneyTime=" + preMoneyTime + ", stuIsDel="
				+ stuIsDel + ", page=" + page + ", rows=" + rows + ", dateA="
				+ dateA + ", dateB=" + dateB + "]";
	}
	
	
	
}
