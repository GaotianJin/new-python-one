package com.sinosoft.util;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;

public class ExcelCells {
    public final static String regex = "\\$\\{[^\\}]+\\}";
    private Sheet sheet ;
    private Cell cell ;
    private int sheetIndex;
    
    public ExcelCells(){}

    public ExcelCells(Sheet sheet,Cell cell){
        this.sheet = sheet;
        this.cell = cell;
    }
    public ExcelCells(Sheet sheet,Cell cell,int sheetIndex){
        this.sheet = sheet;
        this.cell = cell;
        this.sheetIndex = sheetIndex;
    }
    
    public Sheet getSheet() {
        return sheet;
    }
    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
    public Cell getCell() {
        return cell;
    }
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    public int getSheetIndex() {
        return sheetIndex;
    }
    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    /**
     * 获取当前列当中【表达式】集合
     * @return
     */
    @SuppressWarnings("static-access")
	public List<String> getExps(){
        List<String> list = new ArrayList<String>();
        if(this.cell!=null){
            String contents = this.cell.getContents();
            if(!contents.isEmpty()){
                list = StringUtils.search(this.regex, contents);
            }
        }
        return list;
    }
    
    public String getFomatContext(){
        String contents = this.cell.getContents();
        //while(contents.)
        return contents;
    }
    
}