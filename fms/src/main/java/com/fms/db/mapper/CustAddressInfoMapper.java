package com.fms.db.mapper;

import com.fms.db.model.CustAddressInfo;
import com.fms.db.model.CustAddressInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustAddressInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(CustAddressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(CustAddressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByPrimaryKey(Long custAddressInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(CustAddressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(CustAddressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<CustAddressInfo> selectByExample(CustAddressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    CustAddressInfo selectByPrimaryKey(Long custAddressInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") CustAddressInfo record, @Param("example") CustAddressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") CustAddressInfo record, @Param("example") CustAddressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKeySelective(CustAddressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cust_address_info
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKey(CustAddressInfo record);
}