package com.fms.service.file.impl;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.DefFileInfoMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.DefFileInfo;
import com.fms.db.model.DefFileInfoExample;
import com.fms.service.file.FileUploadService;
import com.fms.service.mapper.FileUploadServiceMapper;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefCodeKey;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileUploadServiceImpl implements FileUploadService {

	private static final Logger log = Logger.getLogger(FileUploadServiceImpl.class);
	
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private DefFileInfoMapper defFileInfoMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private FileUploadServiceMapper fileUploadServiceMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Override
	@Transactional
	public ResultInfo uploadFile(MultipartFile[] multipartFileList,String param,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		List<String> fileNameList = new ArrayList<String>();
		List<String> unUpLoadFile = new ArrayList<String>();
		try {
			
			if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件上传相关参数为空");
				return resultInfo;
			}
			String flag=JsonUtils.getJsonValueByKey("flag", param);
			String businessNo=JsonUtils.getJsonValueByKey("businessNo", param);
			String filename=JsonUtils.getJsonValueByKey("uploadFileName", param);
			String businessType=JsonUtils.getJsonValueByKey("businessType", param);
			String businessSubType=JsonUtils.getJsonValueByKey("businessSubType", param);
			
			if (!flag.equals("")&&flag!=null) {
			DefFileInfoExample defFileInfoExample=new DefFileInfoExample();
			defFileInfoExample.createCriteria().andRcStateEqualTo("E")
						.andBusinessNoEqualTo(new Long(businessNo)).andBusinessTypeEqualTo(businessType)
						.andBusinessSubTypeEqualTo(businessSubType).andUploadFileNameEqualTo(filename);
			List<DefFileInfo> defFileInfos = defFileInfoMapper.selectByExample(defFileInfoExample);
			if (defFileInfos!=null&&defFileInfos.size()>0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该产品已经上传该附件，请重新上传！");
				return resultInfo;
			}
			}
			String type = JsonUtils.getJsonValueByKey("type", param);
			String fileSavePath = "";
			//若为上传交易视频文件 则创建特定文件夹保存
			if (businessType.equals("13")) {
				fileSavePath = getFileSavePath("05");
			}else if (businessType.equals("15")){ 
				//若为升级客户文件
				fileSavePath = getFileSavePath("06");
			}else {
				fileSavePath = getFileSavePath(type);
			}
			long fileSize = getUploadFileSize();
			String fileSize_M = String.valueOf(fileSize/1000/1024);
			if (fileSavePath==null||"".equals(fileSavePath)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到文件保存路径");
				return resultInfo;
			}
			
			if (multipartFileList==null||multipartFileList.length==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请选择需要上传的文件！");
				return resultInfo;
			}
			log.info("文件上传保存路径fileSavePath:::" + fileSavePath);
			File folderFile =new File("");
			if (businessType.equals("13")) {
				folderFile = new File(fileSavePath);
			}else {
				folderFile = new File(fileSavePath);
			}
			
			if (!folderFile.exists()) {
				if (!folderFile.mkdirs()) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("创建文件文件存储目录失败！");
					return resultInfo;
				};
			}

			for(MultipartFile multipartFile:multipartFileList){
				DefFileInfo defFileInfo = (DefFileInfo)JsonUtils.jsonStrToObject(param, DefFileInfo.class);
				
				// 获得文件名(全路径)
				String uploadFileName = multipartFile.getOriginalFilename();
				log.info("文件名称uploadFileName：==="+uploadFileName);		
				//获取文件后缀
				String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
				if(suffix==null||"".equals(suffix)){
					unUpLoadFile.add(uploadFileName+" : 文件类型未知");
					continue;
				}
				log.info("====file suffix==="+suffix);
				//校验文件
				if (!businessType.equals("15")) {
					if(!verifyFileType(suffix,businessType)){
						unUpLoadFile.add(uploadFileName+" : 文件类型不符合要求");
						continue;
					}
				}
				
				long size = multipartFile.getSize();
				log.info("====upLoadFileSize==="+size);
				if (size>fileSize) {
					unUpLoadFile.add(uploadFileName+" : 文件过大(最大为"+fileSize_M+"M)");
					continue;
				}
				
				String storeName = rename(defFileInfo.getBusinessType(),uploadFileName);
				String storeFullName = fileSavePath + storeName;
				/*String zipName = zipName(storeName);
				String zipFullName = fileSavePath + zipName;*/
				
				/*ZipOutputStream outputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFullName)));
				outputStream.putNextEntry(new ZipEntry(uploadFileName));
				outputStream.setEncoding("GBK");*/
				
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(storeFullName));
				
				FileCopyUtils.copy(multipartFile.getInputStream(), outputStream);
				
				if (outputStream!=null) {
					outputStream.close();
				}
				
				Long defFileInfoId = commonService.getSeqValByName("SEQ_T_DEF_FILE_INFO");
				defFileInfo.setFileInfoId(defFileInfoId);
				defFileInfo.setFileName(storeName);
				defFileInfo.setUploadFileName(uploadFileName);
				defFileInfo.setBusinessSubType(businessSubType);
				defFileInfo.setFileSavePath(fileSavePath);
				BeanUtils.insertObjectSetOperateInfo(defFileInfo, loginInfo);
				defFileInfoMapper.insert(defFileInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//删除所有已保存的文件
			deleteAllFiles(fileNameList);
			resultInfo.setSuccess(false);
			resultInfo.setMsg("文件上传出现异常");
		}
		resultInfo.setSuccess(true);
		if(unUpLoadFile.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg(JsonUtils.objectToJsonStr(unUpLoadFile));
		}else{
			resultInfo.setMsg("文件上传成功");
		}
		return resultInfo;
	}

	
	/**
	 * @return
	 */
	private String getFileSavePath(String type){
		String fileSavePath = "";
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("fileSavePath");
		defCodeKey.setCode(type);
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		if (defCode!=null) {
			fileSavePath = defCode.getCodeName();
			String dateStr = DateUtils.getCurrentDate();
			String year = dateStr.substring(0, 4);
			String month = dateStr.substring(5, 7);
			String day = dateStr.substring(8, 10);
			fileSavePath = fileSavePath+year+"/"+month+"/"+day+"/";
		}
		return fileSavePath;
	}
	
	/**获取文件尺寸限制
	 * @return
	 */
	private long getUploadFileSize(){
		long fileSize = 0l;
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("uploadFileSize");
		defCodeKey.setCode("01");
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		if (defCode!=null) {
			fileSize = Long.parseLong(defCode.getCodeName())*1024*1000;
		}
		return fileSize;
	}
	
	public void download(HttpServletRequest request, HttpServletResponse response,DefFileInfo defFileInfo,LoginInfo loginInfo){
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			defFileInfo = defFileInfoMapper.selectByPrimaryKey(defFileInfo.getFileInfoId());
			
			String fileSavePath = defFileInfo.getFileSavePath();
			String fileName = defFileInfo.getFileName();
			String uploadFileName = defFileInfo.getUploadFileName();
			String downLoadPath = fileSavePath + fileName;
			String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
			
			 //判断浏览器
			String userAgent = request.getHeader("User-Agent"); 
			//针对IE或者以IE为内核的浏览器：
			if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
				uploadFileName = java.net.URLEncoder.encode(uploadFileName, "UTF-8");
			} else {
			//非IE浏览器的处理：
				uploadFileName = new String(uploadFileName.getBytes("UTF-8"),"ISO-8859-1");
			}
			//response.setContentType("application/octet-stream");
			//response.setHeader("Content-disposition", "attachment; filename=" + new String(uploadFileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-disposition", "attachment; filename=" + uploadFileName);
			//response.setHeader("Content-Length", String.valueOf(fileLength));


			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			if (prefix.equals("pdf")) {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				//获取登录用户Id、姓名
				DefUser userName = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
				String loginUserName = userName.getUserName();
				//获取当前用户工号
				/*AgentExample agentExample = new AgentExample();
				agentExample.createCriteria().andUserIdEqualTo(loginInfo.getUserId()).andRcStateEqualTo("E");
				List<Agent> agent = agentMapper.selectByExample(agentExample);
				String agentCode = agent.get(0).getAgentCode();*/
				//将pdf文件先加水印然后输出
				setWatermark(bos,fileSavePath+fileName,"巨鲸，您的终身财富管理专家，财富顾问：" + loginUserName , 16); 
			}
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setWatermark(BufferedOutputStream bos, String input,String waterMarkName, int permission)
			throws DocumentException, IOException {
			PdfReader reader = new PdfReader(input);
			PdfStamper stamper = new PdfStamper(reader, bos);
			stamper.setEncryption(null, null,PdfWriter.ALLOW_PRINTING,PdfWriter.STANDARD_ENCRYPTION_128);
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte content;
			BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
			BaseFont.EMBEDDED);
			PdfGState gs = new PdfGState();
			for (int i = 1; i < total; i++) {
			content = stamper.getOverContent(i);//在内容上方加水印
			//content = stamper.getUnderContent(i);//在内容下方加水印
			gs.setFillOpacity(0.2f);
			// content.setGState(gs);
			content.beginText();
			content.setColorFill(Color.LIGHT_GRAY);
			content.setFontAndSize(base, 15);
			content.setTextMatrix(70, 200);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 300,430, 15);
			content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, 400,220, 15);
			//content.setColorFill(Color.BLACK);
			//content.setFontAndSize(base, 8);
			//content.showTextAligned(Element.ALIGN_CENTER, "下载时间："+ waterMarkName + "", 300, 10, 0);
			content.endText();


			}
			stamper.close();
			}
	/**删除所有文件
	 * @param fileNameList
	 */
	private void deleteAllFiles(List<String> fileNameList){
		try {
			for (String fileName:fileNameList) {
				File file = new File(fileName);
				if (!file.delete()) {
					log.info("=======fileName===="+fileName+"===删除失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**校验文件类型
	 * @param suffix
	 * @param businessType 
	 * @return
	 */
	private boolean verifyFileType(String suffix, String businessType){
		suffix = suffix.toLowerCase();
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria criteria = defCodeExample.createCriteria();
		if (businessType.equals("13")) {
			criteria.andCodeTypeEqualTo("uploadVideoType").andCodeNameEqualTo(suffix);
		}else {
			criteria.andCodeTypeEqualTo("uploadFileType").andCodeNameEqualTo(suffix);
		}
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		if (defCodeList==null||defCodeList.size()==0) {
			return false;
		}
		return true;
	}


	@Override
	@Transactional
	public ResultInfo deleteFile(List<DefFileInfo> defFileInfoList,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (defFileInfoList!=null&&defFileInfoList.size()>0) {
				for(DefFileInfo defFileInfo:defFileInfoList){
					defFileInfo = defFileInfoMapper.selectByPrimaryKey(defFileInfo.getFileInfoId());
					BeanUtils.deleteObjectSetOperateInfo(defFileInfo, loginInfo);
					defFileInfoMapper.updateByPrimaryKey(defFileInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除文件失败！");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除文件成功！");
		return resultInfo;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid getFileListByBusinessNoAndType(DataGridModel dgm, Map paramMap,
			LoginInfo loginInfo) {
				//datagrid 数据信息
				DataGrid dataGrid = new DataGrid();
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		String businessNo=paramMap.get("businessNo").toString();
		if("".equals(businessNo) || businessNo == null){
			dataGrid.setTotal(0);
			return dataGrid;
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		String businessType=paramMap.get("businessType").toString();
		
		if("09".equals(businessType)){
			String roleId=paramMap.get("roleId").toString();
			if(roleId==null){
				Integer total = fileUploadServiceMapper.getFileListByBuisnessNoAndTypeCount(paramMap);
				List<Map<String,String>> resultList = fileUploadServiceMapper.getFileListByBuisnessNoAndType(paramMap);
				dataGrid.setRows(resultList);
				dataGrid.setTotal(total);
				return dataGrid;
			}else{
				if("09".equals(businessType)){
					if("9".equals(roleId)){
						Integer total = fileUploadServiceMapper.getFileListByBuisnessNoAndTypeControlCount(paramMap);
						List<Map<String,String>> resultList = fileUploadServiceMapper.getFileListByBuisnessNoAndTypeControl(paramMap);
						dataGrid.setRows(resultList);
						dataGrid.setTotal(total);
						return dataGrid;
					}else{
						Integer total = fileUploadServiceMapper.getFileListByBuisnessNoAndTypeCount(paramMap);
						List<Map<String,String>> resultList = fileUploadServiceMapper.getFileListByBuisnessNoAndType(paramMap);
						dataGrid.setRows(resultList);
						dataGrid.setTotal(total);
						return dataGrid;
					}
				}	
			}
		}else{
			Integer total = fileUploadServiceMapper.getFileListByBuisnessNoAndTypeCount(paramMap);
			List<Map<String,String>> resultList = fileUploadServiceMapper.getFileListByBuisnessNoAndType(paramMap);
			dataGrid.setRows(resultList);
			dataGrid.setTotal(total);
			return dataGrid;
		}
		return dataGrid;
		
	}
	
	
	
	/**
	 * 将上传的文件进行重命名
	 * 
	 * @author geloin
	 * @date 2012-3-29 下午3:39:53
	 * @param name
	 * @return
	 */
	private String rename(String fileType,String name) {
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
	 * 压缩后的文件名
	 * 
	 * @author geloin
	 * @date 2012-3-29 下午6:21:32
	 * @param name
	 * @return
	 */
	private String zipName(String name) {
		String prefix = "";
		if (name.indexOf(".") != -1) {
			prefix = name.substring(0, name.lastIndexOf("."));
		} else {
			prefix = name;
		}
		return prefix + ".zip";
	}
	
}
