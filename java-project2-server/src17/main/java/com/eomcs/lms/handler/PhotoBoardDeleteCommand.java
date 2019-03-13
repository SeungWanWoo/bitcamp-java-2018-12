package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;

public class PhotoBoardDeleteCommand extends AbstractCommand {
  
  SqlSessionFactory sqlSessionFactory;
  
  public PhotoBoardDeleteCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    // 1) SqlSession 객체를 준비한다.
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      // 2) SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      PhotoBoardDao photoBoardDao = sqlSession.getMapper(PhotoBoardDao.class);
      PhotoFileDao photoFileDao = sqlSession.getMapper(PhotoFileDao.class);
      int no = response.requestInt("번호?");

      // 데이터를 지울 때는 자식 테이블의 데이터부터 지워야 한다.
      photoFileDao.deleteByPhotoBoardNo(no);

      if (photoBoardDao.findByNo(no) == null) {
        response.println("해당 파일이 존재하지 않습니다.");
        return;
      }
      photoBoardDao.delete(no);
      response.println("게시글을 삭제했습니다.");
      sqlSession.commit();
    } catch (Exception e) {
      response.println("게시글을 삭제 중 문제가 발견됬습니다.");
      sqlSession.rollback();
    } finally {
      sqlSession.close();
    }
  }
}
