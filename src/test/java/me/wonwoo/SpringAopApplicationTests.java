package me.wonwoo;

import me.wonwoo.sample.service.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@SpringJUnitConfig(SpringAopApplication.class)
class SpringAopApplicationTests {

	private final HelloService service;

	@Autowired
	SpringAopApplicationTests(HelloService service) {
		this.service = service;
	}

	@Test
	@DisplayName("😎")
	void methodInjection(@Autowired HelloService service) {
		assertThat(service.hello()).isEqualTo("hello world");
	}

	@Test
	@DisplayName("🤓")
	void constructorInjection() {
		assertThat(service.hello()).isEqualTo("hello world");
	}
}
