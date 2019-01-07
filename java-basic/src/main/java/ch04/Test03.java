// # 연산의 결과는 피연산자의 데이터 타입과 같다.
package ch04;

public class Test03 {
  public static void main(String[] args) {
    // int와 int의 연산 결과는 int이다.
    float r = 5 / 2;    // r 변수에 저장하기 전에 이미 결과는 2 이다.
    System.out.println(r);
    
    // 해결책!
    // => 두 개의 정수 값을 제대로 float으로 계산하고 싶다면 형변환 하라!
    // => 형변환? 다른 타입의 메모리를 임시로 만들어 값을 넣는 방법
    //    주의! 형변환이라고 해서 원래 변수나 값의 타입을 다른 타입으로 변경하는 것이 아니다.
    //    그래서 '형변환(type conversion)'을 '타입 캐스팅(type casting)'이라고도 부른다.
    // => 형변환 문법
    //    (새로 만들 메모리의 타입) 값
    // => 다음과 같이 개발자가 형변환을 명시하는 경우,
    //    "명시적 형변환(explicit type conversion(casting))"이라고 부른다.
    float r2 = (float)5 / (float)2;
    // float 타입의 임시 메모리가 생성된 후 정수 5가 저장된다.
    // float 타입의 임시 메모리가 생성된 후 정수 2가 저장된다.
    // 임시 생성된 두 개의 float 메모리 값을 계산한다.
    // 당연히 그 결과는 float 타입이다.
    System.out.println(r2);
    
    // 타입이 다른 경우 연산을 수행할 수 없다.
    // => 반드시 계산을 수행하는 피연산자의 타입이 같아야한다.
    //    다르다면 내부적으로 두 피연산자의 값을 같은 타입으로 만든 후에 계산을 수행한다.
    //    즉, 개발자가 지시하지 않아도 내부적으로 같은 타입으로 만드는 것을 
    //    '암시적 형변환(implicit type conversion(casting)'이라고 한다.
    //
    float r3 = 5/ (float) 2;
    System.out.println(r3);
    
    byte b1 = 20; byte b2 = 30; 
    //byte b3 = b1 + b2; 
    // 컴파일 오류! 암시적 형변환에 의해 b1, b2의 값은 int 타입의 임시 메모리에 저장. 
    // 그런 후 계산된다.int와 int의 계산 결과는 당연히 int 이다.
    // 그래서 컴파일 오류인것이다.
    int x1 = b1 + b2; // OK!
    
    short s1 = 20; short s2 = 30;
    //short s3 = s1 + s2; // 위와 같다. 계산하기 전에 int 임시 메모리에 값이 저장된다.
    int x2 = s1 + s2; // OK!
    
    char c1 = 20; char c2 = 30;
    //char c3 = c1 + c2; //위와 같다. 컴파일 오류!
    int x3 = c1 + c2; // OK!
    
    int i1 = 100; float f1 = 200.5f;
    
    // int i2 = i1 + f1; // 컴파일 오류!
    float f2 = i1 + f1;
    
    // 주의! 
    float f3 = 9876.543f;
    float f4 = 12.34567f;
    double d1 = f3 + f4;
    System.out.println(d1); // 우리가 의도한대로 값이 나오질 않는다.
    // float과 float의 계산 결과는 float이기 때문이다.
    // d1에 계산되기 전에 자릿수(7자리)를 초과한 값은 오차 값으로 저장된다.
    // 그래서 d1을 출력하면 오차가 있는 값이 출력된다.
    
    // 해결책!
    // => 계산하기 전에 더 큰 타입으로 형변환해라!
    double d2 = (double)f3 + (double)f4;
    System.out.println(d2); // 역시 형변환하면서 값이 제대로 나오질 않는다.
    // 부동소수점의 오차이기 때문이다.
    // => 출력 결과를 보면 예상 결과와 다르게 나온다.
    //    이유? float을 double로 형변환할 때 오차가 이미 생긴다.
    //    부동소수점을 다룰 때 생기는 오차이다. 개발자가 제어할 수 없다.
    double d7 = 9876.543;
    double d8 = 12.34567;
    double d9 = d7 + d8;
    // 해결책?
    // => 부동소수점의 경우 계산결과가 float의 자릿수를 넘어갈 것 같으면
    //    처음부터 double에 저장해 놓고 계산해라.
    System.out.println(d9);
    // 부동소수점 사용할 때는 그냥 double 타입을 사용해라.
    
    int i; //운영체제 bit 수의 따라 크기가 다르지만 java는 4바이트로 고정되어있다.
  }
}

/*
 * 메모리 아깝다고 괜히 short, byte, char, float을 사용하지 마라
 * 오히려 시스템 속도를 더욱 느리게 하는 안좋은 행동이다.
 * 그래서 매우 적은 값을 저장할 때에도 int로 넣는다.
 * 
 * long 값에서 float으로 변환할 때 값이 짤릴 수 있다. long값이 float의 범위를 넘어갈 
 * 경우에는 float형태로 저장하지 말고 double형태로 저장해라.
 * 하지만 계산 결과가 그렇게 크지 않고 *, /를 하지 않는다면 float을 써도 괜찮다.
 * Ex) 사람 키, 몸무게 등
 * 
 * 또한 요즘 기본 cpu에서도 64비트를 사용하기 때문에 8바이트 메모리를 사용하는게 좋다.
 * 
 * # 암시적 형변환의 규칙
 *  - 서로 다른 타입과 연산을 수행한다면 다음 규칙에 따라 오른쪽 타입으로 자동 형변환을 수행함.
 *   byte, short(-32768~32767), char(0~65535) 
 *   => int => long =[굉장히 위험한 과정]> float => double
 *   
 *   Ex1) byte + short + char - float + long + int + double
 *      =>(int) + (int) : int 임시 메모리가 생성되고 byte 값이 저장된다.
 *         주의! byte, short, char를 연산할 때는 무조건 int로 형변환 한 다음에 수행한다.
 *         자바의 최소 연산 단위는 int이다.
 *      =>    (int)    + char
 *      =>    (int)    + (int)
 *      =>          (int)       -float
 *      =>          (float)     -float
 *      =>                  (float)     + long
 *      =>                  (float)     + (float)
 *      =>                         (float)      + int
 *      =>                         (float)      + (float)
 *      =>                                (float)    + (double)
 * # float 과 long 타입을 계산하려고 할때
 * float f = 10.254f;
 * long l = 27,475,558;
 * 위 변수에서 float과 long의 두 형태에 대해서는 조건을 모두 만족을 하고 있다.
 * 그러나 두 값을 더하게 됬을 경우 long 형태가 암시적 형변환에 의해서 float으로 변하게 되는데
 * long 변수의 값이 7자리를 벗어나기 때문에 뒷자리가 짤리게 된다.
 * 그러므로 결과값이 잘못되어지게 되는 것이다.
 * 이 경우, 해결책은 float 값을 애초에 double 타입으로 바꿔준 다음에 계산을 실행하면 된다.
 * 결론! 정수와 부동소수점을 계산하면 정수가 부동소수점의 형태로 변환되고 결과값이 부동소수점으로 
 * 나온다.
 * float을 다룰때에는 기본적으로 double로 사용하는 것이 편하다.
 * byte, short, char는 기본적으로 int 형식으로 변환된다.
 */