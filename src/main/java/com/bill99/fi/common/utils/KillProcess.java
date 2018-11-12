package com.bill99.fi.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.testng.Reporter;

import shelper.environment.Environment;

/**强制结束进程
 * @author KAIQUAN.JIANG
 *
 */
public class KillProcess {

	
	/**多个进程之间用|隔开
	 * @param str
	 */
	public void KillProcessname(String str){
		if(null!=str){
		String killprocess="taskkill /F /IM ";
		String[] tempstr=str.split("\\|");
		for(String s:tempstr){
			
				try {				
				Runtime.getRuntime().exec(killprocess+"\""+s+"\"");
				Reporter.log("初始化环境，结束进程："+s);
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		if(str.equals("IEDriverServer.exe")){
		new KillProcess().temp();
		}
		}	
	}
	
	

		public  void temp(){		
			
		String killprocess="taskkill /F /IM IEDriverServer.exe";
		try {
			String cmd = "tasklist /nh /FI \"IMAGENAME eq IEDriverServer*\""; 
			Process	process = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line =null; 
			while ((line=input.readLine())!= null) {
					Reporter.log(line, true);				
					Runtime.getRuntime().exec(killprocess); 					
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		public static void main(String[] args) {
			new KillProcess().temp();	
//			System.out.println("IEDriverServer.exe|iexplorer.exe".equals("IEDriverServer.exe"));
			
		}

}