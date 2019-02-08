// 계산기 클라이언트 만들기 : +, -, *, /, % 연산자는 지원한다.
package ch23.c;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// 실행 예:
/*
 * - 클라이언트가 계산기 서버에 접속한 후
 * 
 * 계산기 서버에 오신 걸 환영합니다! <== 서버의 응답
 * 계산식을 입력하세요! <== 서버의 응답
 * 예) 23 + 7 <== 서버의 응답
 * > 23 + 7 <== 사용자의 입력. '>'문자는 클라이언트에서 출력한다.
 * 결과는 30 입니다. <== 서버의 응답
 * > 23 ^ 7 <== 사용자의 입력
 * ^ 연산자를 지원하지 않습니다. <== 서버의 응답
 * > ok + yes <== 사용자의 입력
 * 식의 형식이 잘못되었습니다. <== 서버의 응답
 * > quit <== 사용자의 입력
 * 안녕히 가세요! <== 서버의 응답
 */
public class CalculatorClient {
  public static void main(String[] args) {
    try (Scanner keyboard = new Scanner(System.in);
        Socket socket = new Socket("localhost", 8888);
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream())) {

      String response = in.nextLine();
      System.out.println(response);
      response = in.nextLine();
      System.out.println(response);
      response = in.nextLine();
      System.out.println(response);
      
      while (true) {
        System.out.print("> ");
        String command = "";
        command = keyboard.nextLine();
        if (command.equalsIgnoreCase("quit")) {
          out.println("quit");
          break;
        }
        out.println(command);
        out.flush();
        out.println("클라이언트에서 데이터를 전송하였습니다.");
        out.flush();
        String result = in.nextLine();
        System.out.println(result);
        String tresh = in.nextLine();
        System.out.println(tresh);
      }
    } catch (Exception e) {
    }
  }
}
