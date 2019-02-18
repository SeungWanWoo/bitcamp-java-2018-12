// 14단계 : DAO애 프록시 패턴 적용하기
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import com.eomcs.lms.dao.BoardDaoImpl;
import com.eomcs.lms.dao.LessonDaoImpl;
import com.eomcs.lms.dao.MemberDaoImpl;
import com.eomcs.lms.service.BoardDaoSkel;
import com.eomcs.lms.service.LessonDaoSkel;
import com.eomcs.lms.service.MemberDaoSkel;
import com.eomcs.lms.service.Service;

// DAO 프록시 패턴 적용
// => 클라이언트에서 서버쪽에 DAO를 마치 직접 사용하는 것처럼 만들기
// => 작업
// 1) DAO 클래스에서 인터페이스를 추출하여 정의한다.
//    => 예) BoardDao 인터페이스 정의
// 2) 기존 클래스를 인터페이스 구현하도록 변경한다.
//    => 예) BoardDaoImpl 클래스로 변경
// 3) BoardDaoImpl 객체를 서버쪽에서 대행할 클래스를 만든다.
//    => 예) BoardService의 이름을 BoardSkel로 변경한다.
// 4) 칼라이언트 쪽 BoardService
public class ServerApp {

  static BoardDaoImpl boardDao = null;
  static LessonDaoImpl lessonDao = null;
  static MemberDaoImpl memberDao = null;

  public static void main(String[] args) {

    try {
      boardDao = new BoardDaoImpl("board.bin");
      boardDao.loadData();
    } catch (Exception e) {
      System.out.println("게시물 데이터 로딩 중 오류 발생!");
    }

    try {
      lessonDao = new LessonDaoImpl("lesson.bin");
      lessonDao.loadData();
    } catch (Exception e) {
      System.out.println("수업 데이터 로딩 중 오류 발생!");
    }

    try {
      memberDao = new MemberDaoImpl("member.bin");
      memberDao.loadData();
    } catch (Exception e) {
      System.out.println("회원 데이터 로딩 중 오류 발생!");
    }

    HashMap<String, Service> serviceMap = new HashMap<>();

    serviceMap.put("/board/", new BoardDaoSkel(boardDao));
    serviceMap.put("/lesson/", new LessonDaoSkel(lessonDao));
    serviceMap.put("/member/", new MemberDaoSkel(memberDao));

    Set<String> keySet = serviceMap.keySet();

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");


      while (true) {
        try(Socket socket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(
                socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(
                socket.getInputStream())) {
          System.out.println("클라이언트와 연결되었음.");

          // 클라이언트에서 serialize해서 보내온 Member객체의 내용을 출력하라.
          String request = in.readUTF();
          System.out.println(request);

          String serviceName = null;
          for (String key : keySet ) {
            if(request.startsWith(key)) {
              serviceName = key;
              break;
            }
          } //for

          if (serviceName == null) {
            out.writeUTF("FAIL");

          } else {
            Service service = serviceMap.get(serviceName);
            service.execute(request, in, out);
          } 
          out.flush();
        } catch (Exception e) {
          e.printStackTrace();
        } 
        System.out.println("클라이언트와 연결을 끊었음.");
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
  } // while
}
