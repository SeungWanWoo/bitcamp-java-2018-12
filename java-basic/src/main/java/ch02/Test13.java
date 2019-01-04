// 콘솔로 출력하기 III - 형식을 갖춰서 날짜값 출력하기
package ch02;

//Java.lang 패키지의 멤버를 사용할 때는 그냥 이름을 지정하면 된다.
//그 외 다른 패키지의 멤버를 사용할 때는 반드시 패키지 이름을 함께 지정해야 한다.
import java.util.Date;

public class Test13 {
  public static void main(String[] args) {
    //현재 날짜 및 시각 정보를 생성한다.
    Date today = new Date();
    /*
     * Java.util.Date today = new Java.util.date();
     * %t
     * System.out.printf("%t\n", today);
     * %Y
     * System.out.printf("%Y\n", today);
     * */
    System.out.println(today);
    java.util.Date today1 = new java.util.Date();
    //%t[날짜 및 시각 옵션]
    // 날짜 및 시각 옵션
    // Y : 날짜 및 시각 데이터에서 년도를 추출하여 4자리로 표현한다.
    // y : 날짜 및 시각 데이터에서 년도를 추출하여 2자리로 표현한다.
    // B : 날짜 및 시각 데이터에서 월을 추출하여 전체 이름으로 표현한다. January
    // b : 날짜 및 시각 데이터에서 월을 추출하여 단축 이름으로 표현한다. Jan 
    //       한글은 그대로 출력
    // m : 날짜 및 시각 데이터에서 월을 추출하여 2자리 숫자로 표현한다.
    // e : 날짜 및 시각 데이터에서 일을 추출하여 1자리 숫자로 표현한다.
    // d : 날짜 및 시각 데이터에서 일을 추출하여 2자리 숫자로 표현한다.
    // A : 날짜 및 시각 데이터에서 요일을 추출하여 전체 이름으로 표현한다.
    // a : 날짜 및 시각 데이터에서 요일을 추출하여 단축 이름으로 표현한다.
    // H : 날짜 및 시각 데이터에서 시각을 추출하여 24시로 표현한다.
    // I : 날짜 및 시각 데이터에서 시각을 추출하여 12시로 표현한다.
    // M : 날짜 및 시각 데이터에서 시각을 추출하여 분을 표현한다.
    // S : 날짜 및 시각 데이터에서 시각을 추출하여 초를 표현한다.
    // L : 날짜 및 시각 데이터에서 시각을 추출하여 밀리초를 표현한다.
    // N : 날짜 및 시각 데이터에서 시각을 추출하여 나노초를 표현한다. 그러나 나노초는 안나온다.
    System.out.printf("%1$tY %1$ty "
        + "%1$tB %1$tb "
        + "%1$tm %1$te %1$td "
        + "%1$tA %1$ta "
        + "%1$tH %1$tI "
        + "%1$tM "
        + "%1$tS %1$tL %1$tN\n", today1);
    // p : 오전 오후 출력하기 소문자 t를 사용하면 am 또는 pm으로 출력
    //       대문자 T를 출력하면 AM 또는 PM으로 출력한다.
    //       한글은 의미없다.[대소문자가 없기 때문]
    System.out.printf("%1$tp %1$Tp\n", today1);
    // 년 - 월 - 일 시:분:초를 출력하라!
    System.out.printf("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", today1);
    }
}

/* 
 */     
