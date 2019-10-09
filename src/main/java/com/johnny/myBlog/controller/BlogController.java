package com.johnny.myBlog.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.lucene.BlogIndex;
import com.johnny.myBlog.service.BlogService;
import com.johnny.myBlog.service.CommentService;
import com.johnny.myBlog.util.StringFormatUtil;

@Controller
@RequestMapping
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CommentService commentService;
	
	private BlogIndex blogIndex = new BlogIndex();
	
	@RequestMapping("/article/{id}")
	public ModelAndView details(@PathVariable("id")String id,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		Blog blog = blogService.getBlogById(Integer.parseInt(id));
		mav.addObject("blog",blog);
		//阅读人数+1
		blog.setClickHit(blog.getClickHit()+1);
		blogService.update(blog);
		mav.addObject("mainPage","foreground/blog/view.jsp");
		mav.addObject("pageTitle",blog.getTitle()+"_个人博客系统");
		//上一篇下一篇
		mav.addObject("pageCode",getUpAndDownPageCode(blogService.getPreviousBlog(Integer.parseInt(id)),blogService.getNextBlog(Integer.parseInt(id)),req.getServletContext().getContextPath()));
		//查询评论
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blogId",blog.getId());
		map.put("state",1);
		mav.addObject("commentList", commentService.getComments(map));
		//处理关键字
		String keyWord = blog.getKeyWord();
		if(StringFormatUtil.isEmpty(keyWord)) {
			mav.addObject("keyWords",null);
		}else {
			String[] arr = keyWord.split(" ");
			mav.addObject("keyWords",StringFormatUtil.filterSpace(Arrays.asList(arr)));
		}
		mav.setViewName("index");
		return mav;
	}
	
	/**
	 * 上一篇
	 * 下一篇
	 */
	private String getUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext) {
		StringBuffer pageCode = new StringBuffer("");
		if(lastBlog==null||lastBlog.getId()==null) {
			pageCode.append("<p>上一篇:没有了</p>");
		}else {
			pageCode.append("<p><a href='"+projectContext+"/article/"+lastBlog.getId()+".html'>上一篇:"+lastBlog.getTitle()+"</a></p>");
		}
		if(nextBlog==null||nextBlog.getId()==null) {
			pageCode.append("<p>下一篇:没有了</p>");
		}else {
			pageCode.append("<p><a href='"+projectContext+"/article/"+nextBlog.getId()+".html'>下一篇:"+nextBlog.getTitle()+"</a></p>");
		}
		
		return pageCode.toString();
	}
	/**
	 * 全文检索
	 * @throws Exception 
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@RequestMapping("/q")
	public ModelAndView q(@RequestParam(value="q",required=false)String q,
			@RequestParam(value="page",required=false) String page,
			HttpServletRequest req) throws IOException, ParseException, Exception {
		if(StringFormatUtil.isEmpty(page)) {
			page="1";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("mainPage", "foreground/blog/result.jsp");
		//在lucene中查询
		List<Blog> blogList = blogIndex.searchBlog(q.trim());
		int toIndex = 0;
		if(blogList.size()>=Integer.parseInt(page)*10) {
			toIndex = Integer.parseInt(page)*10;
		}else {
			toIndex = blogList.size();
		}
		mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*10, toIndex));
		mav.addObject("q",q);
		mav.addObject("resultTotal",blogList.size());
		mav.addObject("pageTitle","搜索关键字'"+q+"'");
		mav.addObject("pageCode",qGetUpAndDownPageCode(Integer.parseInt(page),blogList.size(),q,10,req.getServletContext().getContextPath()));
		mav.setViewName("index");
		return mav;
	}
	/**
	 * 查询结果的翻页
	 */
	private String qGetUpAndDownPageCode(int page,int totalNum,String q,int pageSize,String projectContext) {
		StringBuffer pageCode = new StringBuffer();
		//总共页数
		int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		if(totalPage==0) {
			return "";
		}
		if(page>1) {
			pageCode.append("<li class='page-item'><a class='page-link' href='"+projectContext+"/q.html?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
		}else {
			pageCode.append("<li class='page-item disabled'><a class='page-link' href='#'>上一页</a></li>");
		}
		if(page<totalPage) {
			pageCode.append("<li class='page-item'><a class='page-link' href='"+projectContext+"/q.html?page="+(page+1)+"&q="+q+"'>下一页</a></li>");
		}else {
			pageCode.append("<li class='page-item disabled'><a class='page-link' href='#'>下一页</a></li>");
		}
		
		return pageCode.toString();
		
	}
	
}
