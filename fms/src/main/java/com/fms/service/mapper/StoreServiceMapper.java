package com.fms.service.mapper;

import java.util.List;
import java.util.Map;
import com.fms.db.model.DefStore;


public interface StoreServiceMapper {
	
	/**
	 * 网点信息列表查询（全部）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryStoreListInfo(Map paramMap);
	
	/**
	 * 网点信息列表查询（总记录数）
	 * @param paramMap
	 * @return
	 */
	public Integer queryStoreListInfoCounts(Map paramMap);
	
	
	/**
	 * 网点信息列表查询（分页查询）
	 * @param paramMap
	 * @return
	 */
	public List<Map> queryStoreListInfoPages(Map paramMap);
	
	
	/**
	 * 网点下拉项信息查询
	 * @return
	 */
	public List<Map>  queryStoreListCode();
	
	
	/**
	 * 查询网点归属信息列表
	 * @return
	 */
	public List<Map> queryStoreBelongListInfo(Map paramMap);
	
	
	/**
	 * 根据网点名称查询机构
	 * @param defStore
	 * @return
	 */
	public List<DefStore> queryStoreByStoreName(DefStore defStore);
	
	/**
	 * 根据网点代码查询机构
	 * @param defStore
	 * @return
	 */
	public List<DefStore> queryStoreByStoreCode(DefStore defStore);
}
