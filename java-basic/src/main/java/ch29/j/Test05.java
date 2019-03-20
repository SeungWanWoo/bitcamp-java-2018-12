// Java config - @PropertySource 사용법
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test05 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new AnnotationConfigApplicationContext(AppConfig5.class);
    
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
  }
}
