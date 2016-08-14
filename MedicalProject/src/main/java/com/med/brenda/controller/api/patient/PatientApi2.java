package com.med.brenda.controller.api.patient;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.model.Hzsfxx;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.service.IHzsfxxService;
import com.med.brenda.service.ITnbTnbsonService;
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
public class PatientApi2 {

	@Autowired
	private ITnbTnbsonService tnbsonService;
	@Autowired
	private IHzsfxxService hzsfxxService;
	
	//添加运动
	@ResponseBody
	@ApiOperation(value = "添加运动，｜  发布时间： 2016-08-13 13:21 ", httpMethod = "POST", response = String.class, notes = "添加运动，｜  发布时间： 2016-08-13 13:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/AddMovement/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String AddMovement(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "itemcode", value = "运动对应编号：022") @RequestParam(value="itemcode",required=true) String itemcode,
			@ApiParam(required = true, name = "jsondata", value = "运动数据，以JSON串形式传入运动的值，格式：{\"movementtype\":1,\"duration\":\"2.5(填写数字不带汉字)\",\"remarks\":\"xxxxx\"}") @RequestParam(value="jsondata",required=true) String jsondata,
			@ApiParam(required = true, name = "date", value = "添加运动对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(itemcode)){
			 result.put("_st", 2);//
			 result.put("_msg", "itemcode无效");
			 return result.toJSONString();
		}

		if(StringUtils.isBlank(jsondata)){
			 result.put("_st", 3);//
			 result.put("_msg", "jsondata无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		//根据患者ID， 时间， itemcode查询出hzsfxx对应记录ID
		 //根据传入的日期转换成对应的long
		 Long _date = null;
		try {
			_date = CommonUtils.getTimeInMillisByDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long hzsfxxid = hzsfxxService.findByHzidDataItemCode(Long.parseLong(hzid), _date, itemcode);
		TnbTnbson bs = new TnbTnbson();
		bs.setFatherid(hzsfxxid);
		bs.setHzid(Long.parseLong(hzid));
		bs.setItemcode(itemcode);
		bs.setItemname(CommonUtils.getBloodSugarByItemCode(itemcode));
		JSONObject json = JSONObject.parseObject(jsondata);
		if(json != null){
			bs.setYdlx(json.getString("movementtype").trim());
			bs.setYdcxsj(json.getString("duration").trim());
			bs.setYdtjbz(json.getString("remarks").trim());
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "添加运动失败");
			 return result.toJSONString(); 
		}
		bs.setTemp4(date);//修改时间
		bs.setTemp5(date);//添加时间
		int rowid = tnbsonService.insert(bs);
		if(rowid > 0 ){
			result.put("_st", 1);//
			 result.put("_msg", "添加运动成功");
			 result.put("_data", JSON.toJSONString(bs));
			 return result.toJSONString(); 
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "添加运动失败");
			 return result.toJSONString(); 
		}
	}
	
	//修改运动
	@ResponseBody
	@ApiOperation(value = "修改运动，｜  发布时间： 2016-08-13 13:21 ", httpMethod = "POST", response = String.class, notes = "修改运动，｜  发布时间： 2016-08-13 13:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/ModifyMovement/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String ModifyMovement(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "dataid", value = "运动记录ID") @RequestParam(value="dataid",required=true) String dataid,
			@ApiParam(required = true, name = "jsondata", value = "运动数据，以JSON串形式传入运动的值，格式：{\"movementtype\":1,\"duration\":\"2.5(填写数字不带汉字)\",\"remarks\":\"xxxxx\"}") @RequestParam(value="jsondata",required=true) String jsondata,
			@ApiParam(required = true, name = "date", value = "修改运动对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(dataid)){
			 result.put("_st", 2);//
			 result.put("_msg", "dataid无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		//根据患者ID，查询出运动对应记录ID
		TnbTnbson bs = tnbsonService.selectByPrimaryKey(Long.parseLong(dataid));
		JSONObject json = JSONObject.parseObject(jsondata);
		if(json != null){
			bs.setYdlx(json.getString("movementtype").trim());
			bs.setYdcxsj(json.getString("duration").trim());
			bs.setYdtjbz(json.getString("remarks").trim());
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "修改运动失败");
			 return result.toJSONString(); 
		}
		bs.setTemp4(CommonUtils.getCurDate());//修改时间
		int rowid = tnbsonService.updateByPrimaryKeySelective(bs);
		if(rowid > 0 ){
			result.put("_st", 1);//
			 result.put("_msg", "修改运动成功");
			 result.put("_data", JSON.toJSONString(bs));
			 return result.toJSONString(); 
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "修改运动失败");
			 return result.toJSONString(); 
		}
	}
	
	//获取运动
	@ResponseBody
	@ApiOperation(value = "获取指定日期运动值，｜  发布时间： 2016-08-13 13:21 ", httpMethod = "GET", response = String.class, notes = "获取指定日期运动值，｜  发布时间： 2016-08-13 13:21 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/GetMovement/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String GetMovement(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "date", value = "指定运动对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		//根据患 者ID， 指定日期， 021 查询出运动记录
		List<TnbTnbson> list = tnbsonService.findFeedList(Long.parseLong(hzid), "021", date);
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
	
	//添加症状symptom
	@ResponseBody
	@ApiOperation(value = "添加症状symptom，｜  发布时间： 2016-08-14 00:10 ", httpMethod = "POST", response = String.class, notes = "添加症状symptom，｜  发布时间： 2016-08-14 00:10")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/AddSymptom/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String AddSymptom(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "itemcode", value = "症状对应编号：023") @RequestParam(value="itemcode",required=true) String itemcode,
			@ApiParam(required = true, name = "jsondata", value = "症状数据，以JSON串形式传入运动的值 (只要是选中为1，未选为0)，格式：{\"ZZDNDY\":\"1|0\",\"ZZYN\":\"1|0\",\"ZZXS\":\"1|0\",\"ZZFL\":\"1|0\",\"ZZQT\":\"0,0,1,1,1,0,0,0,0,0,0,0\"}") @RequestParam(value="jsondata",required=true) String jsondata,
			@ApiParam(required = true, name = "date", value = "添加症状对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(itemcode)){
			 result.put("_st", 2);//
			 result.put("_msg", "itemcode无效");
			 return result.toJSONString();
		}

		if(StringUtils.isBlank(jsondata)){
			 result.put("_st", 3);//
			 result.put("_msg", "jsondata无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		//根据患者ID， 时间， itemcode查询出hzsfxx对应记录ID
		 //根据传入的日期转换成对应的long
		 Long _date = null;
		try {
			_date = CommonUtils.getTimeInMillisByDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long hzsfxxid = hzsfxxService.findByHzidDataItemCode(Long.parseLong(hzid), _date, itemcode);
		TnbTnbson bs = new TnbTnbson();
		bs.setFatherid(hzsfxxid);
		bs.setHzid(Long.parseLong(hzid));
		bs.setItemcode(itemcode);
		bs.setItemname(CommonUtils.getBloodSugarByItemCode(itemcode));
		JSONObject json = JSONObject.parseObject(jsondata);
		if(json != null){
			bs.setZzdndy(json.getString("ZZDNDY").trim());
			bs.setZzyn(json.getString("ZZYN").trim());
			bs.setZzxs(json.getString("ZZXS").trim());
			bs.setZzfl(json.getString("ZZFL").trim());
			bs.setZzqt(json.getString("ZZQT").trim());
			
			bs.setTemp4(date);//修改时间
			bs.setTemp5(date);//添加时间
			
			int rowid = tnbsonService.insert(bs);
			if(rowid > 0 ){
				result.put("_st", 1);//
				 result.put("_msg", "添加症状成功");
				 result.put("_data", JSON.toJSONString(bs));
				 return result.toJSONString(); 
			}else{
				result.put("_st", 4);//
				 result.put("_msg", "添加症状失败");
				 return result.toJSONString(); 
			}
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "添加症状失败");
			 return result.toJSONString(); 
		}
	}
	
	//修改症状symptom
	@ResponseBody
	@ApiOperation(value = "修改症状symptom，｜  发布时间： 2016-08-14 00:10 ", httpMethod = "POST", response = String.class, notes = "修改症状symptom，｜  发布时间： 2016-08-14 00:10")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/ModifySymptom/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String ModifySymptom(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "dataid", value = "症状对应ID ") @RequestParam(value="dataid",required=true) String dataid,
			@ApiParam(required = true, name = "jsondata", value = "症状数据，以JSON串形式传入运动的值 (只要是选中为1，未选为0)，格式：{\"ZZDNDY\":\"1|0\",\"ZZYN\":\"1|0\",\"ZZXS\":\"1|0\",\"ZZFL\":\"1|0\",\"ZZQT\":\"0,0,1,1,1,0,0,0,0,0,0,0\"}") @RequestParam(value="jsondata",required=true) String jsondata,
			@ApiParam(required = true, name = "date", value = "添加症状对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(dataid)){
			 result.put("_st", 2);//
			 result.put("_msg", "dataid无效");
			 return result.toJSONString();
		}

		if(StringUtils.isBlank(jsondata)){
			 result.put("_st", 3);//
			 result.put("_msg", "jsondata无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		
		TnbTnbson bs = tnbsonService.selectByPrimaryKey(Long.parseLong(dataid));
		JSONObject json = JSONObject.parseObject(jsondata);
		if(json != null){
			bs.setZzdndy(json.getString("ZZDNDY").trim());
			bs.setZzyn(json.getString("ZZYN").trim());
			bs.setZzxs(json.getString("ZZXS").trim());
			bs.setZzfl(json.getString("ZZFL").trim());
			bs.setZzqt(json.getString("ZZQT").trim());
			
			bs.setTemp4(CommonUtils.getCurDate());//修改时间
			
			int rowid = tnbsonService.updateByPrimaryKeySelective(bs);
			if(rowid > 0 ){
				result.put("_st", 1);//
				 result.put("_msg", "修改症状成功");
				 result.put("_data", JSON.toJSONString(bs));
				 return result.toJSONString(); 
			}else{
				result.put("_st", 4);//
				 result.put("_msg", "修改症状失败");
				 return result.toJSONString(); 
			}
		}else{
			result.put("_st", 4);//
			 result.put("_msg", "修改症状失败");
			 return result.toJSONString(); 
		}
	}
	
	//获取症状symptom
	@ResponseBody
	@ApiOperation(value = "获取症状symptom，｜  发布时间： 2016-08-14 00:10 ", httpMethod = "GET", response = String.class, notes = "获取症状symptom，｜  发布时间： 2016-08-14 00:10")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/GETSymptom/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String GetSymptom(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "date", value = "添加症状对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		
		//根据患者ID， 日期， 023，获取患者在某天的症状记录
		List<TnbTnbson> list = tnbsonService.findFeedList(Long.parseLong(hzid), "023", date);
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
	
	//添加睡眠、体温、血压 的接口
	@ResponseBody
	@ApiOperation(value = "添加睡眠、体温、血压基础随访数据，｜  发布时间： 2016-08-14 00:10 ", httpMethod = "POST", response = String.class, notes = "添加睡眠、体温、血压基础随访数据，｜  发布时间： 2016-08-14 00:10 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/AddBaseData/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String AddBaseData(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "itemcode", value = "添加项目编号：睡眠：014001；体温：014002；血压：014003") @RequestParam(value="itemcode",required=true) String itemcode,
			@ApiParam(required = true, name = "itemvalue", value = "添加项目的数据，睡眠和体温就是一个数值， 血压二个值以/分隔传递") @RequestParam(value="itemvalue",required=true) String itemvalue,
			@ApiParam(required = true, name = "date", value = "添加症状对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(itemcode)){
			 result.put("_st", 2);//
			 result.put("_msg", "itemcode无效");
			 return result.toJSONString();
		}

		if(StringUtils.isBlank(itemvalue)){
			 result.put("_st", 3);//
			 result.put("_msg", "itemvalue无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		//判断完参数的有效性后，根据患者ID，itemcode， 日期查询出hzsfxx表中结应的记录，然后进行update
		Hzsfxx hzsfxx = null;
		try {
			hzsfxx = hzsfxxService.findByHzidDateTemp3(Long.parseLong(hzid), CommonUtils.getTimeInMillisByDate(date), itemcode);
			if(hzsfxx != null){
				//设置itemvalue , 添加时间temp5, 修改时间temp4
				hzsfxx.setITEMVALUE(itemvalue);
				hzsfxx.setTEMP4(date);
				hzsfxx.setTEMP5(date);
				
				int rowid = hzsfxxService.updateByPrimaryKeySelective(hzsfxx);
				if(rowid > 0 ){
					result.put("_st", 1);//
					result.put("_msg", "添加成功");
					result.put("_data", JSON.toJSONString(hzsfxx));
					return result.toJSONString();
				}else{
					result.put("_st", 5);//
					result.put("_msg", "添加失败");
					return result.toJSONString();
				}
			}else{
				result.put("_st", 5);//
				result.put("_msg", "添加失败");
				return result.toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("_st", 5);//
			result.put("_msg", "添加失败");
			return result.toJSONString();
		}
	}
	
	//修改睡眠、体温、血压 的接口
	@ResponseBody
	@ApiOperation(value = "修改睡眠、体温、血压基础随访数据，｜  发布时间： 2016-08-14 14:10 ", httpMethod = "POST", response = String.class, notes = "修改睡眠、体温、血压基础随访数据，｜  发布时间： 2016-08-14 14:10 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/ModifyBaseData/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String ModifyBaseData(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "dataid", value = "修改项目的ID") @RequestParam(value="dataid",required=true) String dataid,
			@ApiParam(required = true, name = "itemvalue", value = "添加项目的数据，睡眠和体温就是一个数值， 血压二个值以/分隔传递") @RequestParam(value="itemvalue",required=true) String itemvalue){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(dataid)){
			 result.put("_st", 2);//
			 result.put("_msg", "dataid无效");
			 return result.toJSONString();
		}

		if(StringUtils.isBlank(itemvalue)){
			 result.put("_st", 3);//
			 result.put("_msg", "itemvalue无效");
			 return result.toJSONString();
		}
		//判断完参数的有效性后，根据患者ID，itemcode， 日期查询出hzsfxx表中结应的记录，然后进行update
		Hzsfxx hzsfxx = null;
		try {
			hzsfxx = hzsfxxService.selectByPrimaryKey(Long.parseLong(dataid));
			if(hzsfxx != null){
				//设置itemvalue , 添加时间temp5, 修改时间temp4
				hzsfxx.setITEMVALUE(itemvalue);
				hzsfxx.setTEMP4(CommonUtils.getCurDate());
				
				int rowid = hzsfxxService.updateByPrimaryKeySelective(hzsfxx);
				if(rowid > 0 ){
					result.put("_st", 1);//
					result.put("_msg", "修改成功成功");
					result.put("_data", JSON.toJSONString(hzsfxx));
					return result.toJSONString();
				}else{
					result.put("_st", 5);//
					result.put("_msg", "修改失败");
					return result.toJSONString();
				}
			}else{
				result.put("_st", 5);//
				result.put("_msg", "修改失败");
				return result.toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("_st", 5);//
			result.put("_msg", "修改失败");
			return result.toJSONString();
		}
	}
	
	//获取基础数据接口
	@ResponseBody
	@ApiOperation(value = "修改睡眠、体温、血压基础随访数据，｜  发布时间： 2016-08-14 14:10 ", httpMethod = "POST", response = String.class, notes = "修改睡眠、体温、血压基础随访数据，｜  发布时间： 2016-08-14 14:10 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/GETBaseData/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String GETBaseData(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "itemcode", value = "添加项目编号：睡眠：014001；体温：014002；血压：014003") @RequestParam(value="itemcode",required=true) String itemcode,
			@ApiParam(required = true, name = "date", value = "添加症状对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(itemcode)){
			 result.put("_st", 2);//
			 result.put("_msg", "itemcode无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		//根据患者ID，日期， itemcode查询出对应的值
		Hzsfxx hzsfxx = null;
		try {
			hzsfxx = hzsfxxService.findByHzidDateTemp3(Long.parseLong(hzid), CommonUtils.getTimeInMillisByDate(date), itemcode);
			if(hzsfxx == null){
				result.put("_st", 5);//
				result.put("_msg", "获取失败");
				return result.toJSONString();
			}else{
				result.put("_st", 1);//
				result.put("_msg", "获取成功");
				result.put("_data", JSON.toJSONString(hzsfxx));
				return result.toJSONString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("_st", 5);//
			result.put("_msg", "获取失败");
			return result.toJSONString();
		} 
	}
	
	/**添加生长数据， 身高、体重、头围、胸围、上臂围、肱三头肌皮褶厚度
	 * 添加时构建的json数据结构：
	 * 身高：{"height":xxx,"itemcode":"001001","img":"14.png"}
	 * 体重：{"weight":xxx,"itemcode":"001002","img":"15.png"}
	 * 头围:{"headsize":xxx,"itemcode":"001003","img":"16.png"}
	 * 胸围:{"bust":xxx,"itemcode":"001004","img":"17.png"}
	 * 上臂围:{"armsize":xxx,"itemcode":"001005","img":"18.png"}
	 * 肱三头肌皮褶厚度:{"triceps":xxx,"itemcode":"001006","img":"19.png"}
	 */
	@ResponseBody
	@ApiOperation(value = "添加生长数据， 身高、体重、头围、胸围、上臂围、肱三头肌皮褶厚度，格式为："
			+ "身高：{\"height\":xxx,\"itemcode\":\"001001\",\"img\":\"14.png\"}"+
	                            "体重：{\"weight\":xxx,\"itemcode\":\"001002\",\"img\":\"15.png\"}"+
	 "头围:{\"headsize\":xxx,\"itemcode\":\"001003\",\"img\":\"16.png\"}"+
	 " 胸围:{\"bust\":xxx,\"itemcode\":\"001004\",\"img\":\"17.png\"}"+
	 " 上臂围:{\"armsize\":xxx,\"itemcode\":\"001005\",\"img\":\"18.png\"}"+
	 " 肱三头肌皮褶厚度:{\"triceps\":xxx,\"itemcode\":\"001006\",\"img\":\"19.png\"}" + "｜  发布时间： 2016-08-14 15:10 ", httpMethod = "POST", response = String.class, notes = "添加生长数据， 身高、体重、头围、胸围、上臂围、肱三头肌皮褶厚度，｜  发布时间： 2016-08-14 15:10 ")
	@ApiResponse(code = 0, message = "返回JSON串，请查看响应内容")
	@RequestMapping(value="/AddGrowthData/{hzid}",produces = "application/json; charset=utf-8",method=RequestMethod.POST)
	public String AddGrowthData(@ApiParam(required = true, name = "hzid", value = "患者ID")  @PathVariable String hzid,
			@ApiParam(required = true, name = "token", value = "接口安全令牌,当下传入空值") @RequestParam(value="token",required=true) String token,
			@ApiParam(required = true, name = "jsondata", value = "生长数据添加的数据，是以JSON格式.") @RequestParam(value="jsondata",required=true) String jsondata,
			@ApiParam(required = true, name = "date", value = "添加症状对应的日期，格式：yyyy-MM-dd") @RequestParam(value="date",required=true) String date){
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(hzid)){
			 result.put("_st", 0);//
			 result.put("_msg", "患者ID无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(jsondata)){
			 result.put("_st", 2);//
			 result.put("_msg", "jsondata无效");
			 return result.toJSONString();
		}
		if(StringUtils.isBlank(date)){
			 result.put("_st", 4);//
			 result.put("_msg", "日期无效");
			 return result.toJSONString();
		}
		
		JSONArray array = JSONArray.parseArray(jsondata);
		
		try {
			hzsfxxService.addGrowthData(Long.parseLong(hzid), date, array);
			result.put("_st", 1);//
			result.put("_msg", "添加成功");
			return result.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			result.put("_st", 5);//
			result.put("_msg", "添加失败");
			return result.toJSONString();
		}
	}
	
	
}
