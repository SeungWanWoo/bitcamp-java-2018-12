package com.eomcs.util;

import java.lang.reflect.Array;

public class LinkedList<E> {
  protected Node<E> head;
  protected Node<E> tail;
  protected int size;
  
  public LinkedList() {
    head = new Node<>();
    tail = head;
    size = 0;
  }
  
  public int size() {
    return size;
  }
  public void add(E value) {
    tail.value = value;
    Node<E> node = new Node<>();
    tail.next = node;
    node.prev = tail;
    tail = node;
    size++;
  }
  
  public Object[] toArray() {
    Object[] temp = new Object[size()];
    Node<E> cursor = head;
    for (int i = 0; i < size(); i++) {
      temp[i] = cursor.value;
      cursor = cursor.next;
    }
    return temp;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    T[] temp = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
    Node<E> cursor = head;
    for (int i = 0; i < size(); i++) {
      temp[i] = (T) cursor.value;
      cursor = cursor.next;
    }
    return temp;
  }
  
  public E get(int index) {
    if (index < 0 || index >= size())
      return null;
    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }
  
  public E set(int index, E value) {
    if (index < 0 || index >= size())
      return null;
    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    E temp = cursor.value;
    cursor.value = value;
    return temp;
  }
  
  public int insert(int index, E value) {
    if (index < 0 || index >= size())
      return -1;
    Node<E> cursor = head;
    for (int i = 1; i <= index; i++) {
      cursor = cursor.next;
    }
    Node<E> newNode = new Node<>(value);
    if (cursor.prev != null) {
      cursor.prev.next = newNode;
    } else {
      head = newNode;
    }
    newNode.prev = cursor.prev;
    cursor.prev = newNode;
    newNode.next = cursor;
    size++;
    return 0;
  }
  
  public E remove(int index) {
    if (index < 0 || index >= size())
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
    E temp = cursor.value;
    cursor.value = null;
    cursor.next = null;
    cursor.prev = null;
    size--;
    return temp;
  }
  
  private static class Node<E> {
    Node<E> prev;
    Node<E> next;
    E value;
    
    Node() {}
    Node(E value) {
      this.value = value;
    }
  }
}
