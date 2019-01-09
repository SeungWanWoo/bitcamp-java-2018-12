// # 메서드 - 재귀 호출
package ch06;

public class Test12 {
  public static void main(String[] args) {
    int result = sum1(5, sum1(4, sum1(3, sum1(2, sum1(1, 0)))));
    System.out.println(result);
    System.out.println("---------");
    
    int result2 = 0;
    result2 = sum1(result2, 1);
    result2 = sum1(result2, 2);
    result2 = sum1(result2, 3);
    result2 = sum1(result2, 4);
    result2 = sum1(result2, 5);
    System.out.println(result2);
    System.out.println("---------");
    
    System.out.println(sum2(5));
    
    // 위의 식들은 전반적으로 복잡해 보이거나 단순한 반복을 보이고 있다.
    // 이러한 반복된 과정을 단순한 수학 공식으로 표현한다면 다음과 같다.
    // 위의 알고리즘은 다음 수학 공식과 같다.
    // 합계를 구하는 sum(n) = n + sum(n-1)
  }
  
  static int sum1(int a, int b) {
    return a + b;
  }
  
  // 재귀 호출!
  // => 쫄지 마라!
  // => 그냥 다른 메소드를 호출했다고 생각해라.
  // => 메서드가 호출되면? 스택에 그 메소드가 사용할 변수가 생성된다. 이것만 기억하라!
  // => 재귀호출은 수학 공식을 그대로 표현할 수 있어서 읽기도 쉽고 코딩하기도 쉽다.
  // => 그러나 계속 메서드를 호출하기 때문에 메모리를 많이 차지하고,
  //    실행 속도가 느리다.
  // => 17706이상의 수를 호출하게 되면 발생한다.
  
  // => 계속해서 메서드를 호출하게 되면 프레임이 계속적으로 생성 또는 생성 대기 상태가 된다.
  // => 이렇게 메모리를 사용하다보면 CPU가 수용할 수 있는 범위를 넘게 되는데 이때 발생하는 오류가
  // => 바로 StackOverFlow이다. 즉, 스택이 더이상 데이터를 받아들일 수 없는 상태라는 뜻이다.
  // => 따라서 위의 식들 처럼 호출이 깊지 않은 간단한 상황에 재귀 호출을 적용하는 것이 좋다.
  static int sum2(int n) {
    if (n == 1)
      return n;
    
    return n + sum2(n-1);
  }
}