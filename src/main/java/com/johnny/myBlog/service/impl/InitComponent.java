package com.johnny.myBlog.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.entity.BlogType;
import com.johnny.myBlog.entity.Blogger;
import com.johnny.myBlog.entity.Link;
import com.johnny.myBlog.service.BlogService;
import com.johnny.myBlog.service.BlogTypeService;
import com.johnny.myBlog.service.BloggerService;
import com.johnny.myBlog.service.LinkService;
import com.johnny.myBlog.util.CommonParam;


/**
 * 项目启动初始化,预先加载部分资源
 * @author johnny
 *
 */
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void contextInitialized(ServletContextEvent sce) {
		//将博客类别放入缓存中
		ServletContext application = sce.getServletContext();
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeWithoutParam();
		application.setAttribute(CommonParam.Blog_Type_List, blogTypeList);
		//按年月分类的博客数量
		BlogService blogService = (BlogService)applicationContext.getBean("blogService");
		List<Blog> blogCountList = blogService.getBlogWithoutParam();
		application.setAttribute(CommonParam.BLOG_COUNT_LIST, blogCountList);
		//友情链接列表
		LinkService linkService = (LinkService)applicationContext.getBean("linkService");
		List<Link> linkList = linkService.getLink(null);
		application.setAttribute(CommonParam.LINK_LIST, linkList);
		//博主信息
		BloggerService bloggerService = (BloggerService)applicationContext.getBean("bloggerService");
		Blogger blogger = bloggerService.getBlogger();
		blogger.setPassword(null);
		application.setAttribute(CommonParam.BLOGGER, blogger);
		//博客列表
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
