// 멀티파트 파일 업로드 처리하기 - apache 라이브러리 사용
package bitcamp.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ex04/s4")
public class Servlet04 extends GenericServlet {
  
  private static final long serialVersionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    // 멀티파트 형식으로 보낸 첨부 파일 데이터를 읽는 방법
    // => Content-Type 헤더에 지정한 구분자를 사용하여 각 파트를 분리한 다음
    //    데이터를 읽는다.
    // => 문제는 기존에 제공하는 getParameter()로는 멀티파트 형식으로 전송된
    //    데이터를 읽을 수 없다.
    // => 방법?
    //    1) 개발자가 직접 멀티파트 형식을 분석하여 데이터를 추출한다.(X)
    //    2) 외부 라이브러리를 사용한다.
    //       - Apache.org 사이트에서 제공하는 멀티파트 데이터 분석기를 사용한다.
    //       - 실무에서 예전에 많이 사용했다.
    //    3) Servlet 3.0 부터 제공하는 기능을 이용한다.
    //       - 실무에서 사용하던 그대로 사용하는 바람에
    //         Servlet 에서 제공하는 방법을 개발자들이 잘 사용하지 않는다.
    //       - 그것이 문제다!
    //    4) Spring WebMVC를 사용한다면 해당 프레임워크에서 제공하는 기능을 이용한다.
    //       - Spring WebMVC를 설명할 때 실습하겠다.
    // 테스트
    // - /ex04/test04.html 실행
    //
    req.setCharacterEncoding("UTF-8");
    
    // getParameter()가 null을 리턴한다는것을 확인하기 위해
    // 파라미터 모두 String으로 받는다.
    String name = req.getParameter("name");
    String age = req.getParameter("age");
    String photo = req.getParameter("photo");
    
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.printf("이름=%s\n", name);
    out.printf("나이=%s\n", age);
    
    // test03.html에서 파일을 전송할 때 multipart/form-data 형식이 아니기 때문에
    // 첨부 파일의 데이터를 받을 수 없다.
    out.printf("사진=%s\n", photo);
  }
}

// <form> 태그의 enctype을 "multipart/form-data" 로 설정하면,
// 웹 브라우저가 데이터를 전송할 때 다음과 같은 형식으로 보낸다.
// 요청 프로토콜에서 Content-Type을 확인하라.
// 
/*
POST /java-web/ex04/s4 HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Content-Length: 6315
Pragma: no-cache
Cache-Control: no-cache
Origin: http://localhost:8080
Upgrade-Insecure-Requests: 1
Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryOEYFBxqDR0BCYAPa
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,**;q=0.8
Referer: http://localhost:8080/java-web/ex04/test04.html
Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
빈줄
*/