package com.eomcs.lms.handler;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardSearchCommand extends AbstractCommand {
  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardSearchCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) {
    // 1) SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // 2) SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      PhotoBoardDao photoBoardDao = sqlSession.getMapper(PhotoBoardDao.class);
      HashMap<String, Object> params = new HashMap<>();
      try {
        int lessonNo = response.requestInt("수업 번호?");
        params.put("lessonNo", lessonNo);
      } catch (Exception e) {
      }

      try {
        String keyword = response.requestString("검색어?");
        if (keyword.length() > 0) {
          // SQL에서 검색할 때 사용할 문자열 패턴을 다음과 같이 자바에서 만들어 전달할 수 있다.
          // params.put("keyword", "%" + keyword + "%");

          //
          params.put("keyword", keyword);
        }
      } catch (Exception e) {
      }

      List<PhotoBoard> photoBoards = photoBoardDao.findAll(params);

      response.println("[검색 결과]");
      for (PhotoBoard photoBoard : photoBoards) {
        response.println(
            String.format("%3d, %-20s, %s, %d, %d", 
                photoBoard.getNo(), photoBoard.getTitle(), 
                photoBoard.getCreatedDate(), photoBoard.getViewCount(),
                photoBoard.getLessonNo()));
      }
    }
  }
}
