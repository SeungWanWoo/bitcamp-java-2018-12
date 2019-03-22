// 25단계 : business layer 추가
// => Command 객체에서 비지니스 로직을 분리하여 별도의 클래스로 정의한다.
// => 이런 구조로 바꾸면 비즈니스 로직의 재사용성을 높일 수 있다.
//
// 작업
// 1) BoardCommand에서 비즈니스 로직 분리
//    => BoardService 인터페이스 생성
//    => BoardServiceImpl 구현체 생성
// 2) PhotoBoardCommand에서 비즈니스 로직 분리
//    => PhotoBoardService 인터페이스 생성
//    => PhotoBoardServiceImpl 구현체 생성
// 3) MemberCommand에서 비즈니스 로직 분리
//    => MemberService 인터페이스 생성
//    => MemberServiceImpl 구현체 생성
// 4) LessonCommand에서 비즈니스 로직 분리
//    => LessonService 인터페이스 생성
//    => LessonServiceImpl 구현체 생성
//
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import com.eomcs.lms.handler.Response;

public class ServerApp {

  // 공통적으로 수행해야 한다 => 유지
  // 공용 객체를 보관하는 저장소
  HashMap<String, Object> context = new HashMap<>();

  // Command 객체와 그와 관련된 객체를 보관하고 있는 빈 컨테이너
  ApplicationContext iocContainer;

  // 클라이언트 요청을 처리한 메서드 정보가 들어 있는 객체
  RequestMappingHandlerMapping handlerMapping;

  public void service() throws Exception {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      // Spring IoC 컨테이너 준비
      iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);

      // Spring IoC 컨테이너에서 RequestMappingHandlerMapping 객체를 꺼난다.
      // 가능하면 이름을 주지말고 해당 클래스의 정보를 주는 편이 오류를 낼 확률이 적다.
      handlerMapping =
          (RequestMappingHandlerMapping) iocContainer.getBean(
              RequestMappingHandlerMapping.class);
      System.out.println("서버 실행 중...");
      while (true) {
        new RequestHandlerThread(serverSocket.accept()).start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();
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

        String request = in.readLine();

        RequestMappingHandler requestHandler = handlerMapping.get(request);

        if (requestHandler == null) {
          out.println("실행할 수 없는 명령입니다.");
          out.println("!end!");
          out.flush();
          return;
        }
        try {
          requestHandler.method.invoke( 
              requestHandler.bean, 
              new Response(in, out)
              );
        } catch (Exception e) {
          e.printStackTrace();
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
