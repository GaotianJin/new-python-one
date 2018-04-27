package com.fms.Print;

import java.io.Serializable;

public class RedemptionPrint implements Serializable {

	private static final long serialVersionUID = 1L;

	private String PRODUCTNAME;//产品名称
	private String CUSTNAME;//受益人姓名
	private String IDNO;//证件号码
	private String IDTYPE;//证件类型
	private String MOBLIE;//移动电话
	private String EMAIL;//邮箱
	private String ADDRESS;//联系地址
	private String ZIPCODE;//邮编
	private String ACCNAME;//户名
	private String BANKNO;//账号
	private String BANKNAME;//开户行
	private String AGENCYCODE;//产品方
	private String AGENCYNAME;//产品方名称
	private String REDEMPTIONOPENDAY;//赎回申请开放日
	private String REDEMPTIONSHAREBIG;//赎回份额大写
	private String REDEMPTIONSHARESMALL;//赎回份额小写
	private String AGENTNAME;//财富顾问名称
	private String AGENTCODE;//员工编码
	private String REDEMPTIONAPPLYDATE;//申请日期

	///////////////////////////////////////////
	private String REDEMPTIONFEE;//赎回费
	private String CLOSEDATE;//封闭期
	public String getREDEMPTIONFEE() {
		return REDEMPTIONFEE;
	}

	public void setREDEMPTIONFEE(String rEDEMPTIONFEE) {
		REDEMPTIONFEE = rEDEMPTIONFEE;
	}

		public void setCLOSEDATE(String cLOSEDATE) {
		CLOSEDATE = cLOSEDATE;
	}

	public String getCLOSEDATE() {
		return CLOSEDATE;
	}
	///////////////////////////////////////////

	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPRODUCTNAME() {
		return PRODUCTNAME;
	}

	public void setPRODUCTNAME(String pRODUCTNAME) {
		PRODUCTNAME = pRODUCTNAME;
	}

	public String getCUSTNAME() {
		return CUSTNAME;
	}

	public void setCUSTNAME(String cUSTNAME) {
		CUSTNAME = cUSTNAME;
	}

	public String getIDNO() {
		return IDNO;
	}

	public void setIDNO(String iDNO) {
		IDNO = iDNO;
	}

	public String getIDTYPE() {
		return IDTYPE;
	}

	public void setIDTYPE(String iDTYPE) {
		IDTYPE = iDTYPE;
	}

	public String getMOBLIE() {
		return MOBLIE;
	}

	public void setMOBLIE(String mOBLIE) {
		MOBLIE = mOBLIE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getZIPCODE() {
		return ZIPCODE;
	}

	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}

	public String getACCNAME() {
		return ACCNAME;
	}

	public void setACCNAME(String aCCNAME) {
		ACCNAME = aCCNAME;
	}

	public String getBANKNO() {
		return BANKNO;
	}

	public void setBANKNO(String bANKNO) {
		BANKNO = bANKNO;
	}

	public String getBANKNAME() {
		return BANKNAME;
	}

	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}

	public String getAGENCYCODE() {
		return AGENCYCODE;
	}

	public void setAGENCYCODE(String aGENCYCODE) {
		AGENCYCODE = aGENCYCODE;
	}



	public String getAGENCYNAME() {
		return AGENCYNAME;
	}

	public void setAGENCYNAME(String aGENCYNAME) {
		AGENCYNAME = aGENCYNAME;
	}

	public String getREDEMPTIONOPENDAY() {
		return REDEMPTIONOPENDAY;
	}

	public void setREDEMPTIONOPENDAY(String rEDEMPTIONOPENDAY) {
		REDEMPTIONOPENDAY = rEDEMPTIONOPENDAY;
	}

	public String getREDEMPTIONSHAREBIG() {
		return REDEMPTIONSHAREBIG;
	}

	public void setREDEMPTIONSHAREBIG(String rEDEMPTIONSHAREBIG) {
		REDEMPTIONSHAREBIG = rEDEMPTIONSHAREBIG;
	}

	public String getREDEMPTIONSHARESMALL() {
		return REDEMPTIONSHARESMALL;
	}

	public void setREDEMPTIONSHARESMALL(String rEDEMPTIONSHARESMALL) {
		REDEMPTIONSHARESMALL = rEDEMPTIONSHARESMALL;
	}

	public String getAGENTNAME() {
		return AGENTNAME;
	}

	public void setAGENTNAME(String aGENTNAME) {
		AGENTNAME = aGENTNAME;
	}

	public String getAGENTCODE() {
		return AGENTCODE;
	}

	public void setAGENTCODE(String aGENTCODE) {
		AGENTCODE = aGENTCODE;
	}

	public String getREDEMPTIONAPPLYDATE() {
		return REDEMPTIONAPPLYDATE;
	}

	public void setREDEMPTIONAPPLYDATE(String rEDEMPTIONAPPLYDATE) {
		REDEMPTIONAPPLYDATE = rEDEMPTIONAPPLYDATE;
	}

	public RedemptionPrint(String pRODUCTNAME, String cUSTNAME, String iDNO,
			String iDTYPE, String mOBLIE, String eMAIL, String aDDRESS,
			String zIPCODE, String aCCNAME, String bANKNO, String bANKNAME,
			String aGENCYCODE, String aGENCYNAME, String rEDEMPTIONOPENDAY,
			String rEDEMPTIONSHAREBIG, String rEDEMPTIONSHARESMALL,
			String aGENTNAME, String aGENTCODE, String rEDEMPTIONAPPLYDATE,
			String cLOSEDATE,String rEDEMPTIONFEE
			) {
		super();
		
		PRODUCTNAME = pRODUCTNAME;
		CUSTNAME = cUSTNAME;
		IDNO = iDNO;
		IDTYPE = iDTYPE;
		MOBLIE = mOBLIE;
		EMAIL = eMAIL;
		ADDRESS = aDDRESS;
		ZIPCODE = zIPCODE;
		ACCNAME = aCCNAME;
		BANKNO = bANKNO;
		BANKNAME = bANKNAME;
		AGENCYCODE = aGENCYCODE;
		AGENCYNAME = aGENCYNAME;
		REDEMPTIONOPENDAY = rEDEMPTIONOPENDAY;
		REDEMPTIONSHAREBIG = rEDEMPTIONSHAREBIG;
		REDEMPTIONSHARESMALL = rEDEMPTIONSHARESMALL;
		AGENTNAME = aGENTNAME;
		AGENTCODE = aGENTCODE;
		REDEMPTIONAPPLYDATE = rEDEMPTIONAPPLYDATE;
		CLOSEDATE = cLOSEDATE;
		REDEMPTIONFEE = rEDEMPTIONFEE;
	}

	public RedemptionPrint() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
