package com.fms.service.trade.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.controller.trade.FundsShareController;
import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.CustBaseInfoMapper;
import com.fms.db.mapper.CustQuestionnaireInfoMapper;
import com.fms.db.mapper.PDProductMapper;
import com.fms.db.mapper.PdIncomeDisDetailMapper;
import com.fms.db.mapper.PdIncomeDisMapper;
import com.fms.db.mapper.TradeCustInfoMapper;
import com.fms.db.mapper.TradeFundsShareChangeMapper;
import com.fms.db.mapper.TradeInfoMapper;
import com.fms.db.mapper.TradeOperationMapper;
import com.fms.db.mapper.TradeOperationTraceMapper;
import com.fms.db.mapper.TradeProductInfoMapper;
import com.fms.db.mapper.TradeStatusInfoMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.CustQuestionnaireInfoExample;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PdIncomeDis;
import com.fms.db.model.PdIncomeDisDetail;
import com.fms.db.model.TradeCustInfo;
import com.fms.db.model.TradeCustInfoExample;
import com.fms.db.model.TradeFundsShareChange;
import com.fms.db.model.TradeFundsShareChangeExample;
import com.fms.db.model.TradeInfo;
import com.fms.db.model.TradeInfoExample;
import com.fms.db.model.TradeOperation;
import com.fms.db.model.TradeOperationExample;
import com.fms.db.model.TradeOperationTrace;
import com.fms.db.model.TradeProductInfoExample;
import com.fms.db.model.TradeStatusInfo;
import com.fms.db.model.TradeStatusInfoExample;
import com.fms.service.mapper.FundsShareServiceMapper;
import com.fms.service.mapper.IncomeDisServiceMapper;
import com.fms.service.mapper.ProductServiceMapper;
import com.fms.service.mapper.TradeServiceMapper;
import com.fms.service.product.impl.IncomeDisServiceImpl;
import com.fms.service.trade.FundsShareService;
import com.sinosoft.core.db.mapper.DefComMapper;
import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.core.spring.quartz.Constant;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.NumberUtils;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.SimpleMoneyFormat;

