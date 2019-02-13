// 4단계 : 서버 실행 테스트.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerTest {
  
  static ObjectOutputStream out;
  static ObjectInputStream in;
  
  public static void main(String[] args) {
    try (Socket socket = new Socket("127.0.0.1", 9545);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
            socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음.");
      ServerTest.in = in;
      ServerTest.out = out;
      
      new MemberTest(out, in).test();
      System.out.println("------------------------------------");
      
      new LessonTest(out, in).test();
      System.out.println("------------------------------------");
      
      new BoardTest(out, in).test();
      System.out.println("------------------------------------");
      
      quit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버와 연결을 끊었음");
  }
  
  static void quit() throws Exception {
    out.writeUTF("quit");
    out.flush();
    System.out.println(in.readUTF());
  }
}

