package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class LessonHandler {
  static Scanner scn;
  static final int length = 10;
  static int lessonIdx = 0;
  static Lesson[] ls = new Lesson[length];
  
  static void listLesson() {
    for (int i = 0; i < lessonIdx; i++) {
      System.out.printf("%02d, %-20s, %s ~ %s, %04d\n", 
          ls[i].num, ls[i].cName, ls[i].sDate, ls[i].eDate, 
          ls[i].totalLectureTime);
    }
    System.out.println();
  }

  static void addLesson() {
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
