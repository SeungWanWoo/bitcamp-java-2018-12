// java.sql.Connection 객체
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test05 {
  public static void main(String[] args) {
    // DriverManager.getConnection()
    // => org.mariadb.jdbc.Driver.connect()
    // 
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");
      
    } catch (Exception e) {
      System.out.println("DBMS에 연결되지 않음!");
      e.printStackTrace();
    }
  }
}
