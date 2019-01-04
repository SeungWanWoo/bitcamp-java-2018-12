// # 레퍼런스 변수
package ch03;

import java.util.Date;

public class Test05 {
  public static void main(String[] args) {
    java.util.Date d1 = new java.util.Date();
    
    java.util.Date d2 = d1;
    
    System.out.printf("%d, %d\n", d1.getDate(), d2.getDate()); 
    
    d1.setDate(22);
    // 밑줄 이유 : 쓰지 말라고 권고하는 메소드
    System.out.printf("%d, %d\n", d1.getDate(), d2.getDate());
    // d1에 저장된 일자 값을 변경한 후
    // d2저장된 일자 값을 출력해보면 d1과 꼭같이 변경되어 있다.
    // 이유?
    // d1과 d2에 저장되는 것은 값이 아니라 (날짜 정보가 저장되어 있는 메모리의) 주소이다.
    // 이렇게 값을 저장하지 않고 값이 저장된 위치(주소)를 저장하는 변수를 레퍼런스 변수라 부른다.
    // 자바 기본 데이터 타입(byte, short, int, long, float, double, boolean, char)을 
    //제외한 모든 타입의 변수가 레퍼런스이다.
    //java.lang 이외의 패키지를 사용하려면 사용하려는 패키지의 이름을 정확히 적어야
    // 해당 패키지 내부에 있는 타입들을 사용할 수 있다.
  }
}
/* 
 */