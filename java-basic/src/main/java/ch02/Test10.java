// 문자 제어 코드 // ASCII 문자 제어표
package ch02;

public class Test10 {
  public static void main(String[] args) {
    //줄바꿈은 해당 제어 문자 사용
    System.out.println("Hello, \n world");
    //줄바꿈 [엔터로 취급X]
    System.out.println("Hello, \r world");
    //뒤로 이동(콘솔용) [이클립스에서는 오류 뜸]
    System.out.println("Hello, \b\b\b world");
    //탭
    System.out.println("Hello, \tworld");
    //원래는 다음 페이지에 출력되나 콘솔창에서는 아랫줄에 해당 위치에 출력된다.
    System.out.println("Hello, \fworld");
    //" 출력하는 방법 문자열 중간에 "를 출력하려면 \를 사용한다.
    System.out.println("Hello, \"w\"world");
    System.out.println('"');
    //' ""내부이면 그냥 사용해도 되나 '이면 \를 사용해서 출력해준다.
    System.out.println("Hello, 'w'orld");
    System.out.println('\'');
    // \는 중요한 의미를 가진다. \를 출력하고 싶으면 \\를 더 붙여준다.
    System.out.println("Hello, \\world");
  }
}

/* # 문자 제어 코드 = 이스케이프 문자(Escape character)
 *  - 화면에 출력하는 문자가 아니라, 문자 출력을 제어하는 명령이다.
 *  - 제어 문자
 *    \n - Line Feed(LF; 0x0a)
 *    \r - Carrage Return(CR; 0x0d)
 *    \f - Form Feed(FF, 0x0c)[프린터하고 상관이 있음]
 *    \t - Tab(HR, 0x09)
 *    \b - Backspace(BS, 0x08)
 *    \' - Single Quote(0x27)
 *    \" - Double Quote(0x22)
 *    \\ - Backslash(0x59)
 * 
 */     
