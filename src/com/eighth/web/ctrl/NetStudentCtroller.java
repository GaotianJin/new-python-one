package com.eighth.web.ctrl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.Askers;
import com.eighth.entity.Students;
import com.eighth.entity.Users;
import com.eighth.service.AutoOnOffService;
import com.eighth.service.NetFollowService;
import com.eighth.service.NetStudentService;
import com.eighth.service.RoleStusService;
import com.eighth.service.SetWeightService;
import com.eighth.service.StuAddressService;
import com.eighth.util.DateUtil;
import com.eighth.util.ExcelCreate;
import com.eighth.util.StringUtil;

@Controller
public class NetStudentCtroller {
	@Autowired
	private NetStudentService nservice;
	@Autowired
	private SetWeightService swser;
	@Autowired
	private NetFollowService nfser;
	@Autowired
	private AutoOnOffService ofser;
	@Autowired
	private RoleStusService rsser;
	@Autowired
	private StuAddressService saser;
	/**
	 * 显示所有用户
	 * */
	@RequestMapping("/showAllStudent")
	@ResponseBody
	public Object getAllStudent(int page,int rows,String sname,String sphone,String isPay,String isValid,String isReturnVisit,String dateA,String dateB,String sqq,String sasker){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sasker);
		Students ss=null;
		try {
			ss = new Students(StringUtil.isNull(sname),StringUtil.isNull(sphone),StringUtil.isNull(sqq),StringUtil.isNull(isValid),StringUtil.isNull(isReturnVisit),StringUtil.isNull(isPay), page, rows,StringUtil.isNull(sasker), null,null, StringUtil.isEmpty(dateA)? null: sdf.parse(dateA),
					StringUtil.isEmpty(dateB) ? null: sdf.parse(dateB));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map map=nservice.getDatagrid(ss);
		return map;
	}
	
	/**
	 * 显示所有用户
	 * 用于我的学生
	 * */
	@RequestMapping("/showAllStudent1")
	@ResponseBody
	public Object getAllStudent1(int page,int rows,String sname,String sphone,String isPay,String isValid,String isReturnVisit,String dateA,String dateB,String sqq,String sasker){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sasker);
		Students ss=null;
		try {
			ss = new Students(StringUtil.isNull(sname),StringUtil.isNull(sphone),StringUtil.isNull(sqq),StringUtil.isNull(isValid),StringUtil.isNull(isReturnVisit),StringUtil.isNull(isPay), page, rows,StringUtil.isNull(sasker), null,null, StringUtil.isEmpty(dateA)? null: sdf.parse(dateA),
					StringUtil.isEmpty(dateB) ? null: sdf.parse(dateB));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map map=nservice.getDatagrid1(ss);
		return map;
	}
	
