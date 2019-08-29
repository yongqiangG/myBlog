package DaoTest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.johnny.myBlog.dao.BlogTypeDao;
import com.johnny.myBlog.entity.BlogType;

public class BlogTypeDaoTest {
	String[] conf = {"spring-mvc.xml","applicationContext.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	BlogTypeDao dao = ac.getBean("blogTypeDao",BlogTypeDao.class);
	@Test
	public void test1() {
		BlogType b = dao.getBlogTypeById(16);
		System.out.println(b);
	}
	@Test
	public void test2() {
		BlogType b = dao.getBlogTypeById(16);
		b.setTypeName("python");
		dao.update(b);
	}
	@Test
	public void test3() {
		BlogType b = dao.getBlogTypeById(16);
		dao.add(b);
	}
	@Test
	public void test4() {
		dao.delete(19);
	}
	@Test
	public void test5() {
		List<BlogType> list = dao.getBlogTypeWithoutParam();
		for(BlogType b:list) {
			System.out.println(b);
		}
	}
	@Test
	public void test6() {
		
	}
}
