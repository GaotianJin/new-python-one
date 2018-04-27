package com.fms.db.mapper;

import com.fms.db.model.TradeCustAccRela;
import com.fms.db.model.TradeCustAccRelaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeCustAccRelaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int countByExample(TradeCustAccRelaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByExample(TradeCustAccRelaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int deleteByPrimaryKey(Long tradeCustAccRelaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insert(TradeCustAccRela record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int insertSelective(TradeCustAccRela record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    List<TradeCustAccRela> selectByExample(TradeCustAccRelaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    TradeCustAccRela selectByPrimaryKey(Long tradeCustAccRelaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExampleSelective(@Param("record") TradeCustAccRela record, @Param("example") TradeCustAccRelaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByExample(@Param("record") TradeCustAccRela record, @Param("example") TradeCustAccRelaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKeySelective(TradeCustAccRela record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_acc_rela
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    int updateByPrimaryKey(TradeCustAccRela record);
}