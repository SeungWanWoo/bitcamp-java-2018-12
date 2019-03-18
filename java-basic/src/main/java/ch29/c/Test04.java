// 생성자 호출 IV - c 네임스페이스를 사용하여 생성자 지정하기
package ch29.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test04 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/c/application-context-04.xml");
    
    System.out.println("------------------------------------");
    System.out.println(iocContationer.getBean("c1"));
    System.out.println(iocContationer.getBean("c2"));
    System.out.println(iocContationer.getBean("c3"));
    System.out.println(iocContationer.getBean("c4"));
  }
}
