package com.eomcs.lms.listener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import com.eomcs.lms.domain.Lesson;

public class LessonDataLoaderListener implements ApplicationListener {

  @Override
  public void startApplication(HashMap<String, Object> context) {
    System.out.println("데이터를 읽어오는 중입니다.");
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream("lesson2.data")))) {
      context.put("lessonList",in.readObject());
    } catch (Exception e) {
      System.out.println("수업 데이터 로딩 완료");
      context.put("lessonList", new ArrayList<Lesson>());
    }
  }

  @Override
  public void endApplication(HashMap<String, Object> context) {
    System.out.println("데이터를 저장하는 중입니다.");
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream("lesson2.data")))) {
      out.writeObject(context.get("lessonList"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
