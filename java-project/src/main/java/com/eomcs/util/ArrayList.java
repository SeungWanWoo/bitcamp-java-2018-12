package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {
  static final int DEFAULT_CAPACITY = 10;
  Object[] list;
  int size = 0;

  public ArrayList() {
    list  = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity > DEFAULT_CAPACITY)
      list = new Object[initialCapacity];
    else
      list = new Object[DEFAULT_CAPACITY];
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) {
      return (E[]) Arrays.copyOf(list, size, a.getClass());
    }
    System.arraycopy(list, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }

  public void add(E obj) {
    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
    }

    list[size++] = obj;
  }
  public int size() {
    return this.size;
  }
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= size())
      return null;
    return (E) list[index];
  }
  
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if (index < 0 || index >= size())
      return null;
    E obj = (E) new Object(); 
    for (int i = index; i < size - 1; i++) {
      list[i] = list[i + 1];
    }
    size--;
    
    return obj;
  }
  
  @SuppressWarnings("unchecked")
  public E set(int index, E values) {
    if (index < 0 || index >= size())
      return null;
    E obj = (E) new Object(); 
    list[index] = values;
    
    return obj;
  }
}
