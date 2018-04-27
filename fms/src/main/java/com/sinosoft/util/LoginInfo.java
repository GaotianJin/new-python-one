package com.sinosoft.util;

import java.io.Serializable;

/**
 * 用户登陆信息
 * @author likai
 *
 */
public class LoginInfo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6832186717496524342L;
	// 登陆用户ID
	private Long userId;
	// 登陆用户CODE
	private String userCode;
	// 登陆用户所属管理机构ID
	private Long comId;
	// 登陆机构ID
	private Long loginComId;
	// 用户权限
	private String rolePivilege;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getComId()
	{
		return comId;
	}

	public void setComId(Long comId)
	{
		this.comId = comId;
	}

	public Long getLoginComId()
	{
		return loginComId;
	}

	public void setLoginComId(Long loginComId)
	{
		this.loginComId = loginComId;
	}

	public String getRolePivilege()
	{
		return rolePivilege;
	}

	public void setRolePivilege(String rolePivilege)
	{
		this.rolePivilege = rolePivilege;
	}

}
