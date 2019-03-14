package ch28.e;

public @interface MyAnnotation3 {
  String value() default "오호라"; // 선택 입력 => default 값이 있으면 입력을 안해줘도 된다.
}
