package com.yfl.common.util;

import java.io.File;
import java.util.LinkedList;
import java.util.Map;

import com.yfl.common.constant.FileConstant;
import com.yfl.pojo.MeunTree;
import com.yfl.web.filter.MyFileFilter;

/**
 * @author yfl
 * @Description: 此类为树节点的工具类
 * @date 2018年6月8日
 */
public class MyTreeUtil {
	// 子节点遍历
	public static LinkedList<MeunTree> rootsData() {
		// 创建一个LinkedList用于存放根节点
		LinkedList<MeunTree> meunTrees = new LinkedList<>();
		File[] files = File.listRoots();
		for (int i = 0; i < files.length; i++) {
			MeunTree meunTree = new MeunTree();
			meunTree.setId(files[i].getAbsolutePath());
			meunTree.setText(files[i].getAbsolutePath());
			meunTree.getAttributes().put("pid", null);
			meunTree.setState("closed");
			meunTrees.add(meunTree);
		}
		return meunTrees;
	}

	// 根据传入的路径遍历其下一节子节点
	public static LinkedList<MeunTree> treeData(String pathname) {
		// 创建一个LinkedList用于存放子节点
		LinkedList<MeunTree> meunTrees = new LinkedList<>();
		File file = new File(pathname);
		// 如果文件不存在，返回空
		if (!file.exists()) {
			return null;
		}
		File[] files = file.listFiles(new MyFileFilter());
		for (int i = 0; i < files.length; i++) {
			MeunTree meunTree = new MeunTree();
			if (files[i].isDirectory()) {
				meunTree.setState("closed");
			}
			meunTree.getAttributes().put("pid", pathname);
			meunTree.setId(files[i].getAbsolutePath());
			// 对路径进行分割，只显示路径最后部分作为名字。
			String[] strings = files[i].getAbsolutePath().split("\\\\");
			String text = strings[strings.length - 1];
			meunTree.setText(text);
			meunTrees.add(meunTree);
		}
		return meunTrees;
	}

	// 获取默认文件夹树
	public static LinkedList<MeunTree> defaultRoots() {
		// 获取电脑username
		Map<String, String> map = System.getenv();
		String username = map.get("USERNAME");
		LinkedList<MeunTree> meunTrees = new LinkedList<>();
		// 默认文件夹的位置
		String defaultPath = FileConstant.DEFAULT_PATH + "\\" + username;
		File file = new File(defaultPath);
		if (file.exists()) {
			File[] files = file.listFiles(new MyFileFilter());
			for (int i = 0; i < files.length; i++) {
				// 定义默认文件夹位置
				if (files[i].getAbsolutePath().equals(defaultPath + "\\Pictures")
						|| files[i].getAbsolutePath().equals(defaultPath + "\\Documents")
						|| files[i].getAbsolutePath().equals(defaultPath + "\\Videos")
						|| files[i].getAbsolutePath().equals(defaultPath + "\\Music")) {
					MeunTree meunTree = new MeunTree();
					if (files[i].isDirectory()) {
						meunTree.setState("closed");
					}
					meunTree.getAttributes().put("pid", defaultPath);
					meunTree.setId(files[i].getAbsolutePath());
					// 对路径进行分割，只显示路径最后部分作为名字。
					String[] strings = files[i].getAbsolutePath().split("\\\\");
					String text = strings[strings.length - 1];
					meunTree.setText(text);
					meunTrees.add(meunTree);
				}
			}
		}
		return meunTrees;
	}
	
	public static void main(String[] args) {
		LinkedList<MeunTree> meunTrees = defaultRoots();
		for (MeunTree meunTree : meunTrees) {
			System.out.println(meunTree);
		}
	}

}
