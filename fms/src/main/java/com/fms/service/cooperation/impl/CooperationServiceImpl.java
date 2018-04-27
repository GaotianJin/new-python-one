package com.fms.service.cooperation.impl;

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
import com.fms.db.mapper.AgencyComMapper;
import com.fms.db.mapper.AgencyContactsMapper;
import com.fms.db.mapper.AgencyProtocolMapper;
import com.fms.db.mapper.AgencySubProtocolMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.model.AgencyCom;
import com.fms.db.model.AgencyComExample;
import com.fms.db.model.AgencyContacts;
import com.fms.db.model.AgencyContactsExample;
import com.fms.db.model.AgencyProtocol;
import com.fms.db.model.AgencyProtocolExample;
import com.fms.db.model.AgencySubProtocol;
import com.fms.db.model.AgencySubProtocolExample;
import com.fms.db.model.PDProductExample;
import com.fms.service.cooperation.CooperationService;
import com.fms.service.mapper.CooperationServiceMapper;
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
public class CooperationServiceImpl implements CooperationService {

	@Autowired
	private CommonService commonService;
	@Autowired
	private AgencyComMapper agencyComMapper;
	@Autowired
	private AgencyContactsMapper agencyContactsMapper;
	@Autowired
	private CooperationServiceMapper cooperationServiceMapper;
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private AgencySubProtocolMapper agencySubProtocolMapper;
	@Autowired
	private AgencyProtocolMapper agencyProtocolMapper;

	private static final Logger log = Logger
			.getLogger(CooperationServiceImpl.class);

	/** 新增合作机构信息 **/
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	@Transactional
	public ResultInfo addAgencyComInfo(Map tMap,String operate,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		// 相关表的主键变量
		Long agencyComSeq = null;
		AgencyCom agencyCom = (AgencyCom) tMap.get("agencyCom");
		List<AgencyContacts> agencyComContactsList = new ArrayList<AgencyContacts>();
		agencyComContactsList = (List<AgencyContacts>) tMap.get("agencyContactsInfolist");

		// 校验合作机构
		this.verifyAgencyCode(agencyCom, operate);
		// 校验联系人信息
		this.verifyContacts(agencyCom,agencyComContactsList);

		// 保存基本信息
		if (agencyCom != null) {
			agencyComSeq = agencyCom.getAgencyComId();
			if(agencyComSeq==null){
				//agencyComSeq = commonService.getSeqValByName("SEQ_T_AGENCY_COM");
				//agencyCom.setAgencyComId(agencyComSeq);
				BeanUtils.insertObjectSetOperateInfo(agencyCom, loginInfo);
				agencyComMapper.insert(agencyCom);
				log.info(agencyCom);
			}else {
				AgencyCom existAgencyCom = agencyComMapper.selectByPrimaryKey(agencyComSeq);
				BeanUtils.deleteAndInsertObjectSetOperateInfo(existAgencyCom,agencyCom, loginInfo);
				agencyComMapper.updateByPrimaryKey(agencyCom);
			}
		}
		agencyComSeq = agencyCom.getAgencyComId();
		AgencyContactsExample agencyContactsExample = new AgencyContactsExample();
		AgencyContactsExample.Criteria criteria = agencyContactsExample.createCriteria();
		criteria.andAgencyComIdEqualTo(agencyComSeq).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<AgencyContacts> existAgencyContacts = agencyContactsMapper.selectByExample(agencyContactsExample);
		for (AgencyContacts existAgencyContact:existAgencyContacts) {
			BeanUtils.deleteObjectSetOperateInfo(existAgencyContact, loginInfo);
			agencyContactsMapper.updateByPrimaryKey(existAgencyContact);
		}
		// 保存联系人信息
		for (AgencyContacts agencyContacts:agencyComContactsList) {
			Long agencyComContactsSeq = commonService.getSeqValByName("SEQ_T_AGENCY_CONTACTS");
			agencyContacts.setAgencyContactsId(agencyComContactsSeq);
			agencyContacts.setAgencyComId(agencyComSeq);
			BeanUtils beaUtils = new BeanUtils();
			beaUtils.insertObjectSetOperateInfo(agencyContacts, loginInfo);
			agencyContactsMapper.insert(agencyContacts);
		}
		resultInfo.setSuccess(true);
		resultInfo.setObj(agencyCom);
		return resultInfo;
	}

