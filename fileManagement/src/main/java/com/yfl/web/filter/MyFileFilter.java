package com.yfl.web.filter;

import java.io.File;
import java.io.FileFilter;

/**
* @author yfl
*@Description:  文件遍历过滤器
* @date 2018年6月8日 
 */
public class MyFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		//以点开头的隐藏文件进行过滤
		if (file.getName().startsWith(".")||file.getName().startsWith("$")) {
			return false;
		}
		return true;
	}

}
