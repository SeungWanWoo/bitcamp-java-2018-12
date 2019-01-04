// 키보드로 입력한 값을 받기 II - 한 줄에 문자열 읽기
package ch03;

public class Test07 {
  public static void main(String[] args) {
    java.util.Scanner keyboard = new java.util.Scanner(System.in);
    /* java.io.InputStream in = System.in;을 매번 사용하기에는 불편하기 때문에
     * Scanner를 선언할 때 내부에 선언해주어 불편함을 덜어준다.
     */
    
    //무엇인가 입력할때 알림 문구 같은 것을 써주면 사용자 입장에서 알아보기 편해진다.
    System.out.print("이름을 입력하세요 : ");
    java.lang.String name = keyboard.nextLine();
    
    // 사용자가 입력할 때 까지 진행이 안되게 하는 동작을 Blocking이라고 부른다.
    // 입력이 될 때까지 기다리는 것이 아니라 따로 동작을 진행하는 것을
    // nonBloking이라고 부른다.
    System.out.print("나이를 입력하세요 : ");
    String age = keyboard.nextLine(); //java.lang 패키지의 멤버를 사용할 때는
    //패키지 이름을 적지 않아도 된다.

    System.out.printf("%s(%s)\n", name, age);
  }
}

/*
 * - nextLine() 문자열을 입력받을 때 사용한다.
 * - console이 하는 역할은 사용자가 입력한 값을 화면에 띄우고 nextLine()에 보내지게 된다.
 * - console 창에서 값을 여러 개 입력 받을 수 있으나, 한 줄로 계속 출력 입력을 할 수 없다.
 * ex) 이름을 입력하세요 : wany[입력] 나이를 입력하세요 : 26[입력] -->>불가능
 *  >> 엔터를 입력하면 console에서 줄 개행의 값으로 받아들여 처리하므로 우리가 보는
 *     console창처럼만 되게 된다.
 */
