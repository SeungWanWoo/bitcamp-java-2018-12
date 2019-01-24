// Object 클래스 - getClass() 와 배열
package ch15;

public class Test13 {
  public static void main(String[] args) {
    String obj1 = new String();
    
    Class classInfo = obj1.getClass();
    System.out.println(classInfo.getName()); // java.lang.String
    
    String[] obj2 = new String[10];
    
    // 배열의 클래스 정보
    Class classInfo2 = obj2.getClass();
    System.out.println(classInfo2.getName()); //[Ljava.lang.String;
    // => 이런 출력을 만나면 현재 이 레퍼런스의 타입은 String 배열의 인스턴스이다.
    
    int[] obj3 = new int[10];
    classInfo2 = obj3.getClass();
    System.out.println(classInfo2.getName()); // [I
    // 기본 타입에 대해서도 출력이 가능하다.
    
    float[] obj4 = new float[10];
    classInfo2 = obj4.getClass();
    System.out.println(classInfo2.getName()); // [F
    
    double[] obj5 = new double[10];
    classInfo2 = obj5.getClass();
    System.out.println(classInfo2.getName()); // [D
  }
}
