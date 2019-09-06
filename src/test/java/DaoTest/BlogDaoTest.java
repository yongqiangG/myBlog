package DaoTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.johnny.myBlog.dao.BlogDao;

public class BlogDaoTest {
	String[] conf = {"spring-mvc.xml","applicationContext.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	@Test
	public void test1() {
		BlogDao dao = ac.getBean("blogDao",BlogDao.class);
		System.out.println(dao.getBlogCountByBlogType(1));
	}
}
