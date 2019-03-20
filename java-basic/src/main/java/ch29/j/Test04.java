// Java config - @Configuration과 @Bean
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test04 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new AnnotationConfigApplicationContext(AppConfig4.class);
    
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
    
    System.out.println("------------------------------------");
    System.out.println(iocContationer.getBean("car1"));
    System.out.println(iocContationer.getBean("car2"));
  }
}
