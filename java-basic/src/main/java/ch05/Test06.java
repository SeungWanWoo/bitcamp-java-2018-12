// # 흐름 제어문 - switch 문과 enum

package ch05;

public class Test06 {
  
  // 상수를 정의하는 문법
  static enum UserType {
    MANAGER, MEMBER, GUEST
  };
  public static void main(String[] args) {
    // 상수를 정의하는 문법
    UserType type = UserType.MANAGER;
    
    // switch의 조건으로 상수를 의미하는 enum 값이 올 수 있다.
    switch (type) {
      case MANAGER:
        System.out.println("관리자입니다.");
        break;
      case GUEST:
         System.out.println("손님입니다.");
         break;
      case MEMBER:
        System.out.println("일반회원입니다.");
        break;
      default:
         System.out.println("유효하지 않은 사용자입니다.");
    }
  }
}