// 26단계 :  애노테이션으로 트랜잭션 다루기
// => @Transactional 애노테이션을 이용하여 트랜잭션을 사용할 수 있다.
//
// 작업
// 1) @Transactional 애노테이션을 처리할 객체를 스프링 IoC 컨테이너 설정에 등록한다.
//    => Java config 클래스에 @EnableTransactionManagement 애노테이션을 붙인다.
// 2) 트랜잭션을 적용할 서비스 클래스의 메서드에 @Transactional을 붙인다.
//    => 왜? DAO 메서드에 안 붙이고 Service 클래스의 메서드에 붙이는가?
//       - DAO 메서드들은 업무에 따라 단독으로 실행될 때가 있고,
//         다른 DB 작업과 묶여서 실행될 때가 있기 때문이다.
//       - 예를 들어 PhotoBoardDao의 delete() 메서드를 보라.
//         이 메서드는 단독으로 실행할 수도 있지만,
//         보통 PhotoFileDao의 delete()과 묶여서 실행될 때가 있다.
//       - 즉, DAO의 메서드는 업무에 따라 다른 DAO의 데이터 변경 메서드와 묶일 수 있기
//         때문이다.
//    => LessonServiceImpl의 delete()
//       PhotoBoardServiceImpl의 add(), update(), delete()
//       @Transactional을 붙인다.
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
      printBeans();
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
  
  private void printBeans() {
    String[] names = iocContainer.getBeanDefinitionNames();
    System.out.println("-----------------------------------------");
    for (String name : names) {
      System.out.printf("%s ===> %s\n",name,
          iocContainer.getBean(name).getClass().getName());
    }
    System.out.println("-----------------------------------------");
  }
}
