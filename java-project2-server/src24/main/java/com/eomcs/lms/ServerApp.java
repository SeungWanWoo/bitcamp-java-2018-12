// 24단계 : Spring Java config 정리, 
//         스프링의 BeanPostProcessor로 RequestMapping 애노테이션 처리
// => Spring Java config 클래스를 역할 별로 분리한다. 
//
// 작업
// 1) AppConfig에서 데이터베이스 관련 설정 분리
//    => DatabaseConfig 클래스 생성
// 2) AppConfig에서 Mybatis 관련 설정 분리
//    => MybatisConfig 클래스 생성
// 3) RequestMapping 애노테이션 처리
//    => RequestMappingAnnotationBeanPostProcessor 생성
// 4) RequestMappingHandlerMapping을 스프링 IoC 컨테이너 관리대상에 포함시킨다..
//    => @Component 애노테이션을 붙인다.
// 5) ServerApp에서 Spring IoC 컨테이너를 준비한다.
//    => ApplicationInitializer 클래스 삭제
// 6) ServerApp의 시작 종료를 보고받는 옵저버 제거
//    => ApplicationContextListener 인터페이스 제거
//    => ApplicationContextException 클래스 삭제
//    => ServerApp에서 해당 관련 코드 삭제
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
