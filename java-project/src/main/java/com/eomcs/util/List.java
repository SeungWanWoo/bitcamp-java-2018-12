package com.eomcs.util;

public interface List<E> {
  Object[] toArray();
  <T> T[] toArray(T[] sampleArr);
  void add(E obj);
  E get(int index);
  E set(int index, E value);
  E remove(int index);
  int size();
}
