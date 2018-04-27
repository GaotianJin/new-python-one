package com.sinosoft.core.domain.model.quartz.ibatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.sinosoft.core.domain.model.quartz.Scheduleinfo;

@Repository
public class ScheduleInfoSQL extends SqlSessionDaoSupport {

	public Scheduleinfo findByID(int id) {
		return (Scheduleinfo) this.getSqlSession().selectOne("com.sinosoft.core.domain.model.quartz.ScheduleInfoMapper.findByID", id);
	}

	public Map findMapByID(int id) {
		return (Map) this.getSqlSession().selectOne("com.sinosoft.core.domain.model.quartz.ScheduleInfoMapper.findMapByID", id);
	}
}  