// 클라이언트가 보낸 데이터 읽기 - 파일 업로드 처리하기
package bitcamp.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ex04/s3")
public class Servlet03 extends GenericServlet {
  
  private static final long serialVersionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    // Post 요청으로 파일 전송하기
    // - 파일을 첨부하여 서버에 전송한다.
    // - multipart/form-data 형식으로 데이터를 전송하지 않으면
    //   첨부파일의 데이터는 받을 수 없다.
    //
    // 테스트
    // - /ex04/test03.html 실행
    //
    req.setCharacterEncoding("UTF-8");
    String name = req.getParameter("name");
    int age = Integer.parseInt(req.getParameter("age"));
    String photo = req.getParameter("photo");
    
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.printf("이름=%s\n", name);
    out.printf("나이=%d\n", age);
    
    // test03.html에서 파일을 전송할 때 multipart/form-data 형식이 아니기 때문에
    // 첨부 파일의 데이터를 받을 수 없다.
    out.printf("사진=%s\n", photo);
  }
}

// form의 기본 데이터 전송 형식은 "application/x-www-form-urlencoded"이다.
// 즉, "이름=값&이름=값" 형태로 전송한다.
// 다음 요청 프로토콜에서 "Content-Type"헤더를 확인해보라.
/*
POST /java-web/ex04/s3 HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Content-Length: 57
Pragma: no-cache
Cache-Control: no-cache
Origin: http://localhost:8080
Upgrade-Insecure-Requests: 1
Content-Type: application/x-www-form-urlencoded
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*
Referer: http://localhost:8080/java-web/ex04/test03.html
Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
*/