package me.wonwoo.aop;

import java.lang.annotation.*;

import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotationAttributes;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractAttributeSourceTest {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Inherited
    @Documented
    @interface Aop {

        boolean test() default true;
    }

    class Bar {
        private final boolean  test;
        Bar(boolean test) {this.test = test;}
    }

    private final AbstractAttributeSource abstractAttributeSource = new AbstractAttributeSource() {
        @Override
        protected Object parseAnnotation(AnnotationAttributes attributes) {
            return new Bar(attributes.getBoolean("test"));
        }

        @Override
        protected Class<? extends Annotation> getAnnotation() {
            return Aop.class;
        }
    };

    @Test
    void getAttributeTrue() throws NoSuchMethodException {
        Object testMethod = abstractAttributeSource.getAttribute(this.getClass().getMethod("test_method_true"), this.getClass());
        assertThat(testMethod).isInstanceOf(Bar.class);
        assertThat(((Bar) testMethod).test).isEqualTo(true);
    }

    @Test
    void getAttributeFalse() throws NoSuchMethodException {
        Object testMethod = abstractAttributeSource.getAttribute(this.getClass().getMethod("test_method_false"), this.getClass());
        assertThat(testMethod).isInstanceOf(Bar.class);
        assertThat(((Bar) testMethod).test).isEqualTo(false);
    }

    @Test
    void computeAttributeIsPrivate() throws NoSuchMethodException {
        Object testMethodPrivate =
                abstractAttributeSource.computeAttribute(this.getClass().getDeclaredMethod("test_method_private"), this.getClass());
        assertThat(testMethodPrivate).isNull();
    }

    @Test
    void computeAttribute() throws NoSuchMethodException {
        Object testMethod =
                abstractAttributeSource.computeAttribute(this.getClass().getMethod("test_method_true"), this.getClass());
        assertThat(testMethod).isInstanceOf(Bar.class);
        assertThat(((Bar) testMethod).test).isEqualTo(true);
    }

    @Test
    void computeAttributeMethodIsNothing() throws NoSuchMethodException {
        Object testMethod =
                abstractAttributeSource.determineAnnotatedElement(this.getClass().getMethod("test_method_is_nothing"));
        assertThat(testMethod).isNull();
    }

    @Test
    void computeAttributeClassIsNothing() throws NoSuchMethodException {
        Object testMethod =
                abstractAttributeSource.determineAnnotatedElement(this.getClass());
        assertThat(testMethod).isNull();
    }

    public void test_method_is_nothing() {

    }

    @Aop(test = false)
    private void test_method_private() {

    }

    @Aop(test = false)
    public void test_method_false() {

    }

    @Aop
    public void test_method_true() {

    }

}