package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {
  static final int LENGTH = 3;
  Object[] list;
  int size;

  public ArrayList(){
    this.list = new Object[LENGTH];
  }

  public ArrayList(int length) {
    if (length > LENGTH)
      this.list = new Object[length];
    else
      this.list = new Object[LENGTH];
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] sampleArr) {
    /*E[] list = Arrays.copyOf(sampleArr, size);
    for (int i = 0; i < size; i++) {
      list[i] = (E) arr[i];
    }
    return list;*/
    return (E[]) Arrays.copyOf(list, size, sampleArr.getClass());
    //                         원래 배열, 늘릴크기, 옮길 배열 타입
  }

  public void add(E obj) {
    System.out.printf("인덱스의 위치 : %d\n", size);
    System.out.printf("배열의 크기 : %d\n", list.length);

    if (size >= list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
      System.out.printf("늘어난 배열의 크기 : %d\n", newCapacity);
    }
    list[size++] = obj;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= size)
      return null;
    // index: 값을 꺼낼 배열의 항목 위치
    return (E) list[index];
  }

  @SuppressWarnings("unchecked")
  public E set(int index, E value) {
    if (index < 0 || index >= size)
      return null;
    // index: 값을 배열의 항목 위치
    // value: 해당 위치에 있는 값을 대체할 값
    // 리턴 값 : 대체되기 전의 이전 값
    E obj = (E) list[index];
    
    list[index] = value;
    
    return obj;
  }

  @SuppressWarnings("unchecked")
  public E remove(int index) {
    // 인덱스가 유효한지 확인
    if (index < 0 || index >= size)
      return null;
    
    E obj = (E) list[index];
    
    // 한 칸씩 앞으로 당기기
    for (int i = index; i < size - 1; i++) 
      this.list[i] = this.list[i+1];
    //System.arraycopy(this.list, index + 1, this.list, index, size - 2);
    size--;
   // index : 삭제할 배열의 항목 위치
    
    // 힌트: System.arraycopy()참고
    
    // 리턴값 : 삭제된 이전 값
    return obj;
  }

  public int size() {
    return this.size;
  }

}
