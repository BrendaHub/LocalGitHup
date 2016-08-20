
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.med.brenda.controller.common.Query;
import com.med.brenda.model.Hzxx;
import com.med.brenda.service.IHzxxService;




public class TestHzxx {

	//HzsfxxService service ;
	private IHzxxService service;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mvc2.xml"
				,"classpath:spring-mybatis.xml"});
		service = (IHzxxService) context.getBean("hzxxService");
	}
	
	
	@Test
	public void addUser(){
		Query query = new Query();
		Integer count = service.findListCount(query);
		System.out.println(count);
		query.setPageIndex(1);
		query.setPageSize(2);
		List<Hzxx> list = service.findList(query);
		list.stream().forEach(a ->{
			System.out.println(a.getHZNAME());
			System.out.println(a.getSFZCODE());
		});
		
	}
}