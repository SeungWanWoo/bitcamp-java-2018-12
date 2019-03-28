package design_pattern.chain_of_responsibility;

public class TerminalHandler extends AbstractHandler {

  @Override
  public void handle(int a, int b, String op) {
    // 이 핸들러는 끝을 연결의 끝을 표현하기 위해 만든 것이다.
    // 아무런 일을 하지 않는다.
    
    System.out.println("해당 연산자를 지원하지 않습니다.");
  }
}
