package com.sinosoft.core.application.util;

public class CalBase {
	// 险种流水号
	private String pDRiskID;
	// 险种编码
	private String riskCode;
	// 合同流水号
	private String nBPolicyID;
	// 合同号码
	private String policyNo;
	// 投保单号
	private String proposalNo;
	// 投保单责任组合流水号
	private String nBPolicyPlanID;
	// 责任组合流水号
	private String pDRiskPlanID;
	// 责任组合代码
	private String planCode;
	// 保费
	private String prem;
	// 保全申请日期
	private String edorApplyDate;
	// 保单生效日期
	private String policyEffectiveDate;
	// 保险期间
	private String insuYear;
	// 保费差额
	private String difPrem;
	// 交至日期
	private String payToDate;
	// 赠险标记
	private String presentRisk;

	public String getPayToDate() {
		return payToDate;
	}

	public void setPayToDate(String payToDate) {
		this.payToDate = payToDate;
	}

	public String getpDRiskID() {
		return pDRiskID;
	}

	public void setpDRiskID(String pDRiskID) {
		this.pDRiskID = pDRiskID;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getnBPolicyID() {
		return nBPolicyID;
	}

	public void setnBPolicyID(String nBPolicyID) {
		this.nBPolicyID = nBPolicyID;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getnBPolicyPlanID() {
		return nBPolicyPlanID;
	}

	public void setnBPolicyPlanID(String nBPolicyPlanID) {
		this.nBPolicyPlanID = nBPolicyPlanID;
	}

	public String getpDRiskPlanID() {
		return pDRiskPlanID;
	}

	public void setpDRiskPlanID(String pDRiskPlanID) {
		this.pDRiskPlanID = pDRiskPlanID;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getPrem() {
		return prem;
	}

	public void setPrem(String prem) {
		this.prem = prem;
	}

	public String getEdorApplyDate() {
		return edorApplyDate;
	}

	public void setEdorApplyDate(String edorApplyDate) {
		this.edorApplyDate = edorApplyDate;
	}

	public String getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}

	public void setPolicyEffectiveDate(String policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}

	public String getInsuYear() {
		return insuYear;
	}

	public void setInsuYear(String insuYear) {
		this.insuYear = insuYear;
	}

	public String getDifPrem() {
		return difPrem;
	}

	public void setDifPrem(String difPrem) {
		this.difPrem = difPrem;
	}

	public String getPresentRisk() {
		return presentRisk;
	}

	public void setPresentRisk(String presentRisk) {
		this.presentRisk = presentRisk;
	}

}
