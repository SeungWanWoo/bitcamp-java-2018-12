package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;

public class LessonList {

  static final int LENGTH = 10;
  Lesson[] lessons;
  int lessonIdx = 0;
  
  public LessonList() {
    lessons = new Lesson[LENGTH];
  }
  
  public LessonList(int length) {
    if (length > LENGTH) 
      lessons = new Lesson[length];
    else
      lessons = new Lesson[LENGTH];
  }
  
  public Lesson[] toArray() {
    Lesson[] copyLesson = new Lesson[lessonIdx];
    for (int i = 0; i < lessonIdx; i++) {
      copyLesson[i] = lessons[i];
    }
    return copyLesson;
  }
  
  public void add(Lesson lesson) {
    if (lessonIdx >= lessons.length) {
      int orderLength = lessons.length;
      int newLength = orderLength + (orderLength >> 1);
      for (int i = 0; i < newLength; i++) {
        lessons[i] = lesson;
      }
    }
    lessons[lessonIdx++] = lesson;
  }
}
