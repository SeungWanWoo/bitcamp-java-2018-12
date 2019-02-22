// 5단계 : 회원관리에 검색 기능을 추가한다. 그래도 클라이언트는 변경할 필요가 없다.
// => Application Server 아키텍처의 장점은
//    서버의 기능을 변경하더라도 클라이언트를 변경할 필요가 없다는 것이다.
// => 즉 다시 클라이언트를 배포할 필요가 없다.
// 작업
// 1) MemberDao에 키워드로 회원정보를 찾는 findByKeyword()를 추가한다.
// 2) MemberSearchCommand 클래스를 생성한다.
// 3) ApplicationInitializer에 MemberSearchCommand를 등록한다.
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

  public void service() throws Exception {

    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      // 공통적으로 수행해야 한다 => 유지
      HashMap<String, Object> context = new HashMap<>();

      for (ApplicationContextListener listener : listeners) {
        listener.contextInitialized(context);
      }
      System.out.println("서버 실행 중");
      while (true) {

        try (Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())) {

          // 클라이언트의 요청 읽기
          String request = in.readLine();

          if (request.equalsIgnoreCase("stop")) {
            System.out.println("종료 합니다.");
            break;
          }

          // 클라이언트에게 응답하기
          Command commandHandler = (Command) context.get(request);
          if (commandHandler == null) {
            out.println("실행할 수 없는 명령입니다.");
            out.println("!end!");
            out.flush();
            continue;
          }

          commandHandler.execute(in, out);
          out.println("!end!");
          out.flush();
          
        } catch (Exception e) {
          System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
        } 

        // try (Socket)
      } // while

      for (ApplicationContextListener listener : listeners) {
        listener.contextDestroyed(context);
      }
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
}
