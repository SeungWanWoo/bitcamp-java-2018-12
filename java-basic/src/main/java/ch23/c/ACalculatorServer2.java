// 계산기 서버 만들기 : 반복문을 적용하여 클라이언트 계속해서 받ㅇ
package ch23.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ACalculatorServer2 {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("클라이언트의 연결을 기다리고 있음!");

      while (true) {
        try (Socket socket = serverSocket.accept();
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))) {

          String[] input = in.readLine().split(" ");
          int a = Integer.parseInt(input[0]);
          int b = Integer.parseInt(input[2]);
          String op = input[1];

          out.printf("결과는 %d 입니다.\n", Calculate(a, b, op));
          out.flush();
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