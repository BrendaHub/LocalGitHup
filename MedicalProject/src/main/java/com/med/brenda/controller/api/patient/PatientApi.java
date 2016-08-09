package com.med.brenda.controller.api.patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
	public String getHzById(HttpServletResponse response,@ApiParam(required = true, name = "id", value = "患者主键(long类型)") @PathVariable long id) throws Exception{
		response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码
		response.setCharacterEncoding("UTF-8");
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
	public String getUser(HttpServletResponse response,@ApiParam(required = true, name = "username", value = "用户名称") @PathVariable String username, 
			@ApiParam(required = true, name = "password", value = "用户密码MD5转大写") @PathVariable String password) throws Exception{
		response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码
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
	
	@ResponseBody
	@RequestMapping(value="/LOGON/{SFZCODE}/{PASSWORD}",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ApiOperation(value = "患者登录（身份证号 + 密码）", httpMethod = "POST", response = JSON.class, notes = "患者登录（身份证号 + 密码）")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	public String patientLogon(HttpServletRequest request,HttpServletResponse response,@ApiParam(required = true, name = "SFZCODE", value = "患者身份证号") @PathVariable String SFZCODE,
			@ApiParam(required = true, name = "PASSWORD", value = "患者登录密码MD5大写") @PathVariable String PASSWORD){
		response.setContentType("application/json;charset=UTF-8");//防止数据传递乱码
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(SFZCODE)){
			result.put("_st", 2);//
			result.put("_msg", "身份证号不正确");
			return result.toJSONString();
		}
		if(StringUtils.isBlank(PASSWORD)){
			result.put("_st", 3);//
			result.put("_msg", "密码不正确");
			return result.toJSONString();
		}
		Hzxx hz = hzxxService.hzLogon(SFZCODE, PASSWORD);
		if(hz != null){
			result.put("_st", 1);//
			result.put("_msg", "登录成功");
			JSONObject body = new JSONObject();
			JSONObject subbody = new JSONObject();
			subbody.put("Id", hz.getID());
			subbody.put("Name", hz.getHZNAME());
			subbody.put("Sex", hz.getSEX());
			subbody.put("Date", hz.getCSRQ());
			subbody.put("Headurl", hz.getDLH());
			subbody.put("Bjflag", "对应不上什么值");
			body.put("_data", subbody);
			result.put("_body", body);
			return result.toJSONString();
		}else{
			result.put("_st", 0);//
			result.put("_msg", "身份证号OR密码错误");
			return result.toJSONString();
		}
	}
	
	public String 
}
