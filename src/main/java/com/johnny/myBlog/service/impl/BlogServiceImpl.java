package com.johnny.myBlog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnny.myBlog.dao.BlogDao;
import com.johnny.myBlog.dao.CommentDao;
import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.service.BlogService;
@Service("blogService")
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDao dao;
	@Autowired
	private CommentDao commentDao;

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
		//先删除对应评论再删除博客
		commentDao.deleteCommentByBlogId(id);
		return dao.delete(id);
	}

	@Override
	public Integer getBlogCountByBlogType(Integer blogTypeId) {
		return dao.getBlogCountByBlogType(blogTypeId);
	}

	@Override
	public Blog getPreviousBlog(Integer id) {
		return dao.getPreviousBlog(id);
	}

	@Override
	public Blog getNextBlog(Integer id) {
		return dao.getNextBlog(id);
	}

	

}
