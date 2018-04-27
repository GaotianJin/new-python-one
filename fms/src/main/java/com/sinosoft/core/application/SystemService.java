package com.sinosoft.core.application;

import java.util.List;
import java.util.Map;

import com.sinosoft.core.domain.model.user.Privilege;
import com.sinosoft.core.interfaces.util.DataGridModel;

public interface SystemService {

	void saveSystem(Privilege privilege);
	
	void updatePrivilege(Privilege privilege);
	
	void deleteSystem(Integer pid);
	
	List<Privilege> loadPrivilege();
	
	Map<String, Object> getPageList(DataGridModel dgm, Privilege privilege) ;

	Privilege get(Integer key);
}
