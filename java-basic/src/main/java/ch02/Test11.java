// 콘솔로 출력하기
package ch02;

public class Test11 {
  public static void main(String[] args) {
    // 값 출력하기
    // println() = 출력 + 줄바꿈
    System.out.println(20); //정수
    System.out.println(3.14f); //부동소수점
    System.out.println("Hello"); //문자열
    System.out.println('Y'); //문자
    System.out.println(true); //논리
    // 값을 지정하지 않았을 땐 줄바꿈 역할만 한다.
    System.out.println(); //줄바꿈
    
    //print()는 출력만 한다. 줄바꿈 없다.
    System.out.print(20);
    System.out.print(3.14f); //부동소수점
    System.out.print("Hello"); //문자열
    System.out.print('Y'); //문자
    System.out.print(true); //논리
    
    //'\n' ==> println()
    System.out.print('\n');
    System.out.print("OK!\n"); //=println("OK!");
    System.out.print("Hi!\n"); //=println("Hi!");
    
  }
}

/* 
 */     
