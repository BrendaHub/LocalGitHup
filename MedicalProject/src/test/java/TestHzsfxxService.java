import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.med.brenda.service.impl.HzsfxxService;

public class TestHzsfxxService {

	Logger logger = Logger.getLogger("TestHzsfxxService");

	HzsfxxService service = null;

	@Before
	public void init() {

		ApplicationContext aCtx = new FileSystemXmlApplicationContext("classpath:spring-mvc2.xml");
		HzsfxxService service = (HzsfxxService) aCtx.getBean("hzsfxxService");
		assertNotNull(service);
		this.service = service;

	}

	@Test
	public void testInsertAccount() {

//		// 创建一个帐户
//		Account account = new Account();
//		// account.setAccountId(1);
//		account.setUsername("selina");
//		account.setPassword("123456");
//
//		// 将创建的帐户插入到数据库中
//		service.insertAccount(account);
//		logger.debug("account id: " + account.getAccountId());
//
//		// 从数据库获取刚才插入的帐户
//		Account accountFromDb = service.getAccountById(account);
//		assertNotNull(accountFromDb);
//		assertEquals(account.getAccountId(), accountFromDb.getAccountId());
	}

}