// AOP 사용 전 - X.m1()에 기능을 추가하기 전 
package ch30.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch30/a/application-context-01.xml");
    
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
    System.out.println("------------------------------------");
    X x = (X) iocContationer.getBean("x");
    x.m1();
  }
}
