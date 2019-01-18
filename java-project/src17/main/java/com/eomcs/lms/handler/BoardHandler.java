package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  ArrayList ArrayList;
  Scanner keyboard;  
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.ArrayList = new ArrayList(5);
  }
  
  public void listBoard() {
    Object[] boards = ArrayList.toArray();
    for (Object board : boards) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          ((Board)board).getNo(), ((Board) board).getContents(), 
          ((Board) board).getCreatedDate(), ((Board) board).getViewCount());
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
    
    ArrayList.add(board);
    
    System.out.println("저장하였습니다.");
  }
}
