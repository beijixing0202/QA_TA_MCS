package com.bill99.fi.common.helper;
import java.io.File;     
import java.util.Map; 
import jxl.CellType;    
import jxl.Workbook;    
import jxl.write.Label;   
public class ExcelUtil {
	
	  
	    public void editOfflineFile(Map<String,String> data)    
	  
	    {   
	  
	        jxl.Workbook readwb = null;   
	  
	        try    
	  
	        {  
	  
	            //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄   
	  
	            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(   
	  
	                    "src/mian/resources/datadriver.stage2/T/excel/com/bill99/fi/test/Offline.xls"), readwb);   
	  
	            //读取第一张工作表   
	  
	            jxl.write.WritableSheet ws = wwb.getSheet(0);   
	  
	            //获得第一个单元格对象   
	  
	            jxl.write.WritableCell batchId1 = ws.getWritableCell(0, 0);   
	  
	            //判断单元格的类型, 做出相应的转化   
	  
	            if (batchId1.getType() == CellType.LABEL)    
	  
	            {   
	  
	                Label l = (Label) batchId1;   
	  
	                l.setString(data.get("batchId"));   
	  
	            }   
	            jxl.write.WritableCell Code = ws.getWritableCell(1, 0);   
	      	  
	            //判断单元格的类型, 做出相应的转化   
	  
	            if (Code.getType() == CellType.LABEL)    
	  
	            {   
	  
	                Label l = (Label) Code;   
	  
	                l.setString(data.get("Code"));   
	  
	            }   
	            
	  
	            //写入Excel对象   
	  
	            wwb.write();   
	  
	            wwb.close();   
	  
	        } catch (Exception e) {   
	  
	            e.printStackTrace();   
	  
	        
	  
	  
	  
	}   
	    }}
