package com.eighth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.INetStudentDao;
import com.eighth.dao.ISetWeightDao;
import com.eighth.entity.Askers;
import com.eighth.entity.RoleStus;
import com.eighth.service.RoleStusService;
@Service
public class RoleStusServiceImpl implements RoleStusService {
	
	@Autowired
	private ISetWeightDao swdao;
	@Autowired
	private INetStudentDao studao;
	
	/**
	 * NetStudentCtroller调用
	 */
	@Override
	public List<RoleStus> getAll() {
		List<Askers> Aslist=swdao.getAllAsker();
		List list=new ArrayList();
		for (Askers a : Aslist){
			RoleStus rs= new RoleStus();
			rs.setName(a.getAskerName());
			rs.setValue(studao.getCountByAkserId(a.getAskerId()));
			list.add(rs);
		}
		return list;
	}
	
	@Override
	public int getCountByAkserId(String id) {
		return studao.getCountByAkserId(id);
	}

	@Override
	public Object getName() {
		List<Askers> Aslist=swdao.getAllAsker();
		List list=new ArrayList();
		for (Askers a : Aslist) {
			list.add(a.getAskerName());
		}
		String[] str=new String[list.size()];
		for (int i = 0; i < str.length; i++) {
				str[i]=(String) list.get(i);
		}
		return str;
	}
	
	
}
