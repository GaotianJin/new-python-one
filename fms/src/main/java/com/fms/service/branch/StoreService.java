package com.fms.service.branch;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fms.db.model.DefStore;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface StoreService
{
	public Map<String, Object> getPageList(DataGridModel dgm, DefStore defStore);
	public List<Map> getStoreListCode();
	public Map<String, Object> getUpdateListInfo(Long storeId);
	public ResultInfo addStoreSave(Map map ,LoginInfo loginInfo);
	public void deleteStoreSave(Long uid, LoginInfo loginInfo);
	public void updateStoreSave(Map map,LoginInfo loginInfo);
	public ResultInfo uploadStoreImage(MultipartFile[] storeImage, String param, LoginInfo loginInfo);
	public ResultInfo getAllStoreImage(String storeId,String fileType);
	public ResultInfo deleteStoreImage(String param,LoginInfo loginInfo);
}
