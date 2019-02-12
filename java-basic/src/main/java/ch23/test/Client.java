package ch23.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);

      long sessionId = 0;

      while (true) {
      System.out.print("> ");
      String input = keyboard.nextLine();

      if (input.equalsIgnoreCase("quit")) {
        break;
      }
      
      try (
          Socket socket = new Socket("localhost", 8888);
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          PrintStream out = new PrintStream(socket.getOutputStream())) {
        System.out.println("서버와 연결되었음!");

        out.println(sessionId);
        out.println(input);
        out.flush();

        if (sessionId == 0) {
          sessionId = Long.parseLong(in.readLine());
          System.out.printf("발급받은 세션ID: %d\n", sessionId);
        }
        
        String str = in.readLine();
        System.out.println(str);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("서버와 연결이 끊겼음!");
    }
      keyboard.close();
  }
}
