package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;

public class PhotoBoardDeleteCommand extends AbstractCommand {
  PhotoBoardDao photoBoardDao;

  public PhotoBoardDeleteCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");
    
    if (photoBoardDao.findByNo(no) == null) {
      response.println("해당 파일이 존재하지 않습니다.");
      return;
    }
    photoBoardDao.delete(no);
    response.println("게시글을 삭제했습니다.");

  }
}
