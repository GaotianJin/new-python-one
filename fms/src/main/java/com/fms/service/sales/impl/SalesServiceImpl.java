package com.fms.service.sales.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fms.db.mapper.AgencyComMapper;
import com.fms.db.mapper.AgentAccInfoMapper;
import com.fms.db.mapper.AgentAssessInfoMapper;
import com.fms.db.mapper.AgentAssessMapper;
import com.fms.db.mapper.AgentCertificationInfoMapper;
import com.fms.db.mapper.AgentDepartmentMapper;
import com.fms.db.mapper.AgentFamilyInfoMapper;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.AgentNurserInfoMapper;
import com.fms.db.mapper.AgentOtherInfoMapper;
import com.fms.db.mapper.AgentPositionTraceMapper;
import com.fms.db.mapper.AgentStoreMapper;
import com.fms.db.mapper.AgentWageInfoMapper;
import com.fms.db.mapper.AgentWageMapper;
import com.fms.db.mapper.AgentWorkInfoMapper;
import com.fms.db.mapper.DefDepartmentMapper;
import com.fms.db.mapper.DefStoreMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.SlyBaseSalaryMapper;
import com.fms.db.mapper.SlyCommissionMapper;
import com.fms.db.mapper.SlyGuojinCommissionMapper;
import com.fms.db.mapper.SlyOverseasCommissionMapper;
import com.fms.db.mapper.SlyProjectCommissionMapper;
import com.fms.db.mapper.SlyReissueMapper;
import com.fms.db.mapper.SlySalaryMapper;
import com.fms.db.mapper.SlySaleCommissionMapper;
import com.fms.db.mapper.SlyWithholdMapper;
import com.fms.db.mapper.TradeCustInfoMapper;
import com.fms.db.mapper.TradeInfoMapper;
import com.fms.db.mapper.TradeProductInfoMapper;
import com.fms.db.mapper.TradeStatusInfoMapper;
import com.fms.db.model.AgencyCom;
import com.fms.db.model.AgencyComExample;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentAccInfo;
import com.fms.db.model.AgentAccInfoExample;
import com.fms.db.model.AgentAssess;
import com.fms.db.model.AgentAssessExample;
import com.fms.db.model.AgentAssessInfo;
import com.fms.db.model.AgentAssessInfoExample;
import com.fms.db.model.AgentCertificationInfo;
import com.fms.db.model.AgentCertificationInfoExample;
import com.fms.db.model.AgentDepartment;
import com.fms.db.model.AgentDepartmentExample;
import com.fms.db.model.AgentExample;
import com.fms.db.model.AgentFamilyInfo;
import com.fms.db.model.AgentFamilyInfoExample;
import com.fms.db.model.AgentNurserInfo;
import com.fms.db.model.AgentNurserInfoExample;
import com.fms.db.model.AgentOtherInfo;
import com.fms.db.model.AgentOtherInfoExample;
import com.fms.db.model.AgentPositionTrace;
import com.fms.db.model.AgentPositionTraceExample;
import com.fms.db.model.AgentStore;
import com.fms.db.model.AgentStoreExample;
import com.fms.db.model.AgentWage;
import com.fms.db.model.AgentWageExample;
import com.fms.db.model.AgentWageInfo;
import com.fms.db.model.AgentWageInfoExample;
import com.fms.db.model.AgentWorkInfo;
import com.fms.db.model.AgentWorkInfoExample;
import com.fms.db.model.CompanyRosterInfo;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.SlyBaseSalary;
import com.fms.db.model.SlyBaseSalaryExample;
import com.fms.db.model.SlyCommission;
import com.fms.db.model.SlyCommissionExample;
import com.fms.db.model.SlyGuojinCommission;
import com.fms.db.model.SlyGuojinCommissionExample;
import com.fms.db.model.SlyOverseasCommission;
import com.fms.db.model.SlyOverseasCommissionExample;
import com.fms.db.model.SlyProjectCommission;
import com.fms.db.model.SlyProjectCommissionExample;
import com.fms.db.model.SlyReissue;
import com.fms.db.model.SlyReissueExample;
import com.fms.db.model.SlySalary;
import com.fms.db.model.SlySalaryExample;
import com.fms.db.model.SlySaleCommission;
import com.fms.db.model.SlySaleCommissionExample;
import com.fms.db.model.SlyWithhold;
import com.fms.db.model.SlyWithholdExample;
import com.fms.db.model.TradeCustInfo;
import com.fms.db.model.TradeCustInfoExample;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeInfoExample;
import com.fms.db.model.TradeProductInfo;
import com.fms.db.model.TradeProductInfoExample;
import com.fms.db.model.TradeStatusInfo;
import com.fms.db.model.TradeStatusInfoExample;
import com.fms.service.mapper.AgentServiceMapper;
import com.fms.service.mapper.CalSalaryServiceMapper;
import com.fms.service.mapper.SalesServiceMapper;
import com.fms.service.sales.CalSalaryService;
import com.fms.service.sales.SalesService;
import com.sinosoft.core.application.UserService;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefRoleMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeKey;
import com.sinosoft.core.db.model.DefRole;
import com.sinosoft.core.db.model.DefRoleExample;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.db.model.DefUserRoleRela;
import com.sinosoft.core.domain.model.user.Privilege;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.service.mapper.UserServiceMapper;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SalesServiceImpl implements SalesService {
	private static final Logger log = Logger.getLogger(SalesServiceImpl.class);
	@Autowired
	private AgentServiceMapper agentServiceMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private AgentFamilyInfoMapper agentFamilyInfoMapper;
	@Autowired
	private AgentCertificationInfoMapper agentCertificationInfoMapper;
	@Autowired
	private AgentPositionTraceMapper agentPositionTraceMapper;
	@Autowired
	private AgentStoreMapper agentStoreMapper;
	@Autowired
	private AgentDepartmentMapper agentDepartmentMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserServiceMapper userServiceMapper;
	@Autowired
	private SalesServiceMapper salesServiceMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private DefStoreMapper defStoreMapper;
	@Autowired
	private DefDepartmentMapper defDepartmentMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private DefRoleMapper defRoleMapper;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private AgentWageMapper agentWageMapper;
	@Autowired
	private TradeStatusInfoMapper tradeStatusInfoMapper;
	@Autowired
	private TradeProductInfoMapper tradeProductInfoMapper;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private AgencyComMapper agencyComMapper;
	@Autowired
	private TradeCustInfoMapper tradeCustInfoMapper;
	@Autowired
	private CalSalaryService calSalaryService;
	@Autowired
	private PDWealthMapper pdWealthMapper;
	@Autowired
	private AgentWageInfoMapper agentWageInfoMapper;
	@Autowired
	private CalSalaryServiceMapper calSalaryServiceMapper;
	@Autowired
	private AgentAssessInfoMapper agentAssessInfoMapper;
	@Autowired
	private AgentAssessMapper agentAssessMapper;
	@Autowired
	private AgentNurserInfoMapper agentNurserInfoMapper;
	@Autowired
	private AgentWorkInfoMapper agentWorkInfoMapper;

	@Autowired
	private SlyBaseSalaryMapper slyBaseSalaryMapper;
	@Autowired
	private SlySaleCommissionMapper slySaleCommissionMapper;
	@Autowired
	private SlyGuojinCommissionMapper slyGuojinCommissionMapper;
	@Autowired
	private SlyOverseasCommissionMapper slyOverseasCommissionMapper;
	@Autowired
	private SlyProjectCommissionMapper slyProjectCommissionMapper;
	@Autowired
	private SlyReissueMapper slyReissueMapper;
	@Autowired
	private SlyWithholdMapper slyWithholdMapper;

	@Autowired
	private AgentOtherInfoMapper agentOtherInfoMapper;
	
	@Autowired
	private SlySalaryMapper slySalaryMapper;
	@Autowired
	private AgentAccInfoMapper agentAccMapper;
	@Autowired
	private SlyCommissionMapper slyCommissionMapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryAgentBelongList(DataGridModel dgm,Map paramMap ,LoginInfo loginInfo) {
		if(paramMap==null){
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		Integer total = salesServiceMapper.findAgentBelongCount(paramMap);
		List<Map<String,String>> rows = salesServiceMapper.findAgentBelongList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(total);
		dataGrid.setRows(rows);
		return dataGrid;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getAgentPageList(DataGridModel dgm, Agent agent) {
		
		Map<String, Object> result = new HashMap<String, Object>(2);
		List<Map> list = agentServiceMapper.getAgentPageList(agent);
		result.put("rows", list);
		return result;	
	}	

	@Transactional
	public boolean saveAgent(String param) throws ParseException {
		
    	//agent 基本信息
    	String agentInfo = JsonUtils.getJsonValueByKey("agentInfo", param);
    	//家庭信息
    	String familyInfo =  JsonUtils.getJsonValueByKey("familyInfo", param);
    	//资格证信息
    	String certifyInfo =  JsonUtils.getJsonValueByKey("certifyInfo", param);
    	//职级信息
    	String positionInfo =  JsonUtils.getJsonValueByKey("positionInfo", param);
    	//网点信息
    	String buildingInfo =  JsonUtils.getJsonValueByKey("buildingInfo", param);
    	//团队信息
    	String departInfo =  JsonUtils.getJsonValueByKey("departInfo", param);

    	//String 转JsonObject
    	JSONObject agentJsonObject = JSONObject.fromObject(agentInfo);
    	JSONObject familyJsonObject = JSONObject.fromObject(familyInfo.substring(1, familyInfo.length()-1));//从datagrid取出的字符转多[]
    	JSONObject certifyJsonObject = JSONObject.fromObject(certifyInfo.substring(1, certifyInfo.length()-1));//删除[]
    	JSONObject positionJsonObject = JSONObject.fromObject(positionInfo);
    	JSONObject buildingJsonObject = JSONObject.fromObject(buildingInfo);
    	JSONObject departJsonObject = JSONObject.fromObject(departInfo);
    	
    	//JSONObject 转javabean
    	Agent agent = (Agent) JSONObject.toBean(agentJsonObject, Agent.class);//财富顾问基本信息
    	AgentFamilyInfo agentFamily = (AgentFamilyInfo) JSONObject.toBean(familyJsonObject, AgentFamilyInfo.class);//家庭信息
    	AgentCertificationInfo agentCertify = (AgentCertificationInfo) JSONObject.toBean(certifyJsonObject, AgentCertificationInfo.class);//职级信息
    	AgentPositionTrace agentPosition = (AgentPositionTrace) JSONObject.toBean(positionJsonObject, AgentPositionTrace.class);//职级
    	//AgentBuilding agentBuilding = (AgentBuilding) JSONObject.toBean(buildingJsonObject, AgentBuilding.class);//所属网点
    	//AgentDepartment agentDepart = (AgentDepartment) JSONObject.toBean(departJsonObject, AgentDepartment.class);//所属团队
    	
    	//主键生成
    	Long agentId = commonService.getSeqValByName("SEQ_T_AGENT");//Agent 主键
    	Long agentFamilyId = commonService.getSeqValByName("SEQ_T_AGENT_FAMILY_INFO");//家庭主键
    	Long agentCertifyId = commonService.getSeqValByName("SEQ_T_AGENT_CERTIFICATION_INFO");//资格主键
    	Long agentPositionId = commonService.getSeqValByName("SEQ_T_AGENT_POSITION_TRACE");//职级主键
    	Long agentBuildingId = commonService.getSeqValByName("SEQ_T_AGENT_BUILDING");//网点主键
    	Long agentDepartId = commonService.getSeqValByName("SEQ_T_AGENT_DEPARTMENT");//家庭主键
    	
    	//网点信息
    	AgentStore agentStore = new AgentStore();//所属网点
    	String buildingId = buildingJsonObject.get("buildingId")!=null?buildingJsonObject.get("buildingId").toString():null;
    	String buildingStartDateStr = buildingJsonObject.get("startDate")!=null?buildingJsonObject.get("startDate").toString():null;
    	String buildingEndDateStr = buildingJsonObject.get("endDate")!=null?buildingJsonObject.get("endDate").toString():null;
    	Date buildingStartDate = buildingStartDateStr!=null?DateUtils.getDate(buildingStartDateStr):null;
    	Date buildingEndDate = buildingEndDateStr!=null?DateUtils.getDate(buildingEndDateStr):null;
    	agentStore.setStoreId(buildingId!=null?new Long(buildingId):null);
    	agentStore.setAgentId(agentId);
    	agentStore.setStartDate(buildingStartDate);
    	agentStore.setEndDate(buildingEndDate);
    	agentStore.setBelongStoreId(agentBuildingId);//主键
    	agentStore.setRcState(Constants.EFFECTIVE_RECORD);//Rc
    	agentStore.setCreateDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentStore.setModifyDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentStore.setCreateUserId(new Long("01"));
    	agentStore.setModifyUserId(new Long("01"));
    	agentStore.setOperComId(new Long("01"));//
    	
    	//团队信息
    	AgentDepartment agentDepart = new AgentDepartment();//所属团队
    	String departmentId = departJsonObject.get("departmentId")!=null?departJsonObject.get("departmentId").toString():null;
    	String departStartDateStr = departJsonObject.get("startDate")!=null?departJsonObject.get("startDate").toString():null;
    	String departEndDateStr = departJsonObject.get("endDate")!=null?departJsonObject.get("endDate").toString():null;
    	Date departStartDate = departStartDateStr!=null?DateUtils.getDate(departStartDateStr):null;
    	Date departEndDate = departEndDateStr!=null?DateUtils.getDate(departEndDateStr):null;
    	agentDepart.setAgentId(agentId);
    	agentDepart.setDepartmentId(departmentId!=null?new Long(departmentId):null);
    	agentDepart.setStartDate(departStartDate);
    	agentDepart.setEndDate(departEndDate);
    	agentDepart.setBldepartmentId(agentDepartId);//主键
    	agentDepart.setRcState(Constants.EFFECTIVE_RECORD);//Rc
    	agentDepart.setCreateDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentDepart.setModifyDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentDepart.setCreateUserId(new Long("01"));
    	agentDepart.setModifyUserId(new Long("01"));
    	agentDepart.setOperComId(new Long("01"));
    	
    	//机构信息
    	String comId = departJsonObject.get("comId")!=null?departJsonObject.get("comId").toString():null;
    	agent.setComId(comId!=null?new Long(comId):null);
    	//set主键
    	agent.setAgentId(agentId);//主键
    	agent.setRcState(Constants.EFFECTIVE_RECORD);//Rc
    	agent.setCreateDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agent.setModifyDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agent.setCreateUserId(new Long("01"));
    	agent.setModifyUserId(new Long("01"));
    	agent.setOperComId(new Long("01"));//
    	
    	agentPosition.setBlpositionId(agentPositionId);//主键
    	agentPosition.setAgentId(agentId);
    	agentPosition.setRcState(Constants.EFFECTIVE_RECORD);//Rc
    	agentPosition.setCreateDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentPosition.setModifyDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentPosition.setCreateUserId(new Long("01"));
    	agentPosition.setModifyUserId(new Long("01"));
    	agentPosition.setOperComId(new Long("01"));
    	
    	agentCertify.setCertificationId(agentCertifyId);//主键
    	agentCertify.setAgentId(agentId);
    	agentCertify.setRcState(Constants.EFFECTIVE_RECORD);//Rc
    	agentCertify.setCreateDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentCertify.setModifyDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentCertify.setCreateUserId(new Long("01"));//
    	agentCertify.setModifyUserId(new Long("01"));
    	agentCertify.setOperComId(new Long("01"));
    	
    	agentFamily.setFamilyId(agentFamilyId);//
    	agentFamily.setAgentId(agentId);//
    	agentFamily.setRcState(Constants.EFFECTIVE_RECORD);//Rc
    	agentFamily.setCreateDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentFamily.setModifyDate(DateUtils.getDate(DateUtils.getCurrentDate()));
    	agentFamily.setCreateUserId(new Long("01"));//暂定
    	agentFamily.setModifyUserId(new Long("01"));//
    	agentFamily.setOperComId(new Long("01"));
			
		agentMapper.insert(agent);
		agentFamilyInfoMapper.insert(agentFamily);
		agentCertificationInfoMapper.insert(agentCertify);
		agentPositionTraceMapper.insert(agentPosition);
		agentStoreMapper.insert(agentStore);
		agentDepartmentMapper.insert(agentDepart);
		return true;
	}

	@Override
	public Map<String, Object> getPageList(DataGridModel dgm,
			Privilege privilege) {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryAgentList(DataGridModel dgm, Map paramMap,
			LoginInfo loginInfo) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		
		Integer total = salesServiceMapper.queryAgentListCount(paramMap);
		List<Map> resultList = salesServiceMapper.queryAgentList(paramMap);
		//datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultInfo submitAgent(Agent agent,
			List<AgentFamilyInfo> agentFamilyInfoList,
			List<AgentCertificationInfo> agentCertificationInfoList,
			List<AgentPositionTrace> agentPositionList,
			List<AgentNurserInfo> agentNurserInfoList,
			List<AgentWorkInfo> agentWorkInfoList,
			List<Map<String, String>> agentDepartmentList,
			List<AgentOtherInfo> agentOtherInfoList,
			List<AgentAccInfo> agentAccInfoList,
			LoginInfo loginInfo,String userCode,String operate) {
		ResultInfo resultInfo = new ResultInfo();
		//获取最后的所属网点
		/*resultInfo = getTheLastAgentStore(agent,agentStoreList);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		AgentStore lastAgentStore = (AgentStore)resultInfo.getObj();*/
		//获取最后的所属团队
		resultInfo = getTheLastAgentDepartment(agent,agentDepartmentList);
		if (!resultInfo.isSuccess()) {
			return resultInfo;
		}
		AgentDepartment lastAgentDepartment = (AgentDepartment)resultInfo.getObj();
		try {
			if (agent!=null) {
				if (operate!=null&&!"".equals(operate)) {
					//还没有用户，为其创建用户
					DefUser defUser = new DefUser();
					/*if (agent.getUserId()!=null) {
						defUser = defUserMapper.selectByPrimaryKey(agent.getUserId());
					}*/
					DefUserExample defUserExample = new DefUserExample();
					DefUserExample.Criteria criteria = defUserExample.createCriteria();
					criteria.andUserCodeEqualTo(userCode);
					List<DefUser> defUserList = defUserMapper.selectByExample(defUserExample);
					if (defUserList.size()>0) {
						defUser = defUserList.get(0);
						/*defUser.setStoreId(lastAgentStore.getStoreId());*/
						defUser.setDepartmentId(lastAgentDepartment.getDepartmentId());
						defUser.setComId(agent.getComId());
					}else{
						defUser.setUserCode(userCode);
						defUser.setUserName(agent.getAgentName());
						defUser.setPassword(userCode);
						defUser.setComId(agent.getComId());
						/*defUser.setStoreId(lastAgentStore.getStoreId());*/
						defUser.setDepartmentId(lastAgentDepartment.getDepartmentId());
						defUser.setEmail(agent.getEmail());
						defUser.setState("01");
						defUser.setValidstartDate(DateUtils.getCurrentTimestamp());
					}
					
					if (defUser.getUserId()==null) {
						defUser = createUser(defUser,loginInfo);
						//用户创建完成，为用户分配财富顾问的角色
						resultInfo = distributeRoleToUser(defUser,loginInfo);
						if (!resultInfo.isSuccess()) {
							return resultInfo;
						}
					}else {
						this.defUserMapper.updateByPrimaryKey(defUser);
						//defUserMapper.updateByPrimaryKeySelective(defUser);
					}
					agent.setUserId(defUser.getUserId());
					
				}
				
				if (operate!=null&&!"".equals(operate)&&"updateAgent".equals(operate)
						&&agent.getAgentId()==null) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("财富顾问流水号为空，不能更新！");
					return resultInfo;
				}
			/*	if (lastAgentStore!=null) {
					agent.setStoreId(lastAgentStore.getStoreId());
				}*/
				if (lastAgentDepartment!=null) {
					agent.setDepartmentId(lastAgentDepartment.getDepartmentId());
				}
			}
			//1.保存财富顾问基本信息
			resultInfo = saveAgentInfo(agent, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//2.保存财富顾问家庭成员信息
			resultInfo = saveAgentFamilyInfo(agent, agentFamilyInfoList, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//3.保存财富顾问资格证书信息
			resultInfo = saveAgentCertificationInfo(agent, agentCertificationInfoList, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//4.保存财富顾问职级信息
			resultInfo = saveAgentPositionInfo(agent,agentPositionList, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//4.1保存财富顾问培育信息
			resultInfo = saveAgentNurserInfo(agent,agentNurserInfoList, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//4.2保存财富顾问从业经历信息
			resultInfo = saveAgentWorkInfo(agent,agentWorkInfoList, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//5.保存财富顾问所属网点信息
			/*List<AgentStore> agentStores = (List<AgentStore>)JsonUtils.listMapToListObj(agentStoreList,AgentStore.class);
			resultInfo = saveAgentStoreInfo(agent, agentStores, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}*/
			//6.保存财富顾问所属团队信息
			List<AgentDepartment> agentDepartment = (List<AgentDepartment>)JsonUtils.listMapToListObj(agentDepartmentList,AgentDepartment.class);
			resultInfo = saveAgentDepartmentInfo(agent, agentDepartment, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//7.保存员工其他信息
			resultInfo = saveAgentOtherInfo(agent,agentOtherInfoList, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
			//8.保存员工工资卡信息
			resultInfo = saveAgentSalaryAccInfo(agent, agentAccInfoList, loginInfo ,operate);
			if (!resultInfo.isSuccess()) {
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("新增员工信息失败");
		}
		resultInfo.setSuccess(true);
		resultInfo.setObj(agent);
		return resultInfo;
	}
	
	/**
	 * @param agent
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveAgentInfo(Agent agent,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		if (agent==null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("财富顾问基本信息为空");
			return resultInfo;
		}
		try {
			//if (operate!=null&&!"".equals(operate)&&"addAgent".equals(operate)) {
			//用户新增时多次提交或是修改时，直接更新
			if (agent.getAgentId()!=null) {
				Agent existAgent = agentMapper.selectByPrimaryKey(agent.getAgentId());
				if (existAgent!=null) {
					agent.setAgentId(existAgent.getAgentId());
					agent.setAgentImage(existAgent.getAgentImage());
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existAgent, agent, loginInfo);
					agentMapper.updateByPrimaryKey(agent);
				}else {
					Long agentId = commonService.getSeqValByName("SEQ_T_AGENT");
					agent.setAgentId(agentId);
					BeanUtils.insertObjectSetOperateInfo(agent, loginInfo);
					agentMapper.insert(agent);
				}
			}else {
				Long agentId = commonService.getSeqValByName("SEQ_T_AGENT");
				agent.setAgentId(agentId);
				BeanUtils.insertObjectSetOperateInfo(agent, loginInfo);
				agentMapper.insert(agent);
				AgentExample agentExample = new AgentExample();
				agentExample.createCriteria().andUserIdEqualTo(agent.getUserId());
				List<Agent> newAgent = agentMapper.selectByExample(agentExample);
				agent.setAgentId(newAgent.get(0).getAgentId());
			}
			//}
			//更新财富顾问信息，在这里，为了保持原有关联的agentId不变，将原有的copy一条新的数据记录保存成轨迹，原有记录仍然有效
			/*else if(operate!=null&&!"".equals(operate)&&"updateAgent".equals(operate)){
				Agent existAgent = agentMapper.selectByPrimaryKey(agent.getAgentId());
				if (existAgent!=null) {
					Long agentId = commonService.getSeqValByName("SEQ_T_AGENT");
					existAgent.setAgentId(agentId);
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existAgent, agent, loginInfo);
					agentMapper.insert(existAgent);
					agentMapper.updateByPrimaryKey(agent);
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问基本信息出错");
		}
		resultInfo.setSuccess(true);
		resultInfo.setObj(agent);
		return resultInfo;
	}
	
	/**
	 * @param agent
	 * @param agentFamilyInfoList
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveAgentFamilyInfo(Agent agent,
			List<AgentFamilyInfo> agentFamilyInfoList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			/*该模块作用如下：
			 * 作用一、新增财富顾问保存家庭信息时-----（1、先根据agentid查询财富顾问家庭信息是否存在）
			 * 作用二、更新财富顾问家庭信息时----（1、先根据agentid逻辑删除t_agent_family_info表中的所有信息）
			 */
			AgentFamilyInfoExample agentFamilyInfoExample=new AgentFamilyInfoExample();
			AgentFamilyInfoExample.Criteria agentFamilyInfoExampleCriteria=agentFamilyInfoExample.createCriteria();
			agentFamilyInfoExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentFamilyInfo> agentFamilyInfosList = agentFamilyInfoMapper.selectByExample(agentFamilyInfoExample);
			if (agentFamilyInfosList!=null&&agentFamilyInfosList.size()>0) {
				for(AgentFamilyInfo agentFamilyInfo2:agentFamilyInfosList){
					BeanUtils.deleteObjectSetOperateInfo(agentFamilyInfo2, loginInfo);
					agentFamilyInfoMapper.updateByPrimaryKey(agentFamilyInfo2);
				}
			}
			/*该模块作用如下：
			 * 一、新增财富顾问家庭信息时---（2、不存在时将参数agentFamilyInfoList中的信息录入数据库）
			 * 二、更新财富顾问家庭信息时---（2、重新将参数agentFamilyInfoList中的信息录入数据库）
			 */
			if (agentFamilyInfoList!=null&&agentFamilyInfoList.size()>0) {
				for (AgentFamilyInfo agentFamilyInfo:agentFamilyInfoList) {
				//保存新的家庭信息
				Long agentFamilyId = commonService.getSeqValByName("SEQ_T_AGENT_Family_INFO");
				agentFamilyInfo.setAgentId(agent.getAgentId());
				agentFamilyInfo.setFamilyId(agentFamilyId);;
				BeanUtils.insertObjectSetOperateInfo(agentFamilyInfo, loginInfo);
				agentFamilyInfoMapper.insert(agentFamilyInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问家庭成员信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	} 
	
	
	/**
	 * @param agent
	 * @param agentCertificationInfoList
	 * @param loginInfo
	 * @return
	 */
	/*@Transactional
	private ResultInfo saveAgentCertificationInfo(Agent agent,
			List<AgentCertificationInfo> agentCertificationInfoList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			
			if (agentCertificationInfoList!=null&&agentCertificationInfoList.size()>0) {
				for (AgentCertificationInfo agentCertificationInfo:agentCertificationInfoList) {
					if (agentCertificationInfo.getCertificationId()==null) {
						Long agentCertificationInfoId = commonService.getSeqValByName("SEQ_T_AGENT_CERTIFICATION_INFO");
						agentCertificationInfo.setCertificationId(agentCertificationInfoId);
						agentCertificationInfo.setAgentId(agent.getAgentId());
						BeanUtils.insertObjectSetOperateInfo(agentCertificationInfo, loginInfo);
						agentCertificationInfoMapper.insert(agentCertificationInfo);
					}else {
						agentCertificationInfo.setAgentId(agent.getAgentId());
						BeanUtils.updateObjectSetOperateInfo(agentCertificationInfo, loginInfo);
						agentCertificationInfoMapper.updateByPrimaryKeySelective(agentCertificationInfo);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问资格证书信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}*/
	@Transactional
	private ResultInfo saveAgentCertificationInfo(Agent agent,
			List<AgentCertificationInfo> agentCertificationInfoList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			/* 该模块作用如下：
			 * 1、新增
			 * 2、更新---根据财富顾问id逻辑删除t_agent_certification_info表的所有信息
			 */
			AgentCertificationInfoExample agentCertificationInfoExample=new AgentCertificationInfoExample();
			AgentCertificationInfoExample.Criteria agentCertificationInfoExampleCriteria=agentCertificationInfoExample.createCriteria();
			agentCertificationInfoExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentCertificationInfo> agentCertificationInfoList1=agentCertificationInfoMapper.selectByExample(agentCertificationInfoExample);
		    if(agentCertificationInfoList1!=null&&agentCertificationInfoList1.size()>0)
		    {
		    	for(AgentCertificationInfo agentCertificationInfo:agentCertificationInfoList1)
		    	{
		    		BeanUtils.deleteObjectSetOperateInfo(agentCertificationInfo, loginInfo);
		    		agentCertificationInfoMapper.updateByPrimaryKey(agentCertificationInfo);
		    	}
		    }
		    //重新添加
		    if(agentCertificationInfoList!=null&&agentCertificationInfoList.size()>0)
		    {
		    	for(AgentCertificationInfo agentCertificationInfo:agentCertificationInfoList)
		    	{
		    		Long agentCertifyId = commonService.getSeqValByName("SEQ_T_AGENT_CERTIFICATION_INFO");//资格主键
		    		agentCertificationInfo.setAgentId(agent.getAgentId());
		    		agentCertificationInfo.setCertificationId(agentCertifyId);
					BeanUtils.insertObjectSetOperateInfo(agentCertificationInfo, loginInfo);
					agentCertificationInfoMapper.insert(agentCertificationInfo);
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问资格证书信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * @param agent
	 * @param agentPositionTrace
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveAgentPositionInfo(Agent agent,
			List<AgentPositionTrace> agentPositionTraceList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			/* 1、新增
			 * 2、更新---根据财富顾问id逻辑删除t_agent_position_trace表的所有信息
			 */
			AgentPositionTraceExample agentPositionTraceExample = new AgentPositionTraceExample();
			AgentPositionTraceExample.Criteria agentPositionTraceExampleCriteria = agentPositionTraceExample.createCriteria();
			agentPositionTraceExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentPositionTrace> agentPositionTraceList2 = agentPositionTraceMapper.selectByExample(agentPositionTraceExample);
			if (agentPositionTraceList2!=null&&agentPositionTraceList2.size()>0) {
				for(AgentPositionTrace agentPositionTrace:agentPositionTraceList2){
					BeanUtils.deleteObjectSetOperateInfo(agentPositionTrace, loginInfo);
					agentPositionTraceMapper.updateByPrimaryKey(agentPositionTrace);
				}						
			}
			// 将参数agentPositionTraceList列表中新记录重新存入数据库中
			if (agentPositionTraceList!=null&&agentPositionTraceList.size()>0) {
				for (AgentPositionTrace agentPositionTrace:agentPositionTraceList) {
					//保存新的职级信息
					Long agentPositionId = commonService.getSeqValByName("SEQ_T_AGENT_POSITION_TRACE");//职级主键
					agentPositionTrace.setAgentId(agent.getAgentId());
					agentPositionTrace.setBlpositionId(agentPositionId);;
					BeanUtils.insertObjectSetOperateInfo(agentPositionTrace, loginInfo);
					agentPositionTraceMapper.insert(agentPositionTrace);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问职级信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * @param agent
	 * @param agentPositionTrace
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveAgentNurserInfo(Agent agent, List<AgentNurserInfo> agentNurserInfoList, LoginInfo loginInfo, String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 将t_agent_nurse_info表中对应agent对象中agentId的所有记录逻辑删除
			AgentNurserInfoExample agentNurserInfoExample = new AgentNurserInfoExample();
			AgentNurserInfoExample.Criteria agentNurserInfoExampleCriteria = agentNurserInfoExample.createCriteria();
			agentNurserInfoExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentNurserInfo> agentNurserInfosList = agentNurserInfoMapper.selectByExample(agentNurserInfoExample);
			if (agentNurserInfosList!=null&&agentNurserInfosList.size()>0) {
				for(AgentNurserInfo agentNurserInfo2:agentNurserInfosList){
					BeanUtils.deleteObjectSetOperateInfo(agentNurserInfo2, loginInfo);
					agentNurserInfoMapper.updateByPrimaryKey(agentNurserInfo2);
				}
			}
			// 将agentNurserInfoList列表中新纪录重新存入数据库中
			if (agentNurserInfoList!=null&&agentNurserInfoList.size()>0) {
				for (AgentNurserInfo agentNurserInfo:agentNurserInfoList) {
					//保存新的培育信息
					Long agentNurserInfoId = commonService.getSeqValByName("SEQ_T_AGENT_NURSER_INFO");
					agentNurserInfo.setAgentId(agent.getAgentId());
					agentNurserInfo.setAgentNurserInfoId(agentNurserInfoId);
					BeanUtils.insertObjectSetOperateInfo(agentNurserInfo, loginInfo);
					agentNurserInfoMapper.insert(agentNurserInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问培育关系信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	/**
	 * @param agent
	 * @param agentPositionTrace
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveAgentWorkInfo(Agent agent,
			List<AgentWorkInfo> agentWorkInfoList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentWorkInfoExample agentWorkInfoExample = new AgentWorkInfoExample();
			AgentWorkInfoExample.Criteria agentWorkInfoExampleCriteria = agentWorkInfoExample.createCriteria();
			agentWorkInfoExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentWorkInfo> agentWorkInfoList2 = agentWorkInfoMapper.selectByExample(agentWorkInfoExample);
			if (agentWorkInfoList2!=null&&agentWorkInfoList2.size()>0) {
				for(AgentWorkInfo agentWorkInfo:agentWorkInfoList2){
					BeanUtils.deleteObjectSetOperateInfo(agentWorkInfo, loginInfo);
					agentWorkInfoMapper.updateByPrimaryKey(agentWorkInfo);
				}						
			}
			if (agentWorkInfoList!=null&&agentWorkInfoList.size()>0) {
				for (AgentWorkInfo agentWorkInfo:agentWorkInfoList) {
					//保存新的从业经历信息
					Long agentWorkInfoId = commonService.getSeqValByName("SEQ_T_AGENT_WORK_INFO_ID");//从业经历主键
					agentWorkInfo.setAgentId(agent.getAgentId());
					agentWorkInfo.setAgentWorkInfoId(agentWorkInfoId);
					BeanUtils.insertObjectSetOperateInfo(agentWorkInfo, loginInfo);
					agentWorkInfoMapper.insert(agentWorkInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问从业经历信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	
	
	/**
	 * @param agent
	 * @param agentBuilding
	 * @param loginInfo
	 * @return
	 */
/*	@Transactional
	private ResultInfo saveAgentStoreInfo(Agent agent,
			List<AgentStore> agentStoreList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentStoreExample agentStoreExample = new AgentStoreExample();
			AgentStoreExample.Criteria agentStoreExampleCriteria = agentStoreExample.createCriteria();
			agentStoreExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentStore> agentStoreList2 = agentStoreMapper.selectByExample(agentStoreExample);
			if (agentStoreList2!=null&&agentStoreList2.size()>0) {
				for(AgentStore agentStore:agentStoreList2){
					BeanUtils.deleteObjectSetOperateInfo(agentStore, loginInfo);
					agentStoreMapper.updateByPrimaryKey(agentStore);
				}						
			}
			if (agentStoreList!=null&&agentStoreList.size()>0) {
				for (AgentStore agentStore:agentStoreList) {
					//保存新的网点信息
					Long belongStoreId = commonService.getSeqValByName("SEQ_T_AGENT_BUILDING");//网点主键
					agentStore.setAgentId(agent.getAgentId());
					agentStore.setBelongStoreId(belongStoreId);
					BeanUtils.insertObjectSetOperateInfo(agentStore, loginInfo);
					agentStoreMapper.insert(agentStore);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问所属网点信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}*/
	
	/**
	 * @param agent
	 * @param agentDepartment
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveAgentDepartmentInfo(Agent agent,
			List<AgentDepartment> agentDepartmentList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentDepartmentExample agentDepartmentExample = new AgentDepartmentExample();
			AgentDepartmentExample.Criteria agentDepartmentExampleCriteria = agentDepartmentExample.createCriteria();
			agentDepartmentExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentDepartment> agentDepartmentList2 = agentDepartmentMapper.selectByExample(agentDepartmentExample);
			if (agentDepartmentList2!=null&&agentDepartmentList2.size()>0) {
				for(AgentDepartment agentDepartment:agentDepartmentList2){
					BeanUtils.deleteObjectSetOperateInfo(agentDepartment, loginInfo);
					agentDepartmentMapper.updateByPrimaryKey(agentDepartment);
				}						
			}
			if (agentDepartmentList!=null&&agentDepartmentList.size()>0) {
				for (AgentDepartment agentDepartment:agentDepartmentList) {
					//保存新的团队信息
					Long agentDepartmentId = commonService.getSeqValByName("SEQ_T_AGENT_DEPARTMENT_ID");//团队信息主键
					agentDepartment.setAgentId(agent.getAgentId());
					agentDepartment.setBldepartmentId(agentDepartmentId);;
					BeanUtils.insertObjectSetOperateInfo(agentDepartment, loginInfo);
					agentDepartmentMapper.insert(agentDepartment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存财富顾问所属团队信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**
	 * @param agent
	 * @param agentPositionTrace
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo saveAgentOtherInfo(Agent agent,
			List<AgentOtherInfo> agentOtherInfoList,LoginInfo loginInfo,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentOtherInfoExample agentOtherInfoExample = new AgentOtherInfoExample();
			AgentOtherInfoExample.Criteria agentOtherInfoExampleCriteria = agentOtherInfoExample.createCriteria();
			agentOtherInfoExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentOtherInfo> agentOtherInfoList2 = agentOtherInfoMapper.selectByExample(agentOtherInfoExample);
			if (agentOtherInfoList2!=null&&agentOtherInfoList2.size()>0) {
				for(AgentOtherInfo agentOtherInfo:agentOtherInfoList2){
					BeanUtils.deleteObjectSetOperateInfo(agentOtherInfo, loginInfo);
					agentOtherInfoMapper.updateByPrimaryKey(agentOtherInfo);
				}						
			}
			if (agentOtherInfoList!=null&&agentOtherInfoList.size()>0) {
				for (AgentOtherInfo agentOtherInfo:agentOtherInfoList) {
					//保存新的从业经历信息
					Long agentOtherInfoId = commonService.getSeqValByName("SEQ_T_AGENT_Other_INFO_ID");//从业经历主键
					agentOtherInfo.setAgentId(agent.getAgentId());
					agentOtherInfo.setAgentOtherInfoId(agentOtherInfoId);
					BeanUtils.insertObjectSetOperateInfo(agentOtherInfo, loginInfo);
					agentOtherInfoMapper.insert(agentOtherInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存员工其他信息出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	/**查询财富顾问详细信息
	 * @param agent
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo queryAgentInfo(Agent agent, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> agentInfoMap = new HashMap<String, Object>();
		if (agent.getAgentId()==null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("财富顾问流水号为空，未获取到财富顾问详细信息");
			return resultInfo;
		}
		try {
			Map paramMap = new HashMap();
			paramMap.put("agentId", agent.getAgentId().toString());
			//1.获取财富顾问基本信息
			Map<String, String> agentMap = getAgentBaseInfo(agent);
			if (agentMap!=null) {
				agentInfoMap.put("agentBaseInfo", agentMap);
			}
			//2.获取财富顾问家庭成员信息
			List<Map> agentFamilyInfo = getAgentFamilyInfo(paramMap);
			if (agentFamilyInfo!=null&&agentFamilyInfo.size()>0) {
				agentInfoMap.put("agentFamilyInfo", agentFamilyInfo);
			}
			//3.获取财富顾问证书信息
			List<Map> agentCertificationInfo = getAgentCertificationInfo(paramMap);
			if (agentCertificationInfo!=null&&agentCertificationInfo.size()>0) {
				agentInfoMap.put("agentCertificationInfo", agentCertificationInfo);
			}
			//4.获取财富顾问职级信息
			List<Map> agentPositionTraceList = getAgentPositionTrace(paramMap);
			if (agentPositionTraceList!=null&&agentPositionTraceList.size()>0) {
				agentInfoMap.put("agentPositionInfo", agentPositionTraceList);
			}
			//4.1获取财富顾问培育信息
			List<Map> agentNurserInfoList = getAgentNurserInfo(paramMap);
			if (agentNurserInfoList!=null&&agentNurserInfoList.size()>0) {
				agentInfoMap.put("agentNurserInfo", agentNurserInfoList);
			}
			//4.2获取财富顾问从业经历信息
			List<Map> agentWorkInfoList = getAgentWorkInfo(paramMap);
			if (agentWorkInfoList!=null&&agentWorkInfoList.size()>0) {
				agentInfoMap.put("agentWorkInfo", agentWorkInfoList);
			}
			//5.获取财富顾问所属网点信息
			/*List<Map> agentStoreList = getAgentStoreInfo(paramMap);
			if(agentStoreList!=null&&agentStoreList.size()>0){
				agentInfoMap.put("agentStoreInfo", agentStoreList);
			}*/
			//6.获取财富顾问所属团队信息
			List<Map> agentDepartmentList = getAgentDepartmentInfo(paramMap);
			if(agentDepartmentList!=null&&agentDepartmentList.size()>0){
				agentInfoMap.put("agentDepartmentInfo", agentDepartmentList);
			}
			//7.获取员工其他信息
			List<Map> agentOtherList = getAgentOtherInfo(paramMap);
			if(agentOtherList!=null&&agentOtherList.size()>0){
				agentInfoMap.put("agentOtherInfo", agentOtherList);
			}
			//8.获取员工工资卡信息
			List<Map> agentSalaryAccList = getAgentSalaryAccInfo(paramMap);
			if(agentSalaryAccList!=null&&agentSalaryAccList.size()>0){
				agentInfoMap.put("agentSalaryAccInfo", agentSalaryAccList);
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(agentInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取到财富顾问详细信息出现异常");
			return resultInfo;
		}
		return resultInfo;
	}
	
	

	/**
	 * @param paramMap
	 * @return
	 */
	private Map<String, String> getAgentBaseInfo(Agent agent){
		Map<String, String> agentMap = new HashMap<String, String>();
		try {
			Agent agentBaseInfo = agentMapper.selectByPrimaryKey(agent.getAgentId());
			DefUser defUser = defUserMapper.selectByPrimaryKey(agentBaseInfo.getUserId());
			if (agentBaseInfo.getAgentImage()!=null&&!"".equals(agentBaseInfo.getAgentImage())) {
				String fileSaveServerHttpAddress = commonService.getFileSaveServerHttpAddress();
				String fileSavePath = commonService.getFileSavePath("03");
				agentBaseInfo.setAgentImage(fileSaveServerHttpAddress+fileSavePath+agentBaseInfo.getAgentImage());
			}
			agentMap = JsonUtils.objectToMap(agentBaseInfo);
			agentMap.put("userCode", defUser.getUserCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentMap;
	}
	
	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentFamilyInfo(Map paramMap){
		List<Map> agentFamilyInfoList = new ArrayList<Map>();
		try {
			agentFamilyInfoList = salesServiceMapper.getAgentFamilyInfoList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentFamilyInfoList;
	}
	
	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentCertificationInfo(Map paramMap){
		List<Map> agentCertificationInfoList = new ArrayList<Map>();
		try {
			agentCertificationInfoList = salesServiceMapper.getAgentCertificationInfoList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentCertificationInfoList;
	}
	
	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentPositionTrace(Map paramMap){
		List<Map> agentPositionTraceList = new ArrayList<Map>();
		try {
			agentPositionTraceList = salesServiceMapper.getAgentPositionTraceInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentPositionTraceList;
	}
	
	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentNurserInfo(Map paramMap){
		List<Map> agentNurserInfoList = new ArrayList<Map>();
		try {
			agentNurserInfoList = salesServiceMapper.getAgentNurserInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentNurserInfoList;
	}
	
	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentWorkInfo(Map paramMap){
		List<Map> agentWorkInfoList = new ArrayList<Map>();
		try {
			agentWorkInfoList = salesServiceMapper.getAgentWorkInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentWorkInfoList;
	}
	
	/**
	 * @param paramMap
	 * @return
	 */
/*	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentStoreInfo(Map paramMap){
		List<Map> agentStoreList = new ArrayList<Map>();
		try {
			agentStoreList = salesServiceMapper.getAgentStoreTraceInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentStoreList;
	}*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentDepartmentInfo(Map paramMap){
		List<Map> agentDepartmentList = new ArrayList<Map>();
		try {
			agentDepartmentList = salesServiceMapper.getAgentDepartmentTraceInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentDepartmentList;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private List<Map> getAgentOtherInfo(Map paramMap){
		List<Map> agentOtherList = new ArrayList<Map>();
		try {
			agentOtherList = salesServiceMapper.getAgentOtherInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentOtherList;
	}
	
	/**
	 * @param userCode
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private DefUser createUser(DefUser defUser,LoginInfo loginInfo){
		try {
			userService.addUser(defUser, loginInfo);
			List<DefUser> defUserList = userServiceMapper.queryUserByUserCode(defUser);
			if (defUserList!=null&&defUserList.size()>0) {
				defUser = defUserList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defUser;
	}
	
	/**
	 * @param userCode
	 * @return
	 */
	public ResultInfo verifyUserCode(String userId,String userCode,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			/*DefUser defUser = new DefUser();
			defUser.setUserCode(userCode);
			List<DefUser> defUserList = userServiceMapper.queryUserByUserCode(defUser);*/
			DefUserExample defUserExample = new DefUserExample();
			DefUserExample.Criteria criteria = defUserExample.createCriteria();
			//新增时直接判断编码是否存在
			if (operate!=null&&!"".equals(operate)&&"addAgent".equals(operate)) {
				//新增时提交过后再触发校验时排除已经怎加的
				if (userId!=null&&!"".equals(userId)) {
					criteria.andUserIdNotEqualTo(new Long(userId));
				}
				criteria.andUserCodeEqualTo(userCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			}
			//修改时需要把本条记录排除
			else{
				criteria.andUserCodeEqualTo(userCode)
						.andUserIdNotEqualTo(new Long(userId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			}
			
			List<DefUser> defUserList = defUserMapper.selectByExample(defUserExample);
			if (defUserList.size()>0) {
				DefUser defUser = defUserList.get(0);
				AgentExample agentExample = new AgentExample();
				AgentExample.Criteria criteria2 = agentExample.createCriteria();
				Map<String, String> userInfoMap = JsonUtils.objectToMap(defUser);
				Long storeId = defUser.getStoreId();
				if (storeId!=null) {
					String storeName = defStoreMapper.selectByPrimaryKey(storeId).getStoreName();
					userInfoMap.put("storeName", storeId+"-"+storeName);
				}
				Long departmentId = defUser.getDepartmentId();
				if (departmentId!=null) {
					String departmentName = defDepartmentMapper.selectByPrimaryKey(departmentId).getDepartmentName();
					userInfoMap.put("departmentName", departmentId+"-"+departmentName);
				}
				if(operate!=null&&!"".equals(operate)&&"addAgent".equals(operate)){
					criteria2.andUserIdEqualTo(defUser.getUserId())
							 .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<Agent> agentList = agentMapper.selectByExample(agentExample);
					if (agentList.size()>0) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该用户编码已存在关联的财富顾问");
					}else {
						resultInfo.setSuccess(true);
						resultInfo.setObj(userInfoMap);
					}
				}else{
					criteria2.andUserIdEqualTo(defUser.getUserId())
							 .andUserIdNotEqualTo(new Long(userId))
							 .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<Agent> agentList = agentMapper.selectByExample(agentExample);
					if (agentList.size()>0) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该用户编码已存在关联的财富顾问");
					}else {
						resultInfo.setSuccess(true);
						resultInfo.setObj(userInfoMap);
					}
				}
			}else {
				resultInfo.setSuccess(true);
			}
			 
			/*if (defUserList!=null&&defUserList.size()>0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该用户编码已存在");
			}else {
				resultInfo.setSuccess(true);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("通过用户编码查询用户信息出现异常");
			return resultInfo;
		}
		return resultInfo;
	}
	

	public ResultInfo verifyAgentCode(String agentId,String agentCode,String operate){
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria criteria = agentExample.createCriteria();
			//新增时直接判断编码是否存在
			if (operate!=null&&!"".equals(operate)&&"addAgent".equals(operate)) {
				if (agentId!=null&&!"".equals(agentId)) {
					criteria.andAgentIdNotEqualTo(new Long(agentId));
				}
				criteria.andAgentCodeEqualTo(agentCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			}
			//修改时需要把本条记录排除
			else{
				criteria.andAgentCodeEqualTo(agentCode)
						.andAgentIdNotEqualTo(new Long(agentId))
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			}
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			if (agentList!=null&&agentList.size()>0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该财富顾问编码已经存在");
			}else {
				resultInfo.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("通过财富顾问编码查询财富顾问信息出现异常");
			return resultInfo;
		}
		return resultInfo;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public ResultInfo deleteAgent(Agent agent, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		if (agent==null||agent.getAgentId()==null) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("财富顾问信息为空，删除失败！");
			return resultInfo;
		}
		try {
			//0.判断该财富顾问下是否有交易，如果有，则不允许删除
			if(agent.getAgentId()!=null){
				List queryAgentList=qureyTradeByAgentId(agent.getAgentId().toString());
				if(queryAgentList!=null&&queryAgentList.size()>0){
					resultInfo.setSuccess(false);
					resultInfo.setMsg("财富顾问下，有相关的交易，不允许删除该财富顾问！");
					return resultInfo;
				}
			}
			
			//1.逻辑删除财富顾问基本信息
			agent = agentMapper.selectByPrimaryKey(agent.getAgentId());
			BeanUtils.deleteObjectSetOperateInfo(agent, loginInfo);
			agentMapper.updateByPrimaryKey(agent);
			//2.逻辑删除财富顾问家庭成员信息
			AgentFamilyInfoExample agentFamilyInfoExample = new AgentFamilyInfoExample();
			AgentFamilyInfoExample.Criteria agentFamilyCriteria = agentFamilyInfoExample.createCriteria();
			agentFamilyCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentFamilyInfo> agentFamilyInfoList = agentFamilyInfoMapper.selectByExample(agentFamilyInfoExample);
			if (agentFamilyInfoList!=null&&agentFamilyInfoList.size()>0) {
				for(AgentFamilyInfo agentFamilyInfo:agentFamilyInfoList){
					BeanUtils.deleteObjectSetOperateInfo(agentFamilyInfo, loginInfo);
					agentFamilyInfoMapper.updateByPrimaryKey(agentFamilyInfo);
				}
			}
			//3.逻辑删除财富顾问相关证书信息
			AgentCertificationInfoExample agentCertificationInfoExample = new AgentCertificationInfoExample();
			AgentCertificationInfoExample.Criteria agentCertificationCriteria = agentCertificationInfoExample.createCriteria();
			agentCertificationCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentCertificationInfo> agentCertificationInfoList = agentCertificationInfoMapper.selectByExample(agentCertificationInfoExample);
			if (agentCertificationInfoList!=null&&agentCertificationInfoList.size()>0) {
				for(AgentCertificationInfo agentCertificationInfo:agentCertificationInfoList){
					BeanUtils.deleteObjectSetOperateInfo(agentCertificationInfo, loginInfo);
					agentCertificationInfoMapper.updateByPrimaryKey(agentCertificationInfo);
				}
			}
			//4.逻辑删除财富顾问职级信息
			AgentPositionTraceExample agentPositionTraceExample = new AgentPositionTraceExample();
			AgentPositionTraceExample.Criteria agentPositionTraceCriteria = agentPositionTraceExample.createCriteria();
			agentPositionTraceCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentPositionTrace> agentPositionTraceList = agentPositionTraceMapper.selectByExample(agentPositionTraceExample);
			if (agentPositionTraceList!=null&&agentPositionTraceList.size()>0) {
				for(AgentPositionTrace agentPositionTrace:agentPositionTraceList){
					BeanUtils.deleteObjectSetOperateInfo(agentPositionTrace, loginInfo);
					agentPositionTraceMapper.updateByPrimaryKey(agentPositionTrace);
				}
			}
			//5.逻辑删除财富顾问网点归属信息
			AgentStoreExample agentStoreExample = new AgentStoreExample();
			AgentStoreExample.Criteria agentStoreCriteria = agentStoreExample.createCriteria();
			agentStoreCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentStore> agentStoreList = agentStoreMapper.selectByExample(agentStoreExample);
			if (agentStoreList!=null&&agentStoreList.size()>0) {
				for(AgentStore agentStore:agentStoreList){
					BeanUtils.deleteObjectSetOperateInfo(agentStore, loginInfo);
					agentStoreMapper.updateByPrimaryKey(agentStore);
				}
			}
			//6.逻辑删除财富顾问团队归属信息
			AgentDepartmentExample agentDepartmentExample = new AgentDepartmentExample();
			AgentDepartmentExample.Criteria agentDepartmentCriteria = agentDepartmentExample.createCriteria();
			agentDepartmentCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentDepartment> agentDepartmentList = agentDepartmentMapper.selectByExample(agentDepartmentExample);
			if (agentDepartmentList!=null&&agentDepartmentList.size()>0) {
				for(AgentDepartment agentDepartment:agentDepartmentList){
					BeanUtils.deleteObjectSetOperateInfo(agentDepartment, loginInfo);
					agentDepartmentMapper.updateByPrimaryKey(agentDepartment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}
	
	
/*	@SuppressWarnings("rawtypes")
	private ResultInfo getTheLastAgentStore(Agent agent,List<Map<String, String>> agentStoreList){
		ResultInfo resultInfo = new ResultInfo();
		if(agentStoreList==null||agentStoreList.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("所属网点信息为空！");
		}
		//获取起始日期最大的记录，以它的所属网点ID设置为用户的所属网点ID
		Date startDate = null;
		Map lastStoreMap = new HashMap();
		try {
			for (Map map:agentStoreList) {
				if (startDate==null) {
					startDate = DateUtils.getDate((String)map.get("startDate"));
					lastStoreMap = map;
				}else{
					Date startDate_ = DateUtils.getDate((String)map.get("startDate"));
					if (startDate.before(startDate_)) {
						lastStoreMap = map;
						startDate = startDate_;
					}
				}
			}
			agent.setComId(new Long((String)lastStoreMap.get("comId")));
			AgentStore lastAgentStore = (AgentStore)JsonUtils.mapToObject(lastStoreMap, AgentStore.class);
			resultInfo.setSuccess(true);
			resultInfo.setObj(lastAgentStore);
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取财富顾问最后所属网点出错");
			e.printStackTrace();
		}
		
		return resultInfo;
	}*/
	
	@SuppressWarnings("rawtypes")
	private ResultInfo getTheLastAgentDepartment(Agent agent,List<Map<String, String>> agentDepartmentList){
		ResultInfo resultInfo = new ResultInfo();
		if(agentDepartmentList==null||agentDepartmentList.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("所属团队信息为空！");
		}
		//获取起始日期最大的记录，以它的所属团队ID设置为用户的所属团队ID
		Date startDate = null;
		/*AgentDepartment lastAgentDepartment = new AgentDepartment();*/
		Map lastAgentDepartmentMap= new HashMap();
		try {
			
			/*for (AgentDepartment agentDepartment:agentDepartmentList) {
				if (startDate==null) {
					startDate = agentDepartment.getStartDate();
					lastAgentDepartment = agentDepartment;
				}else{
					if (startDate.before(agentDepartment.getStartDate())) {
						lastAgentDepartment = agentDepartment;
						startDate = agentDepartment.getStartDate();
					}
				}
			}*/
			for (Map map:agentDepartmentList) {
				if (startDate==null) {
					startDate = DateUtils.getDate((String)map.get("startDate"));
					lastAgentDepartmentMap = map;
				}else{
					Date startDate_ = DateUtils.getDate((String)map.get("startDate"));
					if (startDate.before(startDate_)) {
						lastAgentDepartmentMap = map;
						startDate = startDate_;
					}
				}
			}
			agent.setComId(new Long((String)lastAgentDepartmentMap.get("comId")));
			AgentDepartment lastAgentDepartment = (AgentDepartment)JsonUtils.mapToObject(lastAgentDepartmentMap, AgentDepartment.class);
			resultInfo.setSuccess(true);
			resultInfo.setObj(lastAgentDepartment);
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取财富顾问最后所属团队出错");
			e.printStackTrace();
		}
		
		return resultInfo;
	}
	
	
	@SuppressWarnings("rawtypes")
	private List qureyTradeByAgentId(String agentId) {
		
		TradeInfoExample tradeInfoExample=new TradeInfoExample();
		
		tradeInfoExample.createCriteria().andAgentIdEqualTo(new Long(agentId)).andRcStateEqualTo("E");
		List tradeByAgentList=	tradeInfoMapper.selectByExample(tradeInfoExample);
		
		return tradeByAgentList;
	}
	
	
	private ResultInfo distributeRoleToUser(DefUser defUser,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取财富顾问角色编码
			DefCodeKey defCodeKey = new DefCodeKey();
			defCodeKey.setCodeType("userRoleType");
			defCodeKey.setCode("01");
			DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
			if(defCode!=null){
				String agentRoleCode = defCode.getCodeName();
				DefRoleExample defRoleExample = new DefRoleExample();
				DefRoleExample.Criteria criteria = defRoleExample.createCriteria();
				criteria.andRoleCodeEqualTo(agentRoleCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<DefRole> defRoleList = defRoleMapper.selectByExample(defRoleExample);
				if (defRoleList!=null&&defRoleList.size()>0) {
					userService.deleteU2R(defUser.getUserId(),loginInfo);
					DefUserRoleRela defUserRoleRela = new DefUserRoleRela();
					defUserRoleRela.setRoleId(defRoleList.get(0).getRoleId());
					defUserRoleRela.setUserId(defUser.getUserId());
					userService.saveSet(defUserRoleRela,loginInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("为用户分配角色出错");
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	/**
	 * 薪资计算
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo salaryCal(String calYear, String calMonth,LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		Map paramMap=new HashMap();
		//一个月结算的总金额
		Double wageSumMoney=0.0;
		//一月承保或成立的单子总金额
		Double wageSumSuccessMoney=0.0;
		//一个月犹撤总金额
		Double wageSumRollBackMoney=0.0;
		int wageCount=0;
		paramMap.put("calDate", (calYear+calMonth).toString());
		paramMap.put("calYear", calYear);
		paramMap.put("calMonth", calMonth);
		//1.先判断这个月的薪资是否已经结转如果结转则不允许计算 STATE=1 已结算 STATE=2 已结转
		AgentWageInfoExample agentWageInfoExample=new AgentWageInfoExample();
		agentWageInfoExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andCalMonthEqualTo(calMonth).andCalYearEqualTo(calYear).andStateEqualTo("2");
		List<AgentWageInfo>  forwardAgentWageInfo=agentWageInfoMapper.selectByExample(agentWageInfoExample);
		if(forwardAgentWageInfo!=null&&forwardAgentWageInfo.size()>0){
			log.info("-------1.判断这个月的薪资是否已经结转--------");
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该月薪资已经结转，不能再进行结算或结转");
			return resultInfo;
		}
		//2.判断这个月的薪资是否已经结算过，如果结算过将这个月的薪资明细数据逻辑删除除掉后，重新结算
		try {
			List<Map> salaryAgentWageInfos=salesServiceMapper.getMaxAgentWageInfoList(paramMap);
			if(salaryAgentWageInfos.size()>0){
			log.info("-------2.判断断这个月的薪资是否已经结算过,如已经结束则进行薪资数据表信息删除--------");
			wageCount= Integer.parseInt(salaryAgentWageInfos.get(0).get("wageCount").toString());
			String agentWageInfoId=salaryAgentWageInfos.get(0).get("agentWageInfoId").toString();
			//逻辑删除薪资历史表
			agentWageInfoExample=new AgentWageInfoExample();
			agentWageInfoExample.createCriteria().andAgentWageInfoIdEqualTo(new Long(agentWageInfoId));
			AgentWageInfo agentWageInfo=(AgentWageInfo)agentWageInfoMapper.selectByExample(agentWageInfoExample).get(0);
			com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(agentWageInfo, loginInfo);
			agentWageInfoMapper.updateByPrimaryKey(agentWageInfo);
			//逻辑删除薪资明细表
			AgentWageExample agentWageExample=new AgentWageExample();
			agentWageExample.createCriteria().andAgentWageInfoIdEqualTo(new Long(agentWageInfoId));
			List<AgentWage> agentWageList=agentWageMapper.selectByExample(agentWageExample);
			if (agentWageList.size()>0) {
				for (AgentWage agentWage : agentWageList) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(agentWage, loginInfo);
					agentWageMapper.updateByPrimaryKey(agentWage);
				}
			 }
			}
			
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("对已结算的数据进行删除出错");
			return resultInfo;
		}
		//3.根据交易状态表查询出这个月所有保险产品承保的单子 tradeStatu=06和财富产品成立tradeStatus=10的单子，并插入薪资明细表t_AGENT_WAGE
		log.info("-------开始插入薪资计算信息汇总表--------");
		++wageCount;
		Long	newAgentWageInfoSeq = commonService.getSeqValByName("SEQ_T_AGENT_WAGE_INFO");
		AgentWageInfo agentWageInfo=new AgentWageInfo();
		agentWageInfo.setAgentWageInfoId(newAgentWageInfoSeq);
		agentWageInfo.setCalMonth(calMonth);
		agentWageInfo.setCalYear(calYear);
		agentWageInfo.setState("1");//1-结算
        try {
			agentWageInfo.setSettleDate(DateUtils.getDate(DateUtils.getCurrentDate()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(agentWageInfo, loginInfo);
		
		try {
			List<Map> getFoundStatusList = salesServiceMapper.getFoundStatusInfo(paramMap);
			List<Map> getRebackStatusList=salesServiceMapper.getRebackStatusInfo(paramMap);
			if (getFoundStatusList.size()<=0&&getRebackStatusList.size()<=0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该月不存在结算的单子");
				return resultInfo;
			}
			agentWageInfoMapper.insert(agentWageInfo);
			log.info("-------3.根据交易状态表查询出这个月所有保险产品承保的单子 tradeStatu=06和财富产品成立tradeStatus=10的单子，并插入薪资明细表t_AGENT_WAGE--------");
			log.info("-------1)查询出"+(calYear+calMonth).toString()+"月已经成立/承保的单子，并插入薪资明细表--------");
			if(getFoundStatusList.size()>0){
				for (Map map : getFoundStatusList) {
				TradeInfoExample tradeInfoExample=new TradeInfoExample();
				tradeInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(new Long(map.get("tradeInfoId").toString()));
				//查出这笔交易主表信息
				TradeInfo tradeInfo=(TradeInfo)tradeInfoMapper.selectByExample(tradeInfoExample).get(0);
				//查出这笔交易的产品信息
				TradeProductInfoExample  tradeProductInfoExample=new TradeProductInfoExample();
				tradeProductInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
				TradeProductInfo tradeProductInfo=(TradeProductInfo)tradeProductInfoMapper.selectByExample(tradeProductInfoExample).get(0);
				PDProductExample pdProductExample=new PDProductExample();
				pdProductExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(tradeProductInfo.getProductId());
				PDProduct pdProduct=(PDProduct)pdProductMapper.selectByExample(pdProductExample).get(0);
				//查出这笔交易对应的合作机构信息
				AgencyComExample agencyComExample=new AgencyComExample();
				agencyComExample.createCriteria().andRcStateEqualTo("E").andAgencyComIdEqualTo(pdProduct.getAgencyComId());
				AgencyCom agencyCom=(AgencyCom)agencyComMapper.selectByExample(agencyComExample).get(0);
				//查出这笔交易理财经理信息
				AgentExample agentExample=new AgentExample();
				agentExample.createCriteria().andAgentIdEqualTo(tradeInfo.getAgentId()).andRcStateEqualTo("E");
				Agent agent=(Agent)agentMapper.selectByExample(agentExample).get(0);
				//查出这笔交易所对应的客户信息
				TradeCustInfoExample tradeCustInfoExample=new TradeCustInfoExample();
				tradeCustInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
				TradeCustInfo tradeCustInfo =(TradeCustInfo)tradeCustInfoMapper.selectByExample(tradeCustInfoExample).get(0);
				//查出这笔交易状态维护的交易金额
				TradeStatusInfoExample tradeStatusInfoExample =new TradeStatusInfoExample();
				tradeStatusInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
				TradeStatusInfo tradeStatusInfo=(TradeStatusInfo)tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample).get(0);
				Long	tAgentWageSeq = commonService.getSeqValByName("SEQ_T_AGENT_WAGE");
				AgentWage agentWage=new AgentWage();
				//将财富产品的单子插入薪资明细表SEQ_T_AGENT_WAGE
				if(pdProduct.getProductType().equals("1")){
					//获得奖金
					resultInfo=calSalaryService.calSalByTrade(tradeInfo.getTradeInfoId().toString(), tradeStatusInfo.getActuSubscriptionAmount(), pdProduct,calYear,calMonth);
					if(resultInfo.isSuccess()){
						BigDecimal wageValue=new BigDecimal(resultInfo.getObj().toString());
						agentWage.setWageValue(wageValue);//薪资金额-奖金金额
						wageSumSuccessMoney=wageSumSuccessMoney+wageValue.doubleValue();
						log.info("wageSumSuccessMoney:"+wageSumSuccessMoney);
					}
					else{
						return resultInfo;
					}
					agentWage.setAgentWageCalId(tAgentWageSeq);//薪资计算流水号
					agentWage.setAgentWageInfoId(newAgentWageInfoSeq);//薪资结算信息汇总表关联ID
					agentWage.setTradeinfoId(tradeInfo.getTradeInfoId().toString());//交易号码
					agentWage.setBussNo(tradeInfo.getTradeNo());//业务号
					agentWage.setAgentId(tradeInfo.getAgentId());//财富顾问流水号
					agentWage.setTradeType(tradeInfo.getTradeType());//交易类别
					agentWage.setOccupationComCode(agencyCom.getAgencyCode());//合作机构代码
					agentWage.setOccupationComName(agencyCom.getAgencyName());//合作机构名称
					agentWage.setProductCode(pdProduct.getProductCode());//产品代码
					agentWage.setProductName(pdProduct.getProductName());//产品名称
					agentWage.setComId(agent.getComId());//所属机构
					agentWage.setStoreId(agent.getStoreId());//所属网点
					agentWage.setCustomerId(tradeCustInfo.getCustBaseInfoId());//客户Id
					agentWage.setTradeMoney(tradeStatusInfo.getActuSubscriptionAmount());//交易金额
					agentWage.setWageState("1");//薪资结算状态1-结算；2-结转
					agentWage.setTradeDate(tradeInfo.getTradeInputDate());//交易日期
					agentWage.setCalYear(calYear);//结算年
					agentWage.setCalMonth(calMonth);//结算月
					agentWage.setDepartmentId(agent.getDepartmentId());//团队
					agentWage.setWageCount(wageCount+"");//结算的次数
					agentWage.setSettleDate(DateUtils.getDate(DateUtils.getCurrentDate()));
					
					//获取标准资产
					resultInfo=calSalaryService.getStandAsset(tradeStatusInfo, pdProduct);
					if (resultInfo.isSuccess()) {
						agentWage.setStandardAsset(new BigDecimal(resultInfo.getObj().toString()));//标准资产
					}
					else {
						return resultInfo;
					}
					agentWage.setSettleStatus("10");//单子状态 10-成立
					//获取奖金比例
					resultInfo=calSalaryService.getBonusRate(tradeInfo.getTradeInfoId().toString(), pdProduct, calYear, calMonth);
					if (resultInfo.isSuccess()) {
						agentWage.setBonusRate(new BigDecimal(resultInfo.getObj().toString()));//奖金比例
					}
					else {
						return resultInfo;
					}
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(agentWage, loginInfo);
					agentWageMapper.insert(agentWage);
				}
				//将保险的单子插入薪资明细表SEQ_T_AGENT_WAGE
				else{
					resultInfo=calSalaryService.calSalByTrade(tradeInfo.getTradeInfoId().toString(), tradeStatusInfo.getActuPremium(), pdProduct,calYear,calMonth);
					if(resultInfo.isSuccess()){
						BigDecimal wageValue=new BigDecimal(resultInfo.getObj().toString());
						agentWage.setWageValue(wageValue);//薪资金额-奖金金额
						wageSumSuccessMoney=wageSumSuccessMoney+wageValue.doubleValue();
						log.info("wageSumSuccessMoney:"+wageSumSuccessMoney);
					}
					else{
						return resultInfo;
					}
					agentWage.setAgentWageCalId(tAgentWageSeq);//薪资计算流水号
					agentWage.setAgentWageInfoId(newAgentWageInfoSeq);//薪资计算信息关联Id
					agentWage.setTradeinfoId(tradeInfo.getTradeInfoId().toString());//交易号码
					agentWage.setBussNo(tradeInfo.getTradeNo());//业务号
					agentWage.setAgentId(tradeInfo.getAgentId());//财富顾问流水号
					agentWage.setTradeType(tradeInfo.getTradeType());//交易类别
					agentWage.setOccupationComCode(agencyCom.getAgencyCode());//合作机构代码
					agentWage.setOccupationComName(agencyCom.getAgencyName());//合作机构名称
					agentWage.setProductCode(pdProduct.getProductCode());//产品代码
					agentWage.setProductName(pdProduct.getProductName());//产品名称
					agentWage.setComId(agent.getComId());//所属机构
					agentWage.setStoreId(agent.getStoreId());//所属网点
					agentWage.setCustomerId(tradeCustInfo.getCustBaseInfoId());//客户Id
					agentWage.setTradeMoney(tradeStatusInfo.getActuPremium());//交易金额
					agentWage.setWageState("1");//薪资结算状态1-结算；2-结转
					agentWage.setTradeDate(tradeInfo.getTradeDate());//交易日期
					agentWage.setCalYear(calYear);//结算年
					agentWage.setCalMonth(calMonth);//结算月
					agentWage.setDepartmentId(agent.getDepartmentId());//团队
					agentWage.setWageCount(wageCount+"");//结算次数
//					agentWage.setForwardDate(DateUtils.getDate(DateUtils.getCurrentDate()));
					agentWage.setSettleDate(DateUtils.getDate(DateUtils.getCurrentDate()));
					
					//获取折标系数
					Map getScaleRateMap=new HashMap();
					getScaleRateMap.put("tradeInfoId", tradeInfo.getTradeInfoId().toString());
					getScaleRateMap.put("trueMoney", tradeStatusInfo.getActuPremium());
					List<Map> scaleRateList=calSalaryServiceMapper.getScaleFactor(getScaleRateMap);
					if (scaleRateList.size()>0) {
					agentWage.setScaleRate(new BigDecimal(scaleRateList.get(0).get("scaleFactor").toString()));//获取折标系数
					}
					//标准保费
					resultInfo=calSalaryService.getStandrdPrem(tradeInfo.getTradeInfoId().toString(), tradeStatusInfo.getActuPremium(), pdProduct);
					if (resultInfo.isSuccess()) {
						agentWage.setStandardPrem(new BigDecimal(resultInfo.getObj().toString()));	
					}
					else {
						return resultInfo;
					}
					//标准资产
					resultInfo=calSalaryService.getStandAsset(tradeStatusInfo, pdProduct);
					if (resultInfo.isSuccess()) {
						agentWage.setStandardAsset(new BigDecimal(resultInfo.getObj().toString()));//标准资产
					}
					else {
						return resultInfo;
					}
					agentWage.setSettleStatus("06");//业务状态 06-承保
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(agentWage, loginInfo);
					agentWageMapper.insert(agentWage);
				}
				}
			}
			log.info("-------2.查询出"+(calYear+calMonth).toString()+"月犹撤的并且已经单子，并插入薪资明细--------");
		    if (getRebackStatusList.size()>0) {
		    	for (Map map : getRebackStatusList) {
					TradeInfoExample tradeInfoExample=new TradeInfoExample();
					tradeInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(new Long(map.get("tradeInfoId").toString()));
					//查出这笔交易主表信息
					TradeInfo tradeInfo=(TradeInfo)tradeInfoMapper.selectByExample(tradeInfoExample).get(0);
					//查出这笔交易的产品信息
					TradeProductInfoExample  tradeProductInfoExample=new TradeProductInfoExample();
					tradeProductInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
					TradeProductInfo tradeProductInfo=(TradeProductInfo)tradeProductInfoMapper.selectByExample(tradeProductInfoExample).get(0);
					PDProductExample pdProductExample=new PDProductExample();
					pdProductExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(tradeProductInfo.getProductId());
					PDProduct pdProduct=(PDProduct)pdProductMapper.selectByExample(pdProductExample).get(0);
					//查出这笔交易对应的合作机构信息
					AgencyComExample agencyComExample=new AgencyComExample();
					agencyComExample.createCriteria().andRcStateEqualTo("E").andAgencyComIdEqualTo(pdProduct.getAgencyComId());
					AgencyCom agencyCom=(AgencyCom)agencyComMapper.selectByExample(agencyComExample).get(0);
					//查出这笔交易理财经理信息
					AgentExample agentExample=new AgentExample();
					agentExample.createCriteria().andAgentIdEqualTo(tradeInfo.getAgentId()).andRcStateEqualTo("E");
					Agent agent=(Agent)agentMapper.selectByExample(agentExample).get(0);
					//查出这笔交易所对应的客户信息
					TradeCustInfoExample tradeCustInfoExample=new TradeCustInfoExample();
					tradeCustInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
					TradeCustInfo tradeCustInfo =(TradeCustInfo)tradeCustInfoMapper.selectByExample(tradeCustInfoExample).get(0);
					//查出这笔交易状态维护的交易金额
					TradeStatusInfoExample tradeStatusInfoExample =new TradeStatusInfoExample();
					tradeStatusInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
					TradeStatusInfo tradeStatusInfo=(TradeStatusInfo)tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample).get(0);
					//将承保/成立的单子插入薪资明细表SEQ_T_AGENT_WAGE
					Long	tAgentWageSeq = commonService.getSeqValByName("SEQ_T_AGENT_WAGE");
					AgentWage agentWage=new AgentWage();
					agentWage.setAgentWageCalId(tAgentWageSeq);//薪资计算流水号
					agentWage.setAgentWageInfoId(newAgentWageInfoSeq);//薪资信息管理表ID
					agentWage.setTradeinfoId(tradeInfo.getTradeInfoId().toString());//交易号码
					agentWage.setBussNo(tradeInfo.getTradeNo());//业务号
					agentWage.setAgentId(tradeInfo.getAgentId());//财富顾问流水号
					agentWage.setTradeType(tradeInfo.getTradeType());//交易类别
					agentWage.setOccupationComCode(agencyCom.getAgencyCode());//合作机构代码
					agentWage.setOccupationComName(agencyCom.getAgencyName());//合作机构名称
					agentWage.setProductCode(pdProduct.getProductCode());//产品代码
					agentWage.setProductName(pdProduct.getProductName());//产品名称
					agentWage.setComId(agent.getComId());//所属机构
					agentWage.setStoreId(agent.getStoreId());//所属网点
					agentWage.setCustomerId(tradeCustInfo.getCustBaseInfoId());//客户Id
					agentWage.setTradeMoney(tradeStatusInfo.getActuPremium());//交易金额
					agentWage.setWageCount(wageCount+"");
					agentWage.setSettleDate(DateUtils.getDate(DateUtils.getCurrentDate()));
					resultInfo=calSalaryService.getRollBackBonos(tradeInfo, pdProduct, agent, tradeStatusInfo);
					if (resultInfo.isSuccess()) {
						agentWage.setWageValue(new BigDecimal("-"+resultInfo.getObj().toString()));	//回退的金额
						wageSumRollBackMoney=wageSumRollBackMoney+new Long(resultInfo.getObj().toString()).doubleValue();
					}
					else {
						return resultInfo;
					}
					agentWage.setWageState("1");//薪资结算状态1-结算；2-结转
					agentWage.setTradeDate(tradeInfo.getTradeDate());//交易日期
					agentWage.setCalYear(calYear);//结算年
					agentWage.setCalMonth(calMonth);//结算月
					agentWage.setSettleStatus("07");//业务状态 07-犹撤
					agentWage.setDepartmentId(agent.getDepartmentId());//团队Id
					agentWage.setWageCount(wageCount+"");
					//标准保费
					if(pdProduct.getProductType().equals("2")&&pdProduct.getProductSubType().equals("01")){
						resultInfo=calSalaryService.getStandrdPrem(tradeInfo.getTradeInfoId().toString(), tradeStatusInfo.getActuPremium(), pdProduct);
						if (resultInfo.isSuccess()) {
							agentWage.setStandardPrem(new BigDecimal(resultInfo.getObj().toString()));
						}
						else {
							return resultInfo;
						}
					}
					//银行标准保费
					if(pdProduct.getProductType().equals("2")&&pdProduct.getProductSubType().equals("03")){
						agentWage.setStandardPrem(tradeStatusInfo.getActuPremium());//银保标准保费
					}
					//标准资产
					resultInfo=calSalaryService.getStandAsset(tradeStatusInfo, pdProduct);
					if (resultInfo.isSuccess()) {
						agentWage.setStandardAsset(new BigDecimal(resultInfo.getObj().toString()));//标准资产
					}
					else {
						return resultInfo;
					}
					//获取折标系数
					Map scaleFactorMap=new HashMap();
					scaleFactorMap.put("tradeInfoId", tradeInfo.getTradeInfoId().toString());
					scaleFactorMap.put("trueMoney", "tradeStatusInfo.getActuPremium()");
					List<Map> scaleRateList=calSalaryServiceMapper.getScaleFactor(scaleFactorMap);
					if (scaleRateList.size()>0) {
						agentWage.setScaleRate(new  BigDecimal(scaleRateList.get(0).get("scaleFactor").toString()));
						
					}
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(agentWage, loginInfo);
					agentWageMapper.insert(agentWage);
				}
			}
			log.info("-------3.计算出"+(calYear+calMonth).toString()+"月需要结算的奖金总额--------");
			//本月结算的奖金总金额为：sum(承保的总奖金)-sum(犹退单子的总奖金)
			wageSumMoney=wageSumSuccessMoney-wageSumRollBackMoney;
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("承保/成立的单子保存薪资明细表出错");
			return resultInfo;
			
		}
		//4.薪资计算历史表t_AGENT_WAGE_HISTOTY——计算总薪资
		try {
		  log.info("-------更新插入薪资计算信息汇总表--------");
		  agentWageInfo.setWageSumMoney(new BigDecimal(wageSumMoney));
		  agentWageInfo.setWageCount(wageCount+"");
		  agentWageInfoMapper.updateByPrimaryKey(agentWageInfo);
		} catch (Exception e) {
			
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新薪资历史表出错");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("结算成功");
		return resultInfo;
	}

	/**
	 * 薪资结转
	 */
	@Transactional
	public ResultInfo salaryForward(String calYear, String calMonth,LoginInfo loginInfo) {
		
	ResultInfo resultInfo=new ResultInfo();
	if (calYear!=null&&calMonth!=null) {
	//1.判断该月是否已经结转
	AgentWageInfoExample agentWageForwardExample=new AgentWageInfoExample();
	agentWageForwardExample.createCriteria().andRcStateEqualTo("E").andCalYearEqualTo(calYear).andCalMonthEqualTo(calMonth).andStateEqualTo("2");
	List<AgentWageInfo> agentWageForwardList= agentWageInfoMapper.selectByExample(agentWageForwardExample);
			if (agentWageForwardList.size() > 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该月薪资已经结转,不能再次进行结转");
				return resultInfo;
			} else {
				//2.找出已经结算的这条数据-AgentWageHistory
				AgentWageInfoExample agentWageCalExample=new AgentWageInfoExample();
				agentWageCalExample.createCriteria().andRcStateEqualTo("E").andCalYearEqualTo(calYear).andCalMonthEqualTo(calMonth).andStateEqualTo("1");
				List<AgentWageInfo> agentWageSalaryList= agentWageInfoMapper.selectByExample(agentWageCalExample);
				if(agentWageSalaryList.size()>0){
				AgentWageInfo agentWageInfo=(AgentWageInfo)agentWageSalaryList.get(0);	
				agentWageInfo.setState("2");//结转
				Map<String, String> paramMap=new HashMap<String,String>();
				paramMap.put("calYear", calYear);
				paramMap.put("calMonth", calMonth);
				paramMap.put("wageCount", agentWageInfo.getWageCount());
				paramMap.put("fowarddate", DateUtils.getCurrentDate());
				//3.找出已经结算的薪资明细表-更新状态AgentWage
		        salesServiceMapper.updateAgentWage(paramMap);
				try {
					agentWageInfo.setForwardDate(DateUtils.getDate(DateUtils.getCurrentDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(agentWageInfo, loginInfo);
				agentWageInfoMapper.updateByPrimaryKey(agentWageInfo);	
				}
			}
	}
	else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("薪资结算年月不能为空");
		return  resultInfo;
	}
	    resultInfo.setSuccess(true);
	    resultInfo.setMsg("薪资结转成功");
		return resultInfo;
	}

	/**
	 * 获取薪资管理页面查询信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getSalaryCalList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.salaryQueryListCount(paramMap);
		List<Map> resultList = salesServiceMapper.salaryQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;	
	}

	/**
	 * 获取薪资查询页面查询信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getAgentWageList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.agentWageListCount(paramMap);
		List<Map> resultList = salesServiceMapper.agentWageListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;	
	}

	
	/**
	 * 薪资报表导出主方法
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResultInfo salaryCalExport(Map paramMap) {
		ResultInfo resultInfo =new ResultInfo();
//		//先判断导出的月份是否已经结算
//		AgentWageInfoExample agentWageInfoExample=new AgentWageInfoExample();
//		agentWageInfoExample.createCriteria().andRcStateEqualTo("E").andCalMonthEqualTo(paramMap.get("calMonth").toString()).andCalYearEqualTo(paramMap.get("calYear").toString()).andStateBetween("1", "2");
//		List<AgentWageInfo> agentWageInfos=agentWageInfoMapper.selectByExample(agentWageInfoExample);
//		if (agentWageInfos.size()<=0) {
//			resultInfo.setSuccess(false);
//			resultInfo.setMsg("该月尚未进行结算,请先进行结算");
//			return resultInfo;
//		}

		List<Map<String, Object>> reportList = new ArrayList<Map<String,Object>>();
		//3.对汇总报表进行结算
		ResultInfo allSalaryCalResultInfo=allSalaryCal(paramMap);
		if (allSalaryCalResultInfo.isSuccess()) {
			Map<String, Object> reportMap = commonService.getReportInfo("AllCalDetail");
			if (reportMap!=null&&reportMap.size()>0) {
				reportMap.put("reportData", allSalaryCalResultInfo.getObj());
				reportList.add(reportMap);
			}
		}
		else{
			allSalaryCalResultInfo.setSuccess(false);
			allSalaryCalResultInfo.setMsg("薪资结算汇总报表出错");
			return allSalaryCalResultInfo;
		}
		//1.对保险产品进行薪资结算
		ResultInfo riskSalaryCalResultInfo=riskSalaryCal(paramMap);
		if (riskSalaryCalResultInfo.isSuccess()) {
			Map<String, Object> reportMap = commonService.getReportInfo("RiskCalDetail");
			if (reportMap!=null&&reportMap.size()>0) {
				reportMap.put("reportData", riskSalaryCalResultInfo.getObj());
				reportList.add(reportMap);
			}
		}
		else {
			riskSalaryCalResultInfo.setSuccess(false);
			riskSalaryCalResultInfo.setMsg("保险产品薪资结算出错");
			return riskSalaryCalResultInfo;
			
		}
		//2.对财富产品进行结算
		ResultInfo wealthSalaryCalResultInfo=wealthSalaryCal(paramMap);
		if (wealthSalaryCalResultInfo.isSuccess()) {
			Map<String, Object> reportMap = commonService.getReportInfo("WealthCalDetail");
			if (reportMap!=null&&reportMap.size()>0) {
				reportMap.put("reportData", wealthSalaryCalResultInfo.getObj());
				reportList.add(reportMap);
			}
		}
		else{
			wealthSalaryCalResultInfo.setSuccess(false);
			wealthSalaryCalResultInfo.setMsg("财富产品薪资结算出错");
			return wealthSalaryCalResultInfo;
		}

		resultInfo.setSuccess(true);
		resultInfo.setObj(reportList);
		return resultInfo;
	}
	
	
	/**
	 * 薪资结算汇总报表处理
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo allSalaryCal(Map paramMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map<String, String>> allCalSucResultList = new ArrayList<Map<String,String>>();
//			List<Map<String, String>> allCalFailResultList = new ArrayList<Map<String,String>>();
			List<Map> allSalaryCalList = salesServiceMapper.getAllCalInfoList(paramMap);
			log.info("=======保险交易薪资结算开始++++++++");
			for (Map allSalaryCalInfoMap:allSalaryCalList) {
				Map<String, String> resultMap = new HashMap<String, String>();
				String calYear=allSalaryCalInfoMap.get("calYear").toString();
				String calMonth=allSalaryCalInfoMap.get("calMonth").toString();
				String agentId=allSalaryCalInfoMap.get("agentId").toString();
				AgentDepartmentExample agentDepartmentExample=new AgentDepartmentExample();
				agentDepartmentExample.createCriteria().andRcStateEqualTo("E").andAgentIdEqualTo(new Long(agentId)).andIsleaderEqualTo("Y");
				List<AgentDepartment> agentDepartments=agentDepartmentMapper.selectByExample(agentDepartmentExample);
				if (agentDepartments.size()>0) {
					ResultInfo manageBonusResultInfo = calSalaryService.getsumManageBonus(agentId, agentDepartments.get(0).getDepartmentId().toString(),calYear, calMonth);	
					if (manageBonusResultInfo.isSuccess()) {
						resultMap.put("sumManageBonus", manageBonusResultInfo.getObj().toString());
					}
					resultMap.putAll(allSalaryCalInfoMap);
				}
				else {
					resultMap.putAll(allSalaryCalInfoMap);
				}
				allCalSucResultList.add(resultMap);
//				//获取管理奖金
//				ResultInfo manageBonusResultInfo = calSalaryService.getsumManageBonus(agentId, agentDepartments.get(0).getDepartmentId().toString(),calYear, calMonth);
//				if(manageBonusResultInfo.isSuccess()){
//					Map<String, String> resultMap = new HashMap<String, String>();
//					resultMap.putAll(allSalaryCalInfoMap);
////					resultMap.put("sumManageBonus",manageBonusResultInfo.getObj().toString() );//管理奖金
//					allCalSucResultList.add(resultMap);
//				}else {
//					allCalFailResultList.add(allSalaryCalInfoMap);
//					log.info("交易("+allSalaryCalInfoMap.get("tradeInfoId")+")结算管理奖金出错："+manageBonusResultInfo.getMsg());
//					continue;
//				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(allCalSucResultList);
			log.info("=======汇总列表薪资计算完成++++++++");
//			log.info("=======汇总列表薪资计算失败(共"+allCalFailResultList.size()+"笔)的交易如下++++++++");
//			log.info(JsonUtils.objectToJsonStr(allCalFailResultList));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("汇总列表薪资结算出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 薪资结算保险明细报表处理
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo riskSalaryCal(Map paramMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map<String, String>> salaryCalSucResultList = new ArrayList<Map<String,String>>();
			List<Map<String, String>> salaryCalFailResultList = new ArrayList<Map<String,String>>();
			List<Map> riskSalaryCalList = salesServiceMapper.getSalaryCalRiskInfoList(paramMap);
			log.info("=======保险交易薪资结算开始++++++++");
			for (Map riskSalaryCalInfoMap:riskSalaryCalList) {
				String tradeInfoId=riskSalaryCalInfoMap.get("tradeInfoId").toString();
				String calYear=riskSalaryCalInfoMap.get("calYear").toString();
				String calMonth=riskSalaryCalInfoMap.get("calMonth").toString();
				Long productId=new Long(riskSalaryCalInfoMap.get("productId").toString());
				BigDecimal actulPrem=new BigDecimal(riskSalaryCalInfoMap.get("actuPremium").toString());
				PDProductExample pdProductExample=new PDProductExample();
				pdProductExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(productId);
				PDProduct pdProduct=(PDProduct)pdProductMapper.selectByExample(pdProductExample).get(0);
				//获取奖金比例
				ResultInfo bonusRateResultInfo = calSalaryService.getBonusRate(tradeInfoId, pdProduct, calYear, calMonth);
				//获取折标系数
				ResultInfo scaleFatorResultInfo=calSalaryService.getScaleFactor(tradeInfoId, actulPrem);
				
				if(bonusRateResultInfo.isSuccess()&&scaleFatorResultInfo.isSuccess()){
					Map<String, String> resultMap = new HashMap<String, String>();
					resultMap.putAll(riskSalaryCalInfoMap);
					resultMap.put("bonusRate",bonusRateResultInfo.getObj().toString() );//奖金比例
					resultMap.put("scaleFactor", scaleFatorResultInfo.getObj().toString());//折标系数
					salaryCalSucResultList.add(resultMap);
				}else {
					salaryCalFailResultList.add(riskSalaryCalInfoMap);
					log.info("交易("+riskSalaryCalInfoMap.get("tradeInfoId")+")获取奖金比例出错："+bonusRateResultInfo.getMsg());
					log.info("交易("+riskSalaryCalInfoMap.get("tradeInfoId")+")结算折标系数出错："+scaleFatorResultInfo.getMsg());
					continue;
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(salaryCalSucResultList);
			log.info("=======保险薪资计算完成++++++++");
			log.info("=======保险薪资计算失败(共"+salaryCalFailResultList.size()+"笔)的交易如下++++++++");
			log.info(JsonUtils.objectToJsonStr(salaryCalFailResultList));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保险薪资结算出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 *  薪资结算财富明细报表处理
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo wealthSalaryCal(Map paramMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map<String, String>> salaryCalSucResultList = new ArrayList<Map<String,String>>();
			List<Map<String, String>> salaryCalFailResultList = new ArrayList<Map<String,String>>();
			List<Map> wealthSalaryCalList = salesServiceMapper.getSalaryCalWealthInfoList(paramMap);
			log.info("=======财富交易薪资结算开始++++++++");
			for (Map wealthSalaryCalInfoMap:wealthSalaryCalList) {
				String tradeInfoId=wealthSalaryCalInfoMap.get("tradeInfoId").toString();
				String calYear=wealthSalaryCalInfoMap.get("calYear").toString();
				String calMonth=wealthSalaryCalInfoMap.get("calMonth").toString();
				Long productId=new Long(wealthSalaryCalInfoMap.get("productId").toString());
				PDProductExample pdProductExample=new PDProductExample();
				pdProductExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(productId);
				PDProduct pdProduct=(PDProduct)pdProductMapper.selectByExample(pdProductExample).get(0);
				//获取奖金比例
				ResultInfo bonusRateResultInfo = calSalaryService.getBonusRate(tradeInfoId, pdProduct, calYear, calMonth);
				if(bonusRateResultInfo.isSuccess()){
					Map<String, String> resultMap = new HashMap<String, String>();
					resultMap.putAll(wealthSalaryCalInfoMap);
					resultMap.put("bonusRate",bonusRateResultInfo.getObj().toString() );//奖金比例
					salaryCalSucResultList.add(resultMap);
				}else {
					salaryCalFailResultList.add(wealthSalaryCalInfoMap);
					log.info("交易("+wealthSalaryCalInfoMap.get("tradeInfoId")+")获取奖金比例出错："+bonusRateResultInfo.getMsg());
					continue;
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(salaryCalSucResultList);
			log.info("=======财富产品薪资计算完成++++++++");
			log.info("=======财富产品薪资计算失败(共"+salaryCalFailResultList.size()+"笔)的交易如下++++++++");
			log.info(JsonUtils.objectToJsonStr(salaryCalFailResultList));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("财富产品薪资结算出现异常！");
		}
		return resultInfo;
	}

		
/*************************考核查询**************************************************************************	
	/**
	 * 考核报表导出主方法
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResultInfo assessCalExport(Map paramMap) {
		ResultInfo resultInfo =new ResultInfo();
		List<Map<String, Object>> reportList = new ArrayList<Map<String,Object>>();
		//3.对考核汇总报表进行结算
		ResultInfo allAssessCalResultInfo=allAssessCal(paramMap);
		if (allAssessCalResultInfo.isSuccess()) {
			Map<String, Object> reportMap = commonService.getReportInfo("AllAssessDetail");
			if (reportMap!=null&&reportMap.size()>0) {
				reportMap.put("reportData", allAssessCalResultInfo.getObj());
				reportList.add(reportMap);
			}
		}
		else{
			allAssessCalResultInfo.setSuccess(false);
			allAssessCalResultInfo.setMsg("考核结算汇总报表出错");
			return allAssessCalResultInfo;
		}
		//1.对保险产品进行考核结算
		ResultInfo riskAssessCalResultInfo=riskAssessCal(paramMap);
		if (riskAssessCalResultInfo.isSuccess()) {
			Map<String, Object> reportMap = commonService.getReportInfo("RiskAssessDetail");
			if (reportMap!=null&&reportMap.size()>0) {
				reportMap.put("reportData", riskAssessCalResultInfo.getObj());
				reportList.add(reportMap);
			}
		}
		else {
			riskAssessCalResultInfo.setSuccess(false);
			riskAssessCalResultInfo.setMsg("保险产品考核结算出错");
			return riskAssessCalResultInfo;
			
		}
		//2.对财富产品进行考核结算
		ResultInfo wealthAssessCalResultInfo=wealthAssessCal(paramMap);
		if (wealthAssessCalResultInfo.isSuccess()) {
			Map<String, Object> reportMap = commonService.getReportInfo("WealthAssessDetail");
			if (reportMap!=null&&reportMap.size()>0) {
				reportMap.put("reportData", wealthAssessCalResultInfo.getObj());
				reportList.add(reportMap);
			}
		}
		else{
			wealthAssessCalResultInfo.setSuccess(false);
			wealthAssessCalResultInfo.setMsg("财富产品考核结算出错");
			return wealthAssessCalResultInfo;
		}

		resultInfo.setSuccess(true);
		resultInfo.setObj(reportList);
		return resultInfo;
	}
	
	
	/**
	 * 考核汇总报表获取相关参数
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo allAssessCal(Map paramMap){
		log.info("=======保险交易考核结算开始++++++++");
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map<String, String>> allCalSucResultList = new ArrayList<Map<String,String>>();
			if (paramMap.get("assessYear")!=null&&paramMap.get("assessMonth")!=null) {
				paramMap.put("assessYear", paramMap.get("assessYear").toString());
				paramMap.put("assessMonth", paramMap.get("assessMonth").toString());
//				paramMap.put("assessUnit", paramMap.get("assessUnit").toString());
			}
			//查询出符合提交的所有产品交易
			List<Map> riskAssessTradeIdList = salesServiceMapper.getAllAssessDetailInfoList(paramMap);
			if (riskAssessTradeIdList.size()>0) {
				for (Map AllAssessDatailMap : riskAssessTradeIdList) {
				 	if (paramMap.get("assessYear")!=null) {
				 		AllAssessDatailMap.put("assessYear", paramMap.get("assessYear"));
					}
				 	if (paramMap.get("assessMonth")!=null) {
				 		AllAssessDatailMap.put("assessMonth",paramMap.get("assessMonth"));
					}
				 	AllAssessDatailMap.put("assessUnit", "S");
				 	allCalSucResultList.add(AllAssessDatailMap);
				}
				
				resultInfo.setSuccess(true);
				resultInfo.setObj(allCalSucResultList);
				return resultInfo;
			}
			else {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("考核结算汇总信息为空");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("考核汇总结算出现异常！");
		}
		return resultInfo;
	}
	
	
	/**
	 * 考核保险明细报表获取相关参数
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo riskAssessCal(Map paramMap){
		log.info("=======保险交易考核结算开始++++++++");
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map<String, String>> allCalSucResultList = new ArrayList<Map<String,String>>();
			//查询出符合提交的所有产品交易
			List<Map> riskAssessTradeIdList = salesServiceMapper.getRiskAssessTradeList(paramMap);
			if (riskAssessTradeIdList.size()>0) {
				for (Map map : riskAssessTradeIdList) {
					Map   tradeMap=new HashMap();
					tradeMap.put("tradeInfoId", map.get("tradeInfoId").toString());
				 	Map riskAssessDatailMap= salesServiceMapper.getRiskAssessDetailInfoList(tradeMap).get(0);
				 	if (paramMap.get("startdate")!=null) {
				 		riskAssessDatailMap.put("assetsStartDate", paramMap.get("startdate"));
					}
				 	if (paramMap.get("enddate")!=null) {
				 		riskAssessDatailMap.put("assetsEndDate",paramMap.get("enddate"));
					}
				 	//获取折标系数
				 	 resultInfo=calSalaryService.getScaleFactor(map.get("tradeInfoId").toString(), new BigDecimal(map.get("actuPrem").toString()));
				 	if (resultInfo.isSuccess()) {
				 		riskAssessDatailMap.put("scaleFactor", resultInfo.getObj());
					}
				 	else {
				 		riskAssessDatailMap.put("scaleFactor", resultInfo.getObj());
					}
				 	//获取久期-产品缴费年期
                    List<Map> getProductDeclineList=salesServiceMapper.getProductDeclineList(map);
				 	if (getProductDeclineList.size()>0) {
				 		riskAssessDatailMap.put("duration", getProductDeclineList.get(0).get("payperiod").toString()+getProductDeclineList.get(0).get("payperiodUnit").toString());
				 		//获取久期系数
					 	if (getProductDeclineList.get(0).get("payperiodUnit").equals("Y")||getProductDeclineList.get(0).get("payperiodUnit").equals("0")) {
					        ResultInfo durationFactorResultInfo= calSalaryService.getdurationFactor(map.get("tradeInfoId").toString(), getProductDeclineList.get(0).get("payperiod").toString(),getProductDeclineList.get(0).get("payperiodUnit").toString());
					 		riskAssessDatailMap.put("durationFactor", durationFactorResultInfo.getObj());
						}
					 	else {
					 		riskAssessDatailMap.put("durationFactor", "");
						}
					}
				 	else {
				 		riskAssessDatailMap.put("duration", "");
				 		riskAssessDatailMap.put("durationFactor", "");
					}
				 	allCalSucResultList.add(riskAssessDatailMap);
				}
				resultInfo.setSuccess(true);
				resultInfo.setObj(allCalSucResultList);
				return resultInfo;
			}
			else {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("保险考核条数为空");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保险明细考核结算出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 *考核财富明细报表获取相关参数
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultInfo wealthAssessCal(Map paramMap){
		log.info("=======财富产品考核结算开始++++++++");
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<Map<String, String>> allCalSucResultList = new ArrayList<Map<String,String>>();
			List<Map> wealthAssessTradeIdList = salesServiceMapper.getWeathAssessTradeList(paramMap);
			if (wealthAssessTradeIdList.size()>0) {
				for (Map map : wealthAssessTradeIdList) {
					Map   tradeMap=new HashMap();
					tradeMap.put("tradeInfoId", map.get("tradeInfoId").toString());
				 	Map wealthAssessDatailMap= salesServiceMapper.getWealthAssessDetailInfoList(tradeMap).get(0);
				 	if (paramMap.get("startdate")!=null) {
				 		wealthAssessDatailMap.put("assetsStartDate", paramMap.get("startdate"));
					}
				 	if (paramMap.get("enddate")!=null) { 
				 		wealthAssessDatailMap.put("assetsEndDate",paramMap.get("enddate"));
					}    
				 	//获取财富产品-久期
				 	TradeProductInfoExample tradeProductInfoExample=new TradeProductInfoExample();
				 	tradeProductInfoExample.createCriteria().andTradeInfoIdEqualTo(new Long(map.get("tradeInfoId").toString())).andRcStateEqualTo("E");
				 	List<TradeProductInfo> tradeProductInfoList=tradeProductInfoMapper.selectByExample(tradeProductInfoExample);
				 	PDWealth pdWealth=null;
				 	if (tradeProductInfoList.size()>0) {
				 		TradeProductInfo tradeProductInfo=(TradeProductInfo)tradeProductInfoList.get(0);
						PDWealthExample pdWealthExample=new PDWealthExample();
						pdWealthExample.createCriteria().andProductIdEqualTo(tradeProductInfo.getProductId()).andRcStateEqualTo("E");
						List<PDWealth> pdWealthList=pdWealthMapper.selectByExample(pdWealthExample);
						if (pdWealthList.size()>0) {
							pdWealth=(PDWealth)pdWealthList.get(0);
						}
					}
				 	wealthAssessDatailMap.put("duration", pdWealth.getCloseDperiod().toString()+pdWealth.getCloseDperiodUnit().toString());
				 	ResultInfo productDedlineResultInfo=calSalaryService.getProductDedline(pdWealth.getProductId().toString());
				 	if (productDedlineResultInfo.isSuccess()) {
				 	 String  payperiod=productDedlineResultInfo.getObj().toString();
				 	//获取财富产品-久期系数
					 resultInfo=calSalaryService.getdurationFactor( map.get("tradeInfoId").toString(), payperiod, null);
					 if (resultInfo.isSuccess()&&resultInfo.getObj()!=null) {
						 wealthAssessDatailMap.put("durationFactor", resultInfo.getObj().toString());
					   }
					 else {
						 wealthAssessDatailMap.put("durationFactor", "");
					  }
					}
				 	//将每个单子的报表信息放到List
				 	allCalSucResultList.add(wealthAssessDatailMap);
				}
				resultInfo.setSuccess(true);
				resultInfo.setObj(allCalSucResultList);
				return resultInfo;
			}
			else {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("保险考核条数为空");
				return resultInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保险明细考核结算出现异常！");
		}
		return resultInfo;
	}

	
	/*************************考核管理*************************************************************************
	/**
	 * 考核预警计算
	 */
	@SuppressWarnings({ "rawtypes",  "unchecked" })
	@Transactional
	public ResultInfo assessCal(String assessCalYear,String assessCalMonth,String assessCalUnit,LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		Map paramMap=new HashMap();
		//一个月结算薪增的总资产
		Double assessSumStandAssets=0.0;
		//预警次数
	    int assessCount=0;
		paramMap.put("assessCalYear", assessCalYear);
		paramMap.put("assessCalMonth", assessCalMonth);
		paramMap.put("assessCalUnit", assessCalUnit);
		
		//1.先判断这个时间段的考核是否已经结转如果结转则不允许在做预警 STATE=1 考核预警  STATE=2 考核结转
		AgentAssessInfoExample agentAssessInfoExample=new AgentAssessInfoExample();
		agentAssessInfoExample.createCriteria().andRcStateEqualTo("E").andAssessYearEqualTo(assessCalYear).
		andAssessMonthEqualTo(assessCalMonth).andAssessYearEqualTo(assessCalYear).andAssessUnitEqualTo(assessCalUnit).andAssessStatusEqualTo("2");
		List<AgentAssessInfo>  forwardAgentAssessInfo=agentAssessInfoMapper.selectByExample(agentAssessInfoExample);
		if(forwardAgentAssessInfo!=null&&forwardAgentAssessInfo.size()>0){
			log.info("-------1.判断这个月的预警是否已经结转--------");
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该月考核已经结转，不能再进行核预警或考核结转");
			return resultInfo;
		}
		//2.判断这个月是否已经考核预警，如已预警逻辑删除除掉后，重新结算
		try {
			List<Map> agentAssessInfos=salesServiceMapper.getMaxAgentAssessInfoList(paramMap);
			if(agentAssessInfos!=null&&agentAssessInfos.size()>0){
			log.info("-------2.判断断这个月的考核是否已经预警过,如已经预警则进行考核数据表信息的逻辑删除--------");
			assessCount= Integer.parseInt(agentAssessInfos.get(0).get("assessCount").toString());
			String agentAssessInfoId=agentAssessInfos.get(0).get("agentAssessInfoId").toString();
			//逻辑删除历史表
			agentAssessInfoExample=new AgentAssessInfoExample();
			agentAssessInfoExample.createCriteria().andAgentAssessInfoIdEqualTo(new Long(agentAssessInfoId));
			AgentAssessInfo agentAssessInfo=(AgentAssessInfo)agentAssessInfoMapper.selectByExample(agentAssessInfoExample).get(0);
			com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(agentAssessInfo, loginInfo);
			agentAssessInfoMapper.updateByPrimaryKey(agentAssessInfo);
			//逻辑删除考核明细表
			AgentAssessExample agentAssessExample=new AgentAssessExample();
			agentAssessExample.createCriteria().andAgentAssessInfoIdEqualTo(new Long(agentAssessInfoId));
			List<AgentAssess> agentAssessList=agentAssessMapper.selectByExample(agentAssessExample);
			if (agentAssessList.size()>0) {
				for (AgentAssess agentAssess : agentAssessList) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(agentAssess, loginInfo);
					agentAssessMapper.updateByPrimaryKey(agentAssess);
				}
			 }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("对已考核预警的数据进行逻辑删除出错");
			return resultInfo;
		}
		//3.根据交易状态表查询出这个月所有保险产品承保的单子 tradeStatu=06和财富产品成立tradeStatus=10的单子，并插入考核明细表t_agent_assess
		log.info("-------开始插入考核计算信息汇总表--------");
		Long	newAgentAssessInfoSeq = commonService.getSeqValByName("SEQ_T_AGENT_ASSESS_INFO");
		AgentAssessInfo agentAssessInfo=new AgentAssessInfo();
		agentAssessInfo.setAgentAssessInfoId(newAgentAssessInfoSeq);//考核信息流水号
		agentAssessInfo.setAssessYear(assessCalYear);//考核年份
		agentAssessInfo.setAssessMonth(assessCalMonth);//考核月份
		agentAssessInfo.setAssessUnit(assessCalUnit);//考核单位
		agentAssessInfo.setAssessStatus("1");//1-考核预警 2-考核结转
		agentAssessInfo.setAssessCount((++assessCount)+"");
		log.info("assessCount:"+assessCount);
        try {
        	agentAssessInfo.setAssessWarndate(DateUtils.getDate(DateUtils.getCurrentDate()));//考核预警日期
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(agentAssessInfo, loginInfo);
		agentAssessInfoMapper.insert(agentAssessInfo);
		try {
			List<Map> getFoundStatusList = salesServiceMapper.getAssessFoundStatusInfo(paramMap);//待调整
			if (getFoundStatusList.size()<=0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该季度不存在考核的单子");
				return resultInfo;
			}
			log.info("-------3.根据交易状态表查询出这个季度所有保险产品承保的单子 tradeStatu=06和财富产品成立tradeStatus=10的单子，并插入考核明细表t_AGENT_ASSESS----");
			if(getFoundStatusList.size()>0){
				for (Map map : getFoundStatusList) {
				TradeInfoExample tradeInfoExample=new TradeInfoExample();
				tradeInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(new Long(map.get("tradeInfoId").toString()));
				//查出这笔交易主表信息
				TradeInfo tradeInfo=(TradeInfo)tradeInfoMapper.selectByExample(tradeInfoExample).get(0);
				//查出这笔交易的产品信息
				TradeProductInfoExample  tradeProductInfoExample=new TradeProductInfoExample();
				tradeProductInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
				TradeProductInfo tradeProductInfo=(TradeProductInfo)tradeProductInfoMapper.selectByExample(tradeProductInfoExample).get(0);
				PDProductExample pdProductExample=new PDProductExample();
				pdProductExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(tradeProductInfo.getProductId());
				PDProduct pdProduct=(PDProduct)pdProductMapper.selectByExample(pdProductExample).get(0);
				//查出这笔交易对应的合作机构信息
				AgencyComExample agencyComExample=new AgencyComExample();
				agencyComExample.createCriteria().andRcStateEqualTo("E").andAgencyComIdEqualTo(pdProduct.getAgencyComId());
				AgencyCom agencyCom=(AgencyCom)agencyComMapper.selectByExample(agencyComExample).get(0);
				//查出这笔交易理财经理信息
				AgentExample agentExample=new AgentExample();
				agentExample.createCriteria().andAgentIdEqualTo(tradeInfo.getAgentId()).andRcStateEqualTo("E");
				Agent agent=(Agent)agentMapper.selectByExample(agentExample).get(0);
				//查出这笔交易所对应的客户信息
				TradeCustInfoExample tradeCustInfoExample=new TradeCustInfoExample();
				tradeCustInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
				TradeCustInfo tradeCustInfo =(TradeCustInfo)tradeCustInfoMapper.selectByExample(tradeCustInfoExample).get(0);
				//查出这笔交易状态维护的交易金额
				TradeStatusInfoExample tradeStatusInfoExample =new TradeStatusInfoExample();
				tradeStatusInfoExample.createCriteria().andRcStateEqualTo("E").andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId());
				TradeStatusInfo tradeStatusInfo=(TradeStatusInfo)tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample).get(0);
				
				Long	newAgentAssessSeq = commonService.getSeqValByName("SEQ_T_AGENT_ASSESS");
				AgentAssess agentAssess=new AgentAssess();
				//将财富产品的单子插入考核明细表SEQ_T_AGNT_ASSESS
				if(pdProduct.getProductType().equals("1")){
					agentAssess.setAgentAssessId(newAgentAssessSeq);
					agentAssess.setAgentAssessInfoId(newAgentAssessInfoSeq);
					agentAssess.setComId(agent.getComId());
					agentAssess.setDepartmentId(agent.getDepartmentId());
					agentAssess.setStoreId(agent.getStoreId());
					agentAssess.setAgentId(agent.getAgentId());
					agentAssess.setBussNo(tradeInfo.getTradeNo());
					agentAssess.setTradeinfoId(tradeInfo.getTradeInfoId().toString());
					agentAssess.setOccupationComCode(agencyCom.getAgencyCode());
					agentAssess.setOccupationComName(agencyCom.getAgencyName());
					agentAssess.setCustomerId(tradeCustInfo.getCustBaseInfoId());
					agentAssess.setProductCode(pdProduct.getProductCode());
					agentAssess.setProductName(pdProduct.getProductName());
					agentAssess.setTradeType(tradeInfo.getTradeType());
					agentAssess.setTradeDate(tradeInfo.getTradeDate());
						//固定成立日
					 if (pdProduct.getProductSubType().equals("02")){
						 PDWealthExample pdWealthExample=new PDWealthExample();
						 pdWealthExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(pdProduct.getProductId());
						 PDWealth pdWealth=(PDWealth)pdWealthMapper.selectByExample(pdWealthExample).get(0);
						 agentAssess.setFoundDate(pdWealth.getFoundDate());
					 }else {
						 paramMap.put("tradeInfoId", tradeInfo.getTradeInfoId().toString());
						 paramMap.put("productId", pdProduct.getProductId().toString());
						 //浮动股权成立日
						 String openDate=salesServiceMapper.getFoundDate(paramMap);
						 if (openDate!=null) {
							 agentAssess.setFoundDate(DateUtils.getDate(openDate));
						}
					  }
					agentAssess.setTradeMoney(tradeStatusInfo.getActuSubscriptionAmount());
					//获取财富产品的久期
					resultInfo=calSalaryService.getProductDedline(pdProduct.getProductId().toString());
					if (resultInfo.isSuccess()) {
						agentAssess.setDuration(resultInfo.getObj()+"Y");
						resultInfo=calSalaryService.getdurationFactor(tradeInfo.getTradeInfoId().toString(), resultInfo.getObj().toString(), "Y");
						if (resultInfo.isSuccess()) {
							agentAssess.setDurationRate(new BigDecimal(resultInfo.getObj().toString()));
						}
						else {
							resultInfo.setSuccess(false);
							resultInfo.setMsg("财富产品获取久期系数出错");
							return resultInfo;	
						}
					}
					else{
						resultInfo.setSuccess(false);
						resultInfo.setMsg("财富产品获取久期出错");
						return resultInfo;
					}
					resultInfo=calSalaryService.getStandAsset(tradeStatusInfo, pdProduct);
					if (resultInfo.isSuccess()) {
						   Double assessStandarValue=new Double(resultInfo.getObj().toString());
						   agentAssess.setStandardAsset(new BigDecimal(assessStandarValue));
						   assessSumStandAssets=assessSumStandAssets+assessStandarValue;
						  log.info("assessSumStandAssets:"+assessSumStandAssets);
						}
					else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("获取财富标准资产出错");
						return resultInfo;
					}
					agentAssess.setAssessStatus("1");//1-考核预警
//					agentAssess.setAssessFowarddate(DateUtils.getDate(DateUtils.getCurrentDate()));//预警日期
					agentAssess.setAssessWarndate(DateUtils.getDate(DateUtils.getCurrentDate()));//预警日期
					agentAssess.setAssessCount(assessCount+"");//考核次数
					agentAssess.setAssessYear(assessCalYear);
					agentAssess.setAssessMonth(assessCalMonth);
					agentAssess.setAssessUnit(assessCalUnit);
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(agentAssess, loginInfo);
					agentAssessMapper.insert(agentAssess);
				}
				//将保险的单子插入薪资明细表SEQ_T_AGENT_WAGE
				else{
					agentAssess.setAgentAssessId(newAgentAssessSeq);
					agentAssess.setAgentAssessInfoId(newAgentAssessInfoSeq);
					agentAssess.setComId(agent.getComId());
					agentAssess.setDepartmentId(agent.getDepartmentId());
					agentAssess.setStoreId(agent.getStoreId());
					agentAssess.setAgentId(agent.getAgentId());
					agentAssess.setBussNo(tradeInfo.getTradeNo());
					agentAssess.setTradeinfoId(tradeInfo.getTradeInfoId().toString());
					agentAssess.setOccupationComCode(agencyCom.getAgencyCode());
					agentAssess.setOccupationComName(agencyCom.getAgencyName());
					agentAssess.setCustomerId(tradeCustInfo.getCustBaseInfoId());
					agentAssess.setProductCode(pdProduct.getProductCode());
					agentAssess.setProductName(pdProduct.getProductName());
					agentAssess.setTradeType(tradeInfo.getTradeType());
					agentAssess.setTradeDate(tradeInfo.getTradeDate());
				    agentAssess.setFoundDate(tradeStatusInfo.getStatusDate());//承保日期
					agentAssess.setTradeMoney(tradeStatusInfo.getActuPremium());//实际保费
					//获取保险产品品的久期——缴费期限
					paramMap.put("tradeInfoId", tradeInfo.getTradeInfoId().toString());
					List<Map> productDeclineList=salesServiceMapper.getProductDeclineList(paramMap);
					
					if (productDeclineList!=null&&productDeclineList.size()>0) { 
						String payPeriod =productDeclineList.get(0).get("payperiod").toString();
						String payPeriodunit=productDeclineList.get(0).get("payperiodUnit").toString();
						agentAssess.setDuration(payPeriod+payPeriodunit);
						resultInfo=calSalaryService.getdurationFactor(tradeInfo.getTradeInfoId().toString(), payPeriod, payPeriodunit);
						if (resultInfo.isSuccess()) {
							agentAssess.setDurationRate(new BigDecimal(resultInfo.getObj().toString()));
						}
						else{
							resultInfo.setSuccess(false);
							resultInfo.setMsg("获取保险产品久期系数失败");
							return  resultInfo;
						}
					}
					//获取保险产品折标系数
					resultInfo=calSalaryService.getScaleFactor(tradeInfo.getTradeInfoId().toString(), tradeStatusInfo.getActuPremium());
					if (resultInfo.isSuccess()) {
						agentAssess.setScaleFactor(new  BigDecimal(resultInfo.getObj().toString()));
					}else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("获取保险产品折标系数失败");
						return  resultInfo;
					}
					resultInfo=calSalaryService.getStandAsset(tradeStatusInfo, pdProduct);
					if (resultInfo.isSuccess()) {
					   Double assessStandarValue=new Double(resultInfo.getObj().toString());
					   agentAssess.setStandardAsset(new BigDecimal(assessStandarValue));
					   assessSumStandAssets=assessSumStandAssets+assessStandarValue;
					  log.info("wageSumStandAssets:"+assessSumStandAssets);
					}
					else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("获取保险标准资产出错");
						return resultInfo;
					}
					agentAssess.setAssessStatus("1");//1-考核预警
//					agentAssess.setAssessFowarddate(DateUtils.getDate(DateUtils.getCurrentDate()));//预警日期
					agentAssess.setAssessWarndate(DateUtils.getDate(DateUtils.getCurrentDate()));//预警日期
					agentAssess.setAssessCount(assessCount+"");//考核预警次数
					agentAssess.setAssessYear(assessCalYear);
					agentAssess.setAssessMonth(assessCalMonth);
					agentAssess.setAssessUnit(assessCalUnit);
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(agentAssess, loginInfo);
					agentAssessMapper.insert(agentAssess);
				}
				}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("承保/成立的单子保存考核明细表出错");
			return resultInfo;
		}
		//4.考核计算历史表t_AGENT_ASSESS_INFO——计算总薪资
		try {
		  log.info("-------更新插入薪资计算信息汇总表--------");
		  agentAssessInfo.setAssessSumAssets(new BigDecimal(assessSumStandAssets));
		  agentAssessInfo.setAssessCount(assessCount+"");
		  agentAssessInfoMapper.updateByPrimaryKey(agentAssessInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新考核信息表出错");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("考核预警成功");
		return resultInfo;
	}
	
    /**
   * 考核结转
   */
	@Transactional
	public ResultInfo assessForward(String assessYear, String assessMonth,String assessUnit,LoginInfo loginInfo) {
	ResultInfo resultInfo=new ResultInfo();
	if (assessYear!=null&&assessMonth!=null&&assessUnit!=null) {
	//1.判断该月是否已经结转
	AgentAssessInfoExample agentAssessForwardExample=new AgentAssessInfoExample();
	agentAssessForwardExample.createCriteria().andRcStateEqualTo("E").andAssessYearEqualTo(assessYear).andAssessMonthEqualTo(assessMonth).andAssessUnitEqualTo(assessUnit).andAssessStatusEqualTo("2");
	List<AgentAssessInfo> agentAssessForwardList= agentAssessInfoMapper.selectByExample(agentAssessForwardExample);
			if (agentAssessForwardList.size() > 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该月考核已经结转,不能再次进行考核结转");
				return resultInfo;
			} else {
				//2.找出已经结算的这条数据-AgentAssessInfo
				AgentAssessInfoExample agentAssessCalExample=new AgentAssessInfoExample();
				agentAssessCalExample.createCriteria().andRcStateEqualTo("E").andAssessYearEqualTo(assessYear).andAssessMonthEqualTo(assessMonth).andAssessUnitEqualTo(assessUnit).andAssessStatusEqualTo("1");
				List<AgentAssessInfo> agentAssessCalList= agentAssessInfoMapper.selectByExample(agentAssessCalExample);
				if(agentAssessCalList.size()>0){
				AgentAssessInfo agentAssessInfo=(AgentAssessInfo)agentAssessCalList.get(0);	
				agentAssessInfo.setAssessStatus("2");//2-考核结转
				Map<String, String> paramMap=new HashMap<String,String>();
				paramMap.put("assessYear", assessYear.toString());
				paramMap.put("assessMonth", assessMonth.toString());
				paramMap.put("assessUnit", assessUnit.toString());
				paramMap.put("assessCount", agentAssessInfo.getAssessCount().toString());
				
				try {
					paramMap.put("assessFowarddate",DateUtils.getDateString(DateUtils.getDate(DateUtils.getCurrentDate())));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				//3.找出已经考核预警的的考核明细表-更新状态AgentAssess
		        salesServiceMapper.updateAgentAssess(paramMap);
				try {
					agentAssessInfo.setAssessFowarddate(DateUtils.getDate(DateUtils.getCurrentDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(agentAssessInfo, loginInfo);
				agentAssessInfoMapper.updateByPrimaryKey(agentAssessInfo);	
				}
			}
	}
	    resultInfo.setSuccess(true);
	    resultInfo.setMsg("考核结转成功");
		return resultInfo;
	}
	
	
	
	/**
	 * 获取考核管理预警页面查询信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getAssessCalList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.AssessCalQueryListCount(paramMap);
		List<Map> resultList = salesServiceMapper.AssessCalQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;	
	}
	
	
	/**
	 * 获取考核查询页面查询信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid getAgentAssessList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.agentAssessListCount(paramMap);
		List<Map> resultList = salesServiceMapper.agentAssessListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;	
	}

	

	@Override
	public ResultInfo uploadAgentImage(MultipartFile[] uploadImageList, String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try{
			//判断参数是否为空
			if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件上传相关参数为空");
				return resultInfo;
			}
			//获取财富顾问流水号
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			//文件类型
			String fileType = JsonUtils.getJsonValueByKey("fileType", param);
			//操作类型
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			//获取文件存储服务器http地址
			String fileSaveServerHttpAddress = commonService.getFileSaveServerHttpAddress();
			//获取文件存储服务器文档存储根目录
			String fileSaveServerRootPath = commonService.getFileSaveServerDocumentRootPath();
			//获取文件保存路径
			String fileSavePath = commonService.getFileSavePath(fileType);
			//创建文件保存日期文件夹，年/月/日
			String dateFolder = commonService.createDateFolder();
			//临时保存文件夹
			String agentImageTempFolder = "temp/agentImage/";
			//获取文件大小
			long fileSize = commonService.getUploadFileSize(fileType);
			String fileSize_M = String.valueOf(fileSize/1000/1024);
			//判断文件路径
			if (fileSavePath==null||"".equals(fileSavePath)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到文件保存路径");
				return resultInfo;
			}
			
			if (uploadImageList==null||uploadImageList.length==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请选择需要上传的照片！");
				return resultInfo;
			}
			String fullSavePath = "";
			if (operate!=null&&!"".equals(operate)&&"upload".equals(operate)) {
				fullSavePath = fileSaveServerRootPath+fileSavePath+dateFolder;
			}else if(operate!=null&&!"".equals(operate)&&"select".equals(operate)){
				fullSavePath = fileSaveServerRootPath+fileSavePath+agentImageTempFolder;
			}
			log.info("文件上传保存路径fileSavePath:::" + fullSavePath);
			File folderFile = new File(fullSavePath);
			if (!folderFile.exists()) {
				if (!folderFile.mkdirs()) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("创建文件文件存储目录失败！");
					return resultInfo;
				};
			}
			//选中文件时上传到临时文件夹，并删除之前的临时图片
			if(operate!=null&&!"".equals(operate)&&"select".equals(operate)){
				//删除文件夹中之前存储的临时图片
				File[] imageList = folderFile.listFiles();
				if(imageList!=null&&imageList.length>0){
					for(File file:imageList){
						file.delete();
					}
				}
			}
			
			//获取到上传到的照片
			MultipartFile uploadImage = uploadImageList[0];
			// 获得文件名(全路径)
			String uploadFileName = uploadImage.getOriginalFilename();
			log.info("文件名称uploadFileName：==="+uploadFileName);		
			//获取文件后缀
			String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
			if(suffix==null||"".equals(suffix)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件类型未知，上传失败！");
				return resultInfo;
			}
			log.info("====file suffix==="+suffix);
			//校验文件类型
			if(!commonService.verifyImageType(suffix)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件类型不符合要求，上传失败！");
				return resultInfo;
			}
			//校验文件大小
			long size = uploadImage.getSize();
			log.info("====upLoadFileSize==="+size);
			if (size>fileSize) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件过大(最大为"+fileSize_M+"M)！");
				return resultInfo;
			}
			//存储文件名	
			String storeName = commonService.rename(fileType,uploadFileName);
			//文件全名称
			String fileFullName = "";
			if (operate!=null&&!"".equals(operate)&&"upload".equals(operate)) {
				fileFullName = fileSaveServerRootPath+fileSavePath + dateFolder +storeName;
			}else if(operate!=null&&!"".equals(operate)&&"select".equals(operate)){
				fileFullName = fileSaveServerRootPath+fileSavePath+agentImageTempFolder+storeName;
			}
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileFullName));
			FileCopyUtils.copy(uploadImage.getInputStream(), outputStream);
			if (outputStream!=null) {
				outputStream.close();
			}
			//更新理财经理表中图片字段
			if (operate!=null&&!"".equals(operate)&&"upload".equals(operate)) {
				Agent agent = agentMapper.selectByPrimaryKey(new Long(agentId));
				BeanUtils.insertObjectSetOperateInfo(agent, loginInfo);
				//存储方式：Apache服务器的IP和端口配置成参数1，文件服务器根目录配置成参数2，agent表中存储具体的年/月/日文件目录以及文件名称
				agent.setAgentImage(dateFolder+storeName);
				agentMapper.updateByPrimaryKeySelective(agent);
				resultInfo.setSuccess(true);
				resultInfo.setObj(fileSaveServerHttpAddress+fileSavePath+dateFolder+storeName);
			}else if(operate!=null&&!"".equals(operate)&&"select".equals(operate)){
				resultInfo.setSuccess(true);
				resultInfo.setObj(fileSaveServerHttpAddress+fileSavePath+agentImageTempFolder+storeName);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	@Override
	public ResultInfo importSalaryFile(MultipartFile[] importFileNameList, String salaryFileType, String salaryFileDate, LoginInfo loginInfo) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		List<String> unUpLoadFile = new ArrayList<String>();
		try {
			if (importFileNameList==null||importFileNameList.length==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请选择需要上传的文件！");
				return resultInfo;
			}

			for(MultipartFile multipartFile:importFileNameList){
				// 获得文件名(全路径)
				String uploadFileName = multipartFile.getOriginalFilename();
				//获取文件后缀
				String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
				if(suffix==null||"".equals(suffix)){
					unUpLoadFile.add(uploadFileName+" : 文件类型未知");
					continue;
				}
				log.info("====file suffix==="+suffix);
				//校验文件
				if(!".xls".equals(suffix)){
					unUpLoadFile.add(uploadFileName+" : 请将文件保存成2003版本");
					continue;
				}
				//得到文件的输入流，转换为WorkBook
				Workbook workbook = Workbook.getWorkbook(multipartFile.getInputStream());
				resultInfo = resolveSalaryExcel(workbook, salaryFileType, salaryFileDate, loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("文件导入出现异常！");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		if(unUpLoadFile.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg(JsonUtils.objectToJsonStr(unUpLoadFile));
		}else{
			resultInfo.setMsg("文件导入成功！");
		}
		return resultInfo;
	}

	/**
	 * 文件模板定义
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private ResultInfo resolveSalaryExcel(Workbook workbook,String salaryFileType,String salaryFileDate,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap = getSalaryMonth(salaryFileDate,loginInfo);
			Long slySalaryId = new Long(paramMap.get("slySalaryId"));
			String newSalaryDate = paramMap.get("newSalaryDate");
			if("01".equals(salaryFileType)){
				SlyBaseSalaryExample slyBaseSalaryExample = new SlyBaseSalaryExample();
				SlyBaseSalaryExample.Criteria criteria = slyBaseSalaryExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlyBaseSalary> slyBaseSalaries = slyBaseSalaryMapper.selectByExample(slyBaseSalaryExample);
				if(slyBaseSalaries.size()>0){
					for(SlyBaseSalary existBaseSalary:slyBaseSalaries){
						BeanUtils.deleteObjectSetOperateInfo(existBaseSalary, loginInfo);
						slyBaseSalaryMapper.updateByPrimaryKey(existBaseSalary);
					}
				}
					SlyBaseSalary slyBaseSalary = new SlyBaseSalary();
					Map baseSalaryMap = new HashMap();
					List baseSalaryList = new ArrayList();
					//只有一个工作表格，取第一个Sheet
					Sheet sheet = workbook.getSheet(0);
					//将所有列名取出放入数组
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,0);
						//取出表头括号内的字段
						String content = cell.getContents();
						String beforeSplit[] =content.split("\\(");
						String afterSplit[] = beforeSplit[1].split("\\)");
						String baseSalaryValue = afterSplit[0];
						if (baseSalaryValue!=null&&!"".equals(baseSalaryValue)) {
							baseSalaryList.add(baseSalaryValue);
						}
					}
					//将每一行数据取出 放到map中 并保存进数据库
					for (int i = 1; i < sheet.getRows(); i++) {
						for (int j = 0; j < sheet.getColumns(); j++) {
							Cell cell = sheet.getCell(j,i);
							String baseSalaryValue = cell.getContents();
							baseSalaryMap.put(baseSalaryList.get(j), baseSalaryValue);
							
						}
						slyBaseSalary = (SlyBaseSalary)JsonUtils.mapToObject(baseSalaryMap, SlyBaseSalary.class);
						//设置month值
						slyBaseSalary.setMonth(newSalaryDate);
						//设置工资主表id
						slyBaseSalary.setSlySalaryId(slySalaryId);
						//设置操作信息
		                BeanUtils.insertObjectSetOperateInfo(slyBaseSalary, loginInfo);
						slyBaseSalaryMapper.insert(slyBaseSalary);
					}
				}
			if("02".equals(salaryFileType)){
				SlySaleCommissionExample slySaleCommissionExample = new SlySaleCommissionExample();
				SlySaleCommissionExample.Criteria criteria = slySaleCommissionExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlySaleCommission> slySaleCommissions = slySaleCommissionMapper.selectByExample(slySaleCommissionExample);
				if(slySaleCommissions.size()>0){
					for(SlySaleCommission existSaleCommission:slySaleCommissions){
						BeanUtils.deleteObjectSetOperateInfo(existSaleCommission, loginInfo);
						slySaleCommissionMapper.updateByPrimaryKey(existSaleCommission);
					}
				}
				SlySaleCommission slySaleCommission = new SlySaleCommission();
				Map slaeCommissionMap = new HashMap();
				List slaeCommissionList = new ArrayList();
				//只有一个工作表格，取第一个Sheet
				Sheet sheet = workbook.getSheet(0);
				//将所有列名取出放入数组
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,0);
					//取出表头括号内的字段
					String content = cell.getContents();
					String beforeSplit[] =content.split("\\(");
					String afterSplit[] = beforeSplit[1].split("\\)");
					String slaeCommissionValue = afterSplit[0];
					if (slaeCommissionValue!=null&&!"".equals(slaeCommissionValue)) {
						slaeCommissionList.add(slaeCommissionValue);
					}
				}
				//将每一行数据取出 放到map中 并保存进数据库
				for (int i = 1; i < sheet.getRows(); i++) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,i);
						String slaeCommissionValue = cell.getContents();
						slaeCommissionMap.put(slaeCommissionList.get(j), slaeCommissionValue);
						
					}
					slySaleCommission = (SlySaleCommission)JsonUtils.mapToObject(slaeCommissionMap, SlySaleCommission.class);
					//设置month值
					slySaleCommission.setMonth(newSalaryDate);
					//设置工资主表id
					slySaleCommission.setSlySalaryId(slySalaryId);
					//设置操作信息
	                BeanUtils.insertObjectSetOperateInfo(slySaleCommission, loginInfo);
					slySaleCommissionMapper.insert(slySaleCommission);
				}
            }
			if("03".equals(salaryFileType)){
				SlyGuojinCommissionExample slyGuojinCommissionExample = new SlyGuojinCommissionExample();
				SlyGuojinCommissionExample.Criteria criteria = slyGuojinCommissionExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlyGuojinCommission> slyGuojinCommissions = slyGuojinCommissionMapper.selectByExample(slyGuojinCommissionExample);
				if(slyGuojinCommissions.size()>0){
					for(SlyGuojinCommission existGuojinCommission:slyGuojinCommissions){
						BeanUtils.deleteObjectSetOperateInfo(existGuojinCommission, loginInfo);
						slyGuojinCommissionMapper.updateByPrimaryKey(existGuojinCommission);
					}
				}
				SlyGuojinCommission slyGuojinCommission = new SlyGuojinCommission();
				Map guojinCommissionMap = new HashMap();
				List guojinCommissionList = new ArrayList();
				//只有一个工作表格，取第一个Sheet
				Sheet sheet = workbook.getSheet(0);
				//将所有列名取出放入数组
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,0);
					//取出表头括号内的字段
					String content = cell.getContents();
					String beforeSplit[] =content.split("\\(");
					String afterSplit[] = beforeSplit[1].split("\\)");
					String guojinCommissionValue = afterSplit[0];
					if (guojinCommissionValue!=null&&!"".equals(guojinCommissionValue)) {
						guojinCommissionList.add(guojinCommissionValue);
					}
				}
				//将每一行数据取出 放到map中 并保存进数据库
				for (int i = 1; i < sheet.getRows(); i++) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,i);
						String guojinCommissionValue = cell.getContents();
						guojinCommissionMap.put(guojinCommissionList.get(j), guojinCommissionValue);
						
					}
					slyGuojinCommission = (SlyGuojinCommission)JsonUtils.mapToObject(guojinCommissionMap, SlyGuojinCommission.class);
					//设置month值
					slyGuojinCommission.setMonth(newSalaryDate);
					//设置工资主表id
					slyGuojinCommission.setSlySalaryId(slySalaryId);
					//设置操作信息
	                BeanUtils.insertObjectSetOperateInfo(slyGuojinCommission, loginInfo);
					slyGuojinCommissionMapper.insert(slyGuojinCommission);
				}
            }
			if("04".equals(salaryFileType)){
				SlyOverseasCommissionExample slyOverseasCommissionExample = new SlyOverseasCommissionExample();
				SlyOverseasCommissionExample.Criteria criteria = slyOverseasCommissionExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlyOverseasCommission> slyOverseasCommissions = slyOverseasCommissionMapper.selectByExample(slyOverseasCommissionExample);
				if(slyOverseasCommissions.size()>0){
					for(SlyOverseasCommission existOverseasCommission:slyOverseasCommissions){
						BeanUtils.deleteObjectSetOperateInfo(existOverseasCommission, loginInfo);
						slyOverseasCommissionMapper.updateByPrimaryKey(existOverseasCommission);
					}
				}
				SlyOverseasCommission slyOverseasCommission = new SlyOverseasCommission();
				Map overseasCommissionMap = new HashMap();
				List overseasCommissionList = new ArrayList();
				//只有一个工作表格，取第一个Sheet
				Sheet sheet = workbook.getSheet(0);
				//将所有列名取出放入数组
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,0);
					//取出表头括号内的字段
					String content = cell.getContents();
					String beforeSplit[] =content.split("\\(");
					String afterSplit[] = beforeSplit[1].split("\\)");
					String overseasCommissionValue = afterSplit[0];
					if (overseasCommissionValue!=null&&!"".equals(overseasCommissionValue)) {
						overseasCommissionList.add(overseasCommissionValue);
					}
				}
				//将每一行数据取出 放到map中 并保存进数据库
				for (int i = 1; i < sheet.getRows(); i++) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,i);
						String overseasCommissionValue = cell.getContents();
						overseasCommissionMap.put(overseasCommissionList.get(j), overseasCommissionValue);
						
					}
					slyOverseasCommission = (SlyOverseasCommission)JsonUtils.mapToObject(overseasCommissionMap, SlyOverseasCommission.class);
					//设置month值
					slyOverseasCommission.setMonth(newSalaryDate);
					//设置工资主表id
					slyOverseasCommission.setSlySalaryId(slySalaryId);
					//设置操作信息
	                BeanUtils.insertObjectSetOperateInfo(slyOverseasCommission, loginInfo);
					slyOverseasCommissionMapper.insert(slyOverseasCommission);
				}
            }
			if("05".equals(salaryFileType)){
				SlyReissueExample slyReissueExample = new SlyReissueExample();
				SlyReissueExample.Criteria criteria = slyReissueExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlyReissue> slyReissues = slyReissueMapper.selectByExample(slyReissueExample);
				if(slyReissues.size()>0){
					for(SlyReissue existReissue:slyReissues){
						BeanUtils.deleteObjectSetOperateInfo(existReissue, loginInfo);
						slyReissueMapper.updateByPrimaryKey(existReissue);
					}
				}
				SlyReissue slyReissue = new SlyReissue();
				Map reissueMap = new HashMap();
				List reissueList = new ArrayList();
				//只有一个工作表格，取第一个Sheet
				Sheet sheet = workbook.getSheet(0);
				//将所有列名取出放入数组
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,0);
					//取出表头括号内的字段
					String content = cell.getContents();
					String beforeSplit[] =content.split("\\(");
					String afterSplit[] = beforeSplit[1].split("\\)");
					String reissueValue = afterSplit[0];
					if (reissueValue!=null&&!"".equals(reissueValue)) {
						reissueList.add(reissueValue);
					}
				}
				//将每一行数据取出 放到map中 并保存进数据库
				for (int i = 1; i < sheet.getRows(); i++) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,i);
						String reissueValue = cell.getContents();
						reissueMap.put(reissueList.get(j), reissueValue);
						
					}
					slyReissue = (SlyReissue)JsonUtils.mapToObject(reissueMap, SlyReissue.class);
					//设置month值
					slyReissue.setMonth(newSalaryDate);
					//设置工资主表id
					slyReissue.setSlySalaryId(slySalaryId);
					//设置操作信息
	                BeanUtils.insertObjectSetOperateInfo(slyReissue, loginInfo);
					slyReissueMapper.insert(slyReissue);
				}
            }
			if("06".equals(salaryFileType)){
				SlyWithholdExample slyWithholdExample = new SlyWithholdExample();
				SlyWithholdExample.Criteria criteria = slyWithholdExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlyWithhold> slyWithholds = slyWithholdMapper.selectByExample(slyWithholdExample);
				if(slyWithholds.size()>0){
					for(SlyWithhold existWithhold:slyWithholds){
						BeanUtils.deleteObjectSetOperateInfo(existWithhold, loginInfo);
						slyWithholdMapper.updateByPrimaryKey(existWithhold);
					}
				}
				SlyWithhold slyWithhold = new SlyWithhold();
				Map withholdMap = new HashMap();
				List withholdList = new ArrayList();
				//只有一个工作表格，取第一个Sheet
				Sheet sheet = workbook.getSheet(0);
				//将所有列名取出放入数组
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,0);
					//取出表头括号内的字段
					String content = cell.getContents();
					String beforeSplit[] =content.split("\\(");
					String afterSplit[] = beforeSplit[1].split("\\)");
					String withholdValue = afterSplit[0];
					if (withholdValue!=null&&!"".equals(withholdValue)) {
						withholdList.add(withholdValue);
					}
				}
				//将每一行数据取出 放到map中 并保存进数据库
				for (int i = 1; i < sheet.getRows(); i++) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,i);
						String withholdValue = cell.getContents();
						withholdMap.put(withholdList.get(j), withholdValue);
						
					}
					slyWithhold = (SlyWithhold)JsonUtils.mapToObject(withholdMap, SlyWithhold.class);
					//设置month值
					slyWithhold.setMonth(newSalaryDate);
					//设置工资主表id
					slyWithhold.setSlySalaryId(slySalaryId);
					//设置操作信息
	                BeanUtils.insertObjectSetOperateInfo(slyWithhold, loginInfo);
					slyWithholdMapper.insert(slyWithhold);
				}
            }
			if("07".equals(salaryFileType)){
				SlyProjectCommissionExample slyProjectCommissionExample = new SlyProjectCommissionExample();
				SlyProjectCommissionExample.Criteria criteria = slyProjectCommissionExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlyProjectCommission> slyProjectCommissions = slyProjectCommissionMapper.selectByExample(slyProjectCommissionExample);
				if(slyProjectCommissions.size()>0){
					for(SlyProjectCommission existProjectCommission:slyProjectCommissions){
						BeanUtils.deleteObjectSetOperateInfo(existProjectCommission, loginInfo);
						slyProjectCommissionMapper.updateByPrimaryKey(existProjectCommission);
					}
				}
				SlyProjectCommission slyProjectCommission = new SlyProjectCommission();
				Map projectCommissionMap = new HashMap();
				List projectCommissionList = new ArrayList();
				//只有一个工作表格，取第一个Sheet
				Sheet sheet = workbook.getSheet(0);
				//将所有列名取出放入数组
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,0);
					//取出表头括号内的字段
					String content = cell.getContents();
					String beforeSplit[] =content.split("\\(");
					String afterSplit[] = beforeSplit[1].split("\\)");
					String projectCommissionValue = afterSplit[0];
					if (projectCommissionValue!=null&&!"".equals(projectCommissionValue)) {
						projectCommissionList.add(projectCommissionValue);
					}
				}
				//将每一行数据取出 放到map中 并保存进数据库
				for (int i = 1; i < sheet.getRows(); i++) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,i);
						String projectCommissionValue = cell.getContents();
						projectCommissionMap.put(projectCommissionList.get(j), projectCommissionValue);
						
					}
					slyProjectCommission = (SlyProjectCommission)JsonUtils.mapToObject(projectCommissionMap, SlyProjectCommission.class);
					//设置month值
					slyProjectCommission.setMonth(newSalaryDate);
					//设置工资主表id
					slyProjectCommission.setSlySalaryId(slySalaryId);
					//设置操作信息
	                BeanUtils.insertObjectSetOperateInfo(slyProjectCommission, loginInfo);
					slyProjectCommissionMapper.insert(slyProjectCommission);
				}
            }
			if("08".equals(salaryFileType)){
				SlyCommissionExample slyCommissionExample = new SlyCommissionExample();
				SlyCommissionExample.Criteria criteria = slyCommissionExample.createCriteria();
				criteria.andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
				List<SlyCommission> slyCommissions = slyCommissionMapper.selectByExample(slyCommissionExample);
				if(slyCommissions.size()>0){
					for(SlyCommission existSlyCommission:slyCommissions){
						BeanUtils.deleteObjectSetOperateInfo(existSlyCommission, loginInfo);
						slyCommissionMapper.updateByPrimaryKey(existSlyCommission);
					}
				}
				SlyCommission slyCommission = new SlyCommission();
				Map slyCommissionMap = new HashMap();
				List slyCommissionList = new ArrayList();
				//只有一个工作表格，取第一个Sheet
				Sheet sheet = workbook.getSheet(0);
				//将所有列名取出放入数组
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,0);
					//取出表头括号内的字段
					String content = cell.getContents();
					String beforeSplit[] =content.split("\\(");
					String afterSplit[] = beforeSplit[1].split("\\)");
					String slyCommissionValue = afterSplit[0];
					if (slyCommissionValue!=null&&!"".equals(slyCommissionValue)) {
						slyCommissionList.add(slyCommissionValue);
					}
				}
				//将每一行数据取出 放到map中 并保存进数据库
				for (int i = 1; i < sheet.getRows(); i++) {
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j,i);
						String slyCommissionValue = cell.getContents();
						slyCommissionMap.put(slyCommissionList.get(j), slyCommissionValue);
						
					}
					slyCommission = (SlyCommission)JsonUtils.mapToObject(slyCommissionMap, SlyCommission.class);
					//设置month值
					slyCommission.setMonth(newSalaryDate);
					//设置工资主表id
					slyCommission.setSlySalaryId(slySalaryId);
					//设置操作信息
	                BeanUtils.insertObjectSetOperateInfo(slyCommission, loginInfo);
					slyCommissionMapper.insert(slyCommission);
				}
            }
            workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("解析文件出现异常！");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("文件导入成功！");
		return resultInfo;
	}

	/**
	 * 工资主表查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid querySalaryList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.querySalaryListCount(paramMap);
		List<Map> resultList = salesServiceMapper.querySalaryList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 基本工资表查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryBaseSalaryList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.queryBaseSalaryListCount(paramMap);
		List<Map> resultList = salesServiceMapper.queryBaseSalaryList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 得到文件的month
	 * @param salaryFileDate
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public Map getSalaryMonth(String salaryFileDate,LoginInfo loginInfo) throws Exception{
		Map<String, String> paramMap = new HashMap<String, String>();
		try {
			SlySalaryExample slySalaryExample = new SlySalaryExample();
			String newSalaryDate = salaryFileDate;
			slySalaryExample.createCriteria().andMonthEqualTo(newSalaryDate).andRcStateEqualTo("E");
			List<SlySalary> slySalaries = slySalaryMapper.selectByExample(slySalaryExample);
			if(slySalaries.size() == 0){
				SlySalary slySalary = new SlySalary();
				slySalary.setMonth(newSalaryDate);
				BeanUtils.insertObjectSetOperateInfo(slySalary, loginInfo);
				slySalaryMapper.insert(slySalary);
				//获取新增之后的主键
				SlySalaryExample salaryExample = new SlySalaryExample();
				salaryExample.createCriteria().andMonthEqualTo(newSalaryDate).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<SlySalary> slySalaryList = slySalaryMapper.selectByExample(salaryExample);
				String slySalaryId = slySalaryList.get(0).getSlySalaryId().toString();
				paramMap.put("slySalaryId", slySalaryId);
				paramMap.put("newSalaryDate", newSalaryDate);
				return paramMap;
			}else{
				String slySalaryId = slySalaries.get(0).getSlySalaryId().toString();
				paramMap.put("slySalaryId", slySalaryId);
				paramMap.put("newSalaryDate", newSalaryDate);
				return paramMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramMap;
	}

	/**
	 * 国金交易佣金查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryGuojinCommissionIdList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.queryGuojinCommissionIdListCount(paramMap);
		List<Map> resultList = salesServiceMapper.queryGuojinCommissionIdList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 海外投资查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryOverseasCommissionIdList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.queryOverseasCommissionIdListCount(paramMap);
		List<Map> resultList = salesServiceMapper.queryOverseasCommissionIdList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 项目端佣金查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryProjectCommissionIdList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.queryProjectCommissionIdListCount(paramMap);
		List<Map> resultList = salesServiceMapper.queryProjectCommissionIdList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 补发利益查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryReissueIdList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.queryReissueIdListCount(paramMap);
		List<Map> resultList = salesServiceMapper.queryReissueIdList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 销售佣金查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid querySaleCommissionIdList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.querySaleCommissionIdListCount(paramMap);
		List<Map> resultList = salesServiceMapper.querySaleCommissionIdList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 暂扣利益查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryWithholdIdList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.queryWithholdIdListCount(paramMap);
		List<Map> resultList = salesServiceMapper.queryWithholdIdList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/***************************************综合查询******************************************/
	/**
	 * 基本工资详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryBaseSalaryDetail(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.queryBaseSalaryDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryBaseSalaryDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 销售佣金详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid querySaleCommissionDetail(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.querySaleCommissionDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.querySaleCommissionDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 国金佣金详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryGuojinCommissionDetail(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.queryGuojinCommissionDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryGuojinCommissionDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 海外佣金详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryOverseasCommissionDetail(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.queryOverseasCommissionDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryOverseasCommissionDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 补发利益详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryReissueDetail(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.queryReissueDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryReissueDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 暂扣利益详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryWithholdDetail(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.queryWithholdDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryWithholdDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 海外佣金详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryProjectCommissionDetail(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.queryProjectCommissionDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryProjectCommissionDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 查询综合工资
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryGeneralSalary(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			//String agentName = agentList.get(0).getAgentName();
			paramMap.put("workNumber", agentCode);
			//paramMap.put("agentName", agentName);
			Integer total = salesServiceMapper.queryGeneralSalaryCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryGeneralSalary(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@Override
	public DataGrid queryGeneralBaseSalaryList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.queryGeneralBaseSalaryListCount(paramMap);
			List<Map> resultList = salesServiceMapper.queryGeneralBaseSalaryList(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 保存财富顾问工资卡信息
	 * @param agent
	 * @param agentSalaryAccInfoList
	 * @param loginInfo
	 * @param operate
	 * @return
	 */
	@Transactional
	public ResultInfo saveAgentSalaryAccInfo(Agent agent,
			List<AgentAccInfo> agentSalaryAccInfoList,LoginInfo loginInfo,String operate){
		    ResultInfo resultInfo = new ResultInfo();
			/*该模块作用如下：
			 * 作用一、新增财富顾问保存工资卡信息时-----（1、先根据agentid查询财富顾问工资卡信息是否存在）
			 * 作用二、更新财富顾问工资卡信息时----（1、先根据agentid逻辑删除t_agent_acc_info表中的所有信息）
			 */
			AgentAccInfoExample agentAccInfoExample = new AgentAccInfoExample();
			AgentAccInfoExample.Criteria agentAccInfoExampleCriteria = agentAccInfoExample.createCriteria();
			agentAccInfoExampleCriteria.andAgentIdEqualTo(agent.getAgentId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgentAccInfo> agentAccInfos = agentAccMapper.selectByExample(agentAccInfoExample);
			if(agentAccInfos!=null&&agentAccInfos.size()>0){
				for(AgentAccInfo agentAccInfo2 :agentAccInfos){
					BeanUtils.deleteObjectSetOperateInfo(agentAccInfo2, loginInfo);
					agentAccMapper.updateByPrimaryKey(agentAccInfo2);
				}
			}
			/*该模块作用如下：
			 * 一、新增财富顾问工资卡信息时---（2、不存在时将参数agentAccInfoList中的信息录入数据库）
			 * 二、更新财富顾问工资卡信息时---（2、重新将参数agentAccInfoList中的信息录入数据库）
			 */
			if (agentSalaryAccInfoList!=null&&agentSalaryAccInfoList.size()>0) {
				//如果是已经存在的账户信息，则要先将ID置空，不然会报错；
				for (AgentAccInfo agentAccInfo:agentSalaryAccInfoList) {
					if(agentAccInfo.getAgentAccInfoId()!=null){
						agentAccInfo.setAgentAccInfoId(null);
					}
				//保存新的工资卡信息
				agentAccInfo.setAgentId(agent.getAgentId());
				BeanUtils.insertObjectSetOperateInfo(agentAccInfo, loginInfo);
				agentAccMapper.insert(agentAccInfo);
				}
			}
		resultInfo.setSuccess(true);
		return resultInfo;
	} 
	
	/**
	 * 获取员工工资卡信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgentSalaryAccInfo(Map paramMap){
		List<Map> agentAccInfoList = new ArrayList<Map>();
		agentAccInfoList = salesServiceMapper.getAgentAccInfoList(paramMap);
		return agentAccInfoList;
	}
	
	/**
	 * 		
	 * 		导出员工信息
	 * */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Map exportAgentInfo(Map paramMap)  throws Exception {
		// Mapper接口方法：统计员工相关信息
		List<Map<String,String>> agentsInfo = this.salesServiceMapper.getAgentsInfo(paramMap);
		Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("data", agentsInfo);
	    return datas;
	}	
	
	/**
	 * 佣金明细查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid querydetailsCommissionIdList(DataGridModel dgm, Map paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = salesServiceMapper.querydetailsCommissionIdListCount(paramMap);
		List<Map> resultList = salesServiceMapper.querydetailsCommissionIdList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 佣金总额详情
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid querydetailsCommission(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
			DataGrid dataGrid = new DataGrid();
		try {
			int startIndex =((dgm.getPage()-1)*dgm.getRows());
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", dgm.getRows());
			//获取登录用户工号
			Long userId = loginInfo.getUserId();
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String agentCode = agentList.get(0).getAgentCode();
			paramMap.put("workNumber", agentCode);
			Integer total = salesServiceMapper.querySlyCommissionDetailCount(paramMap);
			List<Map> resultList = salesServiceMapper.querySlyCommissionDetail(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
