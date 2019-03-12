// reflection API - 메서드의 상세 정보 꺼내기 II
package ch27.b;

import java.lang.reflect.Method;
// Modifier로 시작되는 메서드를 미리 선언해버리면 길게 선언할 필요가 없다.
public class Test06 {
  public static void main(String[] args) throws Exception {
    Class<?> clazz = C.class;
    
    // 상속 받은 메서드를 포함하여 모든 public 메서드의 정보 출력하기
    // 해
    Method[] methods = clazz.getMethods();
    for (Method m : methods) {
      // 메서드가 실제 정의된 클래스의 이름 출력하기
      System.out.printf("클래스 명: %s\n", 
          m.getDeclaringClass().getSimpleName()); // => 해당 메서드가 정의된 클래스의 이름
      System.out.printf("   ==> %s\n", m.getName());
      System.out.println("--------------------------------");
    }
  }
}
