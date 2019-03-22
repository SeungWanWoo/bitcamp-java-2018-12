// 29단계 : Log4j 2.x 적용하기
// => 애플리케이션을 실행하는 중에 내부 상태를 확인할 목적으로 
//    기록을 남기는 것을 로깅(logging)이라 한다.
// => 로깅은 애플리케이션을 실행하는 콘솔창으로 출력할 수도 있고,
//    파일이나 네트웍으로도 출력할 수 있다.
// => 보통 실무에서는 파일로 기록을 남긴다.
// => 로깅 작업을 도와주는 대표적인 라이브러리가 log4j 이다.
//    출력 레벨에 따라 로깅을 조절할 수 있어 편리하다.
//
// 작업
// 1) log4j 2.x 라이브러리를 추가한다.
//    => mvnrepository.com에서 log4j를 검색한다.
//    => build.gradle에 라이브러리 추가한다.
//    => 'gradle eclipse' 실행한다.
//    => eclipse 프로젝트 refresh한다.
// 2) Log4j2 설정 파일을 준비한다.
//    => CLASSPATH 루트 패키지에 log4j2.xml 파일을 생성한다.
//    => 예) src/main/resources/log4j2.xml
// 3) Mybatis에서 사용할 로깅 라이브러리 지정하기
//    => SqlSessionFactory 객체를 생성할 때 어떤 로깅 라이브러리를 사용할 지 지정한다.
//    => MybatisConfig.java에서 SqlSessionFactory 생성하는 메서드 안에 다음 코드 추가.
//       LogFactory.useLog4J2Logging();
//
package com.eomcs.lms;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;
import com.eomcs.lms.handler.Response;

public class ServerApp {
  // 보통 클래스에서 사용할 로그 출력 객체는 클래스의 스태틱 멤버로 선언한다.
  final static Logger logger = LogManager.getLogger(ServerApp.class);
  // 공통적으로 수행해야 한다 => 유지
  // 공용 객체를 보관하는 저장소
  HashMap<String, Object> context = new HashMap<>();

  // Command 객체와 그와 관련된 객체를 보관하고 있는 빈 컨테이너
  ApplicationContext iocContainer;

  // 클라이언트 요청을 처리한 메서드 정보가 들어 있는 객체
  RequestMappingHandlerMapping handlerMapping;

  public void service() throws Exception {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      logger.info("서버 실행 중...");
      
      // Spring IoC 컨테이너 준비
      iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans();
      // Spring IoC 컨테이너에서 RequestMappingHandlerMapping 객체를 꺼난다.
      // 가능하면 이름을 주지말고 해당 클래스의 정보를 주는 편이 오류를 낼 확률이 적다.
      handlerMapping =
          (RequestMappingHandlerMapping) iocContainer.getBean(
              RequestMappingHandlerMapping.class);
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
      logger.info("클라이언트 연결 되었음");
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
        logger.error("명령어 실행 중 오류 발생 : " + e.toString());
        StringWriter strWriter = new StringWriter();
        PrintWriter out = new PrintWriter(strWriter);
        
        e.printStackTrace(out);
        logger.error(strWriter.toString());
      }
      logger.info("클라이언트와 연결 종료.");
    }
  }
  
  private void printBeans() {
    String[] names = iocContainer.getBeanDefinitionNames();
    // 개발하는 동안 참고할 로그는 보통 debug 등급으로 출력한다.
    logger.debug("-----------------------------------------");
    for (String name : names) {
      logger.debug(String.format("빈 생성 됨 (객체명=%s, 클래스명=%s)",name,
          iocContainer.getBean(name).getClass().getName()));
    }
    logger.debug("-----------------------------------------");
  }
}
