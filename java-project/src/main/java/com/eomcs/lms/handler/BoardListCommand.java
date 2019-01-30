package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardListCommand {
  Scanner keyboard;
  List<Board> list;
  
  public BoardListCommand(Scanner keyboard, List<Board> list) {
    this.keyboard = keyboard;
    this.list = list;
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

  public void detailBoard() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    Board board = list.get(index);
    
    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getCreatedDate());
    
    System.out.println();
  }

  public void deleteBoard() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    list.remove(index);
    System.out.println("게시글을 삭제했습니다.");
  }
  public void updateBoard() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    Board board = list.get(index);
    Board temp = new Board();
    
    temp.setNo(board.getNo());
    
    System.out.printf("내용(%s): ", board.getContents());
    String input = keyboard.nextLine();
    temp.setContents(input.length() > 0 ? input : board.getContents());
    
    temp.setCreatedDate(new Date(System.currentTimeMillis()));
    list.set(index, temp);
    System.out.println("게시글을 변경했습니다.");
  }

  private boolean validate(int index) {
    if (index == -1)
      return false;
    
    return true;
  }

  private int indexOf(int index) {
    Board[] temp = list.toArray(new Board[0]);
    for (int i = 0; i < list.size(); i++) {
      if(temp[i].getNo() == index)
        return i;
    }
    return -1;
  }

  private int prompt() {
    System.out.print("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }


}
