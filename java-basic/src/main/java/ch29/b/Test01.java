// 객체 생성
package ch29.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/b/application-context-01.xml");
    
    System.out.println("--------------------------");
    // 스프링 IoC 컨테이너에 보관된 객체 꺼내기
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", 
          name, 
          iocContationer.getBean(name).getClass().getName());
    }
  }
}
