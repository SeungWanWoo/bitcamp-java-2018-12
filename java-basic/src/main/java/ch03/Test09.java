// 키보드로 입력한 값을 받기 IV - int와 문자열을 섞어서 값 받기
package ch03;

public class Test09 {
  public static void main(String[] args) {
    java.util.Scanner keyboard = new java.util.Scanner(System.in);
    
    System.out.print("나이? ");
    int age = keyboard.nextInt(); 
    /* nextInt()는 한 개의 토큰을 읽은 후에 줄 바꿈 코드는 읽기 전 상태로 내비둔다.
     * [OS]에서 키보드로 부터 입력 받은 데이터를 버퍼로 보낸다.
     *  버퍼에서는 입력받은 데이터를 입력하는 형식에 맞게 console화면에 보내는데,
     * nextInt()같은 경우는 빈 공백은 무시한다. 그러므로 일반 데이터는 
     * console 화면에 바로 보내지나 줄바꿈 코드[LF]는 무시되기 때문에 버퍼에 남는다.
     *  따라서 다음에 nextLine()을 호출하면 버퍼에 남아있던 줄바꿈 코드가 바로 출력이 되는,
     *  의도치 않게 빈 문자열을 가진 한 줄을 읽는 상황이 된다. => 이름? 20()
     *  토큰 형식으로 읽는 nextInt() 다음에 줄바꿈 코드[LF]에 따라 입력이 완료되는 
     * nextLine()을 호출할 때 이런 상황이 발생한다.
     *  해결방법? nextInt()를 호출한 후 남아있는 엔터 코드를 읽어서 제거하라.
     */
    keyboard.nextLine(); // 남아 있는 빈 문자열의 한 줄(LF 코드)을 읽어서 버린다.
    
    System.out.print("이름? ");
    String name = keyboard.nextLine();

    System.out.printf("%d(%s)\n", age, name); 
  }
}