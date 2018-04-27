package com.sinosoft.core.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sinosoft.core.service.SendEmailService;
import com.sinosoft.util.ResultInfo;

@SuppressWarnings("serial")
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SendEmailServiceImpl implements SendEmailService,Serializable {

	/**
	 * 发送邮件
	 * @param address 收件地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @param mailHostParam 邮件服务器参数
	 * @return
	 */
	@Override
	public ResultInfo sendEmail(String address, String title, String content,Map<String, String> mailHostParam) {
		ResultInfo resultInfo = new ResultInfo();
		Properties prop = new Properties();          
		prop.setProperty("mail.host", mailHostParam.get("mail.host"));//存储发送邮件的服务器
		prop.setProperty("mail.transport.protocol", "smtp");       
		prop.setProperty("mail.smtp.auth", "true");//通过服务器验证                          
		prop.setProperty("mail.from", mailHostParam.get("mail.from"));//发送人  service1@lvchengcaifu.com 
		prop.setProperty("mail.user", mailHostParam.get("mail.user"));
		//1.创建session
		Session session = Session.getInstance(prop); 
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		try {
			//2.通过Session得到transport对象 
			Transport ts = session.getTransport();
			//使用邮箱的用户名和密码连上邮件服务器发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
			ts.connect(prop.getProperty("mail.host"), prop.getProperty("mail.user"), mailHostParam.get("mail.user.password"));
			//创建邮件
			Message message = makeMessage(session,address,content,title,mailHostParam);      
			//发送邮件
			ts.sendMessage(message, message.getAllRecipients());      
			ts.close();
			resultInfo.setSuccess(true);
			resultInfo.setMsg("邮件发送成功");
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		} 
		return resultInfo;
	}

	
	/**
	 * 创建邮件
	 * @param session
	 * @param address
	 * @param cont
	 * @param title
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private Message makeMessage(Session session,String address,String cont,String title,Map<String, String> mailHostParam) throws AddressException, MessagingException, UnsupportedEncodingException{
		//创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件发件人
		message.setFrom(new InternetAddress(mailHostParam.get("mail.user"),mailHostParam.get("mail.from")));      
		//指明邮件的收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));       
		//邮件标题
		message.setSubject(title);
		//创建邮件正文
		MimeBodyPart text = new MimeBodyPart(); 
		text.setContent(cont,"text/html;charset=UTF-8");        
		//描述关系：正文
		MimeMultipart mp1 = new MimeMultipart();        
		mp1.addBodyPart(text);    
		mp1.setSubType("related");
		MimeMultipart mp2 = new MimeMultipart();        
	    //代表正文的bodypart
	    MimeBodyPart content = new MimeBodyPart();  
	    content.setContent(mp1); 
	    mp2.addBodyPart(content);       
	    mp2.setSubType("mixed");      
	    message.setContent(mp2);        
	    message.saveChanges();
	     
	    return message;
	}


	@Override
	public ResultInfo sendEmail(String[] address, String[] othEmailAddress, String title, String content,
			Map<String, String> mailHostParam) {
		ResultInfo resultInfo = new ResultInfo();
		Properties prop = new Properties();          
		prop.setProperty("mail.host", mailHostParam.get("mail.host"));//存储发送邮件的服务器
		prop.setProperty("mail.transport.protocol", "smtp");       
		prop.setProperty("mail.smtp.auth", "true");//通过服务器验证                          
		prop.setProperty("mail.from", mailHostParam.get("mail.from"));//发送人  service1@lvchengcaifu.com 
		prop.setProperty("mail.user", mailHostParam.get("mail.user"));
		//1.创建session
		Session session = Session.getInstance(prop); 
		//开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		try {
			//2.通过Session得到transport对象 
			Transport ts = session.getTransport();
			//使用邮箱的用户名和密码连上邮件服务器发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
			ts.connect(prop.getProperty("mail.host"), prop.getProperty("mail.user"), mailHostParam.get("mail.user.password"));
			//创建邮件
			Message message = makeMessage(session,address,othEmailAddress,content,title,mailHostParam);      
			//发送邮件
			ts.sendMessage(message, message.getAllRecipients());      
			ts.close();
			resultInfo.setSuccess(true);
			resultInfo.setMsg("邮件发送成功");
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		} 
		return resultInfo;
	}
	
	/**
	 * 创建邮件
	 * @param session
	 * @param address
	 * @param cont
	 * @param title
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unused")
	private Message makeMessage(Session session,String[] address,String[] othEmailAddress,String cont,String title,Map<String, String> mailHostParam) throws AddressException, MessagingException, UnsupportedEncodingException{
		//创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件发件人
		message.setFrom(new InternetAddress(mailHostParam.get("mail.user"),mailHostParam.get("mail.from"))); 
		//指明邮件的收件人（多个收件人）
		int len = address.length;
		InternetAddress[] addresses = new InternetAddress[len];
		for(int i=0;i<len;i++){
			InternetAddress addr = new InternetAddress(address[i]);
			addresses[i] = addr;
		};
		message.setRecipients(Message.RecipientType.TO, addresses);
		//指明邮件抄送人（多个抄送人）
		if (othEmailAddress!=null&&!"".equals(othEmailAddress)) {
			int othLen = othEmailAddress.length;
			InternetAddress[] othEmailAddresses = new InternetAddress[othLen];
			for(int i=0;i<othLen;i++){
				InternetAddress othAddr = new InternetAddress(othEmailAddress[i]);
				othEmailAddresses[i] = othAddr;
			};
			message.setRecipients(Message.RecipientType.CC, othEmailAddresses);
		}
		//邮件标题
		message.setSubject(title);
		//创建邮件正文
		MimeBodyPart text = new MimeBodyPart(); 
		text.setContent(cont,"text/html;charset=UTF-8");        
		//描述关系：正文
		MimeMultipart mp1 = new MimeMultipart();        
		mp1.addBodyPart(text);    
		mp1.setSubType("related");
		MimeMultipart mp2 = new MimeMultipart();        
	    //代表正文的bodypart
	    MimeBodyPart content = new MimeBodyPart();  
	    content.setContent(mp1); 
	    mp2.addBodyPart(content);       
	    mp2.setSubType("mixed");      
	    message.setContent(mp2);        
	    message.saveChanges();
	     
	    return message;
	}
	
}
