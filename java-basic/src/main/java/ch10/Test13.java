// # 초기화 블록 - 클래스 로딩과 스태틱 블록 실행 과정 - 클래스 로딩 시점 파악
package ch10;

class A {
  static int a = 7;
  
  static {
    System.out.println("A.static{}");
    a += B.b;
  }
}

class B {
  static int b = 22;
  
  static {
    System.out.println("B.static{}");
    b += A.a;
  }
}

public class Test13 {
  public static void main(String[] args) {
   System.out.println(A.a); // 29 -> 36
   // A.a를 출력하기 위해 Method Area에 클래스 A 로딩
   // 스태틱 필드 생성 - static int a = 7;
   // 클래스 A의 스태틱 블록 실행 -> A.static{}
   // a에 값을 계산하기 위해 B.b를 계산할 클래스 B를 Method Area에 로딩
   // 스태틱 필드 생성 - static int b = 22;
   // 클래스 B의 스태틱 블록 실행 -> B.static{}
   // b 값에 A.a값을 더한다.[이미 클래스가 로딩되어있으므로 바로 값을 꺼내서 계산한다.]
   // b = 22 + 7 = 29
   // b 값은 29가 되면서 클래스 B에서 A로 리턴한다.
   // 이후 a의 값에 리턴된 b의 값을 더해준다. a = 7 + 29 = 36
   // 해당 값을 console 창에 출력한다.
   System.out.println(B.b); // 51 -> 29
   // 이미 class B가 Method Area에 로딩되어 있으므로 바로 값을 불러와 출력한다.
   
   // 클래스 로딩 절차
   // 1) 클래스를 Method Area에 로딩한다.
   // 2) 스태틱 변수를 만든다.
   // 3) 스태틱 블록을 실행한다.
   // 
   // 클래스 로딩
   // => 클래스 멤버(변수, 메서드)를 사용할 때
   // => Class.forName("클래스명")을 통해 임의로 로딩할 때
   // => 단, 한번 로딩된 클래스는 다시 로딩하지 않는다.
   
   // 스태틱 블록의 목적
   // => 클래스 멤버(스태틱 변수, 스태틱 메서드)를 사용하기 전에 
   //    유효한 값으로 초기화 시키는 것이다.
   
  }
}