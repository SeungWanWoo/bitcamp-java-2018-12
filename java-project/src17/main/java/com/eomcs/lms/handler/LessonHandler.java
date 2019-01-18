package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {
  Scanner keyboard;
  ArrayList ArrayList;
  
  public LessonHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.ArrayList = new ArrayList(5);
  }
  public void listLesson() {
    Object[] lessons = ArrayList.toArray();
    for (Object lesson : lessons) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          ((Lesson) lesson).getNo(), ((Lesson) lesson).getTitle(), 
          ((Lesson) lesson).getStartDate(), 
          ((Lesson) lesson).getEndDate(), ((Lesson) lesson).getTotalHours());
    }
  }

  public void addLesson() {
    Lesson lesson = new Lesson();

    System.out.print("번호? ");
    lesson.setNo(Integer.parseInt(keyboard.nextLine()));

    System.out.print("수업명? ");
    lesson.setTitle(keyboard.nextLine());

    System.out.print("설명? ");
    lesson.setContents(keyboard.nextLine());

    System.out.print("시작일? ");
    lesson.setStartDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("종료일? ");
    lesson.setEndDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("총수업시간? ");
    lesson.setTotalHours(Integer.parseInt(keyboard.nextLine()));

    System.out.print("일수업시간? ");
    lesson.setDayHours(Integer.parseInt(keyboard.nextLine()));

    ArrayList.add(lesson);

    System.out.println("저장하였습니다.");
  }
}
