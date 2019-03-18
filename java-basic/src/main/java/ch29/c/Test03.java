// 생성자 호출 III - <constructor-arg> 의 value 속성
package ch29.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test03 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/c/application-context-03.xml");
    
    System.out.println("------------------------------------");
    
    System.out.println(iocContationer.getBean("c1"));
    System.out.println(iocContationer.getBean("c2"));
    System.out.println(iocContationer.getBean("c3"));
  }
}
