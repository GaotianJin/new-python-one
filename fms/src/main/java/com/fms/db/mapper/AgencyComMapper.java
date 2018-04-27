package com.fms.db.mapper;

import com.fms.db.model.AgencyCom;
import com.fms.db.model.AgencyComExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AgencyComMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(AgencyComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(AgencyComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByPrimaryKey(Long agencyComId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(AgencyCom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(AgencyCom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<AgencyCom> selectByExample(AgencyComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    AgencyCom selectByPrimaryKey(Long agencyComId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") AgencyCom record, @Param("example") AgencyComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") AgencyCom record, @Param("example") AgencyComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKeySelective(AgencyCom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_com
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKey(AgencyCom record);
}