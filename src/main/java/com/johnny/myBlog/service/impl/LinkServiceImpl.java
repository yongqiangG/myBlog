package com.johnny.myBlog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnny.myBlog.dao.LinkDao;
import com.johnny.myBlog.entity.Link;
import com.johnny.myBlog.service.LinkService;
/**
 * 友情链接服务实现类
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {
	@Autowired
	private LinkDao linkDao;
	
	@Override
	public List<Link> getLink(Map<String, Object> map) {
		return linkDao.getLink(map);
	}

	@Override
	public Long getLinkCount(Map<String, Object> map) {
		return linkDao.getLinkCount(map);
	}

	@Override
	public Integer add(Link link) {
		return linkDao.add(link);
	}

	@Override
	public Integer delete(Integer id) {
		return linkDao.delete(id);
	}

	@Override
	public Integer update(Link link) {
		return linkDao.update(link);
	}

}
