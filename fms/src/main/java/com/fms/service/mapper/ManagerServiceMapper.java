package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface ManagerServiceMapper {
	@SuppressWarnings("rawtypes")
	Integer queryManagerListCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	List<Map> queryManagerList(Map paramMap);
	@SuppressWarnings("rawtypes")
	List<Map> getAllManagerInfo(Map paramMap);

}
