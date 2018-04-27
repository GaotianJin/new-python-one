package com.fms.service.mapper;

import java.util.*;

import com.fms.db.model.Agent;

public interface AgentServiceMapper {

	@SuppressWarnings("rawtypes")
	public List<Map> getAgentPageList(Agent agent);
	
	public Map findAgentInfoByCustNo(Map map);
}
