
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.med.brenda.model.SysDlDayLog;
import com.med.brenda.model.User;
import com.med.brenda.service.ISysDlDayLogService;




public class TestSysDlLogService {

	//HzsfxxService service ;
	private ISysDlDayLogService service;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mvc2.xml"
				,"classpath:spring-mybatis.xml"});
		service = (ISysDlDayLogService) context.getBean("sysDlDayLogService");
	}
	
	
	@Test
	public void addUser(){
		
		List<SysDlDayLog> list = service.selectList(0, 5);
		list.stream().forEach(a -> {
			System.out.println(a.getHzname());
			System.out.println(a.getItemvalue());
		});
		
	}
}