package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateCommand implements Command {
  Scanner keyboard;
  List<Lesson> list;
  
  public LessonUpdateCommand (Scanner keyboard, List<Lesson> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
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
