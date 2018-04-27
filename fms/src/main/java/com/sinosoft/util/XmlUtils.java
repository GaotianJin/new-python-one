package com.sinosoft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class XmlUtils {
	private static final Logger log = Logger.getLogger(XmlUtils.class);
	private static List<File> fileList = new ArrayList<File>();
	static{
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/format/";
		log.info("path:==="+path);
		File file = new File(path);
		if (file.isDirectory()) {
			fileList = Arrays.asList(file.listFiles());
		}
	}
	
	
	/**
	 * 将Map转换成XML，map中的object如果是map的实现类，必须使用LinkedHashMap,
	 * 如果Object是List，则list中的元素也使用LinkedHashMap，这样是为了按照顺序生成XML
	 * @param xmlDataMap
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public static void mapToXml(Map<String,Object> xmlDataMap,Element element){
		Set<Entry<String, Object>> entrySet = xmlDataMap.entrySet();
		for(Entry<String, Object> entry:entrySet){
			Element node = element.addElement(entry.getKey());
			//如果是List，则循环处理list中的
			if (entry.getValue().getClass().equals(ArrayList.class)) {
				List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>)entry.getValue();
				for(LinkedHashMap<String, Object> map:mapList){
					mapToXml(map,node);
				}
			}
			//如果是LinkedHashMap，继续递归
			else if(entry.getValue().getClass().equals(LinkedHashMap.class)){
				Map<String, Object> xmlDataMapObj = (Map<String, Object>)entry.getValue();
				mapToXml(xmlDataMapObj,node);
			}else{
				node.setText(entry.getValue().toString());
			}
		}
	}
	
	/**
	 * 解析XML到Map中
	 * @param xmlStr
	 * @param xmlDataMap
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void parseXmlToMap(String xmlStr,Map<String,Object> xmlDataMap) throws DocumentException {
		Document document = DocumentHelper.parseText(xmlStr); 
		Element rootElement = document.getRootElement();
		List<Element> nodeElements = rootElement.elements();
		for(Element nodeElement:nodeElements){
			List<Element> elements = nodeElement.elements();
			String nodeName = nodeElement.getName();
			if (elements!=null && elements.size()>0) {
				if (isLoop(nodeElement.asXML())) {
					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
					parseXmlToList(nodeElement.asXML(),list);
					xmlDataMap.put(nodeName, list);
				}else{
					Map<String,Object> nodeDataMap = new HashMap<String, Object>();
					parseXmlToMap(nodeElement.asXML(),nodeDataMap);
					xmlDataMap.put(nodeName, nodeDataMap);
				}
			}else{
				String nodeValue = nodeElement.getTextTrim();
				xmlDataMap.put(nodeName, nodeValue);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void parseXmlToList(String xmlStr,List<Map<String, Object>> list) throws DocumentException{
		Document document = DocumentHelper.parseText(xmlStr); 
		Element rootElement = document.getRootElement();
		List<Element> nodeElements = rootElement.elements();
		for(Element nodeElement:nodeElements){
			Map<String, Object> loopMap = new HashMap<String, Object>();
			Map<String,Object> nodeDataMap = new HashMap<String, Object>();
			parseXmlToMap(nodeElement.asXML(),nodeDataMap);
			loopMap.put(nodeElement.getName(), nodeDataMap);
			list.add(loopMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static boolean isLoop(String xmlStr) throws DocumentException{
		Document document = DocumentHelper.parseText(xmlStr); 
		Element rootElement = document.getRootElement();
		List<Element> nodeElements = rootElement.elements();
		if(nodeElements==null||nodeElements.size()==0){
			return false;
		}
		int count = 0;
		for(int i=0;i<nodeElements.size();i++){
			String elementName = nodeElements.get(i).getName();
			for(int j=i+1;j<nodeElements.size();j++){
				String innerElementName = nodeElements.get(j).getName();
				if (elementName.equals(innerElementName)) {
					count++;
				}
			}
		}
		if (count>0) {
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 对XML报文体加密(XML报文体+密钥)
	 * @author LIWENTAO
	 * @param secretKey
	 * @param xmlContentStr
	 * @return
	 */
	public static String md5EncodeRequestXmlBody(String secretKey,String xmlContentStr){
		String plainText = xmlContentStr+secretKey;
		StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
	}
		
	public static String md5EncodeXmlContent(String secretKey,String xmlContentStr){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String md5EncodeXmlContent = md5.encodePassword(secretKey+xmlContentStr, null);
		return md5EncodeXmlContent;
	}
	
	public static String md5EncodeEwealthXml(String secretKey,String XMLStr) throws DocumentException{
		String resultXML = "";
		Document document = DocumentHelper.parseText(XMLStr);
		//获取报文体根节点
		Element rootElement = document.getRootElement();
		//报文头
		Element headerElement = rootElement.element("eWealthHeader");
		//加密标签节点
		Element signElement = headerElement.element("signMsg");
		//报文体
		Element contentElement = rootElement.element("eWealthContent");
		String contentXmlStr = contentElement.asXML();
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String md5EncodeXmlContent = md5.encodePassword(contentXmlStr+secretKey, null);
		//将加密后的报文体放在报文头信息的标签中
		signElement.addText(md5EncodeXmlContent);
		resultXML = document.asXML();
		return resultXML;
	}
	
	/**
	 * 判断请求报文和响应报文是否相同
	 * @param secretKey
	 * @param XMLStr
	 * @return
	 * @throws DocumentException
	 */
	public static boolean checkEwealthXml(String secretKey,String XMLStr) throws DocumentException{
		Document document = DocumentHelper.parseText(XMLStr);
		//获取报文体根节点
		Element rootElement = document.getRootElement();
		//报文头
		Element headerElement = rootElement.element("eWealthHeader");
		//加密标签节点
		Element signElement = headerElement.element("signMsg");
		String signElementText = signElement.getTextTrim();
		//报文体
		Element contentElement = rootElement.element("eWealthContent");
		String contentXmlStr = contentElement.asXML();
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String md5EncodeXmlContent = md5.encodePassword(contentXmlStr+secretKey, null);
		System.out.println("请求密钥-----"+signElementText+"响应密钥"+md5EncodeXmlContent);
		System.out.println("服务端生成MD5：" + md5EncodeXmlContent);
		if (md5EncodeXmlContent!=null&&!"".equals(md5EncodeXmlContent)
				&&signElementText!=null&&!"".equals(signElementText)
					&&md5EncodeXmlContent.equals(signElementText)) {
			
			return true;
		}else{
			return false;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static String createXml(String templateFileName,String farmatId,Map<String, Object> reqDataMap) throws DocumentException{
		String resultXml = "";
		for(File file:fileList){
			String fileName = file.getName();
			if (fileName.indexOf(templateFileName)<0) {
				continue;
			}
			System.out.println("从"+templateFileName+"文件中获取Id="+farmatId+"的XML模板！");
			//获取整个XML文件的内容
			String templateFileContent = fmtFile2String(file);
			//将整个XML文件的内容解析成Document文档
			Document document = DocumentHelper.parseText(templateFileContent);
			//获取整个XML文件的根节点
			Element templateFileRootElement = document.getRootElement();
			//获取所有模板节点
			List<Element> templateElementList = templateFileRootElement.elements();
			//循环所有模板的，获取ID，与传入的参数匹配
			for(Element fmtDefElement:templateElementList){
				//模板的ID
				String templateFormatId = fmtDefElement.attributeValue("id");
				//相等的话找到模板，开始解析模板，生成XML文件
				if(templateFormatId.equals(farmatId)){
					Document xmlDocument = DocumentHelper.createDocument();
					List<Element> rootElementList = fmtDefElement.elements();
					if (rootElementList.size()>1) {
						System.out.println("模板定义错误，模板"+fmtDefElement.attributeValue("id")+"只能有一个根节点");
						return resultXml;
					}
					Element fmtRootElement = rootElementList.get(0);
					String rootElementName = fmtRootElement.attributeValue("tag");
					//生成XML文件的根节点
					Element xmlRootElement = xmlDocument.addElement(rootElementName);
					//生成XML文件的文件体
					createXml(xmlRootElement,fmtRootElement,reqDataMap);
					//生成完成，获取XML文件
					resultXml = xmlDocument.asXML();
				}
			}
		}
		return resultXml;
	}
	
	@SuppressWarnings("unchecked")
	private static void createXml(Element xmlElement,Element templateElement,Map<String, Object> reqDataMap){
		//获取模板元素的所有子元素
		List<Element> elementList = templateElement.elements();
		//循环处理所有子元素
		for(Element element:elementList){
			String elementName = element.getName();
			//子元素标签是OBJECT
			if (elementName.equalsIgnoreCase("OBJECT")) {
				//获取Object标签的tag属性值，并创建节点元素
				String tagName = element.attributeValue("tag");
				String tagDataKey = element.attributeValue("data");
				Element objElement = xmlElement.addElement(tagName);
				if (element.elements().size()>0) {
					if (tagDataKey!=null&&!"".equals(tagDataKey)) {
						if (reqDataMap!=null&&reqDataMap.size()>0&&reqDataMap.containsKey(tagDataKey)) {
							Map<String, Object> tagData = (Map<String, Object>)reqDataMap.get(tagDataKey);
							createXml(objElement,element,tagData);
						}else{
							createXml(objElement,element,null);
						}
					}else{
						createXml(objElement,element,reqDataMap);
					}
				}
			}else if (elementName.equalsIgnoreCase("LOOP")) {
				String tagDataKey = element.attributeValue("data");
				if (element.elements().size()>0) {
					if (reqDataMap!=null&&reqDataMap.size()>0&&reqDataMap.containsKey(tagDataKey)) {
						List<Map<String, Object>> tagDataList = (List<Map<String, Object>>)reqDataMap.get(tagDataKey);
						//创建循环体内的Element元素节点
						for(Map<String, Object> tagData:tagDataList){
							Element loopObjElement = (Element)element.elements().get(0);
							String tagName = loopObjElement.attributeValue("tag");
							Element xmlLoopObjElement = xmlElement.addElement(tagName);
							createXml(xmlLoopObjElement,loopObjElement,tagData);
						}
					}
				}
			}else {
				if (element.elements().size()>0) {
					String tagDataKey = element.attributeValue("data");
					Element dataElement = xmlElement.addElement(element.getName());
					if (tagDataKey!=null&&!"".equals(tagDataKey)) {
						if (reqDataMap!=null&&reqDataMap.size()>0&&reqDataMap.containsKey(tagDataKey)) {
							Map<String, Object> tagData = (Map<String, Object>)reqDataMap.get(tagDataKey);
							createXml(dataElement,element,tagData);
						}else{
							createXml(dataElement,element,null);
						}
					}else{
						createXml(dataElement,element,reqDataMap);
					}
				}else{
					String elementText = element.getText();
					Element dataElement = xmlElement.addElement(element.getName());
					if (elementText!=null&&!"".equals(elementText)) {
						if (elementText.indexOf("${")>-1&&elementText.indexOf("}")>0) {
							String dataKey = elementText.substring(2,elementText.length()-1);
							if (reqDataMap!=null&&reqDataMap.size()>0) {
								if (reqDataMap.containsKey(dataKey)) {
									dataElement.addText(reqDataMap.get(dataKey)!=null?reqDataMap.get(dataKey).toString():"");
								}else{
									dataElement.addText("");
								}
							}else{
								dataElement.addText(elementText);
							}
						}else{
							dataElement.addText(elementText);
						}
					}
				}
			}
		}
	}
	
	/**创建报文**/
	@SuppressWarnings("unchecked")
	public static String createXmlStr(String uploadDir,String templateFileName,String farmatId,Map<String, Object> reqDataMap) throws DocumentException{
		//log.info("templateFileName:" + templateFileName);
		String resultXml = "";
		String pathName = XmlUtils.class.getResource("").getPath();
		//log.info(pathName.substring(0, pathName.indexOf("classes")));
		pathName = uploadDir + "WEB-INF/classes/format/" + templateFileName;
		//log.info("---------pathName:" + pathName);
		File file = new File(pathName);
		//log.info("从" + templateFileName + "文件中获取Id=" + farmatId + "的XML模板！+" + reqDataMap);
		// 获取整个XML文件的内容
		String templateFileContent = fmtFile2String(file);
		//log.info("内容" + templateFileContent);
		// 将整个XML文件的内容解析成Document文档
		Document document = DocumentHelper.parseText(templateFileContent);
		// 获取整个XML文件的根节点
		Element templateFileRootElement = document.getRootElement();
		// 获取所有模板节点
		List<Element> templateElementList = templateFileRootElement.elements();
		// 循环所有模板的，获取ID，与传入的参数匹配
		for (Element fmtDefElement : templateElementList) {
			String templateFormatId = fmtDefElement.attributeValue("id");
			// 相等的话找到模板，开始解析模板，生成XML文件
			if (templateFormatId.equals(farmatId)) {
				Document xmlDocument = DocumentHelper.createDocument();
				List<Element> rootElementList = fmtDefElement.elements();
				if (rootElementList.size() > 1) {
					//log.info("模板定义错误，模板" + fmtDefElement.attributeValue("id") + "只能有一个根节点");
					return resultXml;
				}
				Element fmtRootElement = rootElementList.get(0);
				String rootElementName = fmtRootElement.attributeValue("tag");
				// 生成XML文件的根节点
				Element xmlRootElement = xmlDocument.addElement(rootElementName);
				// 生成XML文件的文件体
				createXml(xmlRootElement, fmtRootElement, reqDataMap);
				// 生成完成，获取XML文件
				resultXml = xmlDocument.asXML();
			}
		}

		//log.info("resultXml:" + resultXml);
		return resultXml;
	}
	
	/**
	 * 將XML文件內容读取出来转换成字符串
	 * @param file
	 * @return
	 */
	public static String fmtFile2String(File file){
		StringBuffer result = new StringBuffer();
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
			String s = null;
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
				result.append(s);
				result.append("\n");
			}
			br.close();    
		}catch(Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}
	/**
	 * 截取报文中的密钥
	 * @param messageXml
	 * @return
	 */
	public static String interceptMessageKey(String messageXml){
		String messageKey = "";
		int signMsgIndexOfLeft = messageXml.indexOf("<signMsg>")+9;
		int signMsgIndexOfRight = messageXml.indexOf("</signMsg>");
		messageKey = messageXml.substring(signMsgIndexOfLeft,signMsgIndexOfRight);
		return messageKey;
	}
	
	public static void main(String[] args) throws DocumentException {
		
		String s = "{\"customerNo\":\"100750\",\"queryDate\":\"2017-05-24 15:36:00\"}"+"wechat";
		String s1 = "{\"customerNo\":\"100047\",\"productId\":\"620\"}"+"wechat";
		String s2 = "{\"customerNo\":\"100047\"}"+"wechat";
		String s3 = "{\"customerName\":\"李远\",\"idNo\":\"210106197910026141\",\"mobile\":\"13898844993\"}"+"wechat";
		System.out.println("***************************" + getMd5(s1));
		
		
		String xmlStr ="<eWealthXml><eWealthHeader><businessCode>1</businessCode><systemCode>1</systemCode><password>1</password><resultCode>1</resultCode><resultDesc>1</resultDesc><signMsg></signMsg><requestCode>1</requestCode></eWealthHeader><eWealthContent><customerName>叶兰</customerName><cumulativeInveset>50000.0</cumulativeInveset><cumulativeToday>50000.0</cumulativeToday><profitAmount>0.0</profitAmount><fixedPdInfo><principal>0</principal><estimateProfit>4000</estimateProfit></fixedPdInfo><floatPdInfo><principal>0</principal><estimateProfit>0</estimateProfit></floatPdInfo></eWealthContent></eWealthXml>";




		System.out.println(md5EncodeEwealthXml("liuyi", xmlStr));
	
	
	}

	/**
	 * MD5加密
	 * @param plainText
	 * @return
	 */
    public static String getMd5(String plainText) {  
    	StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
    
    /**
	 * MD5加密
	 * @param plainText
	 * @return
	 */
    public static String Md5(String plainText) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
    
}

