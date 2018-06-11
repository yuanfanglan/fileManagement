package com.yfl.web.servlet;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.yfl.pojo.MeunTree;
import com.yfl.util.MyTreeUtil;

/**
 * 分层加载树
 */
public class TreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TreeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathname = request.getParameter("id");
		// 如果没有传路径，则默认为根路径
		if (pathname == null || pathname == "") {
		    LinkedList<MeunTree> rootsData = MyTreeUtil.rootsData();
			String json = new Gson().toJson(rootsData);
			response.setCharacterEncoding("UTF-8");  //编码设置，防止乱码
			response.getWriter().print(json);
			} else {
			//如果传了路径，则查询子节点
			LinkedList<MeunTree> treeData = MyTreeUtil.treeData(pathname);
			String json = new Gson().toJson(treeData);
			response.setCharacterEncoding("UTF-8");  //编码设置，防止乱码
			response.getWriter().print(json);
		}
	}
}
