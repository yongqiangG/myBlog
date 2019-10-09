package com.johnny.myBlog.service;

import java.util.List;
import java.util.Map;

import com.johnny.myBlog.entity.Comment;

public interface CommentService {
	/**增加一条评论*/
	public Integer add(Comment comment);
	/**更新一条评论*/
	public Integer update(Comment comment);
	/**无参数查询所有评论*/
	public List<Comment> getAllComments();
	/**带参数查询评论*/
	public List<Comment> getComments(Map<String,Object> map);
	/**无参数查询所有评论数量*/
	public Integer getAllCommentsCount();
	/**带参数查询查询评论数量*/
	public Long getCommentsCount(Map<String,Object> map);
	/**删除一条评论*/
	public Integer delete(Integer id);
}
