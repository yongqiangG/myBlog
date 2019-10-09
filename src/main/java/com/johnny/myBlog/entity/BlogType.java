package com.johnny.myBlog.entity;

import java.io.Serializable;
/**
 * 博客类型
 * @author johnny
 *
 */
public class BlogType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**博客类型名称*/
	private String typeName;
	/**优先级序号*/
	private String orderNum;
	/**博客类型数量*/
	private Integer blogCount;
	
	public Integer getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "BlogType [id=" + id + ", typeName=" + typeName + ", orderNum=" + orderNum + ", blogCount=" + blogCount + "]";
	}
	
}
