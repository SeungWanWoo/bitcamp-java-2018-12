package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  BoardList boardList;
  Scanner keyboard;  
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.boardList = new BoardList(5);
  }
  
  // 본인 크기는 크지만 리턴된 배열만큼만 출력한다.
  public void listBoard() {
    Board[] boards = boardList.toArray();
    for (Board board : boards) {
      System.out.printf("%3d, %-20s, %s, %d\n", 
          board.getNo(), board.getContents(), 
          board.getCreatedDate(), board.getViewCount());
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
    
    boardList.add(board);
    
    System.out.println("저장하였습니다.");
  }
}
