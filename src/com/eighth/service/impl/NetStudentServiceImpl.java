package com.eighth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eighth.dao.INetStudentDao;
import com.eighth.entity.Students;
import com.eighth.service.NetStudentService;
@Service
public class NetStudentServiceImpl implements NetStudentService {
	@Autowired
	private INetStudentDao sdao;

	public Map getDatagrid(Students stu) {
		
		Map<String,Object> map=new HashMap<String, Object>();
		int page=stu.getPage();
		int pageSize=stu.getRows();
		int begin=(page-1)*pageSize;
		stu.setPage(begin);
		map.put("total", sdao.getCount(stu));
		map.put("rows",sdao.selectlistPage(stu));
		return map;
	}

	public int insertStudent(Students s) {
		return sdao.insertStudent(s);
	}

	@Override
	public int updateStudent(Students s) {
		return sdao.updateStudent(s);
	}

	@Override
	public int deleteStudent(String id) {
		return sdao.deleteStudent(id);
	}

	@Override
	public int updateStuAskers(Students s) {
		return sdao.updateStuAskers(s);
	}

	@Override
	public int getCountAskerID(String id) {
		return sdao.getCountAskerID(id);
	}

	@Override
	public Students getStudentById(String id) {
		return sdao.getStudentById(id);
	}

	@Override
	public List<Students> queryStuById(String id) {
		String[] idsarr=id.split(",");
		List<Students> list=new ArrayList<Students>();
		for (String uid : idsarr) {
			System.out.println(uid);
			list.add(sdao.getStudentById(uid));
		}
		return list;
	}

	@Override
	public Map getDatagrid1(Students stu) {
		Map<String,Object> map=new HashMap<String, Object>();
		int page=stu.getPage();
		int pageSize=stu.getRows();
		int begin=(page-1)*pageSize;
		stu.setPage(begin);
		map.put("total", sdao.getCount1(stu));
		map.put("rows",sdao.selectlistPage1(stu));
		return map;
	}
}
