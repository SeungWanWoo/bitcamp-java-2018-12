// 14단계 : Mybatis 퍼시스턴스 프레임워크에 트랜잭션 관리자 도입하기
// => 트랜잭션 관리자를 도입하여 여러 개의 데이터 변경 작업을 한 단위읭 작업으로 묶는다.
//
// 작업:
// 1) SqlSessionFactoryProxy 생성
//    => Mybatis가 제공하는 SqlSessionFactory를 대행한다.
//    => 트랜잭션을 위한 SqlSession 객체를 준비하고 해제하는 일을 한다.
//    => 트랜잭션을 시작하면 스레드 보관소에 SqlSession 객체를 보관한다.
//       그래서 스레드가 수행하는 모든 데이터 변경 작업을 한 SqlSession 객체가 다루게 한다.
//
// 2) SqlSessionProxy 생성
//    => Mybatis가 제공하는 SqlSession 객체를 대행한다.
//    => 트랜잭션 중에는 close()를 하지 않는다.
//
// 3) TransactionManager 생성
//    => 트랜잭션을 시작시키고 완료시키는 일을 한다.
//    => SqlSession 객체를 통해 데이터 변경 작업들을 commit()하고 rollback()하는 일을
//       한다.
//
// 4) DAO 변경
//    => DAO에서 데이터 변경(insert/update/delete) 후에 commit() 하지 말
//    => 트랜잭션을 사용할 때는 DAO에서 commit() 하는 것이 아니다.
//       왜? 여러 DAO의 작업을 한 단위로 묶어서 commit()해야 하기 때문이다.
//    => 그러면 트랜잭션을 사용하지 않을 때는 auto_commit이 true인 SqlSession을
//       사용하기 때문에 아무런 문제가 없다.
//
// 5) Command 변경
//    => 트랜잭션을 사용해야하는 Command에 대해서는 TransactionManager를 주입하라!
//    => 예) PhotoBoardAddCommand, PhotoBoardDeleteCommand, 
//          PhotoBoardUpdateCommand, LessonDeleteCommand
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
