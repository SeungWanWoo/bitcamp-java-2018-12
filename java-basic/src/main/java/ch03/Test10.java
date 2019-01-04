// 키보드로 입력한 값을 받기 V - 토큰 단위로 문자열 읽기
package ch03;

public class Test10 {
  public static void main(String[] args) {
    java.util.Scanner keyboard = new java.util.Scanner(System.in);
    
    System.out.print("나이? ");
    int age = keyboard.nextInt(); 
    //기존 사용했던 nextInt -> nextLine을 사용하는 행위는 키보드에서 입력한 값을 바로
    //리턴한다는 내용인데 사실상 불가능하다.
    //왜? 기본적으로 소프트웨어는 하드웨어에 직접 접근할 수 없기 때문이다.
    //그렇기때문에 OS에 존재하는 버퍼를 거쳐서 우리가 확인할 수 있게 되는 것이다.
    System.out.print("이름? ");
    String name = keyboard.next();
    /* next()는 토큰 단위로 문자열을 읽는다.
     * nextInt()를 호출한 후 엔터 코드(LF)가 남아 있다 하더라도
     * next는 공백을 버리기 때문에 영향을 받지 않는다.
     */
    System.out.printf("%d(%s)\n", age, name);
    System.out.println(age + "(" + name + ")");
  }
}

/* 버퍼 : 입출력 장치(키보드 입력)에서 읽어들인 값을 임시 보관하는 메모리
 * 모든 운영체제가 하드웨어를 장악하고 있다 -> 장악하지 않으면 입출력 장치에 의해서 하드웨어가 
 * 해킹당할 수 있다.
 * 운영체제와 하드웨어가 직접적으로 연관되는 장치는 드라이버 소프트웨어 밖에 없다.
 ** 마우스를 사용하기 위해서는 마우스 드라이버가 필요한데 이걸 조작하게 되면 상대방의 컴퓨터를
 *  해킹할 수 있게 된다. Ex) 마우스 잭
 *  자바에서는 일반적으로 소프트웨어에서 하드웨어를 건들일 수 없다.
 *  키보드 -> OS -> 버퍼 -> nextInt() -> 20[여기서 LF는 버퍼에 남아있다.]
 *  이 상태에서 nextLine()을 사용할 경우 가장 앞에 있는 LF를 읽어 빈 문자열을 출력하게 된다.
 *  그런데 next()를 사용하면 한 토큰을 읽을 때 까지이므로 버퍼에 남아있던 LF를 무시하게된다.
 */
