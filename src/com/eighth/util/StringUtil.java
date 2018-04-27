package com.eighth.util;

import java.util.UUID;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)||str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if( !"".equals(str) && str!=null && !"null".equals(str) ){
			return true;
		}else{
			return false;
		}
	}
	
	public static String isNull(String str){
		if("".equals(str)||str==null){
			return null;
		}else{
			return str;
		}
	}
	
	public static Integer isInt(int str){
		if(str>0){
			return str;
		}else{
			return null;
		}
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
