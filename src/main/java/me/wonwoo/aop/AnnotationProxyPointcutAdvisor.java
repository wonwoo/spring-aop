package me.wonwoo.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

import java.io.Serializable;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
public class AnnotationProxyPointcutAdvisor extends AbstractPointcutAdvisor implements Serializable {

  private final Pointcut pointcut;
  private final Advice advice;

  public AnnotationProxyPointcutAdvisor(Advice advice, Pointcut pointcut) {
    this.pointcut = pointcut;
    this.advice = advice;
  }

  @Override
  public Pointcut getPointcut() {
    return pointcut;
  }

  @Override
  public Advice getAdvice() {
    return advice;
  }

}
