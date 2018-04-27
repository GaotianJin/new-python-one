package com.fms.service.index;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fms.db.model.CompanyRosterInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface IndexService {

	/**
	 * 公司政策列表查询
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryComPolicyList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);
	/**
	 * 行业新闻列表查询
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryProfessionNewsList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);
	/**
	 * 上传新文件
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo addUploadFileInfo(Map tMap, LoginInfo loginInfo);
	/**
	 * 上传行业新闻文件
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo professionNewsUploadFileInfo(Map tMap, LoginInfo loginInfo);
	/**
	 * 获取公司政策信息
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getCompanyPolicyContext(Map tMap);
	/**
	 * 获取行业新闻信息
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getProfessionNewsContext(Map tMap);
	/**
	 * 新增通讯录信息操作
	 * @param comRoster
	 * @param operate
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo addCompanyRoster(CompanyRosterInfo comRoster,String operate,LoginInfo loginInfo);
	
	/**
	 * 获取通讯录信息
	 * @param companyRosterInfoId
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo getCompanyRosterInfo(String companyRosterInfoId,LoginInfo loginInfo);
	
	/**
	 * 通讯录查询列表
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryListComRoster(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	
	/**
	 * 删除通讯录信息
	 * @param id
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo deleteCompanyRoster(Long id,LoginInfo loginInfo);
	
	/**
	 * 批量导入通讯录
	 * @param importFileNameList
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo importRosterDisFile(MultipartFile[] importFileNameList,LoginInfo loginInfo);
	/**
	 * 获取公司要闻列表
	 */
	public DataGrid queryCompanyNewsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);
	/**
	 * 上传公司要闻文件
	 */
	public ResultInfo addComNewsInfo(Map tMap, LoginInfo loginInfo);
	/**
	 * 获取公司要闻文件详情
	 */
	public ResultInfo getCompanyNewsContext(Map tMap);
	/**
	 * 删除公司要闻文件
	 * @param id
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo deleteCompanyNews(Long id, LoginInfo loginInfo);
	/**
	 * 更新公司要闻
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updateComNewsInfo(Map tMap, LoginInfo loginInfo);
	/**
	 * 删除公司政策
	 * @param long1
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo deleteCompanyPolicy(Long id, LoginInfo loginInfo);
	/**
	 * 删除行业新闻
	 * @param long1
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo deleteProNews(Long id, LoginInfo loginInfo);
	
	/**
	 * 更新公司政策
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updateComPolicyInfo(Map tMap, LoginInfo loginInfo);
	/**
	 * 更新行业新闻
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updateProNewsInfo(Map tMap, LoginInfo loginInfo);
	
	/**
	 * 查询自我介绍信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo querySelfIntroductionText(Map tMap, LoginInfo loginInfo);
	/**
	 * 更新自我介绍信息
	 * @param tMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo updateSelfIntroductionText(Map tMap, LoginInfo loginInfo);
}
