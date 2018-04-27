package com.fms.service.sales;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fms.db.model.Agent;
import com.fms.db.model.AgentAccInfo;
import com.fms.db.model.AgentCertificationInfo;
import com.fms.db.model.AgentFamilyInfo;
import com.fms.db.model.AgentNurserInfo;
import com.fms.db.model.AgentOtherInfo;
import com.fms.db.model.AgentPositionTrace;
import com.fms.db.model.AgentWorkInfo;
import com.sinosoft.core.domain.model.user.Privilege;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface SalesService {
	@SuppressWarnings("rawtypes")
	DataGrid queryAgentBelongList(DataGridModel dgm,Map paramMap ,LoginInfo loginInfo); 
	Map<String, Object> getAgentPageList(DataGridModel dgm, Agent agent);
	
	boolean saveAgent(String param) throws ParseException;
	
	Map<String, Object> getPageList(DataGridModel dgm, Privilege privilege) ;

	@SuppressWarnings("rawtypes")
	public DataGrid queryAgentList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);
	
	public ResultInfo submitAgent(Agent agent,List<AgentFamilyInfo> agentFamilyInfoList,
			List<AgentCertificationInfo> agentCertificationInfoList,
			List<AgentPositionTrace> agentPositionList,
			List<AgentNurserInfo> agentNurserInfoList,List<AgentWorkInfo> agentWorkInfoList,
			List<Map<String, String>> agentDepartmentList,
			List<AgentOtherInfo> agentOtherInfoList, List<AgentAccInfo> agentAccInfoList, LoginInfo loginInfo,String userCode, String operate);
	
	public ResultInfo deleteAgent(Agent agent,LoginInfo loginInfo);
	
	public ResultInfo queryAgentInfo(Agent agent,LoginInfo loginInfo);
	
	public ResultInfo verifyUserCode(String userId,String userCode,String operate);
	
	public ResultInfo verifyAgentCode(String agentId,String agentCode,String operate);
	
	public ResultInfo salaryCal(String calYear,String calMonth,LoginInfo loginInfo);
	
	public ResultInfo salaryForward(String calYear,String calMonth,LoginInfo loginInfo);
	
	@SuppressWarnings("rawtypes")
	public DataGrid getSalaryCalList(DataGridModel dataGrid,Map paramMap) ;
	
	@SuppressWarnings("rawtypes")
	public DataGrid getAgentWageList(DataGridModel dataGrid,Map paramMap) ;
	
	@SuppressWarnings("rawtypes")
	public ResultInfo salaryCalExport(Map paramMap);
	
	
	public ResultInfo assessCal(String assessCalYear,String assessCalMonth,String assessUnit,LoginInfo loginInfo);
	
	public ResultInfo assessForward(String assessYear, String assessMonth,String assessUnit,LoginInfo loginInfo);
	
	@SuppressWarnings("rawtypes")
	public ResultInfo assessCalExport(Map paramMap);
	
	
	@SuppressWarnings("rawtypes")
	public DataGrid getAssessCalList(DataGridModel dataGrid,Map paramMap) ;
	
	@SuppressWarnings("rawtypes")
	public DataGrid getAgentAssessList(DataGridModel dataGrid,Map paramMap) ;

	public ResultInfo uploadAgentImage(MultipartFile[] uploadImageList, String param, LoginInfo loginInfo);
	
	/**
	 * 工资文件导入
	 * @param salaryFileName
	 * @param salaryFileType
	 * @param loginInfo
	 * @return
	 */
	ResultInfo importSalaryFile(MultipartFile[] importFileNameList, String salaryFileType, String salaryFileDate, LoginInfo loginInfo) throws Exception;
	
	/**
	 * 工资主表查询
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid querySalaryList(DataGridModel dgm, Map paramMap);
	
	/**
	 * 基本工资表查询
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryBaseSalaryList(DataGridModel dgm, Map paramMap);
	
	/**
	 * 国金交易佣金表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryGuojinCommissionIdList(DataGridModel dgm, Map paramMap);
	/**
	 * 海外投资表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryOverseasCommissionIdList(DataGridModel dgm, Map paramMap);
	/**
	 * 项目端佣金表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryProjectCommissionIdList(DataGridModel dgm, Map paramMap);
	/**
	 * 补发利益表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryReissueIdList(DataGridModel dgm, Map paramMap);
	/**
	 * 销售佣金表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid querySaleCommissionIdList(DataGridModel dgm, Map paramMap);
	/**
	 * 暂扣利益表查询
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryWithholdIdList(DataGridModel dgm, Map paramMap);
	/***************************综合查询***************************/
	@SuppressWarnings("rawtypes")
	public DataGrid queryBaseSalaryDetail(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public DataGrid querySaleCommissionDetail(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public DataGrid queryGuojinCommissionDetail(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public DataGrid queryOverseasCommissionDetail(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public DataGrid queryReissueDetail(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public DataGrid queryWithholdDetail(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public DataGrid queryProjectCommissionDetail(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	//查询综合工资
	@SuppressWarnings("rawtypes")
	public DataGrid queryGeneralSalary(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	//查询综合基本工资
	@SuppressWarnings("rawtypes")
	DataGrid queryGeneralBaseSalaryList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	//导出员工信息
	@SuppressWarnings("rawtypes")
	Map exportAgentInfo(Map paramMap)  throws Exception;
	//查询佣金明细信息
	@SuppressWarnings("rawtypes")
	public DataGrid querydetailsCommissionIdList(DataGridModel dgm, Map paramMap);
	//查询佣金总额详情
	@SuppressWarnings("rawtypes")
	DataGrid querydetailsCommission(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	
}
