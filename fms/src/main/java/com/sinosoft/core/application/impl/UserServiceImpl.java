package com.sinosoft.core.application.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.AgentMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.sinosoft.core.application.RoleService;
import com.sinosoft.core.application.UserService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.mapper.DefComMapper;
import com.sinosoft.core.db.mapper.DefRoleMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.mapper.DefUserRoleRelaMapper;
import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.db.model.DefRole;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.db.model.DefUserRoleRela;
import com.sinosoft.core.db.model.DefUserRoleRelaExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.service.mapper.UserServiceMapper;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.LoginInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceImpl implements UserService
{

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private DefComMapper defComMapper;
	
	@Autowired
	private AgentMapper agentMapper;
	
	@Autowired
	private DefUserMapper defUserMapper;

	@Autowired
	private DefRoleMapper defRoleMapper;

	@Autowired
	private UserServiceMapper userServiceMapper;

	@Autowired
	private DefUserRoleRelaMapper defUserRoleRelaMapper;

//	@Autowired
//	CommonService commonService;

	/**
	 * 根据用户名返回用户对象
	 */
	@Transactional
	public DefUser findUserByUser(DefUser defUser)
	{
		if (defUser.getUserCode() == null || "".equals(defUser.getUserCode()))
		{
			throw new CisCoreException("请输入用户名", "1");
		}
		if (defUser.getPassword() == null || "".equals(defUser.getPassword()))
		{
			throw new CisCoreException("请输入密码", "2");
		}

		DefUser defUser1 = new DefUser();
		ArrayList<DefUser> userlist = userServiceMapper.queryUserByUserCode(defUser);
		if (userlist.size() == 0)
		{
			throw new CisCoreException("该用户不存在", "1");
		} 
		else if("00".equals(userlist.get(0).getState()))
		{
			throw new CisCoreException("该用户为失效状态", "1");
		}
		else
		{
			defUser1 = userlist.get(0);
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			System.out.println(md5.encodePassword(defUser.getPassword(), null));
			if (!defUser1.getPassword().equals(md5.encodePassword(defUser.getPassword(), null)))
			{
				throw new CisCoreException("密码错误", "2");
			}
		}
		return defUser1;
	}

	/**
	 * 增加一个用户
	 */
	@Transactional
	public void addUser(DefUser defUser,LoginInfo loginInfo)
	{
		checkUser(defUser);
		checkUserForAdd(defUser);
		passwordEncoder(defUser);
//		Long userSeq = commonService.getSeqValByName("SEQ_T_DEF_USER");
//		defUser.setUserId(userSeq);
		BeanUtils.insertObjectSetOperateInfo(defUser, loginInfo);
		this.defUserMapper.insert(defUser);
	}

	private void checkUserForAdd(DefUser defUser)
	{
		ArrayList<DefUser> userlist = null;
		userlist = this.userServiceMapper.queryUserByUserCode(defUser);
		if (userlist.size() > 0)
		{
			log.info("用户登陆账号已经存在");
			throw new CisCoreException("该用户登陆账号已经存在");
		}
	}

	/**
	 * md5加密
	 */
	public void passwordEncoder(DefUser defUser)
	{
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		defUser.setPassword(md5.encodePassword(defUser.getPassword(), null));
	}

	/**
	 * 更新用户
	 */
	@Transactional
	public void updateUser(DefUser defUser,LoginInfo loginInfo)
	{
		checkUser(defUser);
		
		DefUserExample defUserExample = new DefUserExample();
		DefUserExample.Criteria criteria = defUserExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteria.andUserIdNotEqualTo(defUser.getUserId());
		criteria.andUserCodeEqualTo(defUser.getUserCode());
		List<DefUser> defUserList = defUserMapper.selectByExample(defUserExample);
		if (defUserList.size() > 0)
		{
			log.info("该用户登陆账号已经存在");
			throw new CisCoreException("该用户登陆账号已经存在，请更换");
		}
		DefCom defCom = this.defComMapper.selectByPrimaryKey(defUser.getComId());
		if(defCom == null)
		{
			log.info("该用户所属机构不存在");
			throw new CisCoreException("该用户所属机构不存在");
		}
		DefUser defUser_db = this.defUserMapper.selectByPrimaryKey(defUser.getUserId());
		defUser.setRcState(defUser_db.getRcState());
		defUser.setCreateUserId(defUser_db.getCreateUserId());
		defUser.setCreateDate(defUser_db.getCreateDate());
		defUser.setOperComId(defUser_db.getOperComId());
		BeanUtils.updateObjectSetOperateInfo(defUser, loginInfo);
		defUser.setPassword(defUser_db.getPassword());
		this.defUserMapper.updateByPrimaryKey(defUser);
		// 通过该用户的user_id查询财富顾问表,以此判断该用户是否为财富顾问：
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria agentcriteria = agentExample.createCriteria();
		agentcriteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		agentcriteria.andUserIdEqualTo(defUser.getUserId());
		List<Agent> agentList = agentMapper.selectByExample(agentExample);
		Agent agentObjRef = null;
		// 如果该用户是财富顾问，则同时修改财富顾问信息表；如果该用户不是财富顾问，则不做修改；
		for (int i = 0; i < agentList.size(); ++i) {
			agentObjRef = agentList.get(i);
			agentObjRef.setComId(defUser.getComId());//更新机构Id
			agentObjRef.setStoreId(defUser.getStoreId());//更新网点Id
			agentObjRef.setDepartmentId(defUser.getDepartmentId());//更新团队Id
			BeanUtils.updateObjectSetOperateInfo(agentObjRef, loginInfo);
			this.agentMapper.updateByPrimaryKey(agentObjRef);
		}
	}

	/**
	 * 根据用户id删除用户
	 */
	@Transactional
	public void deleteUser(Long uid,LoginInfo loginInfo)
	{
		
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria criteria = agentExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteria.andUserIdEqualTo(uid);
		List<Agent> agentList = this.agentMapper.selectByExample(agentExample);
		if(agentList.size()>0)
		{
			log.info("该用户是财富顾问，不能删除，财富顾问代码："+agentList.get(0).getAgentCode());
			throw new CisCoreException("该用户是财富顾问，不能删除，财富顾问代码："+agentList.get(0).getAgentCode());
		}
		this.deleteU2R(uid,loginInfo);
		DefUser defUser = this.defUserMapper.selectByPrimaryKey(uid);
		BeanUtils.deleteObjectSetOperateInfo(defUser, loginInfo);
		this.defUserMapper.updateByPrimaryKey(defUser);
	}

	/**
	 * 查询用户已分配的菜单并组成菜单树
	 */
	@Transactional
	public List<Nodes> queryMenu(Long uid)
	{
		ArrayList<DefRole> listRole = this.listRole(uid);
		List<Nodes> list = new ArrayList<Nodes>();
		for (DefRole defRole : listRole)
		{
			list = roleService.queryMenu(listRole, false);
		}
		for (Nodes node : list)
		{
			if (!"".equals(node.getUrl()) && null != node.getUrl())
			{
				node.setClick("addtab('" + node.getName() + "','"
						+ node.getUrl() + "');");
			}
		}
		// 以为一个用户可能有多个角色，菜单可能重复，此处菜单去重
		List<Nodes> list2 = new ArrayList<Nodes>();
		for (int i = 0; i < list.size(); i++)
		{
			int num = 0;
			for (int j = 0; j < list2.size(); j++)
			{	
				
				if (list.get(i).getId().equals(list2.get(j).getId()))
				{
					num += 1;
				}
			}
			if (num == 0)
			{
				list2.add(list.get(i));
			}
		}
		return list2;
	}

	/**
	 * 角色树
	 */
	@Transactional
	public List<Nodes> queryRole(Long uid)
	{
		ArrayList<DefRole> roleList = this.listRole(uid);
		ArrayList<Nodes> list = new ArrayList<Nodes>();
		for (DefRole defRole : roleList)
		{
			Nodes nodes = new Nodes();
			nodes.setId(defRole.getRoleId());
			nodes.setName(defRole.getRoleName());
			list.add(nodes);
		}
		return list;
	}

	/**
	 * 查询用户已有角色
	 */
	@Transactional
	public ArrayList<DefRole> listRole(Long uid)
	{
		ArrayList<DefRole> roleList = new ArrayList<DefRole>();
		DefUser defUser = get(uid);
		roleList = this.userServiceMapper.queryRolesByUserId(defUser);
		return roleList;
	}

	/**
	 * 根据用户id删除用户角色关联表
	 */
	@Transactional
	public void deleteU2R(Long uid,LoginInfo loginInfo)
	{

		DefUserRoleRelaExample examp = new DefUserRoleRelaExample();
		examp.createCriteria().andUserIdEqualTo(uid).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);

		List<DefUserRoleRela> defUserRoleRela = this.defUserRoleRelaMapper.selectByExample(examp);

		Iterator<DefUserRoleRela> iter = defUserRoleRela.iterator();
		while (iter.hasNext())
		{
			DefUserRoleRela defUserRoleRela1 = iter.next();
			BeanUtils.deleteObjectSetOperateInfo(defUserRoleRela1, loginInfo);
			this.defUserRoleRelaMapper.updateByPrimaryKey(defUserRoleRela1);
		}
	}
	/**
	 * 根据用户id得到用户对象
	 */
	public DefUser get(Long key)
	{
		return this.defUserMapper.selectByPrimaryKey(key);
	}
	
	/**
	 * 根据用户ID 获取用户权限（角色对应的权限）
	 */
	public String getUserRolePrivilege(DefUser defUser)
	{
		return this.userServiceMapper.queryUserRolePrivilegeByUserId(defUser);
	}
	/**
	 * 保存用户分配到的角色
	 */
	@Transactional
	public void saveSet(DefUserRoleRela defUserRoleRela,LoginInfo loginInfo)
	{
		
//		Long seqUserRole = this.commonService.getSeqValByName("seq_t_def_user_role_rela");
//		defUserRoleRela.setUserRolerelaId(seqUserRole);
		BeanUtils.insertObjectSetOperateInfo(defUserRoleRela, loginInfo);
		this.defUserRoleRelaMapper.insert(defUserRoleRela);
	}

	/**
	 * 用户校验
	 */
	@Transactional
	public void checkUser(DefUser defUser)
	{
		if ("".equals(defUser.getUserName()) || defUser.getUserName() == null)
		{
			log.info("用户名为空");
			throw new CisCoreException("用户名不能为空");
		}
		if ("".equals(defUser.getUserCode()) || defUser.getUserCode() == null)
		{
			log.info("用户编码为空");
			throw new CisCoreException("用户编码不能为空");
		}

		if (defUser.getComId() == null)
		{
			log.info("管理机构为空");
			throw new CisCoreException("管理机构不能为空");
		}

		DefCom defCom = defComMapper.selectByPrimaryKey(defUser.getComId());
		log.info("公司编码：" + defCom.getComCode() + ";公司名称：" + defCom.getComName());

		if (defCom == null)
		{
			log.info("管理机构错误");
			throw new CisCoreException("管理机构错误");
		}
	}

	/**
	 * 修改用户密码
	 * */
	@Transactional
	public void modifySave(String password, String password1, Long id)
	{
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();

		DefUser defUser = this.defUserMapper.selectByPrimaryKey(id);
		if (!md5.encodePassword(password, null).equals(defUser.getPassword()))
		{
			log.info("用户原始密码输入错误!");
			throw new CisCoreException("用户原始密码输入错误!");
		}
		defUser.setPassword(md5.encodePassword(password1, null));

		this.defUserMapper.updateByPrimaryKey(defUser);
	}
	/**
	 * 一键重置密码至默认
	 */
	@Override
	public void resetPwd(Long id)
	{
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		DefUser defUser = this.defUserMapper.selectByPrimaryKey(id);
		if(defUser==null)
		{
			log.info("没有此用户!");
			throw new CisCoreException("没有此用户!");
		}
		defUser.setPassword(md5.encodePassword("888888" , null));
		this.defUserMapper.updateByPrimaryKey(defUser);
	}
	/**
	 * 查询登陆信息
	 */
	@Override
	public HashMap<String, String> queryLoginInfo(LoginInfo loginInfo)
	{
		HashMap<String, String> msgMap = new HashMap<String, String>();
		DefCom bdDefCom = defComMapper.selectByPrimaryKey(loginInfo.getComId());
		if (bdDefCom != null)
		{
			msgMap.put("_company",bdDefCom.getComCode() +"-"+ bdDefCom.getComName());
		}
		DefUser defUser = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
		if (defUser != null)
		{
			msgMap.put("_user",defUser.getUserCode() + "-" + defUser.getUserName());
		}
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria criteria = agentExample.createCriteria();
		criteria.andUserIdEqualTo(loginInfo.getUserId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<Agent> agentList = agentMapper.selectByExample(agentExample);
		if (agentList!=null&&agentList.size()>0) {
			msgMap.put("_agentCode", agentList.get(0).getAgentCode());
		}else{
			msgMap.put("_agentCode", "");
		}
		return msgMap;
	}

	/**
	 * 用户信息菜单页面，表格初始化，分页查询
	 */
	@Override
	public Map<String, Object> getPageList(DataGridModel dgm, DefUser defUser)
	{
		Map<String, Object> result = new HashMap<String, Object>(2);
		Map paramMap = new HashMap();
		paramMap.put("userCode", defUser.getUserCode());
		paramMap.put("userName", defUser.getUserName());
		paramMap.put("comId", defUser.getComId());
		paramMap.put("queryStartIndex", dgm.getStartIndex());
		paramMap.put("queryEndIndex", dgm.getEndIndex());
		int total = userServiceMapper.queryUserListInfoCounts(paramMap);
		List<Map> list = userServiceMapper.queryUserListInfoPages(paramMap);

		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	/**
	 * 更新页面信息带入，根据用户ID
	 */
	public Map<String, Object> getUpdateListInfo(Long userId)
	{

		// 查询用户基本信息
		DefUser defUser = defUserMapper.selectByPrimaryKey(userId);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("defUser", defUser);
		return result;
	}

	@Override
	public DefUser getDefUserAllByUserCode(String userCode) {
		return defUserMapper.getDefUserAllByUserCode(userCode);
	}
}
