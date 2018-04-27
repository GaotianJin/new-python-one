package com.sinosoft.core.application.impl;

/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 Eric 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 *****************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.core.application.RoleService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.mapper.DefPrivilegeMapper;
import com.sinosoft.core.db.mapper.DefRoleMapper;
import com.sinosoft.core.db.mapper.DefRolePrivilegeRelaMapper;
import com.sinosoft.core.db.mapper.DefUserRoleRelaMapper;
import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.db.model.DefPrivilege;
import com.sinosoft.core.db.model.DefPrivilegeExample;
import com.sinosoft.core.db.model.DefRole;
import com.sinosoft.core.db.model.DefRoleExample;
import com.sinosoft.core.db.model.DefRolePrivilegeRela;
import com.sinosoft.core.db.model.DefRolePrivilegeRelaExample;
import com.sinosoft.core.db.model.DefUserRoleRela;
import com.sinosoft.core.db.model.DefUserRoleRelaExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.service.mapper.RoleServiceMapper;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.LoginInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RoleServiceImpl implements RoleService
{

	@Autowired
	private DefRoleMapper defRoleMapper;

	@Autowired
	private RoleServiceMapper roleServiceMapper;
	
	@Autowired
	private DefPrivilegeMapper defPrivilegeMapper;
	
	@Autowired
	private DefRolePrivilegeRelaMapper defRolePrivilegeRelaMapper;
	
	
	@Autowired 
	private DefUserRoleRelaMapper defUserRoleRelaMapper;
	
	@Autowired
	CommonService commonService;
	
	private static final Logger log = Logger.getLogger(RoleServiceImpl.class);

	/**
	 * 角色列表查询
	 */
	@Transactional
	public Map<String, Object> getPageList(DataGridModel dgm, DefRole defRole)
	{
		Map<String, Object> result = new HashMap<String, Object>(2);
		Map paramMap = new HashMap();
		paramMap.put("roleCode", defRole.getRoleCode());
		paramMap.put("roleName", defRole.getRoleName());
		paramMap.put("rolePrivilege", defRole.getRolePrivilege());
		paramMap.put("queryStartIndex", dgm.getStartIndex());
		paramMap.put("queryEndIndex", dgm.getEndIndex());
		int total = roleServiceMapper.queryRoleListInfoCounts(paramMap);
		List<Map> list = roleServiceMapper.queryRoleListInfoPages(paramMap);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	/**
	 * 删除角色
	 */
	@Transactional
	public void deleteRole(Long rid,LoginInfo loginInfo)
	{
		this.deleteR2P(rid,loginInfo);
		this.deleteU2R(rid,loginInfo);
		
		DefRole defRole = this.get(rid);
		BeanUtils.deleteObjectSetOperateInfo(defRole, loginInfo);
		this.defRoleMapper.updateByPrimaryKey(defRole);
	}

	/**
	 * 增加角色
	 */
	@Transactional
	public void save(DefRole defRole,LoginInfo loginInfo)
	{
		checkRole(defRole);
		Long roleSeq = commonService.getSeqValByName("SEQ_T_DEF_ROLE");
		defRole.setRoleId(roleSeq);
		BeanUtils.insertObjectSetOperateInfo(defRole, loginInfo);
		this.defRoleMapper.insert(defRole);
	}

	/**
	 * 更新角色
	 */
	@Transactional
	public void updateRole(DefRole defRole,LoginInfo loginInfo)
	{
		if ("".equals(defRole.getRoleCode()))
		{
			log.info("角色编码为空");
			throw new CisCoreException("角色编码不能为空");
		}
		if ("".equals(defRole.getRoleName()))
		{
			log.info("角色名为空");
			throw new CisCoreException("角色名不能为空");
		}
		
		DefRole defRole_DB = this.defRoleMapper.selectByPrimaryKey(defRole.getRoleId());
		
		defRole.setCreateDate(defRole_DB.getCreateDate());
		defRole.setCreateUserId(defRole_DB.getCreateUserId());
		defRole.setRcState(defRole_DB.getRcState());
		defRole.setOperComId(defRole_DB.getOperComId());
		BeanUtils.updateObjectSetOperateInfo(defRole, loginInfo);
		this.defRoleMapper.updateByPrimaryKey(defRole);

	}

	/**
	 * 为角色分配菜单
	 */
	@Transactional
	public void saveSet(DefRolePrivilegeRela defRolePrivilegeRela,LoginInfo loginInfo)
	{
		
		Long rolePrivilegeSeq = commonService.getSeqValByName("SEQ_T_DEF_ROLE_PRIVILEGE_RELA");
		defRolePrivilegeRela.setRolePrivilegerelaId(rolePrivilegeSeq);
		BeanUtils.insertObjectSetOperateInfo(defRolePrivilegeRela, loginInfo);
		this.defRolePrivilegeRelaMapper.insert(defRolePrivilegeRela);
	}

	/**
	 * 根据id得到角色对象
	 */
	@Transactional
	public DefRole get(Long key)
	{
		return this.defRoleMapper.selectByPrimaryKey(key);
	}

	/**
	 * 查询角色已有菜单并组成菜单树
	 */
	@Transactional
	public List<Nodes> queryMenu(Long rid)
	{
		ArrayList<Nodes> list = (ArrayList<Nodes>) queryMenu(rid, false);
		return list;
	}

	public List<Nodes> queryMenu(List<DefRole> listRole, boolean hasRemoteMenu)
	{
		ArrayList<DefPrivilege> defPrivilegeList = new ArrayList<DefPrivilege>();
		for (DefRole defRole : listRole)
		{
			String[] privilegetypes = new String[] { "localmenu", "remotemenu" };
			ArrayList<DefPrivilege> allprivilegeList = new ArrayList<DefPrivilege>();
			allprivilegeList = getDefPrivilegeListWithIdAndTypes(defRole.getRoleId(), privilegetypes);
			for (DefPrivilege privilege : allprivilegeList)
			{
				// 菜单是访问其他系统的（method为system的菜单），校验是否有访问该系统的权限
				if (null != privilege.getMethod()
						&& !"".equals(privilege.getMethod()))
				{
					if (privilege.getMethod().equals("system"))
					{
						String result = "";
						
						DefPrivilegeExample defPrivilegeExample = new DefPrivilegeExample();
						defPrivilegeExample.createCriteria().andPrivilegeNameEqualTo(privilege.getPrivilegeName()).andRcStateEqualTo("E");
						List<DefPrivilege> privilegeList = this.defPrivilegeMapper.selectByExample(defPrivilegeExample);
						if (privilegeList.size() > 0)
						{
							result = Integer.parseInt(privilegeList.get(0).getPrivilegeId().toString())+ 0 + "";
						}
						if (result != null && !result.equals("") && hasPrivilege(defRole.getRoleId(), new Long(result)))
						{
							defPrivilegeList.add(privilege);
						}
					}
					else if (hasRemoteMenu)
					{
						defPrivilegeList.add(privilege);
					}
				}
				else
				{
					defPrivilegeList.add(privilege);
				}
			}
		}
		sortDefPrivilegeList(defPrivilegeList);
		ArrayList<Nodes> list = new ArrayList<Nodes>();
		for (DefPrivilege defPrivilege : defPrivilegeList)
		{
			Nodes nodes = new Nodes();
			nodes.setId(defPrivilege.getPrivilegeId());
			nodes.setpId(defPrivilege.getPid());
			nodes.setName(defPrivilege.getPrivilegeName());
			nodes.setUrl(defPrivilege.getUrl());
			list.add(nodes);
		}
		return list;
	}

	/**
	 * 查询角色已有菜单并组成菜单树 登录页面不显示远程菜单，只显示本地菜单和系统根菜单
	 */
	@Transactional
	public List<Nodes> queryMenu(Long roleId, boolean hasRemoteMenu)
	{
		DefRole defRole = defRoleMapper.selectByPrimaryKey(roleId);
		ArrayList<DefRole> listRole = new ArrayList<DefRole>();
		listRole.add(defRole);
		List<Nodes> list = queryMenu(listRole, hasRemoteMenu);
		return list;
	}

	/**
	 * 查询角色已有系统，封装成页面显示需要的Nodes
	 * 
	 * @param rid
	 */
	@Transactional
	public List<Nodes> querySystem(Long rid)
	{
		ArrayList<DefPrivilege> privilegeList = getPrivilegeListWithIdAndType(rid,"system");
		sortDefPrivilegeList(privilegeList);
		ArrayList<Nodes> list = new ArrayList<Nodes>();
		for (DefPrivilege privilege : privilegeList)
		{
			Nodes nodes = new Nodes();
			nodes.setId(privilege.getPrivilegeId());
			nodes.setName(privilege.getPrivilegeName());
			list.add(nodes);
		}
		return list;
	}

	/*
	 * 查询所有系统
	 */
	@Transactional
	public List<Nodes> querySystem()
	{
		List<DefPrivilege> privilegeList = getDefPrivilegeListWithType("system");
		sortDefPrivilegeList(privilegeList);
		ArrayList<Nodes> list = new ArrayList<Nodes>();
		for (DefPrivilege defPrivilege : privilegeList)
		{
			Nodes nodes = new Nodes();
			nodes.setId(defPrivilege.getPrivilegeId());
			nodes.setName(defPrivilege.getPrivilegeName());
			list.add(nodes);
		}
		return list;
	}

	/**
	 * 查询角色是否拥有权限
	 */
	@Transactional
	public boolean hasPrivilege(Long roleid, Long privilegeid)
	{
		boolean result = false;
		ArrayList<DefPrivilege> privilegeList = getPrivilegeListWithIdAndType(
				roleid, null);
		for (DefPrivilege privilege : privilegeList)
		{
			if (privilege.getPrivilegeId() == privilegeid)
			{
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 查询角色拥有的权限
	 * 
	 * @param integer
	 *            某个角色id
	 * @param privilegetype
	 *            某个权限类型（localmenu--本地菜单；remotemenu--远程系统菜单；system--系统访问权限）
	 * @return
	 */
	@Transactional
	public ArrayList<DefPrivilege> getPrivilegeListWithIdAndType(Long integer,
			String privilegetype)
	{
		ArrayList<DefPrivilege> privilegeList = new ArrayList<DefPrivilege>();
		String[] privilegetypes = new String[1];
		if (null != privilegetype)
		{
			privilegetypes[0] = privilegetype;
			privilegeList = getDefPrivilegeListWithIdAndTypes(integer,
					privilegetypes);
		}
		else
		{
			privilegeList = getDefPrivilegeListWithIdAndTypes(integer,
					new String[0]);
		}
		return privilegeList;
	}

	/**
	 * 查询角色拥有的权限
	 * 
	 * @param integer
	 *            某个角色id
	 * @param privilegetypes
	 *            某个权限类型（localmenu--本地菜单；remotemenu--远程系统菜单；system--系统访问权限）
	 * @return
	 */
	@Transactional
	public ArrayList<DefPrivilege> getDefPrivilegeListWithIdAndTypes(
			Long roleId, String[] privilegetypes)
	{
		ArrayList<DefPrivilege> privilegeList = new ArrayList<DefPrivilege>();
		DefRole defRole = this.defRoleMapper.selectByPrimaryKey(roleId);

		List<DefPrivilege> defPrivilege = roleServiceMapper.queryPrivilageByRoleID(defRole);

		Iterator<DefPrivilege> iter = defPrivilege.iterator();
		while (iter.hasNext())
		{
			DefPrivilege privilege = iter.next();
			if (null != privilegetypes && privilegetypes.length > 0)
			{
				for (String privilegetype : privilegetypes)
				{
					if (privilege.getPrivilegeType().equals(privilegetype))
					{
						privilegeList.add(privilege);
					}
				}
			}
			else
			{
				privilegeList.add(privilege);
			}
		}
		return privilegeList;
	}

	/**
	 * 查询某类权限
	 * 
	 * @param privilegetype
	 *            某个权限类型（localmenu--本地菜单；remotemenu--远程系统菜单；system--系统访问权限）
	 * @return
	 */
	@Transactional
	public List<DefPrivilege> getDefPrivilegeListWithType(String privilegetype)
	{
		List<DefPrivilege> result = new ArrayList<DefPrivilege>();
		List<DefPrivilege> privilegeList = loadPrivilege();
		for (DefPrivilege privilege : privilegeList)
		{
			if (privilege.getPrivilegeType().equals("system"))
			{
				result.add(privilege);
			}
		}
		return result;
	}
	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<DefPrivilege> loadPrivilege()
	{
		DefPrivilegeExample examp = new DefPrivilegeExample();
		examp.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD);

		List<DefPrivilege> privilegeList = defPrivilegeMapper.selectByExample(examp);
		
		return privilegeList;
	}
	/**
	 * 查询所有权限
	 * @return
	 */
	public List<DefRole> loadRole()
	{
		DefRoleExample examp = new DefRoleExample();
		examp.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD);

		List<DefRole> roleList = defRoleMapper.selectByExample(examp);
		
		return roleList;
	}
	
	/**
	 * 对权限列表按照num大小排序
	 * 
	 * @param privilegeList
	 */
	public void sortDefPrivilegeList(List<DefPrivilege> privilegeList)
	{
		Collections.sort(privilegeList, new Comparator<DefPrivilege>()
		{
			public int compare(DefPrivilege p1, DefPrivilege p2)
			{
				return (p1.getNum() > p2.getNum()) ? 1 : 0;
			}
		});
	}

	/**
	 * 根据角色id删除角色菜单关联关系
	 */
	@Transactional
	public void deleteR2P(Long rid,LoginInfo loginInfo)
	{
		deleteR2P(rid, null,loginInfo);
	}

	/**
	 * 根据角色id删除角色菜单关联关系，根据privilegetype
	 */
	@Transactional
	public void deleteR2P(Long rid, String privilegetype,LoginInfo loginInfo)
	{
		
		DefRolePrivilegeRelaExample examp = new DefRolePrivilegeRelaExample();
		examp.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andRoleIdEqualTo(rid);

		List<DefRolePrivilegeRela> rolePrivilegeRelaList = defRolePrivilegeRelaMapper.selectByExample(examp);
		
		Iterator<DefRolePrivilegeRela> iter =rolePrivilegeRelaList.iterator();
		while (iter.hasNext())
		{
			DefRolePrivilegeRela defRolePrivilegeRela = iter.next();
			
			DefPrivilege defPrivilege = this.defPrivilegeMapper.selectByPrimaryKey(defRolePrivilegeRela.getPrivilegeId());
			if(defPrivilege!=null){
				if (privilegetype == null || privilegetype.equals(defPrivilege.getPrivilegeType()))
				{
					
					BeanUtils.deleteObjectSetOperateInfo(defRolePrivilegeRela, loginInfo);
					
					this.defRolePrivilegeRelaMapper.updateByPrimaryKey(defRolePrivilegeRela);
				}
			}
		}
	}
	
	public void dealDefRole1(DefRole defRole,String operator)
	{
		if("INSERT".equals(operator))
		{
			Long roleSeq = commonService.getSeqValByName("SEQ_T_DEF_ROLE");
			defRole.setRoleId(roleSeq);
			defRole.setRcState("E");
			defRole.setCreateUserId(new Long(123123123));
			defRole.setModifyUserId(new Long(123123123));
			defRole.setCreateDate(DateUtils.getCurrentTimestamp());
			defRole.setModifyDate(DateUtils.getCurrentTimestamp());
			defRole.setOperComId(new Long(123123123));
			
		}else if("UPDATE".equals(operator))
		{	
			
			//得到数据库实体
			DefRole defRole_DB = defRoleMapper.selectByPrimaryKey(defRole.getRoleId());
			defRole.setRcState(defRole_DB.getRcState());
			defRole.setCreateUserId(defRole_DB.getCreateUserId());
			defRole.setCreateDate(defRole_DB.getCreateDate());
			defRole.setOperComId(defRole_DB.getOperComId());

			defRole.setModifyUserId(new Long(123123123));
			defRole.setModifyDate(DateUtils.getCurrentTimestamp());
			defRole.setOperComId(new Long(123123123));
			
		}else if("DELETE".equals(operator))
		{
			defRole.setRcState("D");
			defRole.setModifyUserId(new Long(123123123));
			defRole.setModifyDate(DateUtils.getCurrentTimestamp());
			defRole.setOperComId(new Long(123123123));
		}
		
	}
	
	/**
	 * 根据角色id删除用户角色关联表
	 */
	@Transactional
	public void deleteU2R(Long rid,LoginInfo loginInfo)
	{
		
		DefUserRoleRelaExample examp = new DefUserRoleRelaExample();
		examp.createCriteria().andRcStateEqualTo("E").andRoleIdEqualTo(rid);
		List<DefUserRoleRela> defUserRoleRelaList = defUserRoleRelaMapper.selectByExample(examp);
		
		Iterator<DefUserRoleRela> iter =defUserRoleRelaList.iterator();
		while (iter.hasNext())
		{
			DefUserRoleRela defUserRoleRela = iter.next();
			BeanUtils.deleteObjectSetOperateInfo(defUserRoleRela, loginInfo);
			this.defUserRoleRelaMapper.updateByPrimaryKey(defUserRoleRela);
		}
	}

	/**
	 * 查询角色树
	 */
	@Transactional
	public List<Nodes> queryRole()
	{
		List<DefRole> roleList = loadRole();
		List<Nodes> list = new ArrayList<Nodes>();
		for (DefRole role : roleList)
		{
			Nodes nodes = new Nodes();
			nodes.setId(role.getRoleId());
			nodes.setName(role.getRoleName());
			list.add(nodes);
		}
		return list;
	}

	/**
	 * 角色校验
	 */
	@Transactional
	public void checkRole(DefRole defRole)
	{
		if ("".equals(defRole.getRoleName()))
		{
			log.info("角色名为空");
			throw new CisCoreException("角色名不能为空");
		}
		else
		{
			ArrayList<DefRole> rolelist =this.roleServiceMapper.queryRoleByRoleName(defRole);
			if (rolelist.size() > 0)
			{
				log.info("该角色已经存在");
				throw new CisCoreException("该角色已经存在");
			}
		}
		if ("".equals(defRole.getRoleCode()))
		{
			log.info("角色编码为空");
			throw new CisCoreException("角色编码不能为空");
		}
		else
		{
			ArrayList<DefRole> rolelist = this.roleServiceMapper.queryRoleByRoleCode(defRole);
			if (rolelist.size() > 0)
			{
				log.info("角色编码已经存在");
				throw new CisCoreException("角色编码已经存在");
			}
		}
	}
}
