package me.wonwoo.sample;

import java.lang.annotation.*;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
public @interface AopTest {

  String test() default "";
}
