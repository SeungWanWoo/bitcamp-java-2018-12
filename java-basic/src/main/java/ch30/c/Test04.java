// AOP 사용 후 - AOP 설정 다루기
package ch30.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test04 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch30/c/application-context-04.xml");
    
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
    System.out.println("------------------------------------");
    X x = (X) iocContationer.getBean("x");
    // => 우리가 만든 X가 아닌 Spring이 만든 프록시 설계패턴에 의해 생성된 X이다.
    x.m1();
    
  }
}
