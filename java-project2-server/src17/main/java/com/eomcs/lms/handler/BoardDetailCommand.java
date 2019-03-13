package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDetailCommand extends AbstractCommand {
  SqlSessionFactory sqlSessionFactory;

  public BoardDetailCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {
    int no = response.requestInt("번호? ");

    // 1) SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // 2) SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      Board board = boardDao.findByNo(no);
      if (board == null) {
        response.println("해당 게시물이 존재하지 않습니다.");
        return;
      }

      // 게시물 데이터를 가지고 왔으면 조회수를 증가시킨다.
      boardDao.increaseCount(no);
      sqlSession.commit();
      
      response.println(
          String.format("내용: %s", board.getContents()));
      response.println(
          String.format("작성일: %s", board.getCreatedDate()));
      response.println(
          String.format("조회수: %d", board.getViewCount()));
    }
  }
}
