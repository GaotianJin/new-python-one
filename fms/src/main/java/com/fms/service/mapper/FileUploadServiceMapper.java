package com.fms.service.mapper;

import java.util.List;
import java.util.Map;

public interface FileUploadServiceMapper {
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getFileListByBuisnessNoAndType(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer getFileListByBuisnessNoAndTypeCount(Map paramMap);
	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> getFileListByBuisnessNoAndTypeControl(Map paramMap);
	@SuppressWarnings("rawtypes")
	public Integer getFileListByBuisnessNoAndTypeControlCount(Map paramMap);
}
