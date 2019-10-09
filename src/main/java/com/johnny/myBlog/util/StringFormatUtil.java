package com.johnny.myBlog.util;

import java.util.ArrayList;
import java.util.List;

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
	/**
	 * 字符串空判断
	 */
	public static Boolean isEmpty(String str) {
		if(str==null || "".equals(str)) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 */
	public static List<String> filterSpace(List<String> list){
		List<String> resultList = new ArrayList<String>();
		for(String l:list) {
			if(isNotEmpty(l)) {
				resultList.add(l);
			}
		}
		return resultList;
	}
}
