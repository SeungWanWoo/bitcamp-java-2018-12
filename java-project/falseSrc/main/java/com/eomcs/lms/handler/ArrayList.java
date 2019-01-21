package com.eomcs.lms.handler;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList<E>{
  static final int LENGTH = 3;
  Object[] arr;
  int size;

  public ArrayList(){
    this.arr = new Object[LENGTH];
  }

  public ArrayList(int length) {
    if (length > LENGTH)
      this.arr = new Object[length];
    else
      this.arr = new Object[LENGTH];
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] sampleArr) {
    /*E[] list = Arrays.copyOf(sampleArr, size);
    for (int i = 0; i < size; i++) {
      list[i] = (E) arr[i];
    }
    return list;*/
    return (E[]) Arrays.copyOf(arr, size, sampleArr.getClass());
    //                         원래 배열, 늘릴크기, 옮길 배열 타입
  }

  public void add(E obj) {
    System.out.printf("인덱스의 위치 : %d\n", size);
    System.out.printf("배열의 크기 : %d\n", arr.length);

    if (size >= arr.length) {
      int oldCapacity = arr.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      arr = Arrays.copyOf(arr, newCapacity);
      System.out.printf("늘어난 배열의 크기 : %d\n", newCapacity);
    }
    arr[size++] = obj;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    // index: 값을 꺼낼 배열의 항목 위치
    return (E) arr[index];
  }

  @SuppressWarnings("unchecked")
  public E set(int index, E value) {
    // index: 값을 배열의 항목 위치
    // value: 해당 위치에 있는 값을 대체할 값
    // 리턴 값 : 대체되기 전의 이전 값
    this.arr[index] = value;
    return (E) arr[index];
  }

  @SuppressWarnings("unchecked")
  public E remove(int index) {
    // index : 삭제할 배열의 항목 위치
    // 리턴값 : 삭제된 이전 값
    Object[] dest = Arrays.copyOf(arr, arr.length-1);
    this.arr[index] = null;
    
    System.arraycopy(this.arr, index+1, dest, index, (arr.length - 1) - index);
    // 힌트: System.arraycopy()참고
    return (E) dest[dest.length];
  }

}
