package com.med.brenda.controller.api.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.med.brenda.exception.BusinessException;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.User;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.service.IUserService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;

/**
 * 患者的端Api<一>
 * <p>com.doctor330.medical</p>
 * <p>Title: PatientApi.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.controller.api.patient</p>
 * @author chenhj(brenda)
 * @date 2016年8月1日 下午4:02:40
 * @tag 
 * @encode
 */
@Controller
@RequestMapping(value="/api/patient")
public class PatientApi {
	
	@Autowired
	private IHzxxService hzxxService;
	@Autowired
	private IUserService userService;
	/**
	 * 根据用户ID获取用户对象
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据用户患者ID获取患者对象", httpMethod = "GET", response = Hzxx.class, notes = "根据用户患者ID获取患者对象")
	public String getHzById(@ApiParam(required = true, name = "id", value = "患者主键(long类型)") @PathVariable long id) throws Exception{
		
		Hzxx hz = hzxxService.findHzByHzID(id);

	    if(hz != null) {
	    	return JSON.toJSONString(hz);
	    } else {
	        throw new BusinessException("根据{id=" + id + "}获取不到Hzxx对象");
	    }
	}
	
	/**
	 * 根据用户名+密码获取用户对象
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/{username}/{password}", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "根据用户名+密码获取用户对象", httpMethod = "POST", response = User.class, notes = "根据用户名+密码获取用户对象")
	public String getUser(@ApiParam(required = true, name = "username", value = "用户名称") @PathVariable String username, 
			@ApiParam(required = true, name = "password", value = "用户密码MD5转大写") @PathVariable String password) throws Exception{
		User puser = new User();
		puser.setUsername(username);
		puser.setPassword(password);
		User newUser = userService.getUserByUserNamePwd(puser);

	    if(newUser != null) {
	    	return JSON.toJSONString(newUser);
	    } else {
	        throw new BusinessException("根据{name=" + username + ", password="+ password +"}获取不到User对象");
	    }
	}
}
