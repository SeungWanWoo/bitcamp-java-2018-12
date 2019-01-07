// # 암시적 형변환
package ch04;

public class Test04 {
  public static void main(String[] args) {
    float r = 5 / 2 + 3.2f;
    System.out.println(r);  // 5.2
    /*
     * 5(int) / 2(int) + 3.2f(float)
     *     2(int)      + 3.2f(float)
     *     2.0f(float) + 3.2f(float)
     *           = 5.2f(float)
     *  계산 결과는 어쨋듯 float이다.
     *  그러나, 모든 과정에서 float으로 계산되는 것이 아니라
     *  int와 int의 계산은 int 형식으로 먼저 계산이되고
     *  이후에 float과의 계산에서 암시적 형변환이 이루어져
     *  float과 float의 계산이 되면서 결과값이 float이 된다.
     */
  }
}