// # 생성자 활용 - java.util.Date 클래스의 생성자
package ch10;

import java.util.Date;

public class Test15 {
  public static void main(String[] args) throws Exception {
    // Date() 기본 생성자
    Date d1 = new Date();   // 현재 시간을 저장한다.
    System.out.println(d1);
    
    // Date(long) :1970-01-01 00:00:00 부터 지금까지 경과된 밀리초를 가리킨다.
    Date d2 = new Date(1000);
    System.out.println(d2);
    
    //클래스 이름으로 바로 호출하기 때문에 스태틱 메서드
    Date d3 = new Date(System.currentTimeMillis());
    System.out.println(d3); // = d1
    
    // 1900년도 기준, 0이 1월로 해야하므로 불편하다->직관적이지 않아서 Deprecated[비난받는]
    // 메서드라 다른 것으로 대체되었다.
    Date d4 = new Date(119, 2, 3);
    System.out.println(d4);
    
    // java.sql.Date
    // 여러 패키지 중 같은 이름의 메서드가 존재하면 정확하게 패키지 명을 적어줘야한다.
    java.sql.Date d5 = new java.sql.Date(System.currentTimeMillis());
    System.out.println(d5);
    // java.sql.Date의 생성자는 기본 생성자가 없기 때문에 
    // 반드시 파라미터 값을 넣어주어야 한다.
    
    // 간접적으로 객체를 생성하기
    // new를 사용하지 않고 간접적으로 객체를 생성할 수 있다.
    java.sql.Date d6 = java.sql.Date.valueOf("2019-01-16");
    System.out.println(d6);
  }
}
