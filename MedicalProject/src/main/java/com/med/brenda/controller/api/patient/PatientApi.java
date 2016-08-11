package com.med.brenda.controller.api.patient;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.exception.BusinessException;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.User;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.service.IUserService;
import com.med.brenda.util.CommonUtils;
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
	@Autowired
	private IHzsfxxService hzsfxxService;
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
	
	 @RequestMapping(value="/uploadHzHeader/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)  
	 @ResponseBody
	 @ApiOperation(value = "上传用户头像 ｜  发布时间： 2016-08-09 22:27", httpMethod = "POST", response = String.class, notes = "上传用户头像  2016-08-09 22:27")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
    public String uploadHzHeader(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid ,
    		@ApiParam(required = true, name = "file", value = "上传的头像文件") @RequestParam(value="file",required=true) MultipartFile file,  
            HttpServletRequest request)throws Exception{  
		 System.out.println("hzid =" + hzid);
		 System.out.println("file =" + file);
		 JSONObject result = new JSONObject();
		 if(StringUtils.isBlank(hzid)){ 
			result.put("_st", 0);//
			result.put("_msg", "患者ID无效");
			return result.toJSONString();
		 }
		 Hzxx hzxx = hzxxService.findHzByHzID(Long.parseLong(hzid));
        //获得物理路径webapp所在路径  
        StringBuilder pathRoot = new StringBuilder((String)request.getSession().getServletContext().getRealPath("")); 
        StringBuilder imagePath = new StringBuilder(request.getContextPath());
        imagePath.append("/upload/");
        imagePath.append(hzid).append("/");
        
        pathRoot.append("/upload/");
        pathRoot.append(hzid);
        pathRoot.append("/");
        File tmpFile = new File(pathRoot.toString());
        if(!tmpFile.isDirectory()){
        	//创建目录了
        	System.out.println("创建目录了");
        	tmpFile.mkdirs();//如果目录 不存在就创建目录
        }
        if(!file.isEmpty()){  
            //生成uuid作为文件名称  
            String uuid = UUID.randomUUID().toString().replaceAll("-","");  
            //获得文件类型（可以判断如果不是图片，禁止上传）  
            String contentType=file.getContentType();  
            //获得文件后缀名称  
            String imageName=contentType.substring(contentType.indexOf("/")+1);  
            pathRoot.append(uuid);
            pathRoot.append(".");
            pathRoot.append(imageName);
            file.transferTo(new File(pathRoot.toString())); 
            //将头像写入用户
            imagePath.append(uuid).append(".").append(imageName);
            hzxx.setTEMP2(imagePath.toString());
            System.out.println(">>>>> "+imagePath.toString());
            hzxxService.updateByPrimaryKeySelective(hzxx);
        }  
        System.out.println(pathRoot.toString());  
        //request.setAttribute("imagesPath", path); 
        result.put("_st", 1);//
		result.put("_msg", "上传用户头像成功");
		result.put("hzxx", JSON.toJSONString(hzxx));
		return result.toJSONString();
    }
	 
	 @ResponseBody
	 @ApiOperation(value = "检查当前用户是否完善了信息 ｜  发布时间： 2016-08-09 22:33", httpMethod = "GET", response = String.class, notes = "检查当前用户是否完善了信息")
	 @ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	 @RequestMapping(value="/perfectHZXX/{hzid}/{pushtoken}/{mobileversion}",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String checkAboutPerfect(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = false, name = "pushtoken", value = "手机用于推送的pushtoken") @PathVariable String pushtoken,
			@ApiParam(required = false, name = "mobileversion", value = "手机品牌&型号") @PathVariable String mobileversion){
		 JSONObject result = new JSONObject();
		 if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		 Hzxx hzxx = hzxxService.findHzByHzID(Long.parseLong(hzid));
		 
		 if(hzxx != null){
			 if(StringUtils.isNoneBlank(pushtoken)){
				 //更新pushToken,到temp9
				 hzxx.setTEMP9(pushtoken);
				 hzxx.setTEMP8(mobileversion);
				 hzxxService.updateByPrimaryKeySelective(hzxx);
			 }
			 String flag = hzxx.getTEMP10();
			 if(StringUtils.isEmpty(flag)){
				 result.put("_st", 2);//
				 result.put("_msg", "需要完善信息");
				 return result.toJSONString();
			 }else{
				 result.put("_st", 1);//
				 result.put("_msg", "信息已完善");
				 return result.toJSONString(); 
			 }
		 }else{
			 result.put("_st", 3);//
			 result.put("_msg", "患者ID不存在");
			 return result.toJSONString(); 
		 }
	}
	 
	@ResponseBody
	@ApiOperation(value = "完善患者主要信息,用户信息以JSON格式串上传,本接口不步完善，第一步就只填写:1、名族,2、生日,3、确诊日期,4、性别; 格式为：{\"nation\":\"汉\",\"sex\":\"男\",\"birthday\":\"生日毫秒数（long）\",\"diagnosisdate\":\"确诊日期对应毫秒数（long）\"}｜发布时间：  2016-08-09 23:03", httpMethod = "POST", response = String.class, notes = "完善患者主要信息,用户信息以JSON格式串上传,本接口不步完善，第一步就只填写:1、名族,2、生日,3、确诊日期4、性别; 格式为：{\"nation\":\"汉\",\"sex\":\"男\",\"birthday\":\"生日毫秒数（long）\",\"diagnosisdate\":\"确诊日期对应毫秒数（long）\"}")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/perfectHZXX",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String perfectHzxx(@ApiParam(required = true, name = "hzid", value = "患者ID") @RequestParam(value="hzid",required=true) String hzid,
			@ApiParam(required = true, name = "perfectinfo", value = "患者完善信息JSON串,方便以后添加") @RequestParam(value="perfectinfo",required=true) String perfectinfo){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		try{
			 Hzxx hzxx = hzxxService.findHzByHzID(Long.parseLong(hzid));
			if(StringUtils.isNoneBlank(perfectinfo)){
				JSONObject perfectJsoninfo = JSON.parseObject(perfectinfo);
				if(perfectJsoninfo != null && hzxx != null){
					String _nation = perfectJsoninfo.getString("nation");
					Long _birthday =  perfectJsoninfo.getLong("birthday");
					Long _diagnosisdate = perfectJsoninfo.getLong("diagnosisdate");
					String _sex = perfectJsoninfo.getString("sex");
					hzxx.setCSRQ(_birthday);
					hzxx.setNFMQZSJ(String.valueOf(_diagnosisdate));
					hzxx.setMZ(_nation);
					hzxx.setSEX(_sex);
					//设备完善信息的标记，即temp10  = 2;
					hzxx.setTEMP10("2");
					hzxxService.updateByPrimaryKeySelective(hzxx);
				}
			}
			result.put("_st", 1);//
			 result.put("_msg", "完善患者信息成功");
			 return result.toJSONString();
		}catch(Exception e){
			e.printStackTrace();
			result.put("_st", 2);//
			 result.put("_msg", "完善患者信息失败");
			 return result.toJSONString();
		}
	}
	
	@ResponseBody
	@ApiOperation(value = "用户每天成功登录系统后的初始化数据+【获取主页数据接口（暂为实现）】 ，｜  发布时间： 2016-08-11 23:45 ", httpMethod = "POST", response = String.class, notes = "")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/initHZSFXX",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String initHzsfxx(@ApiParam(required = true, name = "hzid", value = "患者ID") @RequestParam(value="hzid",required=true) String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		 Hzxx hzxx = hzxxService.findHzByHzID(Long.parseLong(hzid));
		 if(hzxx == null){
			 result.put("_st", 2);//
			 result.put("_msg", "患者不存在");
			 return result.toJSONString();
		 }
		 //检查当前用户有没有初始化当天的随防数据
		 if(!hzsfxxService.checkHzxfxxBaseDB(Long.parseLong(hzid), CommonUtils.getTimeInMillisBy00_00_00())){
			 //初始化
			 hzsfxxService.addHzsfxxBeaseDB(new ArrayList<Hzsfxx>(), Long.parseLong(hzid));
		 }
		 
		 //查询sf的数据返回
		 //暂不提供返回的数据。
		 result.put("_st", 1);//
		 result.put("_msg", "初始化成功");
		 result.put("_data", new JSONObject().toJSONString());
		 return result.toJSONString();
	}
}
