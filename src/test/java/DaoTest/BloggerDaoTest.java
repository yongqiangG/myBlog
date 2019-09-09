package DaoTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.johnny.myBlog.dao.BloggerDao;
import com.johnny.myBlog.entity.Blogger;

public class BloggerDaoTest {
	String[] conf = {"spring-mvc.xml","applicationContext.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	@Test
	public void test1(){
		BloggerDao dao = ac.getBean("bloggerDao",BloggerDao.class);
		System.out.println(dao.getBloggerByName("admin"));
	}
	@Test 
	public void test2() {
		BloggerDao dao = ac.getBean("bloggerDao",BloggerDao.class);
		Blogger blogger = new Blogger();
		blogger.setId(1);
		blogger.setImageName("this is a image");
		blogger.setNickName("johnny");
		blogger.setSign("once more");
		blogger.setProfile("try it");
		dao.updateBlogger(blogger);
	}
}
