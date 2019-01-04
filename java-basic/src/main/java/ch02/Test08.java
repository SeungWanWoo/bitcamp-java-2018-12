// 논리 리터럴
package ch02;

public class Test08 {
  public static void main(String[] args) {
    System.out.println(true);
    System.out.println(false);
    
    //System.out.println((boolean)1); 자바는 정수 값을 true/false로 형변환 X
    
    // 작은 따옴표(single quote)의 리턴 값은 2바이트 정수 값(UTF-16 코드)이다.
    // 아래 노란 줄은 warning인 코드를 나타낸다. 기본적으로 eclipse는 모든 코드가 안정되야
    // 하는데 그렇지 못한 코드이기 때문에 Comparing identical expressions 오류가 떴다.
    // 해당 오류는 SuppressWarnings객체에서 뮤트 시켜주면 된다.
    if ('가' == 0xac00) {//수를 표기할 땐 16진수를 쓴다.
      System.out.println("맞다!");
    } else {
      //컴파일하여 실행은 되지만 쓸데 없는 코드이다. [Dead Code]
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
 *  - 직접 정수 값을 지정해서는 안된다.[C언어와의 차이점]
 */     
