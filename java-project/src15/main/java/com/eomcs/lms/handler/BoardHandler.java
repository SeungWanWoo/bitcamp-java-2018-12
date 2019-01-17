package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {

  // 1. static에서 instance로 변경
  // 2. 클래스 내부에서만 사용하므로 전체 공개할 필요 없음 public -> default
  Scanner keyboard;  
  static final int LENGTH = 10;
  Board[] boards = new Board[LENGTH];
  int boardIdx = 0;
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  // static 메서드는 this라는 내장객체가 없기 때문에 인스턴스 필드가 접근할 수 없다.
  public void listBoard() {
    for (int j = 0; j < boardIdx; j++) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          boards[j].getNo(), boards[j].getContents(), 
          boards[j].getCreatedDate(), boards[j].getViewCount());
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
    
    boards[boardIdx] = board;
    boardIdx++;
    
    System.out.println("저장하였습니다.");
  }
}
