package com.fms.service.branch;

import java.util.Map;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;

public interface BranchService {
	Map<String, String> savaAdd(String param);
	
	public DataGrid queryBuildList(DataGridModel dgm,Map paramMap);
	
	public void deleteBuild(Long id);
	
	public DataGrid queryDepartmentList(DataGridModel dgm,Map paramMap);
}
