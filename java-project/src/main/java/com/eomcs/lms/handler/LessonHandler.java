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
    this.list = new ArrayList<Lesson>(5);
  }

  public void listLesson() {
    Object[] objs = list.toArray();
    for (Object obj : objs) {
      Lesson lesson = (Lesson) obj;
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          lesson.getNo(), lesson.getTitle(), 
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());
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
    int no = promptNo();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    Lesson lesson = list.get(index);
    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("수업내용: %s\n", lesson.getContents());
    System.out.printf("기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총수업시간: %d\n", lesson.getTotalHours());
    System.out.printf("일수업시간: %d\n", lesson.getDayHours());
  }

  public void deleteLesson() {
    int no = promptNo();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    list.remove(index);
    System.out.println("해당 수업을 삭제하였습니다.");
  }
  
  public void updateLesson() {
    int no = promptNo();
    int index = indexOf(no);
    if (!validate(index))
      return;
    
    Lesson lesson = list.get(index);
    Lesson temp = new Lesson();
    
    temp.setNo(lesson.getNo());
    
    System.out.printf("수업명(%s)? ", lesson.getTitle());
    String input = keyboard.nextLine();
    temp.setTitle(input.length() > 0 ? input : lesson.getTitle());
    
    System.out.printf("수업내용? ");
    input = keyboard.nextLine();
    temp.setContents(input.length() > 0 ? input : lesson.getContents());
    
    System.out.printf("시작일(%s)? ", lesson.getStartDate());
    input = keyboard.nextLine();
    temp.setStartDate(input.length() > 0 ? 
        Date.valueOf(input) : lesson.getStartDate());
    
    System.out.printf("종료일(%s)? ", lesson.getEndDate());
    input = keyboard.nextLine();
    temp.setEndDate(input.length() > 0 ? 
        Date.valueOf(input) : lesson.getEndDate());
    
    System.out.printf("총수업시간(%s)? ", lesson.getTotalHours());
    input = keyboard.nextLine();
    temp.setTotalHours(input.length() > 0 ? 
        Integer.parseInt(input) : lesson.getTotalHours());
    
    System.out.printf("일수업시간(%s)? ", lesson.getDayHours());
    input = keyboard.nextLine();
    temp.setDayHours(input.length() > 0 ? 
        Integer.parseInt(input) : lesson.getDayHours());
    
    list.set(index, temp);
    System.out.println("해당 수업의 정보를 수정하였습니다.");
    
  }
  
  private boolean validate(int index) {
    if (index == -1) {
      System.out.println("해당 수업이 존재하지 않습니다.");
      return false;
    }
    return true;
  }

  private int indexOf(int index) {
    for (int i = 0; i < list.size(); i++) {
      Lesson item = list.get(i);
      if (item.getNo() == index)
        return i;
    }
    return -1;
  }

  private int promptNo() {
    System.out.print("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }


}
