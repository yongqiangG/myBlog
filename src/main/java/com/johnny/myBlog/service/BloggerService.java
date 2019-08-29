package com.johnny.myBlog.service;

import com.johnny.myBlog.entity.Blogger;

public interface BloggerService {
	public Blogger getBloggerByUserName(String userName);
}
