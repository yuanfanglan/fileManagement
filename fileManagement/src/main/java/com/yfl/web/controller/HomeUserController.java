package com.yfl.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yfl.common.util.AjaxResult;
import com.yfl.service.interfaces.HomeUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class HomeUserController {

	@Autowired
	private HomeUserService homeUserService;
	
	/*@RequestMapping(value="default",method=RequestMethod.GET)
	public String index() {
		return "jsp/login.jsp";
	}*/
	
	@RequestMapping(value="selectAllHomeUser",method=RequestMethod.GET)
	@ApiOperation(value="查找所有用户信息")
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
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ApiOperation(value="登录",notes="参数为必填项")
	public AjaxResult login(@ApiParam(value="用户名",required=true)@RequestParam("userName") String userName,@ApiParam(value="密码",required=true)@RequestParam("password")String password,HttpSession session) {
		AjaxResult result = homeUserService.login(userName, password);
		if (result.getMeta().isSuccess()==true) {
			Object homeuser = result.getData();
			session.setAttribute("homeuser", homeuser);
		}
		return result;
	}
}
