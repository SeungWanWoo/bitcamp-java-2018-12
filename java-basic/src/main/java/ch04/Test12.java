// # &&, || vs &, |
package ch04;

public class Test12 {
  public static void main(String[] args) {
    // 괄호 연산
    int a = 5;
    int r = 10 / (a = 2);
    System.out.printf("%d, %d\n", a, r);
    // r = 10 / (a = 2)
    // r = 10 / (a 변수에 2를 먼저 저장)
    // r = 10 / (a 변수의 값을 리턴)
    // r = 10 / 2
    // r = 5
    
    // &&와 &의 차이점 --자바 스크립트에서 자주 쓰이며 고급 프로그래밍에서도 사용이 된다.
    boolean b1 = true;
    boolean b2 = false && (b1 = false);
    // && 연산자는 ---------| 까지 실행
    
    System.out.printf("&& 연산자 - b1 = %b, b2 = %b\n", b1, b2);
    // && 연산자는 l-value의 값으로 결과를 유추할 수 있다면, r-value를 실행 X
    
    b1 = true;
    b2 = false & (b1 = false);
    // &  연산자는 ---------------| 까지 실행
    System.out.printf("&  연산자 - b1 = %b, b2 = %b\n", b1, b2);
    // & 연산자는 l-value의 값으로 결과가 결정되었다 하더라도 r-value를 끝까지 실행
    
    b1 = true;
    b2 = true || (b1 = false);
    // ||연산자는 | 까지 실행
    System.out.printf("|| 연산자 - b1 = %b, b2 = %b\n", b1, b2);
    // || 연산자는 l-value의 값으로 결과가 결정되었다 하더라도 r-value를 끝까지 실행
    
    b1 = true;
    b2 = true | (b1 = false);
    // |  연산자는 ---------------| 까지 실행
    System.out.printf("|  연산자 - b1 = %b, b2 = %b\n", b1, b2);
    // | 연산자는 l-value의 값으로 결과가 결정되었다 하더라도 r-value를 끝까지 실행
    }
}