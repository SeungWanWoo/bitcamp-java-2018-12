// 다형적 변수의 규칙
package ch14.f;

public class B extends A{
  
  @Override
  public void m1() {
    System.out.println("B.m1()");
  }
  
  public void m2() {
    System.out.println("B.m2()");
  }
}
