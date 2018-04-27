package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Address;

public interface IAddressDao {
	/**
	 * 查询所有住址
	 * @return
	 */
	List<Address> getAll();
}
