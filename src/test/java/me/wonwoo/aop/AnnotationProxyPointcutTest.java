package me.wonwoo.aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import me.wonwoo.mock.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AnnotationProxyPointcutTest {


    @Test
    void matchesIsNotnull(@Mock AttributeSource source) throws NoSuchMethodException {
        given(source.getAttribute(any(), any())).willReturn("notnull");
        AnnotationProxyPointcut annotationProxyPointcut = new AnnotationProxyPointcut(source);
        boolean result = annotationProxyPointcut.matches(this.getClass().getMethod("test_method"), this.getClass());
        assertThat(result).isTrue();
    }

    @Test
    void matchesIsnull(@Mock AttributeSource source) throws NoSuchMethodException {
        given(source.getAttribute(any(), any())).willReturn(null);
        AnnotationProxyPointcut annotationProxyPointcut = new AnnotationProxyPointcut(source);
        boolean result = annotationProxyPointcut.matches(this.getClass().getMethod("test_method"), this.getClass());
        assertThat(result).isFalse();
    }

    public void test_method() {

    }
}