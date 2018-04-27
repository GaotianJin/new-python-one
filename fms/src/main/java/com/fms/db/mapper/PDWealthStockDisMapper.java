package com.fms.db.mapper;

import com.fms.db.model.PDWealthStockDis;
import com.fms.db.model.PDWealthStockDisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDWealthStockDisMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int countByExample(PDWealthStockDisExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int deleteByExample(PDWealthStockDisExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int deleteByPrimaryKey(Long pdWealthStockDisId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int insert(PDWealthStockDis record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int insertSelective(PDWealthStockDis record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	List<PDWealthStockDis> selectByExample(PDWealthStockDisExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	PDWealthStockDis selectByPrimaryKey(Long pdWealthStockDisId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int updateByExampleSelective(@Param("record") PDWealthStockDis record,
			@Param("example") PDWealthStockDisExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int updateByExample(@Param("record") PDWealthStockDis record, @Param("example") PDWealthStockDisExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int updateByPrimaryKeySelective(PDWealthStockDis record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_pd_wealth_stock_dis
	 * @mbggenerated  Thu Jul 06 14:17:00 CST 2017
	 */
	int updateByPrimaryKey(PDWealthStockDis record);
}