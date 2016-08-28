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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.Im;
import com.med.brenda.model.Mz;
import com.med.brenda.model.Ysxx;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.service.IIMService;
import com.med.brenda.service.IMzService;
import com.med.brenda.service.IYsxxService;
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

	// 获取医生的信息
	@ResponseBody
	@ApiOperation(value = "获取医生的信息，｜  发布时间： 2016-08-28 09:28 ", httpMethod = "GET", response = String.class, notes = "获取医生的信息，｜  发布时间： 2016-08-28 09:28")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value = "/GetDoctorInfo/{ysid}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
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
}
