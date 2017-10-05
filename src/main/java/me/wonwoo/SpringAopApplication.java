package me.wonwoo;

import me.wonwoo.aop.AnnotationProxyInterceptor;
import me.wonwoo.aop.AnnotationProxyPointcut;
import me.wonwoo.aop.AnnotationProxyPointcutAdvisor;
import me.wonwoo.aop.AttributeSource;
import me.wonwoo.sample.DefaultAttributeSource;
import me.wonwoo.sample.DefaultProxyInterceptor;
import me.wonwoo.sample.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class SpringAopApplication {

  @Bean
  AnnotationProxyPointcutAdvisor proxyAdvisor(AnnotationProxyInterceptor proxyInterceptor, AttributeSource attributeSource) {
    AnnotationProxyPointcut proxyPointcut = new AnnotationProxyPointcut(attributeSource);
    return new AnnotationProxyPointcutAdvisor(proxyInterceptor, proxyPointcut);
  }

  @Bean
  AnnotationProxyInterceptor proxyInterceptor() {
    return new DefaultProxyInterceptor(attributeSource());
  }

  @Bean
  AttributeSource attributeSource() {
    return new DefaultAttributeSource();
  }
}
