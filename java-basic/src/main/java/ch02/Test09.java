// 문자열 리터럴
package ch02;

public class Test09 {
  public static void main(String[] args) {
    //문자열 출력
    System.out.println("홍길동");
    
    //Concatenation [+]
    System.out.println("Hello," + "World!");
    
    //Another type Concatenation - 다른 형식과의 + 연산
    System.out.println("나이 : " + 20); // + 정수
    System.out.println(false + "<== 재직자 여부"); // + 논리
    System.out.println("성별 : " + '여'); // + 문자
    System.out.println("키 : " + 170.5f); // + 부동소수점
  }
}
/*
 * # 문자열
 *  - 자바의 기본 타입이 아니라 객체이다.
 *  - 객체란? 여러 데이터 덩어리이다.
 *  - char[] 배열에 유니코드가 저장된다.
 *  - 표기법
 *      큰 따옴표(double qoute) 안에 문자들(문자열)을 작성한다.
 *      예) "홍길동", "임꺽정", "Hello!"
 *  - 문자열 연결 연산자(concatenation) +
 *    +를 이용하여 문자열과 문자열을 연결할 수 있다.
 *    이때 두 개의 문자열이 연결된 새로운 문자열이 새로 생성된다. // 총 3개 생성
 *  - 문자열과 다른 종류의 값(형식)을 연결하기
 *    문자열과 다른 종류의 값을 연결할 수 있다. 연결되기 전에 다른 종류의 값이 먼저 문자열로
 *    바뀐 다음에 문자열이 생성된다.
 */     
