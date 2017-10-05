package me.wonwoo.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
public class AnnotationProxyPointcut extends StaticMethodMatcherPointcut implements Serializable {

  private final AttributeSource attributeSource;

  public AnnotationProxyPointcut(AttributeSource attributeSource) {
    this.attributeSource = attributeSource;
  }

  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    return method.getDeclaringClass() != Object.class && attributeSource.getAttribute(method, targetClass) != null;
  }
}
