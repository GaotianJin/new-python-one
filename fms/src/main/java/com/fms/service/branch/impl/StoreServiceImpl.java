package com.fms.service.branch.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.DefStoreLeaseInfoMapper;
import com.fms.db.mapper.DefStoreMapper;
import com.fms.db.mapper.DefStorePhotoMapper;
import com.fms.db.mapper.StoreBelongComTraceMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.DefStore;
import com.fms.db.model.DefStoreExample;
import com.fms.db.model.DefStoreLeaseInfo;
import com.fms.db.model.DefStoreLeaseInfoExample;
import com.fms.db.model.DefStorePhoto;
import com.fms.db.model.DefStorePhotoExample;
import com.fms.db.model.StoreBelongComTrace;
import com.fms.db.model.StoreBelongComTraceExample;
import com.fms.service.branch.StoreService;
import com.fms.service.mapper.StoreServiceMapper;
import com.google.zxing.Result;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StoreServiceImpl implements StoreService
{
	@Autowired
	CommonService commonService;
	
	@Autowired
	DefStoreMapper defStoreMapper;
	
	@Autowired
	DefUserMapper defUserMapper;
	
	@Autowired
	StoreBelongComTraceMapper storeBelongComTraceMapper;
	
	@Autowired
	AgentMapper agentMapper;
	
	@Autowired
	private DefStorePhotoMapper defStorePhotoMapper;
	
	
	//机构信息查询
	@Autowired
	StoreServiceMapper storeServiceMapper;
	@Autowired
	private DefStoreLeaseInfoMapper storeLeaseInfoMapper;
	
	private static final Logger log = Logger.getLogger(StoreService.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public ResultInfo addStoreSave(Map map,LoginInfo loginInfo)
	{	
		ResultInfo resultInfo = new ResultInfo();
		DefStore defStoreSchema = (DefStore)map.get("defStoreSchema");
		StoreBelongComTrace storeBelongComTrace = (StoreBelongComTrace)map.get("storeBelongComInfo");
		List<DefStoreLeaseInfo> storeLeaseInfoList = (List<DefStoreLeaseInfo>)map.get("storeLeaseInfoList");
		//基本信息校验
		checkStore(defStoreSchema);
		defStoreSchema.setBelongStartDate(storeBelongComTrace.getStartDate());
		defStoreSchema.setBelongEndDate(storeBelongComTrace.getEndDate());
		defStoreSchema.setComId(storeBelongComTrace.getComId());
		checkStoreForAction(defStoreSchema);
		
		List<DefStore> storelist = this.storeServiceMapper.queryStoreByStoreCode(defStoreSchema);
		if (storelist.size() > 0)
		{
			log.info("该网点编码已经存在");
			throw new CisCoreException("该网点编码已经存在");
		}
		
		//网点基本信息新增
		if (defStoreSchema.getStoreId()!=null) {
			DefStore existDefStore = defStoreMapper.selectByPrimaryKey(defStoreSchema.getStoreId());
			BeanUtils.deleteAndInsertObjectSetOperateInfo(existDefStore, defStoreSchema, loginInfo);
			defStoreMapper.updateByPrimaryKey(defStoreSchema);
		}else {
//			Long storeSeq = commonService.getSeqValByName("SEQ_T_DEF_STORE");
//			defStoreSchema.setStoreId(storeSeq);
			BeanUtils.insertObjectSetOperateInfo(defStoreSchema, loginInfo);
			defStoreMapper.insert(defStoreSchema);
		}
		
		
		//网点归属轨迹信息处理
		if (storeBelongComTrace!=null&&storeBelongComTrace.getBlcomId()!=null) {
			StoreBelongComTrace existBelongComTrace = storeBelongComTraceMapper.selectByPrimaryKey(storeBelongComTrace.getBlcomId());
			storeBelongComTrace.setStoreId(defStoreSchema.getStoreId());
			BeanUtils.deleteAndInsertObjectSetOperateInfo(existBelongComTrace, storeBelongComTrace, loginInfo);
			storeBelongComTraceMapper.updateByPrimaryKey(storeBelongComTrace);
		}else {
//			Long storeBlComSeq = commonService.getSeqValByName("SEQ_T_STORE_BELONG_COM_TRACE");
//			storeBelongComTrace.setBlcomId(storeBlComSeq);
			storeBelongComTrace.setStoreId(defStoreSchema.getStoreId());
			BeanUtils.insertObjectSetOperateInfo(storeBelongComTrace, loginInfo);
			storeBelongComTraceMapper.insert(storeBelongComTrace);
		}
		
		//保存网点租金信息
		List<DefStoreLeaseInfo> storeLeaseList = new ArrayList<DefStoreLeaseInfo>();
		for (DefStoreLeaseInfo storeLeaseInfo:storeLeaseInfoList) {
			if(storeLeaseInfo.getStoreLeaseInfoId()!=null){
				DefStoreLeaseInfo existStoreLeaseInfo = storeLeaseInfoMapper.selectByPrimaryKey(storeLeaseInfo.getStoreLeaseInfoId());
				BeanUtils.deleteAndInsertObjectSetOperateInfo(existStoreLeaseInfo, storeLeaseInfo, loginInfo);
				storeLeaseInfoMapper.updateByPrimaryKeySelective(storeLeaseInfo);
			}else {
//				Long storeLeaseInfoId = commonService.getSeqValByName("SEQ_T_DEF_STORE_LEASE_INFO");
//				storeLeaseInfo.setStoreLeaseInfoId(storeLeaseInfoId);
				storeLeaseInfo.setStoreId(defStoreSchema.getStoreId());
				BeanUtils.insertObjectSetOperateInfo(storeLeaseInfo, loginInfo);
				storeLeaseInfoMapper.insert(storeLeaseInfo);
			}
			storeLeaseList.add(storeLeaseInfo);
		}
		
		Map result = new HashMap();
		result.put("storeInfo", JsonUtils.objectToMap(defStoreSchema));
		result.put("storeBelongComInfo", JsonUtils.objectToMap(storeBelongComTrace));
		result.put("storeLeaseMoneyList", JsonUtils.listObjectToListMap(storeLeaseList));
		
		resultInfo.setSuccess(true);
		resultInfo.setObj(result);
		resultInfo.setMsg("网点信息保存成功");
		return resultInfo;
	}


	@Override
	public Map<String, Object> getPageList(DataGridModel dgm, DefStore defStore)
	{
		
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		Map paramMap = new HashMap();
		paramMap.put("storeCode", defStore.getStoreCode());
		paramMap.put("storeName", defStore.getStoreName());
		paramMap.put("comId", defStore.getComId());
		paramMap.put("type", defStore.getType());
		paramMap.put("buildingId", defStore.getBuildingId());
		paramMap.put("queryStartIndex", dgm.getStartIndex());
		paramMap.put("queryEndIndex", dgm.getEndIndex());
		int total = storeServiceMapper.queryStoreListInfoCounts(paramMap);
		List<Map> list = storeServiceMapper.queryStoreListInfoPages(paramMap);
		
		result.put("total", total);
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 初始化页面，显示下拉列表项
	 */
	@Override
	public List<Map> getStoreListCode()
	{
		return (List<Map>) storeServiceMapper.queryStoreListCode();
	}
	
	/**
	 * 删除机构
	 */
	@Override
	@Transactional
	public void deleteStoreSave(Long storeId,LoginInfo loginInfo)
	{
		
		AgentExample agentExample = new AgentExample();
		AgentExample.Criteria criteria = agentExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteria.andStoreIdEqualTo(storeId);
		List<Agent> agentList = this.agentMapper.selectByExample(agentExample);
		if(agentList.size()>0)
		{
			log.info("该网点下存在财富顾问，财富顾问编码："+agentList.get(0).getAgentCode());
			throw new CisCoreException("该网点下存在财富顾问，财富顾问编码："+agentList.get(0).getAgentCode());
		}
		
		DefUserExample defUserExample = new DefUserExample();
		DefUserExample.Criteria criteriaUser = defUserExample.createCriteria();
		criteriaUser.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteriaUser.andStoreIdEqualTo(storeId);
		List<DefUser> userlist = this.defUserMapper.selectByExample(defUserExample);
		if (userlist.size() > 0)
		{
			log.info("该网点下存在用户，用户编码："+userlist.get(0).getUserCode());
			throw new CisCoreException("该网点下存在用户，用户编码："+userlist.get(0).getUserCode());
		}
		
		//删除机构基本信息表
		DefStore defStore = defStoreMapper.selectByPrimaryKey(storeId);
		BeanUtils.deleteObjectSetOperateInfo(defStore, loginInfo);
		defStoreMapper.updateByPrimaryKey(defStore);
		
		//删除机构归属信息表
		StoreBelongComTraceExample storeBelongTraceExample = new StoreBelongComTraceExample();
		storeBelongTraceExample.createCriteria().andStoreIdEqualTo(defStore.getStoreId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<StoreBelongComTrace> storeBelongTraceList = storeBelongComTraceMapper.selectByExample(storeBelongTraceExample);
		for (StoreBelongComTrace storeBelongTrace : storeBelongTraceList)
		{
			BeanUtils.deleteObjectSetOperateInfo(storeBelongTrace, loginInfo);
			storeBelongComTraceMapper.updateByPrimaryKey(storeBelongTrace);
		}
		
	}
	/**
	 * 更新页面信息带入根据机构ID
	 */
	public Map<String, Object> getUpdateListInfo(Long storeId)
	{
		
		//查询机构基本信息
		DefStore defStore = defStoreMapper.selectByPrimaryKey(storeId);
		
		//查询机构归属信息
		/*Map paramMap = new HashMap();
		paramMap.put("storeId", storeId);*/
	//	List<Map> storeBelongTraceList = storeServiceMapper.queryStoreBelongListInfo(paramMap);
		
		StoreBelongComTraceExample storeBelongComTraceExample = new StoreBelongComTraceExample();
		StoreBelongComTraceExample.Criteria criteria = storeBelongComTraceExample.createCriteria();
		criteria.andStoreIdEqualTo(defStore.getStoreId())
				.andStartDateEqualTo(defStore.getBelongStartDate())
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		if (defStore.getBelongEndDate()!=null) {
			criteria.andEndDateEqualTo(defStore.getBelongEndDate());
		}
		StoreBelongComTrace storeBelongComTrace = storeBelongComTraceMapper.selectByExample(storeBelongComTraceExample).get(0);
		
		//查询网点租金信息
		DefStoreLeaseInfoExample defStoreLeaseInfoExample = new DefStoreLeaseInfoExample();
		DefStoreLeaseInfoExample.Criteria criteria1 = defStoreLeaseInfoExample.createCriteria();
		criteria1.andStoreIdEqualTo(defStore.getStoreId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<DefStoreLeaseInfo> storeLeaseInfoList = storeLeaseInfoMapper.selectByExample(defStoreLeaseInfoExample);
		
		
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("defStore", defStore);
		result.put("storeBelongComInfo", JsonUtils.objectToMap(storeBelongComTrace));
		result.put("storeLeaseMoneyList", JsonUtils.listObjectToListMap(storeLeaseInfoList));
	//	result.put("storeBelongTraceList", storeBelongTraceList);
		return result;
	}


	@Override
	@Transactional
	public void updateStoreSave(Map map,LoginInfo loginInfo)
	{
		
		log.info("start ->>>>>  updateStoreSave");
		//获取参数实体
		DefStore defStoreSchema = (DefStore)map.get("defStoreSchema");
		
		//基本信息校验
		checkStore(defStoreSchema);
		checkStoreForAction(defStoreSchema);
		
		DefStoreExample defStoreExample = new DefStoreExample();
		DefStoreExample.Criteria criteria = defStoreExample.createCriteria();
		criteria.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		criteria.andStoreIdNotEqualTo(defStoreSchema.getStoreId());
		criteria.andStoreCodeEqualTo(defStoreSchema.getStoreCode());
		List<DefStore> storelist = this.defStoreMapper.selectByExample(defStoreExample);
		if (storelist.size() > 0)
		{
			log.info("该网点编码已经存在");
			throw new CisCoreException("该网点编码已经存在");
		}
		
		log.info("start ->>>>>  updateStoreSave StoreId " + defStoreSchema.getStoreId());
		//得到数据库实体
		DefStore defStoreDB = defStoreMapper.selectByPrimaryKey(defStoreSchema.getStoreId());
		defStoreSchema.setRcState(defStoreDB.getRcState());
		defStoreSchema.setCreateUserId(defStoreDB.getCreateUserId());
		defStoreSchema.setCreateDate(defStoreDB.getCreateDate());
		defStoreSchema.setOperComId(defStoreDB.getOperComId());
		
		//归属信息旧
		Long oldBelongComId = defStoreDB.getComId();
		Date oldBelongStartDate = defStoreDB.getBelongStartDate();
		Date oldBelongEndDate = defStoreDB.getBelongEndDate();
		
		Long newBelongComId = defStoreSchema.getComId();
		Date newBelongStartDate = defStoreSchema.getBelongStartDate();
		Date newBelongEndDate = defStoreSchema.getBelongEndDate();
		
		//参数实体->更新到数据库实体
		BeanUtils.copyProperties(defStoreSchema, defStoreDB);
		BeanUtils.updateObjectSetOperateInfo(defStoreDB, loginInfo);
		defStoreMapper.updateByPrimaryKey(defStoreDB);
		
		//机构归属轨迹信息处理
		if((oldBelongComId==null && newBelongComId==null)||
		   (oldBelongStartDate==null && newBelongStartDate==null) || 
		   (oldBelongEndDate==null && newBelongEndDate==null))
		{
			return;
		}
		
		if((oldBelongComId==null && newBelongComId!=null) || (oldBelongComId!=null && newBelongComId==null)||
		   (oldBelongStartDate==null && newBelongStartDate!=null) || (oldBelongStartDate!=null && newBelongStartDate==null)||
		   (oldBelongEndDate==null && newBelongEndDate!=null) || (oldBelongEndDate!=null && newBelongEndDate==null)
		|| !oldBelongComId.equals(newBelongComId) 
		|| !oldBelongStartDate.equals(newBelongStartDate) 
		|| !oldBelongEndDate.equals(newBelongEndDate))
		{
			StoreBelongComTrace storeBelongComTrace = new StoreBelongComTrace();
//			Long comBlSeq = commonService.getSeqValByName("SEQ_T_COM_BELONG_TRACE");
//			storeBelongComTrace.setBlcomId(comBlSeq);
//			storeBelongComTrace.setStoreId(defStoreDB.getStoreId());
			storeBelongComTrace.setComId(defStoreDB.getComId());
			storeBelongComTrace.setStartDate(defStoreDB.getBelongStartDate());
			storeBelongComTrace.setEndDate(defStoreDB.getBelongEndDate());
			BeanUtils.insertObjectSetOperateInfo(storeBelongComTrace, loginInfo);
			storeBelongComTraceMapper.insert(storeBelongComTrace);
		}
	}
	
	/**
	 * 机构信息校验
	 */
	public void checkStore(DefStore defStore)
	{
		if ("".equals(defStore.getStoreCode()) || defStore.getStoreCode() == null)
		{
			log.info("网点编码为空");
			throw new CisCoreException("网点编码不能为空");
		}
		if ("".equals(defStore.getStoreName()) || defStore.getStoreName() == null)
		{
			log.info("网点名称为空");
			throw new CisCoreException("网点名称不能为空");
		}
		
		if ("".equals(defStore.getType()) || defStore.getType() == null)
		{
			log.info("网点类型为空");
			throw new CisCoreException("网点类型不能为空");
		}
		if ("".equals(defStore.getState()) || defStore.getState() == null)
		{
			log.info("网点状态为空");
			throw new CisCoreException("网点状态不能为空");
		}
	}
	
	private void checkStoreForAction(DefStore defStore)
	{
		List<DefStore> storelist = null;
//		storelist = this.storeServiceMapper.queryStoreByStoreName(defStore);
//		if (storelist.size() > 0)
//		{
//			log.info("该网点名已经存在");
//			throw new CisCoreException("该网点名已经存在");
//		}
//		storelist = this.storeServiceMapper.queryStoreByStoreCode(defStore);
//		if (storelist.size() > 0)
//		{
//			log.info("该网点编码已经存在");
//			throw new CisCoreException("该网点已经存在");
//		}
		
		if(defStore.getBelongStartDate()==null)
		{
			log.info("网点归属开始日期不能为空");
			throw new CisCoreException("网点归属开始日期不能为空");
		}
		
		if(defStore.getBelongEndDate()!=null && defStore.getBelongStartDate().after(defStore.getBelongEndDate()))
		{
			log.info("网点归属开始日期不能大于结束日期");
			throw new CisCoreException("网点归属开始日期不能大于结束日期");
		}
	}


	@Override
	public ResultInfo uploadStoreImage(MultipartFile[] storeImageList, String param, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try{
			//判断参数是否为空
			if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件上传相关参数为空");
				return resultInfo;
			}
			//获取网点信息流水号
			String storeId = JsonUtils.getJsonValueByKey("storeId", param);
			//照片描述
			String storePhotoDescribe = JsonUtils.getJsonValueByKey("storePhotoDescribe", param);
			//文件类型
			String fileType = JsonUtils.getJsonValueByKey("fileType", param);
			//操作类型
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			//获取文件存储服务器http地址
			String fileSaveServerHttpAddress = commonService.getFileSaveServerHttpAddress();
			//获取文件存储服务器文档存储根目录
			String fileSaveServerRootPath = commonService.getFileSaveServerDocumentRootPath();
			//获取文件保存路径
			String fileSavePath = commonService.getFileSavePath(fileType);
			//创建文件保存日期文件夹，年/月/日
			String dateFolder = commonService.createDateFolder();
			//获取文件大小
			long fileSize = commonService.getUploadFileSize(fileType);
			String fileSize_M = String.valueOf(fileSize/1000/1024);
			//判断文件路径
			if (fileSavePath==null||"".equals(fileSavePath)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到文件保存路径");
				return resultInfo;
			}
			
			if (storeImageList==null||storeImageList.length==0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("请选择需要上传的照片！");
				return resultInfo;
			}
			String fullSavePath = fileSaveServerRootPath+fileSavePath+dateFolder;
			log.info("文件上传保存路径fileSavePath:::" + fullSavePath);
			File folderFile = new File(fullSavePath);
			if (!folderFile.exists()) {
				if (!folderFile.mkdirs()) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("创建文件文件存储目录失败！");
					return resultInfo;
				};
			}
			//获取到上传到的照片
			MultipartFile uploadImage = storeImageList[0];
			// 获得文件名(全路径)
			String uploadFileName = uploadImage.getOriginalFilename();
			log.info("文件名称uploadFileName：==="+uploadFileName);		
			//获取文件后缀
			String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
			if(suffix==null||"".equals(suffix)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件类型未知，上传失败！");
				return resultInfo;
			}
			log.info("====file suffix==="+suffix);
			//校验文件类型
			if(!commonService.verifyImageType(suffix)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件类型不符合要求，上传失败！");
				return resultInfo;
			}
			//校验文件大小
			long size = uploadImage.getSize();
			log.info("====upLoadFileSize==="+size);
			if (size>fileSize) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("文件过大(最大为"+fileSize_M+"M)！");
				return resultInfo;
			}
			//存储文件名	
			String storeName = commonService.rename(fileType,uploadFileName);
			//文件全名称
			String fileFullName = fileSaveServerRootPath+fileSavePath + dateFolder +storeName;
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileFullName));
			FileCopyUtils.copy(uploadImage.getInputStream(), outputStream);
			if (outputStream!=null) {
				outputStream.close();
			}
			//存储图片信息到表中
			DefStorePhoto defStorePhoto = new DefStorePhoto();
//			Long storePhotoInfoId = commonService.getSeqValByName("SEQ_T_DEF_STORE_PHOTO");
//			defStorePhoto.setStorePhotoInfoId(storePhotoInfoId);
			defStorePhoto.setStoreId(new Long(storeId));
			defStorePhoto.setStorePhoto(dateFolder+storeName);
			defStorePhoto.setStorePhotoDescribe(storePhotoDescribe);
			BeanUtils.insertObjectSetOperateInfo(defStorePhoto, loginInfo);
			defStorePhotoMapper.insert(defStorePhoto);
			
			resultInfo.setSuccess(true);
			resultInfo.setObj(fileSaveServerHttpAddress+fileSavePath+dateFolder+storeName);
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	
	public ResultInfo getAllStoreImage(String storeId,String fileType){
		ResultInfo resultInfo = new ResultInfo();
		DefStorePhotoExample defStorePhotoExample = new DefStorePhotoExample();
		DefStorePhotoExample.Criteria criteria = defStorePhotoExample.createCriteria();
		criteria.andStoreIdEqualTo(new Long(storeId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<DefStorePhoto> storePhotoList = defStorePhotoMapper.selectByExample(defStorePhotoExample);
		//获取文件存储服务器http地址
		String fileSaveServerHttpAddress = commonService.getFileSaveServerHttpAddress();
		//获取文件保存路径
		String fileSavePath = commonService.getFileSavePath(fileType);
		for(DefStorePhoto defStorePhoto:storePhotoList){
			defStorePhoto.setStorePhoto(fileSaveServerHttpAddress+fileSavePath+defStorePhoto.getStorePhoto());
		}
		resultInfo.setSuccess(true);
		resultInfo.setObj(storePhotoList);
		return resultInfo;
	}


	@Override
	@Transactional
	public ResultInfo deleteStoreImage(String param,LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		//获取网点基本信息
		String storeId = JsonUtils.getJsonValueByKey("storeId", param);
		String imgUrl = JsonUtils.getJsonValueByKey("imgUrl", param);
		String fileType = JsonUtils.getJsonValueByKey("fileType", param);
		//imgUrl:====http://10.68.60.234:8088/fms/storeImage/2015/10/13/F042015101310235818574130307037.jpg
		//获取文件保存路径
		String fileSavePath = commonService.getFileSavePath(fileType);
		String storePhoto = imgUrl.substring(imgUrl.indexOf(fileSavePath)+fileSavePath.length());
		//逻辑删除数据库中的记录
		DefStorePhotoExample defStorePhotoExample = new DefStorePhotoExample();
		DefStorePhotoExample.Criteria criteria = defStorePhotoExample.createCriteria();
		criteria.andStoreIdEqualTo(new Long(storeId))
				.andStorePhotoEqualTo(storePhoto)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<DefStorePhoto> defStorePhotoList = defStorePhotoMapper.selectByExample(defStorePhotoExample);
		for(DefStorePhoto defStorePhoto:defStorePhotoList){
			defStorePhoto.setRcState(Constants.DELETE_RECORD);
			BeanUtils.deleteObjectSetOperateInfo(defStorePhoto, loginInfo);
			defStorePhotoMapper.updateByPrimaryKey(defStorePhoto);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功");
		return resultInfo;
	}
	
}
