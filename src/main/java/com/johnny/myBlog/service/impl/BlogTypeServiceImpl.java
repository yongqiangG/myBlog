package com.johnny.myBlog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnny.myBlog.dao.BlogTypeDao;
import com.johnny.myBlog.entity.BlogType;
import com.johnny.myBlog.service.BlogTypeService;
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {
	@Autowired
	private BlogTypeDao dao;
	public List<BlogType> getBlogTypeWithoutParam() {
		return dao.getBlogTypeWithoutParam();
	}

	public BlogType getBlogTypeById(Integer id) {
		return dao.getBlogTypeById(id);
	}

	public List<BlogType> getBlogTypeByParam(Map<String, Object> map) {
		return dao.getBlogTypeByParam(map);
	}

	public Long getBlogCount(Map<String, Object> map) {
		return dao.getBlogCount(map);
	}

	public Integer add(BlogType blogType) {
		return dao.add(blogType);
	}

	public Integer update(BlogType blogType) {
		return dao.update(blogType);
	}

	public Integer delete(Integer id) {
		return dao.delete(id);
	}

}
