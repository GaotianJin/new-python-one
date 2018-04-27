package com.sinosoft.core.application;
/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 *****************************************************/
import java.util.List;
import java.util.Map;

import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.model.DefPrivilege;
import com.sinosoft.core.db.model.DefRole;
import com.sinosoft.core.db.model.DefRolePrivilegeRela;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;

public interface RoleService {
	void updateRole(DefRole defRole,LoginInfo loginInfo);

	void deleteRole(Long rid,LoginInfo loginInfo);

	void deleteR2P(Long rid,LoginInfo loginInfo);

	void deleteR2P(Long rid, String privilegetype,LoginInfo loginInfo);

	void save(DefRole defRole,LoginInfo loginInfo);

	void saveSet(DefRolePrivilegeRela defRolePrivilegeRela,LoginInfo loginInfo);
	
	DefRole get(Long key);
	
	void sortDefPrivilegeList(List<DefPrivilege> privilegeList);

	List<Nodes> queryMenu(Long rid);
	
	List<Nodes> queryMenu(List<DefRole> listRole, boolean hasRemoteMenu);

	Map<String, Object> getPageList(DataGridModel dgm, DefRole defRole);
	
	List<Nodes> queryRole();

	List<Nodes> querySystem(Long rid);

	List<Nodes> querySystem();

	List<Nodes> queryMenu(Long rid, boolean hasRemoteMenu);
}
