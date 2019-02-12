package ch23.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
  public static void main(String[] args) {
    HashMap<Long, Integer> resultMap = new HashMap<>();
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      while (true) {
        try (Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream())) {
          System.out.println("클라이언트와 연결됨!");
          long sessionId = Long.parseLong(in.readLine());
          int result = 0;
          boolean isNewSessionId = false;
          System.out.printf("세션ID: %d!\n", sessionId);

          if (sessionId == 0) {
            sessionId = System.currentTimeMillis();
            isNewSessionId = true;
          } else {
            result = resultMap.get(sessionId);
          }

          String[] str = in.readLine().split(" ");

          int b = 0;
          String op = null;

          try {
            op = str[0];
            b = Integer.parseInt(str[1]);
          } catch (Exception e) {
            out.println("식의 형식이 바르지 않습니다.");
            out.flush();
            continue;
          }

          switch(op) {
            case "+": result += b; break;
            case "-": result -= b; break;
            case "*": result *= b; break;
            case "/": result /= b; break;
            case "%": result %= b; break;
            default:
              out.printf("%s 연산자를 사용할 수 없습니다.\n");
              out.flush();
              continue;
          }
          resultMap.put(sessionId, result);

          if (isNewSessionId) {
            out.println(sessionId);
          }
          String output = String.format("결과 값은 %d 입니다.", result);
          out.println(output);
          out.flush();
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println("클라이언트와 연결이 끊김!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  } 
}
