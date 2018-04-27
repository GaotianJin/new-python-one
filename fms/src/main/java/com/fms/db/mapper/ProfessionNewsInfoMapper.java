package com.fms.db.mapper;

import com.fms.db.model.ProfessionNewsInfo;
import com.fms.db.model.ProfessionNewsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfessionNewsInfoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int countByExample(ProfessionNewsInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int deleteByExample(ProfessionNewsInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int deleteByPrimaryKey(Long professionNewsInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int insert(ProfessionNewsInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int insertSelective(ProfessionNewsInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	List<ProfessionNewsInfo> selectByExampleWithBLOBs(ProfessionNewsInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	List<ProfessionNewsInfo> selectByExample(ProfessionNewsInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	ProfessionNewsInfo selectByPrimaryKey(Long professionNewsInfoId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int updateByExampleSelective(@Param("record") ProfessionNewsInfo record,
			@Param("example") ProfessionNewsInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") ProfessionNewsInfo record,
			@Param("example") ProfessionNewsInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int updateByExample(@Param("record") ProfessionNewsInfo record,
			@Param("example") ProfessionNewsInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int updateByPrimaryKeySelective(ProfessionNewsInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(ProfessionNewsInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_profession_news_info
	 * @mbggenerated  Tue Sep 20 09:42:03 CST 2016
	 */
	int updateByPrimaryKey(ProfessionNewsInfo record);
}