package com.johnny.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.johnny.myBlog.entity.Blogger;
import com.johnny.myBlog.service.BloggerService;
import com.johnny.myBlog.util.CryptographyUtil;

/**
 * 博主登录相关
 * @author johnny
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
	@Autowired
	private BloggerService bloggerService;
	
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest req) {
		String userName = blogger.getUserName();
		String password = blogger.getPassword();
		//密码加密处理
		String pw = CryptographyUtil.md5(password, "johnny");
		//Shiro账号验证
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,pw);
		try {
			subject.login(token);
			return "redirect:/admin/main.jsp";
		}catch(Exception e) {
			e.printStackTrace();
			//返回错误的账号密码
			req.setAttribute("blogger", blogger);
			//返回错误信息
			req.setAttribute("errorInfo", "账号或密码错误!");
		}
		return "login";
	}
	
	/**
	 * 关于博主
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe() {
		ModelAndView mav = new ModelAndView();
		Blogger blogger = bloggerService.getBlogger();
		mav.addObject("blogger",blogger);
		mav.addObject("mainPage","foreground/blogger/bloggerInfo.jsp");
		mav.addObject("pageTitle","关于博主");
		mav.setViewName("index");
		return mav;
		
	}
	

}
