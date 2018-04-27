package com.eighth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IModulesDao;
import com.eighth.dao.IRoleModulesDao;
import com.eighth.entity.Modules;
import com.eighth.entity.RoleModules;
import com.eighth.service.ModulesService;
@Service
public class ModulesServiceImpl implements ModulesService {
	@Autowired
	private IModulesDao dao;
	@Autowired
	private IRoleModulesDao rmdao;
	
	public List<Modules> getMenuList(String rId) {
		//查询所有根菜单
		List<Modules> list=dao.selectByParentId(0);
		//递归设置子菜单
		this.setChildrens(list,rId);
		return list;
	}
	/**
	 * 给菜单设计子菜单
	 * */
	private void setChildrens(List<Modules> list,String rId) {
		List<RoleModules> list1=rmdao.selectModuleId(rId);
		for (Modules modules : list) {
			//查询出子菜单
			List<Modules> modulesList=dao.selectByParentId(modules.getModuleId());
			
			for (Modules module : modulesList) {
				for (RoleModules rm : list1) {
					
						if(module.getModuleId().equals(rm.getModuleId())||rm.getModuleId()==module.getModuleId()){
							module.setChecked(true);
					}
				}	
			}
			
			//如果没有子菜单则递归结束
			if(modulesList==null||modulesList.isEmpty()){
				continue;
			}else{//如果有子菜单
				//则设置子菜单
				modules.setChildren(modulesList);
				//如果子菜单还有子菜单则继续设置子菜单
				this.setChildrens(modulesList,rId);
			}
		}
		
	}
	
	public List<Modules> selectByParentId(Integer moduleParentId) {
		return dao.selectByParentId(moduleParentId);
	}


	public int addModules(Modules module) {
		return dao.addModules(module);
	}


	public int updModules(Modules module) {

		return dao.updModules(module);
	}

	public int delModules(int moduleId) {

		return 0;
	}
	@Override
	public List<Modules> selectAll() {
		return dao.selectAll();
	}
	
	@Override
	public int deleteByPrimaryKey(Integer mId) {
		List<Modules> list=selectByParentId(mId);
		if( list==null || list.isEmpty() ){
			return dao.delModules(mId);			
		}else {//有子菜单				
			return this.delChildrens(list,mId);
		}
	}
	
	public int delChildrens(List<Modules> childrenList,int mParentid){
		int n=0;
		for(Modules m:childrenList){
			List<Modules> list=selectByParentId(m.getModuleId());
			if( childrenList==null || childrenList.isEmpty() ){
				n+=dao.delModules(m.getModuleId());
			}else {//有子菜单				
				this.delChildrens(list,m.getModuleId());
			}			
		}
		n+=dao.delModules(mParentid);
		return n;
	}
	@Override
	public int selectCountByModuleName(String name) {
		return dao.selectCountByModuleName(name);
	}
	@Override
	public int selectModuleIdByModuleName(String name) {
		return dao.selectModuleIdByModuleName(name);
	}
	
	public void  setChildrensByUid(List<Modules> rootMenus,String uid){
		for (Modules m : rootMenus) {
			//查询出子菜单modulesMapper.selectByParentId(0);
			List<Modules> childrenList = dao.selectByUserid(uid,m.getModuleId());
			//如果没有子菜单则递归结束
			if( childrenList==null || childrenList.isEmpty() ){
				continue;
			}else {//有子菜单
				//设置子菜单
				m.setChildren(childrenList);
				//如果有子菜单则继续递归设置子菜单
				this.setChildrensByUid(childrenList,uid); 
			}
		}
	}

	@Override
	public List<Modules> selectByUserid(String uId) {
		List<Modules> rootModules=dao.selectByUserid(uId, 0);
		this.setChildrensByUid(rootModules,uId);
		return rootModules;
	}
	@Override
	public void selectParentIdByMid(int mid,int rid) {
		int moduleid=dao.selectParentIdByMid(mid);
		if(moduleid!=0){
			rmdao.insertRoleModules(rid, mid);
			this.selectParentIdByMid(moduleid, rid);
		}else{
			rmdao.insertRoleModules(rid, mid);
		}
	}
}
