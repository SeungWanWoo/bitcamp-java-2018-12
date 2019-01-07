// # 관계 연산자(<, <=, >, >=), 등위 연산자(==, !=)
package ch04;

public class Test06 {
  public static void main(String[] args) {
    System.out.println(10 < 20);
    System.out.println(10 <= 20);
    System.out.println(10 > 20);
    System.out.println(10 >= 20);
    //System.out.println(10 = 20); //컴파일오류!
    //l-value는 무조건 메모리여야한다. l-value엔 절대 값이 오면 안된다.
    System.out.println(10 != 20);
    //System.out.println(a = 20);
    // Java에서만 컴파일 오류!
    // C에서는 맞으면 true 아니면 false이기 때문에
  }
}