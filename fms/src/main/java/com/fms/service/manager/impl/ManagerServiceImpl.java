package com.fms.service.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fms.db.mapper.ManagerMapper;
import com.fms.db.model.Manager;
import com.fms.service.manager.ManagerService;
import com.fms.service.mapper.ManagerServiceMapper;
import com.fms.service.sales.impl.SalesServiceImpl;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerServiceMapper managerServiceMapper;
	@Autowired
	private ManagerMapper managerMapper;
	
	private static final Logger log = Logger.getLogger(ManagerServiceImpl.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryManagerList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		
		Integer total = managerServiceMapper.queryManagerListCount(paramMap);
		List<Map> resultList = managerServiceMapper.queryManagerList(paramMap);
		//datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo queryManagerInfo(String managerId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Manager manager = managerMapper.selectByPrimaryKey(Long.parseLong(managerId));
		Map resultMap = new HashMap();
		resultMap.put("chnName", manager.getChnName());
		resultMap.put("mobile", manager.getMobile());
		resultInfo.setSuccess(true);
		resultInfo.setObj(resultMap);
		resultInfo.setMsg("查询成功！");
		return resultInfo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResultInfo submitManager(Manager manager,LoginInfo loginInfo,String managerId,String operate) {
		ResultInfo resultInfo = new ResultInfo();
		if ("addManager".equals(operate)) {
			//新增保存
			BeanUtils.insertObjectSetOperateInfo(manager, loginInfo);
			managerMapper.insert(manager);
		}else {
			//更新记录
			Manager existManager = managerMapper.selectByPrimaryKey(Long.parseLong(managerId));
			BeanUtils.updateObjectSetOperateInfo(existManager, loginInfo);
			String chnName = manager.getChnName();
			String mobile = manager.getMobile();
			existManager.setChnName(chnName);
			existManager.setMobile(mobile);
			managerMapper.updateByPrimaryKey(existManager);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存成功！");
		
		return resultInfo;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ResultInfo deleteManager(String managerId,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//获取选中人员信息
		Manager existManager = managerMapper.selectByPrimaryKey(Long.parseLong(managerId));
		existManager.setRcState(Constants.DELETE_RECORD);
		BeanUtils.updateObjectSetOperateInfo(existManager, loginInfo);
		managerMapper.updateByPrimaryKey(existManager);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("保存成功！");
		return resultInfo;
	}
}
