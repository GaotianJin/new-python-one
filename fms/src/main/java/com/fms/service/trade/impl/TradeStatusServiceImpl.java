package com.fms.service.trade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.wsdl.symbolTable.Undefined;
import org.apache.log4j.Logger;
import org.exolab.castor.types.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.util.DateUtil;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.CustOthInfoMapper;
import com.fms.db.mapper.SysEmailAdressMapper;
import com.fms.db.mapper.SysEmailInfoMapper;
import com.fms.db.mapper.TradeCustInfoMapper;
import com.fms.db.mapper.TradeInfoMapper;
import com.fms.db.mapper.TradeProductInfoMapper;
import com.fms.db.mapper.TradeStatusInfoMapper;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustOthInfo;
import com.fms.db.model.CustOthInfoExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PdIncomeDis;
import com.fms.db.model.PdIncomeDisDetail;
import com.fms.db.model.SysEmailAdress;
import com.fms.db.model.SysEmailAdressExample;
import com.fms.db.model.SysEmailInfo;
import com.fms.db.model.SysEmailInfoExample;
import com.fms.db.model.TradeCustInfo;
import com.fms.db.model.TradeCustInfoExample;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeProductInfo;
import com.fms.db.model.TradeProductInfoExample;
import com.fms.db.model.TradeStatusInfo;
import com.fms.db.model.TradeStatusInfoExample;
import com.fms.service.mapper.CustomerServiceMapper;
import com.fms.service.mapper.TradeStatusServiceMapper;
import com.fms.service.product.impl.IncomeDisServiceImpl;
import com.fms.service.sms.SmsService;
import com.fms.service.trade.TradeStatusService;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.db.model.DefUserExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.service.SendEmailService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Service("TradeStatusService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TradeStatusServiceImpl implements TradeStatusService{
	
	@Autowired
	private TradeStatusServiceMapper tradeStatusServiceMapper;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private TradeStatusInfoMapper tradeStatusInfoMapper;
	@Autowired
	private TradeCustInfoMapper tradeCustInfoMapper;
	@Autowired
	private CustBaseInfoMapper custBaseInfoMapper;
	@Autowired
	private SmsService smsService;
	@Autowired
	private TradeProductInfoMapper tradeProductInfoMapper;
	@Autowired
	private SysEmailInfoMapper sysEmailInfoMapper;
	@Autowired
	private SysEmailAdressMapper sysEmailAdressMapper;
	@Autowired
	private DefUserMapper defUserMapper;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private CustOthInfoMapper custOthInfoMapper;
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	
	private static final Logger logger = Logger.getLogger(TradeStatusServiceImpl.class);
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid queryStausList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		//1、获取总条数
		Integer total = tradeStatusServiceMapper.getTradeStausTotal(paramMap);
		
		//2、获取所有实体
		List<Map<String,String>> resultList = tradeStatusServiceMapper.getTradeStausServiceList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getTradeDetailInfo(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map<String, String> tradeDetailInfoMap = tradeStatusServiceMapper.getTradeDetailInfo(paramMap);
			resultInfo.setSuccess(true);
			resultInfo.setObj(tradeDetailInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易详细信息出现异常！");
		}
		return resultInfo;
	}

	@Override
	@Transactional
	public ResultInfo saveTradeStatus(TradeStatusInfo tradeStatusInfo,
			LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeStatusInfo==null||tradeStatusInfo.getTradeInfoId()==null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易信息为空，维护交易状态信息失败！");
			}else {
				//保存交易状态信息
				if(tradeStatusInfo.getTradeStatusInfoId()==null){
					TradeStatusInfoExample tradeStatusInfoExample = new TradeStatusInfoExample();
					TradeStatusInfoExample.Criteria criteria = tradeStatusInfoExample.createCriteria();
					criteria.andTradeInfoIdEqualTo(tradeStatusInfo.getTradeInfoId())
							.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<TradeStatusInfo> tradeStatusInfos = tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample);
					if(tradeStatusInfos!=null&&tradeStatusInfos.size()>=0){
						tradeStatusInfo.setStatusSerialNo(new Long(tradeStatusInfos.size()+1+""));
					}else {
						tradeStatusInfo.setStatusSerialNo(new Long("1"));
					}
					//判断此状态是否已经维护过
					criteria.andTradeStatusEqualTo(tradeStatusInfo.getTradeStatus());
					List<TradeStatusInfo> existTradeStatusInfos = tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample);
					if(existTradeStatusInfos!=null&&existTradeStatusInfos.size()>0){
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该笔交易已经维护此状态，不能重复添加！");
						return resultInfo;
					}
					Long tradeStatusInfoId = commonService.getSeqValByName("SEQ_T_TRADE_STATUS_INFO");
					tradeStatusInfo.setTradeStatusInfoId(tradeStatusInfoId);
					BeanUtils.insertObjectSetOperateInfo(tradeStatusInfo,loginInfo);
					tradeStatusInfoMapper.insert(tradeStatusInfo);
				}else{
					TradeStatusInfo existTradeStatusInfo = tradeStatusInfoMapper.selectByPrimaryKey(tradeStatusInfo.getTradeStatusInfoId());
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeStatusInfo, tradeStatusInfo, loginInfo);
					tradeStatusInfoMapper.updateByPrimaryKeySelective(tradeStatusInfo);
				}
				//交易主表状态更新
				TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeStatusInfo.getTradeInfoId());
				tradeInfo.setTradeStaus(tradeStatusInfo.getTradeStatus());
				BeanUtils.updateObjectSetOperateInfo(tradeInfo, loginInfo);
				tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
				if(tradeStatusInfo.getTradeStatus().equals("10")){
					TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
					TradeCustInfoExample.Criteria tradeCustCriteria = tradeCustInfoExample.createCriteria();
					tradeCustCriteria.andTradeInfoIdEqualTo(tradeStatusInfo.getTradeInfoId())
					                 .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);
					if(tradeCustInfoList!=null&&tradeCustInfoList.size()>0){
						Long custBaseInfoId = tradeCustInfoList.get(0).getCustBaseInfoId();
						CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
						custBaseInfo.setCustLevel("01");
						if(custBaseInfo.getPreCustConvertCustTime()==null){
							custBaseInfo.setPreCustConvertCustTime(DateUtils.getCurrentTimestamp());
							BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
							custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
						}
						//交易成立 维护客户投资类型 若存续规模大于500万  则维护成专业投资者
						resultInfo = modifyCustInvestType(custBaseInfo,loginInfo);
					}else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该笔交易没有对应的交易客户联系信息记录");
						return resultInfo;
					}
					//创建产品成立短信
					resultInfo = smsService.createProductFoundMes(tradeStatusInfo, loginInfo);
					
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("交易状态维护成功");
				resultInfo.setObj(tradeStatusInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("交易状态维护出现异常！");
		}
		return resultInfo;
	}
	
	
	/**
	 * 获取最后一次维护的交易状态信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getLastTradeStatusInfo(Map paramMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map<String, String> statusMapInfo = tradeStatusServiceMapper.getLastTradeStatusInfo(paramMap);
			resultInfo.setSuccess(true);
			resultInfo.setObj(statusMapInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易状态信息出现异常！");
		}
		return resultInfo;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getRiskTradeInfo(Map paramMap,String operate) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if ("Modify".equals(operate)) {
				List<Map<String, String>> riskTradeInfoList = tradeStatusServiceMapper.getRiskTradeStatusInfo(paramMap);
				resultInfo.setObj(riskTradeInfoList);
			}else {
				List<Map<String, String>> riskTradeInfoList = tradeStatusServiceMapper.getRiskTradeInfo(paramMap);
				resultInfo.setObj(riskTradeInfoList);
			}
			resultInfo.setSuccess(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取保险交易信息出现异常！");
		}
		return resultInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public ResultInfo saveTradeStatus(List<TradeStatusInfo> tradeStatusInfoList, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			long statusSerialNo = 0;
			for (TradeStatusInfo tradeStatusInfo:tradeStatusInfoList) {
				if (tradeStatusInfo==null||tradeStatusInfo.getTradeInfoId()==null) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("交易信息为空，维护交易状态信息失败！");
				}else {
					//保存交易状态信息
					if(tradeStatusInfo.getTradeStatusInfoId()==null){
						TradeStatusInfoExample tradeStatusInfoExample = new TradeStatusInfoExample();
						TradeStatusInfoExample.Criteria criteria = tradeStatusInfoExample.createCriteria();
						criteria.andTradeInfoIdEqualTo(tradeStatusInfo.getTradeInfoId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<TradeStatusInfo> tradeStatusInfos = tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample);
						if (statusSerialNo==0) {
							if(tradeStatusInfos!=null&&tradeStatusInfos.size()>=0){
								statusSerialNo = new Short(tradeStatusInfos.size()+1+"");
							}else {
								statusSerialNo = 1;
							}
						}
						tradeStatusInfo.setStatusSerialNo(statusSerialNo);
						//判断此状态是否已经维护过
						criteria.andTradeStatusIsNull(/*tradeStatusInfo.getTradeStatus()*/)
								.andProductIdEqualTo(tradeStatusInfo.getProductId());
						List<TradeStatusInfo> existTradeStatusInfos = tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample);
						if(existTradeStatusInfos!=null&&existTradeStatusInfos.size()>0){
							resultInfo.setSuccess(false);
							resultInfo.setMsg("该笔交易已经维护此状态，不能重复添加！");
							return resultInfo;
						}
						Long tradeStatusInfoId = commonService.getSeqValByName("SEQ_T_TRADE_STATUS_INFO");
						tradeStatusInfo.setTradeStatusInfoId(tradeStatusInfoId);
						BeanUtils.insertObjectSetOperateInfo(tradeStatusInfo,loginInfo);
						tradeStatusInfoMapper.insert(tradeStatusInfo);
					}else{
						TradeStatusInfo existTradeStatusInfo = tradeStatusInfoMapper.selectByPrimaryKey(tradeStatusInfo.getTradeStatusInfoId());
						BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeStatusInfo, tradeStatusInfo, loginInfo);
						tradeStatusInfo.setTradeStatusInfoId(existTradeStatusInfo.getTradeStatusInfoId());
						tradeStatusInfoMapper.updateByPrimaryKeySelective(tradeStatusInfo);
					}
				}
			}
			//交易主表状态更新
			TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeStatusInfoList.get(0).getTradeInfoId());
			tradeInfo.setTradeStaus(tradeStatusInfoList.get(0).getTradeStatus());
			BeanUtils.updateObjectSetOperateInfo(tradeInfo, loginInfo);
			tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
			//获取交易状态信息
			Map paramMap = new HashMap();
			paramMap.put("tradeInfoId", tradeStatusInfoList.get(0).getTradeInfoId().toString());
			List<Map<String, String>> riskTradeInfoList = tradeStatusServiceMapper.getRiskTradeStatusInfo(paramMap);
			TradeStatusInfo tradeStatusInfo = tradeStatusInfoList.get(0);
			if(tradeStatusInfo.getTradeStatus()=="06"){
				TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
				TradeCustInfoExample.Criteria tradeCustCriteria = tradeCustInfoExample.createCriteria();
				tradeCustCriteria.andTradeInfoIdEqualTo(tradeStatusInfo.getTradeInfoId())
				                 .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);
				if(tradeCustInfoList!=null&&tradeCustInfoList.size()>0){
					Long custBaseInfoId = tradeCustInfoList.get(0).getCustBaseInfoId();
					CustBaseInfo custBaseInfo = custBaseInfoMapper.selectByPrimaryKey(custBaseInfoId);
					custBaseInfo.setCustLevel("01");
					if(custBaseInfo.getPreCustConvertCustTime()==null){
						custBaseInfo.setPreCustConvertCustTime(DateUtils.getCurrentTimestamp());
						BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
						custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
					}
				}else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("该笔交易没有对应的交易客户联系信息记录");
					return resultInfo;
				}
			}
			//设置完成信息
			resultInfo.setSuccess(true);
			resultInfo.setMsg("交易状态维护成功");
			resultInfo.setObj(riskTradeInfoList);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取保险交易信息出现异常！");
		}
		return resultInfo;
	}
	
	
	@Override
	@Transactional
	public ResultInfo importFloatCopiesFile(MultipartFile[] importFileNameList,LoginInfo loginInfo) {
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
				resultInfo = resolveFloatCopiesExcel(workbook, loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("浮动产品份额导入出现异常！");
		}
		if(unUpLoadFile.size()>0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg(JsonUtils.objectToJsonStr(unUpLoadFile));
								}
		return resultInfo;
	}
	

	/**
	 * @param workbook
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	private ResultInfo resolveFloatCopiesExcel(Workbook workbook,LoginInfo loginInfo){
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
            	//客户姓名
            	String custName = "";
            	//产品编码
            	String productCode = "";
            	//产品份额
            	String subscriptionCopies = "";
                for (int j = 0; j < colSize; j++) {
                	String colName = colNameMap.get(j+"");
                	Cell cell = sheet.getCell(j,i);
                	//获取客户姓名
                	if (colName.indexOf("custName")>-1) {
                		custName = cell.getContents();
					}
                	//获取产品编码
                	if (colName.indexOf("productCode")>-1) {
                		productCode = cell.getContents();
					}
                	//获取浮动产品维护份额
                	if (colName.indexOf("subscriptionCopies")>-1) {
                		subscriptionCopies = cell.getContents();
					}
                }
                    logger.info(custName+"+"+productCode+"+"+subscriptionCopies);
             		Map<String, String> paramMap = new HashMap<String, String>();
             		paramMap.put("custName", custName);
             		paramMap.put("productCode", productCode);
             		//BigDecimal countCopies = new BigDecimal(0);
                    //根据客户姓名和产品编码获取交易流水号
 					List<Map<String,String>> map = tradeStatusServiceMapper.getTradeStatusInfoId(paramMap);
 					if (map.size() == 0) {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("获取交易信息失败");
						return resultInfo;
					}
 					if (map.size() == 1) {
						String tradeStatusInfoId = JsonUtils.getJsonValueByKey("tradeStatusInfoId", map.get(0).toString());
						logger.info("=============="+tradeStatusInfoId+"================");
	 					TradeStatusInfo tradeStatusInfo = new TradeStatusInfo();
	 					tradeStatusInfo = tradeStatusInfoMapper.selectByPrimaryKey(Long.parseLong(tradeStatusInfoId));
	 					//将原来的数据状态改为D
	 					tradeStatusInfo.setRcState("D");
	 					BeanUtils.updateObjectSetOperateInfo(tradeStatusInfo, loginInfo);
	 					tradeStatusInfoMapper.updateByPrimaryKeySelective(tradeStatusInfo);
	 					//创建主键
	 					Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_STATUS_INFO");
	 					tradeStatusInfo.setTradeStatusInfoId(tradeInfoId);
	 					tradeStatusInfo.setRcState("E");
	 					BigDecimal Copies = new BigDecimal(subscriptionCopies);
	 					tradeStatusInfo.setSubscriptionCopies(Copies);
	 					//新创建一条数据 并将excel中的数据更新到数据库中。。。
	 					BeanUtils.updateObjectSetOperateInfo(tradeStatusInfo, loginInfo);
	 					tradeStatusInfoMapper.insert(tradeStatusInfo);
					}
 					if (map.size() > 1) {
 							//查询该产品对应交易的所有份额总和
		 					Map<String,String> amountCopies = tradeStatusServiceMapper.getSubscriptionCopies(paramMap);
		 					String copies = JsonUtils.getJsonValueByKey("subscriptionCopies", amountCopies.toString());
		 					BigDecimal sumCopies = new BigDecimal(copies);
 						for (int j = 0; j < map.size(); j++) {
 							String tradeStatusInfoId = JsonUtils.getJsonValueByKey("tradeStatusInfoId", map.get(j).toString());
 							logger.info("=============="+tradeStatusInfoId+"================");
 		 					TradeStatusInfo tradeStatusInfo = new TradeStatusInfo();
 		 					tradeStatusInfo = tradeStatusInfoMapper.selectByPrimaryKey(Long.parseLong(tradeStatusInfoId));
 		 				
 		 				if(j < (map.size()-1)){
 		 					//将原来的数据状态改为D
 		 					tradeStatusInfo.setRcState("D");
 		 					BeanUtils.updateObjectSetOperateInfo(tradeStatusInfo, loginInfo);
 		 					tradeStatusInfoMapper.updateByPrimaryKeySelective(tradeStatusInfo);
 		 				    //创建主键
 		 					Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_STATUS_INFO");
 		 					tradeStatusInfo.setTradeStatusInfoId(tradeInfoId);
 		 					tradeStatusInfo.setRcState("E");
 		 					//获取未上传的单笔份额
 		 					BigDecimal originalCopies= tradeStatusInfo.getSubscriptionCopies();
 		 					//获取从Excel中传进来的总份额
 		 					BigDecimal Copies = new BigDecimal(subscriptionCopies);
 		 					//按比例计算应分配的份额
 		 					BigDecimal rate = originalCopies.divide(sumCopies,2);
 		 					BigDecimal finalCopies = rate.multiply(Copies);
 		 					tradeStatusInfo.setSubscriptionCopies(finalCopies);
 		 					//新创建一条数据 并将excel中的数据更新到数据库中。。。
 		 					BeanUtils.updateObjectSetOperateInfo(tradeStatusInfo, loginInfo);
 		 					tradeStatusInfoMapper.insert(tradeStatusInfo);
 		 					}
						if (j == (map.size()-1)) {
							//将原来的数据状态改为D
 		 					tradeStatusInfo.setRcState("D");
 		 					BeanUtils.updateObjectSetOperateInfo(tradeStatusInfo, loginInfo);
 		 					tradeStatusInfoMapper.updateByPrimaryKeySelective(tradeStatusInfo);
 		 				    //创建主键
 		 					Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_STATUS_INFO");
 		 					tradeStatusInfo.setTradeStatusInfoId(tradeInfoId);
 		 					tradeStatusInfo.setRcState("E");
 		 					//查询更新过后的总份额
 		 					Map<String,String> newAmountCopies = tradeStatusServiceMapper.getSubscriptionCopies(paramMap);
		 					String newCopies = JsonUtils.getJsonValueByKey("subscriptionCopies", newAmountCopies.toString());
		 					BigDecimal countCopies = new BigDecimal(newCopies);
 		 					//获取从Excel中传进来的总份额
 		 					BigDecimal Copies = new BigDecimal(subscriptionCopies);
 		 					//总份额减掉更新过后的总份额
 		 					BigDecimal finalCopies = Copies.subtract(countCopies);
 		 					tradeStatusInfo.setSubscriptionCopies(finalCopies);
 		 					//新创建一条数据 并将excel中的数据更新到数据库中。。。
 		 					BeanUtils.updateObjectSetOperateInfo(tradeStatusInfo, loginInfo);
 		 					tradeStatusInfoMapper.insert(tradeStatusInfo);
							}
						}
					}
            }
            workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("解析浮动产品份额文件出现异常！");
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("浮动产品份额导入成功！");
		return resultInfo;
	}
	
	/**
	 * 创建并发送产品成立邮件方法
	 * @param loginInfo
	 * @return
	 */
	@Transactional
	public ResultInfo productFoundEmailBatch(){
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = setBatchLoginInfo();
		try {
			Map paramMap = new HashMap();
			List<Map> resultProductList = tradeStatusServiceMapper.getFoundProductList(paramMap);
			if (resultProductList.isEmpty() || resultProductList == null ||resultProductList.size() < 1) {
				resultInfo.setMsg(new Date() + "----未生成产品成立邮件，因为当前日期没有产品成立----");
			}
			for (Map productMap : resultProductList) {
				String productName = productMap.get("productName").toString();
				Long productId = Long.parseLong( productMap.get("productId").toString());
				//先创建短信，然后发送
				resultInfo = createProductFoundEmail(productName,productId,loginInfo);
				if(!resultInfo.isSuccess()){
					return resultInfo;
				}
				List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
				//发送短信
				for (SysEmailInfo sysEmailInfo2 : sysEmailInfoList) {
					resultInfo = sendProductFoundEmail(sysEmailInfo2,loginInfo);
				}
			}
		} catch (Exception e) {
			logger.info("-----------------------------创建并发送产品成立邮件失败-------------------------");
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 设置批处理的操作人员信息，默认设置为fms
	 * @return
	 */
	private LoginInfo setBatchLoginInfo(){
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
		return loginInfo;
	}
	/**
	 * 创建产品发布邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createProductFoundEmail(String productName,Long productId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//产品成立通知邮件
		sysEmailInfo.setEmailType("11"); //产品成立邮件
		sysEmailInfo.setEmailRelationNo(productId);
		//获取不同邮件类型的邮箱地址
		String emailType = sysEmailInfo.getEmailType();
		SysEmailAdressExample sysEmailAdressExample = new SysEmailAdressExample();
		SysEmailAdressExample.Criteria sysEmailAdressCriteria = sysEmailAdressExample.createCriteria();
		sysEmailAdressCriteria.andEmailTypeEqualTo(emailType);
		List<SysEmailAdress> email = sysEmailAdressMapper.selectByExample(sysEmailAdressExample);
		List<SysEmailInfo> resultList = new ArrayList<SysEmailInfo>();
		SysEmailInfo sysEmailInfo1 = null;
		for (int i = 0; i < email.size(); i++) {
			sysEmailInfo1 = new SysEmailInfo();
			//产品成立通知邮件
			sysEmailInfo1.setEmailType("11");
			sysEmailInfo1.setEmailRelationNo(productId);
			sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
			sysEmailInfo1.setEmailTitle("产品成立通知");
			//创建邮件内容
			String mailContent ="<"+ productName+">产品已成立，请发送成立短信。";
			sysEmailInfo1.setEmailContent(mailContent);
			//01-未发送
			sysEmailInfo1.setEmailStatus("01");
			sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
			BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1,loginInfo);
			Long sysEmailInfoId = commonService.getSeqValByName("SEQ_T_SYS_EMAIL_INFO");
			sysEmailInfo1.setSysEmailInfoId(sysEmailInfoId);
			sysEmailInfoMapper.insert(sysEmailInfo1);
			resultList.add(sysEmailInfo1);
		}
		
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品成立邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendProductFoundEmail(SysEmailInfo sysEmailInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
		defCodeCriteria.andCodeTypeEqualTo("sendMailParam");
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		Map<String,String> mailHostParam = new HashMap<String,String>();
		if (defCodeList!=null&&defCodeList.size()>0) {
			for(DefCode defCode:defCodeList){
				mailHostParam.put(defCode.getCodeAlias(), defCode.getCodeName());
			}
		}else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到邮件发送服务器相关参数！");
			return resultInfo;
		}
		if(sysEmailInfo!=null){
			String address = sysEmailInfo.getEmailAddress();
			String title = sysEmailInfo.getEmailTitle();
			String content = sysEmailInfo.getEmailContent();
			//String othEmailAddress = sysEmailInfo.getOthEmailAddress();
			resultInfo = sendEmailService.sendEmail(address, title, content, mailHostParam);
			if (resultInfo.isSuccess()) {
				sysEmailInfo.setEmailSendTime(DateUtils.getCurrentTimestamp());
				//02-已发送
				sysEmailInfo.setEmailStatus("02");
			}else {
				//03-发送失败
				sysEmailInfo.setEmailStatus("03");
			}
			BeanUtils.updateObjectSetOperateInfo(sysEmailInfo, loginInfo);
			sysEmailInfoMapper.updateByPrimaryKey(sysEmailInfo);
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}
	
	
	/**
	 * 交易状态维护
	 * @return
	 */
	@Transactional
	private ResultInfo modifyCustInvestType(CustBaseInfo custBaseInfo,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//客户投资类型 01-普通投资者  02-专业投资者
			String investCustomerType = custBaseInfo.getInvestCustomerType();
			Long custBaseInfoId = custBaseInfo.getCustBaseInfoId();
			CustOthInfoExample custOthInfoExample = new CustOthInfoExample();
			custOthInfoExample.createCriteria().andCustBaseInfoIdEqualTo(custBaseInfoId).andRcStateEqualTo("E");
			List<CustOthInfo> custOthInfos = custOthInfoMapper.selectByExample(custOthInfoExample);
			//客户类型 05-机构客户  机构客户不参与存续份额转换投资类型的情况
			String custType = custOthInfos.get(0).getCustType();
			if (!"02".equals(investCustomerType) && !"05".equals(custType)) {
				Map paramMap= new HashMap();
				paramMap.put("custBaseInfoId", custBaseInfoId);
				//获取客户存续规模
				Map resultMap = customerServiceMapper.queryCustRemainAmount(paramMap);
				Double remainScale = Double.parseDouble(resultMap.get("remainScale").toString());
				//若存续规模大于500万  则修改客户主表中的投资者类型 -> 02-专业投资者
				if (remainScale/10000 >= 500) {
					custBaseInfo.setInvestCustomerType("02");
					BeanUtils.updateObjectSetOperateInfo(custBaseInfo, loginInfo);
					custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
				}
			}
			resultInfo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
}
