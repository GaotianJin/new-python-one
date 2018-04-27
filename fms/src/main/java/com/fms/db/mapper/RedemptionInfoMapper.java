package com.fms.db.mapper;

import com.fms.db.model.RedemptionInfo;
import com.fms.db.model.RedemptionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RedemptionInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int countByExample(RedemptionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByExample(RedemptionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByPrimaryKey(Long redemptionInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insert(RedemptionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insertSelective(RedemptionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    List<RedemptionInfo> selectByExample(RedemptionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    RedemptionInfo selectByPrimaryKey(Long redemptionInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExampleSelective(@Param("record") RedemptionInfo record, @Param("example") RedemptionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExample(@Param("record") RedemptionInfo record, @Param("example") RedemptionInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKeySelective(RedemptionInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_redemption_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKey(RedemptionInfo record);
}