package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;

public class LessonHandler {
  Scanner keyboard;
  ArrayList<Lesson> list;
  
  public LessonHandler (Scanner keyboard) {
    this.keyboard = keyboard;
    list = new ArrayList<>();
  }
  
  public void listLesson() {
    Lesson[] lists = list.toArray(new Lesson[0]);
    for (Lesson temp : lists) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          temp.getNo(), temp.getTitle(), temp.getStartDate(), 
          temp.getEndDate(), temp.getTotalHours());
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

    // i 번째 배열에 수업 정보를 담고 있는 Lesson 객체(의 주소)를 보관한다.
    list.add(lesson);

    System.out.println("저장하였습니다.");
  }

  public void detailLesson() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    Lesson lesson = list.get(index);
    
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("설명: %s\n", lesson.getContents());
    System.out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %s\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %s\n", lesson.getDayHours());
  }
  
  public void deleteLesson() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    list.remove(index);
    System.out.println("수업을 삭제했습니다.");
  }
  public void updateLesson() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    Lesson lesson = list.get(index);
    Lesson temp = new Lesson();
    
    temp.setNo(lesson.getNo());
    
    System.out.printf("수업명(%s)? ", lesson.getTitle());
    String input = keyboard.nextLine();
    temp.setTitle(input.length() > 0 ? input : lesson.getTitle());
    
    System.out.printf("설명(%s)? ", lesson.getContents());
    input = keyboard.nextLine();
    temp.setContents(input.length() > 0 ? input : lesson.getContents());
    
    System.out.printf("시작일(%s)? ", lesson.getStartDate());
    input = keyboard.nextLine();
    temp.setStartDate(input.length() > 0 ? Date.valueOf(input) : lesson.getStartDate());
    
    System.out.printf("종료일(%s)? ", lesson.getEndDate());
    input = keyboard.nextLine();
    temp.setEndDate(input.length() > 0 ? Date.valueOf(input) : lesson.getEndDate());
    
    System.out.printf("총수업시간(%s)? ", lesson.getTotalHours());
    input = keyboard.nextLine();
    temp.setTotalHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getTotalHours());
    
    System.out.printf("일수업시간(%s)? ", lesson.getDayHours());
    input = keyboard.nextLine();
    temp.setDayHours(input.length() > 0 ? Integer.parseInt(input) : lesson.getDayHours());
    
    list.set(index, temp);
    System.out.println("수업 정보를 변경하였습니다.");
  }
  
  private boolean validate(int index) {
    if (index == -1)
      return false;
    
    return true;
  }

  private int indexOf(int index) {
    Lesson[] lesson = list.toArray(new Lesson[0]);
    for (int i = 0; i < list.size(); i++) {
      if (lesson[i].getNo() == index)
        return i;
    }
    return -1;
  }

  private int prompt() {
    System.out.print("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }


}
