package com.fms.service.product;

import java.text.ParseException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface IncomeDisService {

	/**
	 * 收益分配批处理
	 * 
	 * @return
	 */
	public ResultInfo incomeDisBatch();

	/**
	 * 产品到期清算批处理
	 * 
	 * @return
	 */
	public ResultInfo incomeDisEmailBatch();
	/**
	 * 获取产品收益分配信息
	 * 
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryProductIncomeDistibuteList(DataGridModel dgm,
			Map paramMap, LoginInfo loginInfo);

	/**
	 * 获取产品所有交易的收益分配信息
	 * 
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryProductIncomeDistibuteDetailList(DataGridModel dgm,
			Map paramMap, LoginInfo loginInfo);
	
	
	/**
	 * 下载产品所有交易得收益分配的信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getProductIncomeDistributeDetailInfo(Map paramMap);
	
	/**
	 * 导入收益分配文件（Excel）
	 * @return
	 */
	public ResultInfo importIncomeDisFile(MultipartFile[] importFileNameList,LoginInfo loginInfo);
	/**
	 * 发送净值维护邮件批处理
	 * 
	 * @return
	 */
	public ResultInfo netValueNoticeBatch();

	/**
	 * 手动生成收益分配短信
	 * @return
	 * @throws ParseException 
	 */
	public ResultInfo incomeDisSmsBatch() throws Exception;
	
}
