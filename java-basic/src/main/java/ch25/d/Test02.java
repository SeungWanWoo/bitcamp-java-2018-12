// 트랙잭션 다루기 - commit
package ch25.d;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test02 {

  public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      // 1) 트랜잭션 시작 - 커넥션 객체의 auto_commit을 false로 지정한다.
      con.setAutoCommit(false);
      
      // 2) 데이터 변경 작업을 수행 - 여러 개의 insert, update, delete 작업 수행
      for (int i = 0; i < 3; i++) {
        try (PreparedStatement pstmt = con.prepareStatement(
            "insert into x_board(title, contents) values(?,?)")) {
          
          pstmt.setString(1, "aaa");
          pstmt.setString(2, "bbb");
          pstmt.executeUpdate();
          System.out.println("입력 성공!");
        }
      }
      
      // 3) 트랜잭션을 종료 - 서버에 요청한 작업을 처리할 것을 명령한다.
      // => commit()을 호출하지 않으면 서버에 요청한 데이터 변경 작업은 자동 취소된다.
      con.commit();
      
      // 주의!
      // => 트랙잭션은 커넥션 단위로 관리된다.
      // => 즉, 같은 커넥션을 사용하면 서로 영향을 끼치게 된다. 
      
    } catch (Exception e) {
      System.out.println("DBMS에 연결되지 않음!");
      e.printStackTrace();
    }
  }
}
