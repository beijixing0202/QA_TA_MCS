package com.bill99.cps.common.tools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelProvider implements Iterator<Object[]> {

	private Workbook book = null;
	private Sheet sheet = null;
	private int rowNum = 0;	//总行数
	private int curRowNo = 0; // 当前行的行号
	private int columnNum = 0;	// 总列数
	private String[] columnnName;	// 列名

	public ExcelProvider(Object aa, String methodname)
		 {
		
		
		// 读取文件
		try {
			this.book = Workbook.getWorkbook(new File(new File("./").getCanonicalPath()  
							+ "\\src\\main\\resources\\datadriver\\excel\\" + aa.getClass().getName().replaceAll("\\.", "/")
							+ ".xls"));
			

			this.sheet = book.getSheet(methodname);
			// 得到行数
			this.rowNum = sheet.getRows();
			// 得到第一行数据，将第一行的数据作为map的key
			Cell[] c = sheet.getRow(0);
			this.columnNum = c.length;
			columnnName = new String[c.length];
			for (int i = 0; i < c.length; i++) {
				columnnName[i] = c[i].getContents().toString();
			}		
			// 当前行的行号++
			this.curRowNo++;
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 是否有下一行
	 */
	public boolean hasNext() {
		if (this.rowNum == 0 || this.curRowNo >= this.rowNum) {
			book.close();
			return false;
		} else
			return true;
	}

	/**
	 * 得到下一行
	 */
	public Object[] next() {
		Cell[] c = sheet.getRow(this.curRowNo);
		Map<String, String> s = new HashMap<String, String>();
		
		for (int i = 0; i < this.columnNum; i++) {	
			s.put(this.columnnName[i], c[i].getContents().toString());
		}
		this.curRowNo++;
		return new Object[]{s};
	}

	/**
	 * 不使用remove，未实现
	 */
	public void remove() {
		throw new UnsupportedOperationException("remove unsupported.");
	}
}