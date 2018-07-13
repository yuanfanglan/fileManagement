package com.yfl.common.util;

import java.util.ArrayList;
import java.util.List;
import jcifs.smb.SmbFile;

/**  
 * @Description:获得samba共享文件夹，和私有文件夹   
 * @author yfl
 * @param  
 * @date 2018年7月12日  
 */
public class GetShareFolder {
	public static void getShareFolder(String user,String password,String ip,String newDir) throws Exception {
		String url = "smb://"+user+":"+password+"@"+ip+"/";
		SmbFile shareDir = new SmbFile(url);
		if (shareDir.isDirectory()) {
			SmbFile[] listFiles = shareDir.listFiles();
			List<String> list=new ArrayList<>();
			for (SmbFile smbFile : listFiles) {
				if (!smbFile.getName().contains("$")) {
					list.add(smbFile.getName());
				}
			}
			for (String string : list) {
				System.out.println(string);
			}
		} 
	}
	public static void main(String[] args) throws Exception {
		getShareFolder("tom", "tom", "192.168.142.128", "tom");
	}
}
