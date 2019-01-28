// Java Collection API - Hashtable에서 iterator를 얻은 후 값을 변경할 때
//                        - 실행오류 발생
package ch20.d;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Test08 {

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
    
    Hashtable<String, Student> table = new Hashtable<>();
    
    table.put("aaa", new Student("홍길동", 20));
    table.put("bbb", new Student("임꺽정", 30));
    table.put("ccc", new Student("안중근", 25));

    Set<String> keySet = table.keySet();
    
    // Set에서 값을 꺼내기 위해 Iterator의 도움을 받는다.
    Iterator<String> iterator = keySet.iterator();
    
    // Iterator를 통해 키 목록에서 값을 한 개씩 꺼낸다.
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    
    // Iterator를 사용하는 중에 Set의 값을 변경하면
    //  더이상 Iterator는 유효하지 않게 된다.
    //  따라서 다음과 같이 Iterator를 사용하려하면 실행 오류가 발생한다.
    //  => 동기화 관련 실행 오류!
    //
    //  해결책!
    //  => 값을 변경하면 다시 Iterator를 얻어야 한다.
    //  
    table.put("bba", new Student("bba", 25));
    table.put("bbc", new Student("bbc", 25));
    
    System.out.println(iterator.next());
    
  }
}

/* # HashMap과 Hashtable의 비교
 *  항목                                    HashMap                Hashtable
 *  ---------------------------------------------------------------------------------
 *  1)key, value null 허용        |        Yes          |            No
 *  2)Tread-safe                |        No          |           Yes
 *  3)멀티 스레드 환경에서 실행 속도    |      속도 빠름         |           속도 느림
 *   4)고수준의 동기화 처리            |         No          | ConcurrentHashMap사용 권장   
 */
