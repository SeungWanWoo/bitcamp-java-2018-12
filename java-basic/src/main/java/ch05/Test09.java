// # 흐름 제어문 - 반복문 while 과 break, continue
package ch05;

public class Test09 {
  public static void main(String[] args) {
    int a = 1;
    
    while (a <= 10) {
      System.out.print(a++ + " ");
      
      if (a > 5)
        break; // 반복문을 멈추고 나간다.
    }
    
    System.out.println();
    
    a=1;
    while (a <= 10) {
      if (a % 2 == 0) {// 짝수이면 다음 코드를 실행하지 않고 조건 검사로 바로 올라간다.
        a++;
        continue; // 반복문을 멈추고 나간다.
      }
      System.out.print(a++ + " ");
      a++;
    }
  }
}