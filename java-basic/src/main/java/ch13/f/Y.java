package ch13.f;

public class Y extends X{
  private int v2 = 200;
  
  public Y() { // 암시적으로 수퍼클래스인 X의 기본생성자를 호출하려했는데 없엉
    // 개발자야 너가 직접 선언을 행
    //super(); // 컴파일 오류! Y의 수퍼 클래스? X 클래스. 그런데 X 클래스는 기본 생성자가 없다!
    super(500); // 이런 경우 개발자가 호출할 수퍼 클래스의 생성자를 명시해야 한다.
    System.out.println("Y.Y()");
  }
  
  public void m2() {
    System.out.printf("Y.v2 = %d\n", this.v2);
  }
}
