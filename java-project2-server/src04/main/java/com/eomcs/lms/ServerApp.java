// 4단계 : Command를 다루는 클래스에 대해 리팩토링 수행
// => Command를 미리 구현한 AbstractCommand 클래스 생성
// => 클라이언트의 데이터를 읽고 출력하는 것을 담당할
//    Request와 Response 클래스 생성
// => 기존 Command 구현체는 직접 Command 인터페이스를 구현하는 대신에
//    AbstractCommand를 상속 받도록 변경.
// => 또한 execute(BufferedReader, PrintWriter) 대신에
//    execute(Request, Response)를 오버라이딩 하도록 변경
// 이렇게 하는 이유?
// => 클라이언트와 데이터를 주고 받는 것을 보다 쉽게하기 위해

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
