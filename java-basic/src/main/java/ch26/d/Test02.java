// select 결과 값을 자바 인스턴스에 담는 규칙
package ch26.d;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test02 {
  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/d/mybatis-config.xml");
    
    SqlSessionFactory sqlSessionFactory = 
        new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    // mybatis의 SqlSession이 "board.select3" SQL 문을 실행한 후
    // 그 결과는 SQL Mapper 파일에 지정된 대로 ch26.d.Board2 인스턴스에 담아 리턴한다.
    // 그런데 다음과 같이 전혀 다른 타입의 레퍼런스로 받으려하면 오류가 발생한다.
    // Board board = sqlSession.selectOne("board.select3");
    // 위 문장은 Board 객체에 Board2의 select된 결과 값을 받으려고 한 것이다.
    // 따라서 서로 타입이 맞지 않으므로 CastCade오류가 발생한다.
    Board2 board = sqlSession.selectOne("board.select3"); // OK!
    System.out.println(board);
    
    // MyBatis에서 select 결과를 자바 인스턴스에 담을 때 규칙
    // => 컬럼 이름과 같은 property를 찾아서 값을 담는다.
    // => 자바에서 'property'란?
    //    setter/getter를 가리키는 용어다.
    //    예) setNo(), getNo()
    // => property 이름?
    //    setter/getter 이름에서 set/get 이름을 제거한 후, 나머지 이름이다.
    //    단, 첫 알파벳을 소문자로 바꾼 이름이다.
    //    예) setNo()/getNO() => no
    //       setCreadtedDate()/getCreatedDate() => createdDate
    // => 주의!
    //    필드 이름이 property 이름이 아니다!
    
    // 결론!
    // Board2 클래스의 property 이름을 보면 no, title, contents, 
    // createdDate, viewCount 가 있다.
    // 이 property 중에서 컬럼 이름과 같은 property는  title, contents 이다.
    // MyBatis는 컬럼이름과 같은 이름을 가진 property(title,contents)에 대해서만
    // 결과 값을 넣어 준 것이다.
    // 그래서 Board2 객체를 출력해보면 title과 contents 값만 정상적으로 출력된다.
    //
    // 해결책?
    // => 컬럼 이름과 property 명을 같게 하면 되지 않겠는가?
    //    - DB의 이름 짓는 규칙과 자바의 이름 짓는 규칙은 다르다.
    //    - DBMS 마다 또 이름 짓는 규칙이 다르다.
    //    - 그래서 자바에서 property 이름을 지을 때 특정 DBMS에 종속되게 지어서는 안된다.
    //    - 자바는 자바의 명명 규칙을 따르고, DB는 DBMS의 명명 규칙을 따르면 된다.
    // => 그러면 어떻게 하자는 것인가?
    //    - select할 때 컬럼의 별명을 자바 property 이름과 같게 하라!
  }
}
