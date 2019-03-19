// 프로퍼티 값 설정 - properties 프로퍼티 값 설정하기
package ch29.e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test04 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/e/application-context-04.xml");
    
    System.out.println("------------------------------------");
    System.out.println(iocContationer.getBean("t1"));
    //System.out.println(iocContationer.getBean("c2"));
  }
}
