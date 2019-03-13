package com.eomcs.lms.handler;
import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateCommand extends AbstractCommand {
  SqlSessionFactory sqlSessionFactory;

  public LessonUpdateCommand(SqlSessionFactory sqlSessionFactory) {
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
      int no = response.requestInt("번호? ");
      Lesson lesson = lessonDao.findByNo(no);
      if (lesson == null) {
        response.println("해당 번호의 수업이 없습니다.");
        return;
      }

      // 변경할 값만 temp에 저장할 것이기 때문에 기존의 데이터를 복제하지 않는다..
      Lesson temp = new Lesson();
      temp.setNo(lesson.getNo());

      // mybatis는 필드의 값이 null이 아니거나
      // 숫자인 경우 0이 아니면 해당 컬럼 값을 update 한다.
      String input = response.requestString(
          String.format("수업명(%s)? ", lesson.getTitle()));
      if (input.length() > 0) 
        temp.setTitle(input);
      
      input = response.requestString(
          String.format("설명(%s)? ", lesson.getContents()));
      if (input.length() > 0)
        temp.setContents(input);

      input = response.requestString(
          String.format("시작일(%s)? ", lesson.getStartDate()));
      if (input.length() > 0)
        temp.setStartDate(Date.valueOf(input));
      
      input = response.requestString(
          String.format("종료일(%s)? ", lesson.getEndDate()));
      if (input.length() > 0)
        temp.setEndDate(Date.valueOf(input));

      input = response.requestString(
          String.format("총수업시간(%d)? ", lesson.getTotalHours()));
      if (input.length() > 0)
        temp.setTotalHours(Integer.parseInt(input));

      input = response.requestString(
          String.format("일수업시간(%d)? ", lesson.getDayHours()));
      if (input.length() > 0)
        temp.setDayHours(Integer.parseInt(input));

      if (lesson.getTitle() != null
          || lesson.getContents() != null
          || lesson.getStartDate() != null
          || lesson.getEndDate() != null
          || lesson.getDayHours() > 0
          || lesson.getTotalHours() > 0) {
        lessonDao.update(temp);
        sqlSession.commit();
        response.println("수업을 변경했습니다.");
      } else {
        sqlSession.rollback();
        response.println("변경 취소했습니다.");
      }
    } finally {
      sqlSession.close();
    }
  }
}
