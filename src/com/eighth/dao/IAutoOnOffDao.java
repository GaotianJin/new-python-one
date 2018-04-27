package com.eighth.dao;


public interface IAutoOnOffDao {
	/**
	 * 查询开关
	 * 0关1开
	 * @return
	 */
	int selectIsOpen();
	
	/**
	 * 修改开关
	 * 0关1开
	 * @param isOpen
	 * @return
	 */
	int updateIsOpen(Integer isOpen);
}
