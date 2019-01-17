package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;

public class LessonList {

  static final int LENGTH = 3;
  Lesson[] list;
  int size = 0;

  public LessonList() {
    list = new Lesson[LENGTH];
  }

  public LessonList(int length) {
    if (length > LENGTH) 
      list = new Lesson[length];
    else
      list = new Lesson[LENGTH];
  }

  public Lesson[] toArray() {
    Lesson[] copyLesson = new Lesson[size];
    for (int i = 0; i < size; i++) {
      copyLesson[i] = list[i];
    }
    return copyLesson;
  }

  public void add(Lesson lesson) {
    if (size >= list.length) {
      int orderLength = list.length;
      int newLength = orderLength + (orderLength >> 1);
      Lesson[] lessons = new Lesson[newLength];
      for (int i = 0; i < list.length; i++) {
        lessons[i] = list[i];
      }
      list = lessons;
    }
    list[size++] = lesson;
  }
}
