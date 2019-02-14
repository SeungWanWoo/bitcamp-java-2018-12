// 12단계: Service 클래스에서 데이터 처리 코드를 별도의 클래스(DAO)로 분리
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.service.BoardService;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.MemberService;

public class ServerApp {
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  static BoardDao boardDao;
  static LessonDao lessonDao;
  static MemberDao memberDao;
  
  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(9545)) {
      System.out.println("서버 시작!");
      
      
      try {
      memberDao = new MemberDao("member.bin");
      memberDao.loadData();
      } catch (Exception e) {
        System.out.println("회원 데이터 로딩 중 오류 발생!");
      }
      
      try {
        lessonDao = new LessonDao("lesson.bin");
        lessonDao.loadData();
        } catch (Exception e) {
          System.out.println("수업 데이터 로딩 중 오류 발생!");
        }
      
      try {
        boardDao = new BoardDao("board.bin");
        boardDao.loadData();
        } catch (Exception e) {
          System.out.println("게시물 데이터 로딩 중 오류 발생!");
        }
      
      MemberService memberService = new MemberService(memberDao);
      BoardService boardService = new BoardService(boardDao);
      LessonService lessonService = new LessonService(lessonDao);
      
      while (true) {
        try(Socket socket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(
                socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(
                socket.getInputStream())) {
          memberService.init(in, out);
          boardService.init(in, out);
          lessonService.init(in, out);
          System.out.println("클라이언트와 연결되었음.");
          ServerApp.in = in;
          ServerApp.out = out;
          
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
    try {
      boardDao.saveData();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      //e.printStackTrace();
    }
    try {
      memberDao.saveData();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      //e.printStackTrace();
    }
    try {
      lessonDao.saveData();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      //e.printStackTrace();
    }
    
    
    out.writeUTF("종료함!");
    out.flush();
    
  }
  
}
