package com.johnny.myBlog.dao;

import java.util.List;
import java.util.Map;

import com.johnny.myBlog.entity.Link;

/**
 * 友情链接Dao接口
 * @author Administrator
 *
 */
public interface LinkDao {
	/**带参数查询所有链接*/
	public List<Link> getLink(Map<String,Object> map);
	/**带参数查询链接总数*/
	public Long getLinkCount(Map<String,Object> map);
	/**新增一条链接*/
	public Integer add(Link link);
	/**删除一条链接*/
	public Integer delete(Integer id);
	/**修改一条链接*/
	public Integer update(Link link);
}
