package com.eomcs.lms.handler;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;

public class LessonDeleteCommand extends AbstractCommand {
  SqlSessionFactory sqlSessionFactory;

  public LessonDeleteCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    // 1) SqlSession 객체를 준비한다.
    try {
      // 2) SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      LessonDao lessonDao = sqlSession.getMapper(LessonDao.class);
      PhotoBoardDao photoBoardDao = sqlSession.getMapper(PhotoBoardDao.class);
      PhotoFileDao photoFileDao = sqlSession.getMapper(PhotoFileDao.class);
      int no = response.requestInt("번호? ");

      HashMap<String, Object> params = new HashMap<>();
      params.put("lessonNo", no);

      List<PhotoBoard> boards = photoBoardDao.findAll(params);
      for (PhotoBoard board : boards) {
        photoFileDao.deleteByPhotoBoardNo(board.getNo());
        photoBoardDao.delete(board.getNo());
      }

      if (lessonDao.delete(no) == 0) {
        response.println("해당 번호가 존재하지 않습니다.");
        return;
      }
      response.println("수업을 삭제했습니다.");
      sqlSession.commit();
    } catch (Exception e) {
      response.println("수업 삭제 중 오류가 발생했습니다.");
      sqlSession.rollback();
    } finally {
      sqlSession.close();
    }
  }
}
