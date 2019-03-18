// 빈 생성과 꺼내기
package ch29.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test02_j {
  public static void main(String[] args) {
    // 객체 생성
    // @Bean public Student b1() { return new Student(); }
    //
    ApplicationContext iocContationer =
        new AnnotationConfigApplicationContext(
            AppConfig02.class);
    
    // 스프링 IoC 컨테이너가 생성한 객체 꺼내기
    Student b1 = (Student) iocContationer.getBean("b1");
    System.out.println(b1);
    
    // 존재하지 않는 객체를 꺼냐려하면 예외가 발생한다.
    // Student b2 = (Student) iocContationer.getBean("b2");
    // => NoSuchBeanException
    // System.out.println(b2);
    
    System.out.println("실행완료!");
  }
}
