package com.med.brenda.controller.hzxx;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.med.brenda.util.CommonUtils;
import com.med.brenda.util.GlobalVariables;
import com.med.brenda.util.HttpSend;
import com.med.brenda.util.MD5;

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
