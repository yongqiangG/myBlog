package com.johnny.myBlog.dao;

import com.johnny.myBlog.entity.Blogger;

public interface BloggerDao {
	public Blogger getBloggerByName(String userName);
}
