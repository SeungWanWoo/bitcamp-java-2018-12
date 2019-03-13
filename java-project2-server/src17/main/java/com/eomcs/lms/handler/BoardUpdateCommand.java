package com.eomcs.lms.handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand extends AbstractCommand {
  SqlSessionFactory sqlSessionFactory;

  public BoardUpdateCommand(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(Response response) throws Exception {

    // 1) SqlSession 객체를 준비한다.
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

      // 2) SqlSession으로부터 BoardDao 구현체를 얻는다.
      // => getMapper(DAO 인터페이스 타입 정보)
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      Board board = new Board();
      board.setNo(response.requestInt("번호? "));
      String input = response.requestString("내용? ");
      
      if (input.length() > 0) {
        board.setContents(input);
      }
      
      if (board.getContents() != null) {
        if(boardDao.update(board) == 0) {
          response.println("해당 게시물이 존재하지 않습니다.");
          return;
        }
        sqlSession.commit();
        response.println("게시글을 변경했습니다.");
      } else {
        sqlSession.rollback();
        response.println("변경을 취소했습니다.");
      }
    }
  }
}
