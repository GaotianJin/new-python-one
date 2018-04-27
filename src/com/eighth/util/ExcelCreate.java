package com.eighth.util;


import java.io.IOException;

import java.util.List;


import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.eighth.entity.Students;

/**
 * Workbook: EXCEL文件
 * Sheet:  EXCEL文件中的一个sheet表格
 * Row:  sheet表中的一行
 * Cell: sheet表中的一个单元格
 *
 */
public class ExcelCreate {
	/**
	 * 创建EXCEL文件,并把集合数据写入
	 * @param list
	 * @throws IOException
	 */
	public static void createExcel(List<Students> list,ServletOutputStream out) throws IOException {
		int total=list.size();//所有数据行数
		System.out.println("所有数据的行数=> "+total);
		//如果需要下载的数据条数大于 65535 行
		//分多个EXCEL文件,打包压缩.
		//分多个sheet
		HSSFWorkbook workbook=null;
		workbook=createWorkbook(list);
		workbook.write(out);
		out.flush();
		out.close();
	}
		public static HSSFWorkbook createWorkbook(List<Students> studentList) {
			HSSFWorkbook workbook=new HSSFWorkbook();//构造一个xlx后缀的EXCEL文件对象
			HSSFSheet sheet=workbook.createSheet("studentData");//创建一个sheet表,并设置表名称
			//创建表头字段
			HSSFRow row=sheet.createRow(0);//创建索引为0的Row对象
			String[] header={"姓名","年龄","性别","学员电话","学历","个人状态","来源渠道","来源网址","来源关键词","所在区域","录入人Id","咨询师Id","咨询师姓名","学员QQ","学员微信","备注","创建时间","课程方向","是否有效","打分","是否回访","首次回访时间","是否上门","上门时间","无效原因","是否付款","付款时间","付款金额","是否退费","是否进班","进班时间","进班备注","咨询师备注","来源部门","学员关注","是否报备","咨询师填写的姓名","录入人姓名","退费原因","预付定金","预付定金时间","是否删除"};
			int head_length=header.length;
			HSSFCell[] cells=new HSSFCell[head_length];
			//填入表头的值
			for (int i=0;i<cells.length;i++) {
				cells[i]=row.createCell(i);//创建一个单元格对象
				cells[i].setCellType(1);//设置单元格类型为文本
				cells[i].setCellValue(header[i]);
			}
			for (int i = 0; i < studentList.size(); i++) {
				row = getRtData(sheet, i+1,studentList.get(i),head_length);
			}
			return workbook;
		}
		private static HSSFRow getRtData(HSSFSheet sheet,int i,Students student,int head_length) {
			HSSFRow  row = sheet.createRow(i);
			//设计cell的格式
			for (int j = 0; j < head_length; j++) {
				/*cells[i]=getCell(row, i);
				cells[i].setCellType(1);*/
				row.createCell(j);//创建一个单元格对象
				row.getCell(j).setCellType(1);//设置单元格类型为文本
			}
			//Students student=studentList.get(i-1);//从下标为0开始
			row.getCell(0).setCellValue(student.getStuName());
			if(student.getStuAge()==null){
				row.getCell(1).setCellValue("");
			}else{
				row.getCell(1).setCellValue(student.getStuAge());
			}
			row.getCell(2).setCellValue(student.getStuSex());
			row.getCell(3).setCellValue(student.getStuPhone());
			row.getCell(4).setCellValue(student.getStuStatus());
			row.getCell(5).setCellValue(student.getPerState());
			row.getCell(6).setCellValue(student.getMsgSource());
			row.getCell(7).setCellValue(student.getSourceUrl());
			row.getCell(8).setCellValue(student.getSourceKeyWord());
			row.getCell(9).setCellValue(student.getStuAddress());
			row.getCell(10).setCellValue(student.getNetPusherId());
			row.getCell(11).setCellValue(student.getAskerId());
			row.getCell(12).setCellValue(student.getAskerName());
			row.getCell(13).setCellValue(student.getStuQQ());
			row.getCell(14).setCellValue(student.getStuWeiXin());
			row.getCell(15).setCellValue(student.getContent());
			row.getCell(16).setCellValue(DateUtil.setDateToString(student.getCreateTime()));
			row.getCell(17).setCellValue(student.getLearnForward());
			row.getCell(18).setCellValue(student.getIsValid());
			row.getCell(19).setCellValue(student.getRecord());
			row.getCell(20).setCellValue(student.getIsReturnVist());
			if(student.getFirstVisitTime()==null)
				row.getCell(21).setCellValue("");
			else
				row.getCell(21).setCellValue(DateUtil.setDateToString(student.getFirstVisitTime()));
			row.getCell(22).setCellValue(student.getIsHome());
			if(student.getHomeTime()==null)
				row.getCell(23).setCellValue("");
			else
				row.getCell(23).setCellValue(DateUtil.setDateToString(student.getHomeTime()));
			row.getCell(24).setCellValue(student.getLostValid());
			row.getCell(25).setCellValue(student.getIsPay());
			if(student.getPayTime()==null)
				row.getCell(26).setCellValue("");
			else
				row.getCell(26).setCellValue(DateUtil.setDateToString(student.getPayTime()));
			row.getCell(27).setCellValue(student.getMoney().toString());
			row.getCell(28).setCellValue(student.getIsReturnMoney());
			row.getCell(29).setCellValue(student.getIsInClass());
			if(student.getInClassTime()==null)
				row.getCell(30).setCellValue("");
			else
				row.getCell(30).setCellValue(DateUtil.setDateToString(student.getInClassTime()));
			row.getCell(31).setCellValue(student.getInClassContent());
			row.getCell(32).setCellValue(student.getAskerContent());
			row.getCell(33).setCellValue(student.getFromPart());
			row.getCell(34).setCellValue(student.getStuConcern());
			row.getCell(35).setCellValue(student.getIsBaoBei());
			row.getCell(36).setCellValue(student.getZiXunName());
			row.getCell(37).setCellValue(student.getCreateUser());
			row.getCell(38).setCellValue(student.getReturnMoneyReason());
			row.getCell(39).setCellValue(student.getPreMoney().toString());
			if(student.getPreMoneyTime()==null)
				row.getCell(40).setCellValue("");
			else
				row.getCell(40).setCellValue(DateUtil.setDateToString(student.getPreMoneyTime()));
			row.getCell(41).setCellValue(student.getStuIsDel());
			return row;
		}
}