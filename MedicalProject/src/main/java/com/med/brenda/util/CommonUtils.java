package com.med.brenda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
	
	// 先把字符串转成Date类型
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 获取当天00:00:00时的毫秒数
	public static Long getTimeInMillisBy00_00_00() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	// 根据指定的日期得到与这相对应的毫秒数
	public static Long getTimeInMillisByDate(String _date) throws ParseException {
		// 此处会抛异常
		Date date = sdf.parse(_date);
		// 获取毫秒数
		long longDate = date.getTime();

		return longDate;
	}
	
	//获取当下的日期，返回yyyy-MM-dd
	public static String getCurDate(){
		return sdf.format(new Date());
	}
	
	//根据血糖的itemcode取到对应的中文名
	public static String getBloodSugarByItemCode(String itemCode){
		Map<String,String> bloodSugar  = new HashMap<>() ;
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
		return bloodSugar.get(itemCode);
	}
}
