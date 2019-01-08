// # 흐름 제어문 - switch와 break
package ch05;

public class Test07 {
  public static void main(String[] args) {
    String type = "개발팀장";

    // break 문을 쓰지 않으면 계속 이어서 실행된다.
    // => 일부러 쓰지 않을 때가 있다.
    // => 아래 조건에 만족하면서 내용이 필요할 경우
    switch (type) {
      case "개발팀장":
        System.out.println("개발 경력 10년 이상 또는 석사 이상");
      case "개발자":
        System.out.println("정보처리 자격증");
      case "사무직":
        System.out.println("전문학사 이상");
      default:
        System.out.println("이력서");
    }
    int score = 50;
    // break 문을 쓰지 않으면 계속 이어서 실행된다.
    // => 일부러 쓰지 않을 때가 있다.
    // => 아래 조건에 만족하면서 내용이 필요할 경우
    switch (score) {
      case 100:
      case 90:
        System.out.println("A");
        break;
      case 80:
      case 70:
        System.out.println("B");
        break;
      case 60:
      case 50:
      case 40:
        System.out.println("C");
        break;
      default:
        System.out.println("F");
    }
  }
}