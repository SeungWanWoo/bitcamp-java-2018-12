// # l-value와 r-value 용어
package ch03;

public class Test04 {
  public static void main(String[] args) {
    int a = 100;
    int b = a;
    
    System.out.printf("%d, %d\n", a, b);
    
    a = 200;
    System.out.printf("%d, %d\n", a, b);
    
    //100 = 20; 컴파일 오류!
    //100 = a; // 컴파일 오류.
    a = 300; // OK. r-value는 리터럴이 가능
    a = b; // OK. r-value는 변수 가능.
    // 의미? 변수의 값을 왼쪽 변수에 저장하라는 의미
  }
}
/* # l-value와 r-value
 * l-value(left value) : = 연산자 왼쪽 편을 가리키는 용어
 * r-value(right value) : = 연산자 오른쪽 편을 가리키는 용어
 *  int a;
 *  a = 100;
 * - a => l-value; //l - value는 반드시 메모리여야한다! 
 * - 100 => r-value;
 */