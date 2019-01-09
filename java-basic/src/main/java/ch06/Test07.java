// # 메서드 - 가변 파라미터 vs 배열 레퍼런스
package ch06;

public class Test07 {
  public static void main(String[] args) {
    // 가변 파라미터는 호출하는 쪽에서 배열을 만들 필요가 없다.
    // 자바가 자동으로 배열을 만든다.
    plus1();
    plus1(10);
    plus1(10, 20);
    
    // 배열 레퍼런스는 호출자가 만들어서 넘겨야 한다.
    //plus2();    // 컴파일 오류!
    plus2(new int[] {});
    plus2(new int[] {10});
    plus2(new int[] {10, 20});
    
    // => 위의 결과만 보면 호출할 때는 가변 파라미터가 사용하기 편하다.
    //
  }
  
  // 가변 파라미터
  static int plus1(int...value) {
    int sum = 0;    //초기화하지 않으면 안된다. 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    return sum;
  }
  
  // 배열 레퍼런스 파라미터
  static int plus2(int[]value) {
    int sum = 0;    //초기화하지 않으면 안된다. 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    return sum;
  }
}