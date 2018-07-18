package com.yfl.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**  
 * @Description:该类为用于添加samba用户所做一些操作的工具类   
 * @author yfl
 * @param  
 * @date 2018年7月13日  
 */
public class AddSambaUser {
    
	/**
	 * linux下执行添加samba用户所执行的一系列操作
	 * @param userName samba账户
	 * @param password samba密码
	 * */
	public void addSambaUser(String userName,String password) {
		try {
			String[] cmd={"/bin/sh","-c","bash /test.sh "+userName+" "+password+""};	
			Process process = Runtime.getRuntime().exec(cmd);
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			LineNumberReader reader = new LineNumberReader(isr);
			String line;
			process.waitFor();
			while ((line=reader.readLine())!=null) {
				System.out.println(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
	   
	}
}
