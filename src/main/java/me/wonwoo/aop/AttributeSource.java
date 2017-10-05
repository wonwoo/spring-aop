package me.wonwoo.aop;

import java.lang.reflect.Method;

/**
 * Created by wonwoolee on 2017. 10. 5..
 */
public interface AttributeSource {

  Object getAttribute(Method method, Class<?> targetClass);
}
