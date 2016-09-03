package com.med.brenda.controller.user;

import javax.inject.Inject;
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
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public ModelAndView toLogin(HttpServletRequest request){
		return new ModelAndView("Public/login");
	}
	
	//去修改口令
	@RequestMapping(value="/tomodifypwd", method=RequestMethod.GET)
	public ModelAndView tomodifypwd(HttpServletRequest request){
		return new ModelAndView("User/modifypwd");
	}
	//去添加用户
	@RequestMapping(value="/toAddUser", method=RequestMethod.GET)
	public ModelAndView toAddUser(HttpServletRequest request){
		return new ModelAndView("User/add");
	}
	//去修改用户
	@RequestMapping(value="/toEditUser", method=RequestMethod.GET)
	public ModelAndView toEditUser(HttpServletRequest request){
		return new ModelAndView("User/edit");
	}
	//去用户列表页
	@RequestMapping(value="/toUserList", method=RequestMethod.GET)
	public ModelAndView toUserList(HttpServletRequest request){
		return new ModelAndView("User/index");
	}
	//去角色列表
	@RequestMapping(value="/toRoleList", method=RequestMethod.GET)
	public ModelAndView toRoleList(HttpServletRequest request){
		return new ModelAndView("Role/index");
	}
	//去用户列表页
	@RequestMapping(value="/toRoleAdd", method=RequestMethod.GET)
	public ModelAndView toRoleAdd(HttpServletRequest request){
		return new ModelAndView("Role/add");
	}
	//去用户列表页
	@RequestMapping(value="/toRoleEdit", method=RequestMethod.GET)
	public ModelAndView toRoleEdit(HttpServletRequest request){
		return new ModelAndView("Role/edit");
	}
	
	//去Node列表页
	@RequestMapping(value="/toNodeList", method=RequestMethod.GET)
	public ModelAndView toNodeList(HttpServletRequest request){
		return new ModelAndView("Node/index");
	}
	//去Node添加页面
	@RequestMapping(value="/toNodeAdd", method=RequestMethod.GET)
	public ModelAndView toNodeAdd(HttpServletRequest request){
		return new ModelAndView("Node/add");
	}
	//去Node编辑页面
	@RequestMapping(value="/toNodeEdit", method=RequestMethod.GET)
	public ModelAndView toNodeEdit(HttpServletRequest request){
		return new ModelAndView("Node/edit");
	}
	
	//去Menu列表页
	@RequestMapping(value="/toMenuList", method=RequestMethod.GET)
	public ModelAndView toMenuList(HttpServletRequest request){
		return new ModelAndView("Menu/index");
	}
	//去Menu添加页面
	@RequestMapping(value="/toMenuAdd", method=RequestMethod.GET)
	public ModelAndView toMenuAdd(HttpServletRequest request){
		return new ModelAndView("Menu/add");
	}
	//去Menu编辑页面
	@RequestMapping(value="/toMenuEdit", method=RequestMethod.GET)
	public ModelAndView toMenuEdit(HttpServletRequest request){
		return new ModelAndView("Menu/edit");
	}
}
