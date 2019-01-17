package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class BoardList {
  static final int LENGTH = 10;
  Board[] boards;
  int boardIdx = 0;

  public BoardList() {
    boards = new Board[LENGTH];
  }

  public BoardList(int length) {
    if (length > LENGTH)
      boards = new Board[length];
    else 
      boards = new Board[LENGTH];
  }

  public Board[] toArray() {
    return Arrays.copyOf(boards, boardIdx);
  }

  public void add(Board board) {
    if (boardIdx >= boards.length) {
      int olderLength = boards.length;
      int newLength = olderLength + (olderLength / 2);
      boards = Arrays.copyOf(boards, newLength);
    }
    boards[boardIdx++] = board;
  }
}
