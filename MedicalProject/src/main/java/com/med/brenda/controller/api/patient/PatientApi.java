package com.med.brenda.controller.api.patient;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.med.brenda.exception.BusinessException;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.model.TnbYinshi;
import com.med.brenda.model.User;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.service.ITnbTnbsonService;
import com.med.brenda.service.IUserService;
import com.med.brenda.service.IYinshiService;
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
	
	Logger logger = Logger.getLogger(PatientApi.class);
	
	@Autowired
	private IHzxxService hzxxService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IHzsfxxService hzsfxxService;
	@Autowired
	private ITnbTnbsonService tnbsonService;
	@Autowired
	private IYinshiService yinshiService;
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
		User newUser = userService.getUserByUserNamePwd(username,password);

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
	
	@RequestMapping(value="/uploadImage",produces = "application/json; charset=utf-8",method=RequestMethod.POST)  
	 @ResponseBody
	 @ApiOperation(value = "上传单张图片接口 ｜  发布时间： 2016-08-13 12:27", httpMethod = "POST", response = String.class, notes = "上传单张图片接口 ｜  发布时间： 2016-08-13 12:27")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
   public String upLoadImage(
   		@ApiParam(required = true, name = "file", value = "上传的图片文件") @RequestParam(value="file",required=true) MultipartFile file,  
           HttpServletRequest request)throws Exception{  
		JSONObject result = new JSONObject();
		 //获得物理路径webapp所在路径  
        StringBuilder pathRoot = new StringBuilder((String)request.getSession().getServletContext().getRealPath("")); 
        StringBuilder imagePath = new StringBuilder(request.getContextPath());
        imagePath.append("/upload/");
        pathRoot.append("/upload/");
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
            result.put("_st", 1);//
    		result.put("_msg", "上传图片成功");
    		result.put("imagepath", imagePath.toString());
    		return result.toJSONString();
        }else{
        	result.put("_st", 2);//
    		result.put("_msg", "上传图片失败");
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
            System.out.println(pathRoot.toString());  
            //request.setAttribute("imagesPath", path); 
            result.put("_st", 1);//
    		result.put("_msg", "上传用户头像成功");
    		result.put("hzxx", JSON.toJSONString(hzxx));
    		return result.toJSONString();
        }else{
        	result.put("_st", 2);//
    		result.put("_msg", "上传用户头像失败");
    		return result.toJSONString();
        }
        
    }
	 
	@RequestMapping(value="/changeHeadUrl/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@ApiOperation(value = "修改患者头像 ｜  发布时间： 2016-08-21 12:33", httpMethod = "POST", response = String.class, notes = "修改患者头像 ｜  发布时间： 2016-08-21 12:33")
	public String ChangeHeadUrl(@ApiParam(required = true, name = "hzid", value = "患者ID") @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "headerpicurl", value = "头像URL：/upload/") @RequestParam(value="headerpicurl",required=true) String headerpicurl){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(headerpicurl)){
			 result.put("_st", 2);//
			 result.put("_msg", "头像信息无效");
			 return result.toJSONString();
		 }
		Hzxx hzxx = hzxxService.selectByPrimaryKey(Long.parseLong(hzid));
		hzxx.setTEMP2(headerpicurl);//设置头像
		int rowid = hzxxService.updateByPrimaryKeySelective(hzxx);
		if(rowid > 0 ){
			result.put("_st", 1);//
			result.put("_msg", "头像更换成功");
			return result.toJSONString();
		}else{
			result.put("_st", 3);//
			result.put("_msg", "头像更换失败");
			return result.toJSONString();
		}
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
					hzxx.setCSRQ(_birthday);//根据生日计算出年龄
					int age = CommonUtils.getAge(new Date(_birthday));
					hzxx.setAGE(String.valueOf(age));
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
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "date", value = "添加对应的日期，格式：yyyyMMdd") @RequestParam(value="date",required=true) String date,
			@ApiParam(required = true, name = "mon", value = "月份，0：表示当月， -1：上一月， 1：下一月， 认此类推,默认为0,当月") @RequestParam(value="mon",required=false) int mon){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(date)){
			 result.put("_st", 6);//
			 result.put("_msg", "添加对应的日期(date)传入错误");
			 return result.toJSONString(); 
		 }
		 Hzxx hzxx = hzxxService.findHzByHzID(Long.parseLong(hzid));
		 if(hzxx == null){
			 result.put("_st", 2);//
			 result.put("_msg", "患者不存在");
			 return result.toJSONString();
		 }
		 //检查当前用户有没有初始化当天的随防数据
		 try {
			if(!hzsfxxService.checkHzxfxxBaseDB(Long.parseLong(hzid), CommonUtils.getTimeInMillisByDate(date))){//CommonUtils.getTimeInMillisBy00_00_00())){
				 //初始化
				 hzsfxxService.addHzsfxxBeaseDB(new ArrayList<Hzsfxx>(), Long.parseLong(hzid), CommonUtils.getTimeInMillisByDate(date));
			 }

			 //查询sf的数据返回
			String backStr = "";
			 if(mon == 0){//日期不变
				backStr = hzsfxxService.getCurrentDateTNB(Long.parseLong(hzid), date);
			 }else{
				 String cur_date = CommonUtils.getPreDateStr(date);
				 backStr = hzsfxxService.getCurrentDateTNB(Long.parseLong(hzid), cur_date);
			 }
			 //暂不提供返回的数据。
			 return backStr;
		} catch (Exception e) {
			result.put("_st", 7);//
			 result.put("_msg", "初始化失败");
			 return result.toJSONString();
		}
		 
	}
	
	
	@ResponseBody
	@ApiOperation(value = "添加血糖，｜  发布时间： 2016-08-12 13:37 ", httpMethod = "POST", response = String.class, notes = "添加血糖，｜  发布时间： 2016-08-12 13:37")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/FeedXT/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String feedXT(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "itemcode", value = "血糖对应编号：凌晨015001；早餐前015002001；早餐后015002002；...") @RequestParam(value="itemcode",required=true) String itemcode,
			@ApiParam(required = true, name = "addtime", value = "添加血糖的时间,格式为：HH:MM") @RequestParam(value="addtime",required=true) String addtime,
			@ApiParam(required = true, name = "itemvalue", value = "血糖值") @RequestParam(value="itemvalue",required=true) String itemvalue,
			@ApiParam(required = true, name = "date", value = "添加对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
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
		 if(StringUtils.isBlank(itemcode)){
			 result.put("_st", 3);//
			 result.put("_msg", "itemcode传入错误");
			 return result.toJSONString(); 
		 }

		 if(StringUtils.isBlank(itemvalue)){
			 result.put("_st", 4);//
			 result.put("_msg", "itemvalue传入错误");
			 return result.toJSONString(); 
		 }
		 if(StringUtils.isBlank(addtime)){
			 result.put("_st", 5);//
			 result.put("_msg", "addtime传入错误");
			 return result.toJSONString(); 
		 }
		 if(StringUtils.isBlank(date)){
			 result.put("_st", 6);//
			 result.put("_msg", "添加对应的日期(date)传入错误");
			 return result.toJSONString(); 
		 }
		 //根据传入的日期转换成对应的long
		 Long _date = null;
			try {
				_date = CommonUtils.getTimeInMillisByDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 //插入相关的值之前，先查询出在hzsfxx表主对应项目的ID；
		 Long hzsfFatherId = hzsfxxService.findByHzidDataItemCode(Long.parseLong(hzid), _date, itemcode.substring(0,3));
		 //创建一个添加血糖的对象
		 TnbTnbson bloodSugar = new TnbTnbson();
		 bloodSugar.setFatherid(hzsfFatherId);
		 bloodSugar.setHzid(Long.parseLong(hzid));
		 bloodSugar.setItemcode(itemcode);
		 bloodSugar.setItemname(CommonUtils.getBloodSugarByItemCode(itemcode));
		 bloodSugar.setItemvalue(itemvalue);
		 bloodSugar.setTemp1(addtime);
	     bloodSugar.setTemp5(date);	
	     bloodSugar.setTemp4(CommonUtils.getCurDate());
	     int rowid = tnbsonService.insert(bloodSugar);
	     if(rowid > 0 ){
	    	 result.put("_st", 1);//
			 result.put("_msg", "添加成功！");
			 result.put("_data", JSON.toJSONString(bloodSugar));
			 return result.toJSONString(); 
	     }else{
	    	 result.put("_st", 7);//
			 result.put("_msg", "添加失败！");
			 return result.toJSONString(); 
	     }
	}
	
	@ResponseBody
	@ApiOperation(value = "更新血糖，｜  发布时间： 2016-08-12 15:15 ", httpMethod = "POST", response = String.class, notes = "更新血糖，｜  发布时间： 2016-08-12 15:15 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/FeedXTUpdate",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String feedXTUpdate(
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "dataid", value = "血糖项目ID") @RequestParam(value="dataid",required=true) String dataid,
			@ApiParam(required = true, name = "addtime", value = "添加血糖的时间,格式为：HH:MM") @RequestParam(value="addtime",required=true) String addtime,
			@ApiParam(required = true, name = "itemvalue", value = "血糖更新值") @RequestParam(value="itemvalue",required=true) String itemvalue){
		 JSONObject result = new JSONObject();
		 if(StringUtils.isBlank(dataid)){
			 result.put("_st", 0);//
			 result.put("_msg", "修改项目ID值无效");
			 return result.toJSONString(); 
		 }
		 TnbTnbson tnbson = tnbsonService.selectByPrimaryKey(Long.parseLong(dataid));
		 if(tnbson == null){
			 result.put("_st", 2);//
			 result.put("_msg", "项目ID对应值不存在");
			 return result.toJSONString(); 
		 }
		 //update对应值
		 tnbson.setItemvalue(itemvalue!=null?itemvalue.trim():"");
		 tnbson.setTemp1(addtime!=null?addtime.trim():"");
		 tnbson.setTemp4(CommonUtils.getCurDate());
		 int rowid = tnbsonService.updateByPrimaryKeySelective(tnbson);
	     if(rowid > 0 ){
	    	 result.put("_st", 1);//
			 result.put("_msg", "更新成功！");
			 result.put("_data", JSON.toJSONString(tnbson));
			 return result.toJSONString(); 
	     }else{
	    	 result.put("_st", 7);//
			 result.put("_msg", "更新失败！");
			 return result.toJSONString(); 
	     }
	}
	
	@ResponseBody
	@ApiOperation(value = "获取某天的所有血糖数据，｜  发布时间： 2016-08-12 15:26 ", httpMethod = "GET", response = String.class, notes = "获取某天的所有血糖数据，｜  发布时间： 2016-08-12 15:26 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/GetFeedXTByDate/{hzid}/{date}",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String getfeedXT(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "date", value = "要查询的日期，格式：yyyy-MM-dd，如2016-08-09 ， 8和9之前需要补0") @PathVariable String date){
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
		 List<TnbTnbson> list = tnbsonService.findFeedList(Long.parseLong(hzid), "015", date);
		 if(list != null && list.size() > 0 ){
			 result.put("_st", 1);//
			 result.put("_msg", "获取成功");
			 result.put("_datalist", JSON.toJSONString(list));
			 return result.toJSONString();
		 }else{
			 result.put("_st", 3);//
			 result.put("_msg", "获取失败");
			 return result.toJSONString();
		 }
	}
	
	@ResponseBody
	@ApiOperation(value = "添加胰岛素，｜  发布时间： 2016-08-13 10:21 ", httpMethod = "POST", response = String.class, notes = "添加胰岛素，｜  发布时间： 2016-08-13 10:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/FeedYDS/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String feedYDS(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "itemcode", value = "胰岛素对应编号：凌晨015001；早016001；中016002；晚016003；睡前016004") @RequestParam(value="itemcode",required=true) String itemcode,
			@ApiParam(required = true, name = "ydsnames", value = "选择的胰岛素名称，多个以空格分隔") @RequestParam(value="ydsnames",required=true) String ydsnames,
			@ApiParam(required = true, name = "ydsjl", value = "胰岛素计量") @RequestParam(value="ydsjl",required=true) String ydsjl,
			@ApiParam(required = true, name = "ydsrjsh", value = "胰岛素二甲双狐") @RequestParam(value="ydsrjsh",required=true) String ydsrjsh,
			@ApiParam(required = true, name = "date", value = "添加对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		//判断参数
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(date)){
			 result.put("_st", 6);//
			 result.put("_msg", "添加对应的日期(date)传入错误");
			 return result.toJSONString(); 
		 }
		if(StringUtils.isBlank(itemcode)){
			 result.put("_st", 3);//
			 result.put("_msg", "itemcode传入错误");
			 return result.toJSONString(); 
		 }
		if(StringUtils.isBlank(ydsnames)){
			 result.put("_st", 2);//
			 result.put("_msg", "选择的胰岛素名称为空");
			 return result.toJSONString(); 
		}
		if(StringUtils.isBlank(ydsjl)){
			 result.put("_st", 4);//
			 result.put("_msg", "胰岛素计量为空");
			 return result.toJSONString(); 
		}
		if(StringUtils.isBlank(ydsrjsh)){
			 result.put("_st", 5);//
			 result.put("_msg", "胰岛素二甲双狐为空");
			 return result.toJSONString(); 
		}
		//查询出患者随访中对应的ID值
		//插入相关的值之前，先查询出在hzsfxx表主对应项目的ID；
		//根据传入的日期转换成对应的long
		 Long _date = null;
			try {
				_date = CommonUtils.getTimeInMillisByDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 Long hzsfFatherId = hzsfxxService.findByHzidDataItemCode(Long.parseLong(hzid), _date, itemcode.substring(0,3));
		 if(hzsfFatherId == null){
			 result.put("_st", 7);//
			 result.put("_msg", "添加胰岛素失败");
			 return result.toJSONString(); 
		 }else{
			 TnbTnbson bs = new TnbTnbson();
			 bs.setFatherid(hzsfFatherId);
			 bs.setHzid(Long.parseLong(hzid));
			 bs.setItemcode(itemcode);
			 bs.setItemname(CommonUtils.getBloodSugarByItemCode(itemcode));
			 bs.setYds(ydsnames);
			 bs.setYdsjl(ydsjl!=null?ydsjl.trim():"");
			 bs.setYdsejsg(ydsrjsh!=null?ydsrjsh.trim():"");
			 bs.setTemp4(date);//更新时间
			 bs.setTemp5(date);//插入时间
			 
			 int rowid = tnbsonService.insert(bs);
			 if(rowid >0 ){
				 result.put("_st", 1);//
				 result.put("_msg", "添加成功！");
				 result.put("_data", JSON.toJSONString(bs));
				 return result.toJSONString(); 
		     }else{
		    	 result.put("_st", 7);//
				 result.put("_msg", "添加失败！");
				 return result.toJSONString(); 
		     }
		 }
	}
	
	@ResponseBody
	@ApiOperation(value = "修改胰岛素，｜  发布时间： 2016-08-13 11:21 ", httpMethod = "POST", response = String.class, notes = "修改胰岛素，｜  发布时间： 2016-08-13 11:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/FeedYDSUpdate/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String feedYDSUpdate(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "dataid", value = "修改记录ID") @RequestParam(value="dataid",required=true) String dataid,
			@ApiParam(required = true, name = "ydsnames", value = "选择的胰岛素名称，多个以空格分隔") @RequestParam(value="ydsnames",required=true) String ydsnames,
			@ApiParam(required = true, name = "ydsjl", value = "胰岛素计量") @RequestParam(value="ydsjl",required=true) String ydsjl,
			@ApiParam(required = true, name = "ydsrjsh", value = "胰岛素二甲双狐") @RequestParam(value="ydsrjsh",required=true) String ydsrjsh){
		JSONObject result = new JSONObject();
		//判断参数
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(dataid)){
			 result.put("_st", 3);//
			 result.put("_msg", "修改记录ID传入错误");
			 return result.toJSONString(); 
		 }
		if(StringUtils.isBlank(ydsnames)){
			 result.put("_st", 2);//
			 result.put("_msg", "选择的胰岛素名称为空");
			 return result.toJSONString(); 
		}
		if(StringUtils.isBlank(ydsjl)){
			 result.put("_st", 4);//
			 result.put("_msg", "胰岛素计量为空");
			 return result.toJSONString(); 
		}
		if(StringUtils.isBlank(ydsrjsh)){
			 result.put("_st", 5);//
			 result.put("_msg", "胰岛素二甲双狐为空");
			 return result.toJSONString(); 
		}
		TnbTnbson tnbson = tnbsonService.selectByPrimaryKey(Long.parseLong(dataid.trim()));
		if(tnbson != null){
			tnbson.setYds(ydsnames);
			tnbson.setYdsjl(ydsjl!=null?ydsjl.trim():"");
			tnbson.setYdsejsg(ydsrjsh!=null?ydsrjsh.trim():"");
			tnbson.setTemp4(CommonUtils.getCurDate());//更新时间
			int rowid = tnbsonService.updateByPrimaryKeySelective(tnbson);
			if(rowid > 0 ){
				result.put("_st", 1);//
				result.put("_msg", "修改成功！");
				result.put("_data", JSON.toJSONString(tnbson));
				return result.toJSONString(); 
		     }else{
		    	 result.put("_st", 7);//
				 result.put("_msg", "更新失败！");
				 return result.toJSONString(); 
		     }
		}else{
			result.put("_st", 8);//
			result.put("_msg", "记录ID没有对应记录");
			return result.toJSONString(); 
		}
	}
	
	@ResponseBody
	@ApiOperation(value = "获取某天所有胰岛素记录，｜  发布时间： 2016-08-13 11:21 ", httpMethod = "GET", response = String.class, notes = "获取某天所有胰岛素记录，｜  发布时间： 2016-08-13 11:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/getFeedYDS/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String getFeedYDS(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "date", value = "添加对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		//判断参数
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(date)){
			 result.put("_st", 6);//
			 result.put("_msg", "添加对应的日期(date)传入错误");
			 return result.toJSONString(); 
		 }
		List<TnbTnbson> list = tnbsonService.findFeedList(Long.parseLong(hzid), "016", date);
		if(list != null && list.size() > 0 ){
			result.put("_st", 1);//
			 result.put("_msg", "获取成功");
			 result.put("_datalist", JSON.toJSONString(list));
			 return result.toJSONString();
		 }else{
			 result.put("_st", 3);//
			 result.put("_msg", "获取失败");
			 return result.toJSONString();
		 }
	}
	
	//修改饮食
	@ResponseBody
	@ApiOperation(value = "修改饮食，｜  发布时间： 2016-08-13 13:21 ", httpMethod = "POST", response = String.class, notes = "修改饮食，｜  发布时间： 2016-08-13 13:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/ModifyDiet/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String modifyDiet(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "dataid", value = "饮食记录ID") @RequestParam(value="dataid",required=true) String dataid,
			@ApiParam(required = true, name = "dietjsondata", value = "饮食数据，以JSON串形式传入diet饮食的值，addmeal加餐的值，格式：{\"diet0\":1,\"diet1\":2,...,\"addmeal0\":1,\"addmeal\":12,...,\"image0\":\"/upload/图片名称\",...}") @RequestParam(value="dietjsondata",required=true) String dietjsondata,
			@ApiParam(required = true, name = "date", value = "添加饮食对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();

		logger.debug("json 串为= "+ dietjsondata);
		//判断参数
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(date)){
			 result.put("_st", 6);//
			 result.put("_msg", "添加对应的日期(date)传入错误");
			 return result.toJSONString(); 
		 }
		if(StringUtils.isBlank(dataid)){
			 result.put("_st", 3);//
			 result.put("_msg", "dataid传入错误");
			 return result.toJSONString(); 
		 }
		if(StringUtils.isBlank(dietjsondata)){
			 result.put("_st", 2);//
			 result.put("_msg", "饮食数据传入错误");
			 return result.toJSONString(); 
		 }
		//根据ID查询到饮食的值
		TnbYinshi tnbys = yinshiService.selectByPrimaryKey(Long.parseLong(dataid));
		JSONObject perfectJsoninfo = JSON.parseObject(dietjsondata);
		for(int i = 0 ; i< 8 ; i ++){
			if(StringUtils.isNoneBlank(perfectJsoninfo.getString("diet"+i))){
				switch(i){
				case 0:
						tnbys.setZhushi(perfectJsoninfo.getString("diet"+i));
					break;
				case 1:
					tnbys.setSc(perfectJsoninfo.getString("diet"+i));
					break;
				case 2:
					tnbys.setShuiguo(perfectJsoninfo.getString("diet"+i));
					break;
				case 3:
					tnbys.setRou(perfectJsoninfo.getString("diet"+i));
					break;
				case 4:
					tnbys.setNai(perfectJsoninfo.getString("diet"+i));
					break;
				case 5:
					tnbys.setDan(perfectJsoninfo.getString("diet"+i));
					break;
				case 6:
					tnbys.setYou(perfectJsoninfo.getString("diet"+i));
					break;
				case 7:
					tnbys.setBindgan(perfectJsoninfo.getString("diet"+i));
					break;
				default:
					break;
				}
			}
		}
		for(int i = 0 ; i< 8 ; i ++){
			if(StringUtils.isNoneBlank(perfectJsoninfo.getString("addmeal"+i))){
				switch(i){
				case 0:
					tnbys.setJzhushi(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 1:
					tnbys.setJsc(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 2:
					tnbys.setJshuiguo(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 3:
					tnbys.setJrou(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 4:
					tnbys.setJnai(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 5:
					tnbys.setJdan(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 6:
					tnbys.setJyou(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 7:
					tnbys.setJbindgan(perfectJsoninfo.getString("addmeal"+i));
					break;
				default:
					break;
				}
			}
		}
		for(int i = 0 ; i < 3; i ++){
			if(StringUtils.isNoneBlank(perfectJsoninfo.getString("image"+i))){
				switch(i){
				case 0:
					tnbys.setImage1(perfectJsoninfo.getString("image"+i));
					break;

				case 1:
					tnbys.setImage2(perfectJsoninfo.getString("image"+i));
					break;

				case 2:
					tnbys.setImage3(perfectJsoninfo.getString("image"+i));
					break;
				default:
					break;
				}
			}
		}
		tnbys.setTemp4(CommonUtils.getCurDate());//修改时间
		int rowid = yinshiService.updateByPrimaryKeySelective(tnbys);
		if(rowid > 0 ){
			result.put("_st", 1);//
			 result.put("_msg", "添加饮食成功");
			 result.put("_data", JSON.toJSONString(tnbys));
			 return result.toJSONString(); 
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "添加饮食失败");
			 return result.toJSONString(); 
		}
	}
	
	//添加饮食
	@ResponseBody
	@ApiOperation(value = "添加饮食，｜  发布时间： 2016-08-13 13:21 ", httpMethod = "POST", response = String.class, notes = "添加饮食，｜  发布时间： 2016-08-13 13:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/AddDiet/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String addDiet(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "itemcode", value = "饮食对应编号：早021001；中021002；晚021003；") @RequestParam(value="itemcode",required=true) String itemcode,
			@ApiParam(required = true, name = "dietjsondata", value = "饮食数据，以JSON串形式传入diet饮食的值，addmeal加餐的值，格式：{\"diet0\":1,\"diet1\":2,...,\"addmeal0\":1,\"addmeal\":12,...,\"image0\":\"/upload/图片名称\",...}") @RequestParam(value="dietjsondata",required=true) String dietjsondata,
			@ApiParam(required = true, name = "date", value = "添加饮食对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		logger.debug("json 串为= "+ dietjsondata);
		//判断参数
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(date)){
			 result.put("_st", 6);//
			 result.put("_msg", "添加对应的日期(date)传入错误");
			 return result.toJSONString(); 
		 }
		if(StringUtils.isBlank(itemcode)){
			 result.put("_st", 3);//
			 result.put("_msg", "itemcode传入错误");
			 return result.toJSONString(); 
		 }
		if(StringUtils.isBlank(dietjsondata)){
			 result.put("_st", 2);//
			 result.put("_msg", "饮食数据传入错误");
			 return result.toJSONString(); 
		 }
		//根据患者ID、数据日期，和itemcode前三位值，查询出对应的值的hzsfxx表中的ID值。
		//插入相关的值之前，先查询出在hzsfxx表主对应项目的ID；
		//根据传入的日期转换成对应的long
		 Long _date = null;
			try {
				_date = CommonUtils.getTimeInMillisByDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		Long hzsfFatherId = hzsfxxService.findByHzidDataItemCode(Long.parseLong(hzid), _date, itemcode.substring(0,3));
		TnbYinshi tnbys = new TnbYinshi();//饮食对象
		tnbys.setFatherid(hzsfFatherId);
		tnbys.setItemcode(itemcode);
		tnbys.setHzid(Long.parseLong(hzid));
		tnbys.setItemname(CommonUtils.getBloodSugarByItemCode(itemcode));
		JSONObject perfectJsoninfo = JSON.parseObject(dietjsondata);
		for(int i = 0 ; i< 8 ; i ++){
			if(StringUtils.isNoneBlank(perfectJsoninfo.getString("diet"+i))){
				switch(i){
				case 0:
						tnbys.setZhushi(perfectJsoninfo.getString("diet"+i));
					break;
				case 1:
					tnbys.setSc(perfectJsoninfo.getString("diet"+i));
					break;
				case 2:
					tnbys.setShuiguo(perfectJsoninfo.getString("diet"+i));
					break;
				case 3:
					tnbys.setRou(perfectJsoninfo.getString("diet"+i));
					break;
				case 4:
					tnbys.setNai(perfectJsoninfo.getString("diet"+i));
					break;
				case 5:
					tnbys.setDan(perfectJsoninfo.getString("diet"+i));
					break;
				case 6:
					tnbys.setYou(perfectJsoninfo.getString("diet"+i));
					break;
				case 7:
					tnbys.setBindgan(perfectJsoninfo.getString("diet"+i));
					break;
				default:
					break;
				}
			}
		}
		for(int i = 0 ; i< 8 ; i ++){
			if(StringUtils.isNoneBlank(perfectJsoninfo.getString("addmeal"+i))){
				switch(i){
				case 0:
					tnbys.setJzhushi(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 1:
					tnbys.setJsc(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 2:
					tnbys.setJshuiguo(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 3:
					tnbys.setJrou(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 4:
					tnbys.setJnai(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 5:
					tnbys.setJdan(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 6:
					tnbys.setJyou(perfectJsoninfo.getString("addmeal"+i));
					break;
				case 7:
					tnbys.setJbindgan(perfectJsoninfo.getString("addmeal"+i));
					break;
				default:
					break;
				}
			}
		}
		for(int i = 0 ; i < 3; i ++){
			if(StringUtils.isNoneBlank(perfectJsoninfo.getString("image"+i))){
				switch(i){
				case 0:
					tnbys.setImage1(perfectJsoninfo.getString("image"+i));
					break;

				case 1:
					tnbys.setImage2(perfectJsoninfo.getString("image"+i));
					break;

				case 2:
					tnbys.setImage3(perfectJsoninfo.getString("image"+i));
					break;
				default:
					break;
				}
			}
		}
		tnbys.setTemp4(date);//修改时间
		tnbys.setTemp5(date);//创建时间
		
		int rowid = yinshiService.insert(tnbys);
		if(rowid > 0 ){
			result.put("_st", 1);//
			 result.put("_msg", "添加饮食成功");
			 result.put("_data", JSON.toJSONString(tnbys));
			 return result.toJSONString(); 
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "添加饮食失败");
			 return result.toJSONString(); 
		}
	}
	
	//根据患者ID，和日期获取饮食记录
	@ResponseBody
	@ApiOperation(value = "根据患者ID，和日期获取饮食记录，｜  发布时间： 2016-08-13 13:21 ", httpMethod = "GET", response = String.class, notes = "根据患者ID，和日期获取饮食记录，｜  发布时间： 2016-08-13 13:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/GetDiets/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String getDiets(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "date", value = "添加饮食对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		//判断参数
		if(StringUtils.isBlank(hzid+"")){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		 }
		if(StringUtils.isBlank(date)){
			 result.put("_st", 6);//
			 result.put("_msg", "添加对应的日期(date)传入错误");
			 return result.toJSONString(); 
		 }
		String back = "请填写性别和出生日期";
		Hzxx hzxx = hzxxService.selectByPrimaryKey(Long.parseLong(hzid));
		String sex = hzxx.getSEX();
		Long csrq = hzxx.getCSRQ();
		if(StringUtils.isBlank(sex) || csrq == null || csrq == 0){
			back = "请填写性别和出生日期";
		}else{
			back = "287";
		}

		//根据 hzid , 021 ,日期，查询出所有的饮食记录
		List<TnbYinshi> list = yinshiService.getYinshiListByHzid_date(Long.parseLong(hzid), "021", date);
		if(list != null && list.size() > 0 ){
			String _jsonStr = JSON.toJSONString(list);
			//JSONObject j = JSON.parseObject(_jsonStr);
			JSONArray js = JSON.parseArray(_jsonStr);
			JSONArray newArray = new JSONArray();
			if(js.size() > 0 ){
				for(Iterator it = js.iterator(); it.hasNext();){
					JSONObject _tmpJson = (JSONObject)it.next();
					_tmpJson.put("Biaozhunrl", back);
					newArray.add(_tmpJson);
				}
			}
			result.put("_st", 1);//
			result.put("_msg", "获取成功");
			result.put("_datalist", newArray.toJSONString());
			return result.toJSONString();
		 }else{
			 result.put("_st", 3);//
			 result.put("_msg", "获取失败");
			 return result.toJSONString();
		 }
	}
}
