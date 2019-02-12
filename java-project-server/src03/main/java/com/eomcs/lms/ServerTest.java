// 2단계 : 서버 실행 테스트.
package com.eomcs.lms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTest {
  public static void main(String[] args) {
    try (Socket socket = new Socket("localhost", 8888);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream())) {
      System.out.println("서버와 연결되었음.");
      out.println("Hello!");
      out.flush();
      
      String str = in.readLine();
      System.out.println(str);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버와 연결을 끊었음");
  }
}
