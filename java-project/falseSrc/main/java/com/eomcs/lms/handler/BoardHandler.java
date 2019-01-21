package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardHandler {
  ArrayList<Board> ArrayList;
  Scanner keyboard;  

  public BoardHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.ArrayList = new ArrayList<>();
  }

  public void listBoard() {
    Board[] boards = ArrayList.toArray(new Board[0]);
    for (Board board : boards) {
      System.out.printf("번호: %3d\n내용: %-20s\n작성일: %s\n조회수: %d\n", 
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

    ArrayList.add(board);

    System.out.println("저장하였습니다.");
  }

  public void detailBoard() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    Board boards = indexOfBoard(no);
    if (boards != null) {
      System.out.printf("번호: %3d\n내용: %-20s\n작성일: %s\n조회수: %d\n", 
          boards.getNo(), boards.getContents(), 
          boards.getCreatedDate(), boards.getViewCount());
    } else
      System.out.println("해당 게시글을 찾을 수 없습니다.");
  }

  public void updateBoard() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    Board board = indexOfBoard(no);
    if (board != null) {
      System.out.print("내용? ");
      String contents = keyboard.nextLine();
      if (contents.equals("")) {
      } else {
        board.setContents(contents);
        System.out.println("게시글을 변경했습니다.");
      }
    } else
      System.out.println("해당 게시글을 찾을 수 없습니다.");
  }

  public void deleteBoard() {
    Board[] boards = ArrayList.toArray(new Board[0]);
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    ;
    if(indexOfBoard(no) != null) {
      for(int i = 0; i < boards.length; i++) {
        if (boards[i].getNo() == no) {
          ArrayList.remove(i);
          System.out.println("게시글을 삭제했습니다.");
        }
      }
    } else
      System.out.println("해당 게시글을 찾을 수 없습니다.");
  } 

  public Board indexOfBoard(int index) {
    Board[] boards = ArrayList.toArray(new Board[0]);
    Board board = new Board();
    for (int i = 0; i < boards.length; i++) {
      if (boards[i].getNo() == index) {
        board = ArrayList.get(i);
        return board;
      } else 
        continue;
    }
    return null;
  }
}
