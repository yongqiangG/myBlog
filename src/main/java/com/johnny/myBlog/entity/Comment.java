package com.johnny.myBlog.entity;
/**
 * 评论实体类
 * @author Administrator
 *
 */

import java.util.Date;

public class Comment {
	/**主键*/
	private Integer id;
	/**评论用户Ip*/
	private String userIp;
	/**评论内容*/
	private String content;
	/**评论日期*/
	private Date commentDate;
	/**审核状态*/
	private Integer state;
	/**评论所属博客*/
	private Blog blog;
	/**日期的字符串格式*/
	private String commentDateStr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	public String getCommentDateStr() {
		return commentDateStr;
	}
	public void setCommentDateStr(String commentDateStr) {
		this.commentDateStr = commentDateStr;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", userIp=" + userIp + ", content=" + content + ", commentDate=" + commentDate
				+ ", state=" + state + ", blog=" + blog + ", commentDateStr=" + commentDateStr + "]";
	}
	
}
