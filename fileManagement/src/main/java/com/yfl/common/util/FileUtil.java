package com.yfl.common.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import com.sun.tools.corba.se.idl.constExpr.Equal;
import com.yfl.common.constant.FileConstant;
import com.yfl.pojo.MeunTree;

/**
 * @author yfl
 * @Description: 此类包含文件一些方法，如剩余空间大小，删除文件夹,文件过滤等
 * @date 2018年6月8日
 */
public class FileUtil {
	// 定义标量判断有几个同名文件
	private static int theSameFimeNum = 2;
	// 定义标量判断有几个同名文件夹
		private static int theSameFolderNum = 2;
	/**
	 * @param size
	 *            传入磁盘字节大小
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

	/**
	 * @param dirPath
	 *  传入路径
	 * @param isDeleateDir
	 *  是否删除目录
	 */
	public static boolean delFiles(String dirPath, boolean isDeleateDir) {
		boolean flag = false;
		File dir = new File(dirPath);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();// 如果dir是文件的话，dir.listFiles()返回空
			for (File file : files) {
				if (file.isDirectory()) {
					delFiles(file.getAbsolutePath(), isDeleateDir);// 递归
				} else {
					 file.delete();
				}
			}
			if (isDeleateDir) {
				flag = dir.delete();// 删除所有内部文件后再删除目录
			}
		} else {
			flag = dir.delete();
		}
		return flag;
	}

	/**
	 * @Description: 添加文件
	 * @param meuntree 传入tree对象
	 * @throws IOException
	 */
	public static MeunTree appendFile(MeunTree meunTree, String newID) throws IOException {
		// 新文件的父节点
		String pid = meunTree.getId();
		String newName = newFileName(pid, newID);
		MeunTree meunTree2 = new MeunTree();
		File file2 = new File(newName);
		if (!file2.exists()) {
			// 创建文件
			file2.createNewFile();
			// 添加树节点
			meunTree2.setId(newName);
			String[] strings = newName.split(FileConstant.SPITDIAGONAL);
			String text = strings[strings.length - 1];
			meunTree2.setText(text);
			meunTree2.getAttributes().put("pid", pid);
			return meunTree2;
		} else {
			return null;
		}
	}

	// 如果文件名存在，则给新文件名
	public static String newFileName(String pid, String newID) throws IOException {
		String theFirstNewId = pid + FileConstant.NEW_FILE;
		File file = new File(newID);
		//如果存在则给文件新命名
		if (file.exists()) {
			newID = theFirstNewId + theSameFimeNum;
			theSameFimeNum = theSameFimeNum + 1;
			return newFileName(pid, newID);
		} else {
			//成功后数字初始化
			theSameFimeNum = 2;
			return newID;
		}
	}
	
	//添加文件夹
	public static MeunTree appendFolder(MeunTree meunTree, String newID) throws IOException {
		// 新文件的父节点
		String pid = meunTree.getId();
		String newName = newFolderName(pid, newID);
		MeunTree meunTree2 = new MeunTree();
		File file2 = new File(newName);
		if (!file2.exists()) {
			// 创建文件夹
			file2.mkdir();
			// 添加树节点
			meunTree2.setId(newName);
			String[] strings = newName.split(FileConstant.SPITDIAGONAL);
			String text = strings[strings.length - 1];
			meunTree2.setText(text);
			meunTree2.getAttributes().put("pid", pid);
			meunTree2.setState("closed");
			return meunTree2;
		} else {
			return null;
		}
	}
	
	//如果文件夹名存在，则给文件夹重新命名
	public static String newFolderName(String pid, String newID) throws IOException {
		String theFirstNewId = pid + FileConstant.NEW_FOLDER;
		File file = new File(newID);
		//如果存在则给文件新命名
		if (file.exists()) {
			newID = theFirstNewId + theSameFolderNum;
			theSameFolderNum = theSameFolderNum + 1;
			return newFolderName(pid, newID);
		} else {
			//成功后数字初始化
			theSameFolderNum = 2;
			return newID;
		}
	}
	
	public static void main(String[] args) {
		 String string = System.getProperty("file.separator");
		 System.out.println(string);
	}
	
}
