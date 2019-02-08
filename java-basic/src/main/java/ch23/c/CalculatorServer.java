// 계산기 서버 만들기
package ch23.c;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorServer {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("클라이언트의 연결을 기다리고 있음!");

      try (Socket socket = serverSocket.accept();
          PrintWriter out = new PrintWriter(socket.getOutputStream());
          Scanner in = new Scanner(socket.getInputStream())) {

        out.println("계산기 서버에 오신 걸 환영합니다!");
        out.flush();
        out.println("계산식을 입력하세요!");
        out.flush();
        out.println("예) 23 + 7");
        out.flush();
        while (true) {
          System.out.println("반복문 진입");
          try {
            String command = in.nextLine();
            if (command.equals("quit")) {
              out.println("안녕히 가세요!");
              break;
            }
            String str = in.nextLine();
            System.out.println(str);
            String[] commandClient = command.split(" ");
            int an = Integer.parseInt(commandClient[0]);
            String op = commandClient[1];
            int bn = Integer.parseInt(commandClient[2]);
            int result = Calculate(an, bn, op);
            if (result != 0) {
              out.println("결과는 " + result + "입니다.");
              out.flush();
            }
            out.println(op + " 연산자를 지원하지 않습니다.");
            out.flush();
          } catch (NumberFormatException e) {
            out.println("식의 형식이 잘못되었습니다.");
            out.flush();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static int Calculate(int a, int b, String op) {
    switch (op) {
      case "+":
        return a + b;
      case "-":
        return a - b;
      case "*":
        return a * b;
      case "/":
        return a / b;
      case "%":
        return a % b;
      default:
        return 0;
    }
  }
}