package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonListCommand implements Command {
  Scanner keyboard;
  List<Lesson> list;
  
  public LessonListCommand (Scanner keyboard, List<Lesson> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
    Lesson[] lists = list.toArray(new Lesson[0]);
    for (Lesson temp : lists) {
      System.out.printf("%3d, %-15s, %10s ~ %10s, %4d\n", 
          temp.getNo(), temp.getTitle(), temp.getStartDate(), 
          temp.getEndDate(), temp.getTotalHours());
    }
  }
}
