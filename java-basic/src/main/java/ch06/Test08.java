// # 메서드 - 가변 파라미터의 단점
package ch06;

public class Test08 {
  public static void main(String[] args) {
    compute("홍길동", 100, 100, 90);
    compute("임꺽정");
    compute("유관순", 100, 100, 100, 100);

    System.out.println("---------------------------");
    // 배열의 장점은 배열을 여러 개 넘길 수 있으며 위치에 상관않고 넣을 수 있다.
    compute3(new int[] {100, 100, 90}, "홍길동");
    compute3(new int[] {}, "임꺽정");
    compute3(new int[] {100, 100, 100, 100}, "유관순");
    System.out.println("---------------------------");

    compute4(new int[] {100, 90, 80}, new String[] {"홍길동", "임꺽정", "유관순"});
  }

  // 1) 가변 파라미터는 무조건 마지막 순서에 와야 한다.
  //static int plus1(int...value, String name) { // 컴파일 오류!
  static void compute(String name, int...value) {
    int sum = 0;    //초기화하지 않으면 안된다. 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    System.out.printf("%s 님의 총점은 %d입니다.\n", name, sum);
  }

  // 2) 가변 파라미터는 1개만 가능하다.
  // 가변 파라미터는 항상 마지막에 위치하여야 하기 때문에 여러 개를 사용할 수 없다.
  /*static int compute2(int...value, String...name) {
    int sum = 0;    //초기화하지 않으면 안된다. 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    return sum;
  }*/

  // 배열은 순서에 상관없다. 어느 순서에도 올 수 있다.
  static void compute3(int[] value, String name) {
    int sum = 0;    //초기화하지 않으면 안된다. 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    System.out.printf("%s 님의 총점은 %d입니다.\n", name, sum);
  }

  // 여러 개의 배열을 파라미터의 값으로 넘길 수 있다.
  static void compute4(int[] value, String[] name) {
    for (int i = 0; i < value.length; i++) {
      System.out.printf("%s 님의 총점은 %d입니다.\n", name[i], value[i]);
    }
  }
}