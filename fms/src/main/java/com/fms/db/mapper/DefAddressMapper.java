package com.fms.db.mapper;

import com.fms.db.model.DefAddress;
import com.fms.db.model.DefAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DefAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_address
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(DefAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_address
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(DefAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_address
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(DefAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_address
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(DefAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_address
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<DefAddress> selectByExample(DefAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_address
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") DefAddress record, @Param("example") DefAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_address
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") DefAddress record, @Param("example") DefAddressExample example);
}