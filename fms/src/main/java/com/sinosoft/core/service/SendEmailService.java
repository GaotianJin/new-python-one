package com.sinosoft.core.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.util.ResultInfo;

public interface SendEmailService {

	/**
	 * @param address 收件地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @return
	 */
	public ResultInfo sendEmail(String address,String title,String content,Map<String, String> mailHostParam);
	
	/**
	 * @param address 收件人
	 * @param othEmailArress 抄送人
	 * @param title
	 * @param content
	 * @param mailHostParam
	 * @return
	 */
	public ResultInfo sendEmail(String[] address,String[] othEmailAddress,String title,String content,Map<String, String> mailHostParam);
	
}
