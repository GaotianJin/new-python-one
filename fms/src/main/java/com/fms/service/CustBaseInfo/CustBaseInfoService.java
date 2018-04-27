package com.fms.service.CustBaseInfo;

import java.util.List;

import com.fms.db.model.CustBaseInfo;

public interface CustBaseInfoService{
	
	/**
	 * 给下拉框的
	 * @return
	 */
	List<CustBaseInfo> getCustAllForSelect();
}
