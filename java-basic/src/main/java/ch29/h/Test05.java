// BeanPostProcessor - @Autowired 사용법 : 필드에 붙여도 된다. setter/getter 제거 가능
package ch29.h;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test05 {
  public static void main(String[] args) {
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/h/application-context-05.xml");
    
    System.out.println("------------------------------------");
    System.out.println(iocContationer.getBean("c1"));
    System.out.println(iocContationer.getBean("c2"));
    
  }
}
