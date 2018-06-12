package com.yfl.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yfl.common.util.FileUtil;
import com.yfl.pojo.MeunTree;


/**  
 * @Description:删除选中的文件夹以及文件   
 * @author yfl
 * @param  
 * @date 2018年6月11日  
 */
public class DelFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DelFolderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受前台传来的需要删除的数组对象
		String files = request.getParameter("files");
		boolean flag=false;
		if (files!=null&&files!="") {
			Gson gson=new Gson();
			//把string类型的对象转换为对象类型
			MeunTree meunTree = gson.fromJson(files, MeunTree.class);
			//删除文件夹里面的文件以及文件夹
			flag = FileUtil.delFiles(meunTree.getId(),true);
		}
		//返回结果，前台接收判断是否删除成功
		response.getWriter().print(flag);
		
	}

}
