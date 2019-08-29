package com.johnny.myBlog.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {
	/**
	 * MD5加密
	 * @param args
	 */
	public static String md5(String str,String salt) {
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(CryptographyUtil.md5("123456", "johnny"));
	}

}
