package com.fms.db.mapper;

import com.fms.db.model.BasicLaw;
import com.fms.db.model.BasicLawExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicLawMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(BasicLawExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(BasicLawExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByPrimaryKey(Long basicLawId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(BasicLaw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(BasicLaw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<BasicLaw> selectByExample(BasicLawExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    BasicLaw selectByPrimaryKey(Long basicLawId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") BasicLaw record, @Param("example") BasicLawExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") BasicLaw record, @Param("example") BasicLawExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKeySelective(BasicLaw record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_basic_law
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKey(BasicLaw record);
}