// join 데이터 다루기 - join 사용 후
package ch26.h;

import java.io.InputStream;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test02 {
  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/h/mybatis-config.xml");
    
    SqlSessionFactory sqlSessionFactory = 
        new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    Scanner keyboard = new Scanner(System.in);
    System.out.print("게시물 번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    keyboard.close();
    
    
    // 조인을 사용할 경우 부모 객체 안에 하위 테이블의 정보를 받을 수 있다.
    // 작업:
    // 1) 부모 테이블의 데이터를 받는 클래스(ex: Board)에 
    //    자식 테이블의 데이터를 받는 필드(ex: attachFiles)를 선언하라.
    // 2) SQL Mapper 파일에서 <resultMap> 태그에 조인 정보를 정의한다.
    //    => 조인을 이용할 경우 다음과 같이 한 번만 SQL을 실행하면 된다.
    //    mybatis가 SQL 매퍼에 정의된 대로 자식 테이블의 데이터까지 
    //    인스턴스로 만들어 리턴해준다.

    /*
    Board boards = sqlSession.selectOne("board.selectBoard", no);
    System.out.println(boards);
    
    List<AttachFile> attachFiles = sqlSession.selectList("board.selectAttachFile", no);
    for (AttachFile f : attachFiles) {
      System.out.println(" => " + f);
    }
    System.out.println("------------------------------");
     */
    Board boards = sqlSession.selectOne("board.selectBoardWithAttachFile", no);
    System.out.println(boards);
    
    System.out.println("[첨부파일]");
    for (AttachFile f : boards.getAttachFiles()) {
      System.out.println(" => " + f);
    }
  }
}
