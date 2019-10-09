package com.johnny.myBlog.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 博客实体类
 * @author johnny
 *
 */
public class Blog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Integer id;
	/**博客标题*/
	private String title;
	/**博客摘要*/
	private String summary;
	/**博客创建时间*/
	private Date releaseDate;
	/**博客点击数量*/
	private Integer clickHit;
	/**博客回复数量*/
	private Integer replyHit;
	/**博客内容*/
	private String content;
	/**博客关键字*/
	private String keyWord;
	/**博客类别*/
	private BlogType blogType;
	/**纯文本格式的内容*/
	private String contentWithoutTag;
	/**文本格式的博客创建时间*/
	private String releaseDateStr;
	/**博客数量*/
	private Integer blogCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Integer getClickHit() {
		return clickHit;
	}
	public void setClickHit(Integer clickHit) {
		this.clickHit = clickHit;
	}
	public Integer getReplyHit() {
		return replyHit;
	}
	public void setReplyHit(Integer replyHit) {
		this.replyHit = replyHit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public BlogType getBlogType() {
		return blogType;
	}
	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}
	public String getContentWithoutTag() {
		return contentWithoutTag;
	}
	public void setContentWithoutTag(String contentWithoutTag) {
		this.contentWithoutTag = contentWithoutTag;
	}
	public String getReleaseDateStr() {
		return releaseDateStr;
	}
	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}
	public Integer getBlogCount() {
		return blogCount;
	}
	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", summary=" + summary + ", releaseDate=" + releaseDate
				+ ", clickHit=" + clickHit + ", replyHit=" + replyHit + ", content=" + content + ", keyWord=" + keyWord
				+ ", blogType=" + blogType + "]";
	}
	
	
}
