// # 흐름 제어문 - for 반복문 II

package ch05;

public class Test13 {
  public static void main(String[] args) {
    // 변수초기화 시키는 문장에 여러 개의 변수를 선언 할 수 있다.
    // 또한, 변수 증가문에 여러 개의 문장을 작성할 수 있다.
    // 그러나, 조건에는 단 하나의 변수에 대한 조건문만 들어갈 수 있다.
    // 더 들어가게 되면 컴파일 오류가 일어난다.
    for (int i = 1, j = 3, k = 5; i <= 10; i++, j--, k += 2)
      System.out.printf("(%d, %d, %d) ", i, j, k);
    System.out.println();
    
    // for 문의 중첩 = 이중 포문
    for (int i = 1; i <= 10; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(j + " ");
      }
      System.out.println();
    }
    System.out.println("-----------------------");

    // break
    for (int i = 1; i <= 10; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(j + " ");
        if (j == 5)
          break;    //break가 소속된 현재 반복문을 멈춘다.
      }
      System.out.println();
    }
    System.out.println("-----------------------");

    // break 라벨명;
    // 중괄호의 위치에 따라 출력되는 부분이 다르다.
    loop1:
      for (int i = 1; i <= 10; i++) {
        for (int j = 1; j <= i; j++) {
          System.out.print(j + " ");
          if (j == 5)
            break loop1;    // 라벨로 지정한 문장을 나간다.
        }
        System.out.println();
      }
    System.out.println("-----------------------");

    //continue
    for (int i = 1; i <= 10; i++) {
      for (int j = 1; j <= i; j++) {
        if (j % 2 == 0)
          continue;     // 다음 줄로 가지 않고 변수증가문으로 이동한다.
        System.out.print(j + " ");
       
      }
      System.out.println();
    }
    System.out.println("-----------------------");
  }
}