package com.fms.db.mapper;

import com.fms.db.model.PDWealthSalesInfo;
import com.fms.db.model.PDWealthSalesInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDWealthSalesInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int countByExample(PDWealthSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByExample(PDWealthSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByPrimaryKey(Long pdWealthSalesInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insert(PDWealthSalesInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insertSelective(PDWealthSalesInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    List<PDWealthSalesInfo> selectByExample(PDWealthSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    PDWealthSalesInfo selectByPrimaryKey(Long pdWealthSalesInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExampleSelective(@Param("record") PDWealthSalesInfo record, @Param("example") PDWealthSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExample(@Param("record") PDWealthSalesInfo record, @Param("example") PDWealthSalesInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKeySelective(PDWealthSalesInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_sales_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKey(PDWealthSalesInfo record);
}