// # 메서드 - 로컬 변수의 생성과 소멸
package ch06;

public class Test09 {
  public static void main(String[] args) {
    int value = 100;
    m1(value);
    System.out.printf("main() : %d\n", value); //100
    
    m1(value + 15);
    System.out.printf("main() : %d\n", value); //100
    
    m1(value + 15);
    System.out.printf("main() : %d\n", value); //100
    
    // 위의 결과가 모두 100이 나오는 이유는 로컬 변수의 개념 때문이다.
    // 로컬 변수는 로컬 변수가 선언되어 있는 블록에서만 사용이 가능하며
    // 별다른 리턴값이 존재하지 않으면 해당 메서드가 사라지는 동시에
    // 변한 값을 가지는 로컬 변수도 같이 사라지기 때문이다.
    // 그러므로 main() 메서드에 존재하는 value 값인 100이 그대로 출력되게 된다.
  }
  
  static void m1(int value) {
    value = 200;
    System.out.printf("m1() : %d\n", value);
  }
}

// 위 코드를 보고 로컬 변수를 모두 찾아 그 이름을 나열하라
// 답 : main => [String[] args, int value], m1 => [int value]

/*
 * # 로컬 변수의 생성과 소멸
 *   - 메서드가 호출될 때 생성된다.
 *   - 메서드를 실행한 후 리턴 할 때 즉시 소멸된다.
 */
