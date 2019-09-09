package com.johnny.myBlog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.johnny.myBlog.entity.Comment;
import com.johnny.myBlog.entity.PageBean;
import com.johnny.myBlog.service.CommentService;
import com.johnny.myBlog.util.DateJsonValueProcessor;
import com.johnny.myBlog.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
	@Autowired
	private CommentService service;
	/**
	 * 获取所有的评论
	 */
	@RequestMapping("/list")
	public String CommentList(@RequestParam(value="page",required=false)String page,
								@RequestParam(value="rows",required=false)String rows,
								@RequestParam(value="state",required=false)String state,
								HttpServletResponse res) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("state", state);
		Long total = service.getCommentsCount(map);
		List<Comment> list = service.getComments(map);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", jsonArray);
		ResponseUtil.writeRes(res, jsonObject);
		return null;
	}
	/**
	 * 删除评论
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse res) throws Exception {
		String[] strIds = ids.split(",");
		int deleteResult = 0;
		for(String id:strIds) {
			deleteResult = service.delete(Integer.parseInt(id));
		}
		JSONObject result = new JSONObject();
		if(deleteResult>0) {
			result.put("success", Boolean.valueOf(true));
		}else {
			result.put("success", Boolean.valueOf(false));
		}
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 评论审核
	 */
	@RequestMapping("/review")
	public String review(@RequestParam(value="ids",required=false)String ids,
						@RequestParam(value="state",required=false)String state,
						HttpServletResponse res
			) throws Exception {
		String[] strIds = ids.split(",");
		for(String id:strIds) {
			Comment comment = new Comment();
			comment.setId(Integer.parseInt(id));
			comment.setState(Integer.parseInt(state));
			service.update(comment);
		}
		JSONObject result = new JSONObject();
		result.put("success",Boolean.valueOf(true));
		ResponseUtil.writeRes(res, result);
		return null;
	}
	
}

