package com.eighth.web.ctrl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.Netfollows;
import com.eighth.entity.Users;
import com.eighth.service.NetFollowService;
import com.eighth.util.DateUtil;
import com.eighth.util.StringUtil;
@Controller
public class NetFollowCtroller {
	@Autowired
	private NetFollowService nservice;
	
	@RequestMapping("/showAllFollow")
	@ResponseBody
	public Object getAll(int page,int rows,String studentName,String  dateA,String dateB,String followState,String followType,String sasker){
		Netfollows nf=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			nf= new Netfollows(StringUtil.isNull(sasker),StringUtil.isNull(studentName), StringUtil.isNull(followType), StringUtil.isNull(followState), page, rows,StringUtil.isEmpty(dateA)? null: sdf.parse(dateA),
					StringUtil.isEmpty(dateB) ? null: sdf.parse(dateB));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map map=nservice.getDatagrid(nf);
		return map;
	}
	
	@RequestMapping("/showAllFollowByAskerId")
	@ResponseBody
	public Object getAllByAskerId(int page,int rows,String studentName,String  dateA,String dateB,String followState,String followType,HttpSession session){
		Users u=(Users) session.getAttribute("user");
		Netfollows nf=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			nf= new Netfollows(StringUtil.isNull(u.getUserId()),StringUtil.isNull(studentName), StringUtil.isNull(followType), StringUtil.isNull(followState), page, rows,StringUtil.isEmpty(dateA)? null: sdf.parse(dateA),
					StringUtil.isEmpty(dateB) ? null: sdf.parse(dateB));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map map=nservice.getDatagrid(nf);
		return map;
	}
	
	@RequestMapping(value="/addFollow")
	@ResponseBody
	public Object addFollow(String studentId,String studentName,String followTime,String followState,String followType,String nextFollowTime,String content,HttpSession session){
		Users u=(Users) session.getAttribute("user");
		Netfollows nf=new Netfollows();
		nf.setStudentId(StringUtil.isNull(studentId));
		nf.setStudentName(StringUtil.isNull(studentName));
		nf.setFollowTime(DateUtil.setStringToDate(followTime));
		nf.setFollowState(StringUtil.isNull(followState));
		nf.setFollowType(StringUtil.isNull(followType));
		nf.setNextFollowTime(DateUtil.setStringToDate(nextFollowTime));
		nf.setContent(StringUtil.isNull(content));
		nf.setUserId(u.getUserId());
		System.out.println("添加的跟踪为："+nf);
		int row=nservice.insertNetFollow(nf);
		Map map=new HashMap();
		if(row==0){
			map.put("errorMsg", false);
		}else{
			map.put("errorMsg", true);
		}
		return map;
	}
	
	
	
	@RequestMapping("/showFollow")
	@ResponseBody
	public Object getFollowByStuId(int page,int rows,String stuId){
		Netfollows nf=new Netfollows(stuId,page,rows);
		Map map=nservice.getDatagridByStuId(nf);
		return map;
	}
}
