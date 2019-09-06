package com.johnny.myBlog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * Response的工具类
 * @author johnny
 *
 */
public class ResponseUtil {
	public static void writeRes(HttpServletResponse res,Object o) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(o.toString());
		out.flush();
		out.close();
	}
}
