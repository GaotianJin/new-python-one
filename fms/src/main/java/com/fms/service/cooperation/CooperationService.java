package com.fms.service.cooperation;

import java.util.Map;
import com.fms.db.model.AgencyCom;
import com.fms.db.model.AgencyProtocol;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface CooperationService {
	
	/*************** 合作机构信息维护 ***************/
	// 新增合作机构信息
	@SuppressWarnings("rawtypes")
	public ResultInfo addAgencyComInfo(Map tMap,String operate,LoginInfo loginInfo);
	//获取合作机构信息列表
	public  Map<String, Object> getAgencyComPageList(DataGridModel dgm, AgencyCom agencyCom);
	//根据合作机构ID查询详细的信息
	public ResultInfo queryAgencyComInfo(String param,LoginInfo loginInfo);
	//提交修改后的合作机构信息
	@SuppressWarnings("rawtypes")
	public void updateAgencyComInfo(Map tMap,LoginInfo loginInfo);
	//校验合作机构信息
	public Integer checkDeleteAgencycom(Long agencyComid);
    //删除合作机构信息
	public ResultInfo deleteAgencyCom(Long agencyComId,LoginInfo loginInfo);
	
	/*************** 合作机构协议信息维护 ***************/
	//新增合作机构协议信息
	@SuppressWarnings("rawtypes")
	public String addAgencyComProtocolInfo(Map tMap,LoginInfo loginInfo);
	//校验合作机构编码唯一
	public void verifyProtocolCode(String protocolCode,String protocolId,String protocolType,String action);
	//获得合作机构协议信息列表
	public DataGrid getAgencyComProtocolPageList(DataGridModel dgm, Map<String, String> paramMap,LoginInfo loginInfo);
	//根据合作机构ID查询详细的信息
	@SuppressWarnings("rawtypes")
	public ResultInfo queryAgencyComProtocolInfo(Map tMap,LoginInfo loginInfo);
	//修改合作机构协议信息
	@SuppressWarnings("rawtypes")
	public void updateAgencyComProtocolInfo(Map tMap,LoginInfo loginInfo);
	//删除合作机构协议信息
	@SuppressWarnings("rawtypes")
	public ResultInfo deleteAgencyComProtocol(Map tMap,LoginInfo loginInfo);
}
