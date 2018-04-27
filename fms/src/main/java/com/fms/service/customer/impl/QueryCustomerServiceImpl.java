package com.fms.service.customer.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fms.service.customer.QueryCustomerService;
import com.fms.service.mapper.QueryCustomerServiceMapper;
import com.sinosoft.util.ResultInfo;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QueryCustomerServiceImpl implements QueryCustomerService {
	
	@Autowired
	QueryCustomerServiceMapper queryCustomerServiceMapper;
	
	/**
	 * 根据手机号与邮箱号查询客户详细信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	@Override
	public ResultInfo queryCustomerInfo(Map<String, Object> paraMap) {
		//ResultInfo对象存放查询状态及结果集
		ResultInfo resultInfo = new ResultInfo();
		//List对象存放查询数据库可能会返回的多条记录
		List<LinkedHashMap<String, Object>> customerInfoList = new ArrayList<LinkedHashMap<String, Object>>();
		//查询数据库
		try {
			customerInfoList = queryCustomerServiceMapper.queryCustomerInfo(paraMap);
		} catch (Exception e) {
			e.printStackTrace();
			//设置resultInfo状态值
			resultInfo.setSuccess(false);
			resultInfo.setObj(null);
			return resultInfo;
		}

		// 4.数据库查询结果列表长度 等于 0
		if (0 == customerInfoList.size()) {
			resultInfo.setSuccess(false);
			resultInfo.setObj(null);
			return resultInfo;
		}
		// 5.遍历数据库查询结果列表
		for (int i = 0; i < customerInfoList.size(); ++i) {
			// 当前Map元素引用 不等于 null
			if ( null != customerInfoList.get(i) ) {
				// 当前Map对象 包含 客户号
				if (customerInfoList.get(i).containsKey("custNo")) {
					// 将当前对象中客户号重新包装成新Map对象
					Map<String, Object> custNoMap = new HashMap<String, Object>();
					custNoMap.put("custNo", (String)customerInfoList.get(i).get("custNo"));
					// 使用新组装Map对象重新查询数据库
					List<LinkedHashMap<String, String>> addressList = queryCustomerServiceMapper.queryAddressInfo(custNoMap);
					// 遍历结果列表
					for (int j = 0; j < addressList.size(); ++j) {
						// 当前对象引用 不等于 空
						if (null != addressList.get(j)) {
							// 当前对象 包含 地址类型
							if (addressList.get(j).containsKey("addressType")) {
								// 地址类型 等于 01工作地址
								if (addressList.get(j).get("addressType").equals("01")) {
									// 存储工作地址"省市区"
									LinkedHashMap<String, String> addressInOneType = new LinkedHashMap<String, String>();
									// '地址'查询结果中'省'字段 包含 值
									if(addressList.get(j).containsKey("province")) {
										addressInOneType.put("province", addressList.get(j).get("province"));
									}
									// '地址'查询结果中'市'字段 包含 值
									if(addressList.get(j).containsKey("city")) {
										addressInOneType.put("city", addressList.get(j).get("city"));
									}
									// '地址'查询结果中'区'字段 包含 值
									if(addressList.get(j).containsKey("area")) {
										addressInOneType.put("area", addressList.get(j).get("area"));
									}
									// '地址'查询结果中'街道'字段 包含 值
									if(addressList.get(j).containsKey("street")) {
										addressInOneType.put("street", addressList.get(j).get("street"));
									}
									customerInfoList.get(i).put("contactAddress", addressInOneType);
								} 
								// 地址类型 等于 02 居住地址
								if (addressList.get(j).get("addressType").equals("02")) {
									// 存储工作地址"省市区"
									LinkedHashMap<String, String> addressInOneType = new LinkedHashMap<String, String>();
									// '地址'查询结果中'省'字段 包含 值
									if(addressList.get(j).containsKey("province")) {
										addressInOneType.put("province", addressList.get(j).get("province"));
									}
									// '地址'查询结果中'市'字段 包含 值
									if(addressList.get(j).containsKey("city")) {
										addressInOneType.put("city", addressList.get(j).get("city"));
									}
									// '地址'查询结果中'区'字段 包含 值
									if(addressList.get(j).containsKey("area")) {
										addressInOneType.put("area", addressList.get(j).get("area"));
									}
									// '地址'查询结果中'街道'字段 包含 值
									if(addressList.get(j).containsKey("street")) {
										addressInOneType.put("street", addressList.get(j).get("street"));
									}
									customerInfoList.get(i).put("homeAddress", addressInOneType);
								}
							}
						}
					}
				}
			}
		}
		// 6.若列表第一个元素指向对象 不等于 NULL
		if (null != customerInfoList.get(0)) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(customerInfoList);
		} else {
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}
	
	/**
	 * 只根据手机号查询客户详细信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtainCustomerInfoOnlyByMobile(Map<String, Object> paraMap) {
		// 1.新建ResultInfo对象存放查询状态及结果集
		ResultInfo resultInfo = new ResultInfo();
		// 2.新建List<Map>类对象存放查询数据库可能会返回的多条记录，并将Map对象作为查询参数，查询结果每条记录对应一个Map
		List<LinkedHashMap<String, Object>> customerInfoList = queryCustomerServiceMapper.queryCustomerInfoOnlyByMobile(paraMap);
		// 4.数据库查询结果列表长度 等于 0
		if (0 == customerInfoList.size()) {
			resultInfo.setSuccess(false);
			resultInfo.setObj(null);
			return resultInfo;
		}
		// 5.遍历数据库查询结果列表
		for (int i = 0; i < customerInfoList.size(); ++i) {
			// 当前Map元素引用 不等于 null
			if ( null != customerInfoList.get(i) ) {
				// 当前Map对象 包含 客户号
				if (customerInfoList.get(i).containsKey("custNo")) {
					// 将当前对象中客户号重新包装成新Map对象
					Map<String, Object> custNoMap = new HashMap<String, Object>();
					custNoMap.put("custNo", (String)customerInfoList.get(i).get("custNo"));
					// 使用新组装Map对象重新查询数据库
					List<LinkedHashMap<String, String>> addressList = queryCustomerServiceMapper.queryAddressInfo(custNoMap);
					// 遍历结果列表
					for (int j = 0; j < addressList.size(); ++j) {
						// 当前对象引用 不等于 空
						if (null != addressList.get(j)) {
							// 当前对象 包含 地址类型
							if (addressList.get(j).containsKey("addressType")) {
								// 地址类型 等于 01工作地址
								if (addressList.get(j).get("addressType").equals("01")) {
									// 存储工作地址"省市区"
									LinkedHashMap<String, String> addressInOneType = new LinkedHashMap<String, String>();
									// '地址'查询结果中'省'字段 包含 值
									if(addressList.get(j).containsKey("province")) {
										addressInOneType.put("province", addressList.get(j).get("province"));
									}
									// '地址'查询结果中'市'字段 包含 值
									if(addressList.get(j).containsKey("city")) {
										addressInOneType.put("city", addressList.get(j).get("city"));
									}
									// '地址'查询结果中'区'字段 包含 值
									if(addressList.get(j).containsKey("area")) {
										addressInOneType.put("area", addressList.get(j).get("area"));
									}
									// '地址'查询结果中'街道'字段 包含 值
									if(addressList.get(j).containsKey("street")) {
										addressInOneType.put("street", addressList.get(j).get("street"));
									}
									customerInfoList.get(i).put("contactAddress", addressInOneType);
								} 
								// 地址类型 等于 02 居住地址
								if (addressList.get(j).get("addressType").equals("02")) {
									// 存储工作地址"省市区"
									LinkedHashMap<String, String> addressInOneType = new LinkedHashMap<String, String>();
									// '地址'查询结果中'省'字段 包含 值
									if(addressList.get(j).containsKey("province")) {
										addressInOneType.put("province", addressList.get(j).get("province"));
									}
									// '地址'查询结果中'市'字段 包含 值
									if(addressList.get(j).containsKey("city")) {
										addressInOneType.put("city", addressList.get(j).get("city"));
									}
									// '地址'查询结果中'区'字段 包含 值
									if(addressList.get(j).containsKey("area")) {
										addressInOneType.put("area", addressList.get(j).get("area"));
									}
									// '地址'查询结果中'街道'字段 包含 值
									if(addressList.get(j).containsKey("street")) {
										addressInOneType.put("street", addressList.get(j).get("street"));
									}
									customerInfoList.get(i).put("homeAddress", addressInOneType);
								}
							}
						}
					}
				}
			}
		}
		// 6.若列表第一个元素指向对象 不等于 NULL
		if (null != customerInfoList.get(0)) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(customerInfoList);
		} else {
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}
	
	/**
	 * 只根据邮箱查询客户详细信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtainCustomerInfoOnlyByEmail(Map<String, Object> paraMap) {
		// 1.新建ResultInfo对象存放查询状态及结果集
		ResultInfo resultInfo = new ResultInfo();
		// 2.新建List<Map>类对象存放查询数据库可能会返回的多条记录，并将Map对象作为查询参数，查询结果每条记录对应一个Map
		List<LinkedHashMap<String, Object>> customerInfoList = queryCustomerServiceMapper.queryCustomerInfoOnlyByEmail(paraMap);
		// 4.数据库查询结果列表长度 等于 0
		if (0 == customerInfoList.size()) {
			resultInfo.setSuccess(false);
			resultInfo.setObj(null);
			return resultInfo;
		}
		// 5.遍历数据库查询结果列表
		for (int i = 0; i < customerInfoList.size(); ++i) {
			// 当前Map元素引用 不等于 null
			if ( null != customerInfoList.get(i) ) {
				// 当前Map对象 包含 客户号
				if (customerInfoList.get(i).containsKey("custNo")) {
					// 将当前对象中客户号重新包装成新Map对象
					Map<String, Object> custNoMap = new HashMap<String, Object>();
					custNoMap.put("custNo", (String)customerInfoList.get(i).get("custNo"));
					// 使用新组装Map对象重新查询数据库
					List<LinkedHashMap<String, String>> addressList = queryCustomerServiceMapper.queryAddressInfo(custNoMap);
					// 遍历结果列表
					for (int j = 0; j < addressList.size(); ++j) {
						// 当前对象引用 不等于 空
						if (null != addressList.get(j)) {
							// 当前对象 包含 地址类型
							if (addressList.get(j).containsKey("addressType")) {
								// 地址类型 等于 01工作地址
								if (addressList.get(j).get("addressType").equals("01")) {
									// 存储工作地址"省市区"
									LinkedHashMap<String, String> addressInOneType = new LinkedHashMap<String, String>();
									// '地址'查询结果中'省'字段 包含 值
									if(addressList.get(j).containsKey("province")) {
										addressInOneType.put("province", addressList.get(j).get("province"));
									}
									// '地址'查询结果中'市'字段 包含 值
									if(addressList.get(j).containsKey("city")) {
										addressInOneType.put("city", addressList.get(j).get("city"));
									}
									// '地址'查询结果中'区'字段 包含 值
									if(addressList.get(j).containsKey("area")) {
										addressInOneType.put("area", addressList.get(j).get("area"));
									}
									// '地址'查询结果中'街道'字段 包含 值
									if(addressList.get(j).containsKey("street")) {
										addressInOneType.put("street", addressList.get(j).get("street"));
									}
									customerInfoList.get(i).put("contactAddress", addressInOneType);
								} 
								// 地址类型 等于 02 居住地址
								if (addressList.get(j).get("addressType").equals("02")) {
									// 存储工作地址"省市区"
									LinkedHashMap<String, String> addressInOneType = new LinkedHashMap<String, String>();
									// '地址'查询结果中'省'字段 包含 值
									if(addressList.get(j).containsKey("province")) {
										addressInOneType.put("province", addressList.get(j).get("province"));
									}
									// '地址'查询结果中'市'字段 包含 值
									if(addressList.get(j).containsKey("city")) {
										addressInOneType.put("city", addressList.get(j).get("city"));
									}
									// '地址'查询结果中'区'字段 包含 值
									if(addressList.get(j).containsKey("area")) {
										addressInOneType.put("area", addressList.get(j).get("area"));
									}
									// '地址'查询结果中'街道'字段 包含 值
									if(addressList.get(j).containsKey("street")) {
										addressInOneType.put("street", addressList.get(j).get("street"));
									}
									customerInfoList.get(i).put("homeAddress", addressInOneType);
								}
							}
						}
					}
				}
			}
		}
		// 6.若列表第一个元素指向对象 不等于 NULL
		if (null != customerInfoList.get(0)) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(customerInfoList);
		} else {
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}
	
	/**
	 * 根据客户编码查询理财师详细信息
	 * @author LIWENTAO
	 * @param customerId
	 * @return
	 */
	@Override
	public ResultInfo queryAgentInfo(String customerId) {
		// 1.新建ResultInfo对象存放查询状态及结果集
		ResultInfo resultInfo = new ResultInfo();
		// 2.将customerId参数名与值存入Map对象，并将Map对象传入MAPPER层
		Map<String, String> parameterMap = new HashMap<String, String>(); 
		parameterMap.put("customerId", customerId);		
		// 3.根据Map对象查询数据库，返回List类型列表
		List<LinkedHashMap<String, String>> agentInfoList = queryCustomerServiceMapper.queryAgentBaseInfo(parameterMap);
		// 4.数据库查询记录条数等于0
		if (0 == agentInfoList.size()) {
			resultInfo.setSuccess(false);
			resultInfo.setObj(null);
			return resultInfo;
		}
		

		// 判断列表中第一个元素是否为空元素
		if (null != agentInfoList.get(0)) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(agentInfoList);
		} else {
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}
	
	/**
	 * 根据绿城客户号查询其姓名信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtainCustomerNameInfo(Map<String, Object> paraMap) {
		ResultInfo resultInfo = new ResultInfo();
		//客户姓名信息Map
		Map<String, String> customerNameMap = queryCustomerServiceMapper.queryCustomerName(paraMap);
		//客户姓名Map大小 等于 0
		if (0 == customerNameMap.size()) {
			resultInfo.setSuccess(false);
			resultInfo.setObj(null);
			return resultInfo;
		}
		//客户姓名Map大小 大于 0
		if (0 < customerNameMap.size()) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(customerNameMap);
		}
		return resultInfo;
	}
	
	/**
	 * 根据绿城客户号查询其订单信息
	 * @author LIWENTAO
	 * @param paraMap
	 * @return
	 */
	public ResultInfo obtaineweatlthProductOrderInfo(Map<String, Object> paraMap) {
		ResultInfo resultInfo = new ResultInfo();
		//客户订单信息列表
		List<LinkedHashMap<String, String>> wealthProductInfoList = queryCustomerServiceMapper.queryeweatlthProductOrder(paraMap);
		//数据库查询结果列表长度 等于 0
		if (0 == wealthProductInfoList.size()) {
			resultInfo.setSuccess(false);
			resultInfo.setObj(null);
			return resultInfo;
		}
		//判断列表中第一个元素是否为空元素
		if (null != wealthProductInfoList.get(0)) {
			resultInfo.setSuccess(true);
			resultInfo.setObj(wealthProductInfoList);
		} else {
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}
	
	/**
	 * 查看理财师是否已经存在于核心系统中
	 * @author LIWENTAO
	 * @param paraMap
	 * @return 
	 */
	public ResultInfo obtainAgentIsExistInfo(Map<String, Object> paraMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			//Map参数传入MAPPER接口,获取理财师信息
			List<LinkedHashMap<String, String>> agentInfoList = queryCustomerServiceMapper.queryAgentInfo(paraMap);
			if (agentInfoList.size() == 0) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("NoExist");
			} else {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("Exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("error");
			return resultInfo;
		}
		return resultInfo;
	}
}
