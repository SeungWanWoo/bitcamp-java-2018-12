package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;

// 커넥션을 빌려주고 리턴 받는 일을 한다.
public class ConnectionFactory {
  
  // Connection 객체를 현재 Thread에 보관할 수 있도록 보자기를 준비한다.
  static ThreadLocal<Connection> conLocal = new ThreadLocal<>();
  
  public static Connection create() {
    try {
      Connection con = conLocal.get();
      // 현재 스레드 주머니에 들어있는 Connection 객체 꺼내기
      if (con == null) {
      con = DriverManager.getConnection(
              "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
      con.setAutoCommit(false);
      conLocal.set(con); // 새로 생성한 커넥션 객체를 현재 스레드 주머니에 일단 보관한다.
      } 
      return con;
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
