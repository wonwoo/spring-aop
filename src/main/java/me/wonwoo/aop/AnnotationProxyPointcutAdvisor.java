package me.wonwoo.aop;

import java.io.Serializable;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
public class AnnotationProxyPointcutAdvisor extends AbstractPointcutAdvisor implements Serializable {

  private final Advice advice;
  private final Pointcut pointcut;


  public AnnotationProxyPointcutAdvisor(Advice advice, Pointcut pointcut) {
    this.advice = advice;
    this.pointcut = pointcut;
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
