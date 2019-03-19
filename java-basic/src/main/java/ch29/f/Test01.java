// 팩토리 클래스를 통해 객체 생성하기
package ch29.f;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/f/application-context-01.xml");
    
    System.out.println("------------------------------------");
    System.out.println(iocContationer.getBean("c1"));
    java.sql.Date obj = (java.sql.Date) iocContationer.getBean("d1");
    System.out.println(obj);
  }
}
