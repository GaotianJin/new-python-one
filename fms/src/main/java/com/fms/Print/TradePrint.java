package com.fms.Print;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradePrint implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String CURRENCY;//币种
	private String CHNNAME;//中文名
	private String ENGNAME;//英文名
	private String SEX;//性别
	private String BIRTHDAY;//生日
	private String IDTYPE;//证件类型
	private String IDNO;//证件号
	private String PLACE;//国籍
	private String ACCNAME;//开户名
	private String BANKCODE;//银行名
	private String BANKACCNO;//账号
	private String COUNTRY;//详细地址
	private String ZIPCODE;//邮编
	private String MOBILE;//手机
	private String PHONE;//座机
	private String EMAIL;//邮箱
	private String SUBSCRIPTIONFEE;//认购额大写
	private String SUBSCRIPTIONFEESMALL;//认购额小写
	private String STARTFEE;//初始管理费大写
	private String STARTSMALL;//初始管理费小写
	private String EXPECTOPENDAY;//期望开放日
	private String BENEFICIALTYPE;//受益权类型
	private String PRODUCTNAME;//产品名称
	private String CLOSEDPERIOD;//封闭区间
	private String FEERATE;//预期收益率
	private String APPDATE;//申请日期
	private String BARCODE;//条形码
	private String AGENTCODE;//财富顾问代码/工号
	private String AGENTNAME;//财富顾问姓名
	
	public String getAPPDATE() {
		return APPDATE;
	}
	public void setAPPDATE(String aPPDATE) {
		APPDATE = aPPDATE;
	}

	public String getAGENTCODE() {
		return AGENTCODE;
	}
	public void setAGENTCODE(String aGENTCODE) {
		AGENTCODE = aGENTCODE;
	}
	public String getAGENTNAME() {
		return AGENTNAME;
	}
	public void setAGENTNAME(String aGENTNAME) {
		AGENTNAME = aGENTNAME;
	}
	
	
	public String getCURRENCY() {
		return CURRENCY;
	}
	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}
	public String getCHNNAME() {
		return CHNNAME;
	}
	public void setCHNNAME(String cHNNAME) {
		CHNNAME = cHNNAME;
	}
	public String getENGNAME() {
		return ENGNAME;
	}
	public void setENGNAME(String eNGNAME) {
		ENGNAME = eNGNAME;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getBIRTHDAY() {
		return BIRTHDAY;
	}
	public void setBIRTHDAY(String bIRTHDAY) {
		BIRTHDAY = bIRTHDAY;
	}
	public String getIDTYPE() {
		return IDTYPE;
	}
	public void setIDTYPE(String iDTYPE) {
		IDTYPE = iDTYPE;
	}
	public String getIDNO() {
		return IDNO;
	}
	public void setIDNO(String iDNO) {
		IDNO = iDNO;
	}
	public String getPLACE() {
		return PLACE;
	}
	public void setPLACE(String pLACE) {
		PLACE = pLACE;
	}
	public String getACCNAME() {
		return ACCNAME;
	}
	public void setACCNAME(String aCCNAME) {
		ACCNAME = aCCNAME;
	}
	public String getBANKCODE() {
		return BANKCODE;
	}
	public void setBANKCODE(String bANKCODE) {
		BANKCODE = bANKCODE;
	}
	public String getBANKACCNO() {
		return BANKACCNO;
	}
	public void setBANKACCNO(String bANKACCNO) {
		BANKACCNO = bANKACCNO;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	public String getZIPCODE() {
		return ZIPCODE;
	}
	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getSUBSCRIPTIONFEE() {
		return SUBSCRIPTIONFEE;
	}
	public void setSUBSCRIPTIONFEE(String sUBSCRIPTIONFEE) {
		SUBSCRIPTIONFEE = sUBSCRIPTIONFEE;
	}
	public String getSUBSCRIPTIONFEESMALL() {
		return SUBSCRIPTIONFEESMALL;
	}
	public void setSUBSCRIPTIONFEESMALL(String sUBSCRIPTIONFEESMALL) {
		SUBSCRIPTIONFEESMALL = sUBSCRIPTIONFEESMALL;
	}
	public String getSTARTFEE() {
		return STARTFEE;
	}
	public void setSTARTFEE(String sTARTFEE) {
		STARTFEE = sTARTFEE;
	}
	public String getSTARTSMALL() {
		return STARTSMALL;
	}
	public void setSTARTSMALL(String sTARTSMALL) {
		STARTSMALL = sTARTSMALL;
	}
	public String getEXPECTOPENDAY() {
		return EXPECTOPENDAY;
	}
	public void setEXPECTOPENDAY(String eXPECTOPENDAY) {
		EXPECTOPENDAY = eXPECTOPENDAY;
	}
	public String getBENEFICIALTYPE() {
		return BENEFICIALTYPE;
	}
	public void setBENEFICIALTYPE(String bENEFICIALTYPE) {
		BENEFICIALTYPE = bENEFICIALTYPE;
	}
	public String getPRODUCTNAME() {
		return PRODUCTNAME;
	}
	public void setPRODUCTNAME(String pRODUCTNAME) {
		PRODUCTNAME = pRODUCTNAME;
	}
	public String getCLOSEDPERIOD() {
		return CLOSEDPERIOD;
	}
	public void setCLOSEDPERIOD(String cLOSEDPERIOD) {
		CLOSEDPERIOD = cLOSEDPERIOD;
	}
	public String getFEERATE() {
		return FEERATE;
	}
	public void setFEERATE(String fEERATE) {
		FEERATE = fEERATE;
	}
	public String getBARCODE() {
		return BARCODE;
	}
	public void setBARCODE(String bARCODE) {
		BARCODE = bARCODE;
	}
	public TradePrint(String cURRENCY, String cHNNAME, String eNGNAME,
			String sEX, String bIRTHDAY, String iDTYPE, String iDNO,
			String pLACE, String aCCNAME, String bANKCODE, String bANKACCNO,
			String cOUNTRY, String zIPCODE, String mOBILE, String pHONE,
			String eMAIL, String sUBSCRIPTIONFEE, String sUBSCRIPTIONFEESMALL,
			String sTARTFEE, String sTARTSMALL, String eXPECTOPENDAY,
			String bENEFICIALTYPE, String pRODUCTNAME, String cLOSEDPERIOD,
			String fEERATE) {
		super();
		CURRENCY = cURRENCY;
		CHNNAME = cHNNAME;
		ENGNAME = eNGNAME;
		SEX = sEX;
		BIRTHDAY = bIRTHDAY;
		IDTYPE = iDTYPE;
		IDNO = iDNO;
		PLACE = pLACE;
		ACCNAME = aCCNAME;
		BANKCODE = bANKCODE;
		BANKACCNO = bANKACCNO;
		COUNTRY = cOUNTRY;
		ZIPCODE = zIPCODE;
		MOBILE = mOBILE;
		PHONE = pHONE;
		EMAIL = eMAIL;
		SUBSCRIPTIONFEE = sUBSCRIPTIONFEE;
		SUBSCRIPTIONFEESMALL = sUBSCRIPTIONFEESMALL;
		STARTFEE = sTARTFEE;
		STARTSMALL = sTARTSMALL;
		EXPECTOPENDAY = eXPECTOPENDAY;
		BENEFICIALTYPE = bENEFICIALTYPE;
		PRODUCTNAME = pRODUCTNAME;
		CLOSEDPERIOD = cLOSEDPERIOD;
		FEERATE = fEERATE;
	}
	public TradePrint() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
