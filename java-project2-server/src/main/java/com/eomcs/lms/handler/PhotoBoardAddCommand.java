package com.eomcs.lms.handler;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardAddCommand extends AbstractCommand {
  PhotoBoardDao photoBoardDao; // 서버의 PhotoBoardDaoImpl 객체를 대행하는 프록시 객체.
  PhotoFileDao photoFileDao;

  public PhotoBoardAddCommand(
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(Response response) throws Exception {
    PhotoBoard board = new PhotoBoard();

    board.setTitle(response.requestString("제목?"));
    board.setLessonNo(response.requestInt("수업?"));
    photoBoardDao.insert(board);

    response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    response.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    int count = 0;
    while (true) {
      String input = response.requestString("사진 파일?");
      if (input.length() == 0) {
        if (count == 0) {
          response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        } else {
          break;
        }
      }
      PhotoFile files = new PhotoFile();
      files.setFilePath(input);
      files.setPhotoBoardNo(board.getNo());
      photoFileDao.insert(files);
      count++;
    } 
    response.println("저장하였습니다.");
  }
}
