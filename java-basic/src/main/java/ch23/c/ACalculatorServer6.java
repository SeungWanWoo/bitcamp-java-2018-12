// 계산기 서버 만들기 : 5단계 - 클라이언트에서 종료할 전까지는 계속 연결된 상태로 요청 처리하기
package ch23.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ACalculatorServer6 {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("클라이언트의 연결을 기다리고 있음!");

      while (true) {
        Socket socket = null;
        BufferedReader in = null;
        PrintStream out = null;
        try {
          socket = serverSocket.accept();
          out = new PrintStream(socket.getOutputStream());
          in = new BufferedReader(
              new InputStreamReader(socket.getInputStream())); 
          out.println("계산기 서버에 오신 걸 환영합니다!");
          out.println("계산식을 입력하세요!");
          out.println("예) 23 + 7");
          out.println();
          
          while (true) {
            String request = in.readLine();
            if (request.equalsIgnoreCase("quit")) {
              out.println("안녕히 가세요!");
              out.flush();
              break;
            }
            
            try {
              String[] input = request.split(" ");
              int a = Integer.parseInt(input[0]);
              int b = Integer.parseInt(input[2]);
              String op = input[1];
              int result = 0;

              switch (op) {
                case "+": result = a + b; break;
                case "-": result = a - b; break;
                case "*": result = a * b; break;
                case "/": result = a / b; break;
                case "%": result = a % b; break;
                default:
                  out.printf("%s 연산자를 지원하지 않습니다.\n", op);
                  out.flush();
                  continue;
              }
              out.printf("결과는 %d 입니다.\n", result);
              out.flush();
            } catch (Exception e) {
              try {
                out.println("식의 형식이 잘못되었습니다.");
                out.flush();
              } catch (Exception e2) {}
            }
          }
        } catch (Exception e) {
          System.out.println("클라이언트와 통신 중 문제 발생");
          e.printStackTrace();
        } finally {
          try {in.close();} catch (Exception e) {}
          try {out.close();} catch (Exception e) {}
          try {socket.close();} catch (Exception e) {}
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}