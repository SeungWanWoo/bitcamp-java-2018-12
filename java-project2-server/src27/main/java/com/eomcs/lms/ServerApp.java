// 27단계 : XML 설정으로 트랜잭션 다루기
// => 애노테이션을 사용할 경우 각 서비스 클래스의 메서드에 대해 애노테이션을 붙여야 한다.
// => XML 설정을 사용하면 pointcut 규칙으로 트랜잭션을 적용할 메서드를 간단히 지정할 수 있다.
//    그래서 실무에서 많이 사용한다.
//
// 작업
// 1) 트랜잭션을 설정하는 XML 파일을 준비한다.
//    => tx-context.xml
// 2) 기존에 서비스 클래스에 붙인 @Transactional 애너테이션을 모두 제거한다.
//    => LessonServiceImpl의 delete()
//       PhotoBoardServiceImpl의 add(), update(), delete()
//       @Transactional을 제거한다.
// 3) AOP 라이브러리 추가한다.
//    => PlatformTransactionManager를 사용하여 트랜잭션을 다룰 때는 
//       개발자가 해당 메서드에 직접 코드를 삽입하기 때문에 AOP 기술을 사용할 일이 없다.
//    => @Transactional 애노테이션을 사용하여 트랜잭션을 다룰 때도
//       Spring IoC 컨테이너에서 Proxy 생성 기술을 사용하기 때문에 AOP 기술을 사용할 일이
//       없다.
//    => 그러나 XML에서 advice를 이용하여 트랜잭션을 다룰 때는
//       AOP 라이브러리를 사용하기 때문에 프로젝트에 추가해야한다.
//    => aspectj-weaver를 추가해라.
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
