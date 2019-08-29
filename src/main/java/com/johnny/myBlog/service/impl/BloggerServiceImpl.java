package com.johnny.myBlog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.johnny.myBlog.dao.BloggerDao;
import com.johnny.myBlog.entity.Blogger;
import com.johnny.myBlog.service.BloggerService;
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
	@Autowired
	private BloggerDao bloggerDao;
	
	public Blogger getBloggerByUserName(String userName) {
		return bloggerDao.getBloggerByName(userName);
	}

}
