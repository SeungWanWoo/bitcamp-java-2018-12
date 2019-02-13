// 9단계 : 클라이언트 요청을 처리하는 서비스 클래스를 별도의 패키지로 분류하기
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.MemberService;

public class ServerApp {
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(9545)) {

      System.out.println("서버 시작!");
      while (true) {
        try(Socket socket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(
                socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(
                socket.getInputStream())) {
          System.out.println("클라이언트와 연결되었음.");
          ServerApp.in = in;
          ServerApp.out = out;
          
          MemberService memberService = new MemberService(in, out);
          LessonService lessonService = new LessonService(in, out);
          BoardService boardService = new BoardService(in, out);
          // 클라이언트에서 serialize해서 보내온 Member객체의 내용을 출력하라.
          while (true) {
            String request = in.readUTF();
            System.out.println(request);
            
            if (request.startsWith("/member/")) {
              memberService.execute(request);
              
            } else if (request.startsWith("/lesson/")) {
              lessonService.execute(request);
              
            } else if (request.startsWith("/board/")) {
              boardService.execute(request);
              
            } else if (request.equals("quit")) {
              quit();
              break;
              
            } else {
              out.writeUTF("FAIL");
            }
            out.flush();
          } // while
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println("클라이언트와 연결을 끊었음.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  static void quit() throws Exception {
    out.writeUTF("종료함!");
    out.flush();
  }
  
}
