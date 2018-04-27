package com.eighth.web.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.Dynamic;
import com.eighth.entity.Users;
import com.eighth.service.DynamicService;
@Controller
public class DynamicCtroller {
	
	@Autowired
	private DynamicService dser;
	
	/**
	 * 显示所有信息
	 * */
	@RequestMapping("/ShowDynamic")
	@ResponseBody
	public Object getAllDynamic(int page,int rows,HttpSession session){
		Users u= (Users) session.getAttribute("user");
		Dynamic d=new Dynamic(u.getUserId(), page, rows);
		Map map=dser.getDatagrid(d);
		return map;
	}
	
	@RequestMapping("/addDynamic")
	@ResponseBody
	public Object insertDynamic(Dynamic d){
		int row=dser.insertDynamic(d);
		Map map=new HashMap();
		if(row==0){
			map.put("errorMsg", false);
		}else{
			map.put("errorMsg", true);
		}
		return map;
	}
	
	@RequestMapping("/openDynamic")
	@ResponseBody
	public Object openDynamic(HttpSession session){
		Users u=(Users) session.getAttribute("user");
		Dynamic d=new Dynamic();
		d.setDyaskerId(u.getUserId());
		int count=dser.getNoCount(d);
		Map map=new HashMap();
		if(count==0){
			map.put("success", false);
		}else{
			map.put("success", true);
			map.put("count", count);
		}
		return map;
	}
	
	@RequestMapping("/modDynamic")
	@ResponseBody
	public Object modDynamic(String id){
		int row=dser.updateDynamic(id);
		if(row==0){
			return false;
		}else{
			return true;
		}
	}
}
