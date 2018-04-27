package com.sinosoft.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class MapUtils {

	
	/**将map转换成object对象
	 * @param map
	 * @return
	 */
	public static Object mapToObject(Map<?, ?> sourceMap,Object targetObject){
		Gson gson = new Gson();
		String jsonStr = gson.toJson(sourceMap);
		targetObject = gson.fromJson(jsonStr, targetObject.getClass());
		return targetObject;
	}
	
	/**将实体对象转换成Map
	 * @param sourceObject
	 * @param targetMap
	 * @return
	 */
	public static Map<?, ?> objectToMap(Object sourceObject,Map<?, ?> targetMap){
		Gson gson = new Gson();
		String jsonStr = gson.toJson(sourceObject);
		targetMap = gson.fromJson(jsonStr, targetMap.getClass());
		return targetMap;
	}
	
	/**
	 * @param map
	 * @param key
	 * @return
	 */
	public static Object getValue(Map map,String key){
		Object object = new Object();
		if (map==null||map.size()==0) {
			return object;
		}
		if (!map.containsKey(key)) {
			return object;
		}
		object = map.get(key);
		return object;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dbid", "1");
		map.put("companyCode", "86");
		map.put("companyName", "巨鲸财富");
		map.put("companyShortName", "巨鲸");
		map.put("companyEnglishName", "whalewealth");
		
		//BDCompany bdCompany = new BDCompany();
		
		//bdCompany = (BDCompany)mapToObject(map,bdCompany);
		
		//System.out.println(bdCompany.getCompanyCode()+";"+bdCompany.getCompanyName());
		
		//Map<String, String> bdCompanyMap = new HashMap<String, String>();
		
		//bdCompanyMap = (Map<String, String>)objectToMap(bdCompany, bdCompanyMap);
		
		//System.out.println(bdCompanyMap.get("dbid").toString()+";"+bdCompanyMap.get("companyEnglishName"));
		
	}
	
	
	
}
