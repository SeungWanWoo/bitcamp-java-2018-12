// 제네릭 적용하기

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

  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    T[] arr =(T[]) Array.newInstance(a.getClass().getComponentType(), size());
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
    cursor.next.prev = cursor.prev;
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

  public Iterator<E> iterator() {
    return new Iterator<E> () {

      int index = 0;
      @Override
      public boolean hasNext() {
        return index < size;
      }

      @Override
      public E next() {
        return (E) get(index++);
      }
    };
  }


}
