package com.fms.service.file;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.multipart.MultipartFile;

import com.fms.db.model.DefFileInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface FileUploadService {
	public ResultInfo uploadFile(MultipartFile[] multipartFileList,String param,LoginInfo loginInfo);
	public void download(HttpServletRequest request, HttpServletResponse response,DefFileInfo defFileInfo, LoginInfo loginInfo);
	public ResultInfo deleteFile(List<DefFileInfo> defFileInfoList,LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public DataGrid getFileListByBusinessNoAndType(DataGridModel dgm, Map paramMap,
			LoginInfo loginInfo);
}
