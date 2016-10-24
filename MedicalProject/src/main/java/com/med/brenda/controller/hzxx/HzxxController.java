package com.med.brenda.controller.hzxx;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.controller.common.BaseController;
import com.med.brenda.controller.common.Query;
import com.med.brenda.model.AppDlLog;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.SysDlDayLog;
import com.med.brenda.model.SysDlWeekLog;
import com.med.brenda.service.IAppDlLogService;
import com.med.brenda.service.IHzxxService;
import com.med.brenda.service.ISysDlDayLogService;
import com.med.brenda.service.ISysDlWeekLogService;
import com.med.brenda.service.ITnbTnbsonService;
import com.med.brenda.util.CommonUtils;
import com.med.brenda.util.GlobalVariables;
import com.med.brenda.util.HttpSend;
import com.med.brenda.util.MD5;
import com.med.brenda.vo.xuetangVo;

/**
 * 患者信息业务业
 * <p>MedicalApp</p>
 * <p>Title: HzxxController.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.controller.hzxx</p>
 * @author bgly
 * @date 2016年8月19日 下午1:55:06
 * @tag 
 * @encode
 */
@Controller 
@RequestMapping(value="/HZXX")
public class HzxxController extends BaseController {
	
	private Logger logger = Logger.getLogger(HzxxController.class);
	
	@Autowired
	private IHzxxService hzxxService;
	@Autowired
	private ISysDlDayLogService dlDayLogService;
	@Autowired
	private ISysDlWeekLogService dlWeekLogService;
	@Autowired
	private IAppDlLogService appService;
	@Autowired
	private ITnbTnbsonService tnbsonService;
	
	/**
	 * 血糖数据综合分析， 有中值，平均值，和
	 */
	@RequestMapping(value="/sysfx",method=RequestMethod.GET)
	public ModelAndView toSysFx(HttpServletRequest request){
		return new ModelAndView("hzxx/line_sysfx");
	}
	/**
	 * 血糖数据综合分析， meen
	 */
	@RequestMapping(value="/sysavg",method=RequestMethod.GET)
	public ModelAndView sysavg(HttpServletRequest request){
		return new ModelAndView("hzxx/line_avg");
	}
	/**
	 * 血糖数据综合分析， 中值
	 */
	@RequestMapping(value="/sysmed",method=RequestMethod.GET)
	public ModelAndView sysmed(HttpServletRequest request){
		return new ModelAndView("hzxx/line_med");
	}
	
