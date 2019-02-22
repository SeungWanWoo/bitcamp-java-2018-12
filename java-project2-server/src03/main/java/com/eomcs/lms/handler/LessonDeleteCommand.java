package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import com.eomcs.lms.dao.LessonDao;

public class LessonDeleteCommand implements Command {
  LessonDao lessonDao;

  public LessonDeleteCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintWriter out) {

    try {
      out.println("번호? ");
      out.println("!{}!");
      out.flush();
      int no = Integer.parseInt(in.readLine());
      lessonDao.delete(no);
      out.println("수업을 삭제했습니다.");
      
    } catch (Exception e) {
      out.printf("수업 정보 삭제 오류! : %s\n", e.getMessage());
    }
  }
}
