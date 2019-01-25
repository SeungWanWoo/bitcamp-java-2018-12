//List 사용 규칙에 맞춰서 LinkedList를 고친다.
package com.eomcs.util;

import java.lang.reflect.Array;

// LinkedList에 보관되는 값의 타입을 E라고 가정한다.
// => E 타입이라고 가정하고 코드를 작성한다.
// => E가 무슨 타입인지는 LinkedList를 사용할 때 결정된다.
//
public class LinkedList<E> implements Cloneable, List<E> {
  protected Node<E> head;
  protected Node<E> tail;
  protected int size;
  
  public LinkedList() {
    head = new Node<>();
    tail = head;
    size = 0;
  }
  
  public void add(E value) {
    tail.value = value;
    Node<E> node = new Node<>();
    tail.next = node;
    node.prev = tail;
    tail = node;
    size++;
  }
  
  public E get(int index) {
    if (index < 0 || index >= size)
      return null;
    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }
  
  public Object[] toArray() {
    Object[] arr = new Object[size()];
    Node<E> cursor = head;
    int i = 0;
    while (cursor != tail) {
      arr[i++] = cursor.value;
      cursor = cursor.next;
    }
    return  arr;
  }
  
  // T 라는 타입이 있다고 가정하자!
  //
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    T[] arr =null;
    if (a.length >= size()) {
      // 파라미터로 받은 배열의 크기가 리스트의 모든 항목을 담을 만큼 크다면 그대로 사용한다.
      // 배열을 새로 만들지 않고 그대로 사용한다.
      arr = a;
    } else {
      // 만약 파라미터로 받은 배열의 크기가 리스트의 항목 크기보다 작다면 새로 배열을 만든다.
      arr = (T[]) Array.newInstance(
          a.getClass() // getClass()의 리턴 값은 T가 아니라 T[] 이다.
          .getComponentType(), // getComponentType()의 리턴 값은 배열의 항목 타입이다. 즉, T 
          size());
    }
        
    Node<E> cursor = head;
    int i = 0;
    while (cursor != tail) {
      arr[i++] = (T) cursor.value;
      cursor = cursor.next;
    }
    return  arr;
  }
  
  public E set(int index, E value) {
    if (index < 0 || index >= size)
      return null;
    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    E old = cursor.value;
    cursor.value = value;
    return old;
  }
  public int insert(int index, E value) {
    if (index < 0 || index >= size)
      return -1;
    Node<E> node = new Node<>(value);
    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    node.next = cursor;
    node.prev = cursor.prev;
    cursor.prev = node;
    if (node.prev != null) {
      node.prev.next = node;
    } else {
      head = node;
    }
    size++;
    return 0;
  }
  
  public E remove(int index) {
    if (index < 0 || index >= size)
      return null;
    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    if (cursor.prev != null) {
      cursor.prev.next = cursor.next;
    } else {
      head = cursor.next;
    }
    if (cursor.next != null) {
      cursor.next.prev = cursor.prev;
    } else {
      tail = cursor.prev;
    }
    E old = cursor.value;
    cursor.value = null;
    cursor.prev = null;
    cursor.next = null;
    size--;
    return old;
  }
  
  public int size() {
    return size;
  }

  // Node가 다루는 값의 타입을 제네릭(Generic)으로 선언한다.
  //  => 즉, Node가 다루는 데이터의 타입을 E라고 명명하고 코드를 작성한다.
  // => Node 클래스를 사용하는 시점에 E가 무슨 타입인지 결정될 것이다.
  private static class Node<E> {
    E value;
    Node<E> next;
    Node<E> prev;
    
    Node() {}
    
    Node(E value) {
      this.value = value;
    }
  }
}
