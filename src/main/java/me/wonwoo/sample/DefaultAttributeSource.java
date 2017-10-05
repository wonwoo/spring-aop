package me.wonwoo.sample;

import me.wonwoo.aop.AbstractAttributeSource;
import org.springframework.core.annotation.AnnotationAttributes;

import java.lang.annotation.Annotation;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
public class DefaultAttributeSource extends AbstractAttributeSource {

  @Override
  protected Object parseAnnotation(AnnotationAttributes attributes) {
    String test = attributes.getString("test");
    return new Attribute(test);
  }

  @Override
  protected Class<? extends Annotation> getAnnotation() {
    return AopTest.class;
  }

}