package com.johnny.myBlog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnny.myBlog.dao.CommentDao;
import com.johnny.myBlog.entity.Comment;
import com.johnny.myBlog.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao dao;
	

	@Override
	public Integer add(Comment comment) {
		return dao.add(comment);
	}

	@Override
	public Integer update(Comment comment) {
		return dao.update(comment);
	}

	@Override
	public List<Comment> getAllComments() {
		return dao.getAllComments();
	}

	@Override
	public List<Comment> getComments(Map<String, Object> map) {
		return dao.getComments(map);
	}

	@Override
	public Integer getAllCommentsCount() {
		return dao.getAllCommentsCount();
	}

	@Override
	public Long getCommentsCount(Map<String, Object> map) {
		return dao.getCommentsCount(map);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
