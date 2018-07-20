package com.yfl.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yfl.common.util.AjaxResult;
import com.yfl.service.interfaces.HomeUserService;

@Controller
public class HomeUserController {

	@Autowired
	private HomeUserService homeUserService;
	
	@RequestMapping(value="default")
	public String index() {
		return "login";
	}
	
	@RequestMapping(value="selectAllHomeUser")
	@ResponseBody
	public AjaxResult selectAllHomeUser() {
		 AjaxResult result = homeUserService.selectAllHomeUser();
		 System.out.println(result);
		 return result;
	}
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * */
	@RequestMapping(value="login")
	@ResponseBody
	public AjaxResult login(@RequestParam("userName") String userName,@RequestParam("password")String password,HttpSession session) {
		AjaxResult result = homeUserService.login(userName, password);
		if (result.getMeta().isSuccess()==true) {
			Object homeuser = result.getData();
			session.setAttribute("homeuser", homeuser);
		}
		return result;
	}
}
