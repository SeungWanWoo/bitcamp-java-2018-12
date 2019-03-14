package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.mybatis.TransactionManger;

public class PhotoBoardDeleteCommand extends AbstractCommand {
  TransactionManger txManager;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteCommand(
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao
      ,TransactionManger txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
    this.name = "/photoboard/delete";
  }

  @Override
  public void execute(Response response) throws Exception {
    txManager.beginTransaction();
    try {
      int no = response.requestInt("번호?");

      // 데이터를 지울 때는 자식 테이블의 데이터부터 지워야 한다.
      photoFileDao.deleteByPhotoBoardNo(no);

      if (photoBoardDao.findByNo(no) == null) {
        response.println("해당 파일이 존재하지 않습니다.");
        return;
      }
      photoBoardDao.delete(no);
      response.println("게시글을 삭제했습니다.");
      txManager.commit();
    } catch (Exception e) {
      response.println("게시글을 삭제 중 문제가 발견됬습니다.");
      txManager.rollback();
      //
    }
  }
}
