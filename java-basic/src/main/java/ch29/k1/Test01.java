// Mybatis와 Spring 연동하기 - java config 사용하여 연동하기
package ch29.k1;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ch29.k1.dao.BoardDao;
import ch29.k1.vo.Board;

public class Test01 {
  public static void main(String[] args) {
    
    // java config를 다룰 때는 AnnotationConfigApplicationContext 클래스를 사용
    // => 다음과 같이 스프링 IoC 컨테이너를 설정하는 모든 Java config 클래스를 지정할 수 있다.
    //    그러나 이 방법은 Java config 클래스가 추가될 때마다
    //    계속 생성자에 그 클래스 정보를 추가해야 하는 번거로움이 발생한다.
    // => 그래서 실무에서는 보통 main 역할을 하는 Java config 클래스 정보만 넘긴다.
    //    나머지 Java config 클래스들은 @Configuration을 선언하여
    //    자신이 스프링 IoC 컨테이너를 설정하는 클래스임을 알린다.
    /*
    ApplicationContext iocContationer =
        new AnnotationConfigApplicationContext(
            AppConfig.class, DatabaseConfig.class, MybatisConfig.class);
    */
    ApplicationContext iocContationer =
        new AnnotationConfigApplicationContext(
            AppConfig.class);
    // @Component 애노테이션이 붙은 클래스의 인스턴스를 자동으로 생성한다.
    System.out.println("------------------------------------");
    String[] names = iocContationer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", name, 
          iocContationer.getBean(name).getClass().getName());
    }
    System.out.println("------------------------------------");
    
    BoardDao boardDao = (BoardDao) iocContationer.getBean("boardDao");
    List<Board> list = boardDao.findAll();
    for(Board b : list) {
      System.out.println(b);
    }
  }
}
