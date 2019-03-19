// BeanPostProcessor - @Autowired 애노테이션을 처리한 BeanPostProcessor 만들기
package ch29.h;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/h/application-context-02.xml");
    
    System.out.println("------------------------------------");
    System.out.println(iocContationer.getBean("c1"));
  }
}
