// # 할당(배정, 대입) 연산자 : +=, -=, *=, /=, %=, &=, ^=, |=, <<=, >>=, >>>=
package ch04;
public class Test18 {
  public static void main(String[] args) {
    int a = 5;
    a = a+ 3;
    System.out.println(a);
    
    //위의 값과 똑같이 나오게 하는 방법
    a = 5;
    a += 3; // = [a = a + 3;]
    System.out.println(a);
  }
}