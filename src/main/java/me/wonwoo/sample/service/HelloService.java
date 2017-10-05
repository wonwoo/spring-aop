package me.wonwoo.sample.service;

import me.wonwoo.sample.AopTest;
import org.springframework.stereotype.Service;

/**
 * Created by wonwoolee on 2017. 10. 6..
 */
@AopTest(test = "hello")
@Service
public class HelloService {

  @AopTest(test = "world")
  public String hello() {
    return "hello world";
  }
}
