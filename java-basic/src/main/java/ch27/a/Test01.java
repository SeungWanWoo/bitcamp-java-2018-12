// 인터페이스 구현체를 자동으로 생성하기
package ch27.a;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test01 {
  public static void main(String[] args) {
    // java.lang.reflect.Proxy
    // => 인터페이스 구현체를 만드는 역할을 한다.
    //
    // newProxyInstance(
    //    구현체를 만들기 위해 사용하는 인터페이스의 클래스로더,
    //    구현할 인터페이스 목록,
    //    실제 작업을 수행하는 객체)
    // => 파라미터로 넘겨 받은 인터페이스를 모두 구현한 클래스를 만들어 리턴한다.
    //
    // 클래스 로더
    // => 클래스 정보를 로딩하는 역할을 수행한다.
    // => 클래스 로더를 알아내는 방법
    //    - 클래스정보.getClassLoader()
    // => 클래스 정보?
    //    - 인스턴스.getClass()
    //    - 클래스명.class
    //    - Class.forName("패키지명을 포함한 클래스명")
    //
    // 클래스를 얻는 방법 예:
    /*
    String s = "hello";
    Class<?> c1 = String.class; // 클래스의 static 변수인 class의 값을 사용할 수 있다.
    Class<?> c2 = s.getClass(); // 인스턴스로 알아낼 수 있다.
    Class<?> c3 = Class.forName("java.lang.String"); // 클래스 정보를 리턴하는 도구를 이용
    Class<?> c4 = Class.forName(s); // ?

    int a = 10, b = 20, c = 30;
    int[] arr = new int[3];
    arr[0] = 10; arr[1] = 20; arr[2] = 30;

    int[] arr2 = new int[] {10, 20, 30};

    int[] arr3 = {10, 20, 30};

    int[] arr4; arr = {10, 20, 30}; // 앞에 new 생략 불가.

    어차피 바로 인터페이스 한개 쓰는데 왜 굳이 그걸 변수로 선언해서 쓰냐
    바로 쓰는게 효율적
    Class<?> clazz = Calculator.class;
    Class<?> clazz2 = Iterator.class;
    Class<?>[] classes = new Class[] {clazz, clazz2};
    비효율적 => 언제까지 이렇게 쓸거냐
    Class<?>[] classes = new Class[2];
    classes[0] = clazz;
    classes[1] = clazz2;
     */
    //
    // 실제 작업을 수행하는 객체
    // => java.lang.reflect.InvocationHandler 인터페이스에 따라 동작하는 객체
    // => 즉, InvocationHandler 구현체
    //
    /*
    class MyHandler implements InvocationHandler {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // newProxyInstance가 생성한 객체에 대해 메서드를 호출할 때마다 이 메서드가 호출된다.
        System.out.println("==>" + method.getName());
        return 0;
      }
    }
    */
    
    // Calculator 인터페이스를 구현한 클래스를 만들고 그 인스턴스를 생성하여 리턴한다.
    Calculator c1 = (Calculator)Proxy.newProxyInstance(
        Calculator.class.getClassLoader(),
        new Class[] {Calculator.class},
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // newProxyInstance가 생성한 객체에 대해 메서드를 호출할 때마다 이 메서드가 호출된다.
            int a = (int) args[0]; // auto-unboxing => ((Integer)args[0]).intValue();
            int b = (int) args[1]; // auto-unboxing => ((Integer)args[1]).intValue();
            switch (method.getName()) {
              case "plus":
                return a + b;
              case "minus":
                return a - b;
            }
            return 0;
          }
        });
    System.out.println(c1.plus(10, 20));
    System.out.println(c1.minus(10, 20));
  }
}
