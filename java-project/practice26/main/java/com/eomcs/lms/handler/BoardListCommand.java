package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {
  Scanner keyboard;
  List<Board> list;
  
  public BoardListCommand(Scanner keyboard, List<Board> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
    Board[] lists = list.toArray(new Board[0]);
    for (Board temp : lists) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          temp.getNo(), temp.getContents(),
          temp.getCreatedDate(), temp.getViewCount());
    }
  }
}
