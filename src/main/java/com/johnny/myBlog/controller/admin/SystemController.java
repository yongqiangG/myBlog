package com.johnny.myBlog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.entity.BlogType;
import com.johnny.myBlog.entity.Blogger;
import com.johnny.myBlog.entity.Link;
import com.johnny.myBlog.service.BlogService;
import com.johnny.myBlog.service.BlogTypeService;
import com.johnny.myBlog.service.BloggerService;
import com.johnny.myBlog.service.LinkService;
import com.johnny.myBlog.util.CommonParam;
import com.johnny.myBlog.util.CryptographyUtil;
import com.johnny.myBlog.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/system")
public class SystemController {
	@Autowired
	private BlogTypeService service;
	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private LinkService linkService;
	
	/**
	 * 刷新系统缓存
	 */
	@RequestMapping("/refreshSystemCache")
	public String refreshSystemCache(HttpServletRequest req,HttpServletResponse res) throws Exception {
		ServletContext application = RequestContextUtils.findWebApplicationContext(req).getServletContext();
		List<BlogType> list = service.getBlogTypeWithoutParam();
		List<Blog> blogCountList = blogService.getBlogWithoutParam();
		List<Link> linkList = linkService.getLink(null);
		application.setAttribute(CommonParam.Blog_Type_List, list);
		application.setAttribute(CommonParam.BLOG_COUNT_LIST, blogCountList);
		application.setAttribute(CommonParam.LINK_LIST, linkList);
		JSONObject result = new JSONObject();
		result.put("success", Boolean.valueOf(true));
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 修改密码
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String password,String userName,HttpServletResponse res) throws Exception {
		Blogger blogger = bloggerService.getBloggerByUserName(userName);
		String passwordByMD5 = CryptographyUtil.md5(password, "johnny");
		blogger.setPassword(passwordByMD5);
		bloggerService.updateBlogger(blogger);
		JSONObject result = new JSONObject();
		result.put("success",Boolean.valueOf(true));
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	public String logout() {
		//调用Shiro的logout()可以清除session中的博主对象信息
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
