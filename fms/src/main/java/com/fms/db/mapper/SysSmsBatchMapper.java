package com.fms.db.mapper;

import com.fms.db.model.SysSmsBatch;
import com.fms.db.model.SysSmsBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSmsBatchMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int countByExample(SysSmsBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByExample(SysSmsBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByPrimaryKey(Long sysSmsBatchId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insert(SysSmsBatch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insertSelective(SysSmsBatch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    List<SysSmsBatch> selectByExample(SysSmsBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    SysSmsBatch selectByPrimaryKey(Long sysSmsBatchId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysSmsBatch record, @Param("example") SysSmsBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExample(@Param("record") SysSmsBatch record, @Param("example") SysSmsBatchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKeySelective(SysSmsBatch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_sms_batch
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKey(SysSmsBatch record);
}