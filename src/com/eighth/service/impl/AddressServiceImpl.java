package com.eighth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IAddressDao;
import com.eighth.entity.Address;
import com.eighth.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private IAddressDao dao;
	
	@Override
	public List<Address> getAll() {
		return dao.getAll();
	}

}
