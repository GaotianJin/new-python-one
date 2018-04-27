package com.eighth.service;

import java.util.List;

import com.eighth.entity.Address;

public interface AddressService {
	/**
	 * 查询所有住址
	 * @return
	 */
	List<Address> getAll();
}
