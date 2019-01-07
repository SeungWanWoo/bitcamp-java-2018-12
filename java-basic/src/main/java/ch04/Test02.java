// # 데이터 타입과 연산자
package ch04;

public class Test02 {
  public static void main(String[] args) {
    // 데이터 타입에 따라 제공되는 연산자가 다르다.
    System.out.println(5.75 % 0.24);  //OK!
    // boolean 타입에 대해서는 산술 연산자를 사용할 수 없다.
    // System.out.println(true % false); //컴파일오류!
    // System.out.println(true + true); //컴파일오류!
    
    //OK! '+' 연산자는 문자열 연결 용도로 사용
    System.out.println("Hello," + "world!");
    //System.out.println("Hello," - "0,"); //컴파일오류!
    
    //System.out.println("Hello," * 5); //컴파일오류!
    // 하지만 위의 경우 Python 언어에서 가능할 수 있다.
    
    System.out.println(true && true); //OK!
    //System.out.println(10 && 10); // 컴파일 오류!
  }
}