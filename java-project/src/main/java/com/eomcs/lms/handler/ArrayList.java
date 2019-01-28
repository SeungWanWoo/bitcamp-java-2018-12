package com.eomcs.lms.handler;

import java.lang.reflect.Array;

public class ArrayList<E> {

  static final int LENGTH = 10;
  Object[] list;
  int size = 0;
  
  public ArrayList() {
    list = new Object[LENGTH];
  }
  
  public ArrayList(int length) {
    if (length > LENGTH)
      list = new Object[length];
    else
      list = new Object[LENGTH];
  }
  
  public int size() {
    return size;
  }
  
  public Object[] toArray() {
    Object[] temp = new Object[size()];
    for (int i = 0; i < size(); i++)
      temp[i] = list[i];
    
    return temp;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    T[] temp = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
    for (int i = 0; i < size(); i++)
      temp[i] = (T) list[i];
    
    return temp;
  }
  
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= size())
      return null;
    
    return (E) list[index];
  }
  
  @SuppressWarnings("unchecked")
  public E set(int index, E value) {
    if (index < 0 || index >= size())
      return null;
    
    E temp = (E) list[index];
    list[index] = value;
    
    return temp;
  }
  
  public void add(E value) {
    list[size++] = value;
  }
}
