
package com.fms.service.basiclaw.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fms.db.mapper.BasicLawAssessMapper;
import com.fms.db.mapper.BasicLawInsMapper;
import com.fms.db.mapper.BasicLawMapper;
import com.fms.db.mapper.BasicLawWealthMapper;
import com.fms.db.mapper.BasicLawYBMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.model.BasicLaw;
import com.fms.db.model.BasicLawAssess;
import com.fms.db.model.BasicLawAssessExample;
import com.fms.db.model.BasicLawExample;
import com.fms.db.model.BasicLawIns;
import com.fms.db.model.BasicLawInsExample;
import com.fms.db.model.BasicLawWealth;
import com.fms.db.model.BasicLawWealthExample;
import com.fms.db.model.BasicLawYB;
import com.fms.db.model.BasicLawYBExample;
import com.fms.db.model.PDProduct;
import com.fms.service.basiclaw.BasicLawService;
import com.fms.service.mapper.BasicLawServiceMapper;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.LoginInfo;

@Service
public class BasicLawServiceImpl implements BasicLawService 
{
	private static final Logger log = Logger.getLogger(BasicLawServiceImpl.class);
	
	@Autowired
	private BasicLawMapper basicLawMapper;
	
	@Autowired
	private BasicLawInsMapper basicLawInsMapper;
	
	@Autowired
	private BasicLawYBMapper basicLawYBMapper;
	
	@Autowired
	private BasicLawWealthMapper basicLawWealthMapper;
	
	@Autowired
	private BasicLawAssessMapper basicLawAssessMapper;
	
	@Autowired
	private BasicLawServiceMapper basicLawServiceMapper;
	
	@Autowired
	private PDProductMapper pDProductMapper;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 财富产品奖金比例-新增保存
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void addBasicLawProductWealthSave(Map map, LoginInfo loginInfo)
	{
		Long productId = new Long(map.get("productId").toString());
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		List<BasicLawWealth> listBasicLawWealth = (List<BasicLawWealth>)map.get("listBasicLawWealth");
		
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		
		//校验产品是否存在
		checkProduct(productId);
		
		//校验该产品在是否归属其他基本法版本（并且是启用状态）
		checkProductToVersion(productId,basicLawInfo.getBasicLawId(),"Wealth");
		
		//数据处理
		for (BasicLawWealth basicLawWealth : listBasicLawWealth)
		{
			Long basicLawWealthId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_Wealth");
			basicLawWealth.setBasicLawWealthId(basicLawWealthId);
			basicLawWealth.setBasicLawId(basicLawInfo.getBasicLawId());
			basicLawWealth.setProductId(productId);
			BeanUtils.insertObjectSetOperateInfo(basicLawWealth, loginInfo);
			this.basicLawWealthMapper.insert(basicLawWealth);
		}
		
		addBasicLawProductAssessSave(map, loginInfo);
		
	}
	
