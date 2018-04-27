package com.eighth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.ISetWeightDao;
import com.eighth.entity.Askers;
import com.eighth.service.SetWeightService;

@Service("setweight")
public class SetWeightServiceImpl implements SetWeightService {
	@Autowired
	private ISetWeightDao sdao;
	public List<Askers> getAllAsker() {
		return sdao.getAllAsker();
	}
	@Override
	public int updateAskers(Askers asker) {
		return sdao.updateAskers(asker);
	}
	@Override
	public int insertAskers(Askers a) {
		return sdao.insertAskers(a);
	}
	@Override
	public int deleteAskers(String id) {
		return sdao.deleteAskers(id);
	}
	

}
