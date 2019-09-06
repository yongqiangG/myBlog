package com.johnny.myBlog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.johnny.myBlog.entity.BlogType;
import com.johnny.myBlog.entity.PageBean;
import com.johnny.myBlog.service.BlogService;
import com.johnny.myBlog.service.BlogTypeService;
import com.johnny.myBlog.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 博客类型管理
 * @author johnny
 *
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {
	@Autowired
	private BlogTypeService service;
	@Autowired
	private BlogService blogService;
	/**
	 * 博客类型列表
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,
						@RequestParam(value="rows",required=false)String rows,
						HttpServletResponse res) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		//查询当前页面博客类型
		List<BlogType> blogTypeList = service.getBlogTypeByParam(map);
		//查询博客类型总数
		Long total = service.getBlogCount(map);
		//返回输出流res
		JSONObject result =new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 保存博客类别信息
	 * 更新博客类别信息
	 */
	@RequestMapping("/save")
	public String save(BlogType blogType,HttpServletResponse res) throws Exception {
		int saveResult = 0;
		//根据是否传入id判断执行新增或更新博客类别信息
		if(blogType.getId()==null) {
			saveResult = service.add(blogType).intValue();
		}else {
			saveResult = service.update(blogType).intValue();
		}
		JSONObject result = new JSONObject();
		//判断是否保存成功,成功返回一个value为true的布尔对象
		if(saveResult>=1) {
			result.put("success", Boolean.valueOf(true));
		}else {
			result.put("success", Boolean.valueOf(false));
		}
		ResponseUtil.writeRes(res, result);
		return null;
	}
	/**
	 * 删除博客类别信息
	 */
	@RequestMapping("/delete")
	public String delet(String ids,HttpServletResponse res) throws Exception {
		String[] strIds = ids.split(",");
		int deleteResult = 0;
		JSONObject result = new JSONObject();
		//删除博客类别之前先判断是否存在相关博客
		for(String id : strIds) {
			int blogCount = blogService.getBlogCountByBlogType(Integer.parseInt(id));
			if(blogCount==0) {
				int deleteResultSingle = service.delete(Integer.parseInt(id)).intValue();
				if(deleteResultSingle>=1) {
					deleteResult+=1;
				}
			}else {
				result.put("existBlog", Boolean.valueOf(true));
			}
		}
		
		if(deleteResult>=1) {
			result.put("success", Boolean.valueOf(true));
		}else {
			result.put("success", Boolean.valueOf(false));
		}
		ResponseUtil.writeRes(res, result);
		return null;
	}
	
	
}
