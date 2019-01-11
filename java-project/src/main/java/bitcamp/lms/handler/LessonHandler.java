package bitcamp.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import bitcamp.lms.domain.Lesson;

public class LessonHandler {
  public static Scanner scn;
  public static int lessonIdx = 0;
  public static Lesson[] ls = new Lesson[10];
  
  public static void listLesson() {
    for (int i = 0; i < lessonIdx; i++) {
      System.out.printf("%02d, %-20s, %s ~ %s, %04d\n", 
          ls[i].num, ls[i].cName, ls[i].sDate, ls[i].eDate, 
          ls[i].totalLectureTime);
    }
    System.out.println();
  }

  public static void addLesson() {
    Lesson lss = new Lesson();
    System.out.print("번호? ");
    lss.num = Integer.parseInt(scn.nextLine());

    System.out.print("수업명? ");
    lss.cName = scn.nextLine();

    System.out.print("설명? ");
    lss.explan = scn.nextLine();

    System.out.print("시작일? ");
    lss.sDate = Date.valueOf(scn.nextLine());

    System.out.print("종료일? ");
    lss.eDate = Date.valueOf(scn.nextLine());  

    System.out.print("총수업시간? ");
    lss.totalLectureTime = Integer.parseInt(scn.nextLine());

    System.out.print("일수업시간? ");
    lss.dayLectureTime = Integer.parseInt(scn.nextLine());
    System.out.println();

    ls[lessonIdx++] = lss;
    System.out.println("저장하였습니다.");
  }
}
