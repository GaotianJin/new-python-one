package com.sinosoft.core.application;

/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 *****************************************************/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserRoleRela;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;

public interface UserService
{

	void deleteU2R(Long userId,LoginInfo loginInfo);

	void addUser(DefUser defUser,LoginInfo loginInfo);

	void updateUser(DefUser defUser,LoginInfo loginInfo);

	void deleteUser(Long uid,LoginInfo loginInfo);

	void saveSet(DefUserRoleRela defUserRoleRela,LoginInfo loginInfo);

	Map<String, Object> getPageList(DataGridModel dgm, DefUser defUser);

	List<Nodes> queryMenu(Long uid);

	List<Nodes> queryRole(Long uid);

	DefUser get(Long key);
	
	public String getUserRolePrivilege(DefUser defUser);
	
	void modifySave(String password, String password1, Long id);
	/**
	 * 密码重置至默认
	 * @param id
	 */
	void resetPwd(Long id);

	DefUser findUserByUser(DefUser defUser);

	HashMap<String, String> queryLoginInfo(LoginInfo loginInfo);
	
	Map<String, Object> getUpdateListInfo(Long userId);
	
	/**
	 * 根据登陆用户名查询该用户信息
	 * 用于存session
	 * @param userCode
	 * @return
	 */
	DefUser getDefUserAllByUserCode(String userCode);
}
