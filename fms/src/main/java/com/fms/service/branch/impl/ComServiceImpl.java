package com.fms.service.branch.impl;

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

import com.fms.db.mapper.ComBelongTraceMapper;
import com.fms.db.mapper.DefDepartmentMapper;
import com.fms.db.mapper.DefStoreMapper;
import com.fms.db.model.ComBelongTrace;
import com.fms.db.model.ComBelongTraceExample;
import com.fms.db.model.DefDepartment;
import com.fms.db.model.DefDepartmentExample;
import com.fms.db.model.DefStore;
import com.fms.db.model.DefStoreExample;
import com.fms.service.branch.ComService;
import com.fms.service.mapper.ComServiceMapper;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.db.mapper.DefComMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.db.model.DefComExample;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.LoginInfo;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ComServiceImpl implements ComService
{
	@Autowired
	CommonService commonService;
	
	@Autowired
	DefComMapper defComMapper;
	
	@Autowired
	DefStoreMapper defStoreMapper;
	
	@Autowired
	DefUserMapper defUserMapper;
	
	@Autowired
	DefDepartmentMapper defDepartmentMapper;
	
	@Autowired
	ComBelongTraceMapper comBelongTraceMapper;
	
	//机构信息查询
	@Autowired
	ComServiceMapper comServiceMapper;
	
	private static final Logger log = Logger.getLogger(ComService.class);
	
	@Override
	@Transactional
	public boolean addComSave(Map map, LoginInfo loginInfo)
	{	
		
		DefCom defComSchema = (DefCom)map.get("defComSchema");
		
		//基本信息校验
		checkCom(defComSchema);
		checkComForAction(defComSchema,"INSERT");
		
		if("01".equals(defComSchema.getGrade()))
		{
			defComSchema.setParentComId(defComSchema.getComId());
		}
		else if("02".equals(defComSchema.getGrade()))
		{
			if ("".equals(defComSchema.getParentComId()) || defComSchema.getParentComId() == null)
			{
				log.info("归属机构不能为空");
				throw new CisCoreException("归属机构不能为空");
			}
		}
		
		//机构基本信息新增
		Long comSeq = commonService.getSeqValByName("SEQ_T_DEF_COM");
		defComSchema.setComId(comSeq);
		BeanUtils.insertObjectSetOperateInfo(defComSchema, loginInfo);
		defComMapper.insert(defComSchema);
		
		//机构归属轨迹信息处理
		ComBelongTrace comBelongTrace = new ComBelongTrace();
		Long comBlSeq = commonService.getSeqValByName("SEQ_T_COM_BELONG_TRACE");
		comBelongTrace.setBlcomId(comBlSeq);
		comBelongTrace.setComId(comSeq);
		comBelongTrace.setBelongComId(defComSchema.getParentComId());
		comBelongTrace.setStartDate(defComSchema.getBelongStartDate());
		comBelongTrace.setEndDate(defComSchema.getBelongEndDate());
		BeanUtils.insertObjectSetOperateInfo(comBelongTrace, loginInfo);
		comBelongTraceMapper.insert(comBelongTrace);
		return true;
	}


	@Override
	public Map<String, Object> getPageList(DataGridModel dgm, DefCom defCom)
	{
		
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		Map paramMap = new HashMap();
		paramMap.put("comCode", defCom.getComCode());
		paramMap.put("comName", defCom.getComName());
		paramMap.put("parentComId", defCom.getParentComId());
		
		paramMap.put("queryStartIndex", dgm.getStartIndex());
		paramMap.put("queryEndIndex", dgm.getEndIndex());
		int total = comServiceMapper.queryComListInfoCounts(paramMap);
		List<Map> list = comServiceMapper.queryComListInfoPages(paramMap);
		
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 初始化页面，显示下拉列表项
	 */
	@Override
	public List<Map> getComListCode()
	{
		return (List<Map>) comServiceMapper.queryComListCode();
	}
	
	/**
	 * 删除机构
	 */
	@Override
	@Transactional
	public void deleteComSave(Long comId,LoginInfo loginInfo)
	{
		//删除机构基本信息表
		DefCom defCom = defComMapper.selectByPrimaryKey(comId);
		BeanUtils.deleteObjectSetOperateInfo(defCom, loginInfo);
		defComMapper.updateByPrimaryKey(defCom);
		
		DefComExample defComExample = new DefComExample();
		DefComExample.Criteria criteria = defComExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteria.andParentComIdEqualTo(defCom.getComId());
		List<DefCom> comlist = this.defComMapper.selectByExample(defComExample);
		if (comlist.size() > 0)
		{
			log.info("该机构存在下属机构，下属机构编码："+comlist.get(0).getComCode());
			throw new CisCoreException("该机构存在下属机构，下属机构编码："+comlist.get(0).getComCode());
		}
		
		
		DefStoreExample defStoreExample = new DefStoreExample();
		DefStoreExample.Criteria criteriaStore = defStoreExample.createCriteria();
		criteriaStore.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteriaStore.andComIdEqualTo(defCom.getComId());
		List<DefStore> storelist = this.defStoreMapper.selectByExample(defStoreExample);
		if (storelist.size() > 0)
		{
			log.info("当前机构下有已归属的网点信息，请先删除网点记录，网点编码："+storelist.get(0).getStoreCode());
			throw new CisCoreException("当前机构下有已归属的网点信息，请先删除网点记录，网点编码："+storelist.get(0).getStoreCode());
			
		}
		
		
		DefDepartmentExample defDepartmentExample = new DefDepartmentExample();
		DefDepartmentExample.Criteria criteriaDeparment = defDepartmentExample.createCriteria();
		criteriaDeparment.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteriaDeparment.andComIdEqualTo(defCom.getComId());
		List<DefDepartment> departMentlist = this.defDepartmentMapper.selectByExample(defDepartmentExample);
		if (departMentlist.size() > 0)
		{
			log.info("当前机构下有已归属的业务部信息，请先删除业务部记录，业务部编码："+departMentlist.get(0).getDepartmentCode());
			throw new CisCoreException("当前机构下有已归属的业务部信息，请先删除业务部记录，业务部编码："+departMentlist.get(0).getDepartmentCode());
		}
		
		DefUserExample defUserExample = new DefUserExample();
		DefUserExample.Criteria criteriaUser = defUserExample.createCriteria();
		criteriaUser.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteriaUser.andComIdEqualTo(defCom.getComId());
		List<DefUser> userlist = this.defUserMapper.selectByExample(defUserExample);
		if (userlist.size() > 0)
		{
			log.info("当前机构下有用户，请先删除用户记录，用户编码："+userlist.get(0).getUserCode());
			throw new CisCoreException("当前机构下有用户，请先删除用户记录，用户编码："+userlist.get(0).getUserCode());
		}
		
		//删除机构归属信息表
		ComBelongTraceExample comBelongTraceExample = new ComBelongTraceExample();
		comBelongTraceExample.createCriteria().andComIdEqualTo(defCom.getComId());
		List<ComBelongTrace> comBelongTraceList = comBelongTraceMapper.selectByExample(comBelongTraceExample);
		for (ComBelongTrace comBelongTrace : comBelongTraceList)
		{
			BeanUtils.deleteObjectSetOperateInfo(comBelongTrace, loginInfo);
			comBelongTraceMapper.updateByPrimaryKey(comBelongTrace);
		}
		
	}
	/**
	 * 更新页面信息带入根据机构ID
	 */
	public Map<String, Object> getUpdateListInfo(Long comId)
	{
		
		//查询机构基本信息
		DefCom defCom = defComMapper.selectByPrimaryKey(comId);
		
		//查询机构归属信息
		Map paramMap = new HashMap();
		paramMap.put("comId", comId);
		//List<Map> comBelongTraceList = comServiceMapper.queryComBelongListInfo(paramMap);
		
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("defCom", defCom);
		//result.put("comBelongTraceList", comBelongTraceList);
		return result;
	}


	@Override
	@Transactional
	public void updateComSave(Map map,LoginInfo loginInfo)
	{
		
		log.info("start ->>>>>  updateComSave");
		//获取参数实体
		DefCom defComSchema = (DefCom)map.get("defComSchema");
		
		//基本信息校验
		checkCom(defComSchema);
		checkComForAction(defComSchema,"UPDATE");
		
		log.info("start ->>>>>  updateComSave ComId " + defComSchema.getComId());
		//得到数据库实体
		DefCom defComDB = defComMapper.selectByPrimaryKey(defComSchema.getComId());
		defComSchema.setRcState(defComDB.getRcState());
		defComSchema.setCreateUserId(defComDB.getCreateUserId());
		defComSchema.setCreateDate(defComDB.getCreateDate());
		defComSchema.setOperComId(defComDB.getOperComId());
		
		//归属信息旧
		Long oldBelongComId = defComDB.getParentComId();
		Date oldBelongStartDate = defComDB.getBelongStartDate();
		Date oldBelongEndDate = defComDB.getBelongEndDate();
		
		Long newBelongComId = defComSchema.getParentComId();
		Date newBelongStartDate = defComSchema.getBelongStartDate();
		Date newBelongEndDate = defComSchema.getBelongEndDate();
		
		//参数实体->更新到数据库实体
		BeanUtils.copyProperties(defComSchema, defComDB);
		BeanUtils.updateObjectSetOperateInfo(defComDB, loginInfo);
		defComMapper.updateByPrimaryKey(defComDB);
		
		//机构归属轨迹信息处理
		if((oldBelongComId==null && newBelongComId==null)||
		   (oldBelongStartDate==null && newBelongStartDate==null) || 
		   (oldBelongEndDate==null && newBelongEndDate==null))
		{
			return;
		}
		
		if((oldBelongComId==null && newBelongComId!=null) || (oldBelongComId!=null && newBelongComId==null)||
		   (oldBelongStartDate==null && newBelongStartDate!=null) || (oldBelongStartDate!=null && newBelongStartDate==null)||
		   (oldBelongEndDate==null && newBelongEndDate!=null) || (oldBelongEndDate!=null && newBelongEndDate==null)
		|| !oldBelongComId.equals(newBelongComId) 
		|| !oldBelongStartDate.equals(newBelongStartDate) 
		|| !oldBelongEndDate.equals(newBelongEndDate))
		{
			ComBelongTrace comBelongTrace = new ComBelongTrace();
			Long comBlSeq = commonService.getSeqValByName("SEQ_T_COM_BELONG_TRACE");
			comBelongTrace.setBlcomId(comBlSeq);
			comBelongTrace.setComId(defComDB.getComId());
			comBelongTrace.setBelongComId(defComDB.getParentComId());
			comBelongTrace.setStartDate(defComDB.getBelongStartDate());
			comBelongTrace.setEndDate(defComDB.getBelongEndDate());
			BeanUtils.insertObjectSetOperateInfo(comBelongTrace, loginInfo);
			comBelongTraceMapper.insert(comBelongTrace);
		}
	}
	
	/**
	 * 机构信息校验
	 */
	public void checkCom(DefCom defCom)
	{
		if ("".equals(defCom.getComCode()) || defCom.getComCode() == null)
		{
			log.info("机构编码为空");
			throw new CisCoreException("机构编码不能为空");
		}
		if ("".equals(defCom.getComName()) || defCom.getComName() == null)
		{
			log.info("机构名称为空");
			throw new CisCoreException("机构名称不能为空");
		}
		
		if ("".equals(defCom.getGrade()) || defCom.getGrade() == null)
		{
			log.info("机构级别为空");
			throw new CisCoreException("机构级别不能为空");
		}
		
		if (!"01".equals(defCom.getGrade()) && !"02".equals(defCom.getGrade()))
		{
			log.info("机构级别不合法");
			throw new CisCoreException("机构级别不合法");
		}
		
		if ("".equals(defCom.getState()) || defCom.getState() == null)
		{
			log.info("机构状态为空");
			throw new CisCoreException("机构状态不能为空");
		}
	}
	
	private void checkComForAction(DefCom defCom,String Operator)
	{	
		if("INSERT".equals(Operator))
		{
			List<DefCom> comlist = this.comServiceMapper.queryComByComCode(defCom);
			if (comlist.size() > 0)
			{
				log.info("该机构编码已经存在");
				throw new CisCoreException("该机构编码已经存在");
			}
		}
		if("UPDATE".equals(Operator))
		{
			DefComExample defComExample = new DefComExample();
			DefComExample.Criteria criteria = defComExample.createCriteria();
			criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			criteria.andComIdNotEqualTo(defCom.getComId());
			criteria.andComCodeEqualTo(defCom.getComCode());
			List<DefCom> comlist = this.defComMapper.selectByExample(defComExample);
			if (comlist.size() > 0)
			{
				log.info("该机构编码已经存在");
				throw new CisCoreException("该机构编码已经存在");
			}
		}
		
		if("02".equals(defCom.getGrade()))
		{
			if(this.defComMapper.selectByPrimaryKey(defCom.getParentComId()) == null)
			{
				log.info("归属机构不存在");
				throw new CisCoreException("归属机构不存在");
			}
			
			if(defCom.getBelongStartDate()==null)
			{
				log.info("机构归属开始日期不能为空");
				throw new CisCoreException("机构归属开始日期不能为空");
			}
			
			if(defCom.getBelongEndDate()!=null && defCom.getBelongStartDate().after(defCom.getBelongEndDate()))
			{
				log.info("机构归属开始日期不能大于结束日期");
				throw new CisCoreException("机构归属开始日期不能大于结束日期");
			}
		}
	}
}
