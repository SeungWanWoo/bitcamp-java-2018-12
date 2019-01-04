// 키보드로 입력한 값을 받기 III - 한 줄에 int 값 받기
package ch03;

public class Test08 {
  public static void main(String[] args) {
    java.util.Scanner keyboard = new java.util.Scanner(System.in);
    
    System.out.print("a? ");
    int a = keyboard.nextInt(); 
    //주려는 메모리 형식에 맞는 데이터 형식의 변수를 준비한다.
    /* nextInt()는 한 개의 토큰(token)을 읽을 때 까지 기다린다.
     * 한 개의 token을 읽으면 4바이트 정수 값으로 바꾼 다음에 리턴한다.
     * 토큰?
     *  - 토큰이란, 양 쪽이 공백으로 구분되는 단어를 뜻한다.
     * 공백(whitesapce)?
     *  - 스페이스(space), 탭(tab), 줄바꿈 코드를 말한다.
     *  Ex) aaa    bbb  cc 이면 토큰은 aaa, bbb, cc 이다.
     *  중간에 여러 개의 공백이 들어가더라도 한 개의 공백으로 간주.
     */

    System.out.print("b? ");
    int b = keyboard.nextInt();

    System.out.printf("%d * %d = %d\n", a, b, a * b);
  }
}

/*
 */
