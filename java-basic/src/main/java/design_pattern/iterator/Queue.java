// 제네릭 적용
package design_pattern.iterator;

import algorithm.data_structure.linkedlist3.LinkedList;

// Queue가 보관하는 데이터 타입을 E라고 가정하자.
// => E라고 가정한 상태에서 코드를 작성한다.
//
public class Queue<E> extends LinkedList<E> {

  public void offer(E value) {
    // 상속 받은 메서드를 사용하여 값을 추가하라.
    add(value);
  }

  public E poll() {
    // 상속 받은 메서드를 사용하여 값을 꺼내라.
    /*if (size == 0)
      return null;*/
    return remove(0);
  }

  public boolean empty() {
    // 상속 받은 필드나 메서드를 사용하여 값을 리턴하라.
    return head == tail;
  }

  public Iterator<E> iterator() {
    return new Iterator<E>() {
      // 이 클래스는 Queue에서 값을 꺼내주는 일을 전문적으로 한다.
      // => 이런 일을 하는 객체를 "Iterator"라 부른다.
      // 
      int index = 0;

      @Override
      public boolean hasNext() {
        return index < size();
      }

      @Override
      public E next() {
        return get(index++);
      }
    };
  }
}
