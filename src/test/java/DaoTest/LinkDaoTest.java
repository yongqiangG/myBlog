package DaoTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.johnny.myBlog.dao.LinkDao;
import com.johnny.myBlog.entity.Link;

public class LinkDaoTest {
	String[] conf = {"spring-mvc.xml","applicationContext.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	// test add link
	@Test
	public void test1() {
		LinkDao dao = ac.getBean("linkDao",LinkDao.class);
		Link link = new Link();
		link.setLinkname("谷歌");
		link.setLinkurl("www.google.com");
		link.setOrderNum(3);
		dao.add(link);
	}
	// test update link
	@Test
	public void test2() {
		LinkDao dao = ac.getBean("linkDao",LinkDao.class);
		Link link = new Link();
		link.setLinkname("google");
		link.setLinkurl("www.google.com");
		link.setId(8);
		dao.update(link);
	}
	// test delete link
	@Test
	public void test3() {
		LinkDao dao =ac.getBean("linkDao",LinkDao.class);
		dao.delete(8);
	}
	// test query link
	@Test
	public void test4() {
		LinkDao dao =ac.getBean("linkDao",LinkDao.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", 1);
		map.put("size", 10);
		dao.getLink(map);
	}
	// test query link count
	@Test 
	public void test5() {
		LinkDao dao = ac.getBean("linkDao",LinkDao.class);
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(dao.getLinkCount(map));
	}
}
