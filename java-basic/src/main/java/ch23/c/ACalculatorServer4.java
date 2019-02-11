// 계산기 서버 만들기 : 4단계 : 입력값 예외 처리
package ch23.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ACalculatorServer4 {
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

          String[] input = in.readLine().split(" ");
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