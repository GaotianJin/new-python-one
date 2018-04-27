package com.fms.service.branch.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.model.DefBuilding;
import com.fms.db.model.DefDepartmentExample;
import com.fms.db.mapper.DefBuildingMapper;
import com.fms.db.model.DefBuildingExample;
import com.fms.service.branch.BranchService;
import com.fms.service.mapper.BranchServiceMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	private DefBuildingMapper defBuildingMapper;
	@Autowired
	private BranchServiceMapper branchServiceMapper;
	@Autowired
	private CommonService commonService;
	
	@Transactional
	public Map<String, String> savaAdd(String param){
		Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		System.out.println("------------------>param="+param);
		String comInfo = JsonUtils.getJsonValueByKey("comInfo", param);
		System.out.println("------------------>comInfo="+comInfo);
		DefBuilding addBuilding = gson.fromJson(comInfo, DefBuilding.class);
		Long id = commonService.getSeqValByName("SEQ_T_DEF_BUILDING");
		Long a=new Long(22);
//		addBuilding.setBuildingId(id);
		addBuilding.setRcState("E");
		addBuilding.setCreateDate(addBuilding.getDealDate());
		addBuilding.setModifyDate(addBuilding.getDealDate());
		addBuilding.setOperComId(a);
		addBuilding.setCreateUserId(a);
		addBuilding.setModifyUserId(a);
		defBuildingMapper.insert(addBuilding);
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{
			map.put("mes", "操作成功");
		}
		catch (CisCoreException e)
		{
			e.printStackTrace();
			map.put("mes", "操作失败,原因是" + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 查询并列出楼盘信息*/
	@Override
	public DataGrid queryBuildList(DataGridModel dgm,Map paramMap){
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		DefBuildingExample defbuild=new DefBuildingExample();
		Integer total = branchServiceMapper.branchBuildListCount(paramMap);
		List<Map> resultList = branchServiceMapper.branchBuildListPage(paramMap);

		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 查询并列出业务部信息*/
	@Override
	public DataGrid queryDepartmentList(DataGridModel dgm,Map paramMap){
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		DefDepartmentExample defdepartment=new DefDepartmentExample();
		Integer total = branchServiceMapper.branchDepartmentListCount(paramMap);
		List<Map> resultList = branchServiceMapper.branchDepartmentListPage(paramMap);

		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	
	/**
	 * 删除楼盘
	 */
	@Override
	@Transactional
	public void deleteBuild(Long id){
		
		DefBuilding def=defBuildingMapper.selectByPrimaryKey(id);
		def.setRcState("D");
		def.setModifyDate(DateUtils.getCurrentTimestamp());
		def.setModifyUserId(id);
		defBuildingMapper.updateByPrimaryKey(def);
	}

}
