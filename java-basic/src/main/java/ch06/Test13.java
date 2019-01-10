// # main() 메서드의 파라미터
package ch06;

public class Test13 {
  public static void main(String[] args) {
    System.out.println("--------------");
    for (String arg : args) {
      System.out.println(arg);
    }
    System.out.println("--------------");
    
    // 배열의 주소가 넘어오긴 넘어오나 배열에 아무것도 안들어 있기 때문에 출력되는 값이 없다.
    // 만약 배열에 null 값이 있었다면 NullPointerException 오류가 발생한다.
  }
}

/*
 * # 프로그램 아규먼트(arguments)
 *  - 프로그램을 실행할 때 넘겨주는 값.
 *  - 어떻게 아규먼트를 넘기는가?
 *    $ java 클래스명 값1 값2 값3
 *  - 아규먼트는 공백으로 구분한다.
 *  - JVM은 아규먼트의 개수만큼 문자열 배열을 만들어 저장한다.
 *  - 아규먼트가 없으면 빈 배열을 만든다.
 *  - 그런 후 main()을 호출할 때 그 배열의 주소를 넘겨준다. 
 */

// Project Explorer : 이클립스에서 보기 쉽게 가공해서 보여준다. - 빈 폴더를 보여주지 않는다.
// Navigator : 디렉토리 날것 상태 그대로 보여준다. - 모든 폴더를 보여준다.
