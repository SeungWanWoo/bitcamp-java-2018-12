package bitcamp;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// WebApplicationInitializer 구현체를 만드는 두 번째 방법
// => 인터페이스를 직접 구현하는 대신에 그 인터페이스를 구현한
//    AbstractAnnotationConfigDispatcherServletInitializer 상속받기
// 1) 서블릿 컨테이너(예: 톰캣 서버)를 시작한다.
// 2) spring-web-x.x.x.RELEASE.jar 파일에서
//    /META-INF/services/javax.servlet.ServletContainerInitializer 파일을 읽는다.
// 3) 이 파일에 등록된 클래스의 인스턴스를 생성한다.
//    => SpringServletContainerInitializer 인스턴스 생성
// 4) SpringServletContainerInitializer 객체에 대해 onStartup()을 호출한다.
//    => 호출할 때 WebApplicationInitializer를 구현한 클래스 목록을 넘겨준다.
// 5) SpringServletContainerInitializer 는
//    WebApplicationInitializer 구현체의 인스턴스를 만들고,
//    onStartup()을 호출한다.
//
public class WebApplicationInitializerImpl2 
extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // 스프링 Web MVC 프레임워크에서 DispatcherServlet이 사용할 IoC 컨테이너를 준비할 때
    // 이 메서드를 호출한다.
    // 이 메서드가 리턴한 Java config 클래스를 이용하여 IoC 컨테이너를 설정할 것이다.
    // Spring IoC 컨테이너의 설정 정보를 갖고 있는 Java config 클래스를 리턴한다.
    return new Class<?>[] {AppConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    // 스프링 프레임워크에서 DispatcherServlet을 등록할 때 이 메서드를 호출한다.
    // 이 메서드의 리턴 값이 URL 패턴으로 사용된다. 
    return new String[] { "/app7/*" };
  }
  
  @Override
  protected String getServletName() {
    // Spring Web MVC FrameWork는 
    // 웹 애플리케이션에 DispatcherServlet을 등록할 때 이 메서드를 호출한다.
    // 이 메서드의 리턴 값이 서블릿의 이름으로 사용된다.
    // 이 메서드를 오버라이딩 하지 않으면 기본 이름이 사용된다.
    // => 한 개만 등록할 것이라면 오버라이딩 하지 않아도 되지만,
    //    여러 개의 DispatcherServlet을 등록할 것이라면 오버라이딩 하여
    //    이름을 다르게 해야 한다.
    return "app7";
  }
  
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    // 이 메서드가 호출될 때 간단한 메시지를 출력하기 위해 오버라이딩 하였다.
    // 따라서 원래의 메서드를 반드시 호출해줘야 한다.
    System.out.println("WebApplicationInitializerImpl2.onStartup()....호출됨!");
    super.onStartup(servletContext);
  }
}

