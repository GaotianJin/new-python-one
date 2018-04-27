package com.fms.db.mapper;

import com.fms.db.model.DefBuilding;
import com.fms.db.model.DefBuildingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DefBuildingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int countByExample(DefBuildingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByExample(DefBuildingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int deleteByPrimaryKey(Long buildingId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insert(DefBuilding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int insertSelective(DefBuilding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    List<DefBuilding> selectByExample(DefBuildingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    DefBuilding selectByPrimaryKey(Long buildingId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") DefBuilding record, @Param("example") DefBuildingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByExample(@Param("record") DefBuilding record, @Param("example") DefBuildingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKeySelective(DefBuilding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_def_building
     *
     * @mbggenerated Mon Nov 16 15:12:22 CST 2015
     */
    int updateByPrimaryKey(DefBuilding record);
}