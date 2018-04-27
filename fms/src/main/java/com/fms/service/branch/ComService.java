package com.fms.service.branch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;

public interface ComService
{
	public Map<String, Object> getPageList(DataGridModel dgm, DefCom defCom);
	public List<Map> getComListCode();
	public Map<String, Object> getUpdateListInfo(Long comId);
	public boolean addComSave(Map map, LoginInfo loginInfo);
	public void deleteComSave(Long uid, LoginInfo loginInfo);
	public void updateComSave(Map map, LoginInfo loginInfo);
}
