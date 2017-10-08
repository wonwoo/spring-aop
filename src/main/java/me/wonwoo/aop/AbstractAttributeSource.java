package me.wonwoo.aop;

import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wonwoolee on 2017. 10. 5..
 */
public abstract class AbstractAttributeSource implements AttributeSource {

  private final Map<Object, Object> attributeCache =
      new ConcurrentHashMap<>(1024);

  @Override
  @Nullable
  public Object getAttribute(Method method, Class<?> targetClass) {
    Object cacheKey = getCacheKey(method, targetClass);
    Object attribute = attributeCache.get(cacheKey);
    if (attribute != null) {
      return attribute;
    }
    attribute = computeAttribute(method, targetClass);
    if(attribute != null) {
      this.attributeCache.put(cacheKey, attribute);
      return attribute;
    }
    return null;
  }

  @Nullable
  protected Object computeAttribute(Method method, Class<?> targetClass) {
    if (!Modifier.isPublic(method.getModifiers())) {
      return null;
    }
    Class<?> userClass = ClassUtils.getUserClass(targetClass);
    Method specificMethod = ClassUtils.getMostSpecificMethod(method, userClass);
    specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
    Object attribute = determineAnnotatedElement(specificMethod);
    if (attribute != null) {
      return attribute;
    }
    attribute = determineAnnotatedElement(specificMethod.getDeclaringClass());
    if (attribute != null) {
      return attribute;
    }
    return null;
  }

  private Object getCacheKey(Method method, Class<?> targetClass) {
    return new MethodClassKey(method, targetClass);
  }

  @Nullable
  protected Object determineAnnotatedElement(AnnotatedElement ae) {
    if (ae.getAnnotations().length > 0) {
      Class<? extends Annotation> annotation = getAnnotation();
      if(annotation == null) {
        return null;
      }
      AnnotationAttributes annotationAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(ae, annotation);
      if (annotationAttributes != null) {
        return parseAnnotation(annotationAttributes);
      }
    }
    return null;
  }

  @NonNull
  protected abstract Object parseAnnotation(AnnotationAttributes annotationAttributes);

  @NonNull
  protected abstract Class<? extends Annotation> getAnnotation();

}
