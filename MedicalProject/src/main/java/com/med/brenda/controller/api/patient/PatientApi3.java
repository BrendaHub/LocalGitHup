package com.med.brenda.controller.api.patient;

import java.io.File;
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
 * 患者的端Api<三>
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
public class PatientApi3 {

	Logger logger = Logger.getLogger(PatientApi3.class);
	@Autowired
	private IYsxxService ysxxService;
	@Autowired
	private IMzService mzService;
	@Autowired
	private IHzxxService hzxxService;
	@Autowired
	private IIMService imService;
	@Autowired
	private IHzsfxxService hzsfxxService;
	@Autowired
	private IYinshiService yinshiService;
	@Autowired
	private ITnbTnbsonService tnbsonService;
	
	
	//获取医信列表
	@ResponseBody
	@ApiOperation(value = "获取医生的列表，｜  发布时间： 2016-09-06 09:28 ", httpMethod = "GET", response = String.class, notes = "获取医生的信息，｜  发布时间： 2016-09-06 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/getDocList/{hzid}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public String GetDoctorList(@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token) {
		JSONObject result = new JSONObject();
		
		//查询出2表示内分泌科的医生， 1:表示有效的医生
		List<Ysxx> yslist = ysxxService.findYsxxByHzidAndType(2l, "1");
		if(yslist != null && yslist.size() > 0){
			JSONArray array  = new JSONArray();
			yslist.stream().forEach(a -> {
				JSONObject oj = new JSONObject();
				oj.put("ysid", a.getId());
				oj.put("ysname", a.getName());
				oj.put("yssex", a.getSex());
				oj.put("yszc", a.getZc());
				oj.put("ks", a.getSsks());
				oj.put("yy", a.getSzyy());
				oj.put("yszyjs", a.getYszyjs());
				oj.put("ysll", a.getYsll());
				oj.put("ysheader", a.getTemp1());
				List<Mz> list = mzService.selectByYsid(a.getId());
				if (list != null && list.size() > 0) {
					JSONArray mzArray = new JSONArray();
					for (Iterator<Mz> it = list.iterator(); it.hasNext();) {
						JSONObject submz = new JSONObject();
						Mz mz = it.next();
						submz.put("zj", mz.getZj());
						submz.put("qj", mz.getQj());
						mzArray.add(submz);
					}
					oj.put("mzlist", mzArray);
				}
				array.add(oj);
			});
			result.put("_st", 1);//
			result.put("_msg", "获取成功");
			result.put("_data", JSON.toJSON(array));
			return result.toJSONString();
		}else{
			result.put("_st", 2);//
			result.put("_msg", "暂无满足条件的医生信息");
			return result.toJSONString();
		}
	}
	

	// 获取医生的信息
	@ResponseBody
	@ApiOperation(value = "获取医生的信息，包含医生的门诊信息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "GET", response = String.class, notes = "获取医生的信息，包含了生的门诊信息｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/getDocInfo/{ysid}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public String AddMovement(@ApiParam(required = true, name = "ysid", value = "医生ID") @PathVariable String ysid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(ysid)) {
			result.put("_st", 0);//
			result.put("_msg", "医生ID无效");
			return result.toJSONString();
		}
		// 开发查询医生ＩＤ 对应的相关信息
		Ysxx ysxx = ysxxService.selectByPrimaryKey(Long.parseLong(ysid));
		if (ysxx != null) {
			JSONObject _data = new JSONObject();
			// 根据医生ＩＤ 获取到医生的门诊信息
			List<Mz> list = mzService.selectByYsid(Long.parseLong(ysid));
			_data.put("ysid", ysxx.getId());
			_data.put("ysname", ysxx.getName());
			_data.put("zc", ysxx.getZc());
			_data.put("ks", ysxx.getSsks());
			_data.put("yy", ysxx.getSzyy());
			if (list != null && list.size() > 0) {
				JSONArray mzArray = new JSONArray();
				for (Iterator<Mz> it = list.iterator(); it.hasNext();) {
					JSONObject submz = new JSONObject();
					Mz mz = it.next();
					submz.put("zj", mz.getZj());
					submz.put("qj", mz.getQj());
					mzArray.add(submz);
				}
				_data.put("mzlist", mzArray);
			}
			result.put("_st", 1);
			result.put("_msg", "获取成功");
			result.put("_data", _data);
			return result.toJSONString();
		} else {
			result.put("_st", 3);
			result.put("_msg", "获取失败");
			return result.toJSONString();
		}
	}