	/**
	 * 财富产品奖金比例-更新保存
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void updateBasicLawProductWealthSave(Map map, LoginInfo loginInfo)
	{
		Long productId = new Long(map.get("productId").toString());
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		Long oldBasicLawId = new Long(map.get("oldBasicLawId").toString());
		List<BasicLawWealth> listBasicLawWealth = (List<BasicLawWealth>)map.get("listBasicLawWealth");
		
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		
		//校验产品是否存在
		checkProduct(productId);
		
		//当修改基本法所属版本信息是，改为状态为 "启用状态" 的版本进行校验
		//校验该产品在是否归属其他基本法版本（并且是启用状态）
		if("1".equals(basicLawInfo.getExecState()))
		{
			checkProductToVersion(productId,oldBasicLawId,"Wealth");
		}
		
		//根据版本ID和产品ID查询出有效的基本法,并删除
		BasicLawWealthExample basicLawWealthExample = new BasicLawWealthExample();
		BasicLawWealthExample.Criteria criteria = basicLawWealthExample.createCriteria();
		
		criteria.andBasicLawIdEqualTo(oldBasicLawId);
		criteria.andProductIdEqualTo(productId);
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawWealth> basicLawWealthListDB = this.basicLawWealthMapper.selectByExample(basicLawWealthExample);
		for (BasicLawWealth basicLawWealthDB : basicLawWealthListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawWealthDB, loginInfo);
			this.basicLawWealthMapper.updateByPrimaryKey(basicLawWealthDB);
		}
		
		//插入页面传递的产品基本法
		for (BasicLawWealth basicLawWealth : listBasicLawWealth)
		{
			Long basicLawWealthId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_Wealth");
			basicLawWealth.setBasicLawWealthId(basicLawWealthId);
			basicLawWealth.setBasicLawId(basicLawInfo.getBasicLawId());
			basicLawWealth.setProductId(productId);
			BeanUtils.insertObjectSetOperateInfo(basicLawWealth, loginInfo);
			this.basicLawWealthMapper.insert(basicLawWealth);
		}
		
		updateBasicLawProductAssessSave(map, loginInfo);
		
	}
	
	
	/**
	 * 保险产品奖金比例-新增保存
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public void addBasicLawProductInsSave(Map map, LoginInfo loginInfo)
	{
		Long productId=null;
		String productSubType=map.get("productSubType").toString();
		String productType=map.get("productType").toString();
		
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		List<BasicLawIns> listBasicLawIns = (List<BasicLawIns>)map.get("listBasicLawIns");
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		
		
	    if(productType.equals("2")&&productSubType.equals("01")){
		//校验该产品在是否归属其他基本法版本（并且是启用状态）
			for (BasicLawIns basicLawIns : listBasicLawIns)
			{
				Long basicLawWealthId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_INS");
				basicLawIns.setBasicLawInsId(basicLawWealthId);
				basicLawIns.setBasicLawId(basicLawInfo.getBasicLawId());
				BeanUtils.insertObjectSetOperateInfo(basicLawIns, loginInfo);
				this.basicLawInsMapper.insert(basicLawIns);
			}
		}
	    else{
	        productId = new Long(map.get("productId").toString());
			//校验产品是否存在
			checkProduct(productId);
	    	checkProductToVersion(productId,basicLawInfo.getBasicLawId(),"Ins");	

			for (BasicLawIns basicLawIns : listBasicLawIns)
			{
				Long basicLawWealthId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_INS");
				basicLawIns.setBasicLawInsId(basicLawWealthId);
				basicLawIns.setBasicLawId(basicLawInfo.getBasicLawId());
				basicLawIns.setProductId(productId);
				BeanUtils.insertObjectSetOperateInfo(basicLawIns, loginInfo);
				this.basicLawInsMapper.insert(basicLawIns);
			}
	    	
	    }
		
		addBasicLawProductAssessSave(map, loginInfo);
	}
	
	/**
	 * 保险产品奖金比例-更新保存
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public void updateBasicLawProductInsSave(Map map, LoginInfo loginInfo)
	{
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		Long oldBasicLawId = new Long(map.get("oldBasicLawId").toString());
		List<BasicLawIns> listBasicLawIns = (List<BasicLawIns>)map.get("listBasicLawIns");
		
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		

		
		//当修改基本法所属版本信息是，改为状态为启用状态的版本进行校验
		//校验该产品在是否归属其他基本法版本（并且是启用状态）
//		if("1".equals(basicLawInfo.getExecState()))
//		{
//			checkProductToVersion(productId,oldBasicLawId,"Ins");
//		}
		
		//根据版本ID和产品ID查询出有效的基本法 并删除
		BasicLawInsExample basicLawInsExample = new BasicLawInsExample();
		BasicLawInsExample.Criteria criteria = basicLawInsExample.createCriteria();
		criteria.andBasicLawIdEqualTo(oldBasicLawId);
//		criteria.andProductIdEqualTo(productId);
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawIns> basicLawInsListDB = this.basicLawInsMapper.selectByExample(basicLawInsExample);
		for (BasicLawIns basicLawInsDB : basicLawInsListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawInsDB, loginInfo);
			this.basicLawInsMapper.updateByPrimaryKey(basicLawInsDB);
		}

		//插入页面传递的产品基本法
		for (BasicLawIns basicLawIns : listBasicLawIns)
		{
			Long basicLawInsId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_INS");
			basicLawIns.setBasicLawInsId(basicLawInsId);
			basicLawIns.setBasicLawId(basicLawInfo.getBasicLawId());
//			basicLawIns.setProductId(productId);
			BeanUtils.insertObjectSetOperateInfo(basicLawIns, loginInfo);
			this.basicLawInsMapper.insert(basicLawIns);
		}
		
		updateBasicLawProductAssessSave(map, loginInfo);
	}
	
	/**
	 * 银行保险产奖金比例品-新增保存
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void addBasicLawProductYBSave(Map map, LoginInfo loginInfo)
	{
		Long productId = new Long(map.get("productId").toString());
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		List<BasicLawYB> listBasicLawYB = (List<BasicLawYB>)map.get("listBasicLawYB");
		
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		
		//校验产品是否存在
		checkProduct(productId);
		
		//校验该产品在是否归属其他基本法版本（并且是启用状态）
		checkProductToVersion(productId,basicLawInfo.getBasicLawId(),"YB");
		
		for (BasicLawYB basicLawYB : listBasicLawYB)
		{
			Long basicLawYBId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_YB");
			basicLawYB.setBasicLawYbBonusRateId(basicLawYBId);
			basicLawYB.setBasicLawId(basicLawInfo.getBasicLawId());
			basicLawYB.setProductId(productId);
			BeanUtils.insertObjectSetOperateInfo(basicLawYB, loginInfo);
			this.basicLawYBMapper.insert(basicLawYB);
		}
		
		addBasicLawProductAssessSave(map, loginInfo);
		
	}
	
	/**
	 * 银行保险产奖金比例品-更新保存
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public void updateBasicLawProductYBSave(Map map, LoginInfo loginInfo)
	{
		Long productId = new Long(map.get("productId").toString());
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		Long oldBasicLawId = new Long(map.get("oldBasicLawId").toString());
		List<BasicLawYB> listBasicLawYB = (List<BasicLawYB>)map.get("listBasicLawYB");
		
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		checkProduct(productId);
		
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		
		//校验产品是否存在
		checkProduct(productId);
		
		//当修改基本法所属版本信息是，改为状态为启用状态的版本进行校验
		//校验该产品在是否归属其他基本法版本（并且是启用状态）
		if("1".equals(basicLawInfo.getExecState()))
		{
			checkProductToVersion(productId,oldBasicLawId,"YB");
		}
		
		//根据版本ID和产品ID查询出有效的基本法并删除
		BasicLawYBExample basicLawYBExample = new BasicLawYBExample();
		BasicLawYBExample.Criteria criteria = basicLawYBExample.createCriteria();
		criteria.andBasicLawIdEqualTo(oldBasicLawId);
		criteria.andProductIdEqualTo(productId);
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawYB> basicLawYBListDB = this.basicLawYBMapper.selectByExample(basicLawYBExample);
		for (BasicLawYB basicLawYBDB : basicLawYBListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawYBDB, loginInfo);
			this.basicLawYBMapper.updateByPrimaryKey(basicLawYBDB);
		}
						
		//插入页面传递的产品基本法
		for (BasicLawYB basicLawYB : listBasicLawYB)
		{
			Long basicLawYBId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_YB");
			basicLawYB.setBasicLawYbBonusRateId(basicLawYBId);
			basicLawYB.setBasicLawId(basicLawInfo.getBasicLawId());
			basicLawYB.setProductId(productId);
			BeanUtils.insertObjectSetOperateInfo(basicLawYB, loginInfo);
			this.basicLawYBMapper.insert(basicLawYB);
		}
		
		updateBasicLawProductAssessSave(map, loginInfo);
		
	}
	
	
	/**
	 * 车险产品参数-新增保存(暂时没有奖金比例，只有新增考核参数)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public void addBasicLawProductVehicleSave(Map map, LoginInfo loginInfo)
	{
		addBasicLawProductAssessSave(map, loginInfo);
	}
	
	/**
	 * 车险产品参数-更新(暂时没有奖金比例，只有新增考核参数)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public void updateBasicLawProductVehicleSave(Map map, LoginInfo loginInfo)
	{
		updateBasicLawProductAssessSave(map, loginInfo);
	}
	
	
	/**
	 * 产品考核参数-新增保存
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void addBasicLawProductAssessSave(Map map, LoginInfo loginInfo)
	{
		String productType=map.get("productType").toString();
		String productSubType=map.get("productSubType").toString();
		Long productId = null;
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		List<BasicLawAssess> listBasicLawAssess = (List<BasicLawAssess>)map.get("listBasicLawAssess");
		
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
	
		if (productType.equals("2")&&productSubType.equals("01")) {
			
			
			for (BasicLawAssess basicLawAssess : listBasicLawAssess)
			{
				Long basicLawAssessId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_ASSESS");
				basicLawAssess.setBasicLawAssessId(basicLawAssessId);
				basicLawAssess.setBasicLawId(basicLawInfo.getBasicLawId());
				BeanUtils.insertObjectSetOperateInfo(basicLawAssess, loginInfo);
				this.basicLawAssessMapper.insert(basicLawAssess);
			}
			
		}
		else{
			
			productId = new Long(map.get("productId").toString());
			//校验产品是否存在
			checkProduct(productId);
			//校验该产品在是否归属其他基本法版本（并且是启用状态）
			checkProductToVersion(productId,basicLawInfo.getBasicLawId(),"Assess");
			
			for (BasicLawAssess basicLawAssess : listBasicLawAssess)
			{
				Long basicLawAssessId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_ASSESS");
				basicLawAssess.setBasicLawAssessId(basicLawAssessId);
				basicLawAssess.setBasicLawId(basicLawInfo.getBasicLawId());
				basicLawAssess.setProductId(productId);
				BeanUtils.insertObjectSetOperateInfo(basicLawAssess, loginInfo);
				this.basicLawAssessMapper.insert(basicLawAssess);
			}
		}
		
	}
	/**
	 * 产品考核参数-更新保存
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public void updateBasicLawProductAssessSave(Map map, LoginInfo loginInfo)
	{
		Long productId =null;
		log.info("-------------:"+map.get("productId"));
		if(map.get("productId")!=null&&!map.get("productId").equals("")){
		 productId = new Long(map.get("productId").toString());
		//校验产品是否存在
			checkProduct(productId);
			
		}
		
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		
		Long oldBasicLawId = new Long(map.get("oldBasicLawId").toString());
		List<BasicLawAssess> listBasicLawAssess = (List<BasicLawAssess>)map.get("listBasicLawAssess");
		
		//校验基本法是否存在
		checkBasicLawVersion(basicLawInfo.getBasicLawId());
		
		//当修改基本法所属版本信息是，改为状态为启用状态的版本进行校验
				//校验该产品在是否归属其他基本法版本（并且是启用状态）
		if(map.get("productId")!=null&&!map.get("productId").equals("")){
			if("1".equals(basicLawInfo.getExecState()))
			{
				checkProductToVersion(productId,oldBasicLawId,"Assess");
			}
			
		}
		
		//根据版本ID和产品ID查询出有效的基本法
		BasicLawAssessExample basicLawAssessExample = new BasicLawAssessExample();
		BasicLawAssessExample.Criteria criteria = basicLawAssessExample.createCriteria();
		criteria.andBasicLawIdEqualTo(oldBasicLawId);
		if(map.get("productId")!=null&&!map.get("productId").equals("")){
			
			criteria.andProductIdEqualTo(productId);	
		}
		else {
			criteria.andProductIdIsNull();
		}
		
		
//		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawAssess> basicLawAssessListDB = this.basicLawAssessMapper.selectByExample(basicLawAssessExample);
		
		for (BasicLawAssess basicLawAssessDB : basicLawAssessListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawAssessDB, loginInfo);
			this.basicLawAssessMapper.updateByPrimaryKey(basicLawAssessDB);
		}
						
		//插入页面传递的产品基本法
		for (BasicLawAssess basicLawAssess : listBasicLawAssess)
		{
			Long basicLawAssessId = commonService.getSeqValByName("SEQ_T_BASIC_LAW_ASSESS");
			basicLawAssess.setBasicLawAssessId(basicLawAssessId);
			basicLawAssess.setBasicLawId(basicLawInfo.getBasicLawId());
			basicLawAssess.setProductId(productId);
			BeanUtils.insertObjectSetOperateInfo(basicLawAssess, loginInfo);
			this.basicLawAssessMapper.insert(basicLawAssess);
		}
		
	}
	/**
	 * 基本法版本参数信息新增保存
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public void addBasicLawVersionSave(Map map, LoginInfo loginInfo)
	{
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		checkBasicLawVersionForAction(basicLawInfo);
		
		BasicLawExample basicLawExample = new BasicLawExample();
		BasicLawExample.Criteria criteria = basicLawExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteria.andVersionCodeEqualTo(basicLawInfo.getVersionCode());
		List<BasicLaw> versionlist = this.basicLawMapper.selectByExample(basicLawExample);
		if(versionlist.size()>0)
		{
			log.info("该基本法版本编码已经存在");
			throw new CisCoreException("该基本法版本编码已经存在");
		}
		basicLawInfo.setBasicLawId(commonService.getSeqValByName("SEQ_T_BASIC_LAW"));
		BeanUtils.insertObjectSetOperateInfo(basicLawInfo, loginInfo);
		this.basicLawMapper.insert(basicLawInfo);
	}
	
	/**
	 * 基本法版本参数信息更新保存
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public void updateBasicLawVersionSave(Map map, LoginInfo loginInfo)
	{
		BasicLaw basicLawInfo = (BasicLaw)map.get("basicLawInfo");
		checkBasicLawVersionForAction(basicLawInfo);
		
		BasicLawExample basicLawExample = new BasicLawExample();
		BasicLawExample.Criteria criteria = basicLawExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteria.andBasicLawIdNotEqualTo(basicLawInfo.getBasicLawId());
		criteria.andVersionCodeEqualTo(basicLawInfo.getVersionCode());
		List<BasicLaw> versionlist = this.basicLawMapper.selectByExample(basicLawExample);
		if(versionlist.size()>0)
		{
			log.info("该基本法版本编码已经存在");
			throw new CisCoreException("该基本法版本编码已经存在");
		}
		
		
		
		//读取数据库信息
		BasicLaw basicLawInfoDB = this.basicLawMapper.selectByPrimaryKey(basicLawInfo.getBasicLawId());
		basicLawInfo.setCreateDate(basicLawInfoDB.getCreateDate());
		basicLawInfo.setCreateUserId(basicLawInfoDB.getCreateUserId());
		basicLawInfo.setOperComId(basicLawInfoDB.getCreateUserId());
		basicLawInfo.setRcState(Constants.EFFECTIVE_RECORD);
		
		String oldBasicLawVersionExecState = basicLawInfoDB.getExecState();
		String newBasicLawVersionExecState = basicLawInfo.getExecState();
		/**
		 * 如果执行状态由停用改为启用，则对该版本下的所有产品进行校验，校验规则如下
		 * 1：该版本下的每个产品所在的其他版本的执行状态必须停用。
		 * 2：确保每款产品当前只归属在0个或1个启用的版本
		 */
		if("0".equals(oldBasicLawVersionExecState) && "1".equals(newBasicLawVersionExecState))
		{
			//校验财富产品参数关联相关
			BasicLawWealthExample basicLawWealthExample = new BasicLawWealthExample();
			BasicLawWealthExample.Criteria criteriaWealth = basicLawWealthExample.createCriteria();
			criteriaWealth.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
			criteriaWealth.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<BasicLawWealth> basicLawWealthListDB = this.basicLawWealthMapper.selectByExample(basicLawWealthExample);
			for (BasicLawWealth basicLawWealth : basicLawWealthListDB)
			{
				this.checkProductToVersion(basicLawWealth.getProductId(), basicLawWealth.getBasicLawId(), "Wealth");
			}
			
			//校验银行产品参数关联相关
			BasicLawYBExample basicLawYBExample = new BasicLawYBExample();
			BasicLawYBExample.Criteria criteriaYB = basicLawYBExample.createCriteria();
			criteriaYB.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
			criteriaYB.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<BasicLawYB> basicLawYBListDB = this.basicLawYBMapper.selectByExample(basicLawYBExample);
			for (BasicLawYB basicLawYB : basicLawYBListDB)
			{
				this.checkProductToVersion(basicLawYB.getProductId(), basicLawYB.getBasicLawId(), "YB");
			}
			//校验个人寿险产品参数关联相关
			BasicLawInsExample basicLawInsExample = new BasicLawInsExample();
			BasicLawInsExample.Criteria criteriaIns = basicLawInsExample.createCriteria();
			criteriaIns.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
			criteriaIns.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<BasicLawIns> basicLawInsListDB = this.basicLawInsMapper.selectByExample(basicLawInsExample);
			for (BasicLawIns basicLawIns : basicLawInsListDB)
			{
				this.checkProductToVersion(basicLawIns.getProductId(), basicLawIns.getBasicLawId(), "Ins");
			}
			//校验产品考核参数关联相关
			BasicLawAssessExample basicLawAssessExample = new BasicLawAssessExample();
			BasicLawAssessExample.Criteria criteriaAssess = basicLawAssessExample.createCriteria();
			criteriaAssess.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
			criteriaAssess.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<BasicLawAssess> basicLawAssessListDB = this.basicLawAssessMapper.selectByExample(basicLawAssessExample);
			for (BasicLawAssess basicLawAssess : basicLawAssessListDB)
			{
				this.checkProductToVersion(basicLawAssess.getProductId(), basicLawAssess.getBasicLawId(), "Ins");
			}
		}
		
