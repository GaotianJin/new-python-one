package com.eighth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IRolesDao;
import com.eighth.dao.IUserRolesDao;
import com.eighth.entity.Roles;
import com.eighth.entity.UserRoles;
import com.eighth.service.UserRolesService;
@Service
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	private IUserRolesDao dao;
	@Autowired
	private IRolesDao rdao;
	@Override
	public List<UserRoles> selectRoleIdByUserId(String id) {
		return dao.selectRoleIdByUserId(id);
	}

	@Override
	public int insertUserRoles(UserRoles ur) {
		return dao.insertUserRoles(ur);
	}

	@Override
	public int updateUserRoles(UserRoles ur) {
		return dao.updateUserRoles(ur);
	}
	
	public List<Roles> getRolesByUserid(String userid){
		List<UserRoles> list=dao.selectRoleIdByUserId(userid);
		List<Roles> list1=new ArrayList<Roles>();
		for (UserRoles userRoles : list) {
			list1.add(rdao.selectRolesName(Integer.parseInt(userRoles.getRoleId())));
		}
		return list1;
	}

	@Override
	public int selectRole(String uid, String rid) {
		return dao.selectRole(uid, rid);
	}

	@Override
	public int deleteUserRoles(UserRoles ur) {
		return dao.deleteUserRoles(ur);
	}

}
