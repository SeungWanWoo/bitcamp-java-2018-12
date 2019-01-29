package com.eomcs.util;

import java.lang.reflect.Array;

public class LinkedList<E> {
  
  protected Node<E> head;
  protected Node<E> tail;
  int size;
  
  public LinkedList() {
    head = new Node<>();
    tail = head;
    size = 0;
  }
  
  public int size() {
    return size;
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
    T[] temp = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
    
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
    
    for (int i = 1; i <= index; i++)
      cursor = cursor.next;
    return (E) cursor.value;
  }
  
  public E set(int index, E value) {
    if (index < 0 || index >= size())
      return null;
    
    Node<E> cursor = head;
    
    for (int i = 1; i <= index; i++)
      cursor = cursor.next;
    
    
    E temp = (E) cursor.value;
    cursor.value = value;
    
    return temp;
  }
  
  public E remove(int index) {
    if (index < 0 || index >= size())
      return null;
    Node<E> cursor = head;
    
    for (int i = 1; i <= index; i++)
      cursor = cursor.next;
    
    E temp = (E) cursor.value;
    
    if (cursor.prev != null) {
      cursor.prev.next = cursor.next;
    } else
      head = cursor.next;
    
    size--;
    return temp;
  }
  
  public E insert(int index, E value) {
    if (index < 0 || index >= size())
      return null;
    Node<E> cursor = head;
    
    for (int i = 1; i <= index; i++)
      cursor = cursor.next;
    
    E temp = (E) cursor.value;
    Node<E> newNode = new Node<>(value);
    
    newNode.next = cursor;
    newNode.prev = cursor.prev;
    
    if (cursor.prev != null) {
      cursor.prev.next = newNode;
    } else
      head = newNode;
    cursor.prev = newNode;
    
    
    return temp;
  }
  
  public void add(E value) {
    tail.value = value;
    Node<E> newNode = new Node<>();
    newNode.next = tail;
    newNode.prev = tail.prev;
    tail.prev = newNode;
    tail.prev.next = newNode;
    
    size++;
  }
  
  private class Node<E> {
    private Node<E> next;
    private Node<E> prev;
    private E value;
    
    Node() {}
    
    Node(E value) {
      this.value = value;
    }
  }
}
