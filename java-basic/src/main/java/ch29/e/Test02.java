// 프로퍼티 값 설정 - List 프로퍼티 값 설정하기
package ch29.e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test02 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/e/application-context-02.xml");
    
    System.out.println("------------------------------------");
    System.out.println(iocContationer.getBean("c1"));
    System.out.println(iocContationer.getBean("c2"));
//    System.out.println(iocContationer.getBean("c3"));
//    System.out.println(iocContationer.getBean("c4"));
  }
}

// 결론!
// List나 배열이나 사용방법은 똑같다.