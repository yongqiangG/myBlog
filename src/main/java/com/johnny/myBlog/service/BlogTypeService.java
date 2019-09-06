package com.johnny.myBlog.service;

import java.util.List;
import java.util.Map;

import com.johnny.myBlog.entity.BlogType;

public interface BlogTypeService {
	/**无参数查询所有博客类型列表*/
	public List<BlogType> getBlogTypeWithoutParam();
	
	/**根据Id查询博客类型*/
	public BlogType getBlogTypeById(Integer id);
	
	/**不固定参数查询博客类型列表*/
	public List<BlogType> getBlogTypeByParam(Map<String, Object> map);
	
	/**不固定参数查询博客类型数量*/
	public Long getBlogCount(Map<String, Object> map);
	
	/**增加一条博客类型*/
	public Integer add(BlogType blogType);
	
	/**修改一条博客类型*/
	public Integer update(BlogType blogType);
	
	/**删除一条博客类型*/
	public Integer delete(Integer id);
}
