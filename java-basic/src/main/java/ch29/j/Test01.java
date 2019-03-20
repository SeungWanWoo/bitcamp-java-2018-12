// Java config - 자바 클래스로 스프링 IoC 컨테이너 설정하기
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    
    // java config를 다룰 때는 AnnotationConfigApplicationContext 클래스를 사용
    ApplicationContext iocContationer =
        new AnnotationConfigApplicationContext(
            AppConfig1.class);
    
    // @Component 애노테이션이 붙은 클래스의 인스턴스를 자동으로 생성한다.
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
  }
}
