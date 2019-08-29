package DaoTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.johnny.myBlog.dao.BloggerDao;

public class BloggerDaoTest {
	String[] conf = {"spring-mvc.xml","applicationContext.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	@Test
	public void test1(){
		BloggerDao dao = ac.getBean("bloggerDao",BloggerDao.class);
		System.out.println(dao.getBloggerByName("admin"));
	}
}
