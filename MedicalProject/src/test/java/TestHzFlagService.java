import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.med.brenda.model.Hzflag;
import com.med.brenda.model.TnbTnbson;
import com.med.brenda.service.impl.HzFlagService;
import com.med.brenda.service.impl.TnbTnbsonService;

public class TestHzFlagService {

	//HzsfxxService service ;
		HzFlagService service;
		
		@Before
		public void before(){                                                                    
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mvc2.xml"
					,"classpath:spring-mybatis.xml"});
			service = (HzFlagService) context.getBean("hzFlagService");
		}
		
		@Test
		public void addUser(){
			Hzflag hzflag = new Hzflag();
			hzflag.setHZID(1l);
			hzflag.setYSID(161l);
			hzflag.setTEMP1("dsfsdfsdfasdf");
			int rowid = service.insert(hzflag);
			System.out.println("rowid = " + rowid);
			System.out.println("id = " + hzflag.getID());
		}
}
