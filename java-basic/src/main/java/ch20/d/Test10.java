// Java Collection API - custom key 사용
package ch20.d;

import java.util.Hashtable;

public class Test10 {

  public static void main(String[] args) {

    class Key {
      int major;
      int minor;
      
      public Key(int major, int minor) {
        this.major = major;
        this.minor = minor;
      }

      @Override
      public String toString() {
        return "Key [major=" + major + ", minor=" + minor + "]";
      }
      
    }
    class Student {
      String name;
      int age;

      public Student(String name, int age) {
        this.name = name;
        this.age = age;
      }

      @Override
      public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
      }
    }
    
    Key k1 = new Key(100, 1);
    Key k2 = new Key(100, 2);
    Key k3 = new Key(100, 3);
    
    Hashtable<Key, Student> map = new Hashtable<>();
    
    map.put(k1, new Student("홍길동", 20));
    map.put(k2, new Student("임꺽정", 30));
    map.put(k3, new Student("윤봉길", 20));
    
    System.out.println(map.get(k1));
    System.out.println(map.get(k2));
    System.out.println(map.get(k3));
    
    Key k4 = new Key(100, 2);
    // k2와 같은 조건을 가졌는데 왜 널값?
    System.out.println(map.get(k4)); // null
    // 이유
    System.out.println(k2 == k4);   //false
    System.out.println(k2.hashCode() == k4.hashCode()); //false
    System.out.println(k2.equals(k4));  //false
    
    // 저장할 때,
    // => Key 객체의 hashCode() 리턴 값으로 위치를 계산하여 저장한다.
    //
    // 값을 꺼낼 때,
    // => Key 객체의 hashCode() 리턴 값으로 위치를 계산한다.
    // => Key 객체의 equals()의 리턴 값으로 같은 Key 인지 검사한다.
    //
    // 결론!
    // => 위의 k2와 k4는 비록 인스턴스가 다르더라도 인스턴스 필드의 값은 같다.
    // => 그러나 hashCode()가 다르기 때문에 위치계산 결과가 달라진다.
    // => 또한 equals()의 리턴 값이 false이기 때문에 다른 키로 인식한다.
    // => 그래서 k2로 저장한 값을 k4로 꺼낼 수 없다.
    //
    // 해결책?
    // => 인스턴스가 다르더라도 인스턴스 필드의 값이 같을 때는
    //   hashCode()의 리턴 값이 같게 하라!
    //    또한 equals()의 리턴 값이 true가 되게 하라!
  }
}

