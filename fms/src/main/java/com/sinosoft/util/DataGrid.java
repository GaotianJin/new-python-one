package com.sinosoft.util;

import java.util.*;

public class DataGrid {
	
	private Integer total = 0;
	private List rows = new ArrayList();
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
