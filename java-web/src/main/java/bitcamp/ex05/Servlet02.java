// HTTP 프로토콜 다루기 - MyHttpServlet 클래스 활용하기
package bitcamp.ex05;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex05/s2")
public class Servlet02 extends MyHttpServlet {
  
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 테스트
    // - http://localhost:8080/java-web/ex05/test02.html
    //
    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    // HttpServletRequest에는 HTTP 프로토콜의 요청 방식을 리턴하는 메서드가 있다.
    // => HttpServletRequest.getMethod() = 클라이언트가 어떤 방식으로 요청하는지 알아보는 메서드
    if(request.getMethod().equals("GET")) {
      out.println("GET 요청입니다.");
      
    } else if (request.getMethod().equals("POST")) {
      out.println("POST 요청입니다.");
      
    } else {
      out.println("이 서블릿이 다루지 못하는 요청 방식입니다.");
      
    }
  }
}