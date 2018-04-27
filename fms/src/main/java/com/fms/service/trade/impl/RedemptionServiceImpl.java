package com.fms.service.trade.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fms.Print.RedemptionPrint;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.DefFileInfoMapper;
import com.fms.db.mapper.DefPrintInfoMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PDWealthMapper;
import com.fms.db.mapper.RedemptionInfoMapper;
import com.fms.db.mapper.RedemptionOperationMapper;
import com.fms.db.mapper.RedemptionTradeInfoMapper;
import com.fms.db.mapper.SysEmailAdressMapper;
import com.fms.db.mapper.SysEmailInfoMapper;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustBaseInfoExample;
import com.fms.db.model.DefFileInfo;
import com.fms.db.model.DefFileInfoExample;
import com.fms.db.model.DefPrintInfo;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthExample;
import com.fms.db.model.RedemptionInfo;
import com.fms.db.model.RedemptionInfoExample;
import com.fms.db.model.RedemptionOperation;
import com.fms.db.model.RedemptionTradeInfo;
import com.fms.db.model.RedemptionTradeInfoExample;
import com.fms.db.model.SysEmailAdress;
import com.fms.db.model.SysEmailAdressExample;
import com.fms.db.model.SysEmailInfo;
import com.fms.db.model.TradeInfo;
import com.fms.service.mapper.RedemptionServiceMapper;
import com.fms.service.trade.RedemptionService;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.mapper.DefUserMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.db.model.DefCodeKey;
import com.sinosoft.core.db.model.DefUser;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.service.SendEmailService;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.PdfTool;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.SimpleMoneyFormat;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RedemptionServiceImpl implements RedemptionService {
    @Autowired
	RedemptionServiceMapper redemptionServiceMapper;
    @Autowired
    PDProductMapper pdProductMapper;
    @Autowired
    CommonService commonService;
    @Autowired
    RedemptionInfoMapper  redemptionInfoMapper;
    @Autowired
    RedemptionTradeInfoMapper redemptionTradeInfoMapper;
    @Autowired
    DefFileInfoMapper defFileInfoMapper;
    @Autowired
    RedemptionOperationMapper redemptionOperationMapper;
    @Autowired
    DefCodeMapper defCodeMapper;
    @Autowired
    DefPrintInfoMapper defPrintInfoMapper;
    @Autowired
    CustBaseInfoMapper custBaseInfoMapper;
    @Autowired
    private PDWealthMapper pDWealthMapper; 
    @Autowired
    private SysEmailInfoMapper sysEmailInfoMapper;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private DefUserMapper defUserMapper;
    @Autowired
    private SysEmailAdressMapper sysEmailAdressMapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, String>> redemptionCustomerQuery(LoginInfo loginInfo) {
    Map paramMap=new HashMap ();
    paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
    paramMap.put("userId", loginInfo.getUserId());
    paramMap.put("operComId", loginInfo.getComId());
    List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
	List<Map> custMapList =redemptionServiceMapper.customerQuery(paramMap);
	
	if (custMapList.size()>0) {
		for (Map map : custMapList) {
			Map<String,String> custMap=new HashMap<String,String>();
			custMap.put("code", map.get("custNo").toString());
			custMap.put("codeName",map.get("custNo").toString()+"-"+ map.get("custName").toString());
			productMapList.add(custMap);
		}
		
	}
		
	return productMapList;
	}

	public List<Map<String, String>> redemptionProductQuery() {
		PDProductExample pdProductExample=new PDProductExample();
		pdProductExample.createCriteria().andRcStateEqualTo("E").andProductTypeEqualTo("1").andProductSubTypeNotEqualTo("02").andSalesStatusEqualTo("0").andStatusEqualTo("2");
		List<PDProduct> pdPrdouctList= pdProductMapper.selectByExample(pdProductExample);
		List<Map<String,String>> codeList=new ArrayList<Map<String,String>>();
		
		if (pdPrdouctList.size()>0) {
			for (PDProduct pdProduct : pdPrdouctList) {
				Map<String,String> productMap=new HashMap<String, String>();
				productMap.put("code", pdProduct.getProductId().toString());
				productMap.put("codeName", pdProduct.getProductName());
				codeList.add(productMap);
			}
		}
		return codeList;
	}

	@SuppressWarnings("rawtypes")
	public ResultInfo queryTradeInfoByCustandPro(Map paramMap) {
		ResultInfo  resultInfo=new ResultInfo();
		List<Map> tradeInfoList=redemptionServiceMapper.queryTradeInfoByCustandPro(paramMap);
		
		if (tradeInfoList.size()>0) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(tradeInfoList);
		}
		else
		{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该客户不存在该产品的交易信息，所以无法获得详细的赎回交易信息");
		}
		
		return resultInfo;
	}

	@SuppressWarnings("rawtypes")
	public List<Map<String, String>> redemptionExpectOpenDayQuery(Map paramMap) {
		String productId = paramMap.get("productId").toString();
		List<Map<String, String>> codeList = new ArrayList<Map<String, String>>();
		
		if (productId != null) {
			List<Map> openDateList = redemptionServiceMapper.redemptionOpenDayQuery(paramMap);
			if (openDateList.size() > 0) {
				for (Map map : openDateList) {
					Map<String, String> openDayMap = new HashMap<String, String>();
					openDayMap.put("code", map.get("openDate").toString());
					openDayMap.put("codeName", map.get("openDate").toString());
					codeList.add(openDayMap);
				}
			}

		}

		return codeList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo queryNetValueByOpenDay(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		int count=0;
		List<Map> netValueInfo = redemptionServiceMapper.queryNetValueByOpenDay(paramMap);
		List<Map> returnNetvalueInfo=new ArrayList<Map>();
		Map NetValueMap=new HashMap<String, String>();
		if (netValueInfo != null&&netValueInfo.size()>0){
			for (Map map : netValueInfo) {
				//先判断是不是费后净值，如果是费后净值,直接跳出循环
				if (map.get("netType").equals("02")) {
					NetValueMap.put("netValue", map.get("netValue").toString());
					NetValueMap.put("publicDay", map.get("publicDay").toString());
					returnNetvalueInfo.add(NetValueMap);
					count++;
					break;
				}
			}
			//如果查询出来的净值没有费后的，则返回费前的参考净值
			if (count==0) {
				NetValueMap.put("netValue", netValueInfo.get(0).get("netValue").toString());
				NetValueMap.put("publicDay", netValueInfo.get(0).get("publicDay").toString());
				returnNetvalueInfo.add(NetValueMap);
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(returnNetvalueInfo);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("没有获得此赎回开放日对应的参考净值信息,请联系产品经理");
		}
		return resultInfo;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResultInfo addRedemptionInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		try {
			//获取客户号、产品号
			String custNo=paramMap.get("custNo").toString();
			Long productId=new Long(paramMap.get("productId").toString());
			Map param=new HashMap();
			param.put("custNo", custNo);
			param.put("productId", productId);
			//根据productId查询财富主表信息
			PDWealthExample pdWealthExample = new PDWealthExample();
			PDWealthExample.Criteria pdWealthExampleCriteria = pdWealthExample.createCriteria();
			pdWealthExampleCriteria.andProductIdEqualTo(new Long(productId)).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDWealth> pdWealths= this.pDWealthMapper.selectByExample(pdWealthExample);
			for (PDWealth pdWealth : pdWealths) {
				if(StringUtils.isNotBlank(pdWealth.getCloseDperiodType())){
					//可赎回型封闭期内
					if( pdWealth.getCloseDperiodType().equals("1")){
						
					}
					//不可赎回型封闭期内	
					if(pdWealth.getCloseDperiodType().equals("2")){
						//获取产品的成立日
						String fundDate = DateUtils.getDateString(pdWealth.getFoundDate());
						String closeDperiods =pdWealth.getCloseDperiods();
						//获取类型    
						if(StringUtils.isNotBlank(pdWealth.getCloseDperiodUnit())){
							if(pdWealth.getCloseDperiodUnit().equals("Y")){
								//获取封闭期限
								String closeDperiodDate = DateUtils.calDate(fundDate, new Integer(closeDperiods), "Y");
								if(DateUtils.isDateBefore(DateUtils.getCurrentDate(), closeDperiodDate)){
									resultInfo.setSuccess(false);
									resultInfo.setMsg("不可赎回型封闭期内无法进行赎回交易");                      
									return resultInfo;
								}
							}else if(pdWealth.getCloseDperiodUnit().equals("M")){
								//获取封闭期限
								String closeDperiodDate = DateUtils.calDate(fundDate, new Integer(closeDperiods), "M");
								DateUtils.isDateBefore(DateUtils.getCurrentDate(), closeDperiodDate);
								if(DateUtils.isDateBefore(DateUtils.getCurrentDate(), closeDperiodDate)){
									resultInfo.setSuccess(false);
									resultInfo.setMsg("不可赎回型封闭期内无法进行赎回交易");                      
									return resultInfo;
								}
							}else{
								//获取封闭期限
								String closeDperiodDate = DateUtils.calDate(fundDate, new Integer(closeDperiods), "D");
								if(DateUtils.isDateBefore(DateUtils.getCurrentDate(), closeDperiodDate)){
									resultInfo.setSuccess(false);
									resultInfo.setMsg("不可赎回型封闭期内无法进行赎回交易");                      
									return resultInfo;
								}
								
							}
							
						}
						
					}
				}
			}
			//保存赎回信息主表(赎回开放日、赎回申请日期、赎回参考净值)
			RedemptionInfo redemptionInfoMain = (RedemptionInfo) paramMap.get("redemptionRefenceInfo");
			Long seqRedemptionInfo = null;
			String redemptionInfoId = "";
			if(paramMap.get("redemptionInfoId")!=null&&!"".equals(paramMap.get("redemptionInfoId").toString())){
				redemptionInfoId = paramMap.get("redemptionInfoId").toString();
				seqRedemptionInfo = new Long(redemptionInfoId);
			}else{
				seqRedemptionInfo = commonService.getSeqValByName("SEQ_T_REDEMPTION_INFO");
			}
			redemptionInfoMain.setRedemptionInfoId(seqRedemptionInfo);//赎回主表ID
			//获取赎回总份额金额
			RedemptionInfo RedemptionInfoSec = (RedemptionInfo) paramMap.get("redemptionTradeInfo");
			redemptionInfoMain.setRedemptionTotalShare(RedemptionInfoSec.getRedemptionTotalShare());//本次赎回总份额
			redemptionInfoMain.setRedemptionTotalMoney(RedemptionInfoSec.getRedemptionTotalMoney());//本次赎回总金额
			redemptionInfoMain.setRedemptionStatus("01");//赎回状态 01-赎回中；02-复核中；03-确认中；04-已赎回
			redemptionInfoMain.setRedemptionInputOperator(loginInfo.getUserCode());//赎回申请人
			redemptionInfoMain.setCustomerNo(custNo);//客户号
			redemptionInfoMain.setPdProductId(productId);//产品流水号
			BigDecimal subscribeTotalShare=redemptionServiceMapper.querysubscribeTotalShare(param);
			redemptionInfoMain.setSubscribeTotalShare(subscribeTotalShare);//认购初始总份额
			BigDecimal redemptionTotalShare=redemptionServiceMapper.queryAlreadyRedemptionTotalShare(param);
			redemptionInfoMain.setAlreadyRedemptionTotalShare(redemptionTotalShare);//已赎回总份额
			Double remainingTotalShare=subscribeTotalShare.doubleValue()-redemptionTotalShare.doubleValue()-RedemptionInfoSec.getRedemptionTotalShare().doubleValue();
			redemptionInfoMain.setRemainingTotalShare(new BigDecimal(remainingTotalShare.toString()));//剩余全部份额
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(redemptionInfoMain, loginInfo);
			
			if(redemptionInfoId!=null&&!"".equals(redemptionInfoId)){
				redemptionInfoMapper.updateByPrimaryKeySelective(redemptionInfoMain);
			}else {
				redemptionInfoMapper.insert(redemptionInfoMain);
			}
			
			//保存赎回信息详细表
			List<RedemptionTradeInfo> redemptionTradeInfoList = new ArrayList<RedemptionTradeInfo>();
			redemptionTradeInfoList = (List<RedemptionTradeInfo>)paramMap.get("custAllTradeTableInfo");
			List<RedemptionTradeInfo> saveRedemptionTradeInfoList = new ArrayList<RedemptionTradeInfo>();
			
			if (redemptionTradeInfoList.size()>0) {
				for (RedemptionTradeInfo redemptionTradeInfo : redemptionTradeInfoList) {
					Long seqRedemptionTradeInfoSeq = null;
					if(redemptionInfoId!=null&&!"".equals(redemptionInfoId)){
						RedemptionTradeInfoExample redemptionTradeInfoExample = new RedemptionTradeInfoExample();
						RedemptionTradeInfoExample.Criteria criteria = redemptionTradeInfoExample.createCriteria();
						criteria.andRedemptionInfoIdEqualTo(new Long(redemptionInfoId))
								.andTradeInfoIdEqualTo(redemptionTradeInfo.getTradeInfoId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
						List<RedemptionTradeInfo> existRedemptionTradeInfoList = redemptionTradeInfoMapper.selectByExample(redemptionTradeInfoExample);
						seqRedemptionTradeInfoSeq = existRedemptionTradeInfoList.get(0).getRedemptionTradeInfoId();
					}else {
						seqRedemptionTradeInfoSeq = commonService.getSeqValByName("SEQ_T_REDEMPTION_TRADE_INFO");
					}
					
					//赎回详细表(交易流水号、赎回份额)
					redemptionTradeInfo.setRedemptionTradeInfoId(seqRedemptionTradeInfoSeq);//赎回交易流水号
					redemptionTradeInfo.setRedemptionInfoId(redemptionInfoMain.getRedemptionInfoId());//赎回主表流水号
					saveRedemptionTradeInfoList.add(redemptionTradeInfo);
					com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(redemptionTradeInfo, loginInfo);
					if (redemptionInfoId!=null&&!"".equals(redemptionInfoId)) {
						redemptionTradeInfoMapper.updateByPrimaryKeySelective(redemptionTradeInfo);
					}else {
						redemptionTradeInfoMapper.insert(redemptionTradeInfo);
					}
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(redemptionInfoMain.getRedemptionInfoId());
			resultInfo.setMsg("赎回保存信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("赎回保存信息失败");
		}
		return resultInfo;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo printApplicationForm(Map map, String uploadDir,LoginInfo loginInfo) {
		RedemptionPrint redemptionPrint=new RedemptionPrint();
		ResultInfo resultInfo=new  ResultInfo();
		//查询获得生成赎回的信息
		String redemptionInfoId=map.get("redemptionInfoId").toString();
		RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
		redemptionInfoExample.createCriteria().andRcStateEqualTo("E").andRedemptionInfoIdEqualTo(new Long(redemptionInfoId));
		RedemptionInfo redemptionInfo=redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
		Map paramMap=new HashMap();
		paramMap.put("redemptionInfoId", redemptionInfoId);
		Map redemptionFormInfo = redemptionServiceMapper.getRedemptionFormInfo(paramMap);
		//赎回份额
		String redemptionShare=redemptionFormInfo.get("redemptionShare").toString();
		DecimalFormat df=new DecimalFormat(".00");
		if (redemptionShare!=null) {
			String redemptionShareBig = SimpleMoneyFormat.formatChineseShare(Double.valueOf(redemptionShare));//将认购金额改为大写
			double redemptionShareChange=Double.valueOf(redemptionShare);
			String redemptionShareSmall=df.format(redemptionShareChange);
			redemptionFormInfo.put("redemptionShareBig", redemptionShareBig);
			redemptionFormInfo.put("redemptionShareSmall", redemptionShareSmall+"(份)");
			/*redemptionPrint.setREDEMPTIONSHAREBIG(redemptionShareBig);
			redemptionPrint.setREDEMPTIONSHARESMALL(redemptionShareSmall+"(份)");*/
		}
		else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回份额为空");
			return resultInfo;
		}
		String unit = redemptionFormInfo.get("unit").toString();
		String closeDperiods = redemptionFormInfo.get("closeDperiods").toString();
		if(!"".equals(closeDperiods) && !"".equals(unit)){
			if(unit.equals("Y")){
				redemptionFormInfo.put("closeDate", redemptionFormInfo.get("closeDperiods").toString()+"(年)");
				//redemptionPrint.setCLOSEDATE(redemptionFormInfo.get("closeDperiods").toString()+"(年)");
			}else if(unit.equals("M")){
				redemptionFormInfo.put("closeDate", redemptionFormInfo.get("closeDperiods").toString()+"(个月)");
				//redemptionPrint.setCLOSEDATE(redemptionFormInfo.get("closeDperiods").toString()+"(个月)");
			}else{
				redemptionFormInfo.put("closeDate", redemptionFormInfo.get("closeDperiods").toString()+"(天)");
				//redemptionPrint.setCLOSEDATE(redemptionFormInfo.get("closeDperiods").toString()+"(天)");
			}
		}else{
			redemptionFormInfo.put("closeDate", "未提供");
		}
		
		//获得打印模板路径
		String path = uploadDir + "WEB-INF/classes/pdfTemplate/redemptionForm.pdf";
		File file1 = new File(path);
		if(!file1.exists()){
			resultInfo.setMsg("打印失败！原因是模板不存在！");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		//设置生成文件在服务器上的保存位置
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dfFile = new SimpleDateFormat("yyyy/MM/dd/");
		String dateStr = dfFile.format(calendar.getTime());
		DefCodeKey defCodeKey = new DefCodeKey();
		defCodeKey.setCodeType("fileSavePath");
		defCodeKey.setCode("02");
		DefCode defCode = defCodeMapper.selectByPrimaryKey(defCodeKey);
		String fileSavePath = "";
		if (defCode != null) {
			fileSavePath = defCode.getCodeName() + dateStr;
			if (fileSavePath != null && !"".equals(fileSavePath)) {
				File file = new File(fileSavePath);
				if (!file.exists()) {
					file.mkdirs();
				}
			} else {
				resultInfo.setMsg("文件保存路径为空！");
				resultInfo.setSuccess(false);
				return resultInfo;
			}
		} else {
			resultInfo.setMsg("文件输出路径不存在！");
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		try {
			Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
			String printName = "SH"+redemptionInfo.getCustomerNo()+now ;//SH+客户号+当前时间;
			PdfReader pdfReader = new PdfReader(path);
			PdfStamper ps = new PdfStamper(pdfReader, new FileOutputStream(fileSavePath+printName+".pdf"));
			AcroFields fields = ps.getAcroFields();
//			String fontPath = uploadDir + "WEB-INF/classes/simhei.ttf";
//			BaseFont bfChinese=BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//			PdfTool.setAllPropertyFont(fields,bfChinese);
			// 替换PDF文件里面的标签字段
			for (Object key: redemptionFormInfo.keySet()) {
				Object value = redemptionFormInfo.get(key);
				//Paragraph t = new Paragraph(value, chineseFont);
				//t.setAlignment(Paragraph.ALIGN_CENTER);
				fields.setField(key.toString(),value.toString());
			}
		
			//不能缺少
			ps.setFormFlattening(true);
			ps.close();
			Map printCount = new HashMap();
			printCount.put("redemptionInfoId", redemptionInfoId);
			short times =(short) (redemptionServiceMapper.printTotal(printCount) + 1);
			DefPrintInfo defPrint = new DefPrintInfo();
			Long printId = commonService.getSeqValByName("SEQ_T_DEF_PRINT_INFO");
			defPrint.setPrintInfoId(printId);
			defPrint.setPrintTimes((long) times);
			defPrint.setBusinessNo(new Long(redemptionInfoId));
			defPrint.setBusinessType("2");//2-赎回认购书打印
			defPrint.setPrintFileName(printName+".pdf");
			defPrint.setFileSavePath(fileSavePath);
			defPrint.setPrintTime(DateUtils.getCurrentTimestamp());
			defPrint.setDoucmentType("01");
			BeanUtils.insertObjectSetOperateInfo(defPrint,loginInfo);
			defPrintInfoMapper.insert(defPrint);
			resultInfo.setMsg("赎回申请单生成成功，请选择下载并打印！");
			resultInfo.setSuccess(true);
			return resultInfo;
		}catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("生成赎回单失败！原因为："+e);
			resultInfo.setSuccess(false);
			return resultInfo;
		}
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid getTradeRedemptionPageList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {
		if (paramMap == null) {
			paramMap = new HashMap();
		}
	    paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
	    paramMap.put("userId", loginInfo.getUserId());
	    paramMap.put("operComId", loginInfo.getComId());
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		paramMap.put("custNo", paramMap.get("custName"));
		paramMap.put("productId", paramMap.get("productName"));
		// 获取符合条件的行数
		Integer total = redemptionServiceMapper.tradeRedemptionQueryListCount(paramMap);
		List<Map> resultList = redemptionServiceMapper.tradeRedemptionQueryListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@SuppressWarnings("rawtypes")
	public ResultInfo cancelTradeRedemption(Map map, LoginInfo loginInfo) {
		String redemptionInfoId=map.get("redemptionInfoId").toString();
		ResultInfo resultInfo=new ResultInfo();
		if (redemptionInfoId!=null) {
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRcStateEqualTo("E").andRedemptionInfoIdEqualTo(new Long(redemptionInfoId));
			RedemptionInfo redemptionInfo=(RedemptionInfo)redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
			redemptionInfo.setRedemptionStatus("05");//赎回撤销
			com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(redemptionInfo, loginInfo);
			redemptionInfoMapper.updateByPrimaryKey(redemptionInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("赎回撤销成功");
		}
		else
		{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("赎回撤销失败");
		}	
		return resultInfo;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo getTradeRedemptionInfo(String redemptionInfoId,LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		Map paramMap=new HashMap<String,String>();
		paramMap.put("redemptionInfoId", redemptionInfoId);
		
		Map<String, Object>  receptionInfoMap=new HashMap<String, Object>();
		if (redemptionInfoId!=null) {
			//获取赎回客户产品信息
			List<Map>  redemptionCustProInfo=redemptionServiceMapper.getRedemptionCustProInfo(paramMap);
			if (redemptionCustProInfo.size()>0) {
				receptionInfoMap.put("redemptionCustProInfo", redemptionCustProInfo);
			}
			
			//获取赎回信息
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andRcStateEqualTo("E");
			RedemptionInfo redemptionInfo=(RedemptionInfo)redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
			
			paramMap.put("productId", redemptionInfo.getPdProductId().toString());
			//获取参考净值对应的开放日
			List<Map> redemptionInfoNetInfo=redemptionServiceMapper.queryNetValueByOpenDay(paramMap);
			if (redemptionInfoNetInfo.size()>0) {
				receptionInfoMap.put("redemptionInfoNetInfo",redemptionInfoNetInfo);
			}
			
			if (redemptionInfo!=null) {
				receptionInfoMap.put("redemptionInfo", JsonUtils.objectToMap(redemptionInfo));
			}
			//获取赎回交易明细信息		
			List<Map> redemptionInfoList= redemptionServiceMapper.getUpdateTradeRedemptionInfo(paramMap);
			
			if (redemptionInfoList.size()>0) {
				receptionInfoMap.put("redemptionInfoList", redemptionInfoList);
			}
			
			resultInfo.setObj(receptionInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取赎回信息成功");
		}
		else
		{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息失败");
		}
	  return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo updateRedemptionInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		try {
			//获取客户号、产品号
			String custNo=paramMap.get("custNo").toString();
			Long productId=new Long(paramMap.get("productId").toString());
			Long redemtionInfoId=new Long(paramMap.get("redemptionInfoId").toString());
			Map param=new HashMap();
			param.put("custNo", custNo);
			param.put("productId", productId);
			
			//查询到已经存在的要修改的这条记录
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRcStateEqualTo("E").andRedemptionInfoIdEqualTo(redemtionInfoId);
			RedemptionInfo redemptionInfoExist=(RedemptionInfo)redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
			
			//保存赎回信息主表(赎回开放日、赎回申请日期、赎回参考净值)
			RedemptionInfo redemptionInfoMain = (RedemptionInfo) paramMap.get("redemptionRefenceInfo");
			//获取赎回总份额金额
			RedemptionInfo RedemptionInfoSec = (RedemptionInfo) paramMap.get("redemptionTradeInfo");
			redemptionInfoExist.setReferenceNetValue(redemptionInfoMain.getReferenceNetValue());
			redemptionInfoExist.setRedemptionOpenday(redemptionInfoMain.getRedemptionOpenday());
			redemptionInfoExist.setRedemptionApplyDate(redemptionInfoMain.getRedemptionApplyDate());
			redemptionInfoExist.setRedemptionTotalShare(RedemptionInfoSec.getRedemptionTotalShare());//本次赎回总份额
			redemptionInfoExist.setRedemptionTotalMoney(RedemptionInfoSec.getRedemptionTotalMoney());//本次赎回总金额
			redemptionInfoExist.setRedemptionInputOperator(loginInfo.getUserCode());//赎回申请人
			BigDecimal subscribeTotalShare=redemptionServiceMapper.querysubscribeTotalShare(param);
			redemptionInfoExist.setSubscribeTotalShare(subscribeTotalShare);//认购初始总份额
			BigDecimal redemptionTotalShare=redemptionServiceMapper.queryAlreadyRedemptionTotalShare(param);
			redemptionInfoExist.setAlreadyRedemptionTotalShare(redemptionTotalShare);//已赎回总份额
			Double remainingTotalShare=subscribeTotalShare.doubleValue()-redemptionTotalShare.doubleValue()-RedemptionInfoSec.getRedemptionTotalShare().doubleValue();
			redemptionInfoExist.setRemainingTotalShare(new BigDecimal(remainingTotalShare.toString()));//剩余全部份额
			com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(redemptionInfoExist, loginInfo);
			redemptionInfoMapper.updateByPrimaryKey(redemptionInfoExist);
			
			//逻辑删除掉赎回信息交易信息表
			RedemptionTradeInfoExample  redemptionTradeInfoExample=new RedemptionTradeInfoExample();
		   	redemptionTradeInfoExample.createCriteria().andRcStateEqualTo("E").andRedemptionInfoIdEqualTo(redemtionInfoId);
		   	List<RedemptionTradeInfo> existRedemptionTradeInfoList= redemptionTradeInfoMapper.selectByExample(redemptionTradeInfoExample);
		   	if (existRedemptionTradeInfoList.size()>0) {
				for (RedemptionTradeInfo redemptionTradeInfo : existRedemptionTradeInfoList) {
					com.sinosoft.util.BeanUtils.deleteObjectSetOperateInfo(redemptionTradeInfo, loginInfo);
					redemptionTradeInfoMapper.updateByPrimaryKey(redemptionTradeInfo);
				}	
			}
			//保存赎回信息交易详细表
			List<RedemptionTradeInfo> redemptionTradeInfoList = new ArrayList<RedemptionTradeInfo>();
			redemptionTradeInfoList = (List<RedemptionTradeInfo>)paramMap.get("custAllTradeTableInfo");
			List<RedemptionTradeInfo> saveRedemptionTradeInfoList = new ArrayList<RedemptionTradeInfo>();
			
			if (redemptionTradeInfoList.size()>0) {
				for (RedemptionTradeInfo redemptionTradeInfo : redemptionTradeInfoList) {
					Long seqRedemptionTradeInfoSeq=commonService.getSeqValByName("SEQ_T_REDEMPTION_TRADE_INFO");
					//赎回详细表(交易流水号、赎回份额)
					redemptionTradeInfo.setRedemptionTradeInfoId(seqRedemptionTradeInfoSeq);//赎回交易流水号
					redemptionTradeInfo.setRedemptionInfoId(redemtionInfoId);//赎回主表流水号
					saveRedemptionTradeInfoList.add(redemptionTradeInfo);
					if (existRedemptionTradeInfoList.size()>0) {
						com.sinosoft.util.BeanUtils.deleteAndInsertObjectSetOperateInfo(existRedemptionTradeInfoList.get(0), redemptionTradeInfo, loginInfo);
					}else {
						com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(redemptionTradeInfo, loginInfo);
					}
					redemptionTradeInfoMapper.insert(redemptionTradeInfo);
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(redemtionInfoId);
			resultInfo.setMsg("赎回保存信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("赎回保存信息失败");
		}
		
		return resultInfo;
	
	}

	public ResultInfo commitRedemptionCheck(String redemptionInfoId,LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		//校验该赎回是否已经上传影像件
//		DefFileInfoExample defFileInfoExample=new DefFileInfoExample();
//		defFileInfoExample.createCriteria().andRcStateEqualTo("E").andBusinessNoEqualTo(new Long(redemptionInfoId)).andBusinessTypeEqualTo("06");//06-赎回申请单
//		List<DefFileInfo> fileInfoList=defFileInfoMapper.selectByExample(defFileInfoExample);
//		if (fileInfoList.size()>0) {
			//提交至复核岗
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRcStateEqualTo("E").andRedemptionInfoIdEqualTo(new Long(redemptionInfoId));
			RedemptionInfo redemptionInfo=redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
			redemptionInfo.setRedemptionStatus("02");//待复核
			redemptionInfoMapper.updateByPrimaryKey(redemptionInfo);
			//赎回申请触发邮件
			resultInfo = sendRedemptionEmail(redemptionInfoId,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("提交确认成功");
			
//		}
//		else {
//			resultInfo.setSuccess(false);
//			resultInfo.setMsg("请先上传赎回申请单影像件，在进行提交确认");
//		}
		return resultInfo;
	}

	/**
	 * 发送赎回申请邮件
	 * @param tradeCallBackInfo
	 * @return
	 */
	@Transactional
	private ResultInfo sendRedemptionEmail(String redemptionInfoId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//先创建短信，然后发送
		resultInfo = createRedemptionEmail(redemptionInfoId,loginInfo);
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		List<SysEmailInfo> sysEmailInfoList = (List<SysEmailInfo>)resultInfo.getObj();
		//发送短信
		for (SysEmailInfo sysEmailInfo2 : sysEmailInfoList) {
			resultInfo = sendRedemptionEmail(sysEmailInfo2,loginInfo);
		}
		
		if(!resultInfo.isSuccess()){
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	
	/**
	 * 创建赎回申请邮件
	 * @param tradeInfo
	 * @param tradeCallBackInfo
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	private ResultInfo createRedemptionEmail(String redemptionInfoId,LoginInfo loginInfo){
		ResultInfo resultInfo = new ResultInfo();
		//获取交易回访邮件所需数据
		Map paramMap = new HashMap();
		paramMap.put("redemptionInfoId", redemptionInfoId);
		Map<String, String> redemptionEmailData = redemptionServiceMapper.getRedemtionEmailData(paramMap);
		DefUser userName = defUserMapper.selectByPrimaryKey(loginInfo.getUserId());
		String loginUserName = userName.getUserName();
		redemptionEmailData.put("loginUserName", loginUserName);
		if(redemptionEmailData==null||redemptionEmailData.size()==0){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到交易赎回相关数据，创建邮件失败！");
			return resultInfo;
		}
		//邮件信息
		SysEmailInfo sysEmailInfo = new SysEmailInfo();
		//交易复核通知邮件
		sysEmailInfo.setEmailType("03");
		sysEmailInfo.setEmailRelationNo(Long.parseLong(redemptionInfoId));
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
				sysEmailInfo1.setEmailType("03");
				sysEmailInfo1.setEmailTitle("交易赎回审核通知");
				sysEmailInfo1.setEmailAddress(email.get(i).getEmailAddress());
		//创建邮件内容
			String mailContent = createRedemptionEmailContent(redemptionEmailData);
			sysEmailInfo1.setEmailContent(mailContent);
		//01-未发送
		sysEmailInfo1.setEmailStatus("01");
		sysEmailInfo1.setEmailCreateTime(DateUtils.getCurrentTimestamp());
		BeanUtils.insertObjectSetOperateInfo(sysEmailInfo1, loginInfo);
		/*Long sysEmailInfoId = commonService.getSeqValByName("SEQ_T_SYS_EMAIL_INFO");
		sysEmailInfo.setSysEmailInfoId(sysEmailInfoId);*/
		sysEmailInfoMapper.insert(sysEmailInfo1);
		resultList.add(sysEmailInfo1);
				}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("交易赎回邮件创建成功！");
		resultInfo.setObj(resultList);
		return resultInfo;
	}
	
	
	/**
	 * 生成赎回申请邮件类容
	 * @param callBackEmailData
	 * @return
	 */
	private String createRedemptionEmailContent(Map<String,String> redemptionEmailData){
		StringBuffer mailContent = new StringBuffer();

		mailContent.append("用户“");
		mailContent.append(redemptionEmailData.get("loginUserName"));
		mailContent.append("”于“");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		mailContent.append(df.format(new Date()));
		mailContent.append("”提交赎回《");
		mailContent.append(redemptionEmailData.get("productName"));
		mailContent.append("》产品“");
		mailContent.append(redemptionEmailData.get("redemptionShares"));
		mailContent.append("”，请审核。");
		return mailContent.toString();
	}
	/**
	 * 发送邮件
	 * @return
	 */
	@Transactional
	private ResultInfo sendRedemptionEmail(SysEmailInfo sysEmailInfo,LoginInfo loginInfo){
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
			//20170910调整
			resultInfo.setSuccess(true);
			//resultInfo = sendEmailService.sendEmail(address, title, content, mailHostParam);
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid queryRedemptionConfirmList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo) {

		if (paramMap == null) {
			paramMap = new HashMap();
		}
	    paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
	    paramMap.put("userId", loginInfo.getUserId());
	    paramMap.put("operComId", loginInfo.getComId());
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		paramMap.put("custNo", paramMap.get("custNo"));
		paramMap.put("productId", paramMap.get("productId"));
		paramMap.put("agentId", paramMap.get("agentId"));
		// 获取符合条件的行数
		Integer total = redemptionServiceMapper.queryRedemptionConfirmListCount(paramMap);
		List<Map> resultList = redemptionServiceMapper.queryRedemptionConfirmListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo getRedemptionConfirmInfo(String redemptionInfoId,LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		Map paramMap=new HashMap<String,String>();
		paramMap.put("redemptionInfoId", redemptionInfoId);
		
		Map<String, Object>  receptionInfoMap=new HashMap<String, Object>();
		if (redemptionInfoId!=null) {
			//获取赎回客户产品信息
			List<Map>  redemptionCustProInfo=redemptionServiceMapper.getRedemptionCustProInfo(paramMap);
			if (redemptionCustProInfo.size()>0) {
				receptionInfoMap.put("redemptionCustProInfo", redemptionCustProInfo);
			}
			
			//获取赎回信息
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andRcStateEqualTo("E");
			RedemptionInfo redemptionInfo=(RedemptionInfo)redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
			paramMap.put("productId", redemptionInfo.getPdProductId().toString());
			//获取参考净值对应的开放日
			List<Map> redemptionInfoNetInfo=redemptionServiceMapper.queryNetValueByOpenDay(paramMap);
			if (redemptionInfoNetInfo.size()>0) {
				receptionInfoMap.put("redemptionInfoNetInfo",redemptionInfoNetInfo);
			}
			
			if (redemptionInfo!=null) {
				receptionInfoMap.put("redemptionInfo", JsonUtils.objectToMap(redemptionInfo));
			}
			//获取赎回交易明细信息		
			List<Map> redemptionInfoList= redemptionServiceMapper.getUpdateTradeRedemptionInfo(paramMap);
			
			if (redemptionInfoList.size()>0) {
				receptionInfoMap.put("redemptionInfoList", redemptionInfoList);
			}
			
			resultInfo.setObj(receptionInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取赎回信息成功");
		}
		else
		{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息失败");
		}
	  return resultInfo;
	}

	
	@SuppressWarnings("rawtypes")
	public ResultInfo saveRedemptionConfirmCheckInfo(Map paramMap, LoginInfo loginInfo) {
		
		ResultInfo resultInfo=new ResultInfo();
		String redemptionInfoId=paramMap.get("redemptionInfoId").toString();
		RedemptionOperation redemptionOperation=(RedemptionOperation)paramMap.get("redemptionConfirmCheckInfo");
		try {
			if (redemptionInfoId!=null) {
				
				RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
				redemptionInfoExample.createCriteria().andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andRcStateEqualTo("E").andRedemptionStatusEqualTo("05");
				List redemptionInfoExistList=redemptionInfoMapper.selectByExample(redemptionInfoExample);
				if (redemptionInfoExistList.size()>0) {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("该赎回信息已经撤销，无法再提交确认");
					return resultInfo;
				}
			}
			
			if (redemptionInfoId!=null&&redemptionOperation!=null) {
				//更新赎回信息主表
				RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
				redemptionInfoExample.createCriteria().andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andRcStateEqualTo("E");
				RedemptionInfo redemptionInfo=(RedemptionInfo)redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
				redemptionInfo.setRedemptionCheckOperator(loginInfo.getUserCode());
				String conclusion= redemptionOperation.getConclusion();
				//复核通过，状态改为03-确认中
				if (conclusion.equals("01")) {
					redemptionInfo.setRedemptionStatus("03");
					//redemptionOperation.setIsback("N");
				}
				//复核不通过，状态仍为02-复核中
				if (conclusion.equals("02")) {
					redemptionInfo.setRedemptionStatus("02");
					//redemptionOperation.setIsback("N");
				}
				//退回赎回预约，状态改为01-预约中
				if (conclusion.equals("03")) {
					redemptionInfo.setRedemptionStatus("01");
					//redemptionOperation.setIsback("Y");
				}
				com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(redemptionInfo, loginInfo);
				redemptionInfoMapper.updateByPrimaryKey(redemptionInfo);
				//插入赎回轨迹表
				String redemptionOperationId = "";
				Long seqRedemptionOpertation = null;
				if(paramMap.get("redemptionOperationId")!=null&&!"".equals(paramMap.get("redemptionOperationId"))){
					redemptionOperationId = (String)paramMap.get("redemptionOperationId");
					seqRedemptionOpertation = new Long(redemptionOperationId);
				}else {
					seqRedemptionOpertation = commonService.getSeqValByName("SEQ_T_REDEMPTION_OPERATION");
				}
				redemptionOperation.setRedemptionOperationId(seqRedemptionOpertation);
				redemptionOperation.setRedemptionInfoId(new Long(redemptionInfoId));
				redemptionOperation.setUserId(loginInfo.getUserId());
				//redemptionOperation.setOperateNode("02");//复核中节点
				com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(redemptionOperation, loginInfo);
				if (redemptionOperationId!=null&&!"".equals(redemptionOperationId)) {
					redemptionOperationMapper.updateByPrimaryKeySelective(redemptionOperation);
				}else {
					redemptionOperationMapper.insert(redemptionOperation);
				}
				
				resultInfo.setSuccess(true);
				resultInfo.setObj(redemptionOperation.getRedemptionOperationId().toString());
				resultInfo.setMsg("提交确认成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("提交确认失败");
		}
		return resultInfo;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid queryRedemptionUploadInfoList(DataGridModel dgm,Map paramMap, LoginInfo loginInfo) {

		if (paramMap == null) {
			paramMap = new HashMap();
		}
	    paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
	    paramMap.put("userId", loginInfo.getUserId());
	    paramMap.put("operComId", loginInfo.getComId());
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		paramMap.put("custNo", paramMap.get("custNo"));
		paramMap.put("productId", paramMap.get("productId"));
		// 获取符合条件的行数
		Integer total = redemptionServiceMapper.queryRedemptionUploadInfoListCount(paramMap);
		List<Map> resultList = redemptionServiceMapper.queryRedemptionUploadInfoListPage(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultInfo getRedemptionUploadInfo(String redemptionInfoId,LoginInfo loginInfo) {
		
		ResultInfo resultInfo=new ResultInfo();
		Map paramMap=new HashMap<String,String>();
		paramMap.put("redemptionInfoId", redemptionInfoId);
		
		Map<String, Object>  receptionInfoMap=new HashMap<String, Object>();
		if (redemptionInfoId!=null) {
			//获取赎回客户产品信息
			List<Map>  redemptionCustProInfo=redemptionServiceMapper.getRedemptionCustProInfo(paramMap);
			if (redemptionCustProInfo.size()>0) {
				receptionInfoMap.put("redemptionCustProInfo", redemptionCustProInfo);
			}
			
			//获取赎回信息
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andRcStateEqualTo("E");
			RedemptionInfo redemptionInfo=(RedemptionInfo)redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
			if (redemptionInfo!=null) {
				receptionInfoMap.put("redemptionInfo", JsonUtils.objectToMap(redemptionInfo));
			}
			
			paramMap.put("productId", redemptionInfo.getPdProductId().toString());
			//获取参考净值对应的开放日
			List<Map> redemptionInfoNetInfo=redemptionServiceMapper.queryNetValueByOpenDay(paramMap);
			if (redemptionInfoNetInfo.size()>0) {
				receptionInfoMap.put("redemptionInfoNetInfo",redemptionInfoNetInfo);
			}
			
			//获取赎回交易明细信息		
			List<Map> redemptionInfoList= redemptionServiceMapper.getUpdateTradeRedemptionInfo(paramMap);
			
			if (redemptionInfoList.size()>0) {
				receptionInfoMap.put("redemptionInfoList", redemptionInfoList);
			}
			resultInfo.setObj(receptionInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取赎回信息成功");
		}
		else
		{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息失败");
		}
	  return resultInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo saveRedemptionUploadInfo(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo=new ResultInfo();
		String redemptionInfoId=paramMap.get("redemptionInfoId").toString();
		RedemptionInfo redemptionInfo=(RedemptionInfo)paramMap.get("redemptionInfo");
		RedemptionOperation redemptionOperation=(RedemptionOperation)paramMap.get("redemptionOperation");
		List<RedemptionTradeInfo> redemptionTradeInfoList=(List<RedemptionTradeInfo>)paramMap.get("redemptionTradeInfoList");
		//校验该赎回是否已经撤销
		if (redemptionInfoId!=null) {
			
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andRcStateEqualTo("E").andRedemptionStatusEqualTo("05");
			List redemptionInfoExistList=redemptionInfoMapper.selectByExample(redemptionInfoExample);
			if (redemptionInfoExistList.size()>0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("该赎回信息已经撤销，无法再提交保存");
				return resultInfo;
			}
		}
		
		if (redemptionInfoId!=null&&redemptionInfo!=null&&redemptionOperation!=null&&redemptionTradeInfoList.size()>0) {
			
			//更新赎回信息主表
			RedemptionInfoExample redemptionInfoExample=new RedemptionInfoExample();
			redemptionInfoExample.createCriteria().andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andRcStateEqualTo("E");
			RedemptionInfo redemptionInfoExist=(RedemptionInfo)redemptionInfoMapper.selectByExample(redemptionInfoExample).get(0);
			redemptionInfoExist.setRedemptionUploadOperator(loginInfo.getUserCode());//插入上传明细操作UserCode
			redemptionInfoExist.setActuRedemptionNetValue(redemptionInfo.getActuRedemptionNetValue());//实际净值
			redemptionInfoExist.setActuRedemptionTotalShare(redemptionInfo.getActuRedemptionTotalShare());//实际总份额
			redemptionInfoExist.setActuRedemptionTotalMoney(redemptionInfo.getActuRedemptionTotalMoney());//实际总金额
			
			Map param=new HashMap();
			param.put("custNo", redemptionInfoExist.getCustomerNo());
			param.put("productId", redemptionInfoExist.getPdProductId());
			
			BigDecimal subscribeTotalShare=redemptionServiceMapper.querysubscribeTotalShare(param);
			BigDecimal redemptionTotalShare=redemptionServiceMapper.queryAlreadyRedemptionTotalShare(param);

			Double remainingTotalShare=subscribeTotalShare.doubleValue()-redemptionTotalShare.doubleValue()-redemptionInfo.getActuRedemptionTotalShare().doubleValue();
			redemptionInfoExist.setRemainingTotalShare(new BigDecimal(remainingTotalShare.toString()));//更新剩余全部份额
			
			String conclusion= redemptionOperation.getConclusion();
			//确认通过，状态改为03-确认中
			if (conclusion.equals("01")) {
				redemptionInfoExist.setRedemptionStatus("04");
				redemptionOperation.setIsback("N");
			}
			//不通过，状态仍为02-确认中
			if (conclusion.equals("02")) {
				redemptionInfoExist.setRedemptionStatus("03");
				redemptionOperation.setIsback("N");
			}
			//退回赎回预约，状态改为01-预约中
			if (conclusion.equals("03")) {
				redemptionInfoExist.setRedemptionStatus("02");
				redemptionOperation.setIsback("Y");
			}
			//更新赎回信息主表
			com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(redemptionInfoExist, loginInfo);
			redemptionInfoMapper.updateByPrimaryKey(redemptionInfoExist);
			
			//更新交易赎回表
			for (RedemptionTradeInfo redemptionTradeInfo : redemptionTradeInfoList) {
				RedemptionTradeInfoExample redemptionTradeInfoExample=new RedemptionTradeInfoExample();
				redemptionTradeInfoExample.createCriteria().andRcStateEqualTo("E").andRedemptionInfoIdEqualTo(new Long(redemptionInfoId)).andTradeInfoIdEqualTo(redemptionTradeInfo.getTradeInfoId());
				RedemptionTradeInfo existRedemptionTradeInfo=(RedemptionTradeInfo)redemptionTradeInfoMapper.selectByExample(redemptionTradeInfoExample).get(0);
				existRedemptionTradeInfo.setActualRedemptionShare(redemptionTradeInfo.getActualRedemptionShare());//存放实际份额
				//existRedemptionTradeInfo.setActualRedemptionMoney(new Long(Double.parseDouble(redemptionTradeInfo.getActualRedemptionShare().toString())*Double.parseDouble(redemptionInfo.getActuRedemptionNetValue().toString())));//存放实际金额=实际赎回份额*实际净值
				com.sinosoft.util.BeanUtils.updateObjectSetOperateInfo(existRedemptionTradeInfo, loginInfo);
				redemptionTradeInfoMapper.updateByPrimaryKey(existRedemptionTradeInfo);
			}
			
			String redemptionOperationId = "";
			Long seqRedemptionOpertation = null;
			if(paramMap.get("redemptionOperationId")!=null&&!"".equals(paramMap.get("redemptionOperationId"))){
				redemptionOperationId = (String)paramMap.get("redemptionOperationId");
				seqRedemptionOpertation = new Long(redemptionOperationId);
			}else {
				seqRedemptionOpertation = commonService.getSeqValByName("SEQ_T_REDEMPTION_OPERATION");
			}
			//插入赎回轨迹表
			//Long seqRedemptionOpertation = commonService.getSeqValByName("SEQ_T_REDEMPTION_OPERATION");
			redemptionOperation.setRedemptionOperationId(seqRedemptionOpertation);
			redemptionOperation.setRedemptionInfoId(new Long(redemptionInfoId));
			redemptionOperation.setUserId(loginInfo.getUserId());
			redemptionOperation.setOperateNode("03");//复核中节点
			com.sinosoft.util.BeanUtils.insertObjectSetOperateInfo(redemptionOperation, loginInfo);
			if (redemptionOperationId!=null&&!"".equals(redemptionOperationId)) {
				redemptionOperationMapper.updateByPrimaryKeySelective(redemptionOperation);
			}else {
				redemptionOperationMapper.insert(redemptionOperation);
			}
			resultInfo.setSuccess(true);
			resultInfo.setObj(redemptionOperation.getRedemptionOperationId().toString());
			resultInfo.setMsg("赎回明细上传成功");
			
			return resultInfo;
		}
		else 
		{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回明细上传信息出错，保存失败");
			return resultInfo;
			
		}
	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataGrid getRedemptionPrintList(DataGridModel dgm, Map paramMap) {

		if (paramMap==null) {
			paramMap = new HashMap();
		}
		paramMap.put("startIndex", dgm.getStartIndex());
		paramMap.put("endIndex", dgm.getEndIndex());
		Integer total = redemptionServiceMapper.getRedemptionPrintCount(paramMap);
		List<Map> resultList = redemptionServiceMapper.getRedemptionPrintList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo getActuRedemptionInfo(String redemptionInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map paramMap = new HashMap();
			paramMap.put("redemptionInfoId", redemptionInfoId);
			Map resultMap = redemptionServiceMapper.getActuRedemptionInfo(paramMap);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			return resultInfo;
		}
		resultInfo.setSuccess(true);
		return resultInfo;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, String>> redemtionProductQueryByCustNo(LoginInfo loginInfo,String CustNo) {
	    Map paramMap=new HashMap ();
	    paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
	    paramMap.put("userId", loginInfo.getUserId());
	    paramMap.put("operComId", loginInfo.getComId());
	    paramMap.put("custNo", CustNo);
	    List<Map<String, String>> productMapList = new ArrayList<Map<String, String>>();
		List<Map> productMapByCustNoList =redemptionServiceMapper.prodcutBycustNoQuery(paramMap);
		
		if (productMapByCustNoList.size()>0) {
			for (Map map : productMapByCustNoList) {
				Map<String,String> custMap=new HashMap<String,String>();
				custMap.put("code", map.get("productId").toString());
				custMap.put("codeName", map.get("productName").toString());
				productMapList.add(custMap);
			}
			
		}
			
		return productMapList;
		}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultInfo getDetailActuRedemptionInfo(String redemptionInfoId) {
		
		ResultInfo resultInfo=new ResultInfo();
		Map paramMap=new HashMap<String,String>();
		Map<String, Object>  receptionInfoMap=new HashMap<String, Object>();
		if (redemptionInfoId!=null) {
			paramMap.put("redemptionInfoId", redemptionInfoId);
			//获取赎回结论信息
			List<Map>  redemptionDetailUploadConclusion=redemptionServiceMapper.getDetailRedemptionUploadConclusion(paramMap);
//			if (redemptionDetailUploadConclusion!=null&&redemptionDetailUploadConclusion.size()>0) {
//				receptionInfoMap.put("redemptionDetailUploadConclusion", redemptionDetailUploadConclusion);
//			}
			receptionInfoMap.put("redemptionDetailUploadConclusion", redemptionDetailUploadConclusion);
			//获取赎回实际参数
			List<Map>  redemptionDetailActuPrem=redemptionServiceMapper.getDetailRedemptionActuPrem(paramMap);
			if (redemptionDetailActuPrem!=null&&redemptionDetailActuPrem.size()>0) {
				receptionInfoMap.put("redemptionDetailActuPrem", redemptionDetailActuPrem);
			}
			
			resultInfo.setObj(receptionInfoMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("获取赎回上传明细信息成功");
		}
		else
		{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取赎回信息失败");
		}
	  return resultInfo;
	}

	
	
	public ResultInfo queryCustomerInfo(String custNo) {
		ResultInfo resultInfo=new ResultInfo();
		resultInfo.setSuccess(true);
		resultInfo.setObj("");
		if (custNo!=null) {
			CustBaseInfoExample  custBaseInfoExample=new CustBaseInfoExample();
			custBaseInfoExample.createCriteria().andRcStateEqualTo("E").andCustomerNoEqualTo(custNo);
			if (custBaseInfoMapper.selectByExample(custBaseInfoExample).size()>0) {
				CustBaseInfo custBaseInfo=(CustBaseInfo)custBaseInfoMapper.selectByExample(custBaseInfoExample).get(0);
				resultInfo.setObj(custBaseInfo.getIdNo());
				resultInfo.setSuccess(true);
				return resultInfo;
			}
			return resultInfo;
		}
		return resultInfo;
	}
	
}
