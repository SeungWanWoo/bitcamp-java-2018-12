// AOP 사용 전 - X.m1()에 기능 추가하기
package ch30.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    // 기존 메서드에 기능을 추가하는 고전적인 방식
    // => m1() 메서드 안에 직접 코드를 추가한다.
    // => 또는 외부 클래스에 추가할 코드를 작성해 놓고 m1()에서 호출한다.
    // => 어떤 방법을 사용하든 m1()에 기능을 추가하려면 m1()을 편집해야한다.
    //
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch30/b/application-context-01.xml");
    
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
    System.out.println("------------------------------------");
    X x = (X) iocContationer.getBean("x");
    x.m1();
  }
}
