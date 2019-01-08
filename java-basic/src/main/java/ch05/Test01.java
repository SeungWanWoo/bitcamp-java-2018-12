// # 흐름 제어문 - if 조건문
package ch05;

public class Test01 {
  public static void main(String[] args) {
    int age = 19;
    
    if (age >= 20) System.out.println("성인입니다.");
    
    // 보통 조건 다음에 오는 문장은 다음 줄에 놓는다.
    if (age >= 20) 
      System.out.println("성인입니다.");
    
    // 실행할 문장이 여러 개이면 중괄호로 묶어야 한다.
    if (age >= 20) {
      System.out.print("성인");
      System.out.println("입니다");
    }
    System.out.println("-------------------------------");
    // 주의!
    // 중괄호로 묶지 않았을 때 문제점
    if (age >= 20) 
      System.out.print("성인");
      System.out.println("입니다"); // 주의! 이 문장은 if 문에 종속되지 않는다.
    // 들여쓰기는 자바에서 의미가 없다.
  }
}
/*
 *  # if 조건문
 *   문법 1:
 *      if (조건) 문장;
 *       => 조건이 참일 때 문장 1을 수행한다.
 *   문법 2:
 *      if (조건) 문장1; else 문장2;
 *       => 조건이 참일 때 문장 1을 수행하고, 거짓이면 문장 2를 수행한다.
 */

