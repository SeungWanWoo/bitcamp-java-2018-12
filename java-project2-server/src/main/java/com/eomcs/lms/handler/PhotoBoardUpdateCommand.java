package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardUpdateCommand extends AbstractCommand {
  PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    PhotoBoard board = new PhotoBoard();
    int input = response.requestInt("번호?"); 
    board.setNo(input);
    board.setTitle(response.requestString("제목?"));
    board.setLessonNo(photoBoardDao.findByNo(input).getLessonNo());
    photoBoardDao.update(board);

    response.println("사진을 변경했습니다.");

  }
}
