package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonDetailCommand implements Command {
  Scanner keyboard;
  List<Lesson> list;
  
  public LessonDetailCommand (Scanner keyboard, List<Lesson> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
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
