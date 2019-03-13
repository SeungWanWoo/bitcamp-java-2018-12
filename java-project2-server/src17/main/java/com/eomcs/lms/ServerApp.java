// 17단계 : Mybatis의 SqlSession을 이용하여 DAO 구현체 자동 생성하기
// => SqlSession에는 DAO 인터페이스 구현체를 자동으로 생성해주는 메서드가 있다.
// => 이 기능을 사용하여 DAO 구현체를 생성하여 사용한다.
// => 단점:
//    - 자바 코드가 mybatis에 종속되는 문제가 있다.
//    - 다른 퍼시스턴스 프레임워크로 교체하면 전체 코드를 뜯어 고쳐야 한다.
// 작업:
// 1) Command 변경
//    => Command에 DAO를 직접 주입하는 대신에 SqlSessionFactory를 주입한다.
//    => DB 작업은 SqlSession으로부터 얻은 DAO 구현체를 사용한다.
//    => Mybatis는 기본 Auto_commit이 false이기 때문에 메서드가 완료되는 시점에
//       sqlSession으로부터 commit()을 처리해준다.
// 2) 기존의 DaoFactory 삭제
//    => 기존에 사용했던 DAO 구현체 자동 생성기를 제거한다.
//
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class ServerApp {

  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();

  // 공통적으로 수행해야 한다 => 유지
  // 공용 객체를 보관하는 저장소
  HashMap<String, Object> context = new HashMap<>();

  public void service() throws Exception {

    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      for (ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }
      System.out.println("서버 실행 중...");
      while (true) {
        new RequestHandlerThread(serverSocket.accept()).start();
      } // while

      // 애플리케이션을 종료할 때, 등록된 리스너에게 알려준다.
      // => 현재 while 문은 종료 조건이 없기 때문에 다음 문장을 실행할 수 없다.
      //    따라서 주석으로 처리한다.
      //      for (ApplicationContextListener listener : listeners) {
      //        listener.contextDestroyed(context);
      //      }
    } catch (Exception e) {
      e.printStackTrace();
    } // try ServerSocket
  }

  private void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();

    app.addApplicationContextListener(new ApplicationInitializer());

    // App 을 실행한다.
    app.service();
  }

  class RequestHandlerThread extends Thread {

    Socket socket;

    public RequestHandlerThread(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try (Socket socket = this.socket;
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream())) {

        // 클라이언트의 요청 읽기
        String request = in.readLine();

        // 클라이언트에게 응답하기
        Command commandHandler = (Command) context.get(request);

        if (commandHandler == null) {
          out.println("실행할 수 없는 명령입니다.");
          out.println("!end!");
          out.flush();
          return;
        }
        try {
          commandHandler.execute(in, out);
          
        } catch (Exception e) {
          out.printf("실행 오류! : %s\n", e.getMessage());
        }
        
        out.println("!end!");
        out.flush();

      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
        e.printStackTrace();
      } 
    }
  }
}
