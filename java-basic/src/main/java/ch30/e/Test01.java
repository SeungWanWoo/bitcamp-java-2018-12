// AOP 사용 - 메서드 호출 전/후에 파라미터 값이나 리턴 값을 받기
package ch30.e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    // 
    //
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch30/e/application-context-01.xml");

    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
    System.out.println("------------------------------------");
    X x = (X) iocContationer.getBean("x");

    System.out.println(x.calculate(10, 20, "+"));
    
    try {
      System.out.println(x.calculate(10, 20, "%"));
    } catch (Exception e) {
      System.out.println("예외 발생!");
    }
  }
}
