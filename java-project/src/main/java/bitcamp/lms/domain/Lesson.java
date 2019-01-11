// 수업 데이터를 저장할 새 데이터 타입
package bitcamp.lms.domain;

import java.sql.Date;

public class Lesson {
  public int num;
  public String cName;
  public String explan;
  public Date sDate;
  public Date eDate;
  public int totalLectureTime;
  public int dayLectureTime;
}
