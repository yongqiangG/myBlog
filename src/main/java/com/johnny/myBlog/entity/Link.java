package com.johnny.myBlog.entity;

import java.io.Serializable;

public class Link implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Integer id;
	/**链接名称*/
	private String linkname;
	/**链接地址*/
	private String linkurl;
	/**优先级序号*/
	private Integer orderNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
}
