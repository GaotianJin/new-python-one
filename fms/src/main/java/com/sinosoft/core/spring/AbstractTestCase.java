package com.sinosoft.core.spring;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/infrastructure-persistence.xml", "classpath:spring/interfaces.xml", "classpath:spring/security.xml", "classpath:spring/jbpm.xml",
		"classpath:quartz/quartz.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class AbstractTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	@Before
	public void initMocks() {
		// ④ 初始化当前测试类所有@Mock注解模拟对象
		MockitoAnnotations.initMocks(this);
	}


}
