package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand implements Command {
  Scanner keyboard;
  List<Board> list;
  
  public BoardUpdateCommand(Scanner keyboard, List<Board> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
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
