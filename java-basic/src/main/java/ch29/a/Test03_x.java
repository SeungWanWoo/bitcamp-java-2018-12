// 패키지를 탐색하여 빈을 자동 생성하기
package ch29.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test03_x {
  public static void main(String[] args) {
    // 빈을 자동으로 생성하고 싶은 클래스에
    // @Component 등의 애노테이션을 붙인다.
    //
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/a/application-context-03.xml");
    
    // 스프링 IoC 컨테이너가 생성한 객체 꺼내기
    // => @Component가 붙은 클래스의 인스턴스를 꺼낼 때는
    //    클래스이름으로 꺼낸다. 단, 클래스의 첫 번째 알파벳은 소문자로 지정한다.
    Student2 obj1 = (Student2) iocContationer.getBean("student2");
    System.out.println(obj1);
    
    // 존재하지 않는 객체를 꺼내려하면 예외가 발생한다.
    // @Component가 붙지 않은 클래스는 자동으로 객체를 생성하지 않는다.
    Student obj2 = (Student) iocContationer.getBean("student");
    System.out.println(obj2);
    
    System.out.println("실행완료!");
  }
}
