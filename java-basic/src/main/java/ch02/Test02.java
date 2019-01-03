package ch02;

public class Test02 {
  public static void main(String[] args) {
    //20 억을 4바이트 리터럴로 출력하라
    System.out.println(2000000000);
    //20 억을 8바이트 리터럴로 출력하라
    System.out.println(2000000000L);
    //30 억을 8바이트 리터럴로 출력하라
    System.out.println(3000000000L);
    //30 억을 4바이트 리터럴로 출력하라
    //System.out.println(3000000000);
    //The literal 3000000000 of type int is out of range
    //출력 범위가 4바이트 범위를 초과하기 때문에 오류 메시지가 뜬다.
  }
}
/*
 * # 4바이트 정수 리터럴
 * 100
 * # 8바이트 정수 리터럴\
 * 정수 뒤에 접미사 L 또는 l을 붙인다.
 * 100L <주로 사용
 * 100l
 * 
 */
