// Java Collection API - java.util.Set 인터페이스를 구현한 클래스 사용법
//                     - add(), Iterator(), toArray()
package ch20.c;

import java.util.HashSet;
import java.util.Iterator;

public class Test01 {
  
  public static void main(String[] args) {
    HashSet<String> set = new HashSet<>();
    
    // Set에 값 추가하기
    set.add("aaa");
    set.add("bbb");
    set.add("ccc");
    
    // Set은 집합의 특성을 따른다.
    //  => 같은 값을 중복해서 넣을 수 없다.
    set.add("aaa");
    set.add("bbb");
    
    // => null을 넣을 수 있다. 단, 중복해서 넣을 수 없다.
    set.add(null);
    set.add(null);
    
    // Set에 들어 있는 값 꺼내기
    //  => 직접 값을 한 개씩 꺼내는 메서드가 없다.
    //  => 꺼내주는 객체(iterator)의 도움을 받아야 한다.
    //
    Iterator<String> iterator = set.iterator();
    
    // 출력되는 값이 이상하다? aaa -> ccc -> bbb
    //                   aaa -> bbb -> ccc 가 아니라 위처럼 나온다.
    // 입력된 순서로 저장되는 것이 아니라 HashCode()를 사용하여 위치를 선정하기 때문에
    // 순서대로 나온다는 보장이 없다. 순서대로 출력하고 싶다면 HashSet을 사용해서는 안된다.
    // 순서대로 뽑고 싶다면 List<E> 라는 인터페이스를 사용한 클래스들을 사용해야 한다.
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
    
    // 출력 결과를 보면 입력 순서로 꺼낼 수 없다.
    // 이유?
    // => 저장할 때 각 인스턴스에 대해 hashCode()의 리턴 값으로 위치를 정하기 때문이다.
    //
    
    // toArray()를 사용해서 출력
    System.out.println("-------------------------------");
    Object[] values = set.toArray();
    for (Object value : values) {
      System.out.println(value);
    }
    
    System.out.println("-------------------------------");
    
    // 입력한 값의 타입으로 배열을 받고 싶다면?
    // => 파라미터로 넘겨 준 배열이 값을 담을 만큼 크지 않다면, 새 배열을 만들어 리턴한다.
    String[] temp = new String[0];
    String[] values2 = set.toArray(temp);
    System.out.println(temp == values2); //false
    for (String value : values2) {
      System.out.println(value);
    }
    
    System.out.println("-------------------------------");
    
    // 입력한 값의 타입으로 배열을 받고 싶다면?
    // => 파라미터로 넘겨 준 배열이 값을 담을 만큼 충분히 크다면, 새 배열을 리턴하지 않는다.
    String[] values3 = new String[set.size()];
    String[] values4 = set.toArray(values3);
    System.out.println(values3 == values4); //true
    for (String value : values2) {
      System.out.println(value);
    }
  }
}
