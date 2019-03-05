package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import com.eomcs.util.ConnectionFactory;

// 추상 클래스의 목적?
// => 서브 클래스에게 필드나 메서드를 상속해 주는 용도.
// => 직접 사용하지 못한다.
//
public abstract class AbstractCommand implements Command {
  @Override
  public void execute(BufferedReader in, PrintWriter out) {
    try {
      execute(new Response(in, out));
      // 현재 스레드에 보관된 Connection 객체를 꺼낸다.
      // 그리고 그 Connection 객체를 통해 수행했던 모든 데이터 변경 작업을 Commit한다.
      ConnectionFactory.create().commit();
    } catch (Exception e) {
      // 예외가 발생하면 Connection을 통해 데이터 변경 작업을 했던 것을 모두 취소한다.
      try {
        ConnectionFactory.create().rollback();
      } catch (Exception e2) {
        // rollback 하다가 발생된 예외는 달리처리할 방법이 없다. 그냥 예외가
        // 발생하더라도 무시한다.
      }
      out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }

  public void execute(Response response) throws Exception {
    // 서브 클래스에서 오버라이딩 해야할 메서드이다.
    // 그러나 서브 클래스가 반드시 오버라이딩 하도록 강요하지 않기 위해 추상 메서드로 선언
    // 하지 않는다.
    // 왜? 서브 클래스는 Command의 execute(BufferedReader, PrintWriter)를
    // 오버라이딩 할 지도 모르기 때문이다.
    //
  }
}
