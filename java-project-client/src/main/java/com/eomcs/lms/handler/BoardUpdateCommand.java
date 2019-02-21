package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand implements Command {
  BoardDao boardDao;
  Scanner keyboard;

  public BoardUpdateCommand(Scanner keyboard, BoardDao boardDao) {
    this.keyboard = keyboard;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      Board board = new Board();
      System.out.print("번호? ");
      board.setNo(Integer.parseInt(keyboard.nextLine()));
      
      System.out.printf("내용? ");
      board.setContents(keyboard.nextLine());
      
      boardDao.update(board);

      System.out.println("게시글을 변경했습니다.");

    } catch (Exception e) {
      System.out.printf("게시글 변경 오류! : %s\n", e.getMessage());
    }
  }
}
