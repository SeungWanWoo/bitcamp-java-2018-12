// # 흐름 제어문 - do ~ while 반복문
package ch05;

public class Test11 {
  public static void main(String[] args) {
    int i = 1;
    do
      System.out.print(i + " ");
    while (i++ < 10); // i 값이 증가된 상태에서 출력되기 때문에 10까지 출력된다.
    // 후위 연산자 사용 방법
    System.out.println();

    i = 0;
    do {
      System.out.print(++i);    // 전위 연산자 사용 방법
      if(i == 10) 
        break;
      System.out.print(",");
    } while (i < 10);
    System.out.println();
  }
}
/*
 *  # do ~ while
 *    do
 *       문장1;
 *    while (조건);
 *    
 *    do {
 *       문장1;
 *       문장2;
 *       문장3;
 *    } while (조건);
 *    
 *    while문과의 차이점 
 *    - do ~ while 문은 최소 한 번 이상은 반복한다.
 *    - while 문은 0번 이상 반복한다. 
 */