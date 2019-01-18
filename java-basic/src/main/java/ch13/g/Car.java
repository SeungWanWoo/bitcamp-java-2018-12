// 상속: specialization - 해당 클래스를 더 특별한 클래스로 만드는 것.
// - 수퍼 클래스를 상속 받아서 서브 클래스를 만드는 것.
package ch13.g;

public class Car {
  String model;
  int cc;
  
  public void run() {
    System.out.println("달린다");
  }
  
  public void stop() {
    System.out.println("멈춘다.");
  }
}
