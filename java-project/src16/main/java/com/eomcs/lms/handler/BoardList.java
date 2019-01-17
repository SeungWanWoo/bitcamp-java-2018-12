package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class BoardList {
  static final int LENGTH = 3;
  Board[] arr = new Board[LENGTH];
  int size;

  public BoardList() {
    arr = new Board[LENGTH];
  }

  public BoardList(int length) {
    if (length > LENGTH)
      arr = new Board[length];
    else 
      arr = new Board[LENGTH];
  }

  public Board[] toArray() {
    Board[] a = new Board[size];
    for (int i = 0; i < size; i++) {
      a[i] = this.arr[i];
    }
    return a;
  }

  public void add(Board board) {
    System.out.printf("인덱스의 위치 : %d\n", size);
    System.out.printf("배열의 크기 : %d\n", arr.length);
    if (size == arr.length) {
      Board[] a = new Board[arr.length + (arr.length >> 1)];
      for (int i = 0; i < arr.length; i++) {
        a[i] = this.arr[i];
      }
      arr = a;
      System.out.printf("배열의 크기 : %d\n", arr.length);
      
      //arr = Arrays.copyOf(arr, newLength);
    }
    arr[size++] = board;
  }
}


