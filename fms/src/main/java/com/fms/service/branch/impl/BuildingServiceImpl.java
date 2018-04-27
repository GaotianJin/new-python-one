package com.fms.service.branch.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.DefBuildingMapper;
import com.fms.db.mapper.DefStoreMapper;
import com.fms.db.model.DefBuilding;
import com.fms.db.model.DefBuildingExample;
import com.fms.db.model.DefStore;
import com.fms.db.model.DefStoreExample;
import com.fms.service.branch.BuildingService;
import com.fms.service.mapper.BuildServiceMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private DefBuildingMapper defBuildingMapper;
	@Autowired
	private DefStoreMapper defStoreMapper;
	@Autowired
	private BuildServiceMapper buildServiceMapper;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 添加楼盘信息*/
	@Transactional
	@Override
	public ResultInfo savaBuildAdd(String param,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String comInfo = JsonUtils.getJsonValueByKey("comInfo", param);
		String oldId=JsonUtils.getJsonValueByKey("addBuildingId", param);
		
		DefBuilding addBuilding = gson.fromJson(comInfo, DefBuilding.class);
		if(addBuilding.getBuildingType()==null||addBuilding.getBuildingType().equals("")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该楼盘类型不能为空！");
			return resultInfo;
		}
		
		if(addBuilding.getEstateType()==null||addBuilding.getEstateType().equals("")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该物业类型不能为空！");
			return resultInfo;
		}
		
		if(oldId!=null&&(!"null".equals(oldId))){
			addBuilding.setBuildingId(new Long(oldId));
		}

		if(verifyBuildingCode(addBuilding,"add")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该楼盘编码已经存在!");
			return resultInfo;
		}
		
		if(addBuilding.getBuildingId()!=null){
			//根据id查实体
			DefBuilding def=defBuildingMapper.selectByPrimaryKey(addBuilding.getBuildingId());
			BeanUtils.deleteAndInsertObjectSetOperateInfo(def,addBuilding, loginInfo);
			defBuildingMapper.updateByPrimaryKey(addBuilding);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("楼盘信息添加成功！");
			return resultInfo;
		}else{
		Long id = commonService.getSeqValByName("SEQ_T_DEF_BUILDING");
		addBuilding.setBuildingId(id);
		BeanUtils.insertObjectSetOperateInfo(addBuilding,loginInfo);
		defBuildingMapper.insert(addBuilding);
		resultInfo.setObj(addBuilding.getBuildingId());
		resultInfo.setSuccess(true);
		resultInfo.setMsg("楼盘信息添加成功！");
		return resultInfo;
		}

	}
	
	/**
	 * 删除楼盘
	 */
	public ResultInfo deleteBuild(Long id,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {			
			DefBuilding def=defBuildingMapper.selectByPrimaryKey(id);
			//查询与楼盘相关联的网点信息
			DefStoreExample defStoreExample= new DefStoreExample(); 
			DefStoreExample.Criteria criteria= defStoreExample.createCriteria();
			criteria.andBuildingIdEqualTo(id).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<DefStore> StoreList=defStoreMapper.selectByExample(defStoreExample);
			if(StoreList!=null&&StoreList.size()>0){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("此楼盘与网点存在关联，不能删除！");
				return resultInfo;
			}
			BeanUtils.deleteObjectSetOperateInfo(def,loginInfo);
			defBuildingMapper.updateByPrimaryKey(def);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("楼盘信息删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("楼盘信息删除失败！");
		}
		return resultInfo;
	}
	
	
	/**
	 * 楼盘信息下拉项，初始化事件
	 * */
	@Override
	public List<Map> getBuildListCode(){
		return (List<Map>) buildServiceMapper.queryBuildListCode();
	}
	
	/**
	 * 更新页面信息带入根据楼盘ID
	 */
	@Override
	public Map<String, Object> getUpdateListInfo(Long buildingId){
		DefBuilding def = defBuildingMapper.selectByPrimaryKey(buildingId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("def", def);
		return result;
	}
	
	/**
	 * 更新楼盘信息并保存
	 * **/
	@Override
	public ResultInfo updateBuildSave(Map map,LoginInfo loginInfo){
		
		ResultInfo resultInfo = new ResultInfo();
		
		DefBuilding defBuildingSchema = (DefBuilding)map.get("defBuildingSchema");//获取map中的数据信息。
		if(defBuildingSchema.getBuildingId()==null||(defBuildingSchema.getBuildingId()).equals("")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该楼盘id存在异常！");
			return resultInfo;
		}
		
		if(defBuildingSchema.getBuildingType()==null||defBuildingSchema.getBuildingType().equals("")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该楼盘类型不能为空！");
			return resultInfo;
		}
		
		if(defBuildingSchema.getEstateType()==null||defBuildingSchema.getEstateType().equals("")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该物业类型不能为空！");
			return resultInfo;
		}
		
		if(verifyBuildingCode(defBuildingSchema,"update")){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该楼盘编码已经存在!");
			return resultInfo;
		}
		DefBuilding def=defBuildingMapper.selectByPrimaryKey(defBuildingSchema.getBuildingId());//根据id在表中查找到的数据信息
		defBuildingSchema.setCreateDate(def.getCreateDate());
		defBuildingSchema.setRcState(Constants.EFFECTIVE_RECORD);
		defBuildingSchema.setCreateUserId(def.getCreateUserId());
		defBuildingSchema.setOperComId(loginInfo.getLoginComId());
		defBuildingSchema.setStarDate(def.getStarDate());
		defBuildingSchema.setModifyDate(DateUtils.getCurrentTimestamp());
		defBuildingSchema.setModifyUserId(loginInfo.getUserId());
		defBuildingMapper.updateByPrimaryKey(defBuildingSchema);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("修改楼盘信息成功！");
		return resultInfo;
	}
	
	
	/**
	 * 楼盘编码的唯一性验证
	 * **/
	public boolean verifyBuildingCode(DefBuilding oldBuildingCode,String str){
		DefBuildingExample defBuildingExample = new DefBuildingExample();
		DefBuildingExample.Criteria criteria = defBuildingExample
				.createCriteria();
		criteria.andBuildingCodeEqualTo(oldBuildingCode.getBuildingCode())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		if (oldBuildingCode.getBuildingId()!=null) {
			criteria.andBuildingIdNotEqualTo(oldBuildingCode.getBuildingId());
		}
		List<DefBuilding> buildingList = defBuildingMapper
				.selectByExample(defBuildingExample);
		if (buildingList != null && buildingList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**楼盘信息查询的新方法**/
	public DataGrid queryBuildList(DataGridModel dgm, Map paramMap) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = buildServiceMapper.branchBuildListCount(paramMap);
		List<Map> resultList = buildServiceMapper.branchBuildListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
}
