package com.eighth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IRoleModulesDao;
import com.eighth.entity.RoleModules;
import com.eighth.service.RoleModulesService;
@Service
public class RoleModulesServiceImpl implements RoleModulesService {
	
	@Autowired
	private IRoleModulesDao dao;
	
	
	@Override
	public List<RoleModules> selectModuleId(String id) {
		return dao.selectModuleId(id);
	}

	@Override
	public int insertRoleModules(Integer roleid, int menuid) {
		return dao.insertRoleModules(roleid, menuid);
	}

	@Override
	public int deleteRoleModules(int roleid) {
		return dao.deleteRoleModules(roleid);
	}
}
