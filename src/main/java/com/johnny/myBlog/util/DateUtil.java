package com.johnny.myBlog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author johnny
 *
 */
public class DateUtil {
	/**
	 * 返回当前日期的字符串,精确到秒
	 */
	public static String currentDateStr() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
	/**
	 * 
	 */
	public static String formatDate(Date date,String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(date!=null) {
			result = sdf.format(date);
		}
		return result;
	}
}
