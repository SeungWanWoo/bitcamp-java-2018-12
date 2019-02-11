// Stateless 클라이언트 만들기
package ch23.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient {
  public static void main(String[] args) {

    Scanner keyboard = new Scanner(System.in);
    int sessionNum = 0;
    while (true) {
      System.out.print("> ");
      String input = keyboard.nextLine();
      if (input.equalsIgnoreCase("quit"))
        break;

      try (Socket socket = new Socket("localhost", 8888);
          PrintStream out = new PrintStream(socket.getOutputStream());
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()))) {
        System.out.println("서버와 연결됨! 서버에게 계산 작업을 요청함!");

        sessionNum = Integer.parseInt(in.readLine());
        System.out.println("서버로부터 번호를 부여받음 : " + sessionNum);

        out.println(sessionNum);
        out.flush();
        System.out.println("서버로 번호를 전송함");

        out.println(input);
        out.flush();

        String response = in.readLine();
        System.out.println(response);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("서버와 연결 끊음!");

    } // while
    keyboard.close();
  }
}











