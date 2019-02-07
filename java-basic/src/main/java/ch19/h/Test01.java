// Lambda 문법 - functional interface를 효과적으로 다루는 문법
package ch19.h;

public class Test01 {

  // 다음과 같이 추상메서드가 한개 있는 인터페이스를 "fuctional interface"라고 부른다.
  // => 이런 경우에 Lambda 문법을 사용할 수 있다.
  static interface Player {
    void play();
  }
  public static void main(String[] args) {
    // 익명 클래스로 인터페이스 구현하기
    Player p1 = new Player() {
      public void play() {
        System.out.println("테스트1");
      }
    };
    // 람다 클래스로 인터페이스 구현하기
    p1.play();

    Player p2 = () -> {System.out.println("테스트1");};
    p2.play();
  }
}