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
	public static final int DEFAULT_PAGE_SIZE = 2;		//系统默认翻页每页多少条
	
	public static final String WEBSITE_URL = "http://api.doctor330.com/";
	
	public static String YINSHI_ITEMCODE = "021";//饮食
	public static String YUNDONG_ITEMCODE = "022";//运行
	public static String ZHENGZHUANG_ITEMCODE  = "023";//症状
	public static String XUETANG_ITEMCODE = "015";//血糖
	public static String YITAOSU_ITEMCODE = "016"; //胰岛素
	public static String COMMON_ITEMCODE = "014";// 一般清况，itemcode的值
	public static String TIWEN_TEMP5_SUB = "014002";//体温标识， temp3的值
	public static String XUEYA_TEMP5_SUB = "014003";//血压标识， temp3的值
	public static String SUIMIAN_TEMP5_SUB = "014001";//睡眠标识， temp3的值
	public static String SHENGZHANGSHUQU_ITEMCODE = "001";//生长数据， 身高，体重，头围，胸围，上臂围， 肱三厚度
	public static String HEIGHT_TMPE5_SUB = "001001";//身高
	public static String WEIGHT_TMPE5_SUB = "001002";//体重
	public static String TOUWEI_TMPE5_SUB = "001003";//头围
	public static String XONGWEI_TMPE5_SUB = "001004";//胸围
	public static String SHANGBIWEI_TMPE5_SUB = "001005";//上臂围
	public static String GONGSANHOUTU_TMPE5_SUB = "001006";//肱三厚度
	
	

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