	/**
	 * 网络学生
	 * 根据录入人Id查询数据
	 */
	@RequestMapping("/showAllStudentByAskerIdInNetStu")
	@ResponseBody
	public Object getAllStudentByAskerIdInNetStu(int page,int rows,String sname,String sphone,String isPay,String isValid,String isReturnVisit,String dateA,String dateB,String sqq,String sasker,HttpSession session){
			Users u=(Users) session.getAttribute("user");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(sasker);
			Students ss=null;
			try {
				ss = new Students(StringUtil.isNull(sname),StringUtil.isNull(sphone),StringUtil.isNull(sqq),StringUtil.isNull(isValid),StringUtil.isNull(isReturnVisit),StringUtil.isNull(isPay), page, rows,StringUtil.isNull(sasker), StringUtil.isNull(u.getUserId()),null, StringUtil.isEmpty(dateA)? null: sdf.parse(dateA),
						StringUtil.isEmpty(dateB) ? null: sdf.parse(dateB));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Map map=nservice.getDatagrid(ss);
			return map;
		}
	/**
	 * 我的学生
	 * 根据录入人Id查询数据
	 * @return
	 */
	@RequestMapping("/showAllStudentByAskerIdInMyStu")
	@ResponseBody
	public Object getAllStudentByAskerIdInMyStu(int page,int rows,String sname,String sphone,String isPay,String isValid,String isReturnVisit,String dateA,String dateB,String sqq,String sasker,HttpSession session){
		Users u=(Users) session.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sasker);
		Students ss=null;
		try {
			ss = new Students(StringUtil.isNull(sname),StringUtil.isNull(sphone),StringUtil.isNull(sqq),StringUtil.isNull(isValid),StringUtil.isNull(isReturnVisit),StringUtil.isNull(isPay), page, rows,StringUtil.isNull(sasker),null, StringUtil.isNull(u.getUserId()), StringUtil.isEmpty(dateA)? null: sdf.parse(dateA),
					StringUtil.isEmpty(dateB) ? null: sdf.parse(dateB));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map map=nservice.getDatagrid(ss);
		return map;
	}
	/**
	 * 添加学生
	 * @param s
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addStudent")
	@ResponseBody
	public  Object addStu(Students s,HttpSession session){
		int num=0;
		List<Askers> list=swser.getAllAsker();
		Users u=(Users) session.getAttribute("user");
		if(ofser.selectIsOpen()==1){
		List<Askers> list1=new ArrayList<Askers>();
		for (Askers askers : list) {
			for (int i = 0; i < Integer.parseInt(askers.getWeight()); i++) {
				Askers as=new Askers();
				as.setAskerId(askers.getAskerId());
				as.setAskerName(askers.getAskerName());
				list1.add(as);
			}
			num+=Integer.parseInt(askers.getWeight());
		}
		int sj=(int) (Math.random()*num);
		s.setAskerName(list1.get(sj).getAskerName());
		s.setAskerId(list1.get(sj).getAskerId());
		}
		s.setCreateUser(u.getUserName());
		s.setNetPusherId(u.getUserId());
		s.setStuId(StringUtil.getUUID());
		s.setStuSex(StringUtil.isNull(s.getStuSex()));
		s.setStuStatus(StringUtil.isNull(s.getStuStatus()));
		s.setPerState(StringUtil.isNull(s.getPerState()));
		s.setFromPart(StringUtil.isNull(s.getFromPart()));
		s.setIsBaoBei(StringUtil.isNull(s.getIsBaoBei()));
		s.setStuAddress(StringUtil.isNull(s.getStuAddress()));
		s.setStuQQ(StringUtil.isNull(s.getStuQQ()));
		s.setStuWeiXin(StringUtil.isNull(s.getStuWeiXin()));
		s.setContent(StringUtil.isNull(s.getContent()));
		s.setStuConcern(StringUtil.isNull(s.getStuConcern()));
		
		int row=nservice.insertStudent(s);
		Map map=new HashMap();
		if(row==0){
			map.put("success", false);
			map.put("message", "添加失败！");
		}else{
			map.put("success", true);
			map.put("message", "添加成功！");
		}
		return map;
		
	}
	
	/**
	 * 编辑
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/modStudent")
	@ResponseBody
	public Object updateStu(Students s){
		System.out.println(s.getFirstVisitTime());
		System.out.println(s.getContent());
		int row=nservice.updateStudent(s);
		Map map=new HashMap();
		if(row==0){
			map.put("success", false);
			map.put("message", "修改失败！");
		}else{
			map.put("success", true);
			map.put("message", "修改成功！");
		}
		return map;
	}
	
	/**
	 * 删除学生
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delStudent")
	@ResponseBody
	public Object delStudent(String id){
		nfser.deleteNetFollow(id);
		int row=nservice.deleteStudent(id);
		if(row==0){
			return false;
		}else{
			return true;
		}
	}
	
	@RequestMapping(value="/modStudentAskers")
	@ResponseBody
	public Object updateStuAksers(String ids,String askerid,String askerName){
		String[] idsarr=ids.split(",");
		int row=0;
		for (String id : idsarr) {
			Students s=new Students();
			s.setStuId(id);
			s.setAskerId(askerid);
			s.setAskerName(askerName);
			int n=nservice.updateStuAskers(s);
			row+=n;
		}
		Map map=new HashMap();
		if(row==idsarr.length){
			map.put("success", true);
			map.put("message", "修改成功！");
		}else{
			map.put("success", false);
			map.put("message", "修改失败！");
		}
		return map;
	}
	
	/**
	 * 导出excel
	 * */
	@RequestMapping(value="/outExcel")
	@ResponseBody
	public void outExcel(String stuIds,HttpServletResponse response)
			throws IOException {
			String downloadName = "StudentInfo.xls";
			response.setCharacterEncoding("UTF-8");// 设置响应的字符编码格式
			response.setContentType("application/x-download");// 指明响应为文件的类型
			response.setHeader("Content-Disposition", "attachment; filename="
					+ downloadName);// 设置响应头信息,表示响应内容为附加,即下载
			ServletOutputStream outputStream = response.getOutputStream();// 获取响应的字节输出流
			List<Students> list = nservice.queryStuById(stuIds);
			System.out.println(list);
			ExcelCreate.createExcel(list, outputStream);
	}
	/**
	 * 饼状图Json
	 */
	@RequestMapping(value="/RoleStuPic")
	@ResponseBody
	public Object RoleStuPic(){
		Map map=new HashMap();
		List list=rsser.getAll();
		map.put("list", list);
		map.put("str", rsser.getName());
		return map;
	}
	
	@RequestMapping(value="/StuAddress")
	@ResponseBody
	public Object StuAddress(){
		List list=saser.getJson();
		return list;
	}
}
