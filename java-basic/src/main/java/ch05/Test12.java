// # 흐름 제어문 - for 반복문 

package ch05;

public class Test12 {
  public static void main(String[] args) {
    for (int i = 1; i <= 10; i++)
      System.out.print(i + " ");
    System.out.println();
    // 실행 순서
    // 1) 변수초기화 => int i = 1;
    // 2) 조건 => i <= 10
    // 3) 문장 => System.out.print(i + " ");
    // 4) 변수증가문 => i++
    // 조건이 참인 동안 2 ~ 4를 반복한다.

    // 여러 형태의 for 문
    // i = 200; // 컴파일 오류! for 문 안에 선언된 변수는 for 블록을 벗어나면 제거된다.
    //  위의 for 문 안에 선언된 i 변수는 for 블록을 나가면 삭제된다.
    //  그래서 다음 문장에서 i 변수를 선언할 수 있는 것이다.
    int i = 1;
    for (; i <= 10; i++) 
      System.out.print(i + " ");
    System.out.println();
    
    // 변수 증가문을 빼도 된다.
    i = 1;
    for (; i <= 10;) {
      System.out.print(i + " ");
      i++;
    }
    System.out.println();
    
    // 조건 문도 빼도 된다. 하지만 아래 식보다는 while 문으로 쓰는 것이 더 좋다.
    i = 1;
    for (;;) {
      if (i > 10)
        break;
      System.out.print(i + " ");
      i++;
    }
    System.out.println();
  }
}
/*
 *  # for 반복문
 *    - 배열과 함께 사용할 때 유용하다.
 *    
 *    for (변수초기화; 조건; 변수증가문)
 *          문장1;
 *    
 *    for (변수초기화; 조건; 변수증가문) {
 *          문장1;
 *          문장2;
 *          문장3;
 *     }
 */