	/**
	 * 去图表分析页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tofenxi1/{defaultpagesize}", method=RequestMethod.GET)
	public ModelAndView tofenxipage(HttpServletRequest request, @PathVariable String defaultpagesize){
		Query query = new Query();
		//初始化翻页参数
		initPageFlag(request, query, Integer.parseInt(defaultpagesize));
		System.out.println("pageIndex = " + query.getPageIndex());
		System.out.println("pageSize = " + query.getPageSize());
		int count = 50;
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
        List<SysDlDayLog> list = dlDayLogService.selectList(query.getPageIndex(), query.getPageSize());
        
        List<Map<String, Object>> result1 = new ArrayList<>();
        if(list != null && list.size() > 0){
        	List<SysDlDayLog> tmp = null;
        	Map<String, Object> result = null;
        	String old_sfzh = "";
        	String old_hzname = "";
        	for(int i= 0 ; i < list.size() ; i ++){
        		SysDlDayLog dv = list.get(i);
        		if("".equals(old_sfzh )){//第一次进来
        			tmp = new ArrayList<>();
        			result = new HashMap<>();
        		}
        		if(!old_sfzh.equals(dv.getSfzh()) && !"".equals(old_sfzh )){
        			result.put("hzname", old_hzname);
        			result.put("data", tmp);
        			result1.add(result);
        			tmp = null;
        			result = null;
        			result = new HashMap<>();
        			tmp = new ArrayList<>();
        		}
        		tmp.add(dv);
        		old_sfzh = dv.getSfzh();
        		old_hzname = dv.getHzname();
        	}
        }
        Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageIndex());
        resultMap.put("pageTotal", count);
        resultMap.put("result", result1);
        return new ModelAndView("hzxx/line",resultMap);
	}
	/**
	 * 去图表分析页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tofenxi/{defaultpagesize}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> tofenxi(HttpServletRequest request, @PathVariable String defaultpagesize){
		Query query = new Query();
		//初始化翻页参数
		initPageFlag(request, query, Integer.parseInt(defaultpagesize));
		System.out.println("pageIndex = " + query.getPageIndex());
		System.out.println("pageSize = " + query.getPageSize());
		int count = 50;
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
        List<SysDlDayLog> list = dlDayLogService.selectList(query.getPageIndex(), query.getPageSize());
        
        List<Map<String, Object>> result1 = new ArrayList<>();
        if(list != null && list.size() > 0){
        	List<SysDlDayLog> tmp = null;
        	Map<String, Object> result = null;
        	String old_sfzh = "";
        	String old_hzname = "";
        	for(int i= 0 ; i < list.size() ; i ++){
        		SysDlDayLog dv = list.get(i);
        		if("".equals(old_sfzh )){//第一次进来
        			tmp = new ArrayList<>();
        			result = new HashMap<>();
        		}
        		if(!old_sfzh.equals(dv.getSfzh()) && !"".equals(old_sfzh )){
        			result.put("hzname", old_hzname);
        			result.put("data", tmp);
        			result1.add(result);
        			tmp = null;
        			result = null;
        			result = new HashMap<>();
        			tmp = new ArrayList<>();
        		}
        		tmp.add(dv);
        		old_sfzh = dv.getSfzh();
        		old_hzname = dv.getHzname();
        	}
        	if(tmp != null && result != null){
        		result.put("hzname", old_hzname);
    			result.put("data", tmp);
    			result1.add(result);
    			tmp = null;
    			result = null;
        	}
        }
        Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageIndex());
        resultMap.put("pageTotal", count);
        resultMap.put("pageNo", query.getPageNo());
        resultMap.put("result", result1);
		return resultMap;
	}
	
	/**
	 * 去图表分析页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toweekfenxi1/{defaultpagesize}", method=RequestMethod.GET)
	public ModelAndView toweekfenxi1(HttpServletRequest request, @PathVariable String defaultpagesize){
		Query query = new Query();
		//初始化翻页参数
		initPageFlag(request, query, Integer.parseInt(defaultpagesize));
		System.out.println("pageIndex = " + query.getPageIndex());
		System.out.println("pageSize = " + query.getPageSize());
		int count = 50;
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
        List<SysDlWeekLog> list = dlWeekLogService.selectList(query.getPageIndex(), query.getPageSize());
        
        List<Map<String, Object>> result1 = new ArrayList<>();
        if(list != null && list.size() > 0){
        	List<SysDlWeekLog> tmp = null;
        	Map<String, Object> result = null;
        	String old_sfzh = "";
        	String old_hzname = "";
        	for(int i= 0 ; i < list.size() ; i ++){
        		SysDlWeekLog dv = list.get(i);
        		if("".equals(old_sfzh )){//第一次进来
        			tmp = new ArrayList<>();
        			result = new HashMap<>();
        		}
        		if(!old_sfzh.equals(dv.getSfzh()) && !"".equals(old_sfzh )){
        			result.put("hzname", old_hzname);
        			result.put("data", tmp);
        			result1.add(result);
        			tmp = null;
        			result = null;
        			result = new HashMap<>();
        			tmp = new ArrayList<>();
        		}
        		tmp.add(dv);
        		old_sfzh = dv.getSfzh();
        		old_hzname = dv.getHzname();
        	}
        }
        Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageIndex());
        resultMap.put("pageTotal", count);
        resultMap.put("result", result1);
        return new ModelAndView("hzxx/line_week",resultMap);
	}
	
	/**
	 * 去图表分析页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toWeekfenxi/{defaultpagesize}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> toWeekfenxi(HttpServletRequest request, @PathVariable String defaultpagesize){
		Query query = new Query();
		//初始化翻页参数
		initPageFlag(request, query, Integer.parseInt(defaultpagesize));
		System.out.println("pageIndex = " + query.getPageIndex());
		System.out.println("pageSize = " + query.getPageSize());
		int count = 50;
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
        List<SysDlWeekLog> list = dlWeekLogService.selectList(query.getPageIndex(), query.getPageSize());
        
        List<Map<String, Object>> result1 = new ArrayList<>();
        if(list != null && list.size() > 0){
        	List<SysDlWeekLog> tmp = null;
        	Map<String, Object> result = null;
        	String old_sfzh = "";
        	String old_hzname = "";
        	for(int i= 0 ; i < list.size() ; i ++){
        		SysDlWeekLog dv = list.get(i);
        		if("".equals(old_sfzh )){//第一次进来
        			tmp = new ArrayList<>();
        			result = new HashMap<>();
        		}
        		if(!old_sfzh.equals(dv.getSfzh()) && !"".equals(old_sfzh )){
        			result.put("hzname", old_hzname);
        			result.put("data", tmp);
        			result1.add(result);
        			tmp = null;
        			result = null;
        			result = new HashMap<>();
        			tmp = new ArrayList<>();
        		}
        		tmp.add(dv);
        		old_sfzh = dv.getSfzh();
        		old_hzname = dv.getHzname();
        	}
        	if(tmp != null && result != null){
        		result.put("hzname", old_hzname);
    			result.put("data", tmp);
    			result1.add(result);
    			tmp = null;
    			result = null;
        	}
        }
        Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageIndex());
        resultMap.put("pageTotal", count);
        resultMap.put("pageNo", query.getPageNo());
        resultMap.put("result", result1);
		return resultMap;
	}
	
	@RequestMapping(value="/toDetailHzxx/{hzId}")
	public ModelAndView toDetailByHzxx(HttpServletRequest request , @PathVariable String hzId){
		
		Hzxx hzxx = hzxxService.findHzByHzID(Long.parseLong(hzId));
		hzxx = CommonUtils.parseAge(hzxx);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("hzId", hzId);
		resultMap.put("result", hzxx);
		resultMap.put("hzcsrq", CommonUtils.parseByHzxxCSRQ(hzxx));
		resultMap.put("hzqcsj", CommonUtils.parseByHzxxQZSJ(hzxx));
		return new ModelAndView("hzxx/detail",resultMap);
	}
	
	//获取患者血糖数据
	@RequestMapping(value="/loadhzxuetangdata/{hzid}")
	@ResponseBody
	public String loadhzxuetangdata(HttpServletRequest request , @PathVariable String hzid){
		String begdate = StringUtils.isBlank(request.getParameter("begindate"))?"":request.getParameter("begindate");
		String enddate = StringUtils.isBlank(request.getParameter("enddate"))?"":request.getParameter("enddate");
		
		logger.debug("hzid = " + hzid);
		logger.debug("begdate = " + begdate);
		logger.debug("enddate = " + enddate);
		if(!"".equals(begdate)){
			begdate = begdate.replace("-", "");
		}
		if(!"".equals(enddate)){
			enddate = enddate.replace("-", "");
		}
		
		JSONObject jsono = CommonUtils.findxuetangByHzId(tnbsonService, hzid, begdate, enddate);
		System.out.println("jsonString  = " + jsono.toJSONString());
		//在java里面解析这个json串
		JSONArray resultarr = new JSONArray();
		if(jsono != null){
			String flag = jsono.getString("_st");
			if("1".equals(flag)){
				////////////////////////
				JSONObject xtDataList = jsono.getJSONObject("_data");
				Set keyset = xtDataList.keySet();
				for(Iterator it = keyset.iterator(); it.hasNext();){//在这里循环查询出来的所有血糖值
					String key = (String)it.next();
					JSONObject returnvalue = new JSONObject();//返回的对象值
					JSONArray dayXt = xtDataList.getJSONArray(key);
					JSONObject curday_xt  = (JSONObject)dayXt.get(0);//得到了当前的血糖值， 这个血糖是以|线分隔的
					//将每天的数据解析出来，首以以|线分隔出数组
					String values = curday_xt.getString("Content");
					Map<String, String> savetmpxt = new HashMap<String, String>();
					xuetangVo xtvo = new xuetangVo();
					xtvo.setTime(key);//时间
					float xutotal = 0;//累加当天所有的血糖值
					int xutindex = 0 ; //记录当天记录血糖的个数
					if(values != null){
						String[] vals = values.split("\\|");
						for(String v : vals){
							if(v != null && !"".equals(v)){
								String[] sub_vals = v.split(";");
								String itemcode = sub_vals[1];
								String itemvalue = sub_vals[0];
								String itemTime = sub_vals[3];
								xutotal += Float.parseFloat(itemvalue);
								xutindex ++;
								//在这里处理数据的显示颜色
								float xtvalue = 0;
								try{
									xtvalue = Float.parseFloat(itemvalue);
									if(xtvalue < 4.0){
										itemvalue = "<font color='yello'>"+itemvalue+"</font>";
									}else if(xtvalue > 4.0 && xtvalue < 9.0){
										itemvalue = "<font color='green'>"+itemvalue+"</font>";
									}else{
										itemvalue = "<font color='red'>"+itemvalue+"</font>";
									}
								}catch(Exception e){
									
								}
								savetmpxt.put(itemcode, itemvalue);
							}
						}
					}
					xtvo.setAvg(xutotal/xutindex);
					//反复的遍历上面的map用来填充不时的血糖值
					/**
					015001   凌晨
					015002001	早餐前
					015002002	早餐后
					015003001	午餐前
					015003002	午餐后
					015004001	晚餐前
					015004001	晚餐后
					015005		睡前
					015006001	随机
					015006002	随机
					015006003	随机
					015006004	随机
					015006005	随机
				*/
					Set xtkeyset = savetmpxt.keySet();
					for(Iterator it0 = xtkeyset.iterator(); it0.hasNext();){
						String itemcode = (String)it0.next();
						if("015001".equals(itemcode)){
							xtvo.setT_lingcheng(savetmpxt.get(itemcode));
						}
						if("015002001".equals(itemcode)){
							xtvo.setT_zzq(savetmpxt.get(itemcode));
						}
						if("015002002".equals(itemcode)){
							xtvo.setT_zzh(savetmpxt.get(itemcode));
						}
						if("015003001".equals(itemcode)){
							xtvo.setT_czq(savetmpxt.get(itemcode));
						}
						if("015003002".equals(itemcode)){
							xtvo.setT_czh(savetmpxt.get(itemcode));
						}
						if("015004001".equals(itemcode)){
							xtvo.setT_wzq(savetmpxt.get(itemcode));
						}
						if("015004002".equals(itemcode)){
							xtvo.setT_wzh(savetmpxt.get(itemcode));
						}
						if("015005".equals(itemcode)){
							xtvo.setT_sq(savetmpxt.get(itemcode));
						}
						if("015006001".equals(itemcode)){
							xtvo.setT_rand1(savetmpxt.get(itemcode));
						}
						if("015006002".equals(itemcode)){
							xtvo.setT_rand2(savetmpxt.get(itemcode));
						}
						if("015006003".equals(itemcode)){
							xtvo.setT_rand3(savetmpxt.get(itemcode));
						}
						if("015006004".equals(itemcode)){
							xtvo.setT_rand4(savetmpxt.get(itemcode));
						}
						if("015006005".equals(itemcode)){
							xtvo.setT_rand5(savetmpxt.get(itemcode));
						}
					}
					String json = JSONObject.toJSONString(xtvo);
					resultarr.add(json);
				}
			}
		}
		return resultarr.toJSONString();
	}
	
	
	
	@RequestMapping(value="/list")
	public ModelAndView hzlist(HttpServletRequest request , String pageNo, String keyworld){
		
		System.out.println(">>>>>>>>>>>>>>>"+pageNo);
		System.out.println(">>>>>>>>>>keyworld>>>>>"+keyworld);
		//创建出一个查询对象
		Query query = new Query();
		if(StringUtils.isBlank(keyworld)){
			;
		}else{
			query.setKeywords(keyworld);
		}
		int count = hzxxService.findListCount(query);
		//初始化翻页参数
		initPageFlag(request, query, GlobalVariables.DEFAULT_PAGE_SIZE);
		int mode = count % query.getPageSize();
        int pageCount = count / query.getPageSize();
        if (mode > 0) {
            pageCount = pageCount + 1;
        }
		List<Hzxx> list = hzxxService.findList(query);
		list = CommonUtils.parseAge(list);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageCount", pageCount);
        resultMap.put("pageSize", query.getPageSize());
        resultMap.put("pageIndex", query.getPageNo());
        resultMap.put("pageTotal", count);
        resultMap.put("pageNo", query.getPageNo());
        resultMap.put("result", list);
        resultMap.put("keyworld", query.getKeywords());
		return new ModelAndView("hzxx/index",resultMap);
	}
	
	
	
	//去添加用户
	@RequestMapping(value="/toAddUser", method=RequestMethod.GET)
	public ModelAndView toAddUser(HttpServletRequest request){
		return new ModelAndView("hzxx/add");
	}
	//去添加用户
	@RequestMapping(value="/AddHZ", method=RequestMethod.POST)
	public ModelAndView AddHZ(HttpServletRequest request, Hzxx hzxx){
		System.out.println(hzxx.getHZNAME());
		System.out.println(hzxx.getPHONE());
		System.out.println(hzxx.getSFZCODE());
		System.out.println(hzxx.getTEMP1());
		System.out.println(hzxx.getTEMP3());
		Map<String, Object> resultMap = new HashMap<>();
	    //执行添加患者操作， 先得到初始密码
		String init_pwd = "";
		if(!StringUtils.isEmpty(hzxx.getSFZCODE()) && hzxx.getSFZCODE().length() > 6){
			init_pwd = hzxx.getSFZCODE().substring(0,6);
		}
		if(StringUtils.isNotBlank(init_pwd)){
			init_pwd = MD5.GetMD5Code(init_pwd);
		}
		hzxx.setPASSWORD(init_pwd);
		hzxx.setDLH(hzxx.getSFZCODE());
		
		int rowid = hzxxService.insert(hzxx);
		
		if(rowid > 0 ){
			//添加成功后，向新的患者下发短信
			String replaseStr  = hzxx.getSFZCODE().length()>17?hzxx.getSFZCODE().substring(9, 15):"";
			String _sfzCode = hzxx.getSFZCODE().replace(replaseStr,"*******");
			String dlUrl = GlobalVariables.WEBSITE_URL+"api/doctor/dlapp/"+hzxx.getPHONE()+"/"+_sfzCode;
			String smsContent = CommonUtils.getSendSMS(_sfzCode, dlUrl);
			String strTim = null;//HttpSend.paraTo16("2012-2-16 12:00:00"); //定时发送时间
			String strSchSmsParam = "";
			try {
				strSchSmsParam = "reg=" + GlobalVariables.strReg + "&pwd=" + GlobalVariables.strPwd + "&sourceadd=" +
						GlobalVariables.strSourceAdd + "&tim=" + strTim + "&phone=" + hzxx.getPHONE() + "&content=" + HttpSend.paraTo16(smsContent);
				logger.info("短信发送："+strSchSmsParam );
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//定时短信
			String strRes = HttpSend.postSend(GlobalVariables.strSchSmsUrl, strSchSmsParam);
			if(strRes.startsWith("result=0")){
				;
			}else{
				//添加短信发送失败的日志
				AppDlLog appl = new AppDlLog();
				appl.setMobile(hzxx.getPHONE());
				appl.setSfzcode(hzxx.getSFZCODE()+"短信下发失败");
				appl.setCreatetime(System.currentTimeMillis());
				
				rowid = appService.insert(appl);
			}
			return new ModelAndView("redirect:/HZXX/list?ff="+Math.random());
		}else{
			resultMap.put("message", "新增患者失败！");
			return new ModelAndView("/HZXX/toAddUser", resultMap);
		}
	}
	//去修改用户
	@RequestMapping(value="/toEditUser", method=RequestMethod.GET)
	public ModelAndView toEditUser(HttpServletRequest request){
		return new ModelAndView("hzxx/edit");
	}
}
