// Mybatis와 Spring 연동하기 - XML을 사용하여 연동하기
package ch29.k2;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.k2.dao.BoardDao;
import ch29.k2.vo.Board;

public class Test01 {
  public static void main(String[] args) {
    // XML 설정 파일을 사용할 때는 ClassPathXmlApplicationContext 클래스를 이용한다.
    ApplicationContext iocContationer =
        new ClassPathXmlApplicationContext(
            "ch29/k2/application-context.xml",
            "ch29/k2/database-context.xml",
            "ch29/k2/mybatis-context.xml");

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
