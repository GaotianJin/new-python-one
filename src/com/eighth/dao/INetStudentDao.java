package com.eighth.dao;

import java.util.List;

import com.eighth.entity.Students;

public interface INetStudentDao {
	
	/**
	 * 查询是数据总条数
	 * @return
	 */
	int getCount(Students u);
	
	/**
	 * 查询是数据总条数
	 * 用于我的学生
	 * @return
	 */
	int getCount1(Students u);
	
	/**
	 * 查询数据并分页
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<Students> selectlistPage(Students u);
	
	/**
	 * 查询数据并分页
	 * 用于我的学生
	 * @param u
	 * @return
	 */
	List<Students> selectlistPage1(Students u);
	/**
	 * 添加学生
	 * */
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
	 * 查询该咨询师管理多少学生
	 * @param id
	 * @return
	 */
	int getCountByAkserId(String id);
	
	/**
	 * 根据住址查询学生数量
	 * @param name
	 * @return
	 */
	int getCountByAddress(String name);
	
} 
