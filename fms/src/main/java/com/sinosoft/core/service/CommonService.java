package com.sinosoft.core.service;

import java.util.List;
import java.util.Map;

public interface CommonService {
	public List<String> exeQuerySql(String sql);
	public Long getSeqValByName(String seqName);
	public String createTradeNo(String productType);
	public String createCustomerNo();
	public String createPreCustomerNo();
	public Map<String, Object> getReportInfo(String reportCode);
	public String createInviteCode(int length);
	public String getFileSavePath(String fileType);
	public long getUploadFileSize(String fileType);
	public String rename(String fileType,String name);
	public String createDateFolder();
	public String getFileSaveServerHttpAddress();
	public String getFileSaveServerDocumentRootPath();
	public boolean verifyImageType(String suffix);
	public boolean saveWebserviceLog(String webserviceCode,String webserviceName,String businessNo,
			String xmlContent,String responseStatus,String responseMsg);
	public String getCodeName(String codeType,String code);
}
