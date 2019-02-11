// 계산기 서버 만들기 : 6단계 - 리펙토링
package ch23.c;

import java.net.ServerSocket;

public class ACalculatorServer8 {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {

      System.out.println("클라이언트의 연결을 기다리고 있음!");

      while (true) {
        try {
        new CalcultatorProcessor(serverSocket.accept()).execute();
        
        } catch (Exception e) {
          System.out.println("클라이언트와 통신 중에 오류 발생");
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}