import net.sf.ehcache.util.ProductInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FundsShareServiceImpl implements FundsShareService {
	private static final Logger log = Logger.getLogger(FundsShareController.class);
	@Autowired
	private FundsShareServiceMapper fundsShareServiceMapper;
	@Autowired
	private TradeInfoMapper tradeInfoMapper;
	@Autowired
	private TradeServiceMapper tradeServiceMapper;
	@Autowired
	private TradeOperationMapper tradeOperationMapper;
	@Autowired
	private TradeStatusInfoMapper tradeStatusInfoMapper;
	@Autowired
	private TradeOperationTraceMapper tradeOperationTraceMapper;
	@Autowired
	private TradeFundsShareChangeMapper tradeFundsShareChangeMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private DefComMapper defComMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private TradeCustInfoMapper tradeCustInfoMapper;
	@Autowired
	private TradeProductInfoMapper tradeProductInfoMapper;
	@Autowired
	private CustQuestionnaireInfoMapper custQuestionnaireInfoMapper;
	
	@Autowired
	private IncomeDisServiceMapper incomeDisServiceMapper;
	
	@Autowired
	private PdIncomeDisMapper pdIncomeDisMapper;
	
	@Autowired
	private PdIncomeDisDetailMapper pdIncomeDisDetailMapper;
	
	@Autowired
	private PDProductMapper pdProductMapper;
	@Autowired
	private CustBaseInfoMapper custBaseInfoMapper;
	
	private static final Logger logger = Logger.getLogger(IncomeDisServiceImpl.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid queryFundsList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo) {
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		//权限控制，当前登录
//		AgentExample agentExample=new AgentExample();
//		agentExample.createCriteria().andUserIdEqualTo(loginInfo.getUserId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
//		List<Agent> agentList=agentMapper.selectByExample(agentExample);
//		Long agentId=agentList.get(0).getAgentId();
		// paramMap.put("agentId", agentId);
		//
		// Long loginUserId=loginInfo.getUserId();

		// 1、获取总条数
		Integer total = fundsShareServiceMapper.getTradeFundsTotal(paramMap);

		// 2、获取所有实体
		List<Map<String, String>> resultList = fundsShareServiceMapper.getTradeFundsServiceList(paramMap);
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;

	}
/**
 * @author 燕
 * 
 *获取基金份额可受让信息列表
 */
	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public DataGrid queryFundsTranFereeList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex", startIndex);
		paramMap.put("endIndex", dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		String loginUserId=loginInfo.getUserId().toString();
		List<Map<String,String>> resultMapList = new ArrayList<Map<String,String>>();
		Integer total = 0;
		if(!loginUserId.equals("1"))
		{
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andUserIdEqualTo(loginInfo.getUserId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String loginAgentId=agentList.get(0).getAgentId().toString();
			paramMap.put("agentId", loginAgentId);
			//1、获取可受让总记录数
			 total = fundsShareServiceMapper.getTradeFundsTransFereeTotal(paramMap);
			
			//2、获取可受让总记录
			 resultMapList = fundsShareServiceMapper.getTradeFundsTransFereeServiceList(paramMap);
		}
		else
		{
			//1、获取可受让总记录数
			 total = fundsShareServiceMapper.getTradeFundsTransFereeTotal(paramMap);
			
			//2、获取可受让总记录
			 resultMapList = fundsShareServiceMapper.getTradeFundsTransFereeServiceList(paramMap);
		}
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(resultMapList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	/**
	 * @author 燕
	 * 
	 *初始化基金份额受让页面理财经理等信息
	 */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   @Override
   public ResultInfo getTradeTransFereeInfo(Map paramMap, LoginInfo loginInfo)
   {
	   ResultInfo resultInfo = new ResultInfo();
		Map resultMap = new HashMap();
		try {
			String tradeFundsShareChangeId=paramMap.get("tradeFundsShareChangeId").toString();
			String productId = paramMap.get("productId").toString();
			String orTradeId=paramMap.get("orTradeId").toString();
			String tradeStatus=paramMap.get("tradeStatus").toString();
			String comId=loginInfo.getComId().toString();
			//当交易状态为审核退回
			if(tradeStatus.equals("14")||tradeStatus.equals("12")){
				//获取现交易信息
				String tradeInfoId=paramMap.get("tradeInfoId").toString();
				if(tradeInfoId==null&&"".equals(tradeInfoId))
				{
					resultInfo.setSuccess(false);
					resultInfo.setMsg("获取现交易信息出错,未获取到现交易相关信息");
					return resultInfo;
				}
				TradeInfo tradeInfo=tradeInfoMapper.selectByPrimaryKey(new Long(tradeInfoId));
				resultMap.put("tradeNo", tradeInfo.getTradeNo());
				resultMap.put("tradeInfoNo", tradeInfo.getTradeInfoNo());
				resultMap.put("tradeDate", DateUtils.getDateString(tradeInfo.getTradeDate()));
				resultMap.put("tradeComId", tradeInfo.getTradeComId());
			}
			if (comId==null||"".equals(comId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取机构和产品信息出错,未获取到机构相关信息");
				return resultInfo;
			}
			DefCom defCom = defComMapper.selectByPrimaryKey(new Long(comId));
			resultMap.put("comId", comId);
			resultMap.put("comName", defCom.getComName());
			//获取产品相关信息
			if (productId==null||"".equals(productId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品信息出错,未获取到产品相关信息");
				return resultInfo;
			}
			List<Map> productMapList=fundsShareServiceMapper.getProductMapList(paramMap);
			resultMap.put("productMapList", productMapList);
			//获取转让交易金额
			if(tradeFundsShareChangeId==null&&"".equals(tradeFundsShareChangeId))
			{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取转让金额信息出错,未获取到转让金额相关信息");
				return resultInfo;
			}
			TradeFundsShareChange fundsShareChange=tradeFundsShareChangeMapper.selectByPrimaryKey(new Long(tradeFundsShareChangeId));
			resultMap.put("transferAsset", fundsShareChange.getTransferAsset().toString());
			resultMap.put("chnMonTotalAssets",SimpleMoneyFormat.formatChineseCapital(fundsShareChange.getTransferAsset().doubleValue()));
			//根据用户id获取现理财经理信息
			try{
				AgentExample agentExample = new AgentExample();
				AgentExample.Criteria agentCriteria = agentExample.createCriteria();
				agentCriteria.andUserIdEqualTo(loginInfo.getUserId()).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<Agent> agentList = agentMapper.selectByExample(agentExample);
				Agent agent = new Agent();
				if (agentList != null && agentList.size() > 0) {
					agent = agentList.get(0);
					resultMap.put("agentId",agent.getAgentId().toString());
					resultMap.put("agentName",agent.getAgentName());
					resultInfo.setSuccess(true);
				} else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("您不是财富顾问！");
					return resultInfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取现财富顾问信息出错");
			}
			//获取原理财经理信息
			if (orTradeId!=null&&!"".equals(orTradeId)) {
				TradeInfo orTradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(orTradeId));
				Agent agent1 = agentMapper.selectByPrimaryKey(orTradeInfo.getAgentId());
				resultMap.put("orAgentId",agent1.getAgentId().toString());
				resultMap.put("orAgentName",agent1.getAgentName());
				resultInfo.setSuccess(true);
			}
			else {
					resultInfo.setSuccess(false);
					resultInfo.setMsg("获取原理财经理信息出错！");
					return resultInfo;
				 }
			
		
			resultInfo.setObj(resultMap);
			
		} catch(IndexOutOfBoundsException ee){
			ee.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("您不是财富顾问！");
		}catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取机构和产品信息出错,"+e.getMessage());
		}
		return resultInfo;
   }
   
   /**
    * 转让审核界面
    */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   @Override
   public ResultInfo getTransferAuditProInfo(Map paramMap, LoginInfo loginInfo)
   {
	   ResultInfo resultInfo = new ResultInfo();
		Map resultMap = new HashMap();
			String tradeFundsShareChangeId=paramMap.get("tradeFundsShareChangeId").toString();
			String productId = paramMap.get("productId").toString();
			//获取产品相关信息
			if (productId==null||"".equals(productId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品信息出错,未获取到产品相关信息");
				return resultInfo;
			}
			List<Map> productMapList=fundsShareServiceMapper.getProductMapList(paramMap);
			resultMap.put("productMapList", productMapList);
			//获取转让交易金额
			if(tradeFundsShareChangeId==null&&"".equals(tradeFundsShareChangeId))
			{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取转让金额信息出错,未获取到转让金额相关信息");
				return resultInfo;
			}
			TradeFundsShareChange fundsShareChange=tradeFundsShareChangeMapper.selectByPrimaryKey(new Long(tradeFundsShareChangeId));
			resultMap.put("transferAsset", fundsShareChange.getTransferAsset().toString());
			resultMap.put("chnMonTotalAssets",SimpleMoneyFormat.formatChineseCapital(fundsShareChange.getTransferAsset().doubleValue()));
			resultInfo.setObj(resultMap);
			resultInfo.setSuccess(true);
		    return resultInfo;
   }
   
   /**
	 * 获取财富顾问信息
	 * 
	 * @param long1
	 * @return
	 */
	private ResultInfo getAgentInfoByUserId(Long long1) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			AgentExample agentExample = new AgentExample();
			AgentExample.Criteria agentCriteria = agentExample.createCriteria();
			agentCriteria.andUserIdEqualTo(long1).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			Agent agent = new Agent();
			if (agentList != null && agentList.size() > 0) {
				agent = agentList.get(0);
				resultInfo.setObj(agent);
				resultInfo.setSuccess(true);
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到财富顾问相关信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取财富顾问信息出错");
		}

		return resultInfo;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getTradeDetailInfo(Map paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map<String, String> tradeDetailInfoMap = fundsShareServiceMapper.getTradeDetailInfo(paramMap);
			resultInfo.setSuccess(true);
			resultInfo.setObj(tradeDetailInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易详细信息出现异常！");
		}
		return resultInfo;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultInfo getTradeTotalAssets(String tradeInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		
		try {
			TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(Long.parseLong(tradeInfoId));
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("transferAsset", tradeInfo.getTradeTotalAssets().toString());
			resultInfo.setSuccess(true);
			resultInfo.setObj(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取交易详细信息出现异常！");
		}
		return resultInfo;
	}
	/**
	 * 保存基金份额转让信息
	 */
	@Override
	public ResultInfo saveFundsShareChangeInfo(TradeFundsShareChange tradeFundsShareChange, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeFundsShareChange == null || tradeFundsShareChange.getOriginTradeInfoId() == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易信息为空！");
			}else {
				//保存基金份额转让信息
				if(tradeFundsShareChange.getTradeFundsShareChangeId()==null){
					//判断此交易是否进行过份额转让
					TradeFundsShareChangeExample tradeFundsShareChangeExample = new TradeFundsShareChangeExample();
					TradeFundsShareChangeExample.Criteria criteria = tradeFundsShareChangeExample.createCriteria();
					criteria.andOriginTradeInfoIdEqualTo(tradeFundsShareChange.getOriginTradeInfoId())
					.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
					List<TradeFundsShareChange> existTradeFundsInfos = tradeFundsShareChangeMapper.selectByExample(tradeFundsShareChangeExample);
					if(existTradeFundsInfos!=null&&existTradeFundsInfos.size()>0){
						resultInfo.setSuccess(false);
						resultInfo.setMsg("该笔交易已经进行过份额转让，不能重复添加！");
						return resultInfo;
					}
					//创建主键ID
					Long tradeFundsShareChangeId = commonService.getSeqValByName("SEQ_T_TRADE_STATUS_INFO");
					tradeFundsShareChange.setTradeFundsShareChangeId(tradeFundsShareChangeId);
					//根据原交易ID获取部分信息
					TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(Long.parseLong(tradeFundsShareChange.getOriginTradeInfoId()));
					String originTradeNo = tradeInfo.getTradeNo();
					String originAgentId = tradeInfo.getAgentId().toString();
					tradeFundsShareChange.setOriginAgentId(originAgentId);
					tradeFundsShareChange.setOriginTradeNo(originTradeNo);
					//转让后设置交易状态为11，转让中
					tradeFundsShareChange.setTradeStatus("11");
					BeanUtils.insertObjectSetOperateInfo(tradeFundsShareChange,loginInfo);
					tradeFundsShareChangeMapper.insert(tradeFundsShareChange);
				}else{
					TradeFundsShareChange existFundsShareInfo = tradeFundsShareChangeMapper.selectByPrimaryKey(tradeFundsShareChange.getTradeFundsShareChangeId());
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existFundsShareInfo, tradeFundsShareChange, loginInfo);
					tradeFundsShareChangeMapper.updateByPrimaryKeySelective(tradeFundsShareChange);
				}
				//交易主表状态更新
				TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(Long.parseLong(tradeFundsShareChange.getOriginTradeInfoId()));
				tradeInfo.setTradeStaus(tradeFundsShareChange.getTradeStatus());
			/*	//将交易主表中认购金额进行扣除
				BigDecimal originTotalAssets = tradeInfo.getTradeTotalAssets();
				BigDecimal transferAssets = tradeFundsShareChange.getTransferAsset();
				BigDecimal remainAssets = originTotalAssets.subtract(transferAssets);
				tradeInfo.setTradeTotalAssets(remainAssets);*/
				BeanUtils.updateObjectSetOperateInfo(tradeInfo, loginInfo);
				tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
				resultInfo.setSuccess(true);
				resultInfo.setMsg("基金份额转让成功");
				resultInfo.setObj(tradeFundsShareChange);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("提交基金份额转让信息失败！");
		}
		return resultInfo;
	}
	/*
	 * 
	 * 金额转让交易基本信息新增
	 */
	@Override
	public ResultInfo saveTradeInfo(TradeInfo tradeInfo,LoginInfo loginInfo) {
		
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeInfo == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("交易基本信息为空，不能保存");
				return resultInfo;
			}

			if (tradeInfo.getAgentId() == null || tradeInfo.getAgentId().equals("")) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取财富顾问信息,不能保存");
				return resultInfo;
			}

			if (tradeInfo.getTradeInfoId() != null) {
				TradeInfoExample tradeInfoExample = new TradeInfoExample();
				tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeInfo.getTradeInfoId())
						.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeInfo> tradeInfoList = tradeInfoMapper.selectByExample(tradeInfoExample);
				if (tradeInfoList != null && tradeInfoList.size() > 0) {
					TradeInfo existTradeInfo = tradeInfoList.get(0);
					tradeInfo.setTradeInfoId(tradeInfo.getTradeInfoId());
					BeanUtils.deleteAndInsertObjectSetOperateInfo(existTradeInfo, tradeInfo, loginInfo);
					tradeInfoMapper.updateByPrimaryKeySelective(tradeInfo);
				}
			} else {
				String productType=tradeInfo.getTradeType();
				Long tradeInfoId = commonService.getSeqValByName("SEQ_T_TRADE_INFO");
				String tradeInfoNo = commonService.createTradeNo(productType);
			    tradeInfo.setTradeNo(tradeInfoNo);
				tradeInfo.setTradeInfoId(tradeInfoId);
				tradeInfo.setTradeStaus("01");
				tradeInfo.setTradeDate(new Date());//交易日期
				tradeInfo.setInputOperator(loginInfo.getUserCode());
				BeanUtils.insertObjectSetOperateInfo(tradeInfo, loginInfo);
				tradeInfoMapper.insertSelective(tradeInfo);
			}

			resultInfo.setSuccess(true);
			resultInfo.setMsg("受让交易信息保存成功！");
			resultInfo.setObj(tradeInfo);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	/*
	 * 
	 * 提交受让审核
	 */
	@Override
	@Transactional
	public HashMap saveTradeInput(HashMap paramMap,LoginInfo loginInfo) throws RuntimeException{
		String tradeId = (String) paramMap.get("tradeId");
		String tradeType = (String) paramMap.get("tradeType");
		String tradeInfoId = (String) paramMap.get("tradeInfoId");
		String userCode = (String) paramMap.get("userCode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		HashMap resultMap = new HashMap();
		try {
			// 校验该交易流水号对应是否存在记录
				if (tradeId != null && !tradeId.trim().isEmpty()) {
				log.info("存在交易流水号对应的记录，可以保存财富产品信息！");

				Long tradeIdDecimal = new Long(tradeInfoId);

				if (!checkTradeId(tradeIdDecimal)) {
					resultMap.put("success", "false");
					resultMap.put("msg", "请先保存交易信息！");
					return resultMap;
				}
				ResultInfo resultInfo = checkCustomerInfo(tradeIdDecimal);
				if (!resultInfo.isSuccess()) {
					resultMap.put("success", "false");
					resultMap.put("msg", resultInfo.getMsg());
					return resultMap;
				}
				if (tradeType != null && "1".equals(tradeType)) {
					if (!checkTradeAddressInfo(tradeIdDecimal)) {
						resultMap.put("success", "false");
						resultMap.put("msg", "请先保存交易地址信息或是地址信息已修改但未保存！");
						return resultMap;
					}
					//购买财富产品必须填写风控问卷
					if(!checkTradeCustomerQuestion(tradeIdDecimal)){
						resultMap.put("success", "false");
						resultMap.put("msg", "请填写客户风控问卷调查或风控问卷调查已过期！");
						return resultMap;
					}
				}
				if (!checkTradeAccInfo(tradeIdDecimal)) {
					resultMap.put("success", "false");
					resultMap.put("msg", "请先保存交易账户信息或是账户信息已修改但未保存！");
					return resultMap;
				}
				if (!checkProductInfo(tradeIdDecimal)) {
					resultMap.put("success", "false");
					resultMap.put("msg", "请先保存产品信息！");
					return resultMap;
				}
			} else {
				log.info("不存在交易流水号对应的记录，不可以保存财富产品信息！");
				resultMap.put("success", "false");
				resultMap.put("msg", "请先保存交易信息！");
				return resultMap;
			}
			//更新转让表信息
			TradeFundsShareChangeExample tradeFundsShareChangeExample = new TradeFundsShareChangeExample();
			tradeFundsShareChangeExample.createCriteria().andOriginTradeInfoIdEqualTo(tradeId).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<TradeFundsShareChange> tradeFundsShareChangeList = tradeFundsShareChangeMapper.selectByExample(tradeFundsShareChangeExample);
			TradeFundsShareChange tradeFundsShareChange = tradeFundsShareChangeList.get(0);
			tradeFundsShareChange.setTradeStatus("12");
			tradeFundsShareChange.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeFundsShareChange.setModifyUserId(userIdDecimal);
			tradeFundsShareChange.setTradeInfoId(tradeInfoId);
			//获取当前agentId
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andUserIdEqualTo(userIdDecimal).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<Agent> agentInfo = agentMapper.selectByExample(agentExample);
			String agentIdStr = agentInfo.get(0).getAgentId().toString();
			tradeFundsShareChange.setAgentId(agentIdStr);
			BeanUtils.updateObjectSetOperateInfo(tradeFundsShareChange, loginInfo);
			tradeFundsShareChangeMapper.updateByExample(tradeFundsShareChange, tradeFundsShareChangeExample);
			//更新原交易表中交易状态
			String orgTradeInfoId = tradeFundsShareChange.getOriginTradeInfoId();
			TradeInfo orgTradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(orgTradeInfoId));
			orgTradeInfo.setTradeStaus("12");
			orgTradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
			orgTradeInfo.setModifyUserId(userIdDecimal);
			TradeInfoExample orgTradeInfoExample = new TradeInfoExample();
			orgTradeInfoExample.createCriteria().andTradeInfoIdEqualTo(new Long(orgTradeInfoId));
			tradeInfoMapper.updateByExample(orgTradeInfo, orgTradeInfoExample);
			//更新现交易主表交易状态
			TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(new Long(tradeInfoId));
			tradeInfo.setTradeStaus("12");
			tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeInfo.setModifyUserId(userIdDecimal);
			TradeInfoExample tradeInfoExample = new TradeInfoExample();
			tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(new Long(tradeInfoId));
			tradeInfoMapper.updateByExample(tradeInfo, tradeInfoExample);
			resultMap.put("success", "true");
			resultMap.put("msg", "录入完毕！");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg", e.getMessage());
		}
		
		return resultMap;
	}
	
	/***********************************************交易录入验证****************************************/
	/**
	 * 校验是否存在该交易流水号对应的记录
	 * 
	 * @return
	 */
	private boolean checkTradeId(Long tradeIdDecimal) {
		TradeInfoExample tradeInfoExample = new TradeInfoExample();
		tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal);
		int resultCount = tradeInfoMapper.countByExample(tradeInfoExample);
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 校验是否存在该交易流水号对应的记录
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ResultInfo checkCustomerInfo(Long tradeIdDecimal) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setSuccess(true);
		TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeIdDecimal);
		String tradeType = tradeInfo.getTradeType();
		int resultCount = 0;

		if (tradeType != null && !"".equals(tradeType) && "1".equals(tradeType)) {
			// 财富产品
			TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
			TradeCustInfoExample.Criteria criteria = tradeCustInfoExample.createCriteria();
			criteria.andTradeInfoIdEqualTo(tradeIdDecimal).andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			resultCount = tradeCustInfoMapper.countByExample(tradeCustInfoExample);
		} else {
			// 保险产品
			Map paramMap = new HashMap();
			paramMap.put("roleType", "1");// 投保人
			paramMap.put("tradeInfoId", tradeIdDecimal.toString());
			resultCount = tradeServiceMapper.verifyTradeCustCount(paramMap);
			if (resultCount != 1) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("角色信息中未添加投保人信息或角色信息未提交！");
				return resultInfo;
			}
			Map paramMap1 = new HashMap();
			paramMap1.put("roleType", "0");// 被保人
			paramMap1.put("tradeInfoId", tradeIdDecimal.toString());
			resultCount = tradeServiceMapper.verifyTradeCustCount(paramMap1);
			if (resultCount < 1) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("角色信息中未添加被保人信息或角色信息未提交！");
				return resultInfo;
			}
		}
		if (resultCount > 0) {
			resultInfo.setSuccess(true);
		} else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请先保存客户信息！");
		}
		return resultInfo;
	}
	/**
	 * 校验是否存在该交易流水号对应的记录
	 * 
	 * @return
	 */
	private boolean checkProductInfo(Long tradeIdDecimal) {
		TradeProductInfoExample tradeProductInfoExample = new TradeProductInfoExample();
		tradeProductInfoExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		int resultCount = tradeProductInfoMapper.countByExample(tradeProductInfoExample);
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean checkTradeAccInfo(Long tradeIdDecimal) {
		TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeIdDecimal);
		Map paramMap = new HashMap();
		if (tradeInfo.getTradeType().equals("2")) {

			return true;
		}
		paramMap.put("tradeId", tradeIdDecimal.toString());
		paramMap.put("tradeType", tradeInfo.getTradeType());
		int resultCount = tradeServiceMapper.querySaveTradeBankCount(paramMap);
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean checkTradeAddressInfo(Long tradeIdDecimal) {
		TradeInfo tradeInfo = tradeInfoMapper.selectByPrimaryKey(tradeIdDecimal);
		Map paramMap = new HashMap();
		paramMap.put("tradeId", tradeIdDecimal.toString());
		paramMap.put("agentId", tradeInfo.getAgentId().toString());
		int resultCount = tradeServiceMapper.querySaveTradeAddressCount(paramMap);
		if (resultCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验客户风控问卷
	 * @param tradeIdDecimal
	 * @return
	 */
	public boolean checkTradeCustomerQuestion(long tradeInfoId){
		TradeCustInfoExample tradeCustInfoExample = new TradeCustInfoExample();
		TradeCustInfoExample.Criteria tradeCustInfoCriteria = tradeCustInfoExample.createCriteria();
		tradeCustInfoCriteria.andTradeInfoIdEqualTo(tradeInfoId)
							 .andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		List<TradeCustInfo> tradeCustInfoList = tradeCustInfoMapper.selectByExample(tradeCustInfoExample);
		if(tradeCustInfoList==null||tradeCustInfoList.size()<=0){
			return false;
		}
		CustQuestionnaireInfoExample custQuestionnaireInfoExample = new CustQuestionnaireInfoExample();
		CustQuestionnaireInfoExample.Criteria custQuestionnaireCriteria = custQuestionnaireInfoExample.createCriteria();
		custQuestionnaireCriteria.andCustBaseInfoIdEqualTo(tradeCustInfoList.get(0).getCustBaseInfoId())
								.andCustQuestionnaireStateEqualTo("01")
								//.andAgentIdEqualTo(tradeInfo.getAgentId())
								.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
		int count = custQuestionnaireInfoMapper.countByExample(custQuestionnaireInfoExample);
		if(count<=0){
			return false;
		}else{
			return true;
		}
	}
	/***********************************************交易录入验证结束****************************************/
	/**
	 * 提交审核
	 * @throws RuntimeException 
	 */
	@Override
	@Transactional
	public HashMap saveTradeAudit(HashMap paramMap,LoginInfo loginInfo) throws Exception {
		String tradeId = (String) paramMap.get("tradeId");
		String tradeType = (String) paramMap.get("tradeType");
		String userCode = (String) paramMap.get("userCode");
		String tradeConclusion = (String) paramMap.get("tradeConclusion");
		String tradeOperationNode = (String) paramMap.get("tradeOperationNode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		String tradeFundsShareChangeId = (String) paramMap.get("tradeFundsShareChangeId");
		String productId = (String)paramMap.get("productId");

		// 校验该交易流水号对应是否存在记录
		HashMap resultMap = new HashMap();
		if (tradeId == null) {
			log.info("不存在交易流水号对应的记录，不可以保存财富产品信息！");
			resultMap.put("success", "false");
			resultMap.put("msg", "请先保存交易信息！");
			return resultMap;
		}
		try {
			TradeInfo tradeInfo = new TradeInfo();
			tradeInfo.setTradeStaus(Constants.TRADE_STATUS_CHECK);
			//审核通过
			if ("01".equals(tradeConclusion)) {
				//若为准客户 则需更新客户状态并添加转换时间
				Map queryMap = new HashMap();
				queryMap.put("tradeInfoId", tradeId);
				Map custInfo= fundsShareServiceMapper.getCustLevel(queryMap);
				if ("02".equals(custInfo.get("custLevel"))) {
					String custBaseInfoId = custInfo.get("custBaseInfoId").toString();
					CustBaseInfo custBaseInfo = custBaseInfoMapper
							.selectByPrimaryKey(new Long(custBaseInfoId));
					custBaseInfo.setCustLevel("01");
					custBaseInfo.setPreCustConvertCustTime(DateUtils.getCurrentTimestamp());
					BeanUtils.insertObjectSetOperateInfo(custBaseInfo, loginInfo);
					custBaseInfoMapper.updateByPrimaryKey(custBaseInfo);
				}
				//更新交易主表
				tradeInfo.setTradeStaus("10");
				tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				tradeInfo.setModifyUserId(userIdDecimal);
				TradeInfoExample tradeInfoExample = new TradeInfoExample();
				tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(new Long(tradeId));
				tradeInfoMapper.updateByExampleSelective(tradeInfo, tradeInfoExample);
				//更新原交易主表信息
				TradeFundsShareChange tradeFundsShareChangeInfo = tradeFundsShareChangeMapper
						.selectByPrimaryKey(Long.parseLong(tradeFundsShareChangeId));
				Long orTradeInfoId = Long.parseLong(tradeFundsShareChangeInfo.getOriginTradeInfoId());
				TradeInfo orTradeInfo = tradeInfoMapper.selectByPrimaryKey(orTradeInfoId);
				orTradeInfo.setTradeStaus("13");
				BigDecimal originTotalAssets = orTradeInfo.getTradeTotalAssets();
				BigDecimal transferAssets = tradeFundsShareChangeInfo.getTransferAsset();
				BigDecimal remainAssets = originTotalAssets.subtract(transferAssets);
				orTradeInfo.setTradeTotalAssets(remainAssets);
				orTradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				orTradeInfo.setModifyUserId(userIdDecimal);
				TradeInfoExample orTradeInfoExample = new TradeInfoExample();
				orTradeInfoExample.createCriteria().andTradeInfoIdEqualTo(orTradeInfoId);
				tradeInfoMapper.updateByExampleSelective(orTradeInfo, orTradeInfoExample);
				//更新基金份额转让主表
				tradeFundsShareChangeInfo.setTradeStatus("13");
				tradeFundsShareChangeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				tradeFundsShareChangeInfo.setModifyUserId(userIdDecimal);
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date currentDate = sf.parse(sf.format(new Date()));
				tradeFundsShareChangeInfo.setValueDate(currentDate);
				TradeFundsShareChangeExample tradeFundsShareChangeExample = new TradeFundsShareChangeExample();
				tradeFundsShareChangeExample.createCriteria().
				andTradeFundsShareChangeIdEqualTo(Long.parseLong(tradeFundsShareChangeId));
				tradeFundsShareChangeMapper.updateByExample(tradeFundsShareChangeInfo, tradeFundsShareChangeExample);
				//更新原交易状态维护表
				TradeStatusInfoExample tradeStatusInfoExample = new TradeStatusInfoExample();
				tradeStatusInfoExample.createCriteria().andTradeInfoIdEqualTo(orTradeInfoId)
				.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
				List<TradeStatusInfo> tradeStatusInfoList = tradeStatusInfoMapper.selectByExample(tradeStatusInfoExample);
				TradeStatusInfo orgTradeStatusInfo = tradeStatusInfoList.get(0);
				orgTradeStatusInfo.setActuSubscriptionAmount(remainAssets);
				orgTradeStatusInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				orgTradeStatusInfo.setModifyUserId(userIdDecimal);
				orgTradeStatusInfo.setTradeStatus("13");
				tradeStatusInfoMapper.updateByExample(orgTradeStatusInfo, tradeStatusInfoExample);
				//新增交易状态维护
				TradeStatusInfo tradeStatusInfo= new TradeStatusInfo();
				tradeStatusInfo.setTradeInfoId(new Long(tradeId));
				tradeStatusInfo.setStatusSerialNo(new Long(1));
				tradeStatusInfo.setActuSubscriptionAmount(transferAssets);
				tradeStatusInfo.setTradeStatus("10");
				BeanUtils.insertObjectSetOperateInfo(tradeStatusInfo, loginInfo);
				tradeStatusInfoMapper.insert(tradeStatusInfo);
				
			}
			//审核退回
			else if ("02".equals(tradeConclusion)) {
				//更新交易主表
				tradeInfo.setTradeStaus("14");
				tradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				tradeInfo.setModifyUserId(userIdDecimal);
				TradeInfoExample tradeInfoExample = new TradeInfoExample();
				tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(new Long(tradeId));
				tradeInfoMapper.updateByExampleSelective(tradeInfo, tradeInfoExample);
				//更新原交易主表
				TradeFundsShareChange tradeFundsShareChangeInfo = tradeFundsShareChangeMapper
						.selectByPrimaryKey(Long.parseLong(tradeFundsShareChangeId));
				Long orTradeInfoId = Long.parseLong(tradeFundsShareChangeInfo.getOriginTradeInfoId());
				TradeInfo orTradeInfo = tradeInfoMapper.selectByPrimaryKey(orTradeInfoId);
				orTradeInfo.setTradeStaus("14");
				orTradeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				orTradeInfo.setModifyUserId(userIdDecimal);
				TradeInfoExample orTradeInfoExample = new TradeInfoExample();
				orTradeInfoExample.createCriteria().andTradeInfoIdEqualTo(orTradeInfoId);
				tradeInfoMapper.updateByExampleSelective(orTradeInfo, orTradeInfoExample);
				//更新基金份额转让主表
				tradeFundsShareChangeInfo.setTradeStatus("14");
				tradeFundsShareChangeInfo.setModifyDate(DateUtils.getCurrentTimestamp());
				tradeFundsShareChangeInfo.setModifyUserId(userIdDecimal);
				TradeFundsShareChangeExample tradeFundsShareChangeExample = new TradeFundsShareChangeExample();
				tradeFundsShareChangeExample.createCriteria().
				andTradeFundsShareChangeIdEqualTo(Long.parseLong(tradeFundsShareChangeId));
				tradeFundsShareChangeMapper.updateByExample(tradeFundsShareChangeInfo, tradeFundsShareChangeExample);
			}
			//交易轨迹表&交易操作
			if (!dealTradeOperation(paramMap)) {
				resultMap.put("success", "false");
				resultMap.put("msg", "交易轨迹保存失败！");
				return resultMap;
			}
			//触发交易清算
			PDProduct productInfo = pdProductMapper.selectByPrimaryKey(Long.parseLong(productId));
			String productSubType = productInfo.getProductSubType();
			if (productSubType.equals("02")) {
				String clearAnnounce = orgTradeClear(paramMap, loginInfo);
				logger.info("===========清算结论============="+clearAnnounce);
			}
			resultMap.put("success", "true");
			resultMap.put("msg", "审核成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 受让审核提交触发原交易产品收益清算
	 * @param paramMap
	 */
private String orgTradeClear(HashMap paramMap,LoginInfo loginInfo) throws Exception {
	ResultInfo resultInfo = new ResultInfo();
	try {	
			String tradeFundsShareChangeId = (String)paramMap.get("tradeFundsShareChangeId");
			List<Map> incomeDisProductList = incomeDisServiceMapper.getProductDisInfo(paramMap);
			if (incomeDisProductList==null||incomeDisProductList.size()==0) {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("收益分配结算完成！");
			}
			//获取原交易产品 要进行清算
			Map productIncomeDisInfo = incomeDisProductList.get(0);
			Long productId = (Long)productIncomeDisInfo.get("productId");
			String productName = (String)productIncomeDisInfo.get("productName");
			String distributeDate = (String)productIncomeDisInfo.get("distributeDate");
			String distributeStartDate = (String)productIncomeDisInfo.get("distributeStartDate");
			String distributeEndDate = (String)productIncomeDisInfo.get("distributeEndDate");
			//获取转让主表中的转让成立时间
			TradeFundsShareChange tradeFundsShareChange = tradeFundsShareChangeMapper
					.selectByPrimaryKey(Long.parseLong(tradeFundsShareChangeId));
			distributeEndDate = DateUtils.getDateString(tradeFundsShareChange.getValueDate());
			distributeDate = DateUtils.getDateString(tradeFundsShareChange.getValueDate());
			BigDecimal transferAmount = tradeFundsShareChange.getTransferAsset();
			BigDecimal principalDistributeRate = (BigDecimal)productIncomeDisInfo.get("principalDistributeRate");
			logger.info("===收益分配产品流水号及名称：==="+productId+"==="+productName);
			logger.info("===收益分配产品分配日期：==="+distributeDate);
			logger.info("===收益分配产品分配起期：==="+distributeStartDate);
			logger.info("===收益分配产品分配止期：==="+distributeEndDate);
			logger.info("===收益分配产品本金分配比率：==="+principalDistributeRate.toString());
			//获取该产品的所有交易信息
			List<Map> tradeInfoList = incomeDisServiceMapper.getTradeInfoByProductId(productIncomeDisInfo);
			Long pdIncomeDistributeId = null;
			PdIncomeDis pdIncomeDis = new PdIncomeDis();
			if (tradeInfoList!=null&&tradeInfoList.size()>0) {
				pdIncomeDistributeId = commonService.getSeqValByName("SEQ_T_PD_INCOME_DIS");
				pdIncomeDis.setPdIncomeDistributeId(pdIncomeDistributeId);
				pdIncomeDis.setPdProductId(productId);
				pdIncomeDis.setDistributePrincipalRate(principalDistributeRate);
				pdIncomeDis.setDistributeStatus("01");
				pdIncomeDis.setDistributeDate(DateUtils.getDate(distributeDate));
				pdIncomeDis.setDistributeStartDate(DateUtils.getDate(distributeStartDate));
				pdIncomeDis.setDistributeEndDate(DateUtils.getDate(distributeEndDate));
				BeanUtils.insertObjectSetOperateInfo(pdIncomeDis, loginInfo);
				pdIncomeDisMapper.insert(pdIncomeDis);
			}
			logger.info("===收益分配该产品交易数量：==="+productId+"==="+productName+"==="+tradeInfoList.size());
			//本次分配利息总额
			double distributeTotalInterest = 0;
			//本次分配本金总额
			double distributeTotalPrincipal = 0;
			//本次分配总额=本次分配利息总额+本次分配本金总额
			double distributeTotal = 0;
			//收益分配天数，默认包括起始日期不包括结束日期
			int distributeDays = DateUtils.calInterval(distributeStartDate, distributeEndDate, "D")+1;
			for (Map tradeInfoMap:tradeInfoList) {
				//该笔交易分配的利息
				double distributeInterest = 0;
				//该笔交易分配的本金
				double distributePrincipal = 0;
				Long tradeInfoId = (Long)tradeInfoMap.get("tradeInfoId");
				Long custBaseInfoId = (Long)tradeInfoMap.get("custBaseInfoId");
				BigDecimal actuSubscriptionAmount = transferAmount;
				Long custAccInfoId = (Long)tradeInfoMap.get("custAccInfoId");
				BigDecimal expectFeeRate = (BigDecimal)tradeInfoMap.get("expectFeeRate");
				logger.info("===收益分配交易信息（交易流水号、实际认购额、预期收益率）：==="+tradeInfoId.toString()+"=="+actuSubscriptionAmount.toString()+"=="+expectFeeRate.toString());
				//计算利息
				double interestRate = distributeDays*NumberUtils.divide(expectFeeRate.toString(),"100")/365;
				distributeInterest = NumberUtils.multiply(actuSubscriptionAmount.toString(),interestRate+"");
				distributeTotalInterest += distributeInterest;
				logger.info("===收益分配交易分配利息：==="+distributeInterest);
				//计算本金
				double principalRate = NumberUtils.divide(principalDistributeRate.toString(),"100");
				distributePrincipal = NumberUtils.multiply(actuSubscriptionAmount.toString(),principalRate+"");
				distributeTotalPrincipal += distributePrincipal;
				logger.info("===收益分配交易分配本金：==="+distributePrincipal);
				//保存单笔交易收益分配信息
				PdIncomeDisDetail pdIncomeDisDetail = new PdIncomeDisDetail();
				Long pdIncomeDistributeDetailId = commonService.getSeqValByName("SEQ_T_PD_INCOME_DIS_DETAIL");
				pdIncomeDisDetail.setPdIncomeDistributeDetailId(pdIncomeDistributeDetailId);
				pdIncomeDisDetail.setPdIncomeDistributeId(pdIncomeDis.getPdIncomeDistributeId());
				pdIncomeDisDetail.setTradeInfoId(tradeInfoId);
				pdIncomeDisDetail.setCustBaseInfoId(custBaseInfoId);
				pdIncomeDisDetail.setExpectedFeeRate(expectFeeRate);
				pdIncomeDisDetail.setDistributeInterest(new BigDecimal(distributeInterest));
				pdIncomeDisDetail.setDistributePrincipal(new BigDecimal(distributePrincipal));
				pdIncomeDisDetail.setCustAccInfoId(custAccInfoId);
				BeanUtils.insertObjectSetOperateInfo(pdIncomeDisDetail, loginInfo);
				pdIncomeDisDetailMapper.insertSelective(pdIncomeDisDetail);
			
			//分配总额
			distributeTotal = distributeTotalInterest + distributeTotalPrincipal;
			logger.info("===收益分配产品分配信息：==="+productId+"==="+productName);
			logger.info("===收益分配产品分配（利息总额，本金总额、总金额）：==="+distributeTotalInterest+"==="+distributeTotalPrincipal+"==="+distributeTotal);
			pdIncomeDis.setDistributeTotalInterest(new BigDecimal(distributeTotalInterest));
			pdIncomeDis.setDistributeTotalPrincipal(new BigDecimal(distributeTotalPrincipal));
			pdIncomeDis.setDistributeTotal(new BigDecimal(distributeTotal));
			pdIncomeDisMapper.updateByPrimaryKey(pdIncomeDis);
		}
		//收益分配完成
		resultInfo.setSuccess(true);
		resultInfo.setMsg("收益分配完成！");
	} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("收益分配批处理出现异常！");
	}
	return resultInfo.getMsg();
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@Transactional
	private boolean dealTradeOperation(HashMap paramMap) {
		String tradeId = (String) paramMap.get("tradeId");
		String tradeType = (String) paramMap.get("tradeType");
		String userCode = (String) paramMap.get("userCode");
		String tradeConclusion = (String) paramMap.get("tradeConclusion");
		String tradeRemark = (String) paramMap.get("tradeRemark");
		String tradeOperationNode = (String) paramMap.get("tradeOperationNode");
		Long userIdDecimal = (Long) paramMap.get("userId");
		Long comIdDecimal = (Long) paramMap.get("comId");
		Long tradeOperationID = commonService.getSeqValByName("SEQ_T_TRADE_OPERATION");
		Long tradeOperationTraceID = commonService.getSeqValByName("SEQ_T_TRADE_OPERATION_TRACE");
		Long tradeIdDecimal = new Long(tradeId);
		String rc_state = Constants.EFFECTIVE_RECORD;

		TradeOperationExample tradeOperationExample = new TradeOperationExample();
		tradeOperationExample.createCriteria().andTradeInfoIdEqualTo(tradeIdDecimal)
				.andOperationalNodeEqualTo(tradeOperationNode);
		List<TradeOperation> tradeOperationList = tradeOperationMapper.selectByExample(tradeOperationExample);
		if (tradeOperationList != null && tradeOperationList.size() > 0) {
			TradeOperation tradeOperation = new TradeOperation();
			tradeOperation = tradeOperationList.get(0);

			TradeOperationTrace tradeOperationTrace = new TradeOperationTrace();
			tradeOperationTrace.setTradeInfoId(tradeOperation.getTradeInfoId());
			tradeOperationTrace.setTradeOperationId(tradeOperation.getTradeOperationId());
			tradeOperationTrace.setTradeOperationTraceId(tradeOperationTraceID);
			tradeOperationTrace.setOperationalNode(tradeOperation.getOperationalNode());
			tradeOperationTrace.setUserId(tradeOperation.getUserId());
			tradeOperationTrace.setOperComId(tradeOperation.getOperComId());
			tradeOperationTrace.setConclusion(tradeOperation.getConclusion());
			tradeOperationTrace.setBackTime(DateUtils.getCurrentTimestamp());
			tradeOperationTrace.setCreateDate(DateUtils.getCurrentTimestamp());
			tradeOperationTrace.setCreateUserId(userIdDecimal);
			tradeOperationTrace.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeOperationTrace.setModifyUserId(userIdDecimal);
			tradeOperationTrace.setRcState(rc_state);
			tradeOperationTrace.setRemark(tradeOperation.getRemark());
			tradeOperationTraceMapper.insertSelective(tradeOperationTrace);

			tradeOperation.setConclusion(tradeConclusion);
			tradeOperation.setRemark(tradeRemark);
			tradeOperation.setUserId(userIdDecimal);
			tradeOperation.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeOperation.setModifyUserId(userIdDecimal);
			tradeOperationMapper.updateByExampleSelective(tradeOperation, tradeOperationExample);
		} else {
			TradeOperation tradeOperation = new TradeOperation();
			tradeOperation.setTradeInfoId(tradeIdDecimal);
			tradeOperation.setTradeOperationId(tradeOperationID);
			tradeOperation.setUserId(userIdDecimal);
			tradeOperation.setOperComId(comIdDecimal);
			tradeOperation.setConclusion(tradeConclusion);
			tradeOperation.setCreateDate(DateUtils.getCurrentTimestamp());
			tradeOperation.setCreateUserId(userIdDecimal);
			tradeOperation.setModifyDate(DateUtils.getCurrentTimestamp());
			tradeOperation.setModifyUserId(userIdDecimal);
			tradeOperation.setOperationalNode(tradeOperationNode);
			tradeOperation.setRcState(rc_state);
			tradeOperation.setRemark(tradeRemark);
			tradeOperationMapper.insertSelective(tradeOperation);
		}
		return true;
	}
	/**
	 * 转让审核查询列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataGrid queryTransferAuditList(DataGridModel dgm, Map paramMap, LoginInfo loginInfo) {
		DataGrid dataGrid = new DataGrid();
		if (paramMap==null) {
			paramMap = new HashMap();
		}
		int startIndex =((dgm.getPage()-1)*dgm.getRows());
		paramMap.put("startIndex",startIndex);
		paramMap.put("endIndex",dgm.getRows());
		paramMap.put("rolePrivilege", loginInfo.getRolePivilege());
		paramMap.put("operComId", loginInfo.getComId().toString());
		paramMap.put("createUserId", loginInfo.getUserId().toString());
		Integer total = fundsShareServiceMapper.queryTransferAuditListCount(paramMap);
		List<Map> resultList =  fundsShareServiceMapper.queryTransferAuditList(paramMap);
		dataGrid.setRows(resultList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
	
	
	/**
	 * 撤销删除转让信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultInfo deleteTradeFundsShareRecord(Map paramMap, LoginInfo loginInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 1.将原交易交易状态设置为成立-10
			Long orTradeId = Long.parseLong(paramMap.get("orTradeId").toString());
			TradeInfoExample tradeInfoExample = new TradeInfoExample();
			tradeInfoExample.createCriteria().andTradeInfoIdEqualTo(orTradeId).andRcStateEqualTo("E");
			List<TradeInfo> orTradeInfoList = tradeInfoMapper.selectByExample(tradeInfoExample);
			TradeInfo orTradeInfo = orTradeInfoList.get(0);
			orTradeInfo.setTradeStaus("10");
			BeanUtils.updateObjectSetOperateInfo(orTradeInfo, loginInfo);
			tradeInfoMapper.updateByPrimaryKey(orTradeInfo);
			// 2.将新增的交易撤销
			String tradeInfoStr = paramMap.get("tradeInfoId").toString();
			if (!tradeInfoStr.equals("")&&tradeInfoStr != null) {
				Long tradeInfoId = Long.parseLong(tradeInfoStr);
				TradeInfoExample tradeInfoExampleNew = new TradeInfoExample();
				tradeInfoExampleNew.createCriteria().andTradeInfoIdEqualTo(tradeInfoId).andRcStateEqualTo("E");
				List<TradeInfo> tradeInfoList = tradeInfoMapper.selectByExample(tradeInfoExampleNew);
				TradeInfo tradeInfo = tradeInfoList.get(0);
				tradeInfo.setTradeStaus("09");
				BeanUtils.updateObjectSetOperateInfo(tradeInfo, loginInfo);
				tradeInfoMapper.updateByPrimaryKey(tradeInfo);
			}
			// 3.将基金份额转让记录删除
			Long tradeFundsShareChangeId = Long.parseLong(paramMap.get("tradeFundsShareChangeId").toString());
			TradeFundsShareChangeExample tradeFundsShareChangeExample = new TradeFundsShareChangeExample();
			tradeFundsShareChangeExample.createCriteria().andTradeFundsShareChangeIdEqualTo(tradeFundsShareChangeId)
			.andRcStateEqualTo("E");
			List<TradeFundsShareChange> tradeFundsShareChangeList = tradeFundsShareChangeMapper.selectByExample(tradeFundsShareChangeExample);
			TradeFundsShareChange tradeFundsShareChangeInfo = tradeFundsShareChangeList.get(0);
			tradeFundsShareChangeInfo.setRcState("D");
			BeanUtils.updateObjectSetOperateInfo(tradeFundsShareChangeInfo, loginInfo);
			tradeFundsShareChangeMapper.updateByPrimaryKey(tradeFundsShareChangeInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("撤销删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("撤销删除出现异常！");
		}
		return resultInfo;
	}
}
