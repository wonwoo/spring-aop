package me.wonwoo.aop;

import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationProxyPointcutAdvisorTest {

    @Test
    void pointCut() {
        AnnotationProxyPointcutAdvisor advisor = new AnnotationProxyPointcutAdvisor(new Advice() {}, new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return ClassFilter.TRUE;
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return MethodMatcher.TRUE;
            }
        });
        assertThat(advisor.getAdvice()).isInstanceOf(Advice.class);
        assertThat(advisor.getPointcut()).isInstanceOf(Pointcut.class);
    }
}