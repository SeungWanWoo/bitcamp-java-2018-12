// 11단계 : AbstractService 상속받기
package com.eomcs.lms.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonService implements Service {

  LessonDao lessonDao;

  public LessonService(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  public void execute(String request, ObjectInputStream in, 
      ObjectOutputStream out) throws Exception {
    switch (request) {
      case "/lesson/add":
        add(in, out);
        break;
      case "/lesson/list":
        list(in, out);
        break;
      case "/lesson/detail":
        detail(in, out);
        break;
      case "/lesson/update":
        update(in, out);
        break;
      case "/lesson/delete":
        delete(in, out);
        break;
      default:
        out.writeUTF("FAIL");
    } // switch
    out.flush();
  }

  private void add(ObjectInputStream in, 
      ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.flush();
    lessonDao.insert((Lesson) in.readUnshared());
    out.writeUTF("OK");
  }

  private void list(ObjectInputStream in, 
      ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    out.writeUnshared(lessonDao.findAll());
  }

  private void detail(ObjectInputStream in, 
      ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.flush();
    int num = in.readInt();

    Lesson l = lessonDao.findByNo(num); 
    if (l == null) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
    out.writeUnshared(l);
  }

  private void update(ObjectInputStream in, 
      ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.flush();
    Lesson lesson = (Lesson) in.readUnshared();
    if (lessonDao.update(lesson) == 0) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
  }

  private void delete(ObjectInputStream in, 
      ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.flush();
    int num = in.readInt();
    if (lessonDao.delete(num) == 0) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
  }
}
