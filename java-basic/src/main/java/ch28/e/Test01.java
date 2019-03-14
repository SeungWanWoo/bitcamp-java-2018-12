// 애노테이션 property
// => 메서드 선언하는 문법과 비슷하다.
// => 기본 값을 지정하지 않으면 필수 입력이다.
// => value라는 이름을 갖는 프로퍼티의 경우
//    값을 지정할 때 이름을 생략할 수 있다.
//
package ch28.e;

//@MyAnnotation // 필수 property가 있는 경우 값을 반드시 지정해야 한다.
public class Test01 {
  public static void main(String[] args) {
    @MyAnnotation(value="okok")
    int a;
    
    @MyAnnotation("okok") // value 이름 생략하기
    int b;
    
    @MyAnnotation2(value2="okok")
    int c;
    
    //@MyAnnotation2("okok") // property이름이 value가 아니면 생략할 수 없다.
    int d;
    
    @MyAnnotation3(value="okok")
    int e;
    
    @MyAnnotation3() // default가 선언되어 있으면 값을 빼도된다. => 선택 입력
    // property 값 입력이 선택 항목인 경우 값 설정 생략 가능
    int f;
    
    @MyAnnotation3 // property 값을 지정하지 않으면 괄호() 생략 가능 
    int g;
  }
}