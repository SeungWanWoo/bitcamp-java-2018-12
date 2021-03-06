package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;

public class LessonHandler {
  Scanner keyboard;
  ArrayList<Lesson> list;

  public LessonHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList<>();
  }
  public void listLesson() {
    Lesson[] lessons = list.toArray(new Lesson[0]);
    for (Lesson lesson : lessons) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lesson.getNo(), lesson.getTitle(), lesson.getStartDate(), 
          lesson.getEndDate(), lesson.getTotalHours());
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

    list.add(lesson);

    System.out.println("저장하였습니다.");
  }

  public void detailLesson() {
    int no = promptLessonNo();
    int index = indexOf(no);
    
    //Lesson lesson = list.get(index);
    if (!validate(index))
      return;
    
    Lesson lesson = list.get(index);
    if (lesson == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }
    
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("수업내용: %s\n", lesson.getContents());
    System.out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
    
  }
  public void deleteLesson() {
    int no = promptLessonNo();
    int index = indexOf(no);
    
  /*int index = -1;
    int size = list.size();
    for (int i = 0; i < size; i++) {
      Lesson item = list.get(i);
      if (item.getNo() == no) {
        index = i;
        break;
      }
    }*/
    
    if (!validate(index))
      return;
    
    list.remove(index);
    System.out.println("해당 수업을 삭제했습니다.");
  }
  
  public void updateLesson() {
    int no = promptLessonNo();
    int index = indexOf(no);
    
    if(!validate(index))
      return;
  /*int index = -1;
    Lesson lesson = null;
    int size = list.size();
    for (int i = 0; i < size; i++) {
      Lesson item = list.get(i);
      if (item.getNo() == no) {
        lesson = item;
        index = i;
        break;
      }
    }*/
    
    Lesson lesson = list.get(index);
    Lesson temp = new Lesson();
    
    temp.setNo(lesson.getNo());
    
    System.out.printf("수업명(%s)? ", lesson.getTitle());
    String input = keyboard.nextLine();
    if(input.length() > 0) {
      temp.setTitle(input);
    } else {
      temp.setTitle(lesson.getTitle());
    }
    
    System.out.printf("수업내용? ", lesson.getContents());
    input = keyboard.nextLine();
    temp.setContents(input.length() > 0 ? 
        input : lesson.getContents());
    
    System.out.printf("시작일(%s)? ", lesson.getStartDate());
    input = keyboard.nextLine();
    temp.setStartDate(input.length() > 0 ? 
        Date.valueOf(input) : lesson.getStartDate());
    
    System.out.printf("종료일(%s)? ", lesson.getEndDate());
    input = keyboard.nextLine();
    temp.setEndDate(input.length() > 0 ? 
        Date.valueOf(input) : lesson.getEndDate());
    
    System.out.printf("총수업시간(%d)? ", lesson.getTotalHours());
    input = keyboard.nextLine();
    temp.setTotalHours(input.length() > 0 ? 
        Integer.parseInt(input) : lesson.getTotalHours());
    
    System.out.printf("일수업시간(%d)? ", lesson.getDayHours());
    input = keyboard.nextLine();
    temp.setDayHours(input.length() > 0 ? 
        Integer.parseInt(input) : lesson.getDayHours());
    
    list.set(index, temp);
  }
  private int promptLessonNo() {
    System.out.print("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }
  
  private int indexOf(int lessonNo) {
    // 리펙토링 기법 - 속도가 떨어지더라도 유지보수에 힘써라. 가독성을 높여라.
    for (int i = 0; i < list.size(); i++) {
      Lesson item = list.get(i);
      if (item.getNo() == lessonNo) {
        return i;
      }
    }
    return -1;
  }
  
  // 최대한 중복을 줄임으로써 반복되는 주석의 수를 줄이고
  // 내부적으로 리펙토링을 하게되면 private으로 줘서 관리하게 되면
  // 유지보수에 아주 좋다.
  private boolean validate(int index) {
    if (index == -1) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return false;
    }
    return true;
  }
}
