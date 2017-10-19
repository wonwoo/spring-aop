package me.wonwoo.sample;

import org.aopalliance.intercept.MethodInvocation;

import me.wonwoo.aop.AnnotationProxyInterceptor;
import me.wonwoo.aop.AttributeSource;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
public class DefaultProxyInterceptor extends AnnotationProxyInterceptor<Attribute> {

  public DefaultProxyInterceptor(AttributeSource attributeSource) {
    super(attributeSource);
  }

  @Override
  public Object invoke(MethodInvocation invocation, Attribute attr) throws Throwable {
    Object proceed = invocation.proceed();
    System.out.println(attr.getTest());
    return proceed;
  }
}
