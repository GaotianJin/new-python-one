package com.sinosoft.core.application.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sinosoft.core.application.SystemService;
import com.sinosoft.core.application.util.HibernateUtil;
import com.sinosoft.core.domain.model.user.Privilege;
import com.sinosoft.core.domain.model.user.Role2privilege;
import com.sinosoft.core.domain.model.user.dao.PrivilegeDAO;
import com.sinosoft.core.domain.model.user.dao.Role2privilegeDAO;
import com.sinosoft.core.interfaces.util.DataGridModel;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemServiceImpl implements SystemService {

	@Autowired
	PrivilegeDAO privilegeDAO;
	@Autowired
	Role2privilegeDAO role2privilegeDAO;
	private static final Logger log = Logger.getLogger(SystemServiceImpl.class);

	public List<Privilege> loadPrivilege() {
		return (List<Privilege>) privilegeDAO.loadAll();
	}

	/**
	 * 增加应用
	 */
	@Transactional
	public void saveSystem(Privilege privilege) {
		privilege.setPrivilegetype("system");
		int newNum = calNum(privilege);
		privilege.setNum(newNum);
		privilegeDAO.save(privilege);
	}

	/**
	 * 删除应用
	 */
	@Transactional
	public void deleteSystem(Integer pid) {
		deleteR2P(pid);
		Privilege privilege = new Privilege();
		privilege = privilegeDAO.load(pid);
		privilegeDAO.delete(privilege);

	}

	/**
	 * 更新应用
	 */
	@Transactional
	public void updatePrivilege(Privilege privilege) {
		Privilege oldprivilege = privilegeDAO.load(privilege.getId());
		oldprivilege.setPrivilegename(privilege.getPrivilegename());
		oldprivilege.setPrivilegecode(privilege.getPrivilegecode());
		privilegeDAO.update(oldprivilege);
	}

	/**
	 * 计算应用加载的顺序
	 */
	@Transactional
	private Integer calNum(Privilege privilege) {
		String result = "";
		String code = "select p.Num from Privilege p where privilegetype='system' order by int(num) desc";
		Query menucode = privilegeDAO.getSessionFactory().getCurrentSession().createQuery(code);
		if (menucode.list().size() > 0) {
			result = Integer.parseInt(menucode.list().get(0).toString()) + 1 + "";
		}
		return Integer.valueOf(result);
	}



	/**
	 * 查询应用列表
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Privilege privilege) {
		String countQuery = "select count(*) from Privilege privilege where 1=1 and privilege.Privilegetype like '%system%'";
		String fullQuery = "select new map(privilege as privilege,privilege.id as pid) from Privilege privilege where 1=1 and privilege.Privilegetype like '%system%'";

		// 排序
		String orderString = "";
		if (StringUtils.hasLength(dgm.getSort())) {
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
			if (dgm.getSort().equals("privilegecode")) {
				orderString = " order by int(" + dgm.getSort() + ") " + dgm.getOrder();// 排序时字符串要转换成int
			}
		}
		// 条件
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (privilege != null) {
			if (StringUtils.hasLength(privilege.getPrivilegename())) {
				sb.append(" and privilege.Privilegename like :Privilegename");
				params.put("Privilegename", "%" + privilege.getPrivilegename() + "%");
			}
			if (StringUtils.hasLength(privilege.getPrivilegecode())) {
				sb.append(" and privilege.Privilegecode like :Privilegecode");
				params.put("Privilegecode", "%" + privilege.getPrivilegecode() + "%");
			}
		}

		org.hibernate.classic.Session session = privilegeDAO.getSessionFactory().getCurrentSession();

		return HibernateUtil.getPageList(session, dgm, countQuery, fullQuery, orderString, sb, params);
	}

	/**
	 * 根据id得到应用对象
	 */
	@Transactional
	public Privilege get(Integer key) {
		return privilegeDAO.get(key);
	}

	/**
	 * 删除角色应用对应关系
	 */
	@Transactional
	public void deleteR2P(Integer rid) {
		Privilege privilege = this.get(rid);
		Iterator<Role2privilege> iter = privilege.getRole2privileges().iterator();
		while (iter.hasNext()) {
			Role2privilege role2privilege = (Role2privilege) iter.next();
			role2privilegeDAO.delete(role2privilege);
		}
	}
}
