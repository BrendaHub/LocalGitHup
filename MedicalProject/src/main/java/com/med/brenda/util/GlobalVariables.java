package com.med.brenda.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 站点全局常量或变量
 * <p>MedicalApp</p>
 * <p>Title: GlobalVariables.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.util</p>
 * @author bgly
 * @date 2016年8月12日 下午2:30:18
 * @tag 
 * @encode
 */
public class GlobalVariables {
	//定时发短信URL
	public static String strSchSmsUrl = "http://www.stongnet.com/sdkhttp/sendschsms.aspx";
	public static String strReg = "101100-WEB-HUAX-082478";   //注册号（由华兴软通提供）
	public static String strPwd = "YTIAKBRB";                 //密码（由华兴软通提供）
	public static String strSourceAdd = "";                   //子通道号，可为空（预留参数一般为空）
	public static final int DEFAULT_PAGE_SIZE = 20;		//系统默认翻页每页多少条

	static Map<String,String> bloodSugar  = new HashMap<>() ;
	{
		bloodSugar.put("015002001", "早餐前");
		bloodSugar.put("015002002", "早餐后");
		bloodSugar.put("015003001", "午餐前");
		bloodSugar.put("015003002", "午餐后");
		bloodSugar.put("015004001", "晚餐前");
		bloodSugar.put("015004002", "晚餐后");
		bloodSugar.put("015005", "睡前");
		bloodSugar.put("015006001", "随机");
		bloodSugar.put("015006002", "随机");
		bloodSugar.put("015006003", "随机");
		bloodSugar.put("015006004", "随机");
		bloodSugar.put("015006005", "随机");
		bloodSugar.put("015001", "凌晨");
	}
}
