// 연습 - 게시물 목록 출력
package ch25.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardListApp {
  // 다음과 같이 게시물 목록을 출력하라!
  // 게시물 번호를 내림차순으로 정렬하라.
  // ------------------------------
  // 번호, 제목, 등록일, 조회수
  // 2, aaa, 2019-1-1, 2
  // 1, bbb, 2018-12-31, 3
  // ------------------------------
  public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");

      try (Statement stmt = con.createStatement();){
        try (ResultSet rs = stmt.executeQuery(
            "select * from x_board order by board_id desc")) {
          System.out.println("번호, 제목, 등록일,    조회수");
          while (rs.next()) {
            System.out.printf("%d,  %s, %s, %d\n", 
                rs.getInt(1),
                rs.getString(2),
                rs.getDate(4), 
                rs.getInt(5));
          } // while()
        }
      } 
    } catch (Exception e) {
      System.out.println("DBMS에 연결되지 않음!");
      e.printStackTrace();
    }
  }
}
