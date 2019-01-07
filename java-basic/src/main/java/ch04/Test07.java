// # 부동소수점의 비교 오류
package ch04;

public class Test07 {
  public static void main(String[] args) {
    float f1 = 0.1f;
    float f2 = 0.1f;
    
    System.out.println(f1 * f2 == 0.01f); // false
    
    //이유?
    System.out.println(f1 * f2);
    // 0.010000001 결과 값 뒤에 0.000000001 오차 발생!
    
    // 해결책?
    // => 오차를 제거한 후 비교
    System.out.println(((f1 * f2 - 0.01f) <= Float.POSITIVE_INFINITY));
    // 다만, 위의 방법은 양수의 오차가 아닌 음수의 오차일 수 있기 때문에 정확하지 않는다.
    float r = f1 * f2 - 0.01f;
    System.out.println((Math.abs(r) <= Float.POSITIVE_INFINITY));
    // 그렇다면 계산 결과를 절대값으로 만들어주는 도구를 사용하면 된다!
    // 그 다음 오차 이하의 값이지 비교하라!
    // 여기서 바로 Math라는 멕가이버 칼에 절대값의 약자인 abs를 기능을 사용하면 된다!
  }
}