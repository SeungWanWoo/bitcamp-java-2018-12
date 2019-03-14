package com.eomcs.lms.handler;
import com.eomcs.lms.context.Component;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

@Component("/board/update")
public class BoardUpdateCommand extends AbstractCommand {
  BoardDao boardDao;

  public BoardUpdateCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    Board board = new Board();
    board.setNo(response.requestInt("번호? "));
    board.setContents(response.requestString("내용? "));
    boardDao.update(board);
    response.println("게시글을 변경했습니다.");
  }
}
