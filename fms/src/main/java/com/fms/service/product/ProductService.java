package com.fms.service.product;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.db.model.PDContract;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PdAmountDisInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface ProductService {
	
	/*************************** 产品设置页面 ******************************/
	/**
	 * 产品设置页面列表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getPageList(DataGridModel dgm, Map paramMap);
	/**
	 * 新增产品基本信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String addPrdocutBasicInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 新增产品核心信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String addPrdocutCoreInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 新增网销产品信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String addPrdocutInternetInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 获取产品修改基本信息
	 * @param param
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo queryProductBasicInfo(String param,LoginInfo loginInfo);
	/**
	 * 获取产品修改互联网信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo queryProductInternetInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 产品提交审核
	 * @param pram
	 * @param loginInfo
	 */
	public void submitAudit(String pram,LoginInfo loginInfo);
	/**
	 * 修改产品信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String updatePrdocutBasicInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 保存修改的网销产品信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo SaveUpdatePrdocutInternetInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 获取产品修改的核心信息
	 * @param param
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo queryProductCoreInfo(String param,LoginInfo loginInfo);
	/**
	 * 保存修改的产品核心信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updatePrdocutCoreInfo(Map tMap,LoginInfo loginInfo);
	
	/******************************* 产品发布页面 **********************************/
	/**
	 * 产品发布初始化信息列表
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getReleasePageList(DataGridModel dgm, Map paramMap);
	/**
	 * 产品修改申请
	 * @param pram
	 * @param loginInfo
	 */
	public void modifyApply(String pram,LoginInfo loginInfo);
	/**
	 * 产品发布
	 * @param pram
	 * @param loginInfo
	 */
	public ResultInfo productRelease(String pram,LoginInfo loginInfo);

	/************************* 净值维护页面 **************************************/

	/**
	 * 净值信息维护初始查询列表
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getNetValuePageList(DataGridModel dgm, Map paramMap);
	/**
	 * 新增净值信息动作
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String addNetValueInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 获取修改净值信息
	 * @param wealthNetValueId
	 * @return
	 */
	public Map<String, Object> getUpdateListInfo(Long wealthNetValueId);
	/**
	 * 删除净值信息
	 * @param uid
	 * @param loginInfo
	 */
	public void deleteNetValueInfo(Long uid,LoginInfo loginInfo);
	/**
	 * 净值附件信息列表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getNetValueFileInfo(DataGridModel dgm, Map paramMap);
	/**
	 * 保存净值修改的内容
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String updatNetValueSave(Map tMap,LoginInfo loginInfo);

	/************************************** 开放日维护 *****************************/
	/**
	 * 新增开放日动作
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String addopenDayInfo(Map tMap,LoginInfo loginInfo);
	/**
	 * 开放日初始查询列表
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getOpenDayPageList(DataGridModel dgm, Map paramMap);

	/**
	 * 删除开放日信息
	 * @param uid
	 * @param loginInfo
	 */
	public void deleteOpenDayInfo(Long uid,LoginInfo loginInfo);

	/**
	 * 获取修改开放日信息
	 * @param wealthNetValueId
	 * @return
	 */
	public Map<String, Object> getUpdateOpenDayListInfo(Long wealthNetValueId);

	/**
	 * 保存净值修改的内容
	 * @param tMap
	 * @param loginInfo
	 */
	@SuppressWarnings("rawtypes")
	public void updateOpenDaySave(Map tMap,LoginInfo loginInfo);
	
	/**
	 * 查询产品信息录入项信息
	 * @param codeType
	 * @return
	 */
	public List<Map<String, String>> queryProductFactor(String codeType);

	
	/**
	 * 产品额度分配时获取所有分公司信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getAllComInfo(Map paramMap,LoginInfo loginInfo);
	
	/**
	 * 产品额度分配保存所有分公司额度分配信息
	 * @param pdAmountDisInfoList
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo saveProductAmountDisInfo(List<PdAmountDisInfo> pdAmountDisInfoList,
			String productInfo,LoginInfo loginInfo,String operate);
	
	/**
	 * 查询产品信息
	 * @param productId
	 * @return
	 */
	public ResultInfo getProductInfo(String productId);
	
	/**
	 * 获取额度分配信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getPdAmountDisInfo(Map paramMap);
   
    /**
     * 获取额度份额列表
     * @param dgm
     * @param paramMap
     * @return
     */
	@SuppressWarnings("rawtypes")
	public DataGrid getAllPdAmountDisInfo(DataGridModel dgm, Map paramMap);
	
	/**
	 * 获取额度分配信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getProductAmountDisAndOrderInfo(Map paramMap);
	
	
    /**
     * 获取所有的产品报告信息
     * @param dgm
     * @param paramMap
     * @return
     */
	@SuppressWarnings("rawtypes")
	public DataGrid getAllProductReportInfo(DataGridModel dgm, Map paramMap);
	
	/**
	 * 从T_PD_Product表中获取下拉数据所有已发布的产品
	 * @return
	 */
	public List<Map<String,String>>  tproductQueryAllRelease();

	/**
	 * 删除产品报告
	 * @param param
	 * @param loginInfo
	 * @return
	 */
	public  ResultInfo deleteProductReport(String param,LoginInfo loginInfo);
	
	/**
	 * 保存产品附文本信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo addProductEditorInfo(Map tMap,LoginInfo loginInfo);
	
	/**
	 * 获取产品附文本信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getProductEditorContext(Map paramMap);
	
	
	/**
	 * 产品发布接口
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo  internetRelease(Map paramMap, LoginInfo loginInfo);
	
	
	/**
	 * 产品净值发布接口
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo  internetNetValue(Map paramMap,LoginInfo loginInfo);
	
	
	/**
	 * 产品开放日接口
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo  internetOpenDay(Map paramMap,LoginInfo loginInfo);
	
	public ResultInfo addOpenDaysInfo(HashMap tMap, LoginInfo loginInfo);
	/**
	 * 产品净值信息查询
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getNetValueList(DataGridModel dgm, Map paramMap);
	/**
	 * 浮动类产品信息查询
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid searchFloatProductNetValue(DataGridModel dgm, Map paramMap);
	/**
	 * 浮动类产品净值信息查询
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid searchNetValue(DataGridModel dgm, Map paramMap);
	/**
	 * 查询产品净值及公布日期
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo getProductNetValue(Map tMap, LoginInfo loginInfo);
	/**
	 * 查出到处报表所用的产品净值信息
	 * @param paramMap
	 * @return
	 */
	public Map getProductNetValues(Map paramMap);
	/**
	 * 查出固收产品投后清单信息
	 * @param paramMap
	 * @return
	 */
	public DataGrid getAlltFixedProductIncomeInfo(DataGridModel dgm, Map paramMap);
	/**
	 * 下载产品投后清单详情信息
	 * @param paramMap
	 * @return
	 */
	public Map fixedProductDetail(Map paramMap);
	/**
	 * 固收产品投后清单导出
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map exportFixedProIncomeInfo(Map paramMap);
	
	/**
	 * 查询固收产品投后详单
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryDetailFixedProIncomeList(DataGridModel dgm, Map paramMap);
	/**
	 * 查询净值待办任务列表
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid searchNetValueTask(DataGridModel dgm, Map paramMap);

	/**
	 * @author wanghao
	 * 每周周二一次产品净值披露频率任务代办
	 * @return
	 * @throws ParseException 
	 */
	public ResultInfo createNetValueWeekBatch() throws ParseException;

	/**
	 * （每月一次）净值披露频率任务代办
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	public void netValueMonthBatch();

	
	/**
	 * 新增净值信息动作(净值披露频率任务代办)
	 * @author wanghao
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String addNetValueTaskInfo(Map tMap, LoginInfo loginInfo);

	
	/**
	 * 净值待办标记为已处理
	 * @author louao
	 * @param loginInfo 
	 */
	public ResultInfo setNetValueTaskState(String pdWealthNetValueTaskId, LoginInfo loginInfo);
	/**
	 * 自动生成开放日净值待办任务
	 * @author ZYM
	 * @throws Exception 
	 */
	public void openDateBatch() throws Exception;
/**
	 * 创建期间分配到账短信
	 * @author ZYM
	 */
	public ResultInfo createIncomeToAcctSms(Map paramMap, LoginInfo loginInfo);
	/**
	 * 创建到期清算到账短信
	 * @author ZYM
	 */
	public ResultInfo createAllIncomeToAcctSms(Map paramMap, LoginInfo loginInfo);
	/**
	 * 查询股权产品列表
	 * @author ZYM
	 */
	public DataGrid queryStockProductList(DataGridModel dgm, Map paramMap);
	/**
	 * 查询固定产品列表
	 * @author ZYM
	 */
	public DataGrid queryFixedProductList(DataGridModel dgm, Map paramMap);

	/**
	 * 查询浮动产品信息显示初始信息列表
	 * @author cjj
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getFloatProductPageList(DataGridModel dgm, Map paramMap);
	
	/**
	 * 查询海外产品信息显示初始信息列表
	 * @author cjj
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getOverseasProductPageList(DataGridModel dgm, Map paramMap);
	
	/**
	 * 产品查询(浮动海外)初始化走势图获取产品所有净值信息
	 */
	public ResultInfo getPdSearchNetValue(Map tMap, LoginInfo loginInfo);
	
	/**
	 * 产品查询菜单查询浮动、海外类产品净值列表
	 * 
	 */
	public DataGrid pdSearchNetValue(Map paramMap, DataGridModel dgm);
	/**
	 * 产品查询(浮动海外)初始化走势图获取产品所有净值信息
	 */
//	public ResultInfo getInvestSearchNetValue(Map tMap, LoginInfo loginInfo);
	
	/**
	 * 产品查询，获取产品详细信息页面
	 * @param paramMap
	 * @return
	 */
	public ResultInfo searchProductDetail(Map paramMap);
	/**
	 * 保存新增合同信息
	 * @author cjj
	 */
	public ResultInfo saveContractInfo(PDContract contractInfo, LoginInfo loginInfo);
	
	/**
	 * 合同管理页面列表查询
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getContractInfoPageList(DataGridModel dgm, Map paramMap);
	
	/**
	 * 产品合同详细信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getDetailContractInfoPageList(DataGridModel dgm, Map paramMap);
	
	/**
	 * 获取产品合同详细信息
	 * @return
	 */
	public ResultInfo getUpdatePDContractInfo(String param, LoginInfo loginInfo);
	/**
	 * 修改产品合同详细信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updatePdContractInfo(Map tMap, LoginInfo loginInfo);
	/**
	 * 删除产品合同
	 * @return
	 */
	public ResultInfo deleteContract(String contractId, LoginInfo loginInfo);
	
	/**
	 * 导出产品合同号详情信息
	 * */
	@SuppressWarnings({ "rawtypes" })
	public Map getContractInfoDetail(Map paramMap);
	/////////////////////////////////股权投后清单//////////////////////////
	/**
	 * 获取股权产品投后清单列表
	 * */
	@SuppressWarnings({ "rawtypes" })
	public DataGrid getAlltStockProductIncomeInfo(DataGridModel dgm, Map paramMap);
	/**
	 * d导出总表-股权产品投后清单列表
	 * */
	@SuppressWarnings({ "rawtypes" })
	public Map exportStockProIncomeInfo(Map paramMap);
	/**
	 * d导出详细表-股权产品投后清单列表
	 * */
	@SuppressWarnings({ "rawtypes" })
	public Map stockProductDetail(Map paramMap);
	/**
	 * 查询详细列表信息
	 * */
	@SuppressWarnings({ "rawtypes" })
	public DataGrid queryDetailStockProIncomeList(DataGridModel dgm, Map paramMap);
	/**
	 * 产品查询中客户投资记录
	 * */
	@SuppressWarnings({ "rawtypes" })
	public DataGrid queryCustInvestList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	/**
	 * 客户认购记录信息导出
	 * */
	@SuppressWarnings({ "rawtypes" })
	public Map exportDetialCustInvestInfo(Map paramMap);
	/**
	 * 产品删除
	 * */
	public ResultInfo productDelete(String productId, LoginInfo loginInfo);
	/**
	 * 创股权产品分配短信
	 * */
	@SuppressWarnings({ "rawtypes" })
	public ResultInfo createStockDisSms(Map paramMap, LoginInfo loginInfo);
	/**
	 * 创股权产品到账短信
	 * */
	@SuppressWarnings({ "rawtypes" })
	public ResultInfo createStockToAccSms(Map paramMap, LoginInfo loginInfo);
	/**
	 * 删除预约额度分配
	 * @throws ParseException 
	 * */
	@SuppressWarnings({ "rawtypes" })
	public ResultInfo deleteProductAmountDisInfo(Map paramMap, LoginInfo loginInfo) throws ParseException;
	/**
	 * 浮动产品查询中客户投资记录
	 * */
	@SuppressWarnings({ "rawtypes" })
	public DataGrid queryCustFloatInvestList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	
	
}
