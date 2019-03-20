// Java config - @Configuration 애노테이션
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test02 {
  public static void main(String[] args) {
    // AnnotationConfigApplicationContext 컨테이너에 패키지명을 알려주면
    // 그 패키지에 있는 @Component, @Service, @Repository, @Controller 객체를 생성한다.
    // j라는 패키지에는 6개의 클래스가 있는데 그 중 
    // @Component, @Service, @Repository, @Controller 붙은 객체만 생성되었다.
    // 만약 클래스들 중에서 @Configuration 애노테이션이 붙은 클래스가 있다면
    // Java config 클래스로 인식하여 자동으로 처리한다.
    ApplicationContext iocContationer =
        new AnnotationConfigApplicationContext("ch29.j");
    
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
  }
}
