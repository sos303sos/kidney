package com.platform.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Title: 配置文件读取
 * @Description:
 * @author sunhuabin
 * @date 2015-3-25 上午11:19:47
 * @version V1.0
 */
public class CommonConfig {
	private static final CommonConfig cc = new CommonConfig();
	private static final Log logger = LogFactory.getLog(CommonConfig.class);
	private Properties properties = null;

	public CommonConfig() {
		try {
			InputStream input = null;
			properties = new Properties();
			input = Thread.currentThread().getContextClassLoader().getResourceAsStream("commonConfig.properties");
			properties.load(input);
		} catch (IOException e) {
			logger.error("读取配置文件出错" + "commonConfig.properties");
		}
	}

	public static boolean getBoolean(String key) {
		return cc.booleanValue(true, key);
	}

	public static int getInt(String key) {
		return cc.intValue(true, key);
	}

	public static String getString(String key) {
		return cc.stringValue(true, key);
	}

	public static CommonConfig get() {
		return cc;
	}

	private String stringValue(boolean required, String key) {
		String value = null;
		try {
			value = this.properties.getProperty(key).trim();
		} catch (MissingResourceException e) {
		}
		if (StringUtils.isBlank(value)) {
			value = System.getProperty(key);
		}
		return ((value == null) ? "" : value.trim());
	}

	private boolean booleanValue(boolean required, String key) {
		return Boolean.valueOf(stringValue(required, key)).booleanValue();
	}

	private int intValue(boolean required, String key) {
		return Integer.parseInt(stringValue(required, key));
	}

}
