package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface BranchServiceMapper {
	
	public Integer branchBuildListCount(Map queryParam);
	
	public Integer branchDepartmentListCount(Map queryParam);
	
	public List<Map> branchBuildListPage(Map queryParam);
	
	public List<Map> branchDepartmentListPage(Map queryParam);
}
