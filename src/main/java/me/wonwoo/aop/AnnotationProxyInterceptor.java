package me.wonwoo.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
public abstract class AnnotationProxyInterceptor implements MethodInterceptor {

  private final AttributeSource attributeSource;

  public AnnotationProxyInterceptor(AttributeSource attributeSource) {
    this.attributeSource = attributeSource;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
    Object attr = attributeSource.getAttribute(invocation.getMethod(), targetClass);
    return invoke(invocation, attr);
  }

  public abstract Object invoke(MethodInvocation invocation, Object attr) throws Throwable;
}

