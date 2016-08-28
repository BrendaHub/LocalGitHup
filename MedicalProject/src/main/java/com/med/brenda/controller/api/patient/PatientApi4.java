package com.med.brenda.controller.api.patient;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.Im;
import com.med.brenda.model.Mz;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.model.TnbYinshi;
import com.med.brenda.model.Ysxx;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.service.IIMService;
import com.med.brenda.service.IMzService;
import com.med.brenda.service.ITnbTnbsonService;
import com.med.brenda.service.IYinshiService;
import com.med.brenda.service.IYsxxService;
import com.med.brenda.util.CommonUtils;
import com.med.brenda.util.GlobalVariables;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;

/**
 * 患者的端Api<四>
 * <p>
 * com.doctor330.medical
 * </p>
 * <p>
 * Title: PatientApi.java
 * </p>
 * <p>
 * Description:$
 * </p>
 * <p>
 * Package:com.med.brenda.controller.api.patient
 * </p>
 * 
 * @author chenhj(brenda)
 * @date 2016年8月1日 下午4:02:40
 * @tag
 * @encode
 */

@Controller
@RequestMapping(value = "/api/patient")
public class PatientApi4 {

	Logger logger = Logger.getLogger(PatientApi4.class);
	@Autowired
	private IYsxxService ysxxService;
	@Autowired
	private IHzxxService hzxxService;
	@Autowired
	private IYinshiService yinshiService;
	@Autowired
	private ITnbTnbsonService tnbsonService;
	@Autowired
	private IIMService imService;
	@Autowired
	private IHzsfxxService hzsfxxService;

	// 获取患者信息
	@ResponseBody
	@ApiOperation(value = "获取患者基本信息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "GET", response = String.class, notes = "获取患者基本信息，｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/ＧetHzxx/{hzid}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public String ＧetHzxx(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(hzid)) {
			result.put("_st", 0);//
			result.put("_msg", "患者ID无效");
			return result.toJSONString();
		}else{
			//获取患者的信息， 
			Hzxx hzxx = hzxxService.selectByPrimaryKey(Long.parseLong(hzid));
			if(hzxx != null){
				result.put("_st", 1);
				result.put("_msg", "获取成功");
				result.put("_data", JSON.toJSONString(hzxx));
				return result.toJSONString();
			}else{
				result.put("_st", 2);
				result.put("_msg", "获取失败");
				return result.toJSONString();
			}
		}
	}
 
