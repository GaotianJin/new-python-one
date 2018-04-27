package com.fms.service.manager;

import java.util.Map;

import com.fms.db.model.Manager;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface ManagerService {

	ResultInfo submitManager(Manager manager, LoginInfo loginInfo, String managerId, String operate);

	DataGrid queryManagerList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);

	ResultInfo queryManagerInfo(String managerId, LoginInfo loginInfo);

	ResultInfo deleteManager(String managerId, LoginInfo loginInfo);

}
