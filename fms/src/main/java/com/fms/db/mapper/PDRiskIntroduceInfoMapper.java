package com.fms.db.mapper;

import com.fms.db.model.PDRiskIntroduceInfo;
import com.fms.db.model.PDRiskIntroduceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDRiskIntroduceInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int countByExample(PDRiskIntroduceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByExample(PDRiskIntroduceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByPrimaryKey(Long pdRiskIntroduceInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insert(PDRiskIntroduceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insertSelective(PDRiskIntroduceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    List<PDRiskIntroduceInfo> selectByExample(PDRiskIntroduceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    PDRiskIntroduceInfo selectByPrimaryKey(Long pdRiskIntroduceInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExampleSelective(@Param("record") PDRiskIntroduceInfo record, @Param("example") PDRiskIntroduceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExample(@Param("record") PDRiskIntroduceInfo record, @Param("example") PDRiskIntroduceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKeySelective(PDRiskIntroduceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_introduce_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKey(PDRiskIntroduceInfo record);
}