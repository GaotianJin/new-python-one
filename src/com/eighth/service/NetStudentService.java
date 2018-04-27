package com.eighth.service;

import java.util.List;
import java.util.Map;

import com.eighth.entity.Students;

public interface NetStudentService {
	/**
	 * 分页
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map getDatagrid(Students stu);
	
	/**
	 * 分页
	 * 用于我的学生
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map getDatagrid1(Students stu);
	
	
	int insertStudent(Students s);
	
	/**
	 * 编辑
	 * @param s
	 * @return
	 */
	int updateStudent(Students s);
	
	/**
	 * 删除学生
	 * @param s
	 * @return
	 */
	int deleteStudent(String id);
	
	/**
	 * 批量修改咨询师
	 * @param s
	 * @return
	 */
	int updateStuAskers(Students s);
	
	/**
	 * 查询是否存在askerId
	 * 用途：取消用户角色
	 * @param id
	 * @return
	 */
	int getCountAskerID(String id);
	
	/**
	 * 根据ID查对象
	 * @param id
	 * @return
	 */
	Students getStudentById(String id);
	
	/**
	 * 查询要导出的学生信息
	 * @param id
	 * @return
	 */
	List<Students> queryStuById(String id);
}
