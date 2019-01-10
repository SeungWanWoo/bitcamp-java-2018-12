// # main() 메서드의 파라미터 응용 II
package ch06;

public class Test15 {
  public static void main(String[] args) {
    // 학생의 이름과 국영수 점수를 입력 받아 총점과 평균을 출력하라
    // 실행할 때는 $ java -cp ./bin/main ch06.Test15 홍길동 100 100 90
    // 총점 : 290
    // 평균 : 96.9
    int sum = 0;
    if (args.length > 4) {
      System.out.println("실행 형식 : java -cp "
          + "./bin/main ch06.Test15 이름 국어점수 수학점수 영어점수\n");
      return;
    }
    for (int i = 1; i < args.length; i++) {
      sum += Integer.parseInt(args[i]);
      // 이 경우 만약 args 값에 숫자를 집어 넣게 되면 
     // NumberFormatException 오류가 발생한다.
    }
    double avr = sum / 3f;
    System.out.printf("이름 : %s\n", args[0]);
    System.out.printf("합계 : %d\n", sum);
    System.out.printf("평균 : %.2f\n", avr);
    // 배열의 주소가 넘어오긴 넘어오나 배열에 아무것도 안들어 있기 때문에 출력되는 값이 없다.
    // 만약 배열에 null 값이 있었다면 NullPointerException 오류가 발생한다.
  }
}