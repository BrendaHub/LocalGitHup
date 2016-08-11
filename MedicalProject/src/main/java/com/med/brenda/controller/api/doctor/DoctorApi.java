package com.med.brenda.controller.api.doctor;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.model.Gzysxx;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.Ysxx;
import com.med.brenda.service.IGzysxxService;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.service.impl.YsxxService;
import com.med.brenda.util.MD5;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 医生端Api
 * <p>MedicalProject</p>
 * <p>Title: DoctorApi.java</p>
 * <p>Description:com.med.brenda.controller.api.doctor</p>
 * <p>Package:</p>
 * @author Administrator
 * @date 2016年8月8日 下午10:45:40
 * @tag  
 * @encode
 */
@Controller
@RequestMapping(value="/api/doctor")
public class DoctorApi {
	
	private Logger logger = Logger.getLogger(DoctorApi.class);
	
	@Autowired
	private IHzxxService hzxxService;
	@Autowired
	private IGzysxxService gzysxxService;
	@Autowired
	private YsxxService ysxxService;
	
	@RequestMapping(value="/DoctorLogin/{ysdlh}/{yspwd}", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "医生登录", httpMethod = "POST", response = JSONObject.class, notes = "医生登录,返回医生信息")
	public String logon(@ApiParam(required = true, name = "ysdlh", value = "医生登录账号 (String类型)") @PathVariable String ysdlh,
			@ApiParam(required = true, name = "yspwd", value = "医生登录密码 (String类型)") @PathVariable String yspwd){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(ysdlh)){
			result.put("_st", 3);
			result.put("_msg", "医生账号无效");
			return result.toJSONString();
		}
		if(StringUtils.isBlank(yspwd)){
			result.put("_st", 2);
			result.put("_msg", "医生密码无效");
			return result.toJSONString();
		}
		
		Ysxx ys = ysxxService.ysLogon(ysdlh, yspwd);
		if(ys != null){
			result.put("_st", 1);
			result.put("_msg", "调用成功");
			JSONObject _body = new JSONObject();
			JSONObject _data = new JSONObject();
			_data.put("Ysid", ys.getId());
			_data.put("Ysname", ys.getName());
			_data.put("Ksid", ys.getSsksid());
			_data.put("Ksname",ys.getSsks());
			return result.toJSONString();
		}else{
			result.put("_st", 0);
			result.put("_msg", "登录失败");
			return result.toJSONString();
		}
	}

	@RequestMapping(value="/FeedTNBHEData",produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "医生注册新患者", httpMethod = "POST", response = JSONObject.class, notes = "医生注册新患者")
	public String addPatient(@ApiParam(required = true, name = "ysid", value = "医生ID (long类型)") @RequestParam(value="ysid",required=true)Long ysid,
			@ApiParam(required = true, name = "ysname", value = "医生名称 (String类型)") @RequestParam(value="ysname",required=true)String ysname,
			@ApiParam(required = true, name = "strs", value = "新患者基本信息 (string类型),格式为：name,（患者名字）|sfz,（身份证号）|jzphone,（监护人手机号）|hefz,（选择病种,取值依次为：1，2，3，4）|starttime,（随访开始日期，年月日）") @RequestParam(value="strs",required=true)String strs){
		
		logger.debug("ysid = " + ysid);
		logger.debug("ysname = " + ysname);
		logger.debug("strs = " + strs);
		
		JSONObject result = new JSONObject();
		//判断参数
		if(StringUtils.isBlank(ysid+"")){
			result.put("_st", 0);
			result.put("_msg", "医生ID无效");
			return result.toJSONString();
		}
		
		if(StringUtils.isBlank(strs)){
			result.put("_st", 2);
			result.put("_msg", "新的患者信息无效");
			return result.toJSONString();
		}
		try{
			//参数OK后，第一步添加新患 者
			Hzxx hzVo = new Hzxx();
			//格式化新患者的数据
			String[] _tmp_hzxx = strs.split("\\|");
			String pwd = "";//MD5大写
			String hzName = "";
			String dlhCode = "";
			String pztype = "";//病种类型
			if(_tmp_hzxx != null && _tmp_hzxx.length > 0 ){
				for(String s: _tmp_hzxx){
					String[] _sub_s = s.split(",");
					if(_sub_s[0].trim().equals("name")){
						hzVo.setHZNAME(_sub_s[1].trim());
						hzName = _sub_s[1].trim();
					}else if(_sub_s[0].trim().equals("sfz")){
						hzVo.setSFZCODE(_sub_s[1].trim());
						hzVo.setDLH(_sub_s[1].trim());
						dlhCode = _sub_s[1].trim();
						//设置密码，密码默认为身份证前六位
						String _dlh = _sub_s[1].trim();
						pwd = _dlh.substring(0, 6);
						pwd = MD5.GetMD5Code(pwd);
						hzVo.setPASSWORD(pwd);
					}else if(_sub_s[0].trim().equals("jzphone")){
						hzVo.setPHONE(_sub_s[1].trim());
					}else if(_sub_s[0].trim().equals("hefz")){
						hzVo.setTEMP1(_sub_s[1].trim());
					}else if(_sub_s[0].trim().equals("starttime")){
						hzVo.setTEMP3(_sub_s[1].trim());
					}
				}
			}
			long hzId = hzxxService.addHz(hzVo);
			//由于目前mybatis没有返回新添加的记录主键，所以需要查询
			Hzxx newHzVo = hzxxService.hzLogon(dlhCode, pwd);
			
			logger.debug("新注册的患ID = " + hzId + "   > "+ JSON.toJSONString(newHzVo));
			if(hzId > 0){
				//添加医患关系记录
				Gzysxx yshz = new Gzysxx();
				yshz.setHZID(newHzVo.getID());
				yshz.setHZNAME(hzName);
				yshz.setYSID(ysid);
				yshz.setYSNAME(ysname);
				yshz.setKSID(new Long(2));
				yshz.setKSNAME("内分泌科");
				long gzysxxid = gzysxxService.addGzysxx(yshz);
			}
			result.put("_st", 1);
			result.put("_msg", "保存成功");
			return result.toJSONString();
		}catch(Exception e){
			e.printStackTrace();
			result.put("_st", 3);
			result.put("_msg", "新的患者信息失败");
			return result.toJSONString();
		}
	}
}
