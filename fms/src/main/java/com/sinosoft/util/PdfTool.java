package com.sinosoft.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;


public class PdfTool {

	@SuppressWarnings("unchecked")
	public static void setAllPropertyFont(AcroFields fields,BaseFont font){
		Map<String,String> fieldMap = fields.getFields();
		for(String fieldName:fieldMap.keySet()){
			fields.setFieldProperty(fieldName,"textfont", font, null);
		}
	}
	
	public static void createSubscriptionComfirmPdf(){
		String path = PdfTool.class.getClassLoader().getResource("").getPath()+"pdfTemplate/subscriptionConfirm.pdf";
		try {
			PdfReader pdfReader = new PdfReader(path);
			Map<String, Object> pdfDataMap = new HashMap<String, Object>();
			pdfDataMap.put("productName", "【鲸韵文化并购1号投资基金】");
			pdfDataMap.put("custName", "王小明");
			pdfDataMap.put("subscriptionAmountUpper", "壹佰万元整");
			pdfDataMap.put("subscriptionAmountLower", "1,000,000.00");
			pdfDataMap.put("custAccName", "王小明");
			pdfDataMap.put("custAccNo", "6225882134567898");
			pdfDataMap.put("custAccBankName", "招商银行杭州分行");
			pdfDataMap.put("agentName", "李文涛");
			pdfDataMap.put("printDate", "2016年1月20日");
			
			
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String targetPath = "D:/usr/pdf/"+fileName+".pdf";
			PdfStamper ps = new PdfStamper(pdfReader, new FileOutputStream(targetPath));
			AcroFields fields = ps.getAcroFields();
			String fontPath =PdfTool.class.getClassLoader().getResource("").getPath()+"simhei.ttf";
			BaseFont bfChinese=BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			
			//BaseFont bfChinese = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); 
			//Font chineseFont= new Font(bfChinese, 12, Font.BOLD);
			
			setAllPropertyFont(fields,bfChinese);
			
			// 替换PDF文件里面的标签字段
			for (String key: pdfDataMap.keySet()) {
				Object value = pdfDataMap.get(key);
				//Paragraph t = new Paragraph(value, chineseFont);
				//t.setAlignment(Paragraph.ALIGN_CENTER);
				fields.setField(key,value.toString());
			}
			
			
			PdfContentByte cb = ps.getOverContent(1);
			Barcode128 code128ext = new Barcode128();
			code128ext.setCode("1201608080001");
			code128ext.setStartStopText(false);
			code128ext.setSize(10);
			code128ext.setExtended(true);
			code128ext.setTextAlignment(Element.ALIGN_CENTER);
			Image img = code128ext.createImageWithBarcode(cb, null, null);
			img.setAbsolutePosition(450, 758);
			cb.addImage(img);
			
			
			ps.setFormFlattening(true);
			ps.close();
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		createSubscriptionComfirmPdf();
		
		//String path = System.getProperty("fms.webroot");
		
		
		System.out.println(PdfTool.class.getClassLoader().getResource("").getPath());
		
		
		//path = path+"/WEB-INF/classes/pdfTemplate/wyb_confirm.pdf";
		
		/*String path = PdfTool.class.getClassLoader().getResource("").getPath()+"pdfTemplate/subscriptionApply.pdf";
		try {
			PdfReader pdfReader = new PdfReader(path);
			
			
			
			//Barcode128 code128 = new Barcode128();

			//code128.setCode("1201608080001");

			//Image image128 = code128.createImageWithBarcode(cd, null, null);
			//Image image128 = code128.createImageWithBarcode(null, null, null);
			
			Map<String, Object> pdfDataMap = new HashMap<String, Object>();
			pdfDataMap.put("productName", "巨鲸财富产品测试");
			pdfDataMap.put("tradeNo", "1201608080001");
			pdfDataMap.put("beneficialType", "A类");
			pdfDataMap.put("foundDate", "2016年1月1日");
			pdfDataMap.put("closedPeriod", "24月");
			pdfDataMap.put("expectFeeRate", "9.8%");
			pdfDataMap.put("custName", "王小明");
			pdfDataMap.put("birthday", "1980年10月12日");
			pdfDataMap.put("custSex", "男");
			pdfDataMap.put("idType", "身份证");
			pdfDataMap.put("idValidDate", "2020年10月22日");
			pdfDataMap.put("IDNo", "430822198010120098");
			pdfDataMap.put("currency", "人民币");
			pdfDataMap.put("subscriptionAmountUpper", "壹佰万元整");
			pdfDataMap.put("subscriptionAmountLower", "1,000,000.00");
			pdfDataMap.put("subscriptionFeeUpper", "壹万元整");
			pdfDataMap.put("subscriptionFeeLower", "10,000.00");
			pdfDataMap.put("custAccName", "王小明");
			pdfDataMap.put("custAccNo", "6225882134567898");
			pdfDataMap.put("custAccBank", "招商银行杭州分行");
			
			pdfDataMap.put("custMobile", "13838389038");
			pdfDataMap.put("custAddress", "浙江省杭州市西湖区求是路8号公元大厦南楼1503室");
			pdfDataMap.put("custEmail", "wangxiaoming@163.com");
			pdfDataMap.put("zipcode", "310013");
			
			pdfDataMap.put("applyDate", "2015年12月28日");
			pdfDataMap.put("agentName", "发大财");
			
			
			//ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//输出到一个已经存在的地址
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String targetPath = "D:/usr/pdf/"+fileName+".pdf";
			PdfStamper ps = new PdfStamper(pdfReader, new FileOutputStream(targetPath));
			AcroFields fields = ps.getAcroFields();
			
			
			BaseFont bfChinese = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); 
			//Font chineseFont= new Font(bfChinese, 12, Font.BOLD);
			
			setAllPropertyFont(fields,bfChinese);
			
			
			
			//fields.set
			
			
			//为有可能替换为中文的标签进行编码设置，以免乱码
			fields.setFieldProperty("custName","textfont", bfChinese, null);
			fields.setFieldProperty("productName","textfont", bfChinese, null);
			fields.setFieldProperty("subscriptionFeeUpper","textfont", bfChinese, null);
			fields.setFieldProperty("custAccName","textfont", bfChinese, null);
			fields.setFieldProperty("custAccBank","textfont", bfChinese, null);
			
			// 替换PDF文件里面的标签字段
			for (String key: pdfDataMap.keySet()) {
				Object value = pdfDataMap.get(key);
				//Paragraph t = new Paragraph(value, chineseFont);
				//t.setAlignment(Paragraph.ALIGN_CENTER);
				fields.setField(key,value.toString());
			}
			//添加条形码
			// 获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上,参数1表示在第一页生成条形码.
			PdfContentByte cb = ps.getOverContent(1);
			Barcode128 code128ext = new Barcode128();
			code128ext.setCode("1201608080001");
			code128ext.setStartStopText(false);
			code128ext.setSize(10);
			code128ext.setExtended(true);
			code128ext.setTextAlignment(Element.ALIGN_CENTER);
			Image img = code128ext.createImageWithBarcode(cb, null, null);
			img.setAbsolutePosition(450, 758);
			cb.addImage(img);
			
			//stamper.close();
			//不能缺少
			ps.setFormFlattening(true);

			ps.close();
			
			//OutputStream fos = new FileOutputStream(targetPath);
			//fos.write(bos.toByteArray());

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		
		
		
		
		
	}
	
}
