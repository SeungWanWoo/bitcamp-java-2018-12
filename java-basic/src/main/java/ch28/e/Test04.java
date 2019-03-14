// 애노테이션 property 추출하기
// => 메서드 선언하는 문법과 비슷하다.
// => 기본 값을 지정하지 않으면 필수 입력이다.
// => value라는 이름을 갖는 프로퍼티의 경우
//    값을 지정할 때 이름을 생략할 수 있다.
//
package ch28.e;

import java.lang.annotation.Annotation;

@MyAnnotation7(
    name="홍길동",
    age=20)
public class Test04 {
  public static void main(String[] args) {
    Class<?> clazz = Test04.class;
    MyAnnotation7 anno = clazz.getAnnotation(MyAnnotation7.class);
    for (String s : anno.value()) {
      System.out.println(" ==> " + s);
    }
    System.out.println(anno.name());
    System.out.println(anno.age());
    System.out.println(anno.working());
    System.out.println("-------------------------------------");
    
    // 위와 같다!
    MyAnnotation7 anno1 = Test04.class.getAnnotation(MyAnnotation7.class);
    for (String s : anno1.value()) {
      System.out.println(" ==> " + s);
    }
    System.out.println(anno1.name());
    System.out.println(anno1.age());
    System.out.println(anno1.working());
    System.out.println("-------------------------------------");
    
    // 이러한 방법도 있다.
    Annotation[] annotations = Test04.class.getAnnotations();
    
    // 클래스에서 애노테이션 배열을 받았을 경우 형변환해서 사용하면
    // 애노테이션의 property 값을 추출할 수 있다.
    MyAnnotation7 anno3 = (MyAnnotation7) annotations[0];
    for (String s : anno3.value()) {
      System.out.println(" ==> " + s);
    }
    System.out.println(anno3.name());
    System.out.println(anno3.age());
    System.out.println(anno3.working());
    System.out.println("-------------------------------------");
  }
}