		BeanUtils.updateObjectSetOperateInfo(basicLawInfo, loginInfo);
		this.basicLawMapper.updateByPrimaryKey(basicLawInfo);
	}
	
	
	/**
	 * 基本法版本参数删除保存
	 */
	@Override
	@Transactional
	public void deleteBasicLawVersionSave(Long basicLawId,
			LoginInfo loginInfo)
	{
		BasicLaw basicLawInfoDB = this.basicLawMapper.selectByPrimaryKey(basicLawId);
		
		//校验财富产品参数关联相关
		BasicLawWealthExample basicLawWealthExample = new BasicLawWealthExample();
		BasicLawWealthExample.Criteria criteria = basicLawWealthExample.createCriteria();
		criteria.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawWealth> basicLawWealthListDB = this.basicLawWealthMapper.selectByExample(basicLawWealthExample);
		if(basicLawWealthListDB.size()>0)
		{
			PDProduct pDProduct = this.pDProductMapper.selectByPrimaryKey(basicLawWealthListDB.get(0).getProductId());
			log.info("该版本下存在财富产品参数，产品编码："+pDProduct.getProductCode()+",不能删除");
			throw new CisCoreException("该版本下存在财富产品参数，产品编码："+pDProduct.getProductCode()+",不能删除");
		}
		
		//校验银行产品参数关联相关
		BasicLawYBExample basicLawYBExample = new BasicLawYBExample();
		BasicLawYBExample.Criteria criteriaYB = basicLawYBExample.createCriteria();
		criteriaYB.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
		criteriaYB.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawYB> basicLawYBListDB = this.basicLawYBMapper.selectByExample(basicLawYBExample);
		if(basicLawYBListDB.size()>0)
		{
			PDProduct pDProduct = this.pDProductMapper.selectByPrimaryKey(basicLawYBListDB.get(0).getProductId());
			log.info("该版本下存在银行产品参数，产品编码："+pDProduct.getProductCode() +",不能删除");
			throw new CisCoreException("该版本下存在银行产品参数，产品编码："+pDProduct.getProductCode()+",不能删除");
		}
		
		//校验个人寿险产品参数关联相关
		BasicLawInsExample basicLawInsExample = new BasicLawInsExample();
		BasicLawInsExample.Criteria criteriaIns = basicLawInsExample.createCriteria();
		criteriaIns.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
		criteriaIns.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawIns> basicLawInsListDB = this.basicLawInsMapper.selectByExample(basicLawInsExample);
		if(basicLawInsListDB.size()>0)
		{
			PDProduct pDProduct = this.pDProductMapper.selectByPrimaryKey(basicLawInsListDB.get(0).getProductId());
			log.info("该版本下存在个人寿险产品参数，产品编码："+pDProduct.getProductCode()+",不能删除");
			throw new CisCoreException("该版本下存在个人寿险产品参数，产品编码："+pDProduct.getProductCode()+",不能删除");
		}
		
		//校验产品考核参数关联相关
		BasicLawAssessExample basicLawAssessExample = new BasicLawAssessExample();
		BasicLawAssessExample.Criteria criteriaAssess = basicLawAssessExample.createCriteria();
		criteriaAssess.andBasicLawIdEqualTo(basicLawInfoDB.getBasicLawId());
		criteriaAssess.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawAssess> basicLawAssessListDB = this.basicLawAssessMapper.selectByExample(basicLawAssessExample);
		if(basicLawAssessListDB.size()>0)
		{
			PDProduct pDProduct = this.pDProductMapper.selectByPrimaryKey(basicLawAssessListDB.get(0).getProductId());
			log.info("该版本下存在产品考核参数，产品编码："+pDProduct.getProductCode()+",不能删除");
			throw new CisCoreException("该版本下存在产品考核参数，产品编码："+pDProduct.getProductCode()+",不能删除");
		}
		
		BeanUtils.deleteObjectSetOperateInfo(basicLawInfoDB, loginInfo);
		this.basicLawMapper.updateByPrimaryKey(basicLawInfoDB);
	}
	
	
	/**
	 * 基本法产品参数-删除保存
	 * 根据产品ID和基本法版本ID删除
	 * @param map
	 * @param loginInfo
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public void deleteBasicLawProductSave(Map paramMap, LoginInfo loginInfo)
	{
		
		PDProduct pdProduct=null;
		if(paramMap.get("basicLawId")==null)
		{
			log.info("产品基本法参数删除失败，基本法版本ID为空");
			throw new CisCoreException("产品基本法参数删除失败，基本法版本ID不能为空");
		}
//		if(paramMap.get("productId")==null)
//		{
//			log.info("产品基本法参数删除失败，基本法产品ID为空");
//			throw new CisCoreException("产品基本法参数删除失败，基本法产品ID不能为空");
//		}
		BasicLaw basicLaw =  this.basicLawMapper.selectByPrimaryKey(new Long(paramMap.get("basicLawId").toString()));
		if(basicLaw==null)
		{
			log.info("产品基本法参数删除失败，基本法版本不存在");
			throw new CisCoreException("产品基本法参数删除失败，基本法版本不存在");
		}
		
		if(paramMap.get("productId")!=null&&!paramMap.get("productId").equals("")){
			 pdProduct =  this.pDProductMapper.selectByPrimaryKey(new Long(paramMap.get("productId").toString()));
			if(pdProduct==null)
			{
				log.info("产品基本法参数删除失败，产品不存在");
				throw new CisCoreException("产品基本法参数删除失败，产品不存在");
			}
			
			
		}
		
		
		
		//根据版本ID和产品ID查询出有效的基本法,财富产品奖金比例删除
		BasicLawWealthExample basicLawWealthExample = new BasicLawWealthExample();
		BasicLawWealthExample.Criteria criteriaWealth = basicLawWealthExample.createCriteria();
		criteriaWealth.andBasicLawIdEqualTo(basicLaw.getBasicLawId());
		if(pdProduct!=null){
		criteriaWealth.andProductIdEqualTo(pdProduct.getProductId());}
		else {
			criteriaWealth.andProductIdIsNull();
		}
		criteriaWealth.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawWealth> basicLawWealthListDB = this.basicLawWealthMapper.selectByExample(basicLawWealthExample);
		for (BasicLawWealth basicLawWealthDB : basicLawWealthListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawWealthDB, loginInfo);
			this.basicLawWealthMapper.updateByPrimaryKey(basicLawWealthDB);
		}
		
		//根据版本ID和产品ID查询出有效的基本法,个人寿险奖金比例删除
		BasicLawInsExample basicLawInsExample = new BasicLawInsExample();
		BasicLawInsExample.Criteria criteriaIns = basicLawInsExample.createCriteria();
		criteriaIns.andBasicLawIdEqualTo(basicLaw.getBasicLawId());
		if (pdProduct!=null) {
			criteriaIns.andProductIdEqualTo(pdProduct.getProductId());
		}
		else
		{
			criteriaIns.andProductIdIsNull();
		}
		
		criteriaIns.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawIns> basicLawInsListDB = this.basicLawInsMapper.selectByExample(basicLawInsExample);
		for (BasicLawIns basicLawInsDB : basicLawInsListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawInsDB, loginInfo);
			this.basicLawInsMapper.updateByPrimaryKey(basicLawInsDB);
		}
		
		//根据版本ID和产品ID查询出有效的基本法,银行保险奖金比例删除
		BasicLawYBExample basicLawYBExample = new BasicLawYBExample();
		BasicLawYBExample.Criteria criteriaYB = basicLawYBExample.createCriteria();
		criteriaYB.andBasicLawIdEqualTo(basicLaw.getBasicLawId());
		if(pdProduct!=null){
			criteriaYB.andProductIdEqualTo(pdProduct.getProductId());
			}
		else
		{
			criteriaYB.andProductIdIsNull();
		}
		
		criteriaYB.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawYB> basicLawYBListDB = this.basicLawYBMapper.selectByExample(basicLawYBExample);
		for (BasicLawYB basicLawYBDB : basicLawYBListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawYBDB, loginInfo);
			this.basicLawYBMapper.updateByPrimaryKey(basicLawYBDB);
		}
		
		//根据版本ID和产品ID查询出有效的基本法,产品考核参数删除
		BasicLawAssessExample basicLawAssessExample = new BasicLawAssessExample();
		BasicLawAssessExample.Criteria criteria = basicLawAssessExample.createCriteria();
		criteria.andBasicLawIdEqualTo(basicLaw.getBasicLawId());
		
		if(pdProduct!=null){
			criteria.andProductIdEqualTo(pdProduct.getProductId());
			}
		else {
			criteria.andProductIdIsNull();
		}
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<BasicLawAssess> basicLawAssessListDB = this.basicLawAssessMapper.selectByExample(basicLawAssessExample);
		for (BasicLawAssess basicLawAssessDB : basicLawAssessListDB)
		{
			BeanUtils.deleteObjectSetOperateInfo(basicLawAssessDB, loginInfo);
			this.basicLawAssessMapper.updateByPrimaryKey(basicLawAssessDB);
		}
			
	}
	
	
	
	/**
	 * 基本法版本信息校验
	 */
	public void checkBasicLawVersionForAction(BasicLaw basicLaw)
	{
		if ("".equals(basicLaw.getVersionCode()) || basicLaw.getVersionCode() == null)
		{
			log.info("基本法版本编码为空");
			throw new CisCoreException("基本法版本编码不能为空");
		}
		if ("".equals(basicLaw.getVersionName()) || basicLaw.getVersionName() == null)
		{
			log.info("基本法版本名称为空");
			throw new CisCoreException("基本法版本名称不能为空");
		}
		
		if ("".equals(basicLaw.getExecState()) || basicLaw.getExecState() == null)
		{
			log.info("基本法版本执行状态为空");
			throw new CisCoreException("基本法版本执行状态不能为空");
		}
		if ("".equals(basicLaw.getExecStartdate()) || basicLaw.getExecStartdate() == null)
		{
			log.info("基本法版本执行开始日期为空");
			throw new CisCoreException("基本法版本执行开始日期不能为空");
		}
		if(basicLaw.getExecEnddate()!=null && basicLaw.getExecStartdate().after(basicLaw.getExecEnddate()))
		{
			log.info("基本法版本执行开始日期不能大于结束日期");
			throw new CisCoreException("基本法版本执行开始日期不能大于结束日期");
		}
	}
	
	/**
	 * 校验该产品在是否归属其他基本法版本（并且是启用状态）
	 * @param productId
	 * @param bussType
	 */
	public void checkProductToVersion(Long productId,Long basicLawId,String bussType)
	{
		
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("productId", productId);
		paramMap.put("basicLawId", basicLawId);
		List<BasicLaw> basicLawList = null;
		log.info("bussType->>>>>" + bussType + "productId::::"+productId);
		if("Wealth".equals(bussType))
		{
			basicLawList = this.basicLawServiceMapper.queryWealthProductToVersionExecState(paramMap);
		}
		if("Ins".equals(bussType))
		{
			basicLawList = this.basicLawServiceMapper.queryInsProductToVersionExecState(paramMap);
		}
		if("YB".equals(bussType))
		{
			basicLawList = this.basicLawServiceMapper.queryYBProductToVersionExecState(paramMap);
		}
		if("Vehicle".equals(bussType))
		{
			//暂时没有车险
		}
		if("Assess".equals(bussType))
		{
			basicLawList = this.basicLawServiceMapper.queryAssessProductToVersionExecState(paramMap);
		}
		
		if(basicLawList.size()>0)
		{
			PDProduct pDProduct = this.pDProductMapper.selectByPrimaryKey(productId);
			log.info("该版本下的"+pDProduct.getProductCode()+"产品，在其他版本("+basicLawList.get(0).getVersionCode()+")中是启用状态,请检查");
			throw new CisCoreException("该版本下的"+pDProduct.getProductCode()+"产品，在其他版本("+basicLawList.get(0).getVersionCode()+")中是启用状态,请检查");
		}
	}
	
	
	/**
	 * 根据产品ID判断产品是否存在
	 * @param productId
	 */
	public void checkProduct(Long productId)
	{
		PDProduct pDProduct =  this.pDProductMapper.selectByPrimaryKey(productId);
		if(pDProduct==null)
		{
			log.info("基本法产品为空");
			throw new CisCoreException("基本法产品不能为空");
		}
	}
	
	/**
	 * 根据基本法版本ID判断基本法版本是否存在
	 * @param productId
	 */
	public void checkBasicLawVersion(Long long1)
	{
		BasicLaw basicLaw = getBasicLawVersionInfoById(long1);
		if(basicLaw==null)
		{
			log.info("基本法版本为空");
			throw new CisCoreException("基本法版本不能为空");
		}
	}
	/**
	 * 基本法-版本-参数信息列表查询（菜单页面查询）
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getBasicLawVersionPageList(DataGridModel dgm,  Map<String,Object> paramMap)
	{
		Map<String, Object> result = new HashMap<String, Object>(2);
		paramMap.put("queryStartIndex", dgm.getStartIndex());
		paramMap.put("queryEndIndex", dgm.getEndIndex());
		int total = basicLawServiceMapper.queryBasicLawVersionInfoCounts(paramMap);
		List<Map> list = basicLawServiceMapper.queryBasicLawVersionInfoPages(paramMap);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	/**
	 * 基本法-产品-参数信息列表查询（菜单页面查询）
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getBasicLawProductPageList(DataGridModel dgm,Map<String, Object> paramMap)
	{
		Map<String, Object> result = new HashMap<String, Object>(2);
		paramMap.put("queryStartIndex", dgm.getStartIndex());
		paramMap.put("queryEndIndex", dgm.getEndIndex());
		int total = basicLawServiceMapper.queryBasicLawProductInfoCounts(paramMap);
		List<Map> list = basicLawServiceMapper.queryBasicLawProductInfoPages(paramMap);
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	/**
	 * 根据基本法版本ID获取基本法信息（选择版本时，带出版本信息）
	 * @param paramMap
	 * @return
	 */
	@Override
	public BasicLaw getBasicLawVersionInfoById(Long long1)
	{
		return this.basicLawMapper.selectByPrimaryKey(long1);
	}
	
	/**
	 * 根据基本法版本ID,产品ID获取基本法信息(更新和明细页面初始化)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> queryBasicLawProductVersionInfo( Map<String, Object> paramMap)
	{
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(paramMap.get("basicLawId")==null)
		{
			log.info("页面初始化失败，基本法版本ID为空");
			throw new CisCoreException("页面初始化失败，基本法版本ID不能为空");
		}
		//获取基本法主表信息
		BasicLaw basicLaw =  this.basicLawMapper.selectByPrimaryKey(new Long(paramMap.get("basicLawId").toString()));
		
		map.put("basicLaw", basicLaw);
		if(basicLaw==null)
		{
			log.info("页面初始化失败，基本法版本不存在");
			throw new CisCoreException("页面初始化失败，基本法版本不存在");
		}
		if(paramMap.get("productTypeCode").toString().equals("2")&&paramMap.get("productSubTypeCode").toString().equals("01")){
		   PDProduct pdProduct=new PDProduct();
		   pdProduct.setProductType(paramMap.get("productTypeCode").toString());
		   pdProduct.setProductSubType(paramMap.get("productSubTypeCode").toString());
			//获取基本信息
			map.put("productType", paramMap.get("productTypeCode").toString());
			map.put("productSubType", paramMap.get("productSubTypeCode").toString());
			map.put("pdProduct", pdProduct);
			paramMap.put("productId", "");
			//获取个人寿险薪资奖金比例信息
			List<Map> basicLawInsList = this.basicLawServiceMapper.queryBasicLawInsByLPID(paramMap);
			map.put("basicLawInsList", basicLawInsList);
			
			//获取考核参数信息
			List<Map> basicLawAssessList = this.basicLawServiceMapper.queryBasicLawAssessByLPID(paramMap);
			map.put("basicLawAssessList", basicLawAssessList);
		}
		else{
			if(paramMap.get("productId")==null)
			{
				log.info("页面初始化失败，基本法产品ID为空");
				throw new CisCoreException("页面初始化失败，基本法产品ID不能为空");
			}	
		    PDProduct  pdProduct =  this.pDProductMapper.selectByPrimaryKey(new Long(paramMap.get("productId").toString()));
			if(pdProduct==null)
			{
			     log.info("页面初始化失败，产品不存在");
				 throw new CisCoreException("页面初始化失败，产品不存在");
			}
			 map.put("basicLaw", basicLaw);
			 map.put("pdProduct", pdProduct);	
			 map.put("productType", paramMap.get("productTypeCode").toString());
			 map.put("productSubType", paramMap.get("productSubTypeCode").toString());
			 if (pdProduct!=null) {
				  //财富产品
			      if("1".equals(pdProduct.getProductType()))
					{
					  List<Map> basicLawWealthList = this.basicLawServiceMapper.queryBasicLawWealthByLPID(paramMap);
					  map.put("basicLawWealthList", basicLawWealthList);
					}
					//保险产品
					if("2".equals(pdProduct.getProductType()))
					{	//银保
					   if("03".equals(pdProduct.getProductSubType()))
						{
							List<Map> basicLawYBList = this.basicLawServiceMapper.queryBasicLawYBByLPID(paramMap);
							map.put("basicLawYBList", basicLawYBList);
						}
					}
					List<Map> basicLawAssessList = this.basicLawServiceMapper.queryBasicLawAssessByLPID(paramMap);
					map.put("basicLawAssessList", basicLawAssessList);
				}
		}
	
		return map;
	}

	/**
	 * 根据产品类型和产品Id验证该产品是否已经存在有效的考核基本法
	 */
	@Override
	public Boolean  verifyBasicExist(String productType, String productSubType,String productId) {
		//个人寿险 T_BASIC_LAW_INS 薪资参数奖金比例  T_BASIC_LAW_ASSESS 考核参数信息表
		if (productType.equals("2")&&productSubType.equals("01")) {
			BasicLawInsExample basicLawInsExample=new BasicLawInsExample();
			basicLawInsExample.createCriteria().andRcStateEqualTo("E").andProductIdIsNull().andExecStateEqualTo("1");
			List<BasicLawIns> basicLawInsList=basicLawInsMapper.selectByExample(basicLawInsExample);
			BasicLawAssessExample  basicLawAssessExample=new BasicLawAssessExample();
			basicLawAssessExample.createCriteria().andRcStateEqualTo("E").andExecStateEqualTo("1").andProductIdIsNull();
			List<BasicLawAssess> basicLawAssesslist=basicLawAssessMapper.selectByExample(basicLawAssessExample);
			if(basicLawInsList!=null&&basicLawInsList!=null&&basicLawInsList.size()>0&&basicLawAssesslist.size()>0){
				return false;
 			}
		}
		//保险  银保产品 T_BASIC_LAW_YB 银行保险薪资奖金比例   T_BASIC_LAW_ASSESS 考核参数信息表
		if(productType.equals("2")&&productSubType.equals("03")){
			BasicLawYBExample basicLawYBExample=new BasicLawYBExample();
			basicLawYBExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(productId)).andExecStateEqualTo("1");
			List<BasicLawYB> basicLawYBList=basicLawYBMapper.selectByExample(basicLawYBExample);
			BasicLawAssessExample  basicLawAssessExample=new BasicLawAssessExample();
			basicLawAssessExample.createCriteria().andRcStateEqualTo("E").andExecStateEqualTo("1").andProductIdEqualTo(new Long(productId));
			List<BasicLawAssess> basicLawAssesslist=basicLawAssessMapper.selectByExample(basicLawAssessExample);
			if(basicLawYBList!=null&&basicLawAssesslist!=null&&basicLawYBList.size()>0&&basicLawAssesslist.size()>0){
				return false;
			}
		}
		//财富产品 T_BASIC_LAW_WEALTH 财富产品薪资奖金比例  T_BASIC_LAW_ASSESS 考核参数信息表
		if(productType.equals("1")){
		
			BasicLawWealthExample basicLawWealthExample=new BasicLawWealthExample();
			basicLawWealthExample.createCriteria().andRcStateEqualTo("E").andProductIdEqualTo(new Long(productId)).andExecStateEqualTo("1");
			List<BasicLawWealth> basicLawWealthList=basicLawWealthMapper.selectByExample(basicLawWealthExample);
			BasicLawAssessExample  basicLawAssessExample=new BasicLawAssessExample();
			basicLawAssessExample.createCriteria().andRcStateEqualTo("E").andExecStateEqualTo("1").andProductIdEqualTo(new Long(productId));
			List<BasicLawAssess> basicLawAssesslist=basicLawAssessMapper.selectByExample(basicLawAssessExample);
			if (basicLawWealthList!=null&&basicLawAssesslist!=null&&basicLawAssesslist.size()>0&&basicLawWealthList.size()>0) {
				return false;
			}
		}
		return true;
	}
}