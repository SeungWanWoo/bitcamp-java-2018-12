package com.eomcs.util;

// 스택에 복제 기능 활성화 시키기
public class Stack<E> implements Cloneable {

  public static final int DEFAULT_SIZE = 5;
  Object[] list;
  int size;

  public Stack() {
    list = new Object[DEFAULT_SIZE];
  }

  public void push(E value) {
    // 맨 마지막에 추가한다.
    // 배열의 크기가 작다면 확장해야 한다.
    if (list.length == size) {
      int oldLength = list.length;
      int newLength = oldLength + (oldLength >> 1);
      Object[] temp = new Object[newLength];
      for (int i = 0; i < oldLength; i++) {
        temp[i] = list[i];
      }
      list = temp;
    }
    list[size++] = value; 
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    // 맨 마지막 값을 꺼내 리턴한다.
    // 꺼낸 값을 배열에서 제거되어야 한다.
    if (size == 0)
      return null;
    
    return (E) list[--size];
  }

  public boolean empty() {
    return size == 0;
  }

  public int size() {
    return size;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public Stack<E> clone() throws CloneNotSupportedException {
    return (Stack<E>) super.clone();
  }
}
