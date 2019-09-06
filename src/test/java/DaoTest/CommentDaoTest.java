package DaoTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.johnny.myBlog.dao.BlogDao;
import com.johnny.myBlog.dao.CommentDao;
import com.johnny.myBlog.entity.Comment;

public class CommentDaoTest {
	String[] conf = {"spring-mvc.xml","applicationContext.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	@Test
	public void test1() {
		CommentDao dao = ac.getBean("commentDao",CommentDao.class);
		BlogDao blogDao = ac.getBean("blogDao",BlogDao.class);
		Comment c = new Comment();
		c.setBlog(blogDao.getBlogById(50));
		c.setUserIp("192.168.1.1");
		c.setCommentDate(new Timestamp(System.currentTimeMillis()));
		c.setContent("内容");
		c.setState(0);
		dao.add(c);
	}
	@Test
	public void test2() {
		CommentDao dao = ac.getBean("commentDao",CommentDao.class);
		BlogDao blogDao = ac.getBean("blogDao",BlogDao.class);
		Comment c = new Comment();
		c.setBlog(blogDao.getBlogById(50));
		c.setUserIp("192.168.1.1");
		c.setCommentDate(new Timestamp(System.currentTimeMillis()));
		c.setContent("内容改变了");
		c.setState(0);
		c.setId(1);
		dao.update(c);
	}
	@Test
	public void test3() {
		CommentDao dao = ac.getBean("commentDao",CommentDao.class);
		dao.delete(2);
	}
	@Test
	public void test4() {
		CommentDao dao = ac.getBean("commentDao",CommentDao.class);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Comment> comments = dao.getComments(map);
		for(Comment c:comments) {
			System.out.println(c);
		}
	}
	@Test
	public void test5() {
		CommentDao dao = ac.getBean("commentDao",CommentDao.class);
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(dao.getCommentsCount(map));
	}
	
}
