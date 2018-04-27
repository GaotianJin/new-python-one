package com.sinosoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;

import jxl.write.WritableWorkbook;


public class ExcelTool {

    /**
     * 导出 Excel
     * 
     * @param template Excel模板
     * @param datas 数据
     * @return
     */
    public static FileInputStream exportExcel(String template, Map<String, Object> datas) {
    	//返回的文件流
        FileInputStream fis = null;
        //模板文件输入流
        InputStream is = FileTool.getFileInputStream(template);
        try {
            if (is != null) {
                Workbook book = Workbook.getWorkbook(is);
                //创建临时文件
                File tempFile = File.createTempFile("temp", ".xls");
                //创建一个工作区
                WritableWorkbook wWorkbook = Workbook.createWorkbook(tempFile, book);
                //处理【表达式】类型的数据
                generateExpData(book, wWorkbook, datas);
                //处理【循环结果集】类型的数据
                generateEachData(book, wWorkbook, datas);
                //数据写入
                wWorkbook.write();
                wWorkbook.close();
                //获取生成的文件流
                fis = new FileInputStream(tempFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(is!=null){
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return fis;
    }

    
    /**
     * 处理【表达式】类型的数据。
     * 
     * @param book【模板】对象
     * @param wWorkbook 根据模板创建的【新数据文件】对象
     */
    private static void generateExpData(Workbook book, WritableWorkbook wWorkbook, Map<String, Object> datas) throws Exception {
        List<ExcelCells> expcells = search("${", book);
        for (ExcelCells cell : expcells) {
            wWorkbook.getSheet(cell.getSheetIndex()).addCell(getValueByExp(cell, datas));
        }
    }
    

    /**
     * 处理【循环结果集】类型的数据
     * 
     * @param book
     *            【模板】对象
     * @param wWorkbook
     *            根据模板创建的【新数据文件】对象
     */
    @SuppressWarnings("unchecked")
	private static void generateEachData(Workbook book, WritableWorkbook wWorkbook, Map<String, Object> datas) throws Exception {
        List<ExcelCells> eachExcelCells = search("each.", book);
        // 先对模板列对象，进行分组
        Map<String, List<ExcelCells>> map = new LinkedHashMap<String, List<ExcelCells>>();
        for (ExcelCells cell : eachExcelCells) {
            String[] array = cell.getCell().getContents().trim().split("\\.");
            if (array.length >= 3) {
                List<ExcelCells> list = map.get(array[0] + "." + array[1]);
                if (list == null) {
                    list = new ArrayList<ExcelCells>();
                    map.put(array[0] + "." + array[1], list);
                }
                list.add(cell);
            }
        }

        Iterator<String> iterator = map.keySet().iterator();
        //标识当前工作表新增了多少条数据。
        int insertrow = 0;
        //标识上一次工作表的下标。
        int lastSheetIndex = -1;

        while (iterator.hasNext()) {
            List<ExcelCells> list = map.get(iterator.next());
            //获取面板下标
            int sheetIndex = list.get(0).getSheetIndex();
            //当切换工作表事  insertrow 清 0
            if(lastSheetIndex != -1 && lastSheetIndex != sheetIndex){
            	insertrow = 0;
            }
            lastSheetIndex = sheetIndex;
            //获取开始行下标
            int startRow = list.get(0).getCell().getRow() + insertrow;
            String[] array = list.get(0).getCell().getContents().trim().split("\\.");
            if (array.length > 0) {
                Object data = datas.get(array[1]);
                if (data != null && !data.getClass().getName().equals(List.class.getName()) && !data.getClass().getName().equals(ArrayList.class.getName())) {
                    throw new Exception("数据：" + array[1] + "不是一个集合类！");
                }
                List<Object> rowsData = (List<Object>) data;
                // 有数据时。
                if (rowsData != null && rowsData.size() > 0) {
                    for (int i = 0; i < rowsData.size(); i++) {
                        // 第一行数据，覆盖模板位置，所以不需要创建新行 
                        if (i == 0) {
                            for (ExcelCells cell : list) {
                                wWorkbook.getSheet(sheetIndex).addCell(getValueByEach(cell, rowsData.get(i), startRow, cell.getCell().getColumn()));
                            }
                            continue;
                        }
                        // 创建新行 
                        wWorkbook.getSheet(sheetIndex).insertRow(startRow + i);
                        for (ExcelCells cell : list) {
                            wWorkbook.getSheet(sheetIndex).addCell(getValueByEach(cell, rowsData.get(i), startRow + i, cell.getCell().getColumn()));
                        }
                        insertrow++;
                    }
                }
                // 无数据时。
                else {
                    for (ExcelCells cell : list) {
                        wWorkbook.getSheet(sheetIndex).addCell(getValueByEach(cell, null, startRow, cell.getCell().getColumn()));
                    }
                }
            }
        }

    }

    /**
     * 根据【表达式】从数据集中获取相应数据。
     * 
     * @param exp 表达式
     * @param datas 数据集
     * @return
     */
    @SuppressWarnings("el-syntax")
    public static WritableCell getValueByExp(ExcelCells cells, Map<String, Object> datas) {
        WritableCell writableCell = null;
        //获取表达式集合
        List<String> exps = cells.getExps();
        //模板原内容
        String old_c = cells.getCell().getContents();

        for (String exp : exps) {
            String[] names = exp.replace("${", "").replace("}", "").split("\\.");
            Object object = null;
            for (String name : names) {
                if (object == null){
                	object = ObjectCustomUtil.getValueByFieldName(name, datas);
                }else{
                	object = ObjectCustomUtil.getValueByFieldName(name, object);
                }
            }
            // ${asd.sdfa}
            if (!old_c.isEmpty()) {
                while (old_c.indexOf(exp) != -1){
                    old_c = old_c.replace(exp, object.toString());
                }
            }
        }

        writableCell = getWritableCellByObject(cells.getCell().getRow(), cells.getCell().getColumn(), old_c);
        writableCell.setCellFormat(cells.getCell().getCellFormat());

        return writableCell;
    }
    
    

    /**
     * 根据【Each表达式】从数据集中获取相应数据。
     * 
     * @param exp 表达式
     * @param datas 数据集
     * @return
     */
    public static WritableCell getValueByEach(ExcelCells cells, Object datas, int rows, int column) {
        WritableCell writableCell = null;

        if (datas != null) {
            //获取表达式集合
            String[] exps = cells.getCell().getContents().trim().split("\\.");

            Object object = null;
            for (int i = 2; i < exps.length; i++) {
                if (object == null){
                	object = ObjectCustomUtil.getValueByFieldName(exps[i], datas);
                }
                else{
                	object = ObjectCustomUtil.getValueByFieldName(exps[i], object);
                }
            }
            writableCell = getWritableCellByObject(rows, column, object);
        } else {
            writableCell = getWritableCellByObject(rows, column, null);
        }
        writableCell.setCellFormat(cells.getCell().getCellFormat());

        return writableCell;
    }

    /**
     * 根据提供的【列标】、【行标】、【对象值】构建一个Excel列对象。
     * 
     * @param beginRow
     *            【行标】
     * @param beginColumn
     *            【列标】
     * @param obj
     *            【对象值】
     * @return
     */
    public static WritableCell getWritableCellByObject(int beginRow, int beginColumn, Object obj) {
        WritableCell cell = null;

        if (obj == null)
            return new Label(beginColumn, beginRow, "");
        if (obj.getClass().getName().equals(String.class.getName())) {
            cell = new Label(beginColumn, beginRow, obj.toString());
        } else if (obj.getClass().getName().equals(int.class.getName()) || obj.getClass().getName().equals(Integer.class.getName())) {
            // jxl.write.Number
            cell = new Number(beginColumn, beginRow, Integer.parseInt(obj.toString()));
        } else if (obj.getClass().getName().equals(float.class.getName()) || obj.getClass().getName().equals(Float.class.getName())) {
            cell = new Number(beginColumn, beginRow, Float.parseFloat(obj.toString()));
        } else if (obj.getClass().getName().equals(double.class.getName()) || obj.getClass().getName().equals(Double.class.getName())) {
            cell = new Number(beginColumn, beginRow, Double.parseDouble(obj.toString()));
        } else if (obj.getClass().getName().equals(long.class.getName()) || obj.getClass().getName().equals(Long.class.getName())) {
            cell = new Number(beginColumn, beginRow, Long.parseLong(obj.toString()));
        } else if (obj.getClass().getName().equals(Date.class.getName())) {
            cell = new DateTime(beginColumn, beginRow, (Date)obj);
        } else {
            cell = new Label(beginColumn, beginRow, obj.toString());
        }
        return cell;
    }


    /**
     * 查找包含某字符所有的列对象。
     * 
     * @param text【文本】
     * @param book【Excel对象】
     * @return
     */
    public static List<ExcelCells> search(String text, Workbook book) {
        List<ExcelCells> cellsList = new ArrayList<ExcelCells>();

        Sheet[] sheets = book.getSheets();
        if (sheets != null)
            for (Sheet sheet : sheets) {
                if (sheet != null) {
                    int rows = sheet.getRows();
                    if (rows > 0) {
                        for (int i = 0; i < rows; i++) {
                            Cell[] cells = sheet.getRow(i);
                            if (cells != null) {
                                for (Cell cell : cells) {
                                    if (cell != null && !StringUtils.isNull(cell.getContents())) {
                                        String contents = cell.getContents();
                                        if (contents.indexOf(text) != -1){
                                        	cellsList.add(new ExcelCells(sheet, cell));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return cellsList;
    }

}
