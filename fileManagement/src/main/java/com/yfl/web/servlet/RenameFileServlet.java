package com.yfl.web.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yfl.pojo.MeunTree;

/**  
 * @Description:文件或文件夹的重命名   
 * @author yfl
 * @date 2018年6月13日  
 */
public class RenameFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RenameFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String node = request.getParameter("node");
		if (node!=null||node!="") {
			Gson gson=new Gson();
			MeunTree meunTree = gson.fromJson(node, MeunTree.class);
			String oldId=meunTree.getId();
			String pid=(String) meunTree.getAttributes().get("pid");
			String text=meunTree.getText();
			String newId=pid+"\\"+text;
			meunTree.setId(newId);
			//判断新名字是否存在
			if (new File(newId).exists()) {
				//如果存在,不进行操作
				response.setCharacterEncoding("utf-8");
				response.getWriter().print("");
			}else {
				//文件名修改
				new File(oldId).renameTo(new File(newId));  
				String json = gson.toJson(meunTree);
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
			}
		}
	}

}
