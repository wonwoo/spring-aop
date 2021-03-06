package me.wonwoo.aop;

import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * Created by wonwoolee on 2017. 10. 5..
 */
public interface AttributeSource {

  @Nullable
  Object getAttribute(Method method, Class<?> targetClass);
}
