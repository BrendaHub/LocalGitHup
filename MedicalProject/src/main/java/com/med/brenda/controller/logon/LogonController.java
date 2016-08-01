package com.med.brenda.controller.logon;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.med.brenda.model.User;
import com.med.brenda.service.IUserService;
import com.med.brenda.util.MD5;

/**
 * 用户登录认证Controller
 * <p>MedicalProject</p>
 * <p>Title: LogonController.java</p>
 * <p>Description:com.med.brenda.controller.logon</p>
 * <p>Package:</p>
 * @author Administrator
 * @date 2016年7月31日 下午4:54:28
 * @tag  
 * @encode 
 */
@Controller 
public class LogonController {
	
	private Logger logger = Logger.getLogger(LogonController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView toLogin(HttpServletRequest request){
		//注销当前登录用户
		request.getSession().removeAttribute("_userinfo");
		
		return new ModelAndView("Public/login");
	}
	
	@RequestMapping(value="/logon", method=RequestMethod.POST)
	public ModelAndView Logon(HttpServletRequest request, User user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取到的验证码
		String verifyCode = request.getParameter("verifycode");
		verifyCode = StringUtils.isBlank(verifyCode)?"":verifyCode;
//		if(logger.isDebugEnabled()){
		logger.debug("verifyCode = " + verifyCode);
		logger.debug("username  = " + user.getUsername());
		logger.debug("password  = " + user.getPassword());
		logger.debug("verifyCode session  = " + request.getSession().getAttribute("verify_code"));
//		}
		String sessionVerify = (String)request.getSession().getAttribute("verify_code");
		sessionVerify = StringUtils.isBlank(sessionVerify)?"":sessionVerify.trim();
		if(!sessionVerify.equals(verifyCode)){
			resultMap.put("_st", Integer.parseInt("1003"));
			resultMap.put("_msg", "验证码错误");
			return new ModelAndView("Public/login").addObject("result", JSON.toJSON(resultMap));
		}else{
			user.setPassword(new MD5().GetMD5Code(user.getPassword()));
			User newUser = userService.getUserByUserNamePwd(user);
			if(newUser == null){
				resultMap.put("_st", Integer.parseInt("1002"));
				resultMap.put("_msg", "账号密码错误");
				return new ModelAndView("Public/login").addObject("result", JSON.toJSON(resultMap));
			}else{
				if(newUser.getId() != null){
					request.getSession().setAttribute("_userinfo", newUser);

					return new ModelAndView("redirect:/toindex");
				}else{
					resultMap.put("_st", Integer.parseInt("1001"));
					resultMap.put("_msg", "登录异常，请再偿试");
					return new ModelAndView("Public/login").addObject("result", JSON.toJSON(resultMap));
				}
			}
		}
		
		//
	}
	
	@RequestMapping(value="/toindex")
	public  ModelAndView toindex(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("_userinfo");
		return new ModelAndView("index").addObject("user", JSON.toJSON(user));
	}
	
	@RequestMapping(value="/logonout")
	public  ModelAndView logonout(HttpServletRequest request){
		request.getSession().removeAttribute("_userinfo");
		return new ModelAndView("redirect:/login");
	}
}
