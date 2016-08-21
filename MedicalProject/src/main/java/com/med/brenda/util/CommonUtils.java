package com.med.brenda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class CommonUtils {
	
	private static Logger logger = Logger.getLogger(CommonUtils.class);
	private static String[] WEEKS = {"日","一","二","三","四","五","六"};
	// 先把字符串转成Date类型
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
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
	
	public static String getSendSMS(String sfzcode, String downloadurl){
		StringBuilder sb = new StringBuilder("欢迎使用糖宝随访系统，您的用户名为：");
		sb.append(sfzcode);
		sb.append(",密码为：");
		sb.append(sfzcode.substring(0,6));
		sb.append(", 点击下载App用户端:");
		sb.append(downloadurl);
		return sb.toString();
	}
	
	/**
     * 把毫秒转化成日期
     * @param dateFormat(日期格式，例如：MM/ dd/yyyy HH:mm:ss)
     * @param millSec(毫秒数)
     * @return
     */
    public static String transferLongToDate(Long millSec){
		Date date= new Date(millSec);
        return sdf.format(date);
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
			bloodSugar.put("016001", "早(胰岛素)");
			bloodSugar.put("016002", "中(胰岛素)");
			bloodSugar.put("016003", "晚(胰岛素)");
			bloodSugar.put("016004", "睡前(胰岛素)");
			bloodSugar.put("021001", "早(饮食)");
			bloodSugar.put("021002", "中(饮食)");
			bloodSugar.put("021003", "晚(饮食)");
			bloodSugar.put("022", "运动");
			bloodSugar.put("023", "症状");
			bloodSugar.put("001001", "身高");
			bloodSugar.put("001002", "体重");
			bloodSugar.put("001003", "头围");
			bloodSugar.put("001004", "胸围");
			bloodSugar.put("001005", "上臂围");
			bloodSugar.put("001006", "肱三头肌皮褶厚度");
			bloodSugar.put("003", "用药");
			bloodSugar.put("018", "门诊检查/化验");
			bloodSugar.put("012", "费用");
		}
		return bloodSugar.get(itemCode);
	}
	
	//根据传入的日期，行到当前日期前后，各10天的日期
	public static List<Long> get_10Date(String _date){
		List<Long> dateList = new ArrayList<>();
		try {
			logger.debug("传入的日期为：" + _date);
			Date date = sdf.parse(_date);
			System.out.println(date);
			Calendar   calendar   =   new   GregorianCalendar(); 
			
			//开始造 出相应的数据, 先是前面10个
			for(int i = 10 ; i >= 1 ; i --){
				calendar.setTime(date); 
				logger.debug("i = " + i );
				calendar.add(calendar.DATE, -i);//把日期往后增加一天.整数往后推,负数往前移动 
				Date __date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
				logger.debug(sdf.format(__date));
				logger.debug(__date.getTime());
				dateList.add(__date.getTime());
			}
			dateList.add(date.getTime());
			//造出后面的10个日期记录
			for(int i = 1; i < 11 ; i ++){
				calendar.setTime(date); 
				logger.debug("i = " + i );
				calendar.add(calendar.DATE, i);//把日期往后增加一天.整数往后推,负数往前移动 
				Date __date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
				logger.debug(sdf.format(__date));
				logger.debug(__date.getTime());
				dateList.add(__date.getTime());
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateList;
	}
	
	/**
	 * date 转成 对应的json对角，有如下元素
	 * Day
	 * Week
	 * Mon
	 * @param date
	 * @return
	 */
	public static JSONObject parseLongDatetoJson(Long date){
			Date _date = new Date(date);
			System.out.println(sdf.format(_date));
			Calendar cal=Calendar.getInstance();
			cal.setTime(_date);
	        int year = cal.get(Calendar.YEAR);//获取年份
	        int month=cal.get(Calendar.MONTH) + 1;//获取月份
	        int day=cal.get(Calendar.DATE);//获取日 
			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;//星期
			if(week_index<0){
				week_index = 0;
			}
			JSONObject result = new JSONObject();
			result.put("Mon", year + "年"+month+"月");
			result.put("Day", day+"");
			result.put("Week", WEEKS[week_index]);
		return result;
	}
	
	public static void main(String[] args) {
//		List<Long> list = new CommonUtils().get_10Date("20180812");
//		System.out.println("===========================================");
//		list.stream().forEach(a ->{
//			System.out.println(a);
//			System.out.println(CommonUtils.transferLongToDate(a));
//		});
//		CommonUtils.parseLongDatetoJson(1470931200000l);
		 try {
			System.out.println( CommonUtils.parseLongDatetoJson(CommonUtils.getTimeInMillisByDate("20160812")).get("Mon"));
			//System.out.println(CommonUtils.getTimeInMillisByDate("20160812"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
