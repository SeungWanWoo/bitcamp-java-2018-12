package com.eomcs.lms.handler;
import com.eomcs.lms.context.Component;
import com.eomcs.lms.dao.BoardDao;

@Component("/board/delete")
public class BoardDeleteCommand extends AbstractCommand {
  BoardDao boardDao;

  public BoardDeleteCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호? ");

    if (boardDao.delete(no) == 0) {
      response.println("해당 게시물이 존재하지 않습니다.");
      return;
    }
    response.println("게시글을 삭제했습니다.");
  }
}
