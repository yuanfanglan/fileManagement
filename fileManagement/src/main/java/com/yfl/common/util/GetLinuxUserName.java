package com.yfl.common.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**  
 * @Description:该工具类用于获取linux环境下的用户名
 * @author yfl
 * @date 2018年6月19日  
 */
public class GetLinuxUserName {
	public static String exec() {
		try {
			//linux下执行who命令获取用户名，并取第一个值
			String[] cmd = { "/bin/sh", "-c", "who" };
			Process process = Runtime.getRuntime().exec(cmd);
			LineNumberReader br = new LineNumberReader(new InputStreamReader(
					process.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			String[] splited = sb.toString().split("\\s+");
			return splited[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
