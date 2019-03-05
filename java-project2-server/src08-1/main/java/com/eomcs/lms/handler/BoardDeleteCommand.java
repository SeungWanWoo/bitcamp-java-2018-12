package com.eomcs.lms.handler;
import com.eomcs.lms.dao.BoardDao;

public class BoardDeleteCommand extends AbstractCommand {
  BoardDao boardDao;

  public BoardDeleteCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호? ");

    boardDao.delete(no);
    response.println("게시글을 삭제했습니다.");

  }
}
