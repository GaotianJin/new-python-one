package com.fms.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.AgencyComMapper;
import com.fms.db.mapper.AgencyProtocolMapper;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.BasicLawMapper;
import com.fms.db.mapper.DefAddressMapper;
import com.fms.db.mapper.DefDepartmentMapper;
import com.fms.db.mapper.DefStoreMapper;
import com.fms.db.mapper.PDContractDetailMapper;
import com.fms.db.mapper.PDContractMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.model.AgencyCom;
import com.fms.db.model.AgencyComExample;
import com.fms.db.model.AgencyProtocol;
import com.fms.db.model.AgencyProtocolExample;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.BasicLaw;
import com.fms.db.model.BasicLawExample;
import com.fms.db.model.DefAddress;
import com.fms.db.model.DefAddressExample;
import com.fms.db.model.DefDepartment;
import com.fms.db.model.DefDepartmentExample;
import com.fms.db.model.DefStore;
import com.fms.db.model.DefStoreExample;
import com.fms.db.model.PDContract;
import com.fms.db.model.PDContractDetail;
import com.fms.db.model.PDContractDetailExample;
import com.fms.db.model.PDContractExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.service.common.CodeQueryService;
import com.fms.service.mapper.CodeQueryServiceMapper;
import com.sinosoft.core.db.mapper.DefBankMapper;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.model.DefBank;
import com.sinosoft.core.db.model.DefBankExample;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.util.LoginInfo;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CodeQueryServiceImpl implements CodeQueryService {

	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private PDProductMapper pDProductMapper;
	@Autowired
	private AgencyComMapper agencyComMapper;
	@Autowired
	private DefAddressMapper defAddressMapper;
	@Autowired
	private DefStoreMapper defStoreMapper;
	@Autowired
	private DefDepartmentMapper defDepartmentMapper;
	@Autowired
	private BasicLawMapper basicLawMapper;
	@Autowired
	private AgencyProtocolMapper agencyProtocolMapper;
	@Autowired
	private DefBankMapper defBankMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private CodeQueryServiceMapper codeQueryServiceMapper;
    @Autowired
    private PDContractMapper pdContractMapper;
    @Autowired
    private PDContractDetailMapper pdContractDetailMapper;
	
	@Transactional
	public List<Map<String, String>> tdCodeQuery(String codeType) {

		DefCodeExample defCodeExample = new DefCodeExample();
		defCodeExample.setOrderByClause("code");
		defCodeExample.createCriteria().andCodeTypeEqualTo(codeType);
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		List<Map<String, String>> codeMapList = new ArrayList<Map<String, String>>();
		for (DefCode defCode : defCodeList) {
			Map<String, String> codeMap = new HashMap<String, String>();
			String code = defCode.getCode();
			String codeName = defCode.getCodeName();
			codeMap.put("code", code);
			codeMap.put("codeName", code + "-" + codeName);
			// codeMap.put("codeName", codeName);
			codeMap.put("name", codeName);
			codeMapList.add(codeMap);
		}
		return codeMapList;
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List<Map<String, String>> codeQueryByParam(Map paramMap) {

		DefCodeExample defCodeExample = new DefCodeExample();
		defCodeExample.setOrderByClause("code");
		String codeType = paramMap.get("codeType").toString();
		String businessType = paramMap.get("businessType").toString();
		System.out.println("codeType:" + codeType + "businessType" + businessType);

		defCodeExample.createCriteria().andCodeTypeEqualTo(codeType).andOtherSignEqualTo(businessType);
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		List<Map<String, String>> codeMapList = new ArrayList<Map<String, String>>();
		for (DefCode defCode : defCodeList) {
			Map<String, String> codeMap = new HashMap<String, String>();
			String code = defCode.getCode();
			String codeName = defCode.getCodeName();
			codeMap.put("code", code);
			codeMap.put("codeName", code + "-" + codeName);
			// codeMap.put("codeName", codeName);
			codeMap.put("name", codeName);
			codeMapList.add(codeMap);
		}
		return codeMapList;
	}

	public List<Map<String, String>> tdCodeQueryIn(String codeType, List<String> codeList) {

		DefCodeExample defCodeExample = new DefCodeExample();
		defCodeExample.setOrderByClause("code");
		defCodeExample.createCriteria().andCodeTypeEqualTo(codeType).andCodeIn(codeList);
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		List<Map<String, String>> codeMapList = new ArrayList<Map<String, String>>();
		for (DefCode defCode : defCodeList) {
			Map<String, String> codeMap = new HashMap<String, String>();
			String code = defCode.getCode();
			String codeName = defCode.getCodeName();
			codeMap.put("code", code);
			// codeMap.put("codeName", code + "-" + codeName);
			codeMap.put("codeName", codeName);
			codeMap.put("name", codeName);
			codeMapList.add(codeMap);
		}
		return codeMapList;
	}

	// @Transactional
	// public List<Map<String, String>> tdCodeNameQuery(String codeType) {
	//
	// DefCodeExample defCodeExample = new DefCodeExample();
	// defCodeExample.setOrderByClause("code");
	// defCodeExample.createCriteria().andCodeTypeEqualTo(codeType);
	// List<DefCode> defCodeList = defCodeMapper
	// .selectByExample(defCodeExample);
	// List<Map<String, String>> codeMapList = new ArrayList<Map<String,
	// String>>();
	// for (DefCode defCode : defCodeList) {
	// Map<String, String> codeMap = new HashMap<String, String>();
	// String code = defCode.getCode();
	// String codeName = defCode.getCodeName();
	// codeMap.put("code", code);
	// codeMap.put("codeName", code + "-" + codeName);
	// codeMap.put("name", codeName);
	// codeMapList.add(codeMap);
	// }
	// return codeMapList;
	// }

	/**
	 * ??T_PD_Product??????????????
	 */
	@Transactional
	public List<Map<String, String>> tdProductQuery() {
		PDProductExample pDProductExample = new PDProductExample();
		pDProductExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<PDProduct> pdProductList = pDProductMapper.selectByExample(pDProductExample);
		List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
		for (PDProduct pDProduct : pdProductList) {
			Map<String, String> productMap = new HashMap<String, String>();
			String productId = String.valueOf(pDProduct.getProductId());
			String productCode = pDProduct.getProductCode();
			String productName = pDProduct.getProductName();
			productMap.put("code", productId);
			productMap.put("codeName", productCode + "-" + productName);
			productMapList.add(productMap);
		}
		return productMapList;
	}

	/**
	 * ??T_AGENCY_COM??????????????
	 */
	@Transactional
	public List<Map<String, String>> tAgencyQuery() {
		AgencyComExample agencyComExample = new AgencyComExample();
		agencyComExample.createCriteria().andRcStateEqualTo("E");
		List<AgencyCom> agencyList = agencyComMapper.selectByExample(agencyComExample);
		List<Map<String, String>> agencyMapList = new ArrayList<Map<String, String>>();
		for (AgencyCom agencyCom : agencyList) {
			Map<String, String> angencyMap = new HashMap<String, String>();
			String agencyComId = agencyCom.getAgencyComId().toString();
			String agencyCode = agencyCom.getAgencyCode();
			String agencyName = agencyCom.getAgencyName();
			angencyMap.put("code", agencyComId);
			angencyMap.put("codeName", agencyCode + "-" + agencyName);
			agencyMapList.add(angencyMap);
		}
		return agencyMapList;
	}

	/**
	 * ???agentCode???agentId,???agentId???T_PD_Product???2???????????????
	 */
	@Transactional
	public List<Map<String, String>> tdProductQuery(String agentComdId) {
		// ?????????id.
		AgencyComExample agencyComExample = new AgencyComExample();
		agencyComExample.createCriteria().andAgencyComIdEqualTo(new Long(agentComdId));
		List<AgencyCom> agencyList = agencyComMapper.selectByExample(agencyComExample);
		if (agencyList != null) {
			// ????agentcomId???????????????????????
			PDProductExample pDProductExample = new PDProductExample();
			System.out.println("----:" + agencyList.get(0));
			pDProductExample.createCriteria().andAgencyComIdEqualTo(agencyList.get(0).getAgencyComId());
			List<PDProduct> pdProductList = pDProductMapper.selectByExample(pDProductExample);
			List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
			for (PDProduct pDProduct : pdProductList) {
				Map<String, String> productMap = new HashMap<String, String>();
				String productCode = pDProduct.getProductCode();
				String productId = pDProduct.getProductId().toString();
				String productName = pDProduct.getProductName();
				productMap.put("code", productId);
				productMap.put("codeName", productCode + "-" + productName);
				productMapList.add(productMap);
			}
			return productMapList;

		}
		return null;
	}

	@Override
	public List<Map<String, String>> addressCodeQuery(String placeType, String upPlaceCode) {
		DefAddressExample defAddressExample = new DefAddressExample();
		defAddressExample.setOrderByClause("place_Code");
		DefAddressExample.Criteria criteria = defAddressExample.createCriteria();
		criteria.andPlaceTypeEqualTo(placeType);
		if (upPlaceCode != null && !"".equals(upPlaceCode)) {
			criteria.andUpPlaceCodeEqualTo(upPlaceCode);
		}
		List<DefAddress> defAddressList = defAddressMapper.selectByExample(defAddressExample);
		List<Map<String, String>> addressMapList = new ArrayList<Map<String, String>>();
		for (DefAddress defAddress : defAddressList) {
			Map<String, String> addressMap = new HashMap<String, String>();
			String placeCode = defAddress.getPlaceCode();
			String placeName = defAddress.getPlaceName();
			addressMap.put("placeCode", placeCode);
			addressMap.put("placeName", placeCode + "-" + placeName);
			addressMapList.add(addressMap);
		}
		return addressMapList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map<String, String>> comCodeQuery(LoginInfo loginInfo) {
		Map paramMap = new HashMap();
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
		List<Map<String, String>> comMapList = codeQueryServiceMapper.getDefComInfo(paramMap);
		return comMapList;
	}

	/**
	 * ��T_DEF_STORE���л�ȡ�������
	 * 
	 * @return
	 */
/*	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, String>> storeCodeQuery(LoginInfo loginInfo) {
		Map paramMap = new HashMap();
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
		List<Map<String, String>> comMapList = codeQueryServiceMapper.getDefStoreInfo(paramMap);
		return comMapList;
	}
	*/
	/**
	 * 根据comId查询网点信息，级联使用
	 * @author LIWENTAO
	 * @param  
	 * @return
	 * 
	 */
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,String>> storeCodeQueryByComId(String comId) {
		Map paramMap = new HashMap();
		paramMap.put("comId", comId);
		List<Map<String, String>> comMapList = codeQueryServiceMapper.getDefStoreInfoByComId(paramMap);
		return comMapList;
	}*/
	
	/**
	 * ��T_DEF_STORE���л�ȡ�������
	 * 
	 * @return
	 */
	public List<Map<String, String>> storeCodeQuery() {
		DefStoreExample defStoreExample = new DefStoreExample();
		defStoreExample.createCriteria().andRcStateEqualTo("E").andStateEqualTo("01");
		List<DefStore> defStoreList = defStoreMapper.selectByExample(defStoreExample);
		List<Map<String, String>> storeMapList = new ArrayList<Map<String, String>>();
		for (DefStore defStore : defStoreList) {
			Map<String, String> storeMap = new HashMap<String, String>();
			String storeId = defStore.getStoreId().toString();
			String storeCode = defStore.getStoreCode();
			String storeName = defStore.getStoreName();
			storeMap.put("id", storeId);
			storeMap.put("name", storeCode + "-" + storeName);
			storeMapList.add(storeMap);
		}
		return storeMapList;
	}

	/**
	 * ��T_AGENT���л�ȡ�������
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, String>> agentQuery(LoginInfo loginInfo) {
		Map paramMap = new HashMap();
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
		List<Map<String, String>> agentMapList = codeQueryServiceMapper.getAgentInfoList(paramMap);
		return agentMapList;
	}

	/**
	 * ��T_DEF_DEPARTMENT���л�ȡ�������
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, String>> departmentCodeQuery(LoginInfo loginInfo) {
		Map paramMap = new HashMap();
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
		paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
		paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
		List<Map<String, String>> departmentMapList = codeQueryServiceMapper.getDefDepartmentInfo(paramMap);
		return departmentMapList;
	}
	
	/**
	 * 根据comId查询网点信息，级联使用
	 * @author LIWENTAO
	 * @param  
	 * @return
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,String>> departmentCodeQueryByComId(String comId) {
		Map paramMap = new HashMap();
		paramMap.put("comId", comId);
		List<Map<String, String>> comMapList = codeQueryServiceMapper.getDefDepartmentInfoByComId(paramMap);
		return comMapList;
	}
	// protocolQuery

	/**
	 * 查询状态为有效的合作机构协议T_AGENCY_PROTOCOL
	 */
	// @Transactional
	// public List<Map<String, String>> protocolQuery() {
	// // 总返回集合
	// List<Map<String, String>> protocolMapList = new ArrayList<Map<String,
	// String>>();
	// // 查出框架协议和普通协议信息
	// List<AgencyProtocol> listAgencyProtocol = new
	// ArrayList<AgencyProtocol>();
	// AgencyProtocolExample agencyProtocolExample = new
	// AgencyProtocolExample();
	// agencyProtocolExample.createCriteria().andRcStateEqualTo("E");
	// listAgencyProtocol = agencyProtocolMapper
	// .selectByExample(agencyProtocolExample);
	// // 把查出的listAgencyProtocol框架协议和普通协议信息放入集合中
	// for (AgencyProtocol agencyProtocol : listAgencyProtocol) {
	// Map<String, String> agencyPorotocolMap = new HashMap<String, String>();
	// String agencyProtocolId = agencyProtocol.getAgencyProtocolId()
	// .toString();
	// String protocolCode = agencyProtocol.getProtocolCode();
	// String protocolName = agencyProtocol.getProtocolName();
	// agencyPorotocolMap.put("code", agencyProtocolId);
	// agencyPorotocolMap.put("codeName", protocolCode + "-"
	// + protocolName);
	// protocolMapList.add(agencyPorotocolMap);
	// }
	//
	// return protocolMapList;
	// }

	/**
	 * 查询状态为有效的框架协议
	 */
	@Transactional
	public List<Map<String, String>> protocolQuery(String comId) {
		// 总返回集合
		List<Map<String, String>> protocolMapList = new ArrayList<Map<String, String>>();
		// 查出框架协议和普通协议信息
		List<AgencyProtocol> listAgencyProtocol = new ArrayList<AgencyProtocol>();
		AgencyProtocolExample agencyProtocolExample = new AgencyProtocolExample();
		AgencyProtocolExample.Criteria criteria = agencyProtocolExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProtocolTypeEqualTo("1");
		if (comId != null && !"".equals(comId)) {
			criteria.andAgencyComIdEqualTo(new Long(comId));
		}
		listAgencyProtocol = agencyProtocolMapper.selectByExample(agencyProtocolExample);
		// 把查出的listAgencyProtocol框架协议信息放入集合中
		for (AgencyProtocol agencyProtocol : listAgencyProtocol) {
			Map<String, String> agencyPorotocolMap = new HashMap<String, String>();
			String agencyProtocolId = agencyProtocol.getAgencyProtocolId().toString();
			String protocolCode = agencyProtocol.getProtocolCode();
			String protocolName = agencyProtocol.getProtocolName();
			agencyPorotocolMap.put("code", agencyProtocolId);
			agencyPorotocolMap.put("codeName", protocolCode + "-" + protocolName);
			protocolMapList.add(agencyPorotocolMap);
		}

		return protocolMapList;
	}

	/**
	 * 从T_BASIC_LAW表中获取下拉数据
	 * 
	 * @return
	 */
	public List<Map<String, String>> basicLawVersionQuery(String execState) {
		BasicLawExample basicLawExample = new BasicLawExample();
		BasicLawExample.Criteria criteria = basicLawExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		// 版本状态，1-启用状态
		if (null != execState && !"".equals(execState) && !"01".equals(execState)) {
			criteria.andExecStateEqualTo(execState);
		}
		List<BasicLaw> basicLawList = basicLawMapper.selectByExample(basicLawExample);
		List<Map<String, String>> basicLawMapList = new ArrayList<Map<String, String>>();
		for (BasicLaw basicLaw : basicLawList) {
			Map<String, String> basicLawMap = new HashMap<String, String>();
			String basicLawtId = basicLaw.getBasicLawId().toString();
			String versionCode = basicLaw.getVersionCode();
			String versionName = basicLaw.getVersionName();
			basicLawMap.put("basicLawtId", basicLawtId);
			basicLawMap.put("versionName", versionCode + "-" + versionName);
			basicLawMapList.add(basicLawMap);
		}
		return basicLawMapList;
	}

	/**
	 * 根据产品类别，产品子类获取产品代码
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, String>> tdProductQuery(String productType, String productSubType) {
		PDProductExample pDProductExample = new PDProductExample();
		PDProductExample.Criteria criteria = pDProductExample.createCriteria();
		criteria.andProductTypeEqualTo(productType);
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		// 2 产品发布状态为完成 已发布的在售产品
		criteria.andStatusEqualTo("2").andSalesStatusEqualTo("0");
		if (productSubType != null && !"".equals(productSubType)) {
			criteria.andProductSubTypeEqualTo(productSubType);
		}

		List<PDProduct> pDProductList = pDProductMapper.selectByExample(pDProductExample);
		List<Map<String, String>> pDProductMapList = new ArrayList<Map<String, String>>();
		for (PDProduct pPProduct : pDProductList) {
			Map<String, String> pDProductMap = new HashMap<String, String>();
			String productId = String.valueOf(pPProduct.getProductId());
			String productCode = pPProduct.getProductCode();
			String productName = pPProduct.getProductName();
			pDProductMap.put("code", productId);
			pDProductMap.put("codeName", productCode + "-" + productName);
			pDProductMapList.add(pDProductMap);
		}
		return pDProductMapList;
	}

	/**
	 * ??T_PD_Product??????????????
	 */
	@Transactional
	public List<Map<String, String>> tdwealthProductQuery() {
		PDProductExample pDProductExample = new PDProductExample();
		pDProductExample.createCriteria().andRcStateEqualTo(Constants.EFFECTIVE_RECORD).andProductTypeEqualTo("1")
				.andStatusEqualTo("2");
		List<PDProduct> pdProductList = pDProductMapper.selectByExample(pDProductExample);
		List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
		for (PDProduct pDProduct : pdProductList) {
			Map<String, String> productMap = new HashMap<String, String>();
			String productId = String.valueOf(pDProduct.getProductId());
			String productCode = pDProduct.getProductCode();
			String productName = pDProduct.getProductName();
			productMap.put("code", productId);
			productMap.put("codeName", productCode + "-" + productName);
			productMapList.add(productMap);
		}
		return productMapList;
	}

	public List<Map<String, String>> productwealthQuery(String agentComdId) {
		/*
		 * AgencyComExample agencyComExample = new AgencyComExample();
		 * agencyComExample.createCriteria().andAgencyComIdEqualTo(new
		 * Long(agentComdId)); List<AgencyCom> agencyList =
		 * agencyComMapper .selectByExample(agencyComExample); if (agencyList !=
		 * null) {
		 */
		PDProductExample pDProductExample = new PDProductExample();
		PDProductExample.Criteria criteria = pDProductExample.createCriteria();
		if (agentComdId != null && !"".equals(agentComdId)) {
			criteria.andAgencyComIdEqualTo(new Long(agentComdId));
		}
		// System.out.println("----:"+agencyList.get(0));
		criteria.andProductTypeEqualTo("1").andRcStateEqualTo("E").andStatusEqualTo("2").andSalesStatusEqualTo("0");
		List<PDProduct> pdProductList = pDProductMapper.selectByExample(pDProductExample);
		List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
		for (PDProduct pDProduct : pdProductList) {
			Map<String, String> productMap = new HashMap<String, String>();
			String productCode = pDProduct.getProductCode();
			String productId = pDProduct.getProductId().toString();
			String productName = pDProduct.getProductName();
			productMap.put("code", productId);
			productMap.put("codeName", productCode + "-" + productName);
			productMapList.add(productMap);
		}
		return productMapList;

		// }
		// return null;
	}

	public List<Map<String, String>> queryBankInfo() {
		DefBankExample defBankExample = new DefBankExample();
		DefBankExample.Criteria criteria = defBankExample.createCriteria();
		criteria.andBankIdIsNotNull();
		List<DefBank> defBankList = defBankMapper.selectByExample(defBankExample);
		List<Map<String, String>> bankMapList = new ArrayList<Map<String, String>>();
		for (DefBank defBank : defBankList) {
			Map<String, String> bankMap = new HashMap<String, String>();
			String bankId = defBank.getBankId().toString();
			String bankCode = defBank.getBankCode();
			String bankName = defBank.getBankName();
			bankMap.put("bankId", bankId);
			bankMap.put("bankName", bankCode + "-" + bankName);
			bankMapList.add(bankMap);
		}
		return bankMapList;
	}

	@Override
	public List<Map<String, String>> queryAllFixedIncomeProduct(String agencyComId) {
		PDProductExample pdProductExample = new PDProductExample();
		PDProductExample.Criteria criteria = pdProductExample.createCriteria();
		pdProductExample.setOrderByClause("PRODUCT_CODE");
		if (agencyComId != null && !"".equals(agencyComId)) {
			criteria.andAgencyComIdEqualTo(new Long(agencyComId));
		}
		criteria.andProductTypeEqualTo("1").andProductSubTypeEqualTo("02").andStatusEqualTo("2")
				.andSalesStatusEqualTo("0").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<PDProduct> pdProductList = pDProductMapper.selectByExample(pdProductExample);
		List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
		for (PDProduct pDProduct : pdProductList) {
			Map<String, String> productMap = new HashMap<String, String>();
			String productCode = pDProduct.getProductCode();
			String productId = pDProduct.getProductId().toString();
			String productName = pDProduct.getProductName();
			productMap.put("code", productId);
			productMap.put("codeName", productCode + "-" + productName);
			productMapList.add(productMap);
		}
		return productMapList;
	}

	/**
	 * 获取财富顾问的信息
	 * 
	 * @param agentName
	 * @return
	 */
	public List<Map<String, String>> queryAgentInfo(String agentName) {
		List<Map<String, String>> agentMapList = new ArrayList<Map<String, String>>();
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria criteria = agentExample.createCriteria();
		if (agentName != null && !"".equals(agentName)) {
			criteria.andAgentNameLike("%" + agentName + "%").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		} else {
			return agentMapList;
		}
		List<Agent> agentList = agentMapper.selectByExample(agentExample);
		for (Agent agent : agentList) {
			Map<String, String> agentMap = new HashMap<String, String>();
			String agentCode = agent.getAgentCode();
			String agentId = agent.getAgentId().toString();
			String agentName1 = agent.getAgentName();
			agentMap.put("agentId", agentId);
			agentMap.put("agentCode", agentCode);
			agentMap.put("agentName", agentName1);
			agentMap.put("agentCodeAndName", agentCode + "-" + agentName1);
			agentMapList.add(agentMap);
		}
		return agentMapList;
	}

	@Override
	public List<Map<String, String>> queryAllAgent(String agentName) {
		List<Map<String, String>> agentMapList = new ArrayList<Map<String, String>>();
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria criteria = agentExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		if (agentName != null && !"".equals(agentName)) {
			criteria.andAgentNameLike("%" + agentName + "%");
		}
		List<Agent> agentList = agentMapper.selectByExample(agentExample);
		for (Agent agent : agentList) {
			Map<String, String> agentMap = new HashMap<String, String>();
			String agentCode = agent.getAgentCode();
			String agentId = agent.getAgentId().toString();
			String agentName1 = agent.getAgentName();
			agentMap.put("agentId", agentId);
			agentMap.put("agentCode", agentCode);
			agentMap.put("agentName", agentName1);
			agentMapList.add(agentMap);
		}
		return agentMapList;
	}

	/**
	 * 根据机构获取网点信息
	 */
	/*@Override
	public List<Map<String, String>> defStoreQuery(String codeType) {
		List<Map<String, String>> storeMapList = new ArrayList<Map<String, String>>();
		if (codeType != null && !"".equals(codeType)) {
			Long comId = new Long(codeType);
			DefStoreExample defStoreExample = new DefStoreExample();
			DefStoreExample.Criteria defStoreCriteria = defStoreExample.createCriteria();
			defStoreCriteria.andComIdEqualTo(comId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<DefStore> defStores = defStoreMapper.selectByExample(defStoreExample);
			for (DefStore defStore : defStores) {
				Map<String, String> defStoreMap = new HashMap<String, String>();
				String storeId = defStore.getStoreId().toString();
				String storeCode = defStore.getStoreCode();
				String storeName = defStore.getStoreName();
				defStoreMap.put("storeId", storeId);
				defStoreMap.put("storeName", storeCode + "-" + storeName);
				storeMapList.add(defStoreMap);
			}
			return storeMapList;

		}
		return null;
	}*/

	/**
	 * 根据机构获取业务部信息
	 */
	@Override
	public List<Map<String, String>> defDepartmentQuery(String codeType) {
		List<Map<String, String>> departmentMapList = new ArrayList<Map<String, String>>();
		if (codeType != null && !"".equals(codeType)) {
			Long comId = new Long(codeType);
			DefDepartmentExample defDepartmentExample = new DefDepartmentExample();
			DefDepartmentExample.Criteria defDepartmentCriteria = defDepartmentExample.createCriteria();
			defDepartmentCriteria.andComIdEqualTo(comId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<DefDepartment> defDepartments = defDepartmentMapper.selectByExample(defDepartmentExample);
			for (DefDepartment defDepartment : defDepartments) {
				Map<String, String> defDepartmentMap = new HashMap<String, String>();
				String departmentId = defDepartment.getDepartmentId().toString();
				String departmentCode = defDepartment.getDepartmentCode();
				String departmentName = defDepartment.getDepartmentName();
				defDepartmentMap.put("departmentId", departmentId);
				defDepartmentMap.put("departmentName", departmentCode + "-" + departmentName);
				departmentMapList.add(defDepartmentMap);
			}
			return departmentMapList;

		}
		return null;
	}

	/**
	 * 根据网点和业务部获取理财经理信息
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> limitAgentInfo(Map param) {
		List<Map<String, String>> agentMapList = new ArrayList<Map<String, String>>();
		List<Map> agents = codeQueryServiceMapper.getAgentList(param);
		for(Map map : agents){
			Map<String, String> agentMap = new HashMap<String, String>();
			String id = ((Long) map.get("agentId")).toString();
			String code  = (String) map.get("agentCode");
			String name = (String) map.get("agentName");
			agentMap.put("id", id);
			agentMap.put("name", code+"-"+name);
			agentMapList.add(agentMap);
			
		}
		return agentMapList;

	}

	/**
	 * 根据机构获取理财经理信息
	 * @param codeType
	 * @return
	 */
	@Override
	public List<Map<String, String>> defAgentQuery(String codeType) {
		List<Map<String, String>> agentMapList = new ArrayList<Map<String, String>>();
		if (codeType != null && !"".equals(codeType)) {
			Long comId = new Long(codeType);
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andComIdEqualTo(comId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agents = agentMapper.selectByExample(agentExample);
			for (Agent agent : agents) {
				Map<String, String> defDepartmentMap = new HashMap<String, String>();
				String id = agent.getAgentId().toString();
				String code = agent.getAgentCode();
				String name = agent.getAgentName();
				defDepartmentMap.put("id", id);
				defDepartmentMap.put("name", code + "-" + name);
				agentMapList.add(defDepartmentMap);
			}
			return agentMapList;

		}
		return null;
	}
	
	/**
	 * 查询理财经理信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> agentAllQuery(LoginInfo loginInfo) {
		Map paramMap = new HashMap();
		List<Map<String, String>> agentMapList = codeQueryServiceMapper.getAgentInfoList(paramMap);
		return agentMapList;
	}
	
	/**
	 * 根据agentId查询rm名下客户
	 * @author ZYM
	 * @param  agentId
	 * @return
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,String>> customerQueryByUserId(String userId) {
		Map paramMap = new HashMap();
		paramMap.put("userId", userId);
		List<Map<String, String>> comMapList = codeQueryServiceMapper.getCustNameByUserId(paramMap);
		return comMapList;
	}
	
	//	根据productId获取合同号
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String,String>> contractNumberAllQuery(String productId) {
		Map paramMap = new HashMap();
		if("".equals(productId) || productId == null){
			List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
			return returnList;
		}
		//所有共享相同合同的产品  根据产品ID查询所有 对象合同编码的产品号  取第一个作为基准
		PDContractExample pdContractExample = new PDContractExample();
		pdContractExample.createCriteria().andProductIdEqualTo(Long.parseLong(productId)).andRcStateEqualTo("E");
		List<PDContract> pdContractList = pdContractMapper.selectByExample(pdContractExample);
		if (pdContractList.isEmpty() || pdContractList.size() < 1) {
			List<Map<String,String>> returnList = new ArrayList<Map<String,String>>();
			return returnList;
		}
		String contractCode = pdContractList.get(0).getContractCode();
		PDContractExample pdContractExampleNew = new PDContractExample();
		pdContractExampleNew.createCriteria().andContractCodeEqualTo(contractCode).andRcStateEqualTo("E");
		List<PDContract> pdContracts = pdContractMapper.selectByExample(pdContractExampleNew);
		productId = pdContracts.get(0).getProductId().toString();
		
		paramMap.put("productId", productId);
		List<Map<String, String>> numMapList = codeQueryServiceMapper.getContractNumberByPdId(paramMap);
		return numMapList;
	}
	/**
	 * 产品预约审核中预约状态条件查询下拉框
	 * @author wh
	 */
	@Transactional
	public List<Map<String, String>> tdCodeQueryOrderStatus(String codeType) {

		DefCodeExample defCodeExample = new DefCodeExample();
		defCodeExample.setOrderByClause("code");
		defCodeExample.createCriteria().andCodeTypeEqualTo(codeType);
		List<DefCode> defCodeList = defCodeMapper.selectByExampleOrderStatus(defCodeExample);
		List<Map<String, String>> codeMapList = new ArrayList<Map<String, String>>();
		for (DefCode defCode : defCodeList) {
			Map<String, String> codeMap = new HashMap<String, String>();
			String code = defCode.getCode();
			String codeName = defCode.getCodeName();
			codeMap.put("code", code);
			codeMap.put("codeName", code + "-" + codeName);
			// codeMap.put("codeName", codeName);
			codeMap.put("name", codeName);
			codeMapList.add(codeMap);
		}
		return codeMapList;
	}

}