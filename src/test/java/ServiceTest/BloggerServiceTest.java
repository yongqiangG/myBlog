package ServiceTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.johnny.myBlog.service.BloggerService;

public class BloggerServiceTest {
	String[] conf = {"spring-mvc.xml","applicationContext.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	@Test
	public void test1() {
		BloggerService service = ac.getBean("bloggerService",BloggerService.class);
		System.out.println(service.getBloggerByUserName("admin"));
	}
}
