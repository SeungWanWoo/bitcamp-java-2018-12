// Java Collection API - custom key 사용
package ch20.d;

import java.util.Hashtable;

public class Test11 {

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

      @Override
      public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + major;
        result = prime * result + minor;
        return result;
      }

      @Override
      public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (!(obj instanceof Key))
          return false;
        Key other = (Key) obj;
        if (major != other.major)
          return false;
        if (minor != other.minor)
          return false;
        return true;
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
    System.out.println(k2.hashCode() == k4.hashCode()); // 
    System.out.println(k2.equals(k4));  // 
    // String이 아닌 사용자 임의 key를 사용하려면 hashCode()와 equals()를 오버라이딩하라
    
    
  }
}

