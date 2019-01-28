package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  Scanner keyboard;
  ArrayList<Board> list;
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    list = new ArrayList<>();
  }
  
  public void listBoard() {
    Board[] lists = list.toArray(new Board[0]);
    for (Board temp : lists) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          temp.getNo(), temp.getContents(),
          temp.getCreatedDate(), temp.getViewCount());
    }
  }

  public void addBoard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("내용? ");
    board.setContents(keyboard.nextLine());
    
    board.setCreatedDate(new Date(System.currentTimeMillis())); 
    
    board.setViewCount(0);
    
    list.add(board);
    System.out.println("저장하였습니다.");
  }
}
