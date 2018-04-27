package com.fms.service.CustBaseInfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.model.CustBaseInfo;
import com.fms.service.CustBaseInfo.CustBaseInfoService;

@Service
public class CustBaseInfoServiceImpl implements CustBaseInfoService{
	
	@Autowired
	private CustBaseInfoMapper cbm;

	@Override
	public List<CustBaseInfo> getCustAllForSelect() {
		List<CustBaseInfo> list=cbm.getCustAllForSelect();
		return list;
	}
	
}
