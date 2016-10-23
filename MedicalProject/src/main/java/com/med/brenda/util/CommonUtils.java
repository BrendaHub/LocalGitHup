package com.med.brenda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.med.brenda.model.Hzxx;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.service.ITnbTnbsonService;

public class CommonUtils {
	
	private static Logger logger = Logger.getLogger(CommonUtils.class);
	private static String[] WEEKS = {"日","一","二","三","四","五","六"};
	private static String[] BaseAH = {"a","b","c","d","e","f","g","k","l","m","n","p","q","r","s","t","u","v","w","x","y","x","h","8","7","3","4","5","2"};
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
	
	public static String getPiTuiCode(){
		java.util.Random random=new java.util.Random();// 定义随机类
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < 6; i ++){
			int result=random.nextInt(29);// 返回[0,10)集合中的整数，注意不包括10
			sb.append(BaseAH[result]);
		}
		return sb.toString();
	}
	
	public static String getVerifCode(){
		java.util.Random random=new java.util.Random();// 定义随机类
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < 4; i ++){
			int result=random.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
			sb.append(String.valueOf(result));
		}
		return sb.toString();
	}

	// 根据指定的日期得到与这相对应的毫秒数
	public static Long getTimeInMillisByDate(String _date) throws ParseException {
		// 此处会抛异常
		Date date = sdf.parse(_date);
		// 获取毫秒数
		long longDate = date.getTime();

		return longDate;
	}
	
	public static String getAge1(Date birthDay) throws Exception { 
        //获取当前系统时间
        Calendar cal = Calendar.getInstance(); 
        //如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthDay)) { 
            throw new IllegalArgumentException( 
                "The birthDay is before Now.It's unbelievable!"); 
        } 
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR); 
        int monthNow = cal.get(Calendar.MONTH); 
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
         
        //将日期设置为出生日期
        cal.setTime(birthDay); 
        //取出出生日期的年、月、日部分  
        int yearBirth = cal.get(Calendar.YEAR); 
        int monthBirth = cal.get(Calendar.MONTH); 
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 
        //当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth; 
        System.out.println("age  = "+ age);
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        int _month = 0;
        if (monthNow <= monthBirth) { 
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) { 
                if (dayOfMonthNow < dayOfMonthBirth) age--; 
            }else{ 
                age--; 
            }
            if(age == 0 )
            	_month = monthBirth - monthNow + 10;
            else
            	_month = monthBirth - monthNow;
            
        }else{
        	_month = monthNow - monthBirth;
        }
        logger.debug("age:"+age);
        logger.info("_month = " + _month);
        StringBuilder sb = new StringBuilder();
        sb.append(age);
        sb.append(".");
        sb.append(_month);
        return sb.toString(); 
    }
	//计算出日期
	public static String getAge(Date birthDay) throws Exception {
        Date date1=birthDay;
        Date date2=new Date();
        long time1=date1.getTime();
        long time2=date2.getTime();
        long time=(time2-time1)/1000;
        long year=time/(24*3600*365);
        long month=time%(24*3600*365)/(24*3600*30);
        StringBuilder sb = new StringBuilder();
        if(year>0){
        	sb.append(year);
        	sb.append(".");
        	sb.append(month);
            System.out.println(year+"年"+month+"月");
        }else{
        	sb.append("0.");
        	sb.append(month);
            System.out.println(month+"月");
        }
        return sb.toString();
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
	 * 获取指定日期上月的当前日期
	 * getStartDate
	 * @param date
	 * @return
	 * String
	 */
	public static String getPreDateStr(String date, int mon) {
		System.out.println("#############" + date);
		String resultString;
		try {
			if (StringUtils.isEmpty(date)) {
				return getEndDate();//当前日期
			}
			long _dateLong = 0l;
			try {
				_dateLong = getTimeInMillisByDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(_dateLong);
			cal.add(Calendar.MONTH, mon);
			int day = 0 ; 
			if(date.indexOf("-") != -1){
				day = Integer
					.parseInt(StringUtils.substring(date, StringUtils.lastIndexOf(date, "-") + 1, date.length()));
			}else{
				day = Integer
						.parseInt(StringUtils.substring(date, 6, date.length()));
			}
			System.out.println("day = "+ day);
			cal.set(Calendar.DATE, day);
			String lastMonthStart = sdf.format(cal.getTime());// 上月开始
			cal.clear();
			resultString = lastMonthStart;
		} catch (NumberFormatException e) {
			return getEndDate();
		}
		return resultString;
	}
	/**
	 * 获取指定日期上月的当前日期
	 * getStartDate
	 * @param date
	 * @return
	 * String
	 */
	public static String getPreDateStr(String date) {
		System.out.println("#############" + date);
		String resultString;
		try {
			if (StringUtils.isEmpty(date)) {
				return getEndDate();//当前日期
			}
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			int day = Integer
					.parseInt(StringUtils.substring(date, StringUtils.lastIndexOf(date, "-") + 1, date.length()));

			System.out.println("day = "+ day);
			cal.set(Calendar.DATE, day);
			String lastMonthStart = sdf.format(cal.getTime());// 上月开始
			cal.clear();
			resultString = lastMonthStart;
		} catch (NumberFormatException e) {
			return getEndDate();
		}
		return resultString;
	}
	//获取当前日期，返回字符串
	private static String getEndDate() {
		return sdf.format(new Date());// new Date()为获取当前系统时间
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
	
	//添加一个处理年龄的方法
	public static Hzxx parseAge(Hzxx h){
		String _age = h.getAGE();
		StringBuffer newAge = new StringBuffer();
		if(_age != null && !"".equals(_age.trim())){
			if(_age.indexOf(".") != -1){
				String[] age_arr = _age.split("\\.");//小数点前面是年龄，后面是月份
				if("0".equals(age_arr[0])){
					;
				}else{
					newAge.append(age_arr[0]);
					newAge.append("岁");
				}
				if("0".equals(age_arr[1])){
					;
				}else{
					newAge.append(age_arr[1]);
					newAge.append("个月");
				}
			}else{
				newAge.append(_age);
				newAge.append("岁");
			}
		}
		h.setAGE(newAge.toString());
		return h;
	}
	
	//根据患者生日得到日期对象
	public static Date parseByHzxxCSRQ(Hzxx hzxx){
		return new Date(hzxx.getCSRQ()!=null?hzxx.getCSRQ():0l);
	}
	
	public static Date parseByHzxxQZSJ(Hzxx hzxx){
		try{
			String qzsj = hzxx.getNFMQZSJ();
			return new Date(getTimeInMillisByDate(qzsj));
		}catch(Exception e){
			return new Date();
		}
	}
	//如果是血糖
	public static String findxuetangByHzId(ITnbTnbsonService tnbsonService, String hzid, String startdate, String enddate){
		JSONObject result = new JSONObject();
		try{
			List<TnbTnbson> list = tnbsonService.getTnbsonlistByDateRang(Long.parseLong(hzid), GlobalVariables.XUETANG_ITEMCODE, CommonUtils.getTimeInMillisByDate(startdate), CommonUtils.getTimeInMillisByDate(enddate));
			logger.info("list<<<<<< " + JSON.toJSONString(list));
			if(list != null && list.size() > 0){
				//这里需要按不同的日期处理结果集
				Long old_fatherid = new Long(0);
				String old_date = "";
				JSONArray arr = null;//new JSONArray();
				JSONObject tmpson = new JSONObject();
				JSONObject content = null;
				StringBuilder sb = null;
				for(int index = 0 ; index < list.size() ; index ++){
					TnbTnbson tnbson = (TnbTnbson)list.get(index);
					if(old_fatherid.longValue() == new Long(0).longValue()){//第一个
						logger.info(">>>>>>>>>>>>>>>>>>>>>");
						arr = new JSONArray();
						sb = new StringBuilder();
						content = new JSONObject();
					}
					logger.info(">>>>>>>>>>>>old_fatherid.longValue()>>>>>>>>>"+old_fatherid.longValue());
					logger.info(">>>>>>>tnbson.getFatherid().longValue()>>>>>>>>>>>>>>"+tnbson.getFatherid().longValue());
					logger.info(">>>>>>>false>>>>>>>>>>>>>>"+(old_fatherid.longValue() != tnbson.getFatherid().longValue()));
					
					if(old_fatherid.longValue() != new Long(0).longValue() && old_fatherid.longValue() != tnbson.getFatherid().longValue()){//表示换了一个新的数据
						//在不同时，需要做的事情 1 完成 Ｃontent 的值， 2 将ＪＳＯＮ对象添加到 ＪＳＯＮＡrray 中， 3 以日期为ＫＥＹ 添加到ＪＳＯＮＯbject中去。
						content.put("Content", sb.toString());
						arr.add(content);
						tmpson.put(old_date, arr);
						sb = null;
						sb = new StringBuilder();
						content = null;
						content = new JSONObject();
						arr = null;
						arr = new JSONArray();
					}else{
						if(old_fatherid.longValue() != new Long(0).longValue())
							sb.append("|");
					}
	
					sb.append(tnbson.getItemvalue());
					sb.append(";");
					sb.append(tnbson.getItemcode());
					sb.append(";");
					sb.append(tnbson.getId());
					sb.append(";");
					sb.append(tnbson.getTemp1());
	
					old_fatherid = tnbson.getFatherid();
					old_date = tnbson.getTemp5();
					
				}
				
				logger.info(");)))))))))))) = sb = "+ sb.toString());
				logger.info("content = " + JSON.toJSONString(content));
				logger.info("arrg = " + JSON.toJSONString(arr));
				
				if(sb != null && !"".equals(sb.toString())){
					content.put("Content", sb.toString());
					arr.add(content);
					tmpson.put(old_date, arr);
				}
				result.put("_st", 1);
				result.put("_msg", "获取成功");
				result.put("_data", tmpson);
				return result.toJSONString();
			}else {
				result.put("_st", 6);
				result.put("_msg", "获取失败");
				return result.toJSONString();
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("_st", 6);
			result.put("_msg", "获取失败,程序异常");
			return result.toJSONString();
		}
	}
	
	//添加一个处理年龄的方法
	public static List<Hzxx> parseAge(List<Hzxx> list){
		if(list != null && list.size() > 0){
			for(Iterator<Hzxx> it = list.iterator(); it.hasNext();){
				Hzxx h = (Hzxx)it.next();
				String _age = h.getAGE();
				StringBuffer newAge = new StringBuffer();
				if(_age != null && !"".equals(_age.trim())){
					if(_age.indexOf(".") != -1){
						String[] age_arr = _age.split("\\.");//小数点前面是年龄，后面是月份
						if("0".equals(age_arr[0])){
							;
						}else{
							newAge.append(age_arr[0]);
							newAge.append("岁");
						}
						if("0".equals(age_arr[1])){
							;
						}else{
							newAge.append(age_arr[1]);
							newAge.append("个月");
						}
					}else{
						newAge.append(_age);
						newAge.append("岁");
					}
				}
				h.setAGE(newAge.toString());
			}
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception{
//		List<Long> list = new CommonUtils().get_10Date("20180812");
//		System.out.println("===========================================");
//		list.stream().forEach(a ->{
//			System.out.println(a);
//			System.out.println(CommonUtils.transferLongToDate(a));
//		});
//		CommonUtils.parseLongDatetoJson(1470931200000l);
//		 try {
//			System.out.println( CommonUtils.parseLongDatetoJson(CommonUtils.getTimeInMillisByDate("20160812")).get("Mon"));
//			//System.out.println(CommonUtils.getTimeInMillisByDate("20160812"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Long birthday = getTimeInMillisByDate("20160907");
//		System.out.println(birthday);
//		Date date= new Date(birthday);
//		System.out.println(getAge(date));
//		
//		System.out.println(transferLongToDate(1470807735323l));
//		Long _l = CommonUtils.getTimeInMillisByDate("20170909");
//		Date d = new Date(_l);
////		System.out.println(getAge(d));
//		java.util.Random random=new java.util.Random();// 定义随机类
//		int result=random.nextInt(30);// 返回[0,10)集合中的整数，注意不包括10
////		return result+1;              // +1后，[0,10)集合变为[1,11)集合，满足要求
//		System.out.println(BaseAH.length);
//		System.out.println(result);
//		System.out.println(CommonUtils.getPiTuiCode());
//		System.out.println(CommonUtils.getVerifCode());
		
		System.out.println(CommonUtils.getPreDateStr("20160824",1));
	}
}
