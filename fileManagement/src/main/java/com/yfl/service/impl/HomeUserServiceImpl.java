package com.yfl.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yfl.common.util.AjaxResult;
import com.yfl.mapper.HomeuserMapper;
import com.yfl.pojo.Homeuser;
import com.yfl.pojo.HomeuserExample;
import com.yfl.pojo.HomeuserExample.Criteria;
import com.yfl.service.interfaces.HomeUserService;

/**  
 * @Description:   
 * @author yfl
 * @param  HomeUser实现类
 * @date 2018年7月18日  
 */
@Service
public class HomeUserServiceImpl implements HomeUserService{

	@Autowired
	private HomeuserMapper homeuserMapper;
	
	/**
	 * 查询所有
	 * */
	@Override
	public AjaxResult selectAllHomeUser() {
		HomeuserExample example=new HomeuserExample();
		List<Homeuser> list = homeuserMapper.selectByExample(example);
		if (list!=null) {
			return new AjaxResult().success(list);
		}
		return null;
	}
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * */
	@Override
	public AjaxResult login(String userName, String password) {
		if (StringUtils.isBlank(userName)) {
			return new AjaxResult().failure("用户名不能为空");
		}else if (StringUtils.isBlank(password)) {
			return new AjaxResult().failure("密码不能为空");
		}else {
			HomeuserExample example=new HomeuserExample();
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(userName);
			List<Homeuser> list = homeuserMapper.selectByExample(example);
			if (list!=null) {
				if (list.get(0).getPassword().equals(password)) {
					return new AjaxResult().success(list.get(0));
				}else {
					return new AjaxResult().failure("密码错误");
				}
			}
			return null;
		}
	}

}
