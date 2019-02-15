package design_pattern.proxy.after.server;

import java.io.DataOutputStream;
import java.net.Socket;
import ch22.c.DataInputStream;

// 클라이언트 요청을 서버에 전달하고 서버의 작업 결과를 클라이언트에게 리턴하는 일을 한다.

// Stub은 실제 일을 하는 객체를 대행하기 때문에 같은 규칙에 따라 구현되어야 한다.
// => 클라이언트는 이 스텁 클래스가 실제 일을 하는 객체인양 그대로 사용한다.
// => 이렇게 실제 일을 하는 객체와 같은 규칙을 따르지만 메서드가 호출될 때
//    자신이 직접 일을 하지 않고, 실제 일을 하는 객체에게 위임한다.
//    이런 식으로 설계하는 것을 "프록시(Proxy) 디자인 패턴" 라고 한다.
public class CalculatorStub implements Calculator {
  private int compute(int a, int b, String op) throws Exception {
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

  @Override
  public int plus(int a, int b) {
    try {
      return compute(a, b, "+");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int minus(int a, int b) {
    try {
      return compute(a, b, "-");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int multiple(int a, int b) {
    try {
      return compute(a, b, "*");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int divide(int a, int b) {
    try {
      return compute(a, b, "/");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}