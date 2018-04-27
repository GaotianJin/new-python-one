package com.sinosoft.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.alibaba.fastjson.JSON;
import com.fms.db.model.BasicLawAssess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.application.util.CisCoreException;

public class JsonUtils {
	
	/**实体对象转换成JSON字符串
	 * @param object
	 * @return
	 */
	public static String objectToJsonStr(Object object){
		//Gson gson = new Gson();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd");
		Gson gson =  gsonBuilder.create();
		return gson.toJson(object);
	}
	
	/**json字符串转换成实体对象
	 * @param jsonStr
	 * @param targetObject
	 * @return
	 */
	public static Object jsonStrToObject(String jsonStr,Object targetObject){
		Object object = null;
		try {
			if (jsonStr!=null&&!"".equals(jsonStr)) {
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.setDateFormat("yyyy-MM-dd");
				Gson gson =  gsonBuilder.create();
				object = gson.fromJson(jsonStr, targetObject.getClass());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return object;
	}
	
	/**json字符串转换成实体对象
	 * @param jsonStr
	 * @param targetObject
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonStrToObject(String jsonStr, Class clazz){
		Object object = null;
		try {
			if(jsonStr!=null&&!"".equals(jsonStr)){
				/*GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.setDateFormat("yyyy-MM-dd");
				Gson gson =  gsonBuilder.create();*/
				object = clazz.newInstance();
				//object = gson.fromJson(jsonStr, clazz);
				
				object = JSON.parseObject(jsonStr, clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return object;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> jsonStrToMap(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(jsonStr!=null&&!"".equals(jsonStr)){
				JSONObject jsonObject = new JSONObject(jsonStr);
				Iterator<String> iterator = jsonObject.keys();
				while(iterator.hasNext()){
					String key = iterator.next();
					String value = jsonObject.getString(key);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return map;
	}
	
	/**从json字符串中根据key获取value
	 * @param key
	 * @param jsonStr
	 * @return
	 */
	public static String getJsonValueByKey(String key,String jsonStr){
		String jsonValue = "";
		try
		{
			JSONObject jsonObj = new JSONObject(jsonStr);
			if(jsonObj.has(key)){
				jsonValue = jsonObj.getString(key);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return jsonValue;
	}
	
	/**从json字符串中根据key获取value
	 * @param key
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List jsonArrStrToList(String jsonArrStr,Class clazz){
		List resultList = new ArrayList();
		try
		{
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyy-MM-dd");
			Gson gson =  gsonBuilder.create();
			if(jsonArrStr!=null&&!"".equals(jsonArrStr))
			{
				resultList = JSON.parseArray(jsonArrStr, clazz);
				/*JSONArray jsonArray = new JSONArray(jsonArrStr);
				for (int i = 0; i < jsonArray.length(); i++) {
					String jsonObjStr = jsonArray.getString(i);
					Object obj = clazz.newInstance();
					obj = gson.fromJson(jsonObjStr, clazz);
					resultList.add(obj);
				}*/
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return resultList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map<String, String>> jsonArrStrToListMap(String jsonArrStr){
		List resultList = new ArrayList();
		try
		{
			if(jsonArrStr!=null&&!"".equals(jsonArrStr)){
				JSONArray jsonArray = new JSONArray(jsonArrStr);
				for (int i = 0; i < jsonArray.length(); i++) {
					String jsonObjStr = jsonArray.getString(i);
					Map<String, String> map = jsonStrToMap(jsonObjStr);
					resultList.add(map);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return resultList;
	}
	/**
	 * 对请求参数按指定字符集格式进行解码
	 * @param urlParams
	 * @param encoding
	 * @return
	 */
	public static String decodeUrlParams(String urlParams,String encoding){
		try {
			urlParams = URLDecoder.decode(urlParams,encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return urlParams;
	}
	
	
	/**
	 * @param object
	 * @return
	 */
	public static Map<String, String> objectToMap(Object object){
		Class<?> clazz = object.getClass();
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field:fields) {
				String fieldName = field.getName();
				String fieldType = field.getType().getName();
				String first = fieldName.substring(0, 1).toUpperCase();
				String rest = fieldName.substring(1, fieldName.length());
				String fieldGetMethodName = new StringBuffer("get").append(first).append(rest).toString();
				Method fieldGetMethod = clazz.getDeclaredMethod(fieldGetMethodName);
				if (fieldType.equals("java.math.BigDecimal")) {
					BigDecimal bigDecimalVal = (BigDecimal)fieldGetMethod.invoke(object);
					if(bigDecimalVal!=null){
						resultMap.put(fieldName, bigDecimalVal.toString());
					}
				}
				else if (fieldType.equals("java.lang.String")) {
					String stringVal = (String)fieldGetMethod.invoke(object);
					if (stringVal!=null&&!"".equals(stringVal)) {
						resultMap.put(fieldName, stringVal);
					}
				}
				else if (fieldType.equals("java.lang.Short")) {
					Short shortVal = (Short)fieldGetMethod.invoke(object);
					if (shortVal!=null) {
						resultMap.put(fieldName, shortVal.toString());
					}
				}
				else if (fieldType.equals("java.util.Date")) {
					Date dateVal = (Date)fieldGetMethod.invoke(object);
					if (dateVal!=null) {
						String dateValStr = DateUtils.getDateTimeString(dateVal);
						String dateTime = dateValStr.substring(11);
						if(dateTime!=null&&!"".equals(dateTime)&&"00:00:00".equals(dateTime)){
							resultMap.put(fieldName, dateValStr.substring(0, 10));
						}else {
							resultMap.put(fieldName, dateValStr);
						}
					}
				}
				else if (fieldType.equals("java.lang.Integer")) {
					Integer dateVal = (Integer)fieldGetMethod.invoke(object);
					if (dateVal!=null) {
						resultMap.put(fieldName, dateVal.toString());
					}
				}
				else if (fieldType.equals("java.lang.Long")) {
					Long dateVal = (Long)fieldGetMethod.invoke(object);
					if (dateVal!=null) {
						resultMap.put(fieldName, dateVal.toString());
					}
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @param list
	 * @return
	 */
	public static List<Map<String, String>> listObjectToListMap(List<?> list){
		List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		try {
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Object object = list.get(i);
					Map<String, String> objectMap = objectToMap(object);
					mapList.add(objectMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return mapList;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object mapToObject(Map map,Class<?> clazz){
		Object object = null;
		try {
			object = jsonStrToObject(objectToJsonStr(map), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<?> listMapToListObj(List<?> list,Class<?> clazz){
		List<Object> listObj = new ArrayList();
		try {
			if (list!=null&&list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map)list.get(i);
					Object object = mapToObject(map, clazz);
					listObj.add(object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CisCoreException("Json处理异常");
		}
		return listObj;
	}
	
	/**
	 * 判断请求报文和响应报文是否相同
	 * @param secretKey
	 * @param jsonStr
	 * @return
	 */
	public static boolean checkWechatJsonLogin(String secretKey,String jsonStr){
		String wechatHeader = getJsonValueByKey("wechatHeader", jsonStr);
		String wechatContent = getJsonValueByKey("wechatContent", jsonStr);
		String customerName=getJsonValueByKey("customerName", wechatContent);
		String idNo=getJsonValueByKey("idNo", wechatContent);
		String mobile=getJsonValueByKey("mobile", wechatContent);
		String wechatContent1="{\"customerName\":\""+customerName+"\",\"idNo\":\""+idNo+"\",\"mobile\":\""+mobile+"\"}";
		if(wechatHeader==null||"".equals(wechatHeader)){
			return false;
		}
		String signMsg = getJsonValueByKey("signMsg", wechatHeader);
		//MD5加密
		String md5EncodeJsonContent =XmlUtils.getMd5(wechatContent1+secretKey);
		System.out.println("请求密钥-----"+signMsg+"响应密钥"+md5EncodeJsonContent);
		if (md5EncodeJsonContent!=null&&!"".equals(md5EncodeJsonContent)
				&&signMsg!=null&&!"".equals(signMsg)&&md5EncodeJsonContent.equals(signMsg)) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断请求报文和响应报文是否相同
	 * @param secretKey
	 * @param jsonStr
	 * @return
	 */
	public static boolean checkWechatJson(String secretKey,String jsonStr){
		String wechatHeader = getJsonValueByKey("wechatHeader", jsonStr);
		String wechatContent = getJsonValueByKey("wechatContent", jsonStr);
		/*String customerName=getJsonValueByKey("customerName", wechatContent);
		String idNo=getJsonValueByKey("idNo", wechatContent);
		String mobile=getJsonValueByKey("mobile", wechatContent);
		String wechatContent1="{\"customerName\":\""+customerName+"\",\"idNo\":\""+idNo+"\",\"mobile\":\""+mobile+"\"}";*/
		if(wechatHeader==null||"".equals(wechatHeader)){
			return false;
		}
		String signMsg = getJsonValueByKey("signMsg", wechatHeader);
		//MD5加密
		String md5EncodeJsonContent =XmlUtils.getMd5(wechatContent+secretKey);
		System.out.println("请求密钥-----"+signMsg+"响应密钥"+md5EncodeJsonContent);
		if (md5EncodeJsonContent!=null&&!"".equals(md5EncodeJsonContent)
				&&signMsg!=null&&!"".equals(signMsg)&&md5EncodeJsonContent.equals(signMsg)) {
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean checkWechatNetvalueJson(String secretKey,String jsonStr){
		String wechatHeader = getJsonValueByKey("wechatHeader", jsonStr);
		String wechatContent = getJsonValueByKey("wechatContent", jsonStr);
		String productId=getJsonValueByKey("productId", wechatContent);
		String type=getJsonValueByKey("type", wechatContent);
		String wechatContent1="{\"productId\":\""+productId+"\",\"type\":\""+type+"\"}";
		if(wechatHeader==null||"".equals(wechatHeader)){
			return false;
		}
		String signMsg = getJsonValueByKey("signMsg", wechatHeader);
		//MD5加密
		String md5EncodeJsonContent =XmlUtils.getMd5(wechatContent1+secretKey);
		System.out.println("请求密钥-----"+signMsg+"响应密钥"+md5EncodeJsonContent);
		if (md5EncodeJsonContent!=null&&!"".equals(md5EncodeJsonContent)
				&&signMsg!=null&&!"".equals(signMsg)&&md5EncodeJsonContent.equals(signMsg)) {
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		/*Map map = new HashMap();
		
		String jsonStr = "[{dbid:1,companyCode:86,companyName:绿城财富,companyShortName:绿城,companyEnglishName:lvchengcaifu},"+
				"{dbid:2,companyCode:8602,companyName:绿城财富,companyShortName:绿城,companyEnglishName:lvchengcaifu}]";
		List<Map<String, String>> list = jsonArrStrToListMap(jsonStr);
		System.out.println("list:"+objectToJsonStr(list));*/
		String REDUCTIONRADIO = "0";
		BigDecimal SUBSCRIPTIONFEERATIO = new BigDecimal((100.00-Double.parseDouble(REDUCTIONRADIO))/100);
		double fee = NumberUtils.mul("18000", SUBSCRIPTIONFEERATIO.toString());
		System.out.println(fee);
		/*BDCompany bdCompany = new BDCompany();
		
		bdCompany = (BDCompany)jsonStrToObject(jsonStr,bdCompany);
		bdCompany.setInsertTime(DateUtils.getCurrentTimestamp());
		Map<String, String> objectMap = objectToMap(bdCompany);
		System.out.println("JSONStr:"+objectToJsonStr(objectMap));
		jsonStr = objectToJsonStr(objectMap);
		bdCompany = (BDCompany)jsonStrToObject(jsonStr,bdCompany);
		System.out.println("JSONStr:"+objectToJsonStr(bdCompany));*/
		/*System.out.println(bdCompany.getDbid());
		System.out.println(bdCompany.getCompanyCode());
		
		System.out.println("JSONStr:"+objectToJsonStr(bdCompany));
		
		jsonStr = objectToJsonStr(bdCompany);
		map = (Map)jsonStrToObject(jsonStr,map);
		System.out.println(objectToJsonStr(map));
		System.out.println("companyName:"+map.get("companyName").toString());
		jsonStr = objectToJsonStr(map);
		bdCompany = (BDCompany)jsonStrToObject(jsonStr,bdCompany);
		System.out.println(bdCompany.getDbid());
		System.out.println(bdCompany.getDbid().toString());
		System.out.println("JSONStr1111:"+objectToJsonStr(bdCompany));*/
		/*String custAddressList = "[{addressType:01,province:110000,city:110100,country:110103,street:qqqqqqqqqqqqqqqqq,zipCode:123},"
							+"{addressType:02,province:120000,city:120100,country:120103,street:wwwwwwwww,zipCode:123}]";
		CustAddressInfo custAddressInfo = new CustAddressInfo();
		List<CustAddressInfo> list = jsonArrStrToList(custAddressList,CustAddressInfo.class);
		System.out.println(objectToJsonStr(list));*/
	}
	
}
