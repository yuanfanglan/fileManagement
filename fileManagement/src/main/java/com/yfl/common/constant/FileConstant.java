package com.yfl.common.constant;

/**  
 * @Description: 该类用于定义文件树的一些常量  
 * @author yfl
 * @date 2018年6月12日  
 */
public class FileConstant {
	//文件路径在windows和linux的正反斜杆
	public static final String DIAGONAL=System.getProperty("file.separator");
	public static final String SPITDIAGONAL="/";
    //新文件的文件名
	public static final String NEW_FILE=DIAGONAL+"new_item";
	//新文件夹
	public static final String NEW_FOLDER=DIAGONAL+"新建文件夹";
	//默认文件路径
	public static final String DEFAULT_PATH=DIAGONAL+"home";
    //"C:"+DIAGONAL+"Users";
}
