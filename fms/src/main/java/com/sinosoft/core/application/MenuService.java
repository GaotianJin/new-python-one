package com.sinosoft.core.application;
/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 *****************************************************/
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.core.application.util.Nodes;
import com.sinosoft.core.db.model.DefPrivilege;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;

public interface MenuService {

	void saveMenu(DefPrivilege defPrivilege,LoginInfo loginInfo);
	
	void saveMenu(List<LinkedHashMap<String, Object>> list,LoginInfo loginInfo);

	void updatePrivilege(DefPrivilege defPrivilege,LoginInfo loginInfo);
	
	void deleteMenu(Long id,LoginInfo loginInfo);
	
	List<DefPrivilege> loadPrivilege();
	
	List<Nodes> queryMenu() ;
	
	Map<String, Object> getPageList(DataGridModel dgm, DefPrivilege defPrivilege) ;

	DefPrivilege get(Long key);
}
