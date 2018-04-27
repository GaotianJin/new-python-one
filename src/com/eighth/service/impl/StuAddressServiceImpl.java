package com.eighth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IAddressDao;
import com.eighth.dao.INetStudentDao;
import com.eighth.entity.Address;
import com.eighth.entity.StuAddress;
import com.eighth.service.StuAddressService;
@Service
public class StuAddressServiceImpl implements StuAddressService {
	
	@Autowired
	private IAddressDao adao;
	@Autowired
	private INetStudentDao ndao;
	
	/**
	 * 学生住址分布图Json
	 */
	@Override
	public List<StuAddress> getJson() {
		List<Address> alist=adao.getAll();
		List list=new ArrayList();
		for (Address a : alist) {
			StuAddress sa=new StuAddress();
			sa.setName(a.getAddress());
			sa.setValue(ndao.getCountByAddress(a.getAddress()));
			list.add(sa);
		}
		return list;
	}

}
