package com.johnny.myBlog.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.johnny.myBlog.entity.BlogType;
import com.johnny.myBlog.service.BlogTypeService;
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
		ServletContext application = sce.getServletContext();
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeWithoutParam();
		application.setAttribute(CommonParam.Blog_Type_List, blogTypeList);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
