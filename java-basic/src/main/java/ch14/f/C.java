// 다형적 변수의 규칙
package ch14.f;

public class C extends B {
  
  @Override
  public void m1() {
    System.out.println("C.m1()");
  }
  
  public void m3() {
    System.out.println("C.m3()");
  }
}
