package com.med.brenda.util;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: ReloadableFrontConfig</p>
 * <p>Description:front-web</p>
 * <p>Package:com.binggou.spider.front.action.common</p>
 * @author chenhj(brenda)
 * @date 2016年7月28日 上午11:34:38
 * @tag
 */
public class InitPropertiesConfig {
	private static Properties pro;
	private static InputStream in;

	static {
		try {
			pro = new Properties();
			in = InitPropertiesConfig.class.getResourceAsStream("/config/frontConfig.properties");
			pro.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("reloadableConfig  properties file not find ");
		} catch (IOException e) {
			System.out.println("reloadableConfig initial faile");
		} catch (Exception e) {
			System.out.println("property file not exits");
		}

	}

	

	public static String getStringProperty(String key) {
		if (pro == null) {
			return null;
		}
		String value = pro.getProperty(key);
		return value;
	}

	public static String getStringProperty(String key, String defaultValue) {
		if (pro == null) {
			return defaultValue;
		}
		String value = pro.getProperty(key);
		if(StringUtils.isEmpty(value)) return defaultValue;
		return value;
	}
	public static Integer getIntProperty(String key){
		if (pro == null) {
			return null;
		}
		String value = pro.getProperty(key);
		if(StringUtils.isNumeric(value)){
			return Integer.parseInt(value);
		}
		return null;
	}
	public static Integer getIntProperty(String key, int defaultValue){
		if (pro == null) {
			return defaultValue;
		}
		String value = pro.getProperty(key);
		if(StringUtils.isNumeric(value)){
			return Integer.parseInt(value);
		}
		return defaultValue;
	}
	public static void main(String[] args) {
		
	}
}
