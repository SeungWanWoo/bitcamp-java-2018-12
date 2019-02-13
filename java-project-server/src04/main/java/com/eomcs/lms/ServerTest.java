// 4단계 : 서버 실행 테스트.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  public static void main(String[] args) {
    try (Socket socket = new Socket("127.0.0.1", 9545);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
            socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음.");
      
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPassword("1111");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");
      // Member 객체를 서버로 Serialize하라
      out.writeObject(member);
      out.flush();
      
      // 또한 서버에서 serailize한 Member객체를 받아라.
      System.out.println(in.readObject());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버와 연결을 끊었음");
  }
}
