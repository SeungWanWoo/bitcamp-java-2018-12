package com.eomcs.lms.handler;

public class ArrayList {
  static final int LENGTH = 3;
  Object[] arr;
  int size;

  public ArrayList(Object[] mem) {
    this.arr = new Object[mem.length];
  }

  public Object[] toArray() {
    Object[] a = new Object[size];
    for (int i = 0; i < size; i++) {
      a[i] = this.arr[i];
    }
    return a;
  }

  public void add(Object obj) {
    System.out.printf("인덱스의 위치 : %d\n", size);
    System.out.printf("배열의 크기 : %d\n", arr.length);
    if (size == arr.length) {
      Object[] a = new Object[arr.length + (arr.length >> 1)];
      for (int i = 0; i < arr.length; i++) {
        a[i] = this.arr[i];
      }
      arr = a;
      System.out.printf("배열의 크기 : %d\n", arr.length);
    }
    arr[size++] = obj;
  }
}