	/** 查询合作机构信息 **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Map<String, Object> getAgencyComPageList(DataGridModel dgm,
			AgencyCom agencyCom) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		Map paramMap = new HashMap();
		if (agencyCom != null) {
			paramMap.put("agencyComId", agencyCom.getAgencyCode());
			paramMap.put("agencyType", agencyCom.getAgencyType());
			paramMap.put("agencyName", agencyCom.getAgencyName());
		}
		paramMap.put("startIndex", dgm.getStartIndex());// 获取页
		paramMap.put("endIndex", dgm.getEndIndex());// 获取止页
		Integer total = cooperationServiceMapper.agencyComListCount(paramMap);
		List<Map> resultList = cooperationServiceMapper
				.agencyComListPage(paramMap);
		result.put("total", total);
		result.put("rows", resultList);
		return result;
	}

	/** 获取合作机构详细信息返回到修改页面 **/
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public ResultInfo queryAgencyComInfo(String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> agencyComInfoMap = new HashMap<String, Object>();
		try {
			// 1.获取合作机构基本信息
			AgencyCom agencyComBaseInfo = getAgencyComBaseInfo(param);
			if (agencyComBaseInfo != null) {
				agencyComInfoMap.put("agencyComBaseInfo",
						JsonUtils.objectToMap(agencyComBaseInfo));
			}
			// 2.获取合作机构联系人信息
			List<Map> agencyComContactsInfo = getAgencyComContactsInfo(param);
			if (agencyComContactsInfo != null
					&& agencyComContactsInfo.size() > 0) {
				agencyComInfoMap.put("agencyComContactsInfo",
						agencyComContactsInfo);
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(agencyComInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取到合作机构信息出现异常");
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * @param paramMap
	 * @return
	 */
	private AgencyCom getAgencyComBaseInfo(String agencyComId) {
		AgencyCom agencyComBaseInfo = new AgencyCom();
		try {
			agencyComBaseInfo = agencyComMapper
					.selectByPrimaryKey(new Long(agencyComId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencyComBaseInfo;
	}

	/**
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getAgencyComContactsInfo(String param) {
		List<Map> agencyComContactsInfoList = new ArrayList<Map>();
		Map queryParam = new HashMap();
		queryParam.put("agencyComId", param);
		try {
			agencyComContactsInfoList = cooperationServiceMapper
					.getAgencyComContactsInfoList(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencyComContactsInfoList;
	}

	/** 修改并保存合作机构信息 **/
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	@Transactional
	public void updateAgencyComInfo(Map tMap, LoginInfo loginInfo) {
		// 相关表的主键变量
		AgencyCom agencyCom = (AgencyCom) tMap.get("agencyCom");
		String modifyAgencyComId = tMap.get("modifyAgencyComId").toString();

		this.verifyAgencyCode(agencyCom, "");

		// 保存基本信息
		if (agencyCom != null && modifyAgencyComId != null) {

			// 找到原来的机构对象
			AgencyCom exitAgencyCom = new AgencyCom();
			exitAgencyCom = agencyComMapper.selectByPrimaryKey(new Long(
					modifyAgencyComId));
			agencyCom.setAgencyComId(new Long(modifyAgencyComId));
			BeanUtils beanUtils = new BeanUtils();
			beanUtils.deleteAndInsertObjectSetOperateInfo(exitAgencyCom,
					agencyCom, loginInfo);
			beanUtils.updateObjectSetOperateInfo(agencyCom, loginInfo);
			agencyComMapper.updateByPrimaryKey(agencyCom);
		}
		// 将该合作机构对应的原联系人信息批量删除
		AgencyContactsExample agencyContactsExample = new AgencyContactsExample();
		agencyContactsExample.createCriteria().andAgencyComIdEqualTo(
				new Long(modifyAgencyComId));
		List existAgencyContactsList = agencyContactsMapper
				.selectByExample(agencyContactsExample);
		for (int i = 0; i < existAgencyContactsList.size(); i++) {
			AgencyContacts existAgencyContacts = new AgencyContacts();
			existAgencyContacts = (AgencyContacts) existAgencyContactsList
					.get(i);
			BeanUtils beaUtils = new BeanUtils();
			beaUtils.deleteObjectSetOperateInfo(existAgencyContacts, loginInfo);
			agencyContactsMapper
					.updateByPrimaryKeySelective(existAgencyContacts);

		}

		// 批量删除后在重新修改并保存修改的联系人信息
		List<AgencyContacts> agencyComContactsList = new ArrayList<AgencyContacts>();
		agencyComContactsList = (List<AgencyContacts>) tMap.get("agencyContactsInfolist");
		verifyContacts(agencyCom,agencyComContactsList);
		for (int i = 0; i < agencyComContactsList.size(); i++) {
			Long agencyComContactsSeq = commonService
					.getSeqValByName("SEQ_T_AGENCY_CONTACTS");
			AgencyContacts agencyContacts = (AgencyContacts) agencyComContactsList
					.get(i);
			agencyContacts.setAgencyContactsId(agencyComContactsSeq);
			agencyContacts.setAgencyComId(new Long(modifyAgencyComId));
			BeanUtils beaUtils = new BeanUtils();
			beaUtils.insertObjectSetOperateInfo(agencyContacts, loginInfo);
			agencyContactsMapper.insert(agencyContacts);
		}
	}

	/** 校验合作机构信息是否删除 **/
	@SuppressWarnings("rawtypes")
	@Transactional
	public Integer checkDeleteAgencycom(Long agencyComid) {
		PDProductExample pdProductExample = new PDProductExample();
		pdProductExample.createCriteria().andAgencyComIdEqualTo(agencyComid);
		List productList = pdProductMapper.selectByExample(pdProductExample);
		if (productList != null && productList.size() > 0) {

			return productList.size();
		}
		return null;
	}

	/** 合作机构信息删除 **/
	@SuppressWarnings({ "static-access", "rawtypes" })
	@Transactional
	public ResultInfo deleteAgencyCom(Long agencyComId, LoginInfo loginInfo) {
		BeanUtils beanUtils = new BeanUtils();
		ResultInfo resultInfo=new ResultInfo();
		//0.先判断该合作机构下是否有产品或者有有效的协议
		if(agencyComId!=null){
			List productList=queryProductByAgencyComId(agencyComId.toString());
			if(productList!=null&&productList.size()>0){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该合作机构下存在在售的产品，不允许删除！");
				return resultInfo;
			}
			List protoclolList=queryAgencyProtocolByAgencyComId(agencyComId.toString());
			if(protoclolList!=null&&protoclolList.size()>0){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该合作机构下存在有效的合作机构协议信息，不允许删除！");
				return resultInfo;
			}
		  }
		
		if (agencyComId != null) {
			// 1.先根据合作机构Id找到相关联的子表：合作机构联系人信息T_AGENCY_CONTACTS和合作机构协议信息表T_AGENCY_PROTOCOL
			AgencyContactsExample agencyContactsExample = new AgencyContactsExample();
			agencyContactsExample.createCriteria().andAgencyComIdEqualTo(agencyComId);
			List agencyComContactList = agencyContactsMapper.selectByExample(agencyContactsExample);
			// 删除合作机构联系人信息
			for (int i = 0; i < agencyComContactList.size(); i++) {
				AgencyContacts exisAgencyContacts = (AgencyContacts) agencyComContactList.get(i);
				beanUtils.deleteObjectSetOperateInfo(exisAgencyContacts,loginInfo);
				agencyContactsMapper.updateByPrimaryKey(exisAgencyContacts);
			}

			// 删除合作机构信息
			AgencyCom exisAgencyCom = agencyComMapper.selectByPrimaryKey(agencyComId);
			beanUtils.deleteObjectSetOperateInfo(exisAgencyCom, loginInfo);
			agencyComMapper.updateByPrimaryKey(exisAgencyCom);
		    resultInfo.setSuccess(true);
		    resultInfo.setMsg("操作成功！");
		}
		
		return resultInfo;

	}
	
	// 根据合作机构Id查询已存在的产品
	@SuppressWarnings({ "rawtypes" })
	private List queryProductByAgencyComId(String agencyComId) {
		 List productList=null;
		if(agencyComId!=null){
			PDProductExample pdProductExample=new PDProductExample();
			pdProductExample.createCriteria().andRcStateEqualTo("E").andAgencyComIdEqualTo(new Long(agencyComId)).andSalesStatusEqualTo("0");
			productList=pdProductMapper.selectByExample(pdProductExample);
		}
	return productList;
	
   }

	// 根据合作机构查询已存在的合作机构协议
	@SuppressWarnings({ "rawtypes"})
	private List queryAgencyProtocolByAgencyComId(String agencyComId) {
		List protocolList = null;
		if (agencyComId != null) {
			AgencyProtocolExample agencyProtocolExample = new AgencyProtocolExample();
			agencyProtocolExample.createCriteria().andRcStateEqualTo("E").andAgencyComIdEqualTo(new Long(agencyComId));
			protocolList = agencyProtocolMapper.selectByExample(agencyProtocolExample);
		}

		return protocolList;
	}
	
	
	
	
	/** 合作机构协议新增 **/
	@SuppressWarnings({ "rawtypes", "static-access" })
	@Transactional
	public String addAgencyComProtocolInfo(Map tMap, LoginInfo loginInfo) {
		Long agencyComSubProtocolseq = null;
		Long agencyComProtocolseq = null;
		BeanUtils beanUtils = new BeanUtils();
		String protocolId = (String)tMap.get("protocolId");

	
			AgencyProtocol agencyProtocol = (AgencyProtocol) tMap.get("agencyProtocol");
			//verifyProtocolCode(agencyProtocol.getProtocolCode(), protocolId, "12","INSERT");
			// T_AGENCY_PROTOCOL
			if (protocolId==null||"".equals(protocolId)) {
				//agencyComProtocolseq = commonService.getSeqValByName("SEQ_T_AGENCY_PROTOCOL");
				//agencyProtocol.setAgencyProtocolId(agencyComProtocolseq);
				beanUtils.insertObjectSetOperateInfo(agencyProtocol, loginInfo);
				agencyProtocolMapper.insert(agencyProtocol);	
				protocolId = agencyProtocol.getAgencyProtocolId().toString();
			}else{
				AgencyProtocol existAgencyProtocol = agencyProtocolMapper.selectByPrimaryKey(new Long(protocolId));
				agencyProtocol.setAgencyProtocolId(new Long(protocolId));
				beanUtils.deleteAndInsertObjectSetOperateInfo(existAgencyProtocol,agencyProtocol, loginInfo);
				agencyProtocolMapper.updateByPrimaryKey(agencyProtocol);
			}			
		
		return protocolId;

	}

	/** 校验协议编码唯一新增 **/
	public void verifyProtocolCode(String protocolCode, String protocolId,
			String protocolType, String action) {
		if (protocolType.equals("3")) {
			AgencySubProtocolExample agencySubProtocolExample = new AgencySubProtocolExample();
			AgencySubProtocolExample.Criteria criteriaSub = agencySubProtocolExample.createCriteria();
			criteriaSub.andSubprotocolCodeEqualTo(protocolCode);
			criteriaSub.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			if (protocolId!=null&&!"".equals(protocolId)) {
				criteriaSub.andAgenctSubProtocolIdNotEqualTo(new Long(protocolId));
			}
			List<AgencySubProtocol> agencySubProtocolsList = agencySubProtocolMapper.selectByExample(agencySubProtocolExample);
			if (agencySubProtocolsList.size() > 0) {
				log.info("子协议");
				throw new CisCoreException("该协议编码已经存在");
			}

		} else {
			AgencyProtocolExample agencyProtocolExample = new AgencyProtocolExample();
			AgencyProtocolExample.Criteria criteria = agencyProtocolExample.createCriteria();
			criteria.andProtocolCodeEqualTo(protocolCode);
			if (protocolId!=null&&!"".equals(protocolId)) {
				criteria.andAgencyProtocolIdNotEqualTo(new Long(protocolId));
			}
			criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgencyProtocol> agencyProtocolsList = agencyProtocolMapper.selectByExample(agencyProtocolExample);
			if (agencyProtocolsList.size() > 0) {
				log.info("非协议");
				throw new CisCoreException("该协议编码已经存在");
			}

		}
	}

	// 校验联系人信息不能重复
	private void verifyContacts(AgencyCom agencyCom,List<AgencyContacts> ContactsList) {
		int firstContactNum = 0;
		int existFirstContactNum = 0;
		AgencyContactsExample agencyContactsExample = new AgencyContactsExample();
		AgencyContactsExample.Criteria criteria = agencyContactsExample.createCriteria();
		Long agencyComId = agencyCom.getAgencyComId();
		//数据库中的第一联系人
		if(agencyComId!=null){
			criteria.andAgencyComIdEqualTo(agencyComId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<AgencyContacts> existAgencyContacts = agencyContactsMapper.selectByExample(agencyContactsExample);
			for (AgencyContacts agencyContacts:existAgencyContacts) {
				String agencyLevel = agencyContacts.getAgencyLevel();
				if(agencyLevel!=null&&!"".equals(agencyLevel)&&"01".equals(agencyLevel)){
					existFirstContactNum++;
				}
			}
		}
		//页面传入的第一联系人数量
		if (ContactsList != null & ContactsList.size() > 0) {
			for (AgencyContacts agencyContacts:ContactsList) {
				String agencyLevel = agencyContacts.getAgencyLevel();
				Long agencyContactsId = agencyContacts.getAgencyContactsId();
				if (agencyLevel!=null&&!"".equals(agencyLevel)&&"01".equals(agencyLevel)) {
					firstContactNum++;
					if(agencyContactsId==null){
						existFirstContactNum++;
					}
				}
			}
		}

		if (firstContactNum > 1||existFirstContactNum>1) {
			log.info("只能有一个第一联系人！！！");
			throw new CisCoreException("只能有一个第一联系人");
		 }

	}
		

	/** 校验合作机构编码唯一 **/
	public void verifyAgencyCode(AgencyCom agencyCom,String operate) {
		AgencyComExample agencyComExample = new AgencyComExample();
		String agencyCode = agencyCom.getAgencyCode();
		Long agencyComId = agencyCom.getAgencyComId();
		AgencyComExample.Criteria criteria = agencyComExample.createCriteria();
		criteria.andAgencyCodeEqualTo(agencyCode).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		if (agencyComId!=null) {
			criteria.andAgencyComIdNotEqualTo(agencyComId);
		}

		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<AgencyCom> agencyComsList = agencyComMapper.selectByExample(agencyComExample);

		if (agencyComsList.size() > 0) {
			log.info("作机构编码已经");
			throw new CisCoreException("该合作机构编码已经存在");
		}
	}

	/** 合作机构协议初始信息列表 **/
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public DataGrid getAgencyComProtocolPageList(DataGridModel dgm,
			Map<String, String> paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		if (paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		paramMap.put("startIndex", String.valueOf(dgm.getStartIndex()));// 获取页
		paramMap.put("endIndex", String.valueOf(dgm.getEndIndex()));// 获取止页
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		paramMap.put("rolePivilege", loginInfo.getRolePivilege().toString());

		Integer total = cooperationServiceMapper
				.agencyComProtocolListCount(paramMap);
		List<Map> resultList = cooperationServiceMapper
				.agencyComProtocolListPage(paramMap);
		dataGrid.setTotal(total);
		dataGrid.setRows(resultList);
		return dataGrid;
	}

	/** 合作机构协议获取修改信息 **/
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResultInfo queryAgencyComProtocolInfo(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> agencyComProtocolInfoMap = new HashMap<String, Object>();
		// 协议类型
		String modifyProtocolType = tMap.get("modifyProtocolType").toString();
		// 合作机构Id
		String modifyAgencyComId = tMap.get("modifyAgencyComId").toString();
		// 协议流水号
		String modifyProtocolId = tMap.get("modifyProtocolId").toString();

		try {
			if (modifyProtocolType != null) {
					// 1.获得框架协议和普通协议的信息
					AgencyProtocol agencyProtocol = getAgencyComProtocolInfo(modifyProtocolId);
					// 2.获得该协议的合作机构信息
					AgencyCom agencyCom = getAgencyComBaseInfo(modifyAgencyComId);
					// 3.获取上传的附件信息?????????????????

					agencyComProtocolInfoMap.put("agencyProtocol",
							JsonUtils.objectToMap(agencyProtocol));
					agencyComProtocolInfoMap.put("agencyCom",
							JsonUtils.objectToMap(agencyCom));
			}

			resultInfo.setSuccess(true);
			resultInfo.setObj(agencyComProtocolInfoMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取到合作机构协议信息出现异常");
			return resultInfo;
		}
		return resultInfo;
	}

	/**
	 * @param paramMap
	 * @return
	 */
	private AgencyProtocol getAgencyComProtocolInfo(String modifyProtocolId) {
		AgencyProtocol agencyProtocolInfo = new AgencyProtocol();
		try {
			agencyProtocolInfo = agencyProtocolMapper
					.selectByPrimaryKey(new Long(modifyProtocolId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencyProtocolInfo;
	}

	/**
	 * @param paramMap
	 * @return
	 */
	private AgencySubProtocol getAgencyComSubProtocolInfo(
			String modifyProtocolId) {
		AgencySubProtocol agencySubProtocolInfo = new AgencySubProtocol();
		try {
			agencySubProtocolInfo = agencySubProtocolMapper
					.selectByPrimaryKey(new Long(modifyProtocolId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencySubProtocolInfo;
	}

	/** 修改并保存合作机构协议信息 **/
	@SuppressWarnings({ "rawtypes", "static-access" })
	@Transactional
	public void updateAgencyComProtocolInfo(Map tMap, LoginInfo loginInfo) {
		BeanUtils beanUtils = new BeanUtils();
		String protocolType = tMap.get("protocolType") != null ? tMap.get(
				"protocolType").toString() : "";
		String protocolId = tMap.get("protocolId") != null ? tMap.get(
				"protocolId").toString() : "";
		// 修改子协议
		if (protocolType == "3" || protocolType.equals("3")) {
			AgencySubProtocolExample agencySubProtocolExample = new AgencySubProtocolExample();
			agencySubProtocolExample.createCriteria()
					.andAgenctSubProtocolIdEqualTo(new Long(protocolId))
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			// 查出系统存在的协议信息
			AgencySubProtocol agencyExisAgencySubProtocol = (AgencySubProtocol) agencySubProtocolMapper
					.selectByExample(agencySubProtocolExample).get(0);
			// 获取修改的协议信息
			AgencySubProtocol newAgencySubProtocol = (AgencySubProtocol) tMap
					.get("agencySubProtocolInfo");

			verifyProtocolCode(newAgencySubProtocol.getSubprotocolCode(),
					protocolId, "3", "UPDATE");

			newAgencySubProtocol.setAgenctSubProtocolId(new Long(
					protocolId));
			beanUtils.deleteAndInsertObjectSetOperateInfo(
					agencyExisAgencySubProtocol, newAgencySubProtocol,
					loginInfo);
			beanUtils.updateObjectSetOperateInfo(newAgencySubProtocol,
					loginInfo);
			agencySubProtocolMapper.updateByPrimaryKey(newAgencySubProtocol);
		} else {
			AgencyProtocolExample agencyProtocolExample = new AgencyProtocolExample();
			agencyProtocolExample.createCriteria()
					.andAgencyProtocolIdEqualTo(new Long(protocolId))
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			// 取出系统中已经存在的协议信息
			AgencyProtocol agencyExisAgencyProtocol = (AgencyProtocol) agencyProtocolMapper.selectByExample(agencyProtocolExample).get(0);
			// 获得修改的协议信息
			AgencyProtocol newAgencyProtocol = (AgencyProtocol) tMap.get("agencyProtocol");

			verifyProtocolCode(newAgencyProtocol.getProtocolCode(),protocolId, "12", "UPDATE");

			newAgencyProtocol.setAgencyProtocolId(new Long(protocolId));
			beanUtils.deleteAndInsertObjectSetOperateInfo(agencyExisAgencyProtocol, newAgencyProtocol, loginInfo);
			beanUtils.updateObjectSetOperateInfo(newAgencyProtocol, loginInfo);
			agencyProtocolMapper.updateByPrimaryKey(newAgencyProtocol);
		}

	}

	/** 删除并保存合作机构协议信息 **/
	@SuppressWarnings({ "rawtypes", "static-access" })
	@Transactional
	public ResultInfo deleteAgencyComProtocol(Map tMap, LoginInfo loginInfo) {
		BeanUtils beanUtils = new BeanUtils();
		String protocolType = tMap.get("potocolType").toString();
		String protocolId = tMap.get("protocolId").toString();
		ResultInfo resultInfo=new ResultInfo();
		// 子协议直接删除相关信息
		if (protocolType.equals("3")) {
			AgencySubProtocol agencyExisSubProtocol = agencySubProtocolMapper.selectByPrimaryKey(new Long(protocolId));
			beanUtils.deleteObjectSetOperateInfo(agencyExisSubProtocol,loginInfo);
			agencySubProtocolMapper.updateByPrimaryKey(agencyExisSubProtocol);
		} 
		//1-框架协议
		else  if(protocolType.equals("1")){
			List agencySubProtoList=querySubProList(protocolId);
			if(agencySubProtoList!=null&&agencySubProtoList.size()>0){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该框架协议下面存在子协议，不允许删除！");
				
				return  resultInfo;
			}
			else{
				
				AgencyProtocol agencyExisProtocol = agencyProtocolMapper.selectByPrimaryKey(new Long(protocolId));
				beanUtils.deleteObjectSetOperateInfo(agencyExisProtocol, loginInfo);
				agencyProtocolMapper.updateByPrimaryKey(agencyExisProtocol);
			}
			
		}
		else 
		{
			AgencyProtocol agencyExisProtocol = agencyProtocolMapper.selectByPrimaryKey(new Long(protocolId));
			beanUtils.deleteObjectSetOperateInfo(agencyExisProtocol, loginInfo);
			agencyProtocolMapper.updateByPrimaryKey(agencyExisProtocol);
		}
		
		resultInfo.setSuccess(true);
		resultInfo.setMsg("操作成功！");
		return resultInfo;
	}
	
	
	@SuppressWarnings("rawtypes")
	public List querySubProList(String id ){
		List agencysubList=null;
		if(id!=null){
			AgencySubProtocolExample agencySubProtocolExample=new AgencySubProtocolExample();
			agencySubProtocolExample.createCriteria().andRcStateEqualTo("E").andAgencyProtocolIdEqualTo(new Long(id));
			agencysubList=agencySubProtocolMapper.selectByExample(agencySubProtocolExample);
		}
		return  agencysubList;
	}
}
