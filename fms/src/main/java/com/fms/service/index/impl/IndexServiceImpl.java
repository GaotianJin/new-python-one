package com.fms.service.index.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fms.controller.index.IndexController;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.CompanyNewsInfoMapper;
import com.fms.db.mapper.CompanyPolicyInfoMapper;
import com.fms.db.mapper.CompanyRosterInfoMapper;
import com.fms.db.mapper.CompanyRosterInfoMapper;
import com.fms.db.mapper.ProfessionNewsInfoMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.CompanyNewsInfo;
import com.fms.db.model.CompanyPolicyInfo;
import com.fms.db.model.CompanyRosterInfo;
import com.fms.db.model.ProfessionNewsInfo;
import com.fms.service.index.IndexService;
import com.fms.service.mapper.IndexServiceMapper;
import com.ibm.wsdl.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.spring.quartz.Constant;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IndexServiceImpl implements IndexService {

	private static final Logger logger = Logger.getLogger(IndexController.class);
	@Autowired
	private IndexServiceMapper indexServiceMapper;
	@Autowired
	private CompanyPolicyInfoMapper companyPolicyInfoMapper;
	@Autowired
	private CompanyRosterInfoMapper comRosterMapper;
	@Autowired
	private ProfessionNewsInfoMapper professionNewsInfoMapper;
	@Autowired
	private CompanyNewsInfoMapper companyNewsInfoMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 公司政策列表查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryComPolicyList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		Integer total = indexServiceMapper.queryComPolicyListCount(paramMap);
		List<Map<String, String>> resultList = indexServiceMapper.queryComPolicyList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 行业新闻列表查询
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryProfessionNewsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		Integer total = indexServiceMapper.queryProfessionNewsListCount(paramMap);
		List<Map<String, String>> resultList = indexServiceMapper.queryProfessionNewsList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	/**
	 * 通讯录查询列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DataGrid queryListComRoster(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		Integer total = indexServiceMapper.queryListComRosterCount(paramMap);
		List<Map<String, String>> resultList = indexServiceMapper.queryListComRoster(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 保存公司政策文本
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo addUploadFileInfo(Map tMap, LoginInfo loginInfo) {

		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag = null;
		if (tMap.get("editorInfoSucFlag") != null) {
			editorInfoSucFlag = tMap.get("editorInfoSucFlag").toString();
		}
		String htmlContext = null;
		if (!editorInfoSucFlag.equals("sucFlag")) {
			/*** 新增附文本编辑框信息 ****/
			CompanyPolicyInfo companyPolicyInfo = new CompanyPolicyInfo();
			String title = tMap.get("title").toString();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}
			Long companyPolicyInfoId = commonService.getSeqValByName("SEQ_T_COMPANY_POLICY_INFO_ID");
			companyPolicyInfo.setCompanyPolicyInfoId(companyPolicyInfoId);
			companyPolicyInfo.setTitle(title);
			companyPolicyInfo.setContent(htmlContext);
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(companyPolicyInfo, loginInfo);
			companyPolicyInfoMapper.insert(companyPolicyInfo);
			resultInfo.setSuccess(true);
			resultInfo.setObj("sucFlag");
			resultInfo.setMsg("上传成功");
			return resultInfo;
		} else {
			resultInfo.setSuccess(true);
			resultInfo.setObj("sucFlag");
			resultInfo.setMsg("上传成功");
			return resultInfo;
		}
	}
	
	/**
	 * 保存行业新闻附文本问题
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo professionNewsUploadFileInfo(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag = null;
		if (tMap.get("editorInfoSucFlag") != null) {
			editorInfoSucFlag = tMap.get("editorInfoSucFlag").toString();
		}
		String htmlContext = null;
		if(!editorInfoSucFlag.equals("sucFlag")){
			/*** 新增附文本编辑框信息 ****/
			ProfessionNewsInfo professionNewsInfo = new ProfessionNewsInfo();
			String title = tMap.get("title").toString();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}
			Long professionNewsInfoId = commonService.getSeqValByName("SEQ_T_PROFESSION_NEWS_INFO_ID");
			professionNewsInfo.setProfessionNewsInfoId(professionNewsInfoId);
			professionNewsInfo.setTitle(title);
			professionNewsInfo.setContent(htmlContext);
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(professionNewsInfo, loginInfo);
			professionNewsInfoMapper.insert(professionNewsInfo);
			resultInfo.setSuccess(true);
			resultInfo.setObj("sucFlag");
			resultInfo.setMsg("上传成功");
			return resultInfo;
		}else{
			resultInfo.setSuccess(true);
			resultInfo.setObj("sucFlag");
			resultInfo.setMsg("上传成功");
			return resultInfo;
		}
	}

	/**
	 * 获取公司政策附文本编辑信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getCompanyPolicyContext(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		if (paramMap!=null) {
			List<Map> resultList= indexServiceMapper.getCompanyPolicyContext(paramMap);	
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultList.get(0));
		}
		return resultInfo;
	}

	/**
	 * 获取行业新闻信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getProfessionNewsContext(Map tMap) {
		ResultInfo resultInfo = new ResultInfo();
		if(tMap!=null){
			List<Map> resultList= indexServiceMapper.getProfessionNewsContext(tMap);
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultList.get(0));
		}
		return resultInfo;
	}
	
    /**
     * 新增通讯录信息操作
     */
	@Override
	public ResultInfo addCompanyRoster(CompanyRosterInfo comRoster, String operate, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Long companyRosterInfoId = null;
		try{
			if(comRoster!=null){
				companyRosterInfoId=comRoster.getCompanyRosterInfoId();	
				if(companyRosterInfoId==null){
					 BeanUtils.insertObjectSetOperateInfo(comRoster, loginInfo);
					 comRosterMapper.insert(comRoster);
				}else{
					CompanyRosterInfo existComRoster = comRosterMapper.selectByPrimaryKey(companyRosterInfoId);
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existComRoster,comRoster, loginInfo);
					comRosterMapper.updateByPrimaryKey(comRoster);
				}
			}else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("通讯录信息保存失败！");
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("通讯录信息保存成功！");
			resultInfo.setObj(comRoster);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 获取通讯录信息
	 */
	@Override
	public ResultInfo getCompanyRosterInfo(String companyRosterInfoId, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> comRosterMap = new HashMap<String, Object>();
		try {
			// 获取需要修改的产品主表信息T_PD_PRODUCT
			CompanyRosterInfo comRoster = (CompanyRosterInfo) comRosterMapper.selectByPrimaryKey(new Long(companyRosterInfoId));
			comRosterMap.put("companyRosterInfo", JsonUtils.objectToMap(comRoster));
			resultInfo.setObj(comRosterMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取通讯录信息成功！");
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取通讯录信息失败！");
		}
		return resultInfo;
	}

	/**
	 * 删除通讯录信息
	 */
	@Override
	public ResultInfo deleteCompanyRoster(Long id, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		CompanyRosterInfo comRoster = comRosterMapper.selectByPrimaryKey(id);
		BeanUtils.deleteObjectSetOperateInfo(comRoster, loginInfo);
		comRosterMapper.updateByPrimaryKey(comRoster);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}
	
	/**
	 * 批量导入通讯录
	 */
	@Override
	@Transactional
	public ResultInfo importRosterDisFile(MultipartFile[] importFileNameList,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		List<String> unUpLoadFile = new ArrayList<String>();
		try {
			if (importFileNameList==null||importFileNameList.length==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请选择需要上传的文件！");
				return resultInfo;
			}

			for(MultipartFile multipartFile:importFileNameList){
				// 获得文件名(全路径)
				String uploadFileName = multipartFile.getOriginalFilename();
				//获取文件后缀
				String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
				if(suffix==null||"".equals(suffix)){
					unUpLoadFile.add(uploadFileName+" : 文件类型未知");
					continue;
				}
				logger.info("====file suffix==="+suffix);
				//校验文件
				if(!".xls".equals(suffix)){
					unUpLoadFile.add(uploadFileName+" : 请将文件保存成2003版本");
					continue;
				}
				//得到文件的输入流，转换为WorkBook
				Workbook workbook = Workbook.getWorkbook(multipartFile.getInputStream());
				resultInfo = resolveRosterDisExcel(workbook, loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("通讯录文件导入出现异常！");
		}
		resultInfo.setSuccess(true);
		if(unUpLoadFile.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg(JsonUtils.objectToJsonStr(unUpLoadFile));
		}else{
			resultInfo.setMsg("通讯录文件导入成功！");
		}
		return resultInfo;
	}
	
	/**
	 * 通讯录批量导入模板
	 * @param workbook
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo resolveRosterDisExcel(Workbook workbook,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//只有一个工作表格，取第一个Sheet
			Sheet sheet = workbook.getSheet(0);
			//得到单元格，第一行为列名称，获取每列名称,放入Map中
			Map<String, String> colNameMap = new HashMap<String, String>();
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,i);
					String colName = cell.getContents();
					if (colName!=null&&!"".equals(colName)) {
						colNameMap.put(j+"", colName);
					}
				}
			}
			//总列数
			int colSize = colNameMap.size();
			//获取所有数据，从第二行开始，第一行为表头
            for (int i = 1; i < sheet.getRows(); i++) {
            	//通讯录流水号
            	String companyRosterInfoId = "";
            	//姓名
            	String chnName = "";
            	//固定电话
            	String mobile = "";
            	//手机
            	String telephone = "";
            	//E-mail
            	String email = "";
            	//传真
            	String fax = "";
            	String filiale = "";
            	String department = "";
            	String post = "";
                for (int j = 0; j < colSize; j++) {
                	String colName = colNameMap.get(j+"");
                	Cell cell = sheet.getCell(j,i);
                	//获取通讯录流水号
                	if (colName.indexOf("companyRosterInfoId")>-1) {
                		companyRosterInfoId = cell.getContents();
					}
                	//获取姓名
                	if (colName.indexOf("chnName")>-1) {
                		chnName = cell.getContents();
					}
                	//获取固定电话
                	if (colName.indexOf("mobile")>-1) {
                		mobile = cell.getContents();
					}
                	//获取手机
                	if (colName.indexOf("telephone")>-1) {
                		telephone = cell.getContents();
					}
                	//获取email
                	if (colName.indexOf("email")>-1) {
                		email = cell.getContents();
					}
                	//获取传真
                	if (colName.indexOf("fax")>-1) {
                		fax = cell.getContents();
					}
                	if (colName.indexOf("filiale")>-1) {
                		filiale = cell.getContents();
					}
                	if (colName.indexOf("department")>-1) {
                		department = cell.getContents();
					}
                	if (colName.indexOf("post")>-1) {
                		post = cell.getContents();
					}
                }
                //获取通讯录信息
                CompanyRosterInfo companyRosterInfo = new CompanyRosterInfo();
                if (companyRosterInfoId==null&&"".equals(companyRosterInfoId)) {
                	companyRosterInfoId = commonService.getSeqValByName("SEQ_T_COMPANY_ROSTER_INFO").toString();
				}
                //姓名
                if (chnName!=null&&!"".equals(chnName)) {
                	companyRosterInfo.setChnName(chnName);
				}
                //固话
                if (mobile!=null&&!"".equals(mobile)) {
                	companyRosterInfo.setMobile(mobile);
				}
                //手机
                if (telephone!=null&&!"".equals(telephone)) {
                	companyRosterInfo.setTelephone(telephone);
				}
                //email
                if (email!=null&&!"".equals(email)) {
                	companyRosterInfo.setEmail(email);
				}
                //传真
                if (fax!=null&&!"".equals(fax)) {
                	companyRosterInfo.setFax(fax);
				}
                if (filiale!=null&&!"".equals(filiale)) {
                	companyRosterInfo.setFiliale(new Long(filiale));
				}
                if (department!=null&&!"".equals(department)) {
                	companyRosterInfo.setDepartment(new Long(department));
				}
                if (post!=null&&!"".equals(post)) {
                	companyRosterInfo.setPost(post);
                }
                //设置操作信息
                BeanUtils.insertObjectSetOperateInfo(companyRosterInfo, loginInfo);
                //更新上传的数据
                comRosterMapper.insert(companyRosterInfo);
            }
            workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("解析通讯录文件出现异常！");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("通讯录文件导入成功！");
		return resultInfo;
	}
	/**
	 * 获取公司要闻列表信息
	 */
	@Override
	public DataGrid queryCompanyNewsList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());

		Integer total = indexServiceMapper.queryCompanyNewsListCount(paramMap);
		List<Map<String, String>> resultList = indexServiceMapper.queryCompanyNewsList(paramMap);
		// datagrid 数据信息
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	/**
	 * 保存公司要闻附文本问题
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo addComNewsInfo(Map tMap, LoginInfo loginInfo) {

		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag = null;
		if (tMap.get("editorInfoSucFlag") != null) {
			editorInfoSucFlag = tMap.get("editorInfoSucFlag").toString();
		}
		String htmlContext = null;
		if (!editorInfoSucFlag.equals("sucFlag")) {
			/*** 新增附文本编辑框信息 ****/
			CompanyNewsInfo companyNewsInfo = new CompanyNewsInfo();
			String title = tMap.get("title").toString();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}
				Long companyNewsInfoId = commonService.getSeqValByName("SEQ_T_COMPANY_NEWS_INFO_ID");
				companyNewsInfo.setCompanyNewsInfoId(companyNewsInfoId);
				companyNewsInfo.setTitle(title);
				companyNewsInfo.setContent(htmlContext);
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(companyNewsInfo, loginInfo);
				companyNewsInfoMapper.insert(companyNewsInfo);
				resultInfo.setSuccess(true);
				resultInfo.setObj("sucFlag");
				resultInfo.setMsg("上传成功");
				return resultInfo;
		} else {
			resultInfo.setSuccess(true);
			resultInfo.setObj("sucFlag");
			resultInfo.setMsg("上传成功");
			return resultInfo;
		}
	}
	/**
	 * 获取公司要闻附文本编辑信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getCompanyNewsContext(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		if (paramMap!=null) {
			List<Map> resultList= indexServiceMapper.getCompanyNewsContext(paramMap);	
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultList.get(0));
		}
		return resultInfo;
	}

	@Override
	public ResultInfo deleteCompanyNews(Long id, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		CompanyNewsInfo comNews = companyNewsInfoMapper.selectByPrimaryKey(id);
		BeanUtils.deleteObjectSetOperateInfo(comNews, loginInfo);
		companyNewsInfoMapper.updateByPrimaryKey(comNews);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}

	/**
	 * 更新公司要闻
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public ResultInfo updateComNewsInfo(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag = null;
		if (tMap.get("editorInfoSucFlag") != null) {
			editorInfoSucFlag = tMap.get("editorInfoSucFlag").toString();
		}
		String htmlContext = null;
			/*** 新增附文本编辑框信息 ****/
			CompanyNewsInfo companyNewsInfo = new CompanyNewsInfo();
			String title = tMap.get("title").toString();
			String companyNewsInfoId = tMap.get("companyNewsInfoId").toString();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}			
			//删除
			    CompanyNewsInfo existCompanyNewsInfo = companyNewsInfoMapper.selectByPrimaryKey(new Long(companyNewsInfoId));
			    BeanUtils.deleteObjectSetOperateInfo(existCompanyNewsInfo, loginInfo);
			    companyNewsInfoMapper.updateByPrimaryKey(existCompanyNewsInfo);
			    //删除后新增一条记录
			    Long companyNewsInfoSeq = commonService.getSeqValByName("SEQ_T_COMPANY_NEWS_INFO_SEQ");
			    companyNewsInfo.setCompanyNewsInfoId(companyNewsInfoSeq);
				companyNewsInfo.setTitle(title);
				companyNewsInfo.setContent(htmlContext);
				BeanUtils.insertObjectSetOperateInfo(companyNewsInfo, loginInfo);
				companyNewsInfoMapper.insert(companyNewsInfo);
				resultInfo.setSuccess(true);
				resultInfo.setObj("sucFlag");
				resultInfo.setMsg("更新成功");
				return resultInfo;
	}

	@Override
	public ResultInfo deleteCompanyPolicy(Long id, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		CompanyPolicyInfo comPolicy = companyPolicyInfoMapper.selectByPrimaryKey(id);
		BeanUtils.deleteObjectSetOperateInfo(comPolicy, loginInfo);
		companyPolicyInfoMapper.updateByPrimaryKey(comPolicy);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}

	@Override
	public ResultInfo deleteProNews(Long id, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		ProfessionNewsInfo proNews = professionNewsInfoMapper.selectByPrimaryKey(id);
		BeanUtils.deleteObjectSetOperateInfo(proNews, loginInfo);
		professionNewsInfoMapper.updateByPrimaryKey(proNews);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功！");
		return resultInfo;
	}

	@Override
	public ResultInfo updateComPolicyInfo(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag = null;
		if (tMap.get("editorInfoSucFlag") != null) {
			editorInfoSucFlag = tMap.get("editorInfoSucFlag").toString();
		}
		String htmlContext = null;
			/*** 新增附文本编辑框信息 ****/
			CompanyPolicyInfo companyPolicyInfo = new CompanyPolicyInfo();
			String title = tMap.get("title").toString();
			String companyPolicyInfoId = tMap.get("companyPolicyInfoId").toString();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}			
			//删除
			CompanyPolicyInfo existCompanyPolicyInfo = companyPolicyInfoMapper.selectByPrimaryKey(new Long(companyPolicyInfoId));
			    BeanUtils.deleteObjectSetOperateInfo(existCompanyPolicyInfo, loginInfo);
			    companyPolicyInfoMapper.updateByPrimaryKey(existCompanyPolicyInfo);
			    //删除后新增一条记录
			    Long companyPolicyInfoSeq = commonService.getSeqValByName("SEQ_T_COMPANY_POLICY_INFO_SEQ");
			    companyPolicyInfo.setCompanyPolicyInfoId(companyPolicyInfoSeq);
			    companyPolicyInfo.setTitle(title);
			    companyPolicyInfo.setContent(htmlContext);
				BeanUtils.insertObjectSetOperateInfo(companyPolicyInfo, loginInfo);
				companyPolicyInfoMapper.insert(companyPolicyInfo);
				resultInfo.setSuccess(true);
				resultInfo.setObj("sucFlag");
				resultInfo.setMsg("更新成功");
				return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public ResultInfo updateProNewsInfo(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag = null;
		if (tMap.get("editorInfoSucFlag") != null) {
			editorInfoSucFlag = tMap.get("editorInfoSucFlag").toString();
		}
		String htmlContext = null;
			/*** 新增附文本编辑框信息 ****/
			ProfessionNewsInfo professionNewsInfo = new ProfessionNewsInfo();
			String title = tMap.get("title").toString();
			String professionNewsInfoId = tMap.get("professionNewsInfoId").toString();
			if (tMap.get("htmlContext") != null) {
				htmlContext = tMap.get("htmlContext").toString().trim();
			}			
			//删除
			ProfessionNewsInfo existProfessionNewsInfo=professionNewsInfoMapper.selectByPrimaryKey(new Long(professionNewsInfoId));
			    BeanUtils.deleteObjectSetOperateInfo(existProfessionNewsInfo, loginInfo);
			    professionNewsInfoMapper.updateByPrimaryKey(existProfessionNewsInfo);
			    //删除后新增一条记录
			    Long professionNewsSeq = commonService.getSeqValByName("SEQ_T_PROFESSION_NEWS_INFO_SEQ");
			    professionNewsInfo.setProfessionNewsInfoId(professionNewsSeq);
			    professionNewsInfo.setTitle(title);
			    professionNewsInfo.setContent(htmlContext);
				BeanUtils.insertObjectSetOperateInfo(professionNewsInfo, loginInfo);
				professionNewsInfoMapper.insert(professionNewsInfo);
				resultInfo.setSuccess(true);
				resultInfo.setObj("sucFlag");
				resultInfo.setMsg("更新成功");
				return resultInfo;
	}

	@Override
	public ResultInfo querySelfIntroductionText(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo(); 
		String SelfIntroduction = indexServiceMapper.querySelfIntroductionText(tMap);
		resultInfo.setSuccess(true);
		resultInfo.setObj(SelfIntroduction);
		resultInfo.setMsg("查询成功");
		return resultInfo;
	}

	@Override
	public ResultInfo updateSelfIntroductionText(Map tMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		Long userId = Long.parseLong(tMap.get("userId").toString());
		String selfIntroduction = tMap.get("selfIntroduction").toString();
		AgentExample agentExample = new AgentExample();
		agentExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
		List<Agent> agentList = agentMapper.selectByExample(agentExample);
		Agent agent = agentList.get(0);
		agent.setSelfIntroduction(selfIntroduction);
		agentMapper.updateByPrimaryKey(agent);
		resultInfo.setSuccess(true);
		resultInfo.setObj("sucFlag");
		resultInfo.setMsg("更新成功");
		return resultInfo;
	}




}
