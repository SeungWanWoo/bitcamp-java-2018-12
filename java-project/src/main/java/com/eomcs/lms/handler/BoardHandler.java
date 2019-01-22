package com.eomcs.lms.handler;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;

public class BoardHandler {
  
  Scanner keyboard;
  ArrayList<Board> list;
  
  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList<Board>(5);
  }
  
  public void listBoard() {
    Object[] objs = list.toArray();
    for (Object obj : objs) {
      Board board = (Board) obj;
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
    
    list.add(board);
    
    System.out.println("저장하였습니다.");
  }

  public void detailBoard() {
    int no = promptNo();
    int index = indexOf(no);
    if(!validate(index))
      return;
    
    Board board = list.get(index);
    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getCreatedDate());
  }
  
  public void deleteBoard() {
    int no = promptNo();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    list.remove(index);
    System.out.println("해당 게시판을 삭제했습니다.");
  }
  
  public void updateBoard() {
    int no = promptNo();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    Board board = list.get(index);
    Board temp = new Board();
    
    temp.setNo(board.getNo());
    
    System.out.printf("내용(%s): ", board.getContents());
    String input = keyboard.nextLine();
    
    temp.setContents(input.length() > 0 ? input : board.getContents());
    temp.setCreatedDate(input.length() > 0 ? 
        new Date(System.currentTimeMillis()) : board.getCreatedDate());
    
    list.set(index, temp);
    System.out.println("해당 게시판 정보를 수정하였습니다.");
  }

  private boolean validate(int index) {
    if (index == -1) {
      System.out.println("해당 게시물이 존재하지 않습니다.");
      return false;
    }
    return true;
  }

  private int indexOf(int index) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.getNo() == index) {
        return i;
      }
    }
    return -1;
  }

  private int promptNo() {
    System.out.print("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }

}
