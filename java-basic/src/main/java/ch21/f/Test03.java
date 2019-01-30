// 예외 처리 - try-with-resources 문법 : 문법 확인
package ch21.f;

public class Test03 {

  static class MyResources1 {
    public int divide(int a, int b) {
      return a / b;
    }
    public void close() {
      System.out.println("MyResource1.close()");
    }
  }
  static class MyResources2 implements AutoCloseable {
    public int divide(int a, int b) {
      return a / b;
    }
    @Override
    public void close() {
      System.out.println("MyResource2.close()");
    }
  }
  public static void main(String[] args) {

    try (
        //java.lang.AutoCloseable을 구현하지 않은 객체는 선언할 수 없다.
        // MyResources1 r1 = new MyResources1(); // 컴파일 오류!
        // String str = "Hello"; // 컴파일 오류!

        //java.lang.AutoCloseable을 구현한 클래스는 객체를 선언할 수 있다.
        MyResources2 r2 = new MyResources2(); // OK!
        
        ) {
      System.out.println(r2.divide(10, 2));
      System.out.println(r2.divide(10, 0));
      
      // try 블록을 벗어나기 전에 close()가 자동 호출된다.
      
    } catch (Exception e){
      System.out.println("예외 발생!");
    }
  }
}
