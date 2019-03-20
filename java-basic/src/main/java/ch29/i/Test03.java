// <context:component-scan> - 
//           @Component, @Service, @Repository, @Controller 애노테이션
package ch29.i;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test03 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/i/application-context-03.xml");
    
    // @Component 애노테이션이 붙은 클래스의 인스턴스를 자동으로 생성한다.
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
  }
}
