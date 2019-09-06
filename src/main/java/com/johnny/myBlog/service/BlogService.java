package com.johnny.myBlog.service;

import java.util.List;
import java.util.Map;

import com.johnny.myBlog.entity.Blog;

public interface BlogService {
	/**无参数查询所有博客列表*/
	public List<Blog> getBlogWithoutParam();
	
	/**根据Id查询博客*/
	public Blog getBlogById(Integer id);
	
	/**不固定参数查询博客列表*/
	public List<Blog> getBlogByParam(Map<String, Object> map);
	
	/**不固定参数查询博客数量*/
	public Long getBlogCount(Map<String, Object> map);
	
	/**增加一条博客*/
	public Integer add(Blog blog);
	
	/**修改一条博客*/
	public Integer update(Blog blog);
	
	/**删除一条博客*/
	public Integer delete(Integer id);
	
	/**根据博客类型查询对应的博客数量*/
	public Integer getBlogCountByBlogType(Integer blogTypeId);
}
