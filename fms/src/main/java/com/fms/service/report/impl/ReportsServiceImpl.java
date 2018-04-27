package com.fms.service.report.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.controller.product.ProductController;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.PDProduct;
import com.fms.service.mapper.ReportsServiceMapper;
import com.fms.service.report.ReportsService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportsServiceImpl implements ReportsService{
	
	private static final Logger logger = Logger.getLogger(ProductController.class);
	@Autowired
	private ReportsServiceMapper reportsServiceMapper;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AgentMapper agentMapper;
	
	/**
	 * 查询业务部报表信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryBusinessManageList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());

		Integer total = reportsServiceMapper.queryBusinessManageListCount(paramMap);
		List<Map<String, String>> resultList = reportsServiceMapper.queryBusinessManageList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 产品设置页面初始化查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid queryProductList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = reportsServiceMapper.productQueryListCount(paramMap);
		List<Map> resultList = reportsServiceMapper.productQueryList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 获取产品基本信息
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Transactional
	public ResultInfo queryProductBasicInfo(Map param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map productInfoMap = new HashMap();
		try {
			// 获取需要修改的产品主表信息T_PD_PRODUCT
			List<Map> resultMap = reportsServiceMapper.queryProductBasicInfo(param);
			productInfoMap.put("modifyBasicProduct",resultMap.get(0));
			productInfoMap.put("modifyStatus",resultMap.get(0).get("status"));
			resultInfo.setObj(productInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取产品基本信息成功！");
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品基本信息失败！");
		}
		return resultInfo;
	}
	
	/**
	 * 获取产品核心信息
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Transactional
	public ResultInfo getProductCoreInfo(Map param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map productInfoMap = new HashMap();
		try {
			// 获取需要修改的产品主表信息T_PD_PRODUCT
			List<Map> resultMap = reportsServiceMapper.getProductCoreInfo(param);
			productInfoMap.put("modifyPdWealth",resultMap.get(0));
			resultInfo.setObj(productInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取产品核心信息成功！");
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品核心信息失败！");
		}
		return resultInfo;
	}
	
	/**
	 * 查询业务部报表信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryRedemptionDetailList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());

		Integer total = reportsServiceMapper.queryRedemptionDetailListCount(paramMap);
		List<Map<String, String>> resultList = reportsServiceMapper.queryRedemptionDetailList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 查询募集期产品信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryRaisingProductsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());

		Integer total = reportsServiceMapper.queryRaisingProductsListCount(paramMap);
		List<Map<String, String>> resultList = reportsServiceMapper.queryRaisingProductsList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	
	/**
	 * 查询销售清单报表信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid querySaleProductsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());

		Integer total = reportsServiceMapper.querySaleProductsListCount(paramMap);
		List<Map<String, String>> resultList = reportsServiceMapper.querySaleProductsList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 查询业管部所有月份
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map<String, String>> monthQuery(LoginInfo loginInfo) {
		Map paramMap = new HashMap();
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
		List<Map<String, String>> comMapList = reportsServiceMapper.getMonthes(paramMap);
		return comMapList;
	}
	/**
	 * 查询业管部所有年度
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map<String, String>> yearQuery(LoginInfo loginInfo) {
		Map paramMap = new HashMap();
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
		List<Map<String, String>> comMapList = reportsServiceMapper.getYears(paramMap);
		return comMapList;
	}
	
	/**
	 * 导出业务部报表信息
	 */
	//数据库中建列和表名 比较繁琐不灵活
	/*@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public ResultInfo businessManagementDetail(Map paramMap,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		List<Map<String, Object>> reportList = new ArrayList<Map<String,Object>>();
		try {
			paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
			paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
			paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
			AgentExample agentExample=new AgentExample();
			agentExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andUserIdEqualTo(loginInfo.getUserId());
			List<Agent> agentList=agentMapper.selectByExample(agentExample);
			if (agentList!=null&&agentList.size()>0) {
				paramMap.put("loginAgentId", agentList.get(0).getAgentId());
			}else{
				paramMap.put("loginAgentId", "");
			}
			List<Map<String,String>> businessManagementDetailList = reportsServiceMapper.queryBusinessManageDetailList(paramMap);
			List<Map> returnResultList=new ArrayList<Map>();
			if (businessManagementDetailList!=null&&businessManagementDetailList.size()>0) {
				for (Map map : businessManagementDetailList) {
					returnResultList.add(map);
				}
				
			}
			if (returnResultList!=null) {
				//生成Excel
				Map<String, Object> reportMap = commonService.getReportInfo("BusinessManageDetail");
				//对sheet名称加上当前月份
				String reportName = reportMap.get("reportName").toString();
				reportMap.put("reportName", reportName);
				if (reportMap!=null&&reportMap.size()>0&&returnResultList!=null&&returnResultList.size()>0) {
					reportMap.put("reportData", returnResultList);
					reportList.add(reportMap);
				}
				resultInfo.setObj(reportList);
				resultInfo.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出业管部报表出现异常！");
		}
		return resultInfo;
	}*/
	
	/**
	 * 		
	 * 		导出业管部报表
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map businessManagementDetail(Map paramMap)  throws Exception {
		// 输出日志，便于调试
		logger.info("Service层方法getBusinessStatisticsExcel()接受参数个数:" + paramMap.size());
		// Mapper接口方法：统计业务报表相关信息
		List<Map<String,String>> businessStatisticsList = this.reportsServiceMapper.queryBusinessManageDetailList(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", businessStatisticsList);
	    return datas;
	}	
	
	
	/**
	 * 		
	 * 		导出赎回详情信息报表
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map redemptionDetail(Map paramMap)  throws Exception {
		// 输出日志，便于调试
		logger.info("Service层方法getBusinessStatisticsExcel()接受参数个数:" + paramMap.size());
		// Mapper接口方法：统计业务报表相关信息
		List<Map<String,String>> businessStatisticsList = this.reportsServiceMapper.queryRedemptionDetail(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", businessStatisticsList);
	    return datas;
	}	
	
	/**
	 * 		
	 * 		导出募集期产品业务报表
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map raisingProductsDetail(Map paramMap)  throws Exception {
		// 输出日志，便于调试
		logger.info("Service层方法getBusinessStatisticsExcel()接受参数个数:" + paramMap.size());
		// Mapper接口方法：统计业务报表相关信息
		List<Map<String,String>> businessStatisticsList = this.reportsServiceMapper.queryRaisingProductsDetailList(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", businessStatisticsList);
	    return datas;
	}	

/**
 * 		
 * 		导出销售清单的业务报表
 * */
@SuppressWarnings({ "rawtypes" })
@Override
public Map saleProductsDetail(Map paramMap)  throws Exception {
	// 输出日志，便于调试
	logger.info("Service层方法getBusinessStatisticsExcel()接受参数个数:" + paramMap.size());
	// Mapper接口方法：统计业务报表相关信息
	//List<Map> businessStatisticsList = this.productOrderServiceMapper.getBusinessStatisticsInfo(paraMap);
	List<Map<String,String>> businessStatisticsList = this.reportsServiceMapper.querySaleProductsDetailList(paramMap);
	/*Map businessStatisticsSumMap = this.productOrderServiceMapper.getBusinessReportTotalSumInfo(paraMap);
	if (businessStatisticsSumMap != null ) {
		businessStatisticsSumMap.put("productName", "合计");
	} else {
		businessStatisticsSumMap = new HashMap();
		logger.info("Service层方法getBusinessStatisticsExcel()中根据时间查询返回空对象引用");
	}
	
	*/
	// 将行求和的结果作为一条记录放入统计业务报表
	/*businessStatisticsList.add(businessStatisticsSumMap);*/
	/*Mapper接口方法：删除视图
	this.productOrderServiceMapper.deleteView();*/
	// 将统计期间与统计结果列表放入Map集合
	/*Map<String, Object> datas = new HashMap<String, Object>();
	String dateBetween = "";
	if (paraMap.size() > 0) {
		dateBetween =  (String)paraMap.get("businessStatisticStartTime") + "至" + (String)paraMap.get("businessStatisticEndTime");
	}
    datas.put("dateBetween", dateBetween);*/
	Map<String, Object> datas = new HashMap<String, Object>();
    datas.put("data", businessStatisticsList);
    return datas;
}
}