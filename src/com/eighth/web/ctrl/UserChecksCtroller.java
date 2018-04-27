package com.eighth.web.ctrl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.UserChecks;
import com.eighth.entity.Users;
import com.eighth.service.UserChecksService;
import com.eighth.util.StringUtil;
@Controller
public class UserChecksCtroller {
	
	@Autowired
	private UserChecksService service;
	
	@RequestMapping("/getUserChecksAll")
	@ResponseBody
	public Object getAllUserChecks(int page,int rows,String UserName,String checkInTime,String CheckOutTime,String checkState){
		Date date1=null;
		Date date2=null;
		if(checkInTime==""||checkInTime==null){
			date1=null;
		}else{
			date1=Date.valueOf(checkInTime);
		}
		if(CheckOutTime==""||CheckOutTime==null){
			date2=null;
		}else{
			date2=Date.valueOf(CheckOutTime);
		}
		UserChecks u=new UserChecks(StringUtil.isNull(UserName), date1, StringUtil.isNull(checkState), date2, page, rows);
		Map map=service.getDatagrid(u);
		return map;
	}
	/**
	 * 签到
	 * @param session
	 * @return
	 */
	@RequestMapping("/addUserChecks")
	@ResponseBody
	public Object addUserChecks(HttpSession session){
		Users users=(Users)session.getAttribute("user");
		java.util.Date date=new java.util.Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date);
		Date checkintime=Date.valueOf(time);
		UserChecks uc=new UserChecks();
		uc.setUserId(users.getUserId());
		uc.setCheckState("已签到");
		uc.setUserName(users.getUserName());
		uc.setCheckInTime(checkintime);
		List<UserChecks> list=service.selectUserChecksByUserIDAndDate(uc);
		Calendar now = Calendar.getInstance();
		Map map=new HashMap();
		if(list.size()>0){
			map.put("success", false);
			map.put("message", "今日已签到,请勿重复签到！");
		}else{
			if(now.get(Calendar.HOUR_OF_DAY)<8){
			int row=service.insertUserChecks(uc);
			if(row==0){
				map.put("success", false);
				map.put("message", "签到失败,请重试！");
			}else{
				map.put("success", true);
				map.put("message", "签到成功！");
				}
			}else{
				uc.setCheckState("迟到");
				int row=service.insertUserChecks(uc);
				if(row==0){
					map.put("success", false);
					map.put("message", "签到失败,请重试！");
				}else{
					map.put("success", true);
					map.put("message", "签到成功！");
					}
			}
		}
		return map;
	}
	/**
	 * 控制显示签到或签退
	 * @param session
	 * @return
	 */
	@RequestMapping("/showAValue")
	@ResponseBody
	public Object showAValue(HttpSession session){
		Users us=(Users)session.getAttribute("user");
		java.util.Date date=new java.util.Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date);
		Date checkintime=Date.valueOf(time);
		UserChecks u=new UserChecks();
		u.setUserId(us.getUserId());
		u.setCheckInTime(checkintime);
		List<UserChecks> list=service.selectUserChecksByUserIDAndDate(u);
		System.out.println("list.size="+list.size()+"+++++++++++++++++++++++++++++++++++++++++++++++++");
		if(list.size()==0){//当天没签到
			return true;//显示签到
		}else{//签过到
			 java.util.Date checkouttime = null;
			for (UserChecks uc : list) {
				checkouttime=uc.getCheckOutTime();
			}
			if(checkouttime==null){//如果没签退
				return false;//显示签退
			}else{//已经签退
				return true;//显示签到
			}
			
		}
	}
	@RequestMapping("/userCheckout")
	@ResponseBody
	public Object userCheckout(HttpSession session){
		Users us=(Users)session.getAttribute("user");
		java.util.Date date=new java.util.Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		java.util.Date checkouttime=null;
		try {
			checkouttime = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		String time1=format1.format(date);
		Date checkintime=Date.valueOf(time1);
		UserChecks u=new UserChecks();
		u.setUserId(us.getUserId());
		u.setCheckState("已签退");
		u.setCheckOutTime(checkouttime);
		u.setCheckInTime(checkintime);
		Calendar now = Calendar.getInstance();
		Map map=new HashMap();
		if(now.get(Calendar.HOUR_OF_DAY)>=16){
			int row=service.updateUserChecksState(u);
			if(row==0){
				map.put("success", false);
				map.put("message", "签退失败，请重试！");
			}else{
				map.put("success", true);
				map.put("message", "签退成功！");
			}
		}else{
			u.setCheckState("早退");
			int row=service.updateUserChecksState(u);
			if(row==0){
				map.put("success", false);
				map.put("message", "签退失败，请重试！");
			}else{
				map.put("success", true);
				map.put("message", "签退成功！");
			}
		}
		return map;
	}
}
