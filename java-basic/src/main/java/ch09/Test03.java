// # 클래스 필드의 한계
package ch09;

public class Test03 {
  
  public static void main(String[] args) {
    
    // 두 계산식을 동시에 하기
    // 식1) 2 * 3 - 2 + 7 = ?
    // 식2) 6 / 2 + 8 = ?
    
    // Calculator2의 result 변수는 오직 한 개만 존재한다.
    // 따라서 다음과 같이 두 개의 계산식을 동시에 수행할 수 없다.
    // 식1과 식2가 서로 다른 식이 아니라 한 식으로 계산되어 버린다.
    
    Calculator2.plus(2); // 식1 계산 => 2
    Calculator2.plus(6); // 식2 계산 => 6 을 생각하겠지만 실제는 2 + 6 이 된다.
    
    Calculator2.multiple(3); // 식1 => 2 * 3
    // 을 생각하겠지만 (2 + 6) * 3 이 된다.
    Calculator2.divide(2);   // 식2 => 6 / 2
    // 나머지 계산도 똑같다.
    
    Calculator2.minus(2);   // 식1 => 2 * 3 - 2
    Calculator2.plus(8);    // 식2 => 6 / 2 + 8
    
    Calculator2.plus(7);    // 식1 => 2 * 3 - 2 + 7
    
    System.out.println(Calculator2.result); // 25
    
    // 동시에 계산식을 수행하는 방법은 없을까?
    // Calculator2로는 불가능하다.
    // 오히려 Calculator1은 가능하다. 왜? 계산 결과를 호출하는 쪽에서 관리하기 때문이다.
    
  }
}
