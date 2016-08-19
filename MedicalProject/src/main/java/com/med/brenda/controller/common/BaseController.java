package com.med.brenda.controller.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Title: BaseAction
 * </p>
 * <p>
 * Description:front-web
 * </p>
 * <p>
 * Package:com.binggou.spider.front.action.common
 * </p>
 * 
 * @author chenhj(brenda)
 * @date 2016年7月28日 上午11:33:58
 * @tag
 */
public class BaseController {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	/**
	 * 处是带翻页的处理方法
	 */
	public void initPageFlag(HttpServletRequest request,Query query, int defaultPageSize){
		String pageNo = request.getParameter("pageNo");// 第几页
		if (pageNo == null || pageNo.equals("") || pageNo.equals("0")) {
			pageNo = "1";
		}
		String pageSizestr = request.getParameter("pageSize");// 条数
		if (pageSizestr == null || pageSizestr.equals("") || pageSizestr.equals("0")) {
			pageSizestr = String.valueOf(defaultPageSize);
		}
		Integer pageSize = defaultPageSize;
		if (pageSizestr != null && !pageSizestr.trim().equals("")) {
			pageSize = Integer.valueOf(pageSizestr);
		}
		int beginNo = 0;
		beginNo = Integer.valueOf(pageNo) > 1 ? pageSize * (Integer.valueOf(pageNo) - 1) : 0;
		query.setPageIndex(beginNo);
		query.setPageSize(pageSize);
	}

	/**
	 * 专门做 pageNo ,pageSize 的回工处理，返回处理后的pageNo 数
	 * 
	 * @return
	 */
	protected Integer processPageParam(String pageNo, String _pageSize, int defaultPageSize) {
		int beginNo = 0;
		// 处理参数格式
		if (StringUtils.isBlank(pageNo))
			pageNo = StringUtils.isBlank(pageNo) ? "1" : pageNo;// 第几页
		String pageSizestr = StringUtils.isBlank(_pageSize) ? defaultPageSize + "" : _pageSize;// request.getParameter("pageSize");//
																								// 条数
		Integer pageSize = defaultPageSize;
		beginNo = Integer.valueOf(pageNo) > 1 ? pageSize * (Integer.valueOf(pageNo) - 1) : 0;
		return beginNo;
	}

	/**
	 * Action 中获取cookie的value值
	 * 
	 * @param key
	 *            cookie的键
	 * @return cookie 的值
	 */
	protected String getCookieValue(String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		String value = null;
		for (Cookie c : cookies) {
			if (c.getName().equals(key)) {
				value = c.getValue();
			}
		}
		return value;
	}


	/**
	 * 格式化标签 from 11=房地产{#}15=军工{#}16=节能环保{#} to String [] = new
	 * String[]{房地产,军工,节能环保}
	 * 
	 * @param labels
	 * @return 注意返回值有可能为null;
	 */
	protected String[] formatLabels(String labels) {
		if (StringUtils.isEmpty(labels)) {
			return null;
		}
		String[] labelArray = labels.split("\\{#\\}");
		String[] labelList = new String[labelArray.length];
		for (int i = 0; i <= labelArray.length - 1; i++) {
			String[] result = labelArray[i].split("=");
			try {
				labelList[i] = result[1];
			} catch (Exception e) {
			}
		}
		return labelList;
	}

	/**
	 * 根据当前日期，格式化成以天为粒度的显示文字
	 * 
	 * @param date
	 * @return
	 */
	public String getTimeTip(Date date) {
		String tip;
		try {
			int day = daysBetween(date, new Date());
			if (day > 30) {
				tip = "一个月前浏览";
			} else if (day <= 0) {
				tip = "刚刚浏览";
			} else {
				tip = day + "天前浏览";
			}

		} catch (ParseException e) {
			tip = "最近几天浏览";
		}
		return tip;
	}

	/**
	 * 获取二个日期的间隔天数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	private int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 将以逗号分隔的数字字符串，转换成List的Integer集合
	 * 
	 * @param convertStrs
	 * @return
	 */
	public List<Integer> convertStrsToArray(String convertStrs) throws Exception {
		if (org.apache.commons.lang.StringUtils.isEmpty(convertStrs))
			return null;
		String[] ids = convertStrs.split(",");
		List<Integer> idArray = new ArrayList<Integer>();
		if (ids != null && ids.length > 0) {
			for (String s : ids) {
				idArray.add(Integer.parseInt(s));
			}
		}
		return idArray;
	}


	/**
	 * 获得用户的真实IP
	 *
	 * @return 用户的真实IP
	 */
	protected String getRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 隐藏手机号的第四位到第八的内容 ，将其转成*显示
	 * 
	 * @param mobile
	 * @return
	 */
	public String formatMobile(String mobile) {
		String s = mobile.substring(3, 7);
		return mobile.replace(s, "****");
	}

}
