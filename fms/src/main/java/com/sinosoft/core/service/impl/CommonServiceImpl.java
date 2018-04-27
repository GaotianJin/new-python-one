package com.sinosoft.core.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fms.db.mapper.DefReportColMapper;
import com.fms.db.mapper.WebServiceLogInfoMapper;
import com.fms.db.model.DefReportCol;
import com.fms.db.model.DefReportColExample;
import com.fms.db.model.WebServiceLogInfo;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.mapper.SysMaxNoMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefCodeKey;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.db.model.SysMaxNo;
import com.sinosoft.core.db.model.SysMaxNoExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.service.mapper.CommonServiceMapper;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.LoginInfo;

@SuppressWarnings("serial")
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommonServiceImpl implements CommonService,Serializable {

	@Autowired
	private CommonServiceMapper commonServiceMapper;
	@Autowired
	private SysMaxNoMapper sysMaxNoMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private DefReportColMapper defReportColMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private WebServiceLogInfoMapper webserviceLogInfoMapper;
	
	@Override
	public List<String> exeQuerySql(String sql) {
		Map<String, String> paramMap = new HashMap<String,String>();
		List<String> resultList = commonServiceMapper.exeQuerySql(paramMap);
		return resultList;
	}

	@Override
	public Long getSeqValByName(String seqName) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("seqName", seqName);
//		String value = commonServiceMapper.getSeqValByName(paramMap);
//		Long seqBigDecimal = new Long(value);
		return null;
	} 

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public synchronized String createTradeNo(String productType) {
		if (productType==null||"".equals(productType)) {
			return null;
		}
		String maxNo = "";
		//获取日期
		String currentDate = DateUtils.getCurrentDate(); 
		currentDate = currentDate.replaceAll("-", "");
		StringBuffer sb = new StringBuffer();
		sb.append(productType);
		sb.append(currentDate);
		SysMaxNoExample sysMaxNoExample = new SysMaxNoExample();
		//01表示交易号码
		sysMaxNoExample.createCriteria().andMaxNoLike(sb.toString()+"%").andMaxNoTypeEqualTo("01");
		List<SysMaxNo> maxNoList = sysMaxNoMapper.selectByExample(sysMaxNoExample);
		if (maxNoList!=null&&maxNoList.size()>0) {
			SysMaxNo sysMaxNo = maxNoList.get(0);
			maxNo = String.valueOf(Long.parseLong(sysMaxNo.getMaxNo())+1);
			sysMaxNo.setMaxNo(maxNo);
			sysMaxNoMapper.updateByPrimaryKey(sysMaxNo);
		}else {
			SysMaxNo sysMaxNo = new SysMaxNo();
			Long sysMaxNoId = getSeqValByName("SEQ_T_SYS_MAX_NO");
			maxNo = sb.append("0001").toString();
			sysMaxNo.setSysMaxNoId(sysMaxNoId);
			sysMaxNo.setMaxNo(maxNo);
			sysMaxNo.setMaxNoType("01");
			sysMaxNoMapper.insert(sysMaxNo);
		}
		return maxNo;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public synchronized String createCustomerNo() {
		String maxCustomerNo = "";
		SysMaxNoExample sysMaxNoExample = new SysMaxNoExample();
		//01表示交易号码
		sysMaxNoExample.createCriteria().andMaxNoTypeEqualTo("02");
		List<SysMaxNo> maxNoList = sysMaxNoMapper.selectByExample(sysMaxNoExample);
		if (maxNoList!=null&&maxNoList.size()>0) {
			SysMaxNo sysMaxNo = maxNoList.get(0);
			maxCustomerNo = String.valueOf(Long.parseLong(sysMaxNo.getMaxNo())+1);
			sysMaxNo.setMaxNo(maxCustomerNo);
			sysMaxNoMapper.updateByPrimaryKey(sysMaxNo);
		}else {
			SysMaxNo sysMaxNo = new SysMaxNo();
			Long sysMaxNoId = getSeqValByName("SEQ_T_SYS_MAX_NO");
			maxCustomerNo = "100001";
			sysMaxNo.setSysMaxNoId(sysMaxNoId);
			sysMaxNo.setMaxNo(maxCustomerNo);
			sysMaxNo.setMaxNoType("02");
			sysMaxNoMapper.insert(sysMaxNo);
		}
		return maxCustomerNo;
	}
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public synchronized String createPreCustomerNo() {
		String maxCustomerNo = "";
		SysMaxNoExample sysMaxNoExample = new SysMaxNoExample();
		//01表示交易号码
		sysMaxNoExample.createCriteria().andMaxNoTypeEqualTo("03");
		List<SysMaxNo> maxNoList = sysMaxNoMapper.selectByExample(sysMaxNoExample);
		if (maxNoList!=null&&maxNoList.size()>0) {
			SysMaxNo sysMaxNo = maxNoList.get(0);
			maxCustomerNo = String.valueOf(Long.parseLong(sysMaxNo.getMaxNo())+1);
			sysMaxNo.setMaxNo(maxCustomerNo);
			sysMaxNoMapper.updateByPrimaryKey(sysMaxNo);
		}else {
			SysMaxNo sysMaxNo = new SysMaxNo();
			Long sysMaxNoId = getSeqValByName("SEQ_T_SYS_MAX_NO");
			maxCustomerNo = "10000001";
			sysMaxNo.setSysMaxNoId(sysMaxNoId);
			sysMaxNo.setMaxNo(maxCustomerNo);
			sysMaxNo.setMaxNoType("03");
			sysMaxNoMapper.insert(sysMaxNo);
		}
		return maxCustomerNo;
	}
	
	/**根据报表编码，获取报表相关信息
	 * @param reportCode
	 * @return
	 */
	public Map<String, Object> getReportInfo(String reportCode){
		Map<String, Object> reportInfoMap = new HashMap<String, Object>();
		//1.获取报表名称，生成Excel时的sheet名
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("reportCode");
		defCodeKey.setCode(reportCode);
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		reportInfoMap.put("reportName", defCode.getCodeName());
		//2.获取报表的列编号，列编码，列名称
		DefReportColExample defReportColExample = new DefReportColExample();
		DefReportColExample.Criteria criteria = defReportColExample.createCriteria();
		criteria.andReportCodeEqualTo(reportCode);
		List<DefReportCol> reportColInfoList = defReportColMapper.selectByExample(defReportColExample);
		//3.生成列编号与列名称的对应关系		
		Map<String, String> colNoToColNameMap = new HashMap<String, String>();
		for (DefReportCol defReportCol:reportColInfoList) {
			colNoToColNameMap.put(defReportCol.getColumnNo(), defReportCol.getColumnName());
		}
		reportInfoMap.put("reportColNoToColNameMapping", colNoToColNameMap);
		//4.生成列编号与列编码的对应关系	
		Map<String, String> colNoToColCodeMap = new HashMap<String, String>();
		for (DefReportCol defReportCol:reportColInfoList) {
			colNoToColCodeMap.put(defReportCol.getColumnNo(), defReportCol.getColumnCode());
		}
		reportInfoMap.put("reportColNoToColCodeMapping", colNoToColCodeMap);
		return reportInfoMap;
	}
	
	
	
	/**
	 * 生成邀请码,长度可以指定
	 * @param length
	 * @return
	 */
	public String createInviteCode(int length){
		StringBuffer inviteCode = new StringBuffer();
		try {
			//邀请码由数字、大写字母、小写字母组成
			for (int i = 0; i < length; i++) {
				//利用Random产生小于3的随机随机数，除以3求余数，余数为0时，取数字，余数为1时，去大写字母，余数为2时，取小写字母
				Random random = new Random();
				int dataType = random.nextInt(3)%3;
				if(dataType==0){
					//0-9随机取一个
					int data = random.nextInt(10);
					inviteCode.append(data);
				}else if(dataType==1){
					//得到65-90的随机数
					int upCase = random.nextInt(26)+65;
					//A-Z随机取一个
					String up =String.valueOf((char)upCase);
					inviteCode.append(up);
				}else if (dataType==2) {
					//得到97-122的随机数
					int downCase = random.nextInt(26)+97;
					//a-z随机取一个
					String down =String.valueOf((char)downCase);
					inviteCode.append(down);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inviteCode.toString();
	}
	
	
	/**
	 * @return
	 */
	public String getFileSavePath(String fileType){
		String fileSavePath = "";
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("fileSavePath");
		defCodeKey.setCode(fileType);
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		if (defCode!=null) {
			fileSavePath = defCode.getCodeName();
		}
		return fileSavePath;
	}
	
	/**
	 * 创建如期文件夹
	 * @return
	 */
	public String createDateFolder(){
		String dateFolder = "";
		String dateStr = DateUtils.getCurrentDate();
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(5, 7);
		String day = dateStr.substring(8, 10);
		dateFolder = year+"/"+month+"/"+day+"/";
		return dateFolder;
	}
	
	/**
	 * @return
	 */
	public String getFileSaveServerHttpAddress(){
		String fileSavePath = "";
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("fileSaveServerParam");
		defCodeKey.setCode("01");
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		if (defCode!=null) {
			fileSavePath = defCode.getCodeName();
		}
		return fileSavePath;
	}
	
	/**
	 * @return
	 */
	public String getFileSaveServerDocumentRootPath(){
		String fileSavePath = "";
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("fileSaveServerParam");
		defCodeKey.setCode("02");
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		if (defCode!=null) {
			fileSavePath = defCode.getCodeName();
		}
		return fileSavePath;
	}
	
	/**获取文件尺寸限制
	 * @return
	 */
	public long getUploadFileSize(String fileType){
		long fileSize = 0l;
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("uploadFileSize");
		defCodeKey.setCode(fileType);
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		if (defCode!=null) {
			fileSize = Long.parseLong(defCode.getCodeName())*1024*1000;
		}
		return fileSize;
	}
	
	
	/**
	 * 生成文件名
	 * @param fileType
	 * @param name
	 * @return
	 */
	public String rename(String fileType,String name) {
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long random = (long) (Math.random() * now);
		StringBuffer fileName = new StringBuffer();
		fileName.append("F");
		fileName.append(fileType);
		fileName.append(now);
		fileName.append(random);
		if (name.indexOf(".") != -1) {
			fileName.append(name.substring(name.lastIndexOf(".")));
		}
		return fileName.toString();
	}
	
	
	/**
	 * 图片上传类型限制
	 * @param suffix
	 * @return
	 */
	public boolean verifyImageType(String suffix){
		suffix = suffix.toLowerCase();
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria criteria = defCodeExample.createCriteria();
		criteria.andCodeTypeEqualTo("uploadImageType").andCodeNameEqualTo(suffix);
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		if (defCodeList==null||defCodeList.size()==0) {
			return false;
		}
		return true;
	}

	
	@Override
	@Transactional
	public boolean saveWebserviceLog(String webserviceCode, String webserviceName, String businessNo, String xmlContent,
			String responseStatus, String responseMsg) {
		try {
			
			
			WebServiceLogInfo webserviceLogInfo = new WebServiceLogInfo();
			Long WebserviceLogSeq = getSeqValByName("SEQ_T_WEBSERVICE_LOG_INFO");
			webserviceLogInfo.setWebserviceLogInfoId(WebserviceLogSeq);
			webserviceLogInfo.setWebserviceCode(webserviceCode);
			webserviceLogInfo.setBusinessNo(businessNo);
			webserviceLogInfo.setWebserviceName(webserviceName);
			webserviceLogInfo.setResponseStatus(responseStatus);// 02-失败
			webserviceLogInfo.setXmlContent(xmlContent);
			webserviceLogInfo.setResponseMsg(responseMsg);
			webserviceLogInfo.setResponseTime(DateUtils.getCurrentTimestamp());
			
			//设置操作信息
			LoginInfo loginInfo = new LoginInfo();
			DefUserExample defUserExample = new DefUserExample();
			DefUserExample.Criteria criteria = defUserExample.createCriteria();
			criteria.andUserCodeEqualTo("fms").andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<DefUser> defUserList = defUserMapper.selectByExample(defUserExample);
			if (defUserList!=null&&defUserList.size()>0) {
				DefUser defUser = defUserList.get(0);
				loginInfo.setUserId(defUser.getUserId());
				loginInfo.setComId(defUser.getComId());
				loginInfo.setLoginComId(defUser.getComId());
				loginInfo.setUserCode(defUser.getUserCode());
			}
			BeanUtils.insertObjectSetOperateInfo(webserviceLogInfo, loginInfo);
			webserviceLogInfoMapper.insert(webserviceLogInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String getCodeName(String codeType, String code) {
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType(codeType);
		defCodeKey.setCode(code);
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		if(defCode!=null){
			return defCode.getCodeName();
		}else{
			return null;
		}
	}
	
}
