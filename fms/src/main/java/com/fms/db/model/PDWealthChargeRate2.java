package com.fms.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class PDWealthChargeRate2 {
   

    
    private String floatManagementFeeRatio;

   
    private String fixManagementFeeRatio;

	private String channelFee;
    
    private String taxFee;

    
    public String getFloatManagementFeeRatio() {
		return floatManagementFeeRatio;
	}


	public void setFloatManagementFeeRatio(String floatManagementFeeRatio) {
		this.floatManagementFeeRatio = floatManagementFeeRatio;
	}


	public String getFixManagementFeeRatio() {
		return fixManagementFeeRatio;
	}


	public void setFixManagementFeeRatio(String fixManagementFeeRatio) {
		this.fixManagementFeeRatio = fixManagementFeeRatio;
	}


	public String getTaxFee() {
		return taxFee;
	}


	public void setTaxFee(String taxFee) {
		this.taxFee = taxFee;
	}


	public String getChannelFee() {
		return channelFee;
	}


	public void setChannelFee(String channelFee) {
		this.channelFee = channelFee;
	}



}