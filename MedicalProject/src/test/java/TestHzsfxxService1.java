
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.med.brenda.model.Hzsfxx;
import com.med.brenda.service.impl.HzsfxxService;
import com.med.brenda.util.CommonUtils;




public class TestHzsfxxService1 {

	HzsfxxService service ;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mvc2.xml"
				,"classpath:spring-mybatis.xml"});
		service = (HzsfxxService) context.getBean("hzsfxxService");
	}
	
//	public void checkHz(){
//		System.out.println(service.checkHzxfxxBaseDB(1l, System.currentTimeMillis()));
//	}
	@Test
	public void addUser(){
//		Hzsfxx user = new Hzsfxx();
//		user.setID(3l);
//		user.setAFP("AFP11111");
////		System.out.println(service.insertSelective(user));
//		System.out.println(service.checkHzxfxxBaseDB(1l, CommonUtils.getTimeInMillisBy00_00_00()));
//		service.addHzsfxxBeaseDB(new ArrayList<Hzsfxx>(), 1l, CommonUtils.getTimeInMillisBy00_00_00());
//		System.out.println(service.checkHzxfxxBaseDB(1l, CommonUtils.getTimeInMillisBy00_00_00()));
		
//		try {
//			System.out.println(CommonUtils.getTimeInMillisByDate("20160815"));
//			System.out.println(CommonUtils.getTimeInMillisBy00_00_00());
//			System.out.println(service.checkHzxfxxBaseDB(Long.parseLong("1"), CommonUtils.getTimeInMillisByDate("20160815")));
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String json = "";
		try {
			json = service.getCurrentDateTNB(1l, "20160812");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
	}
}