	//处理最后一个功能栏目接口，获取患者日志数据
	@ResponseBody
	@ApiOperation(value = "获取患者日志信息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "GET", response = String.class, notes = "获取患者日志信息，｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/ＧetHzxxLog/{hzid}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public String ＧetHzxxLog(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token,
			@ApiParam(required = true, name = "startdate", value = "开始日期，格式：yyyymmdd") @RequestParam(value = "startdate", required = true) String startdate,
			@ApiParam(required = true, name = "enddate", value = "截止日期，格式：yyyymmdd") @RequestParam(value = "enddate", required = true) String enddate,
			@ApiParam(required = true, name = "itemcode", value = "表示查询不同的项目，") @RequestParam(value = "itemcode", required = true) String itemcode) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(hzid)) {
			result.put("_st", 0);//
			result.put("_msg", "患者ID无效");
			return result.toJSONString();
		}
		if (StringUtils.isBlank(startdate)) {
			result.put("_st", 2);//
			result.put("_msg", "开始日期，格式：yyyymmdd无效");
			return result.toJSONString();
		}
		if (StringUtils.isBlank(enddate)) {
			result.put("_st", 3);//
			result.put("_msg", "截止日期，格式：yyyymmdd无效");
			return result.toJSONString();
		}
		if (StringUtils.isBlank(itemcode)) {
			result.put("_st", 5);//
			result.put("_msg", "itemcode值无效");
			return result.toJSONString();
		}
		if(itemcode.equals(GlobalVariables.YINSHI_ITEMCODE)){//如果是饮食
			try{
				List<TnbYinshi> list = yinshiService.getYinshilistByDateRang(Long.parseLong(hzid), CommonUtils.getTimeInMillisByDate(startdate), CommonUtils.getTimeInMillisByDate(enddate));
				if(list != null && list.size() > 0){
					result.put("_st", 1);
					result.put("_msg", "获取成功");
					result.put("_data", JSON.toJSONString(list));
					return result.toJSONString();
				}else {
					result.put("_st", 6);
					result.put("_msg", "获取失败");
					return result.toJSONString();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(itemcode.equals(GlobalVariables.YUNDONG_ITEMCODE)){//如果是运动
			try{
				List<TnbTnbson> list = tnbsonService.getTnbsonlistByDateRang(Long.parseLong(hzid), GlobalVariables.YUNDONG_ITEMCODE, CommonUtils.getTimeInMillisByDate(startdate), CommonUtils.getTimeInMillisByDate(enddate));
				if(list != null && list.size() > 0){
					result.put("_st", 1);
					result.put("_msg", "获取成功");
					result.put("_data", JSON.toJSONString(list));
					return result.toJSONString();
				}else {
					result.put("_st", 6);
					result.put("_msg", "获取失败");
					return result.toJSONString();
				}
			}catch(Exception e){
				e.printStackTrace();
				result.put("_st", 6);
				result.put("_msg", "获取失败,程序异常");
				return result.toJSONString();
			}
		}
		if(itemcode.equals(GlobalVariables.ZHENGZHUANG_ITEMCODE)){//如果是症状
			try{
				List<TnbTnbson> list = tnbsonService.getTnbsonlistByDateRang(Long.parseLong(hzid), GlobalVariables.ZHENGZHUANG_ITEMCODE, CommonUtils.getTimeInMillisByDate(startdate), CommonUtils.getTimeInMillisByDate(enddate));
				if(list != null && list.size() > 0){
					result.put("_st", 1);
					result.put("_msg", "获取成功");
					result.put("_data", JSON.toJSONString(list));
					return result.toJSONString();
				}else {
					result.put("_st", 6);
					result.put("_msg", "获取失败");
					return result.toJSONString();
				}
			}catch(Exception e){
				e.printStackTrace();
				result.put("_st", 6);
				result.put("_msg", "获取失败,程序异常");
				return result.toJSONString();
			}
		}
		if(itemcode.equals(GlobalVariables.ZHENGZHUANG_ITEMCODE)){//如果是症状
			try{
				List<TnbTnbson> list = tnbsonService.getTnbsonlistByDateRang(Long.parseLong(hzid), GlobalVariables.ZHENGZHUANG_ITEMCODE, CommonUtils.getTimeInMillisByDate(startdate), CommonUtils.getTimeInMillisByDate(enddate));
				if(list != null && list.size() > 0){
					result.put("_st", 1);
					result.put("_msg", "获取成功");
					result.put("_data", JSON.toJSONString(list));
					return result.toJSONString();
				}else {
					result.put("_st", 6);
					result.put("_msg", "获取失败");
					return result.toJSONString();
				}
			}catch(Exception e){
				e.printStackTrace();
				result.put("_st", 6);
				result.put("_msg", "获取失败,程序异常");
				return result.toJSONString();
			}
		}
		if(itemcode.equals(GlobalVariables.XUETANG_ITEMCODE)){//如果是血糖
			try{
				List<TnbTnbson> list = tnbsonService.getTnbsonlistByDateRang(Long.parseLong(hzid), GlobalVariables.XUETANG_ITEMCODE, CommonUtils.getTimeInMillisByDate(startdate), CommonUtils.getTimeInMillisByDate(enddate));
				if(list != null && list.size() > 0){
					//这里需要按不同的日期处理结果集
					Long old_fatherid = 0l;
					JSONArray arr = new JSONArray();
					JSONObject tmpson = null;
					for(int index = 0 ; index < list.size() ; index ++){
						TnbTnbson tnbson = (TnbTnbson)list.get(index);
						if(old_fatherid == 0l){
							tmpson = new JSONObject();
						}
						if(old_fatherid != tnbson.getFatherid()){//表示换了一个新的数据
							arr.add(tmpson);
							tmpson = null;
							tmpson = new JSONObject();
						}
						tmpson.put("id", tnbson.getId());
						tmpson.put("fatherid", tnbson.getFatherid());
						tmpson.put("hzid", tnbson.getHzid());
						tmpson.put("itemcode", tnbson.getItemcode());
						tmpson.put("itemname", tnbson.getItemname());
						tmpson.put("itemvalue", tnbson.getItemvalue());
						tmpson.put("time", tnbson.getTemp1());
						tmpson.put("date", tnbson.getTemp5());
						old_fatherid = tnbson.getFatherid();
					}
					if(tmpson != null){
						arr.add(tmpson);
					}
					result.put("_st", 1);
					result.put("_msg", "获取成功");
					result.put("_data", JSON.toJSONString(arr));
					return result.toJSONString();
				}else {
					result.put("_st", 6);
					result.put("_msg", "获取失败");
					return result.toJSONString();
				}
			}catch(Exception e){
				e.printStackTrace();
				result.put("_st", 6);
				result.put("_msg", "获取失败,程序异常");
				return result.toJSONString();
			}
		}
		if(itemcode.equals(GlobalVariables.YITAOSU_ITEMCODE)){//如果是胰岛素
			try{
				List<TnbTnbson> list = tnbsonService.getTnbsonlistByDateRang(Long.parseLong(hzid), GlobalVariables.YITAOSU_ITEMCODE, CommonUtils.getTimeInMillisByDate(startdate), CommonUtils.getTimeInMillisByDate(enddate));
				if(list != null && list.size() > 0){
					//这里需要按不同的日期处理结果集
					Long old_fatherid = 0l;
					JSONArray arr = new JSONArray();
					JSONObject tmpson = null;
					for(int index = 0 ; index < list.size() ; index ++){
						TnbTnbson tnbson = (TnbTnbson)list.get(index);
						if(old_fatherid == 0l){
							tmpson = new JSONObject();
						}
						if(old_fatherid != tnbson.getFatherid()){//表示换了一个新的数据
							arr.add(tmpson);
							tmpson = null;
							tmpson = new JSONObject();
						}
						tmpson.put("id", tnbson.getId());
						tmpson.put("fatherid", tnbson.getFatherid());
						tmpson.put("hzid", tnbson.getHzid());
						tmpson.put("itemcode", tnbson.getItemcode());
						tmpson.put("itemname", tnbson.getItemname());
						tmpson.put("yds", tnbson.getYds());
						tmpson.put("ydsjl", tnbson.getYdsjl());
						tmpson.put("ydsejsg", tnbson.getYdsejsg());
						tmpson.put("time", tnbson.getTemp1());
						tmpson.put("date", tnbson.getTemp5());
						old_fatherid = tnbson.getFatherid();
					}
					if(tmpson != null){
						arr.add(tmpson);
					}
					result.put("_st", 1);
					result.put("_msg", "获取成功");
					result.put("_data", JSON.toJSONString(arr));
					return result.toJSONString();
				}else {
					result.put("_st", 6);
					result.put("_msg", "获取失败");
					return result.toJSONString();
				}
			}catch(Exception e){
				e.printStackTrace();
				result.put("_st", 6);
				result.put("_msg", "获取失败,程序异常");
				return result.toJSONString();
			}
		}
		if(itemcode.equals(GlobalVariables.SUIMIAN_TEMP5_SUB) || itemcode.equals(GlobalVariables.XUEYA_TEMP5_SUB) 
				|| itemcode.equals(GlobalVariables.TIWEN_TEMP5_SUB) || itemcode.equals(GlobalVariables.HEIGHT_TMPE5_SUB)
				|| itemcode.equals(GlobalVariables.WEIGHT_TMPE5_SUB)
				|| itemcode.equals(GlobalVariables.TOUWEI_TMPE5_SUB)
				|| itemcode.equals(GlobalVariables.XONGWEI_TMPE5_SUB)
				|| itemcode.equals(GlobalVariables.SHANGBIWEI_TMPE5_SUB)
				|| itemcode.equals(GlobalVariables.GONGSANHOUTU_TMPE5_SUB)){//如果是睡眠标识 014001 体温标识 014002 血压标识 014003
			try{
				List<Hzsfxx> hzsfxxlist = hzsfxxService.findListDateRangByTemp3(Long.parseLong(hzid), itemcode, Long.parseLong(startdate), Long.parseLong(enddate));
				if(hzsfxxlist != null && hzsfxxlist.size()>0){
					JSONArray arr = new JSONArray();
					JSONObject j = null;
					for(int index = 0 ; index < hzsfxxlist.size() ; index ++){
						Hzsfxx hzsfxx = (Hzsfxx)hzsfxxlist.get(index);
						j = new JSONObject();
						j.put("id", hzsfxx.getID());
						j.put("hzid", hzsfxx.getHZID());
						j.put("hzname", hzsfxx.getHZNAME());
						j.put("sfdate", hzsfxx.getSFDATE());
						j.put("itemcode", hzsfxx.getTEMP3());
						j.put("itemname", hzsfxx.getITEMNAME());
						j.put("itemvalue", hzsfxx.getITEMVALUE());
						j.put("icon", hzsfxx.getTEMP1());
						arr.add(j);
					}
					result.put("_st", 1);
					result.put("_msg", "获取成功");
					result.put("_data", JSON.toJSONString(arr));
					return result.toJSONString();
				}else{
					result.put("_st", 7);
					result.put("_msg", "获取失败,没有对应的数据");
					return result.toJSONString();
				}
			}catch(Exception e){
				e.printStackTrace();
				result.put("_st", 6);
				result.put("_msg", "获取失败,程序异常");
				return result.toJSONString();
			}
		}else{
			result.put("_st", 8);
			result.put("_msg", "itemcode错误，请检查");
			return result.toJSONString();
		}
	}
}
