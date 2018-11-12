package com.bill99.fi.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Reporter;


public class GetMobileValidateCode {
	private static Connection conn=null;//数据库连接对象
	private static String str=null;
	
	public GetMobileValidateCode(){
		try {
			//驱动
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.138:1530:billdb","QAMODIFY","J4dhclYx");
		} catch (SQLException e) {
			Reporter.log("数据库连接失败。");
		}
	}
	
	private   void queryCode(String orderid){
		try {
			PreparedStatement	pstmt = conn.prepareStatement("Select content  from seashell.SMSNOTIFICATIONLOG t  Where  t.appid=?");
			pstmt.setString(1, orderid);
			ResultSet rset= pstmt.executeQuery();

			while(rset.next()){
			str=rset.getString("content");// print the first column
//			System.out.println(str);
			}
			
			rset.close();
			pstmt.close();

			} catch (SQLException e) {
				System.out.println("查询失败。");
			}
	}
	

	public  String getValidateCode(String orderid){
		str=null;
		int i=1;
		do{
			try {
				Thread.sleep(i*500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new GetMobileValidateCode().queryCode(orderid);
			i++;
		}while(str==null&&i<10);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(str.split("验证码：|。")[1]);
		return str.split("验证码：|。")[1];
	}
}
