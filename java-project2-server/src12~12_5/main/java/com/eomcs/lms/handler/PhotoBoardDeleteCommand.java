package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;

public class PhotoBoardDeleteCommand extends AbstractCommand {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  
  public PhotoBoardDeleteCommand(
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호?");
    
    // 데이터를 지울 때는 자식 테이블의 데이터부터 지워야 한다.
    photoFileDao.deleteByPhotoBoardNo(no);
    
    if (photoBoardDao.findByNo(no) == null) {
      response.println("해당 파일이 존재하지 않습니다.");
      return;
    }
    photoBoardDao.delete(no);
    
    response.println("게시글을 삭제했습니다.");

  }
}
