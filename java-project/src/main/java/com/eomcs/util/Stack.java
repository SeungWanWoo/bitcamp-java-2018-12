package com.eomcs.util;

public class Stack<E> implements Cloneable{
  static final int DEFAULT_SIZE = 5;
  Object[] list;
  int size;
  
  public Stack() {
    list = new Object[DEFAULT_SIZE];
    size = 0;
  }
  
  public int size() {
    return size;
  }
  public void push(E value) {
    if (list.length == size()) {
      Object[] temp = new Object[list.length + (list.length >> 1)];
      for (int i = 0; i < list.length; i++) {
        temp[i] = list[i];
      }
      list = temp;
    }
    list[size++] = value;
  }
  
  @SuppressWarnings("unchecked")
  public E pop() {
    if(size == 0)
      return null;
    return (E) list[--size];
  }
  
  public boolean empty() {
    return size == 0;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public Stack<E> clone() throws CloneNotSupportedException {
    Stack<E> temp = new Stack<>();
    for (int i = 0; i < size(); i++) {
      temp.push((E) list[i]); 
    }
    return temp;
  }

}
