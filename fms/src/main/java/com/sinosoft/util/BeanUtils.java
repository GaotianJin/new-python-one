package com.sinosoft.util;

import java.lang.reflect.Method;
import java.util.Date;

import com.fms.db.model.CustBaseInfo;
import com.sinosoft.core.interfaces.util.Constants;


public class BeanUtils extends org.springframework.beans.BeanUtils{

	
	public static void insertObjectSetOperateInfo(Object object,LoginInfo loginInfo){
		Class<?> clazz = object.getClass(); 
		try {
			Method setRcStateMethod = clazz.getDeclaredMethod("setRcState",String.class);
			Method setCreateDateMethod = clazz.getDeclaredMethod("setCreateDate",Date.class);
	        Method setModifyDateMethod = clazz.getDeclaredMethod("setModifyDate",Date.class);
	        Method setOperComIdMethod = clazz.getDeclaredMethod("setOperComId",Long.class);
	        Method setCreateUserIdMethod = clazz.getDeclaredMethod("setCreateUserId",Long.class);
	        Method setModifyUserIdMethod = clazz.getDeclaredMethod("setModifyUserId",Long.class);
	        setRcStateMethod.invoke(object, Constants.EFFECTIVE_RECORD);
	        setCreateDateMethod.invoke(object, DateUtils.getCurrentTimestamp());
	        setModifyDateMethod.invoke(object, DateUtils.getCurrentTimestamp());
	        setOperComIdMethod.invoke(object, loginInfo.getComId());
	        setCreateUserIdMethod.invoke(object, loginInfo.getUserId());
	        setModifyUserIdMethod.invoke(object, loginInfo.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void deleteObjectSetOperateInfo(Object object,LoginInfo loginInfo){
		Class<?> clazz = object.getClass(); 
		try {
			Method setRcStateMethod = clazz.getDeclaredMethod("setRcState",String.class);
	        Method setModifyDateMethod = clazz.getDeclaredMethod("setModifyDate",Date.class);
	        Method setOperComIdMethod = clazz.getDeclaredMethod("setOperComId",Long.class);
	        Method setModifyUserIdMethod = clazz.getDeclaredMethod("setModifyUserId",Long.class);
	        setRcStateMethod.invoke(object, Constants.DELETE_RECORD);
	        setModifyDateMethod.invoke(object, DateUtils.getCurrentTimestamp());
	        setOperComIdMethod.invoke(object, loginInfo.getComId());
	        setModifyUserIdMethod.invoke(object, loginInfo.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void updateObjectSetOperateInfo(Object object,LoginInfo loginInfo){
		Class<?> clazz = object.getClass(); 
		try {
	        Method setModifyDateMethod = clazz.getDeclaredMethod("setModifyDate",Date.class);
	        //Method setOperComIdMethod = clazz.getDeclaredMethod("setOperComId",Long.class);
	        Method setModifyUserIdMethod = clazz.getDeclaredMethod("setModifyUserId",Long.class);
	        setModifyDateMethod.invoke(object, DateUtils.getCurrentTimestamp());
	        //setOperComIdMethod.invoke(object, loginInfo.getComId());
	        setModifyUserIdMethod.invoke(object, loginInfo.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void deleteAndInsertObjectSetOperateInfo(Object sourceObject,Object targetObject,LoginInfo loginInfo){
		Class<?> targeClazz = targetObject.getClass();
		Class<?> sourceClazz = sourceObject.getClass(); 
		try {
			Method sourceGetCreateDateMethod = sourceClazz.getDeclaredMethod("getCreateDate");
			Method sourceGetCreateUserIdMethod = sourceClazz.getDeclaredMethod("getCreateUserId");
			Method sourceGetOperComIdMethod = sourceClazz.getDeclaredMethod("getOperComId");
			//Method sourceSetModifyDateMethod = sourceClazz.getDeclaredMethod("setModifyDate",Date.class);
//	        Method sourceSetOperComIdMethod = sourceClazz.getDeclaredMethod("setOperComId",Long.class);
	        //Method sourceSetModifyUserIdMethod = sourceClazz.getDeclaredMethod("setModifyUserId",Long.class);
	        Method sourceSetRcStateMethod = sourceClazz.getDeclaredMethod("setRcState",String.class);
	        //设置目标对象的操作信息
			Method targetSetRcStateMethod = targeClazz.getDeclaredMethod("setRcState",String.class);
			Method targetSetCreateDateMethod = targeClazz.getDeclaredMethod("setCreateDate",Date.class);
	        Method targetSetModifyDateMethod = targeClazz.getDeclaredMethod("setModifyDate",Date.class);
	        Method targetSetOperComIdMethod = targeClazz.getDeclaredMethod("setOperComId",Long.class);
	        Method targetSetCreateUserIdMethod = targeClazz.getDeclaredMethod("setCreateUserId",Long.class);
	        Method targetSetModifyUserIdMethod = targeClazz.getDeclaredMethod("setModifyUserId",Long.class);
	        targetSetRcStateMethod.invoke(targetObject, Constants.EFFECTIVE_RECORD);
	        targetSetCreateDateMethod.invoke(targetObject, sourceGetCreateDateMethod.invoke(sourceObject));
	        targetSetModifyDateMethod.invoke(targetObject, DateUtils.getCurrentTimestamp());
	        Object operComId = sourceGetOperComIdMethod.invoke(sourceObject);
	        if(operComId==null){
	        	targetSetOperComIdMethod.invoke(targetObject, loginInfo.getComId());
	        }else {
	        	targetSetOperComIdMethod.invoke(targetObject, operComId);
			}
	        targetSetCreateUserIdMethod.invoke(targetObject, sourceGetCreateUserIdMethod.invoke(sourceObject));
	        targetSetModifyUserIdMethod.invoke(targetObject, loginInfo.getUserId());
	        //设置源对象的操作信息
	        /*sourceSetModifyDateMethod.invoke(sourceObject, DateUtils.getCurrentTimestamp());
	        sourceSetOperComIdMethod.invoke(sourceObject, loginInfo.getComId());
	        sourceSetModifyUserIdMethod.invoke(sourceObject, loginInfo.getUserId());*/
	        sourceSetRcStateMethod.invoke(sourceObject, Constants.DELETE_RECORD);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*TfPosItem tfPosItem1 = new TfPosItem();
		TfPosItem tfPosItem2 = new TfPosItem();

		tfPosItem1.setId(1);
		tfPosItem2.setId(2);
		BeanUtils.copyProperties(tfPosItem1, tfPosItem2);
		System.out.println(tfPosItem1.getId());
		System.out.println(tfPosItem2.getId());
*/
	}

}
