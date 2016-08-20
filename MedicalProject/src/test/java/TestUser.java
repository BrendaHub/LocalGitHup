
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.med.brenda.model.User;
import com.med.brenda.service.IUserService;




public class TestUser {

	//HzsfxxService service ;
	private IUserService service;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mvc2.xml"
				,"classpath:spring-mybatis.xml"});
		service = (IUserService) context.getBean("userService");
	}
	
	
	@Test
	public void addUser(){
//		TnbTnbson bs = new TnbTnbson();
//		 bs.setFatherid(2312312l);
//		 bs.setHzid(1l);
//		 bs.setItemcode("016001");
//		 bs.setItemname("早");
//		 bs.setYds("adfadsfaF");
//		 bs.setYdsjl("12");
//		 bs.setYdsejsg("1212");
//		 bs.setTemp4("2016-08-13");//更新时间
//		 bs.setTemp5("2016-08-13");//插入时间
//		 int rowid = service.insert(bs);
//		 System.out.println(rowid);
//		 System.out.println(bs.getId());
		
//		List<TnbTnbson> list = service.findFeedList(1l, "016", "2016-08-13");
//		list.stream().forEach(t ->{
//			System.out.println(t.getFatherid());
//			System.out.println(t.getItemname());
//		});
		User user = service.getUserByUserNamePwd("admin", "adfasdfasdf");
		System.out.println(JSON.toJSONString(user));
		
	}
}