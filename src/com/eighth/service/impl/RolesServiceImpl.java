package com.eighth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IRolesDao;
import com.eighth.entity.Roles;
import com.eighth.entity.Users;
import com.eighth.service.RolesService;
@Service
public class RolesServiceImpl implements RolesService {
	@Autowired
	private IRolesDao dao;

	public int addRoles(Roles role) {
		return dao.addRoles(role);
	}

	public int updRoles(Roles role) {
		return dao.updRoles(role);
	}

	public int delRoles(int roleId) {
		return dao.delRoles(roleId);
	}

	@Override
	public Roles selectRolesName(Integer id) {
		return dao.selectRolesName(id);
	}

	@Override
	public Map getDatagrid(Roles role) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=role.getPage();
		int pageSize=role.getRows();
		int begin=(page-1)*pageSize;
		role.setPage(begin);
		map.put("total", dao.getCountRoles(role));
		map.put("rows", dao.getAllRole(role));
		return map;
	}

	@Override
	public int selectRolesByName(String name) {
		return dao.selectRolesByName(name);
	}
}
