package com.med.brenda.controller.activity;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.med.brenda.controller.common.BaseController;
import com.med.brenda.model.TActivity;
import com.med.brenda.service.IActivityService;

/**
 * 用户参与活动Controller
 * <p>MedicalProject</p>
 * <p>Title: ActivityController.java</p>
 * <p>Description:com.med.brenda.controller.activity</p>
 * <p>Package:</p>
 * @author Administrator
 * @date 2016年7月31日 下午4:54:28
 * @tag  
 * @encode 
 */

@Controller 
@RequestMapping(value="/api/doctor")
public class ActivityController extends BaseController {

	private Logger logger = Logger.getLogger(ActivityController.class);
	@Autowired
	private IActivityService acService;
	
	/**
	 * 添加活动数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveActivityData", method=RequestMethod.POST)
	@ResponseBody
	public String toLogin(HttpServletRequest request, TActivity ac){
		//注销当前登录用户
		JSONObject result = new JSONObject();
		logger.info("name = " + ac.getName());
		logger.info("phone = " + ac.getPhone());
		logger.info("age = "+ ac.getAge());
		
		ac.setCreatetime(System.currentTimeMillis());
		ac.setModifytime(System.currentTimeMillis());
		ac.setSource("controller");
		ac.setStatus(0);
		
		int rowid = acService.insert(ac);
		long id = ac.getId();
		if(rowid > 0  && id != 0){
			result.put("_st", 1);
			result.put("_msg", "信息提交成功");
			return result.toJSONString();
		}else{
			result.put("_st", 0);
			result.put("_msg", "信息提交失败");
			return result.toJSONString();
		}
	}
	
}
