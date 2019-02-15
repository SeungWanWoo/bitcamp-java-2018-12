package design_pattern.proxy.before2.client;

import java.io.DataOutputStream;
import java.net.Socket;
import ch22.c.DataInputStream;

// 클라이언트 요청을 서버에 전달하고 서버의 작업 결과를 클라이언트에게 리턴하는 일을 한다.
public class CalculatorClient {
  public int compute(int a, int b, String op) throws Exception {
    // 실제 계산 작업을 수행할 객체
    try (Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(
            socket.getOutputStream());
        DataInputStream in = new DataInputStream(
            socket.getInputStream())) {

      out.writeInt(a);
      out.writeInt(b);
      out.writeUTF(op);

      if (in.readUTF().equalsIgnoreCase("OK")) {
        return in.readInt();
      } else {
        throw new RuntimeException(in.readUTF());
      }
    } 
  }
}