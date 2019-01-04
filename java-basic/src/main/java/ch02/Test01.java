// 리터럴 = 값을 표현하는 방법
package ch02;

public class Test01 {
  public static void main(String[] args) {
    // 1) 정수 78을 출력하라
    // => 10진수
    System.out.println(78);
    // => 2진수
    System.out.println(0b01001110);
    // => 8진수
    System.out.println(0116);
    // => 16진수
    System.out.println(0x4e);
    // 2) 부동소수점을 출력하라.
    // => 12.345
    System.out.println(12.345);
    // => 1.2345를 출력할 때 12.345가 나오게 하라
    System.out.println(1.2345E1);
    // => 0.12345를 출력할 때 12.345가 나오게 하라
    System.out.println(0.12345E2);
    // => 123.45를 출력할 때 12.345가 나오게 하라
    System.out.println(123.45e-1);
    // 3) 논리 값을 출력하라.
    // => 참을 출력하라 true
    System.out.println(true);
    // => 거짓을 출력하라 false
    System.out.println(false);
    // 4) 문자를 출력하라.
    // => 0x61을 입력하였을 때 문자 'a'가 나오게 하라.
    System.out.println((char)0x61);
    // => 97 값을 출력했을 때 a가 나오게 하라.
    System.out.println((char)97);
    // => 작은 따옴표를 사용하여 a를 출력하라.
    System.out.println('a');
    // 5) 문자열을 출력하라.
    // => Hello를 출력하라
    System.out.println("Hello");
    // => 안녕하세요를 출력하라
    System.out.println("안녕하세요");
  }
}