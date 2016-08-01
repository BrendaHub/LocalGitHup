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
import com.med.brenda.service.IHzxxService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 患者的端Api<一>
 * <p>com.doctor330.medical</p>
 * <p>Title: PatientApi.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.controller.api.patient</p>
 * @author bgly
 * @date 2016年8月1日 下午4:02:40
 * @tag 
 * @encode
 */
@Controller
@RequestMapping(value="/api/patient")
public class PatientApi {
	
	@Autowired
	private IHzxxService hzxxService;
	/**
	 * 根据用户名获取用户对象
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据用户患者ID获取患者对象", httpMethod = "GET", response = Hzxx.class, notes = "根据用户患者ID获取患者对象")
	public String getUserByName(@ApiParam(required = true, name = "id", value = "患者主键(long类型)") @PathVariable long id) throws Exception{
		
		Hzxx hz = hzxxService.findHzByHzID(id);

	    if(hz != null) {
	    	return JSON.toJSONString(hz);
	    } else {
	        throw new BusinessException("根据{id=" + id + "}获取不到Hzxx对象");
	    }
	}
}
