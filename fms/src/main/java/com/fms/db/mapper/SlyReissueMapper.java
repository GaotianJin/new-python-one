package com.fms.db.mapper;

import com.fms.db.model.SlyReissue;
import com.fms.db.model.SlyReissueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlyReissueMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int countByExample(SlyReissueExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int deleteByExample(SlyReissueExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int deleteByPrimaryKey(Long slyReissueId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int insert(SlyReissue record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int insertSelective(SlyReissue record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	List<SlyReissue> selectByExample(SlyReissueExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	SlyReissue selectByPrimaryKey(Long slyReissueId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByExampleSelective(@Param("record") SlyReissue record, @Param("example") SlyReissueExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByExample(@Param("record") SlyReissue record, @Param("example") SlyReissueExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByPrimaryKeySelective(SlyReissue record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_reissue
	 * @mbggenerated  Tue Jun 27 14:28:48 CST 2017
	 */
	int updateByPrimaryKey(SlyReissue record);
}