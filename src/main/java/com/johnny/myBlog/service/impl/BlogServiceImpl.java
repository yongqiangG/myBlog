package com.johnny.myBlog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnny.myBlog.dao.BlogDao;
import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.service.BlogService;
@Service("blogService")
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDao dao;

	public List<Blog> getBlogWithoutParam() {
		return dao.getBlogWithoutParam();
	}

	public Blog getBlogById(Integer id) {
		return dao.getBlogById(id);
	}

	public List<Blog> getBlogByParam(Map<String, Object> map) {
		return dao.getBlogByParam(map);
	}

	public Long getBlogCount(Map<String, Object> map) {
		return dao.getBlogCount(map);
	}

	public Integer add(Blog blog) {
		return dao.add(blog);
	}

	public Integer update(Blog blog) {
		return dao.update(blog);
	}

	public Integer delete(Integer id) {
		return dao.delete(id);
	}

	@Override
	public Integer getBlogCountByBlogType(Integer blogTypeId) {
		return dao.getBlogCountByBlogType(blogTypeId);
	}

	

}
