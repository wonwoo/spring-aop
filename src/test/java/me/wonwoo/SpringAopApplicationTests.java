package me.wonwoo;

import me.wonwoo.sample.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringAopApplication.class)
public class SpringAopApplicationTests {

	@Autowired
	private HelloService service;


	@Test
	public void contextLoads() {
		System.out.println(service.hello());;


	}

}
