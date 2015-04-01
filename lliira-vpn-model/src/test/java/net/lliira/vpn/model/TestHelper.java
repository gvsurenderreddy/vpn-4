package net.lliira.vpn.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelper {

  private static final ApplicationContext context =
      new ClassPathXmlApplicationContext("/applicationContext.xml");

  public static <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }
}
