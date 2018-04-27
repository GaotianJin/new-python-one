package com.fms.db.mapper;

import com.fms.db.model.PDRiskSalesInfo;
import com.fms.db.model.PDRiskSalesInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDRiskSalesInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int countByExample(PDRiskSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByExample(PDRiskSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByPrimaryKey(Long pdRiskSalesInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insert(PDRiskSalesInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insertSelective(PDRiskSalesInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    List<PDRiskSalesInfo> selectByExample(PDRiskSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    PDRiskSalesInfo selectByPrimaryKey(Long pdRiskSalesInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExampleSelective(@Param("record") PDRiskSalesInfo record, @Param("example") PDRiskSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExample(@Param("record") PDRiskSalesInfo record, @Param("example") PDRiskSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKeySelective(PDRiskSalesInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_risk_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKey(PDRiskSalesInfo record);
}