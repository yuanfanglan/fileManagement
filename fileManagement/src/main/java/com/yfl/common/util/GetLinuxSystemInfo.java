package com.yfl.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**  
 * @Description:该工具类用于获取linux环境下的用户名和系统各类信息
 * @author yfl
 * @date 2018年6月19日  
 */

public class GetLinuxSystemInfo {
    /**
     * 获取系统用户名
     * */
	public static String getUserName() {
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
	
	/**
	   * 内存信息
	   * @throws IOException
	   * @throws InterruptedException
	   */
	  public static Map<String, String> getMemInfo() throws IOException, InterruptedException {
	    File file = new File("/proc/meminfo");
	    BufferedReader br = new BufferedReader(new InputStreamReader(
	        new FileInputStream(file)));
	    Map<String, String> map=new HashMap<>();
	    String str = null;
	    StringTokenizer token = null;
	    while ( (str = br.readLine()) != null) {
	      token = new StringTokenizer(str);
	      if (!token.hasMoreTokens()) {
	        continue;
	      }

	      str = token.nextToken();
	      if (!token.hasMoreTokens()) {
	        continue;
	      }

	      if (str.equalsIgnoreCase("MemTotal:")) {
	        map.put("MemTotal", GetLinuxSystemInfo.formatDataSize(Integer.parseInt(token.nextToken())*1024));
	      }
	      else if (str.equalsIgnoreCase("MemFree:")) {
	    	map.put("MemFree", GetLinuxSystemInfo.formatDataSize(Integer.parseInt(token.nextToken())*1024));
	      }
	      else if (str.equalsIgnoreCase("SwapTotal:")) {
	    	  map.put("SwapTotal", GetLinuxSystemInfo.formatDataSize(Integer.parseInt(token.nextToken())*1024));
	      }
	      else if (str.equalsIgnoreCase("SwapFree:")) {
	    	  map.put("SwapFree", GetLinuxSystemInfo.formatDataSize(Integer.parseInt(token.nextToken())*1024));
	      }
	    }

	    return map;
	  }
      
	  /**
	   * cpu利用率
	   * 计算方法：读取linux /proc/stat的信息，后面有4列数字，依次是user, nice, sys, idle的数值，读取两次，由于两次读取的时间间隔比较短，就近似的认为这就是即时的CPU利用率
	   * CPU占用率：IntCpuRate =(int)rintf(((float)((user_2 +sys_2+nice_2) - (user_1 + sys_1+nice_1))/(float)(total_2 - total_1) )*100) 
	   * */
	  public static String getCpuInfo() throws IOException, InterruptedException {
		    File file = new File("/proc/stat");
		    BufferedReader br = new BufferedReader(new InputStreamReader(
		        new FileInputStream(file)));
		    StringTokenizer token = new StringTokenizer(br.readLine());
		    token.nextToken();
		    int user1 = Integer.parseInt(token.nextToken());
		    int nice1 = Integer.parseInt(token.nextToken());
		    int sys1 = Integer.parseInt(token.nextToken());
		    int idle1 = Integer.parseInt(token.nextToken());

		    Thread.sleep(3000);

		    br = new BufferedReader(
		        new InputStreamReader(new FileInputStream(file)));
		    token = new StringTokenizer(br.readLine());
		    token.nextToken();
		    int user2 = Integer.parseInt(token.nextToken());
		    int nice2 = Integer.parseInt(token.nextToken());
		    int sys2 = Integer.parseInt(token.nextToken());
		    int idle2 = Integer.parseInt(token.nextToken());
		    //保留两位小数
		    DecimalFormat format = new DecimalFormat("####.##");
		    return format.format((float) 100*( (user2 + sys2 + nice2) - (user1 + sys1 + nice1)) /
		            (float) ( (user2 + nice2 + sys2 + idle2) -
		                    (user1 + nice1 + sys1 + idle1)));
		  }

	/**
	 * @param size
	 * 传入磁盘字节大小
	 */
	public static String formatDataSize(long size) {
		DecimalFormat formater = new DecimalFormat("####.00");
		if (size < 1024)
			return size + "bytes";
		else if (size < Math.pow(1024, 2))
			return formater.format(size * Math.pow(1024, -1)) + "KB";
		else if (size < Math.pow(1024, 3))
			return formater.format(size * Math.pow(1024, -2)) + "MB";
		else if (size < Math.pow(1024, 4))
			return formater.format(size * Math.pow(1024, -3)) + "GB";
		else if (size < Math.pow(1024, 5))
			return formater.format(size * Math.pow(1024, -4)) + "TB";
		else
			return "有这么大的硬盘吗？";
	}
	
	//Linux下测试
	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
		    Map<String, String> memInfo = GetLinuxSystemInfo.getMemInfo();
		    System.out.println("MemTotal:" + memInfo.get("MemTotal"));
		    System.out.println("MemFree:" + memInfo.get("MemFree"));
		    System.out.println("SwapTotal:" + memInfo.get("SwapTotal"));
		    System.out.println("SwapFree:" + memInfo.get("SwapFree"));
		    System.out.println("CPU利用率:" + Double.parseDouble(GetLinuxSystemInfo.getCpuInfo())+"%");
		
	}
}
