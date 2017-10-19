package me.wonwoo.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import me.wonwoo.mock.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class AnnotationProxyInterceptorTest {

    class Foo {
        private final String name;
        Foo(String name) {
            this.name = name;
        }
    }

    @Test
    void testInvoke(@Mock AttributeSource attributeSource, @Mock MethodInvocation invocation) throws Throwable {
        given(attributeSource.getAttribute(any(), any())).willReturn(new Foo("wonwoo"));
        AnnotationProxyInterceptor<Foo> interceptor = new AnnotationProxyInterceptor<Foo>(attributeSource) {
            @Override
            public Object invoke(MethodInvocation invocation, Foo attr) throws Throwable {
                return attr;
            }
        };
        Object result = interceptor.invoke(invocation);
        assertThat(result).isInstanceOf(Foo.class);
        assertThat(((Foo) result).name).isEqualTo("wonwoo");
    }
}