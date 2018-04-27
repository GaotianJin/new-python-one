package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Modules;

public interface IModulesDao {
	/**
	 * 根据Id查询子菜单
	 * @param moduleParentId
	 * @return com.eighth.entity.Modules
	 * */
    List<Modules> selectByParentId(Integer moduleParentId);
	/**
	 * 创建模块
	 * */
	int addModules(Modules module);
	/**
	 * 修改模块
	 * */
	int updModules(Modules module);
	/**
	 * 删除模块
	 * */
	int delModules(int moduleId);
	
	 /**
	 * 查询所有模块
	 * 返回集合
	 * **/
	 List<Modules> selectAll();
	 
	 /**
	  * 查询数据库是否已存在该节点名称
	  * @param name
	  * @return
	  */
	 int selectCountByModuleName(String name);
	 
	 /**
	  * 根据节点名称查询id
	  * @param name
	  * @return
	  */
	 int selectModuleIdByModuleName(String name);
	 
	 /**
	  * 根据用户id查所属模块
	  * @param uid
	  * @return
	  */
	 List<Modules> selectByUserid(String uid,Integer parentid);
	 
	 /**
	  * 根据当前ID查父ID
	  * @param mid
	  * @return
	  */
	 int selectParentIdByMid(int mid);
}
