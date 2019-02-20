// 연습 - 게시물 삭제
package ch25.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BoardDeleteApp {

  // 다음과 같이 게시물을 삭제하는 프로그램을 작성하라!
  // ------------------------------
  // 번호? 1
  // 삭제하였습니다.
  // (또는)
  // 해당 번호의 게시물이 존재하지 않습니다.
  // ------------------------------
  public static void main(String[] args) {

    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");

      try (Scanner keyboard = new Scanner(System.in);
          Statement stmt = con.createStatement()) {
        
        System.out.print("번호? ");
        int no = Integer.parseInt(keyboard.nextLine());
        try (ResultSet rs = stmt.executeQuery(
            "select * from x_board where board_id = " + no)) {

          if (rs.next()) {
            stmt.executeUpdate(
                "delete from x_board where board_id = " + no);
            System.out.println("삭제하였습니다.");
          } else {
            System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
          }
        }
      } 
    } catch (Exception e) {
      System.out.println("DBMS에 연결되지 않음!");
      e.printStackTrace();
    }

  }
}
