// Java Collection API - Hashtable에서 value 목록 꺼내기 
package ch20.d;

import java.util.Enumeration;
import java.util.Hashtable;

public class Test09 {

  public static void main(String[] args) {

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
    
    Hashtable<String, Student> map = new Hashtable<>();
    
    map.put("aaa", new Student("홍길동", 20));
    map.put("bbb", new Student("임꺽정", 30));
    Student s = new Student("안중근", 25);
    map.put("ccc", s);
    map.put("ddd", s);
    // value 목록 꺼내기
    // Enumeration은 Connection 인터페이스하고 관계가 있지 않다.
    // Enumeration은 for문을 사용할 수 없는 이유이다.
    Enumeration<Student> values = map.elements();
    while (values.hasMoreElements()) {
      System.out.println(values.nextElement());
    }
  }
}

/*
 * #            
 * 항목                       List              Set               Map
 * 1) 중복 저장         |      가능        |       불가능      | key 불가능, value 가능
 * 2) null 허용 여부  |      가능        |     가능(한개만)   | HashMap(key/value 가능),
 *                                                   Hashtable(key/value 불가능)
 * 
 */
