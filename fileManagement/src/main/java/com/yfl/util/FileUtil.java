package com.yfl.util;

import java.io.File;
import java.text.DecimalFormat;

/**
* @author yfl
*@Description:   此类包含文件一些方法，如剩余空间大小，删除文件夹,文件过滤等
* @date 2018年6月8日 
 */
public class FileUtil {
    /**
     * @param size  传入磁盘字节大小
     * */
	public static String formatDataSize(long size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) return size + "bytes";
        else if (size < Math.pow(1024, 2)) return formater.format(size * Math.pow(1024, -1)) + "KB";
        else if (size < Math.pow(1024, 3)) return formater.format(size * Math.pow(1024, -2)) + "MB";
        else if (size < Math.pow(1024, 4)) return formater.format(size * Math.pow(1024, -3)) + "GB";
        else if (size < Math.pow(1024, 5)) return formater.format(size * Math.pow(1024, -4)) + "TB";
        else return "有这么大的硬盘吗？";
    }
	
	/**
	 * @param dirPath  传入路径
	 * @param isDeleateDir  是否删除目录
	 * */
	public static boolean delFiles(String dirPath, boolean isDeleateDir) {
		boolean flag=false;
	    File dir = new File(dirPath);
	    if (dir.isDirectory()) {
	        File[] files = dir.listFiles();//如果dir是文件的话，dir.listFiles()返回空
	        for (File file : files) {
	            if (file.isDirectory()) {
	                delFiles(file.getAbsolutePath(), isDeleateDir);//递归
	            } else {
	                System.out.println("文件【" + file + "】删除结果：" + file.delete());
	            }
	        }
	        if (isDeleateDir) {
	        	flag =dir.delete();//删除所有内部文件后再删除目录
	        }
	    } else {
	        flag = dir.delete();
	    }
	    return flag;
	}
}
