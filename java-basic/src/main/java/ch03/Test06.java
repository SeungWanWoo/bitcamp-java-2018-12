// 키보드로 입력한 값을 받기
package ch03;

public class Test06 {
  public static void main(String[] args) {
    // 1) 키보드로 입력한 데이터를 읽을 때 사용할 도구를 준비한다.
    java.io.InputStream /*키보드 읽기 도구 = */in = System.in;
    
    /* 2) InputStream은 바이트 단위로 읽는 기능이 있다.
     *     바이트 단위로 읽어서 int나 문자열로 바꾸려면 또 코딩해야 하는 불편함이 있다.
     *     이런 불편함을 줄이기 위해 자바에서는 바이트를 개발자가 원하는 값으로
     *     바꿔주는 기능을 제공한다.
     *     그런 기능이 들어있는 도구가 java.util.Scanner 이다.
     */
    java.util.Scanner 
    /*데이터를 종류별로 쉽게 읽을 수 있는 기능을 갖춘 도구 = */keyboard = 
                          new java.util.Scanner(in/*어댑터*/);
    
    //사용자에게 입력하라고 간단히 출력하자.
    System.out.print("이름을 입력하세요 : ");
    
    /* 3) Scanner에 들어 있는 nextLine()은 
     *     사용자가 한 줄을 입력할 때까지(LF 코드를 읽을 때까지) 기다리다가
     *     한 줄을 입력하면 그 값을 문자열로 만들어 리턴한다.
     */
    java.lang.String /*읽은 문자열 = */str = keyboard.nextLine();
    
    //키보드에서 입력한 값 한 줄 읽기 -> 사용자가 입력한 문자열을 출력한다.
    System.out.printf("당신의 이름은 %s입니다.\n", str);
    
    //키보드읽기도구.read로는 모든 키보드 읽기를 수행할 수 없다!
    //기존 도구에는 본인이 원하는 기능이 없다면 새롭게 기능을 붙여야만 한다.
  }
}

/* # adapter[어댑터]
 *  
 */
/* # 캡슐화
 * 물건을 사용할 때 어떻게 해당하는 물건이 어떻게 작동하는지 보다는
 * 어떻게 사용하는 지를 알아보고 그것만 익힌다.
 * 그 해당하는 물건 내부가 어떤 구조로 이루어졌는지는 겉에 케이스로 가려져 있어
 * 알아볼 수 없다.
 * 겉을 두르고 있는 케이스를 캡슐이라고 하고, 접근하는 방법을 메소드라고 부른다.
 * 이 모든 과정을 캡슐화라고 부른다.
 */