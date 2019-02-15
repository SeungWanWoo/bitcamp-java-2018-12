package design_pattern.proxy.after.server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import ch22.c.DataInputStream;

public class CalculatorSkel {
  public static void main(String[] args) {
    // 실제 계산 작업을 수행할 객체
    Calculator calc = new CalculatorImpl();
    
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행 중...");
      
      while (true) {
        try (Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(
                socket.getInputStream());
            DataOutputStream out = new DataOutputStream(
                socket.getOutputStream())) {
          
          int a = in.readInt();
          int b = in.readInt();
          String op = in.readUTF();
          
          switch (op) {
            
            case "+":
              // 클라이언트에서 요청을 처리하기 위해 실제 작업을 수행하는 객체를 사용한다.
              out.writeUTF("OK");
              out.writeInt(calc.plus(a, b));
              break;
            case "-": 
              out.writeUTF("OK");
              out.writeInt(calc.minus(a, b));
              break;
            case "*":
              out.writeUTF("OK");
              out.writeInt(calc.multiple(a, b));
              break;
            case "/":
              out.writeUTF("OK");
              out.writeInt(calc.divide(a, b));
              break;
            default:
              out.writeUTF("FAIL");
              out.writeUTF("해당 연산자를 지원하지 않습니다.");
          }
          out.flush();
          
        } catch (Exception e) {
          System.out.println("클라이언트 요청 처리 중에 오류 발생!");
        }
      } // while
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
