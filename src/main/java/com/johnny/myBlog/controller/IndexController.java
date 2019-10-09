package com.johnny.myBlog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.entity.PageBean;
import com.johnny.myBlog.service.BlogService;
import com.johnny.myBlog.util.PageUtil;
import com.johnny.myBlog.util.StringFormatUtil;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private BlogService blogService; 
	
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,
							@RequestParam(value="type_id",required=false)String type_id,
							@RequestParam(value="releaseDateStr",required=false)String releaseDateStr,
							HttpServletResponse res,
							HttpServletRequest req
								) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title","个人博客系统");
		if(StringFormatUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		map.put("type_id",type_id);
		map.put("releaseDateStr",releaseDateStr);
		List<Blog> list = blogService.getBlogByParam(map);
		//翻页
		StringBuffer param = new StringBuffer();
		if(StringFormatUtil.isNotEmpty(type_id)) {
			param.append("type_id="+type_id+"&");
		}
		if(StringFormatUtil.isNotEmpty(releaseDateStr)) {
			param.append("releaseDateStr="+releaseDateStr+"&");
		}
		mav.addObject("mainPage","foreground/blog/list.jsp");
		String pageCode = PageUtil.getPagination(req.getContextPath()+"/index.html", blogService.getBlogCount(map), 10, param, Integer.parseInt(page));
		mav.addObject("pageCode",pageCode);
		mav.addObject("blogList",list);
		return mav;
	}
}
