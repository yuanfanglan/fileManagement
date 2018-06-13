package com.yfl.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yfl.common.constant.FileConstant;
import com.yfl.common.util.FileUtil;
import com.yfl.pojo.MeunTree;

/**
 * @Description: 添加文件夹   
 * @author yfl
 * @date 2018年6月12日  
 */
public class AppendFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AppendFolderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/**
     * 添加文件夹
     * @param node 传过来的树节点
     * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String node = request.getParameter("node");
		if (node!=null&&node!=null) {
			Gson gson=new Gson();
			MeunTree meunTree = gson.fromJson(node, MeunTree.class);
			//添加文件
			MeunTree tree = FileUtil.appendFolder(meunTree, meunTree.getId()+FileConstant.NEW_FOLDER);
			if (tree!=null) {
				String json = gson.toJson(tree);
				response.setCharacterEncoding("UTF-8");  //编码设置，防止乱码
				response.getWriter().print(json);
			}
		}
	
	}

}
