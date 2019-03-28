// 클라이언트가 보낸 데이터 읽기
package bitcamp.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ex04/s1")
public class Servlet01 extends GenericServlet {
  
  private static final long serialVersionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    
    // GET 요청
    // - 웹 브라우저에 URL을 입력한 후 엔터를 치면 GET 요청을 보낸다.
    // - 웹 페이지에서 링크를 클릭하면(자바스크립트 처리하지 않은 상태) GET 요청을 보낸다.
    // - 웹 페이지의 폼(method='GET'일 때)에서 전송 버튼을 클릭하면 GET 요청을 보낸다.
    //
    // 테스트
    // - /ex04/test01.html 실행
    //
    // 웹 브라우저가 보낸 데이터 읽기
    String name = req.getParameter("name");
    int age = Integer.parseInt(req.getParameter("age"));
    
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.printf("이름=%s\n", name);
    out.printf("나이=%d\n", age);
  }
}

// => HTTP 요청 형식
//    method sp request-URI sp http_version CRLF
//    *(general header | request header | entity header) CRLF
//    CRLF
//    message-body
//
// GET 요청 HTTP 프로토콜 예)
//
// => GET 요청은 데이터를 request-URI에 붙여서 보낸다.
// => request-URI 예)
//    /java-web/ex04/s1?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20
//    서블릿 URL: /java-web/ex04/s1
//    데이터(= Query String): name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20
// => 데이터 형식
//    이름=값&이름=값&이름=값
// => URL 인코딩
//    - 데이터를 보낼 때 7bit 네트워크 장비를 거치면 8bit 데이터가 깨진다.
//    - 이를 방지하고자 보내는 데이터를 7bit로 변환한다.
//    - 어떻게 변환한다? 원래 코드 값을 아스키(ASCII) 문자 코드로 변환한다.
//    - ASCII 코드는 7비트이기 때문에 데이터를 주고 받을 때 깨지지 않을 것이다.
//    - URL 인코딩이랑? 문자 코드의 값을 ASCII 코드화 시키는 것이다.
//      예) "ABC가각"을 전송한다고 가정하자.
//          "ABC가각"의 문자 코드(UTF-8) 값 : 414243EAB080EAb081 
//          ASCII 문자코드로 변환한다면: 
//          => 코드 값이 이미 ASCII라면 그대로
//              41 ==> 41
//              42 ==> 42
//          => 코드 값이 ASCII가 아니라면 각 4비트 값을 ASCII 문자라 간주하고 코드로 변환한다.
//             E ==> 'E' ==> 45
//             A ==> 'A' ==> 41
//            이렇게 변경한 후 URL인코딩 값임을 표시하기 위해 앞에 "%" 코드를 붙인다.
//             EA ==> 25 45 41 ==> 사람이 보는 문자로 표현한하면==>$EA
//
// => 예)
/*
GET /java-web/ex04/s1?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20 HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Pragma: no-cache
Cache-Control: no-cache
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,**;q=0.8
Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
빈줄
 */
// HTTP 응답 프로토콜
// => 형식
//    status-line(HTTP프로토콜 상태코드 간단한문구) CRLF
//    *(general header | response header | entity header) CRLF
//    CRLF
//    message-body
//
// => 예)
/*
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 27
Date: Thu, 28 Mar 2019 05:46:44 GMT
CRLF
이름=홍길동
나이=20 
 */
// URI (Uniform Resource Identifier)
// => 웹 자원의 위치를 가리키는 식별
// => 종류
//    - URL(Uniform Resource Locator) :
//      예) scheme:[//[user[:password]@]host[:port]][/path][?query][#fragment]
//          http://localhost:8080/java-web/ex04/s1?name=홍길동&age=20 
//
//    - URN(Uniform Resource Name) :
//      예) <URN> ::= "urn:" <NID> ":" <NSS>
//          urn:lex:eu:council:directive:2010-03-09;2010-19-UE
//      