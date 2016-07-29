package com.med.brenda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.med.brenda.model.User;
import com.med.brenda.service.IUserService;

/**
 * 用户操作控制类
 * <p>MedicalProject</p>
 * <p>Title: UserController.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.controller</p>
 * @author bgly
 * @date 2016年7月28日 下午8:30:36
 * @tag 
 * @encode
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/userinfo/{userId}", method = RequestMethod.GET)
	public ModelAndView toUserinfo(HttpServletRequest request,@PathVariable("userId") long userId) {
		User user = userService.getUserById(userId);
		return new ModelAndView("index").addObject("userinfo", user);
	}
}
