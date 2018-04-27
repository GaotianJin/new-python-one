package com.fms.service.branch.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.DefDepartment;
import com.fms.db.model.DefDepartmentExample;
import com.fms.db.model.DepartmentBelongTrace;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.DefDepartmentMapper;
import com.fms.db.mapper.DepartmentBelongTraceMapper;
import com.fms.service.branch.DepartmentService;
import com.fms.service.mapper.DepartmentServiceMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private CommonService commonService;
	@Autowired
	private DefDepartmentMapper defDepartmentMapper;
	@Autowired
	private DepartmentServiceMapper departmentServiceMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private DepartmentBelongTraceMapper departmentBelongTraceMapper;

	/**
	 * 添加业务部信息
	 */
	@Transactional
	@Override
	public ResultInfo savaDepartmentAdd(String param,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String comInfo = JsonUtils.getJsonValueByKey("comInfo", param);
		String addDepartmentId=JsonUtils.getJsonValueByKey("addDepartmentId", param);
		DefDepartment defdep = gson.fromJson(comInfo, DefDepartment.class);
		
		if(addDepartmentId!=null&&(!"null".equals(addDepartmentId))){
			defdep.setDepartmentId(new Long(addDepartmentId));
		}
		
		if(defdep.getState()==null||defdep.getState().equals("")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("业务部状态不能为空！");
			return resultInfo;
		}
		
		if(defdep.getComId()==null||defdep.getComId().equals("")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上级机构不能为空！");
			return resultInfo;
		}
		
		// 验证业务部编码是否重复。
		if(verifyDepartmentCode(defdep,"update")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("此业务部编码已存在！");
			return resultInfo;
		}
		
		if(addDepartmentId!=null&&(!"null".equals(addDepartmentId))){
			defdep.setDepartmentId(new Long(addDepartmentId));
		}
		
		//将新的数据修改，旧的数据添加入轨迹表中
		if(defdep.getDepartmentId()!=null){
//			DefDepartment dep=defDepartmentMapper.selectByPrimaryKey(defdep.getDepartmentId());
//			BeanUtils.deleteAndInsertObjectSetOperateInfo(dep,defdep, loginInfo);
//			defDepartmentMapper.updateByPrimaryKey(defdep);
//			
//			if(!insterBelongCom(defdep,loginInfo)){
//				resultInfo.setMsg("操作失败！");
//				resultInfo.setSuccess(false);
//				return resultInfo;
//			}
//			Long depId = commonService.getSeqValByName("SEQ_T_DEF_DEPARTMENT");
//			dep.setDepartmentId(depId);
//			defDepartmentMapper.insert(dep);
			
			if(updateDepartment(defdep,defdep.getDepartmentId(),loginInfo)){
			resultInfo.setMsg("操作成功");
			resultInfo.setSuccess(true);
			return resultInfo;
			}else{
				resultInfo.setMsg("操作失败");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		}else{
		
		Long depId = commonService.getSeqValByName("SEQ_T_DEF_DEPARTMENT");
		defdep.setDepartmentId(depId);
		BeanUtils.insertObjectSetOperateInfo(defdep,loginInfo);
		defDepartmentMapper.insert(defdep);
		
		if(!insterBelongCom(defdep,loginInfo)){
			resultInfo.setMsg("操作失败！");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setObj(defdep.getDepartmentId());
		resultInfo.setMsg("操作成功");
		resultInfo.setSuccess(true);
		return resultInfo;}
	}

	/**
	 * 查询业务部信息
	 * */
	@Override
	public Map<String, Object> queryDepartmentList(DataGridModel dgm,
			DefDepartment defdep) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		Map paramMap = new HashMap();
		paramMap.put("departmentId", defdep.getDepartmentId());
		paramMap.put("comId", defdep.getComId());
		paramMap.put("startIndex", dgm.getStartIndex());// 获取页
		paramMap.put("endIndex", dgm.getEndIndex());// 获取止页
		Integer total = departmentServiceMapper
				.branchDepartmentListCount(paramMap);// 页码数
		List<Map> resultList = departmentServiceMapper
				.branchDepartmentListPage(paramMap);

		JsonUtils.objectToJsonStr(resultList);

		result.put("total", total);
		result.put("rows", resultList);
		return result;
	}

	/**
	 * 业务部信息下拉项，初始化事件
	 * */
	@Override
	public List<Map> getDepartmentListCode() {
		return (List<Map>) departmentServiceMapper.queryDepartmentListCode();
	}

	/**
	 * 业务部信息初始化数据填充
	 */
	@Override
	public Map<String, Object> getUpdateListInfo(Long departmentId) {
		// 查询业务部基本信息
		DefDepartment def = defDepartmentMapper
				.selectByPrimaryKey(departmentId);

		// 查询业务部归属信息
		Map paramMap = new HashMap();
//		paramMap.put("departmentCode", def.getDepartmentCode());
		paramMap.put("departmentId", departmentId);

		List<Map> departmentBelongTraceList = departmentServiceMapper
				.queryDepartmentBelongListInfo(paramMap);
		Map<String, Object> result = new HashMap<String, Object>(2);

		result.put("def", def);
		result.put("departmentBelongTraceList", departmentBelongTraceList);
		return result;
	}

	/**
	 * 更新业务部
	 */
	@Override
	public ResultInfo updateDepartmentSave(Map map,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		DefDepartment defDefDepartmentSchema = (DefDepartment) map.get("defDepartmentSchema");// 新的业务部实体
		if(verifyDepartmentCode(defDefDepartmentSchema,"update")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("此业务部编码已存在！");
			return resultInfo;
		}
		
		if(updateDepartment(defDefDepartmentSchema,defDefDepartmentSchema.getDepartmentId(),loginInfo)){
			resultInfo.setMsg("操作成功");
			resultInfo.setSuccess(true);
			return resultInfo;
			}else{
				resultInfo.setMsg("操作失败");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		
//		Date date=new Date();
//		Calendar cal = Calendar.getInstance();
//		DefDepartment def = defDepartmentMapper
//				.selectByPrimaryKey(defDefDepartmentSchema.getDepartmentId());// 旧的业务部实体
//		Long id = commonService.getSeqValByName("SEQ_T_DEF_DEPARTMENT");
//		defDefDepartmentSchema.setOperComId(def.getOperComId());
//		
//		if(verifyDepartmentCode(defDefDepartmentSchema,"add")){
//			resultInfo.setSuccess(false);
//			resultInfo.setMsg("此业务部编码已存在！");
//			return resultInfo;
//		}
//		
//		if(defDefDepartmentSchema.getBelongStartDate()==null){
//			//提示归属开始时间必填
//			
//			resultInfo.setSuccess(false);
//			resultInfo.setMsg("归属开始时间必须填写！");
//			return resultInfo;
//		}
//		/*1、当此业务部的归属信息不变时，只执行更新*/
//		
//		System.out.println("旧的："+(defDefDepartmentSchema.getBelongEndDate()==null)+"-----新的："+(def.getBelongEndDate()==null));
//		if(defDefDepartmentSchema.getComId().compareTo(def.getComId())==0&&
//				defDefDepartmentSchema.getBelongStartDate().getTime()==def.getBelongStartDate().getTime()&&
//				(((defDefDepartmentSchema.getBelongEndDate()==null)&&(def.getBelongEndDate()==null))
//						||defDefDepartmentSchema.getBelongEndDate().getTime()==def.getBelongEndDate().getTime())//先判断是否为空，如果同时为空
//				){
//			defDefDepartmentSchema.setOperComId(def.getOperComId());
//			defDefDepartmentSchema.setCreateUserId(def.getCreateUserId());
//			defDefDepartmentSchema.setCreateDate(def.getCreateDate());
//			defDefDepartmentSchema.setRcState(def.getRcState());
//			BeanUtils.updateObjectSetOperateInfo(defDefDepartmentSchema,loginInfo);
//			defDepartmentMapper.updateByPrimaryKey(defDefDepartmentSchema);
//			
//			DepartmentBelongTrace departmentBelongTrace=new DepartmentBelongTrace();
//			Long depaBSeq = commonService.getSeqValByName("SEQ_T_DEPARMENT_BELONG_TRACE");
//			departmentBelongTrace.setBlcomId(depaBSeq);
//			departmentBelongTrace.setBelongComId(defDefDepartmentSchema.getComId());
//			departmentBelongTrace.setDepartmentId(defDefDepartmentSchema.getDepartmentId());
//			departmentBelongTrace.setStartDate(defDefDepartmentSchema.getBelongStartDate());
//			departmentBelongTrace.setEndDate(defDefDepartmentSchema.getBelongEndDate());
//			BeanUtils.insertObjectSetOperateInfo(departmentBelongTrace, loginInfo);
//			departmentBelongTraceMapper.insert(departmentBelongTrace);
//			
//			
//			resultInfo.setSuccess(true);
//			resultInfo.setMsg("信息修改成功！");
//			return resultInfo;
//		}
//		
//		/*2、当业务部的归属信息改变时，如下*/
//		
//		/*a、当上级机构改变时，修改上次信息的结束日期为当前日期-1，并判断此次的归属开始日期是否大于上次的结束日期*/
//		if(defDefDepartmentSchema.getComId().compareTo(def.getComId())!=0){
//			//判断此次开始时间是否大于上次开始时间
//			//1、将当前日期改为Calendar格式
//			cal.setTime(def.getBelongStartDate());
//			cal.add(Calendar.DATE, 1);
//			date=cal.getTime();
//			//判断，如果新输入的日期在旧的日期+1之后则允许添加并将旧的归属结束日期置为新的开始日期-1
//
//			
//			
//			if(defDefDepartmentSchema.getBelongStartDate().after(date)){//判断两次的开始时间的间隔
//				//将但前日起减去一天
//				cal.setTime(defDefDepartmentSchema.getBelongStartDate());
//				cal.add(Calendar.DATE, -1);
//				date=cal.getTime();
//				//将旧的结束日期改为新的开始日期减1
//				/*****************先将新的数据更新********************/
//				//将新的信息插入表中，并配新的id
//				defDefDepartmentSchema.setDepartmentId(def.getDepartmentId());
//				//修改操作员信息
//				defDefDepartmentSchema.setCreateUserId(def.getCreateUserId());
////				defDefDepartmentSchema.setModifyUserId(def.getModifyUserId());
//				defDefDepartmentSchema.setCreateDate(def.getCreateDate());
////				defDefDepartmentSchema.setModifyDate(def.getModifyDate());
////				defDefDepartmentSchema.setRcState("E");
//				//将信息添加入表中
//				
//				BeanUtils.deleteAndInsertObjectSetOperateInfo(def,defDefDepartmentSchema,loginInfo);
//				defDepartmentMapper.updateByPrimaryKey(defDefDepartmentSchema);
//				
//				DepartmentBelongTrace departmentBelongTrace=new DepartmentBelongTrace();
//				Long depaBSeq = commonService.getSeqValByName("SEQ_T_DEPARMENT_BELONG_TRACE");
//				departmentBelongTrace.setBlcomId(depaBSeq);
//				departmentBelongTrace.setBelongComId(defDefDepartmentSchema.getComId());
//				departmentBelongTrace.setDepartmentId(defDefDepartmentSchema.getDepartmentId());
//				departmentBelongTrace.setStartDate(defDefDepartmentSchema.getBelongStartDate());
//				departmentBelongTrace.setEndDate(defDefDepartmentSchema.getBelongEndDate());
//				BeanUtils.insertObjectSetOperateInfo(departmentBelongTrace, loginInfo);
//				departmentBelongTraceMapper.insert(departmentBelongTrace);
//				
//				
//				//提示修改成功
//				/****************将旧的数据插入*****************/
//				def.setBelongEndDate(date);
//				//添加操作员，和操作时间
//				def.setCreateUserId(def.getCreateUserId());
//				//修改旧信息的id
//				def.setDepartmentId(id);
//				//更新旧的信息
//				defDepartmentMapper.insert(def);
//				
//				resultInfo.setSuccess(true);
//				resultInfo.setMsg("信息修改成功！");
//				return resultInfo;
//			}else{
//				//提示开始日期不合适不能修改
//				//提示开始日期必须大于上次的开始日期
//				resultInfo.setSuccess(false);
//				resultInfo.setMsg("开始时间至少大于上次开始时间两天！");
//				return resultInfo;
//			}
//		}else{
//			//上级机构不变则不能修改开始时间
//			if(defDefDepartmentSchema.getBelongStartDate().getTime()!=def.getBelongStartDate().getTime()){
//				//不能修改
//				resultInfo.setSuccess(false);
//				resultInfo.setMsg("上级机构不变，则开始时间不能修改！");
//				return resultInfo;
//			}else{
//				if(defDefDepartmentSchema.getBelongStartDate().before(defDefDepartmentSchema.getBelongEndDate())){
//					defDefDepartmentSchema.setDepartmentId(def.getDepartmentId());
//					//修改操作员信息
//					defDefDepartmentSchema.setCreateUserId(def.getCreateUserId());
//					defDefDepartmentSchema.setModifyUserId(loginInfo.getUserId());
//					defDefDepartmentSchema.setCreateDate(def.getCreateDate());
//					defDefDepartmentSchema.setModifyDate(DateUtils.getCurrentTimestamp());
//					defDefDepartmentSchema.setRcState(Constants.EFFECTIVE_RECORD);
//					//将信息添加入表中
//					defDepartmentMapper.updateByPrimaryKey(defDefDepartmentSchema);
//					
//					DepartmentBelongTrace departmentBelongTrace=new DepartmentBelongTrace();
//					Long depaBSeq = commonService.getSeqValByName("SEQ_T_DEPARMENT_BELONG_TRACE");
//					departmentBelongTrace.setBlcomId(depaBSeq);
//					departmentBelongTrace.setBelongComId(defDefDepartmentSchema.getComId());
//					departmentBelongTrace.setDepartmentId(defDefDepartmentSchema.getDepartmentId());
//					departmentBelongTrace.setStartDate(defDefDepartmentSchema.getBelongStartDate());
//					departmentBelongTrace.setEndDate(defDefDepartmentSchema.getBelongEndDate());
//					BeanUtils.insertObjectSetOperateInfo(departmentBelongTrace, loginInfo);
//					departmentBelongTraceMapper.insert(departmentBelongTrace);
//					
//					//提示修改成功
//					resultInfo.setSuccess(true);
//					resultInfo.setMsg("信息修改成功！");
//					return resultInfo;
//				}else{
//					//不修改上级，不修改开始，不修改结束
//					//提交信息未做任何改变
//					resultInfo.setSuccess(false);
//					resultInfo.setMsg("不能重复提交！");
//					return resultInfo;
//				}
//			}
//		}
	}

	/**
	 * 删除业务部
	 */
	
	
	/**
	 1、如果归属结束日期为空则添加归属结束日期为当期日期+1.
	 2、如果结束日期为空给改业务部添加结束日期。
	 3、结束信息判断，如果开始日期是今日，则结束日期为今天+1
	 4、判断是否还有财富顾问与之关联，存在则提示不能删除。
	 */
	@Override
	@Transactional
	public ResultInfo delDepartment(Long id,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		DefDepartment defDep = defDepartmentMapper.selectByPrimaryKey(id);
		
		//1、判断是否有用户与之关联！判断条件业务部id和用户状态为E！
		DefUserExample defUserExample= new DefUserExample();
//		DefUserExample.Criteria criteriaUser= defUser.createCriteria();
		defUserExample.createCriteria().andDepartmentIdEqualTo(id).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<DefUser> userList= defUserMapper.selectByExample(defUserExample);
		if(userList!=null&& userList.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("此业务部下存在用户，不能删除！");
			return resultInfo;
		}
		
		//2、判断是否有财富顾问关联！判断条件业务部id和财富顾问状态为E
		AgentExample agentExample=new AgentExample();
//		AgentExample.Criteria criteriaAgent=agent.createCriteria();
		agentExample.createCriteria().andDepartmentIdEqualTo(id).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<Agent> agentList=agentMapper.selectByExample(agentExample);
		if(agentList!=null&&agentList.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("此业务部下存在用户，不能删除！");
			return resultInfo;
		}
		BeanUtils.deleteObjectSetOperateInfo(defDep,loginInfo);
		defDepartmentMapper.updateByPrimaryKey(defDep);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}

	/**
	 * 业务部编码的唯一性验证
	 * **/
	public boolean verifyDepartmentCode(DefDepartment def,String str) {
		DefDepartmentExample defDepartmentExample = new DefDepartmentExample();
		DefDepartmentExample.Criteria criteria = defDepartmentExample.createCriteria();
		criteria.andDepartmentCodeEqualTo(def.getDepartmentCode()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		if(def.getDepartmentId()!=null){
			criteria.andDepartmentIdNotEqualTo(def.getDepartmentId());
		}
		List<DefDepartment> defDepList = defDepartmentMapper.selectByExample(defDepartmentExample);
		if (defDepList != null && defDepList.size() > 0) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 添加轨迹信息
	 * */
	public boolean insterBelongCom(DefDepartment defdep,LoginInfo loginInfo){
		DepartmentBelongTrace departmentBelongTrace=new DepartmentBelongTrace();
		Long depaBSeq = commonService.getSeqValByName("SEQ_T_DEPARMENT_BELONG_TRACE");
		departmentBelongTrace.setBelongComId(defdep.getComId());
		departmentBelongTrace.setBlcomId(depaBSeq);
		departmentBelongTrace.setDepartmentId(defdep.getDepartmentId());
		departmentBelongTrace.setStartDate(defdep.getBelongStartDate());
		departmentBelongTrace.setEndDate(defdep.getBelongEndDate());
		BeanUtils.insertObjectSetOperateInfo(departmentBelongTrace, loginInfo);
		departmentBelongTraceMapper.insert(departmentBelongTrace);
		return true;
	}
	
	
	
	/**
	 * 将数据更新提出
	 * 
	 * */
	public boolean updateDepartment(DefDepartment defdep,Long id,LoginInfo loginInfo){
		DefDepartment dep=defDepartmentMapper.selectByPrimaryKey(id);
		BeanUtils.deleteAndInsertObjectSetOperateInfo(dep,defdep, loginInfo);
		defDepartmentMapper.updateByPrimaryKey(defdep);
		if(!insterBelongCom(defdep,loginInfo)){
			return false;
		}
		Long depId = commonService.getSeqValByName("SEQ_T_DEF_DEPARTMENT");
		dep.setDepartmentId(depId);
		defDepartmentMapper.insert(dep);
		return true;
	}
	
//	/**
//	 * 业务部关联性验证
//	 * **/
//	public ResultInfo verifyDepartmentId(String departmentId) {
//		ResultInfo resultInfo = new ResultInfo();
//		try {
//			AgentExample agentExample = new AgentExample();
//			AgentExample.Criteria criteria = agentExample.createCriteria();
//			Long dep = new Long(departmentId);
//			criteria.andDepartmentIdEqualTo(dep).andRcStateEqualTo(
//					Constants.EFFECTIVE_RECORD);
//			List<Agent> agentList = agentMapper.selectByExample(agentExample);
//			if (agentList != null && agentList.size() > 0) {
//				resultInfo.setSuccess(false);
//				resultInfo.setMsg("尚且有财富顾问与之关联，不能删除！");
//			} else {
//				resultInfo.setSuccess(true);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultInfo.setSuccess(false);
//			resultInfo.setMsg("通过业务部编码查询是否有财富顾问与之关联时出现异常！");
//			return resultInfo;
//		}
//		return resultInfo;
//	}
}
