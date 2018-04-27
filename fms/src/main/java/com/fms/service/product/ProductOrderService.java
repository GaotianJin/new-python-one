package com.fms.service.product;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.PDAmountOrderQueueInfo;
import com.fms.db.model.PDProduct;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


public interface ProductOrderService {

	/**
	 * 获取“产品预约申请”信息
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getAllPdVerificationInfo(DataGridModel dgm, Map paramMap, LoginInfo loginInfo);

	
	/**
	 * 提交预约产品信息审核
	 * @param pdAmountOrderInfo
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo submitPdOrderAudit(PDAmountOrderInfo pdAmountOrderInfo,LoginInfo loginInfo);
	
	/**
	 * 撤销产品预约
	 * @param pdAmountOrderInfo
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo cancelPdAmountOrderAudit(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo);
	
	
	/**
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryComPdAmountOrderInfoList(DataGridModel dgm,Map paramMap, LoginInfo loginInfo);
	
	
	/**
	 * 获取产品、机构、理财经理相关信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getProductAndComInfo(Map paramMap,LoginInfo loginInfo);

	/**
	 * 保存产品预约相关信息
	 * @param pdAmountOrderQueueInfo 
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo saveProductAmountOrderInfo(PDAmountOrderInfo pdAmountOrderInfo,PDAmountOrderQueueInfo pdAmountOrderQueueInfo, Map<String, String> paramMap, LoginInfo loginInfo) throws Exception;
 
    
	/**产品预约查询
	 * 
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getAllPdAmountOrderInfo(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	
	/**产品队列预约查询
	 * 
	 * @param dgm
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid getRemainAmountOrderInfo(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
	 
	/**
	 * 撤销预约信息
	 * @param uid
	 * @param loginInfo
	 */
    //public ResultInfo deletepdAmountOrderInfo(PDAmountOrderInfo pdAmountOrderInfo,LoginInfo loginInfo);


	
	/**
	 * 查询客户预约产品明细信息
	 * @param pdAmountOrderInfoId
	 * @return
	 */
	public ResultInfo getCustProductAmountOrderInfo(String pdAmountOrderInfoId);
	
	/**
	 * 查询队列客户预约产品明细信息
	 * @param pdAmountOrderQueueInfoId
	 * @return
	 */
	public ResultInfo getCustRemainAmountOrderInfo(String pdAmountOrderQueueInfoId);

	
	
	/**
	 * 获取邀请码
	 * @return
	 */
	public ResultInfo getInviteCode();
	/**
	 * 导出预约信息
	 * @param paramMap
	 * @param loginInfo 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	/*public ResultInfo getProductOrderDetailInfo(Map paramMap);*/
	
	public Map getProductOrderDetailInfo(Map paramMap, LoginInfo loginInfo) throws Exception;
	/**
	 * 从T_DEF_COM中获取下拉数据
	 * @return
	 */
	public List<Map<String,String>> comNameQuery();
	
	
	/**
	 * 判断客户在一年之内是否已购买过物业宝产品，客户要求按中文名判断，所以这才会按中文名判断，
	 * 本来想在产品定义中添加产品系列这个属性的，客户嫌麻烦，不让加，所以不要怀疑我这代码有问题,找IT张总也确认过此问题，说就按名称来，于是我妥协了
	 * @param productId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo isHadBuyWYBProduct(Map paramMap);

/*
	*//**
	 * 预约查询时导出数据
	 * @param paramMap
	 * @return
	 *//*
	@SuppressWarnings("rawtypes")
	public ResultInfo productOrderQueryInfo(Map paramMap);
*/
	/**
	 * 查询预约总额
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getPdOrderTotalAmount(Map paramMap, LoginInfo loginInfo);
	
	/**
	 * 根据邀请码与手机号查询产品预约状态及预约额度
	 * @author
	 * @param
	 * @return
	 */
	public ResultInfo getProductOrderInfoByMobileNumAndInviteCode(Map<String, Object> paraMap);
	
	/**
	 * 根据邀请码查询产品预约状态及预约额度
	 * @author LIWENTAO
	 * @param
	 * @return
	 * @category EwealthWebService
	 */
	public ResultInfo getProductOrderInfoByInviteCode(Map<String, Object> paraMap);
	
	/**
	 * 
	 * @param pdAmountOrderInfo
	 * @param loginInfo
	 * @return
	 * @category EwealthWebservice
	 */
	@Transactional
	public ResultInfo updatePdOrderStatus(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo);
	
	/**
	 * 获取队列产品、机构、理财经理相关信息
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getQueueProductAndComInfo(Map paramMap,LoginInfo loginInfo);


	/**
	 * 获取剩余额度
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getRemainTotalAmount(Map paramMap, LoginInfo loginInfo);

    /**
     * 队列产品预约分配
     * @param pdAmountOrderQueueInfoId
     * @param loginInfo
     * @return
     * @throws Exception 
     */
	public ResultInfo submitQueueDistribute(String pdAmountOrderQueueInfoId, String amount, String foundDate, LoginInfo loginInfo) throws Exception;


    /**
     * 产品预约排队导出
     * @param pdAmountOrderQueueInfoId
     * @param loginInfo
     * @return
     * @throws Exception 
     */
	public Map productOrderQueueInfo(Map paramMap) throws Exception;
	
	/**热门产品信息管理
	 * @param dgm
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryHotPdOrderInfoList(DataGridModel dgm,Map paramMap, LoginInfo loginInfo);
	
	/**修改热门产品状态
	 * @param loginInfo
	 * @return
	 * @throws Exception 
	 */
	public ResultInfo cancelHotProductInfo(String productId, LoginInfo loginInfo) throws Exception;
	

	/**
	 * 根据客户号查询客户信息
	 * @author ZYM
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo getCustomerInfo(Map paramMap, LoginInfo loginInfo);

	
	/**
	 * 根据客户号查询客户信息
	 * @author CJJ
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 * @throws Exception 
	 */
	public ResultInfo updateProductRemainAmount(Map paramMap, LoginInfo loginInfo);

	
	 /**
     * 产品预约查询导出
	 * @param loginInfo 
     * @param pdAmountOrderQueueInfoId
     * @param loginInfo
     * @return
     * @throws Exception 
     */
	public Map productOrderQueryInfo(Map paramMap, LoginInfo loginInfo) throws Exception;

	/**
     * 批量导入产品预约审核信息
	 * @param pdAmountOrderAuditFile 
     * @param loginInfo
     * @return
	 * @throws Exception 
     */
	public ResultInfo importPDAmountOrderAuditFile(MultipartFile[] pdAmountOrderAuditFile, LoginInfo loginInfo) throws Exception;

	/**
	 * 定金打款审核
	 * @author ZYM
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public ResultInfo submitEarnestAudit(PDAmountOrderInfo pdAmountOrderInfo, LoginInfo loginInfo);

	/**
	 * 
	 * 新增热门产品
	 * @param productId
	 * @param loginInfo
	 * @return
	 * @throws Exception 
	 */
	public ResultInfo addHotProductInfo(String productId, LoginInfo loginInfo) throws Exception;

	/**
	 * 查商品名给下拉框
	 * @return
	 */
	List<PDProduct> getProductAllForSelect();
}

    
