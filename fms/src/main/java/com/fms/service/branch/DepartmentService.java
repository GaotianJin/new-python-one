package com.fms.service.branch;

import java.util.List;
import java.util.Map;

import com.fms.db.model.DefDepartment;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface DepartmentService {
	
	/*添加业务部信息*/
	public ResultInfo savaDepartmentAdd(String param,LoginInfo loginInfo);
	
	/*查询业务部信息*/
	public Map<String, Object> queryDepartmentList(DataGridModel dgm,DefDepartment defdep);
	
	/*查询业务部信息组件*/
	public List<Map> getDepartmentListCode();
	
	/*删除业务部信息*/
	public ResultInfo delDepartment(Long id,LoginInfo loginInfo);
	
	/*查询*/
	public Map<String, Object> getUpdateListInfo(Long departmentId);
	
	/*更新*/
	public ResultInfo updateDepartmentSave(Map map,LoginInfo loginInfo);
	
//	/*业务部编码唯一性验证*/
//	public ResultInfo verifyDepartmentCode(String departmentCode);
//	
//	/*业务部关联性验证*/
//	public ResultInfo verifyDepartmentId(String departmentId);
}
