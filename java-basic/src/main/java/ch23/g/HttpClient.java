// HTTP 클라이언트 만들기
package ch23.g;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class HttpClient {
  public static void main(String[] args) {
    // HTTP 프로토콜
    // => 웹서버와 웹브라우저 사이의 통신 규칙이다.
    // => Stateless 방식으로 동작한다.
    //
    // HTTP 요청 규칙
    // ----------------------------------------
    // GET [URL] [프로토콜/버전](CRLF)
    // Host: [서버주소] (CRLF)
    // (CRLF)
    // ----------------------------------------
    try (Socket socket = new Socket("www.skhynix.com", 80);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {
      
      // HTTP 클라이언트의 최소 조건
      out.println("GET / HTTP/1.1");
      out.println("Host: www.skhynix.com");
      //out.println("Connection: keep-alive");
      //out.println("Pragma: no-cache");
      //out.println("Cache-Control: no-cache");
      //out.println("Upgrade-Insecure-Requests: 1");
      //out.println("User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
      //out.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
      //out.println("Accept-Encoding: gzip, deflate");
      //out.println("Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
      out.println();
      out.flush();
      
      while (true) {
        System.out.println(in.nextLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
