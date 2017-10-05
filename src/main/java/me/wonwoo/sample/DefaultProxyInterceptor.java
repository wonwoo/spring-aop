package me.wonwoo.sample;

import me.wonwoo.aop.AnnotationProxyInterceptor;
import me.wonwoo.aop.AttributeSource;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
public class DefaultProxyInterceptor extends AnnotationProxyInterceptor {

  public DefaultProxyInterceptor(AttributeSource attributeSource) {
    super(attributeSource);
  }

  @Override
  public Object invoke(MethodInvocation invocation, Object attr) throws Throwable {
    Object proceed = invocation.proceed();
    Attribute attribute = (Attribute) attr;
    System.out.println(attribute.getTest());
    return proceed;
  }
}
