// 2단계 : '규칙1'에 따라 클라이언트에게 게시물 목록 보내기
// 클라이언트와 서버 사이의 통신 규칙
// 규칙1) 단순한 명령어 전송과 실행 결과 수신
//
// [클라이언트]                                        [서버]
// 서버에 연결 요청        -------------->           연결 승인
// 명령(CRLF)              -------------->           명령처리
// 화면 출력               <--------------           응답(CRLF)
// 화면 출력               <--------------           응답(CRLF)
// 명령어 실행 완료        <--------------           !end!(CRLF)
//
// quit(CRLF)              -------------->           연결 끊기
//```
//규칙2) 사용자 입력을 포함하는 경우 
//```
//[클라이언트]                                        [서버]
//서버에 연결 요청        -------------->           연결 승인
//명령(CRLF)              -------------->           명령처리
//화면 출력               <--------------           응답(CRLF)
//사용자 입력 요구        <--------------           !{}!(CRLF)
//입력값(CRLF)            -------------->           입력 값 처리
//화면 출력               <--------------           응답(CRLF)
//사용자 입력 요구        <--------------           !{}!(CRLF)
//입력값(CRLF)            -------------->           입력 값 처리
//명령어 실행 완료        <--------------           !end!(CRLF)
//.
//.
//.
//quit(CRLF)              -------------->           연결 끊기

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
