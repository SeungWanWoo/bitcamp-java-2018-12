package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.eomcs.lms.context.Component;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.mybatis.TransactionManger;

@Component
public class LessonCommand {
  TransactionManger txManager;
  LessonDao lessonDao;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public LessonCommand(LessonDao lessonDao, 
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      TransactionManger txManager) {
    this.lessonDao = lessonDao;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @RequestMapping("/lesson/list")
  public void list(Response response) {
    List<Lesson> lessons = lessonDao.findAll();
    for (Lesson lesson : lessons) {
      response.println(
          String.format("%3d, %-15s, %10s ~ %10s, %4d", 
              lesson.getNo(), lesson.getTitle(), 
              lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours()));
    }
  }
  
  @RequestMapping("/lesson/add")
  public void add(Response response) throws Exception {

      Lesson lesson = new Lesson();
      
      lesson.setTitle(response.requestString("수업명? "));
      lesson.setContents(response.requestString("설명? "));
      lesson.setStartDate(response.requestDate("시작일? "));
      lesson.setEndDate(response.requestDate("종료일? "));
      lesson.setTotalHours(response.requestInt("총수업시간? "));
      lesson.setDayHours(response.requestInt("일수업시간? "));
      
      lessonDao.insert(lesson);

      response.println("저장하였습니다.");
  }
  
  @RequestMapping("/lesson/detail")
  public void detail(Response response) throws Exception {
    int no = response.requestInt("번호? ");

    Lesson lesson = lessonDao.findByNo(no);
    if (lesson == null) {
      response.println("해당 수업 정보가 존재하지 않습니다.");
      return;
    }
    response.println(
        String.format("수업명: %s", lesson.getTitle()));
    response.println(
        String.format("설명: %s", lesson.getContents()));
    response.println(
        String.format("기간: %s ~ %s", lesson.getStartDate(), lesson.getEndDate()));
    response.println(
        String.format("총수업시간: %d", lesson.getTotalHours()));
    response.println(
        String.format("일수업시간: %d", lesson.getDayHours()));
  }
  
  @RequestMapping("/lesson/update")
  public void update(Response response) throws Exception {

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

    if ((input = response.requestString(
        String.format("시작일(%s)? ", lesson.getStartDate()))).length() > 0)
      temp.setStartDate(Date.valueOf(input));

    if ((input = response.requestString(
        String.format("종료일(%s)? ", lesson.getEndDate()))).length() > 0)
      temp.setEndDate(Date.valueOf(input));

    if ((input = response.requestString(
        String.format("총수업시간(%d)? ", lesson.getTotalHours()))).length() > 0)
      temp.setTotalHours(Integer.parseInt(input));

    if ((input = response.requestString(
        String.format("일수업시간(%d)? ", lesson.getDayHours()))).length() > 0)
      temp.setDayHours(Integer.parseInt(input));
    
    if (temp.getTitle() != null
          || temp.getContents() != null
          || temp.getStartDate() != null
          || temp.getEndDate() != null
          || temp.getDayHours() > 0
          || temp.getTotalHours() > 0) {
      lessonDao.update(temp);
      response.println("수업을 변경했습니다.");
    } else {
      response.println("변경 취소했습니다.");
    }
  }
  
  @RequestMapping("/lesson/delete")
  public void delete(Response response) throws Exception {
    txManager.beginTransaction();
    try {
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
      txManager.commit();
    } catch (Exception e) {
      response.println("수업 삭제 중 오류가 발생했습니다.");
      txManager.rollback();
    }
  }
}
