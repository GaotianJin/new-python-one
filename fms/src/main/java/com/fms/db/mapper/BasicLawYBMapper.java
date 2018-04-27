package com.fms.db.mapper;

import com.fms.db.model.BasicLawYB;
import com.fms.db.model.BasicLawYBExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicLawYBMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(BasicLawYBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(BasicLawYBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByPrimaryKey(Long basicLawYbBonusRateId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(BasicLawYB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(BasicLawYB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<BasicLawYB> selectByExample(BasicLawYBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    BasicLawYB selectByPrimaryKey(Long basicLawYbBonusRateId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") BasicLawYB record, @Param("example") BasicLawYBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") BasicLawYB record, @Param("example") BasicLawYBExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKeySelective(BasicLawYB record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law_yb
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKey(BasicLawYB record);
}