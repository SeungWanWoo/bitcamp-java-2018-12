// # main() 메서드의 파라미터 응용 I
package ch06;

public class Test14 {
  public static void main(String[] args) {
    // 합계를 출력하는 프로그램을 작성하라. 
    // 실행할 때는 $ java -cp ./bin/main ch06.Test14 200 43 56
    int sum = 0;
    for (String arg : args) {
      sum += Integer.parseInt(arg);
      // 이 경우 만약 args 값에 숫자를 집어 넣게 되면 
     // NumberFormatException 오류가 발생한다.
    }
    System.out.printf("합계 : %d\n", sum);
    // 배열의 주소가 넘어오긴 넘어오나 배열에 아무것도 안들어 있기 때문에 출력되는 값이 없다.
    // 만약 배열에 null 값이 있었다면 NullPointerException 오류가 발생한다.
  }
}