package com.fms.service.branch;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.fms.db.model.DefBuilding;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface BuildingService {
	
	/*添加楼盘信息*/
	public ResultInfo savaBuildAdd(String param,LoginInfo loginInfo);
	
	/*删除楼盘信息*/
	public ResultInfo deleteBuild(Long id,LoginInfo loginInfo);
	
	/*查询楼盘信息组件*/
	public List<Map> getBuildListCode();
	
	/*更新*/
	public Map<String, Object> getUpdateListInfo(Long buildingId);
	
	/*楼盘信息更新提交*/
	public ResultInfo updateBuildSave(Map map,LoginInfo loginInfo);
	
	public DataGrid queryBuildList(DataGridModel dgm,Map paramMap);
}
