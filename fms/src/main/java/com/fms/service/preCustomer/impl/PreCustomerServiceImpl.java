package com.fms.service.preCustomer.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.fms.db.mapper.PRECustomerBaseInfoMapper;
import com.fms.db.mapper.PRECustomerVistorInfoMapper;
import com.fms.db.model.PRECustomerBaseInfo;
import com.fms.db.model.PRECustomerBaseInfoExample;
import com.fms.db.model.PRECustomerVistorInfo;
import com.fms.db.model.PRECustomerVistorInfoExample;
import org.springframework.transaction.annotation.Transactional;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.service.mapper.PreCustomerServiceMapper;
import com.fms.service.preCustomer.PreCustomerService;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PreCustomerServiceImpl  implements PreCustomerService{
	
	private static final Logger log = Logger.getLogger(PreCustomerServiceImpl.class);
	
	@Autowired
	private PreCustomerServiceMapper preCustomerServiceMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private PRECustomerBaseInfoMapper preCustomerBaseInfoMapper;
	@Autowired
	private PRECustomerVistorInfoMapper preCustomerVistorInfoMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	DefCodeMapper defCodeMapper;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public DataGrid queryPreCustomerList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
		log.info("准客户列表信息查询");
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		AgentExample agentExample=new AgentExample();
		agentExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andUserIdEqualTo(loginInfo.getUserId());
		List<Agent> agentList=agentMapper.selectByExample(agentExample);
		if (agentList!=null&&agentList.size()>0) {
			paramMap.put("loginAgentId", agentList.get(0).getAgentId());
		}else{
			paramMap.put("loginAgentId", "");
		}
		Integer total = preCustomerServiceMapper.preCustomerQueryListCount(paramMap);
		List<Map> resultList = preCustomerServiceMapper.preCustomerQueryListPage(paramMap);
		String loginAgentId="";

		if (agentList!=null&&agentList.size()>0) {
			loginAgentId=agentList.get(0).getAgentId().toString();
		}
		List<Map> returnResultList=new ArrayList<Map>();
		if (resultList!=null&&resultList.size()>0) {
			for (Map map : resultList) {
				// 如果是操作人与客户的所属财富顾问不为同一人，则准客户姓名和电话号码需隐藏
				String existAgentId=map.get("agentId").toString();
				if (!existAgentId.equals(loginAgentId)) {
					// 处理电话号码，隐藏中间四位
					if (map.get("preCustMobile") != null) {
						StringBuilder preCustMobile = new StringBuilder(map.get("preCustMobile").toString());
						//preCustMobile.replace(3, 7, "****");
						map.put("preCustMobile", preCustMobile);
					}
					// 处理准客户姓名隐藏名字
					if (map.get("preCustName") != null) {
						String preCustNameExist=map.get("preCustName").toString().trim();
						String code="";
						/*for (int i = 0; i < preCustNameExist.length()-1; i++) {
							code=code+"*";
						}*/
						StringBuilder preCustName = new StringBuilder(map.get("preCustName").toString());
						//preCustName.replace(1, preCustName.length(), code);
						map.put("preCustName", preCustName);
					}
					//处理楼盘号
					if (map.get("preCustBuildingNo")!=null) {
						String preCustBuildingNo=map.get("preCustBuildingNo").toString().trim();
						String code="";
						for (int i = 0; i < preCustBuildingNo.length(); i++) {
							code=code+"*";	
						}
						map.put("preCustBuildingNo", code);
					}
				}
				returnResultList.add(map);
			}
			
		}
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(returnResultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 更新准客户信息
	 */
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo getPreCustomerInfo(String param) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		Map resultMap=new HashMap();
		
		try {
			
			if (param!=null) {
				paramMap.put("preCustBaseInfoId", param);
				//获取准客户详细信息
				PRECustomerBaseInfo preCustomerBaseInfo = preCustomerBaseInfoMapper.selectByPrimaryKey(new Long(param));
				//获取准客户拜访时间信息
				List<Map>  getpreCustomerVistorInfoList=preCustomerServiceMapper.preCustomerQueryVisitTime(paramMap);
	
				resultMap.put("preCustomerBaseInfo", JsonUtils.objectToMap(preCustomerBaseInfo));
				resultMap.put("preCustomerVistorInfoList", getpreCustomerVistorInfoList);
				
				resultInfo.setObj(resultMap);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("获取准客户修改信息成功！");
				return resultInfo;
				
			}
			else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取准客户信息的参数信息失败，无法获取准客户信息");
				return resultInfo;
			}
			//获取拜访信息
	/*		PRECustomerVistorInfoExample preCustomerVistorInfoExample = new PRECustomerVistorInfoExample();
			PRECustomerVistorInfoExample.Criteria preCustomerVistorInfoCriteria = preCustomerVistorInfoExample.createCriteria();
			preCustomerVistorInfoCriteria.andPreCustBaseInfoIdEqualTo(new BigDecimal(param)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PRECustomerVistorInfo> preCustomerVistorInfoList = preCustomerVistorInfoMapper.selectByExample(preCustomerVistorInfoExample);
			List<Map> getpreCustomerVistorInfoList = new ArrayList();
			if (preCustomerVistorInfoList!=null&&preCustomerVistorInfoList.size()>0) {
				for (PRECustomerVistorInfo preCustomerVistorInfo : preCustomerVistorInfoList) {
					String custVisitType = preCustomerVistorInfo.getPreCustVisitType();
					String custVisitAction = preCustomerVistorInfo.getPreCustVisitAction();
					DefCodeExample defCodeExample1 = new DefCodeExample();
					DefCodeExample.Criteria defCodeCriteria1 = defCodeExample1.createCriteria();
					defCodeCriteria1.andCodeTypeEqualTo("preCustVisitType").andCodeEqualTo(custVisitType);
					String custVisitTypeName  = defCodeMapper.selectByExample(defCodeExample1).get(0).getCodeName();
					DefCodeExample defCodeExample2 = new DefCodeExample();
					DefCodeExample.Criteria defCodeCriteria2 = defCodeExample2.createCriteria();
					defCodeCriteria2.andCodeTypeEqualTo("preCustVisitAction").andCodeEqualTo(custVisitAction);
					String custVisitActionName = defCodeMapper.selectByExample(defCodeExample2).get(0).getCodeName();
					Map map = new HashMap();
					map.put("preCustVisitTime", DateUtils.getDateTimeString(preCustomerVistorInfo.getPreCustVisitTime()));
					map.put("preCustVisitType", custVisitType);
					map.put("preCustVisitAction", custVisitAction);
					map.put("preCustVisitContent", preCustomerVistorInfo.getPreCustVisitContent());
					map.put("preCustVisitorInfoId", preCustomerVistorInfo.getPreCustVisitorInfoId());
					map.put("preCustVisitTypeName", custVisitType+"-"+custVisitTypeName);
					map.put("preCustVisitActionName", custVisitAction+"-"+custVisitActionName);
					getpreCustomerVistorInfoList.add(map);
				}
			}*/
			
/*			resultMap.put("preCustomerBaseInfo", JsonUtils.objectToMap(preCustomerBaseInfo));
			resultMap.put("preCustomerVistorInfoList", getpreCustomerVistorInfoList);
			resultInfo.setObj(resultMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取准客户修改信息成功！");*/
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取准客户修改信息成功失败！");
		}
		return resultInfo;
	}
	/**
	 * 
	 * 准客户基本信息新增保存
	 * @author LiWenTao
	 * @param
	 * @return 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Map addPreCustomerBasicInfoSave(Map map, LoginInfo loginInfo)
	{
		/************************将准客户基本信息存入数据库***********/
		PRECustomerBaseInfo preCustomerBaseInfo = (PRECustomerBaseInfo)map.get("preCustomerBaseInfo");
		Long userId = loginInfo.getUserId();
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria  criteriaAgent = agentExample.createCriteria();
		criteriaAgent.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andUserIdEqualTo(userId);
		List<Agent> listAgents = this.agentMapper.selectByExample(agentExample);
		Map messageMap = new HashMap();
		if (listAgents.size() < 1 || null == listAgents) {
			messageMap.put("msg", "您不是财富顾问，无法新增");
			messageMap.put("succ", "false");
			return messageMap;
		}
		if (listAgents.size() > 1) {
			messageMap.put("msg", "在数据库中查询到多条对应您的理财师信息");
			messageMap.put("succ", "false");
			return messageMap;
		}
		String param = null;
		//处理楼盘号
		try {
			if (preCustomerBaseInfo.getPreCustBuildingNo()!=null) {
				param =  java.net.URLDecoder.decode(preCustomerBaseInfo.getPreCustBuildingNo(),"UTF-8");
				param =  java.net.URLDecoder.decode(param,"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			messageMap.put("msg", "提交失败！您输入的楼号只能包含字符#");
			messageMap.put("succ", "false");
			return messageMap;
		}
		Long agentId = (listAgents.get(0)).getAgentId();//获取AGENTID
		Long preCustBaseInfoId = this.commonService.getSeqValByName("SEQ_T_PRE_CUSTOMER_BASE_INFO");//生成流水号
		String preCustNo = commonService.createPreCustomerNo();//生成客户号
		//设置PRECustomerBaseInfo对象对应属性值
		preCustomerBaseInfo.setPreCustBaseInfoId(preCustBaseInfoId);
		preCustomerBaseInfo.setPreCustNo(preCustNo);
		preCustomerBaseInfo.setPreCustBuildingNo(param);
		preCustomerBaseInfo.setPreAgentId(agentId);
		BeanUtils.insertObjectSetOperateInfo(preCustomerBaseInfo, loginInfo);
		this.preCustomerBaseInfoMapper.insert(preCustomerBaseInfo);//新增准客户
		/********************将准客户拜访信息存入数据库****************/
		List<PRECustomerVistorInfo> listPreCustomerVisitorInfo = (List<PRECustomerVistorInfo>)map.get("listPreCustomerVistorInfo");
		for (PRECustomerVistorInfo preCustomerVistorInfo : listPreCustomerVisitorInfo) {
			Long preCustomerVisitorInfoId = commonService.getSeqValByName("SEQ_T_PRE_CUSTOMER_VISIT_INFO");
			preCustomerVistorInfo.setPreCustVisitContent(preCustomerVistorInfo.getPreCustVisitContent());
			preCustomerVistorInfo.setPreCustVisitorInfoId(preCustomerVisitorInfoId);
			preCustomerVistorInfo.setPreCustBaseInfoId(preCustBaseInfoId);
			BeanUtils.insertObjectSetOperateInfo(preCustomerVistorInfo, loginInfo);
			this.preCustomerVistorInfoMapper.insert(preCustomerVistorInfo);
			
		}
		messageMap.put("msg", "信息保存成功");
		messageMap.put("succ", "true");
		return messageMap;
	}
	
	/**
	 * 准客户基本信息-删除保存
	 * @author LiWenTao
	 * @param map
	 * @param loginInfo
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Map deletePreCustomerBasicInfo(Map paramMap, LoginInfo loginInfo) {
		log.info("准客户信息删除");
		// 判断前端页面传入的agentId是否为空
		if(paramMap.get("agentId") == null||paramMap.get("agentId").equals(""))
		{
			log.info("前端页面传入的理财师编号为空，不能删除该条信息");
			throw new CisCoreException("前端页面传入的理财师编号为空，不能删除该条信息");
		}
		Long userIdInloginInfo = loginInfo.getUserId();
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria  criteriaAgent = agentExample.createCriteria();
		criteriaAgent.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andUserIdEqualTo(userIdInloginInfo);
		List<Agent> listAgents = agentMapper.selectByExample(agentExample);
		Map messageMap = new HashMap();
		if (listAgents.size() < 1 || null == listAgents) {
			messageMap.put("msg", "您不是财富顾问，无法进行删除");
			messageMap.put("succ", "false");
			return messageMap;
		}
		if (listAgents.size() > 1) {
			messageMap.put("msg", "在数据库中查询到多条对应您的理财师信息");
			messageMap.put("succ", "false");
			return messageMap;
		}
		// 若前端页面传入的agentId与从t_agent表中查询到的agentId不相同，则返回信息：不能删除理财师
		Long agentId = (listAgents.get(0)).getAgentId();
		BigDecimal agentIdInParam = new BigDecimal((Integer)(paramMap.get("agentId")));
		if (!agentId.equals(agentIdInParam)) {
			messageMap.put("msg", "该准客户所属财富顾问不允许为,您无法进行删除");
			messageMap.put("succ", "false");
			return messageMap;
		}
		// 通过页面传入preCustBaseInfoId查询准客户拜访信息表
		Integer preCustBaseInfoId = (Integer)paramMap.get("preCustBaseInfoId");
		PRECustomerVistorInfoExample 			preCustomerVistorInfoExample = new PRECustomerVistorInfoExample();
		PRECustomerVistorInfoExample.Criteria   preCustomerVistorInfoExampleCriteria = preCustomerVistorInfoExample.createCriteria();
		preCustomerVistorInfoExampleCriteria.andRcStateEqualTo("E")
											.andPreCustBaseInfoIdEqualTo(new Long(preCustBaseInfoId));
		List<PRECustomerVistorInfo> preCustomerVistorInfosList = this.preCustomerVistorInfoMapper.selectByExample(preCustomerVistorInfoExample);
		// 遍历List中每个PRECustomerVistorInfo对象，删除该对象对应的记录
		for (PRECustomerVistorInfo preCustomerVistorInfo : preCustomerVistorInfosList)
		{
			BeanUtils.deleteObjectSetOperateInfo(preCustomerVistorInfo, loginInfo);
			this.preCustomerVistorInfoMapper.updateByPrimaryKey(preCustomerVistorInfo);
		}
		// 新建PRECustomerBasicInfo
		PRECustomerBaseInfo preCustomerBaseInfo = this.preCustomerBaseInfoMapper.selectByPrimaryKey(new Long(preCustBaseInfoId));
		// 删除该对象对应的记录
		BeanUtils.deleteObjectSetOperateInfo(preCustomerBaseInfo, loginInfo);
		this.preCustomerBaseInfoMapper.updateByPrimaryKey(preCustomerBaseInfo);
		messageMap.put("msg", "删除成功");
		messageMap.put("succ", "true");
		return messageMap;
	}
	
	/**
	 * 校验该客户是否归属于您
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updateVerifyPreCustomer(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		if (paramMap!=null) {
		    AgentExample agentExample=new AgentExample();
		    agentExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andUserIdEqualTo(loginInfo.getUserId());
		    List<Agent> listAgent=agentMapper.selectByExample(agentExample);
		    if (listAgent!=null&&listAgent.size()>0) {
		    	if (listAgent.get(0).getAgentId().toString().equals(paramMap.get("agentId"))) {
		    		resultInfo.setSuccess(true);
		    		resultInfo.setMsg("该准客户属于您");
		            return  resultInfo;
				}
		    	else{
		    	resultInfo.setSuccess(false);
		    	resultInfo.setMsg("该准客户不属于您，您无法进行更新");
		    	return resultInfo;
		    	}
			}
		    else{
		    	resultInfo.setSuccess(false);
		    	resultInfo.setMsg("您不是财富顾问，无法进行更新");
		    	return resultInfo;
		    }
		}
		else{
			resultInfo.setMsg("修改获取财富顾问ID失败");	
			resultInfo.setSuccess(false);
		}
		
		return resultInfo;
	}

	/**
	 * 保存修改的准客户信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo keepPreCustomerInfo(Map tMap,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		List<PRECustomerVistorInfo> preCustomerVistorInfos=null;
		String updatePreCustBaseInfoId =null;
		PRECustomerBaseInfo preCustomerBaseInfo=null;
		
			if (tMap.get("updatePreCustBaseInfoId")!=null&&tMap.get("preCustomerBaseInfo")!=null) {
				 updatePreCustBaseInfoId = tMap.get("updatePreCustBaseInfoId").toString();//获取更新的准客户Id
				 preCustomerBaseInfo = (PRECustomerBaseInfo) tMap.get("preCustomerBaseInfo");//获取准客户更新信息
				 preCustomerVistorInfos = (List<PRECustomerVistorInfo>) tMap.get("preCustomerVistorInfos");//获取拜访时间信息
			}
			else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新准客户信息，获取准客户信息失败");
			return resultInfo;
			}
			
			String param=null;
			//处理楼盘号
			try {
				if (preCustomerBaseInfo.getPreCustBuildingNo()!=null) {
					param =  java.net.URLDecoder.decode(preCustomerBaseInfo.getPreCustBuildingNo(),"UTF-8");
					param =  java.net.URLDecoder.decode(param,"UTF-8");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("提交失败！您输入的楼号只能包含字符#");
				return resultInfo;
			}
			
			try {
				if (updatePreCustBaseInfoId != null) {
					// 查找已经存在的准客户主表,修改相应的准客户信息
					PRECustomerBaseInfoExample preCustomerBaseInfoExample = new PRECustomerBaseInfoExample();
					preCustomerBaseInfoExample.createCriteria().andPreCustBaseInfoIdEqualTo(new Long(updatePreCustBaseInfoId)).andRcStateEqualTo("E");
					PRECustomerBaseInfo existpreCustomerBaseInfo = preCustomerBaseInfoMapper.selectByExample(preCustomerBaseInfoExample).get(0);
					existpreCustomerBaseInfo.setPreCustName(preCustomerBaseInfo.getPreCustName());
					existpreCustomerBaseInfo.setPreCustMobile(preCustomerBaseInfo.getPreCustMobile());
					existpreCustomerBaseInfo.setPreCustSex(preCustomerBaseInfo.getPreCustSex());
					existpreCustomerBaseInfo.setPreCustType(preCustomerBaseInfo.getPreCustType());
					
					existpreCustomerBaseInfo.setPreCustResidentialBuilding(preCustomerBaseInfo.getPreCustResidentialBuilding());
					existpreCustomerBaseInfo.setPreCustBuildingNo(param);
					existpreCustomerBaseInfo.setPreCustObtainWay(preCustomerBaseInfo.getPreCustObtainWay());
					com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(existpreCustomerBaseInfo,loginInfo);
					preCustomerBaseInfoMapper.updateByPrimaryKey(existpreCustomerBaseInfo);
					//查询出该准客户的拜访信息删除
					PRECustomerVistorInfoExample preCustomerVistorInfoExample = new PRECustomerVistorInfoExample();
					PRECustomerVistorInfoExample.Criteria preCustomerVistorInfoCriteria = preCustomerVistorInfoExample.createCriteria();
					preCustomerVistorInfoCriteria.andPreCustBaseInfoIdEqualTo(new Long(updatePreCustBaseInfoId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<PRECustomerVistorInfo> preCustomerVistorInfoExist = preCustomerVistorInfoMapper.selectByExample(preCustomerVistorInfoExample);
                    if (preCustomerVistorInfoExist!=null&&preCustomerVistorInfoExist.size()>0) {
						for (PRECustomerVistorInfo preCustomerVistorInfo : preCustomerVistorInfoExist) {
							com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(preCustomerVistorInfo, loginInfo);
							preCustomerVistorInfoMapper.updateByPrimaryKey(preCustomerVistorInfo);
						}
                    	
					}
                    //新增现在的准客户信息
                    if (preCustomerVistorInfos!=null&&preCustomerVistorInfos.size()>0) {
						for (PRECustomerVistorInfo preCustomerVistorInfo : preCustomerVistorInfos) {
							Long preCustomerVisitorInfoId = commonService.getSeqValByName("SEQ_T_PRE_CUSTOMER_VISIT_INFO");
							preCustomerVistorInfo.setPreCustVisitorInfoId(preCustomerVisitorInfoId);
							preCustomerVistorInfo.setPreCustBaseInfoId(new Long(updatePreCustBaseInfoId));
							com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(preCustomerVistorInfo, loginInfo);
							preCustomerVistorInfoMapper.insert(preCustomerVistorInfo);
						}
					}
					resultInfo.setSuccess(true);
					resultInfo.setMsg("操作成功！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("操作失败,原因是" + e.getMessage());
			}
			return resultInfo;
				
	}

	@Override
	public ResultInfo addVerifyPreAgent(LoginInfo loginInfo) {
	ResultInfo resultInfo=new ResultInfo();
	if (loginInfo.getUserId()!=null) {
		AgentExample agentExample=new AgentExample();
		agentExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andUserIdEqualTo(loginInfo.getUserId());
		List<Agent> agents=agentMapper.selectByExample(agentExample);
		if (agents!=null&&agents.size()>0) {
		resultInfo.setSuccess(true);
		resultInfo.setMsg("验证财富顾问信息成功");
		return resultInfo;
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("您好！您不是财富顾问，无法新增准客户信息");
		return resultInfo;
		}
	}
	else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取当前操作人信息为空");
		return resultInfo;
	}
	}
	/**
	 * 准客户活动量信息查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid getAllPreCustActivityInfo(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		//登录信息
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
		//获取符合条件的行数
		Integer total = preCustomerServiceMapper.getAllPreCustActivityInfoCount(paramMap);
		List<Map> resultList = preCustomerServiceMapper.getAllPreCustActivityInfo(paramMap);
		String loginAgentId="";
		if (agentList!=null&&agentList.size()>0) {
			loginAgentId=agentList.get(0).getAgentId().toString();
		}
		List<Map> returnResultList=new ArrayList<Map>();
		if (resultList!=null&&resultList.size()>0) {
			for (Map map : resultList) {
				// 如果是操作人与客户的所属财富顾问不为同一人，则准客户姓名和电话号码需隐藏
				if (map.containsKey("agentId")) {
					String existAgentId=map.get("agentId").toString();
					if (!existAgentId.equals(loginAgentId)) {
						// 处理准客户姓名隐藏名字
						if (map.get("preCustName") != null) {
							String preCustNameExist=map.get("preCustName").toString().trim();
							String code="";
							/*for (int i = 0; i < preCustNameExist.length()-1; i++) {
								code=code+"*";
							}*/
							StringBuilder preCustName = new StringBuilder(map.get("preCustName").toString());
							//preCustName.replace(1, preCustName.length(), code);
							map.put("preCustName", preCustName);
						}
					}
				}
				returnResultList.add(map);
			}
			
		}
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * 导出准客户活动量信息明细
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public ResultInfo preCustActivityDetail(Map paramMap,LoginInfo loginInfo) {
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
			List<Map> preCustActivityDetailList = preCustomerServiceMapper.preCustActivityDetail(paramMap);
			String loginAgentId="";
			if (agentList!=null&&agentList.size()>0) {
				loginAgentId=agentList.get(0).getAgentId().toString();
			}
			List<Map> returnResultList=new ArrayList<Map>();
			if (preCustActivityDetailList!=null&&preCustActivityDetailList.size()>0) {
				for (Map map : preCustActivityDetailList) {
					// 如果是操作人与客户的所属财富顾问不为同一人，则准客户姓名和电话号码需隐藏
					String existAgentId=map.get("agentId").toString();
					if (!existAgentId.equals(loginAgentId)) {
						// 处理电话号码，隐藏中间四位
						if (map.get("preCustMobile") != null) {
							StringBuilder preCustMobile = new StringBuilder(map.get("preCustMobile").toString());
							//preCustMobile.replace(3, 7, "****");
							map.put("preCustMobile", preCustMobile);
						}
						// 处理准客户姓名隐藏名字
						if (map.get("preCustName") != null) {
							String preCustNameExist=map.get("preCustName").toString().trim();
							String code="";
							/*for (int i = 0; i < preCustNameExist.length()-1; i++) {
								code=code+"*";
							}*/
							StringBuilder preCustName = new StringBuilder(map.get("preCustName").toString());
							//preCustName.replace(1, preCustName.length(), code);
							map.put("preCustName", preCustName);
						}
					}
					returnResultList.add(map);
				}
				
			}
			if (returnResultList!=null) {
				//生成Excel
				Map<String, Object> reportMap = commonService.getReportInfo("PreCustActivityDel");
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
			resultInfo.setMsg("导出准客户活动量信息出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 
	 * 导出准客户活动量管理打分表信息
	 * @param  Map,LoginInfo
	 * @author LIWENTAO
	 * @return ResultInfo
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	public ResultInfo preCustActivityScoreManagement(Map paramMap,LoginInfo loginInfo) {
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
			
			List<Map> preCustActivityDetailList = this.preCustomerServiceMapper.queryPreCustActivityScoreManagementDetail(paramMap);
			
			if (preCustActivityDetailList!=null) {
				//生成Excel
				Map<String, Object> reportMap = commonService.getReportInfo("PreCustActGradManage");
				//对sheet名称加上当前月份
				String reportName = reportMap.get("reportName").toString();
				reportMap.put("reportName", reportName);
				if (reportMap!=null&&reportMap.size()>0&&preCustActivityDetailList!=null&&preCustActivityDetailList.size()>0) {
					reportMap.put("reportData", preCustActivityDetailList);
					reportList.add(reportMap);
				} else {
					Map<String, Object> emptyMap = new HashMap<String, Object>();
					emptyMap.put("appDownload", "");emptyMap.put("privateEquityFundIntroduction", "");emptyMap.put("baobaoFinancialIntroduction", "");emptyMap.put("selfIntroduction", "");
					emptyMap.put("weixinAccountIntroduction", "");emptyMap.put("visitDay", "");emptyMap.put("insurancefIntroduction", "");emptyMap.put("financleIntroduction, ", "");
					emptyMap.put("nameListObtained", "");emptyMap.put("agentName", "");emptyMap.put("interview", "");emptyMap.put("companyIntroduction, ", "");
					emptyMap.put("apointmentVisit", "");emptyMap.put("assetManagementIntroduction", "");
					preCustActivityDetailList.add(emptyMap);
					reportMap.put("reportData", preCustActivityDetailList);
					reportList.add(reportMap);
				}
				resultInfo.setObj(reportList);
				resultInfo.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出准客户活动量信息出现异常！");
		}
		return resultInfo;
	}
}
