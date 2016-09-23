package com.med.brenda.util;

import java.io.UnsupportedEncodingException;

/**
 * 短信发送按扭
 * <p>MedicalApp</p>
 * <p>Title: SMS.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.util</p>
 * @author bgly
 * @date 2016年8月18日 下午5:36:16
 * @tag 
 * @encode
 */
public class SMS {

	public static int smsV(String mobile, String content, String _pre_time){
		String strSchSmsParam;
		try {
			String strTim = null;//HttpSend.paraTo16("2012-2-16 12:00:00"); //定时发送时间
			if(_pre_time == null || "".equals(_pre_time)){
				
			}else{
				strTim = HttpSend.paraTo16(_pre_time); //定时发送时间
			}
			
			strSchSmsParam = "reg=" + GlobalVariables.strReg + "&pwd=" + GlobalVariables.strPwd + "&sourceadd=" +
					GlobalVariables.strSourceAdd + "&tim=" + strTim + "&phone=" + mobile + "&content=" + HttpSend.paraTo16(content);

			//定时短信
			String strRes = HttpSend.postSend(GlobalVariables.strSchSmsUrl, strSchSmsParam);
			if(strRes.startsWith("result=0")){
				return 1;
			}else{
				return 0;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

			return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		int result = SMS.smsV("13552666934", "验证码：888888， 对应批次abcdef", "");
		System.out.println(result);
	}
}
