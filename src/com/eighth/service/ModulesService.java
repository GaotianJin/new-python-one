package com.eighth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.IModulesDao;
import com.eighth.entity.Modules;

@Service
public interface ModulesService {
	/**
	 * 根据Id查询子菜单
	 * @param moduleParentId
	 * @return com.eighth.entity.Modules
	 * */
    List<Modules> selectByParentId(Integer moduleParentId);
    
    /**
	 * 查询菜单集合
	 * */
	List<Modules> getMenuList(String rId);
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
	  * 递归删除
	  * */
	int deleteByPrimaryKey(Integer mId);
	
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
	  * 查询用户权限模块
	  * @param uId
	  * @return
	  */
	 List<Modules> selectByUserid(String uId);
	 
	 /**
	  * 递归添加父节点
	  * @param mid
	  * @param rid
	  * @return
	  */
	void selectParentIdByMid(int mid, int rid);
}
