package com.eighth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IAutoOnOffDao;
import com.eighth.service.AutoOnOffService;
@Service
public class AutoOnOffServiceImpl implements AutoOnOffService {

	@Autowired
	private IAutoOnOffDao dao;
	
	@Override
	public int selectIsOpen() {
		return dao.selectIsOpen();
	}

	@Override
	public int updateIsOpen(Integer isOpen) {
		return dao.updateIsOpen(isOpen);
	}

}
