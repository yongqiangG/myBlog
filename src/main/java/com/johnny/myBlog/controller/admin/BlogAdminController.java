package com.johnny.myBlog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.johnny.myBlog.entity.Blog;
import com.johnny.myBlog.entity.PageBean;
import com.johnny.myBlog.lucene.BlogIndex;
import com.johnny.myBlog.service.BlogService;
import com.johnny.myBlog.util.DateJsonValueProcessor;
import com.johnny.myBlog.util.ResponseUtil;
import com.johnny.myBlog.util.StringFormatUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * 博客信息管理
 * @author johnny
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
	@Autowired
	private BlogService service;
	
	private BlogIndex blogIndex = new BlogIndex();
	/**
	 * 保存博客
	 */
	@RequestMapping("/save")
	public String save(Blog blog,HttpServletResponse res) throws Exception {
		int saveResult = 0;
		if(blog.getId()==null) {
			saveResult = service.add(blog);
			blogIndex.addIndex(blog);
		}else {
			saveResult = service.update(blog);
			blogIndex.updateIndex(blog);
		}
		JSONObject result = new JSONObject();
		if(saveResult>=1) {
			result.put("success", Boolean.valueOf(true));
		}else {
			result.put("success", Boolean.valueOf(false));
		}
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 分页查询博客列表
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,Blog blog,
			HttpServletResponse res) throws Exception {
		PageBean pageBean = new PageBean(Integer.valueOf(page),Integer.valueOf(rows));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		//根据标题模糊查询
		map.put("title",StringFormatUtil.stringFormat(blog.getTitle()));
		List<Blog> list = service.getBlogByParam(map);
		Long total = service.getBlogCount(map);
		JSONObject result = new JSONObject();
		//日期转字符串
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		//将列表转换为json对象
		JSONArray jsonArray = JSONArray.fromObject(list,config);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 根据Id查询博客信息
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam("id")String id,HttpServletResponse res) throws Exception {
		Blog blog = service.getBlogById(Integer.parseInt(id));
		JSONObject result = JSONObject.fromObject(blog);
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 删除博客信息
	 */
	@RequestMapping("/delete")
	public String deleteBlog(@RequestParam("ids")String ids,HttpServletResponse res) throws Exception {
		String[] strIds = ids.split(",");
		int deleteResult = 0;
		for(int i=0;i<strIds.length;i++) {
			deleteResult = service.delete(Integer.parseInt(strIds[i]));
			blogIndex.deleteIndex(strIds[i]);
		}
		JSONObject result = new JSONObject();
		if(deleteResult!=0) {
			result.put("success", Boolean.valueOf(true));
		}else {
			result.put("success", Boolean.valueOf(false));
		}
		ResponseUtil.writeRes(res, result);
		return null;
	}
}
