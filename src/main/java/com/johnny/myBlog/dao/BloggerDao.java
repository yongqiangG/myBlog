package com.johnny.myBlog.dao;

import com.johnny.myBlog.entity.Blogger;

public interface BloggerDao {
	/**根据登录名查询博主对象*/
	public Blogger getBloggerByName(String userName);
	/**更新博主个人信息*/
	public Integer updateBlogger(Blogger blogger);
}