	// 添加患者给医生发消息接口
	@ResponseBody
	@ApiOperation(value = "患者给医生发消息接口，就只是用来发送文本消息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "POST", response = String.class, notes = "患者给医生发消息接口，｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/SendMesg/{hzid}/{ysid}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public String SendMesg(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = true, name = "ysid", value = "医生ID") @PathVariable String ysid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token,
			@ApiParam(required = true, name = "message", value = "发送的消息内容") @RequestParam(value = "message", required = true) String message) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(ysid)) {
			result.put("_st", 0);//
			result.put("_msg", "医生ID无效");
			return result.toJSONString();
		}
		if (StringUtils.isBlank(hzid)) {
			result.put("_st", 2);//
			result.put("_msg", "患者ID无效");
			return result.toJSONString();
		}
		if (StringUtils.isBlank(message)) {
			result.put("_st", 3);//
			result.put("_msg", "消息内容无效");
			return result.toJSONString();
		}
		// 根据患者ＩＤ 查询出患者信息
		Hzxx hzxx = hzxxService.selectByPrimaryKey(Long.parseLong(hzid));
		if (hzxx == null) {
			result.put("_st", 22);//
			result.put("_msg", "患者ID对应患者不存在");
			return result.toJSONString();
		}
		Ysxx ysxx = ysxxService.selectByPrimaryKey(Long.parseLong(ysid));
		if (ysxx == null) {
			result.put("_st", 0);//
			result.put("_msg", "医生ID对应的医生不存在");
			return result.toJSONString();
		}
		// 构造ＩＭ 消息
		Im im = new Im();
		im.setYsid(ysxx.getId());
		im.setYsname(ysxx.getName());
		im.setHzid(hzxx.getID());
		im.setHzname(hzxx.getHZNAME());
		im.setTemp1(ysxx.getTemp1());// 设置医生的头像信息
		im.setTemp2(hzxx.getTEMP2());// 设置患者的头像信息
		im.setTztime(System.currentTimeMillis());
		im.setContent(message);
		int rowid = imService.insert(im);
		if (rowid > 0) {
			result.put("_st", 1);//
			result.put("_msg", "消息发送成功");
			return result.toJSONString();
		} else {
			result.put("_st", 4);//
			result.put("_msg", "消息发送失败");
			return result.toJSONString();
		}
	}

	// 添加患者给医生发图片接口
	@ResponseBody
	@ApiOperation(value = "患者给医生发图片接口，用来发送图片信息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "POST", response = String.class, notes = "患者给医生发图片接口，用来发送图片信息，｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/SendImgMesg/{hzid}/{ysid}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public String SendImgMesg(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = true, name = "ysid", value = "医生ID") @PathVariable String ysid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token,
			@ApiParam(required = true, name = "file", value = "上传的图片文件") @RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(ysid)) {
			result.put("_st", 0);//
			result.put("_msg", "医生ID无效");
			return result.toJSONString();
		}
		if (StringUtils.isBlank(hzid)) {
			result.put("_st", 2);//
			result.put("_msg", "患者ID无效");
			return result.toJSONString();
		}
		if (file == null || file.isEmpty()) {
			result.put("_st", 3);//
			result.put("_msg", "图片信息无效");
			return result.toJSONString();
		}
		// 根据患者ＩＤ 查询出患者信息
		Hzxx hzxx = hzxxService.selectByPrimaryKey(Long.parseLong(hzid));
		if (hzxx == null) {
			result.put("_st", 22);//
			result.put("_msg", "患者ID对应患者不存在");
			return result.toJSONString();
		}
		Ysxx ysxx = ysxxService.selectByPrimaryKey(Long.parseLong(ysid));
		if (ysxx == null) {
			result.put("_st", 0);//
			result.put("_msg", "医生ID对应的医生不存在");
			return result.toJSONString();
		}
		// 需要处理上传的图片信息
		// 获得物理路径webapp所在路径
		StringBuilder pathRoot = new StringBuilder((String) request.getSession().getServletContext().getRealPath(""));
		StringBuilder imagePath = new StringBuilder(request.getContextPath());
		imagePath.append("/upload/");
		pathRoot.append("/upload/");
		File tmpFile = new File(pathRoot.toString());
		if (!tmpFile.isDirectory()) {
			// 创建目录了
			System.out.println("创建目录了");
			tmpFile.mkdirs();// 如果目录 不存在就创建目录
		}
		if (!file.isEmpty()) {
			try {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = file.getContentType();
				// 获得文件后缀名称
				String imageName = contentType.substring(contentType.indexOf("/") + 1);
				pathRoot.append(uuid);
				pathRoot.append(".");
				pathRoot.append(imageName);
				file.transferTo(new File(pathRoot.toString()));
				// 将头像写入用户
				imagePath.append(uuid).append(".").append(imageName);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("_st", 5);//
				result.put("_msg", "图片处理失败");
				return result.toJSONString();
			}
		}

		// 构造ＩＭ 消息
		Im im = new Im();
		im.setYsid(ysxx.getId());
		im.setYsname(ysxx.getName());
		im.setHzid(hzxx.getID());
		im.setHzname(hzxx.getHZNAME());
		im.setTemp1(ysxx.getTemp1());// 设置医生的头像信息
		im.setTemp2(hzxx.getTEMP2());// 设置患者的头像信息
		im.setTztime(System.currentTimeMillis());
		im.setContent(imagePath.toString());
		int rowid = imService.insert(im);
		if (rowid > 0) {
			result.put("_st", 1);//
			result.put("_msg", "消息发送成功");
			return result.toJSONString();
		} else {
			result.put("_st", 4);//
			result.put("_msg", "消息发送失败");
			return result.toJSONString();
		}
	}
	//患者端获取留言消息接口
	@ResponseBody
	@ApiOperation(value = "患者端获取留言消息接口，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "GET", response = String.class, notes = "患者端获取留言消息接口｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/getMesgs/{hzid}/{ysid}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public String getMesgs(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = true, name = "ysid", value = "医生ID") @PathVariable String ysid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(ysid)) {
			result.put("_st", 0);//
			result.put("_msg", "医生ID无效");
			return result.toJSONString();
		}
		if (StringUtils.isBlank(hzid)) {
			result.put("_st", 2);//
			result.put("_msg", "患者ID无效");
			return result.toJSONString();
		}
		//开始获取数据
		List<Im> list = imService.selectByYsid_Hzid(Long.parseLong(ysid), Long.parseLong(hzid));
		String hzheader = "";
		String ysheader = "";
		if(list != null && list.size() >0){
			JSONArray arr = new JSONArray();
			for(Iterator<Im> it = list.iterator(); it.hasNext(); ){
				Im im = it.next();
				JSONObject i = new JSONObject();
				i.put("content", im.getContent());
				i.put("time", im.getTztime());
				hzheader = im.getTemp2();
				ysheader = im.getTemp1();
				arr.add(i);
			}
			result.put("_st", 1);
			result.put("_msg", "获取成功");
			result.put("_hzheader", hzheader);
			result.put("_ysheader", ysheader);
			result.put("_data", arr);
			return result.toJSONString();
		}else{
			result.put("_st", 3);//
			result.put("_msg", "获取失败");
			return result.toJSONString();
		}
	}
	
	// 获取患者信息
	@ResponseBody
	@ApiOperation(value = "获取患者基本信息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "GET", response = String.class, notes = "获取患者基本信息，｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/getHzxx/{hzid}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public String getHzxx(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value = "token", required = true) String token) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(hzid)) {
			result.put("_st", 0);//
			result.put("_msg", "患者ID无效");
			return result.toJSONString();
		}else{
			logger.debug("患者 ＩＤ 转换后的值为：  " + Long.parseLong(hzid));
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
	@ApiOperation(value = "获取患者日志信息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "POST", response = String.class, notes = "获取患者基本信息，｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/findHzxxLog/{hzid}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public String findHzxxLog(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
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
		logger.info(" itemcode = =========== " + itemcode);
		logger.info(" hzid = =========== " + hzid);
		logger.info(" token = =========== " + token);
		logger.info(" startdate = =========== " + startdate);
		logger.info(" enddate = =========== " + enddate);
		
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
				result.put("_st", 6);
				result.put("_msg", "获取失败");
				return result.toJSONString();
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
