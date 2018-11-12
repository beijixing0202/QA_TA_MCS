package com.bill99.fi.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**对Excel的 读写操作
 * @author kaiquan.jiang
 *
 */
public class Excel {

	private String VALUE="";
	private String FILEPATH="";
	private int SHEETNUM=0;
	public  Excel(String filepath,int sheetnum) {
		this.FILEPATH=filepath;
		this.SHEETNUM=sheetnum;		
			}
	
	
	/**写入excel
	 * @param row 行
	 * @param column 列
	 * @param value 要写入单元格的值
	 */
	public  void setExcel(int row,int column,String value){
		try{
			

				// 创建对Excel工作簿文件的引用
				  POIFSFileSystem fs  =new POIFSFileSystem(new FileInputStream(FILEPATH));  
				  FileOutputStream fileOut =new   FileOutputStream(FILEPATH); 
				  HSSFWorkbook workbook = new HSSFWorkbook(fs);
				  // 也可用getSheetAt(int index)按索引引用， 在Excel文档中，第一张工作表的缺省索引是0，建议使用这个
						HSSFSheet sheet = workbook.getSheetAt(SHEETNUM);		
						HSSFRow rowresult=sheet.getRow(row);
						if(rowresult == null){
						rowresult=sheet.createRow(row);	
						}
						HSSFCell cellresult=rowresult.getCell(column);
						if(cellresult == null){
						cellresult=rowresult.createCell(column);
						}						
						cellresult.setCellType(HSSFCell.CELL_TYPE_BLANK);
						cellresult.setCellValue(value);
						workbook.write(fileOut);  
						fileOut.close();  
							  
			  }catch (IOException ex) {
					
				}
			  	

		
	}	
	
	/**多行值写入excel，用一个二维数组传参
	 * @param row 开始行
	 * @param column 开始列
	 * @param value
	 */
	public  void setExcel(int row,int column,String[][] value){
		try{

				// 创建对Excel工作簿文件的引用
				  POIFSFileSystem fs  =new POIFSFileSystem(new FileInputStream(FILEPATH));  
				  FileOutputStream fileOut =new   FileOutputStream(FILEPATH); 
				  HSSFWorkbook workbook = new HSSFWorkbook(fs);
				  // 也可用getSheetAt(int index)按索引引用， 在Excel文档中，第一张工作表的缺省索引是0，建议使用这个
						HSSFSheet sheet = workbook.getSheetAt(SHEETNUM);		
						for(int i=row;i<value.length+row;i++){
							for(int j=column;j<value[i-row].length+column;j++){
								HSSFRow rowresult=sheet.getRow(i);
								if(rowresult == null){
								rowresult=sheet.createRow(i);	
								}
								HSSFCell cellresult=rowresult.getCell(j);
								if(cellresult == null){
								cellresult=rowresult.createCell(j);
								}						
								cellresult.setCellType(HSSFCell.CELL_TYPE_BLANK);
								cellresult.setCellValue(value[i-row][j-column]);
												
							}						
						}
						workbook.write(fileOut);  
						fileOut.close();  
							  
			  }catch (IOException ex) {
					
				}
			  	

		
	}		
	
	/**读取excel
	 * @param row 行
	 * @param column 列
	 * @return 返回单元格的值
	 * 
	 */
	public  String  getExcel(int row,int column) throws IOException{
		try {
			// 创建对Excel工作簿文件的引用
			  POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(FILEPATH));
			
			  HSSFWorkbook workbook = new HSSFWorkbook(fs);
			  
					// 也可用getSheetAt(int index)按索引引用， 在Excel文档中，第一张工作表的缺省索引是0，建议使用这个
					HSSFSheet sheet = workbook.getSheetAt(SHEETNUM);
					// 读第几行，从0开始。
					HSSFRow Row = sheet.getRow(row);
					// 读第几列，从0开始。
					HSSFCell cellvaluee = Row.getCell(column);// 
					// 获取目标单元格的值
					VALUE= cellvaluee.getStringCellValue();
					


					
		  } catch (IOException ex) {
					
			} 
		  return VALUE;
	}	
	
	
	public int getRowNum() throws FileNotFoundException, IOException{
		
		
		  POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(FILEPATH));			
		  HSSFWorkbook workbook = new HSSFWorkbook(fs);
		  // 也可用getSheetAt(int index)按索引引用， 在Excel文档中，第一张工作表的缺省索引是0，建议使用这个
		  HSSFSheet sheet = workbook.getSheetAt(SHEETNUM);
		
		//int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();//获得总列数
		 return sheet.getLastRowNum();//获得总行数
	}

	public static void main(String[] args) throws IOException {
		String[][] bankorderid={{"1","2","3"},{"1","2","3"},{"1","2","3"},{"1","2","3"}};
		new  Excel("D:\\TA\\resource\\批量补单模板.xls",0).setExcel(3, 0, bankorderid);
		System.out.println(new  Excel("D:\\TA\\resource\\批量补单模板.xls",0).getExcel(0, 0));

		
		

	}

}
