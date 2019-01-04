// 논리 리터럴
package ch02;

public class Test08 {
  public static void main(String[] args) {
    System.out.println(true);
    System.out.println(false);
    
    //System.out.println((boolean)1); 자바는 정수 값을 true/false로 형변환 X
    
    // 작은 따옴표(single quote)의 리턴 값은 2바이트 정수 값(UTF-16 코드)이다.
    if ('가' == 0xac00) {//수를 표기할 땐 16진수를 쓴다.
      System.out.println("맞다!");
    } else {
      System.out.println("아니다!");
    }   // == 연산의 결과는 true/false이다.
    
    /* 컴파일 오류!
    if( 10 - 10) { //c언어에서는 0은 false, 그 외의 모든 수는 true이다. 자바는 X
      System.out.println("참이다!");
    }
    */
  }
}

/*
 * # 논리 리터럴
 *  - 자바 참, 거짓을 표현하는 키워드를 제공
 *  
 * # 논리 값을 메모리에 저장할 때 크기
 *  - 4바이트 int 메모리에 저장 (JVM 명세서 참조)
 *  - 배열 값인 경우 1 바이트 메모리에 저장한다. (JVM 명세서 참조)
 *  - true = 1; false = 0;
 */     
