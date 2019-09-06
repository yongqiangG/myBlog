package com.johnny.myBlog.util;
/**
 * 字符串工具类
 * @author johnny
 *
 */
public class StringFormatUtil {
	
	/**
	 * 在字符串两边加上%,用于数据库模糊查询
	 */
	public static String stringFormat(String str) {
		if(StringFormatUtil.isNotEmpty(str)) {
			return "%"+str+"%";
		}
		return null;
	}
	/**
	 * 字符串非空判断
	 */
	public static Boolean isNotEmpty(String str) {
		if(str!=null && !"".equals(str)) {
			return true;
		}
		return false;
	}
	
}
