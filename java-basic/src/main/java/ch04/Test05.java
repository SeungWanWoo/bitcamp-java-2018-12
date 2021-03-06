// # 연산자 우선순위
package ch04;

public class Test05 {
  public static void main(String[] args) {
    float r = 3.2f + 5 / 2;
    System.out.println(r);  // 5.2
    /*
     * => 연산자 우선 순위에 따라 *, /, % 연산자가 +, - 연산자보다 먼저 계산된다.
     * => 계산 순서 
     * 3.2f(float) + 5(int) / 2(int)
     * 3.2f(float) + 2(int)
     * 3.2f(float) + 2.0f(float)
     * = 5.2(float)
     */
    float r2 = (3.2f + 5) / 2;
    System.out.println(r2);  // 4.1
    //연산자 우선 순위에서 괄호가 모든 연산자 보다 우선으로 취급된다.
    
    int a = 2; // a = 5;
    int r3 = a++ + a++ * a++;
    /*
     * r3 = 2 + 3 * 4;      <== ++ 연산을 먼저 수행
     *      = 2 + 12;           <== * 연산을 먼저 수행
     *      = 14;               <== + 연산을 가장 나중에 수행
     */
    System.out.println(r3);
  }
}

/*
 * # 연산자 우선 순위
 *  1. 괄호 : ()
 *  2. 후위 연산자 : a++, a--
 *  3. 전위 연산자 : ++a, --a, 단항 연산자(+, -)
 *  4. * / %
 *  5. + -
 *  6. 비트이동 연산자 : <<, >>, >>>
 *  7. 관계 연산자 : <, >, <=, >=, instanceof
 *  8. 등위 연산자 : ==, !=
 *  9. &
 *  10. ^
 *  11. |
 *  12. 논리 연산자 AND : &&
 *  13. 논리 연산자 OR : ||
 *  14. 삼항 연산자 : (조건) ? 값 : 값
 *  15. 할당 연산자 : =, +=, -=, *=, /=, %=, &=, ^=, |=, <<=, >>=, >>>=
 */