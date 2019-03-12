// reflection API - 메서드의 상세 정보 꺼내기
package ch27.b;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import static java.lang.reflect.Modifier.*;
// Modifier로 시작되는 메서드를 미리 선언해버리면 길게 선언할 필요가 없다.
public class Test05 {
  public static void main(String[] args) throws Exception {
    Class<?> clazz = String.class;
    
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      System.out.printf("메서드 명: %s.%s\n", 
          m.getDeclaringClass().getSimpleName(), m.getName());
      System.out.printf("파라미터: %d\n", m.getParameterCount());
      Parameter[] params = m.getParameters();
      for (Parameter p : params) {
        System.out.printf("    %s:%s\n", 
            p.getName(), // 파라미터 명
            p.getType().getName()); // 파라미터 타입명
      }
      System.out.println("리턴 타입:");
      System.out.printf("    %s\n", m.getReturnType().getName());
      
      System.out.println("modifier:");
      int modifiers = m.getModifiers();
      if (isPublic(modifiers))
        //(modifiers & PUBLIC) == PUBLIC)
        //(modifiers & PUBLIC) != 0)
        System.out.println("    public");
      else if (Modifier.isProtected(modifiers))
        //(modifiers & PROTECTED) == PROTECTED)
        //(modifiers & PROTECTED) != 0)
        System.out.println("    protected");
      else if ((modifiers & PRIVATE) != 0)
        //(modifiers & PRIVATE) == PRIVATE)
        //
        System.out.println("    private");
      
      if ((modifiers & STATIC) != 0)
        System.out.println("    static");
      if ((modifiers & FINAL) != 0)
        System.out.println("    final");
      
      System.out.println("--------------------------------");
    }
  }
}
