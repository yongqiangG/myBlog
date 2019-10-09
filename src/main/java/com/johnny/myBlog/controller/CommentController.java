package com.johnny.myBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.entity.Comment;
import com.johnny.myBlog.service.BlogService;
import com.johnny.myBlog.service.CommentService;
import com.johnny.myBlog.util.ResponseUtil;

import net.sf.json.JSONObject;
/**
 * 前端用户提交评论
 * @author johnny
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/save")
	public String save(Comment comment,@RequestParam("imageCode")String imageCode,HttpServletRequest req,HttpServletResponse res,HttpSession session) throws Exception {
		String sRand = (String)session.getAttribute("sRand");
		JSONObject result = new JSONObject();
		int resultTotal = 0;
		if(!imageCode.equals(sRand)) {
			result.put("success", Boolean.FALSE);
			result.put("errorInfo","验证码错误");
		}else {
			String userIp = req.getRemoteAddr();
			comment.setUserIp(userIp);
			if(comment.getId()==null) {
				resultTotal = commentService.add(comment);
				
				//给对应的评论数+1
				Blog blog = blogService.getBlogById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);
				blogService.update(blog);
			}
		}
		if(resultTotal>0) {
			result.put("success", Boolean.TRUE);
		}else {
			result.put("success", Boolean.FALSE);
		}
		
		ResponseUtil.writeRes(res, result);
		return null;
	}
}
