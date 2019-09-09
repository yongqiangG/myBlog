package com.johnny.myBlog.service;

import com.johnny.myBlog.entity.Blogger;

public interface BloggerService {
	/**根据用户名获取博主对象*/
	public Blogger getBloggerByUserName(String userName);
	/**更新博主信息*/
	public Integer updateBlogger(Blogger blogger);
}
