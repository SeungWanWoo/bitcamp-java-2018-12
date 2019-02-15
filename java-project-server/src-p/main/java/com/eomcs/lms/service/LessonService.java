// 11단계 : AbstractService 상속받기
package com.eomcs.lms.service;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonService extends AbstractService<Lesson> {
  LessonDao lessonDao;
  public LessonService(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  public void execute(String request) throws Exception {
    switch (request) {
      case "/lesson/add":
        add();
        break;
      case "/lesson/list":
        list();
        break;
      case "/lesson/detail":
        detail();
        break;
      case "/lesson/update":
        update();
        break;
      case "/lesson/delete":
        delete();
        break;
      default:
        out.writeUTF("FAIL");
    } // switch
    out.flush();
  }

  private void add() throws Exception {
    out.writeUTF("OK");
    out.flush();
    lessonDao.insert((Lesson) in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    out.writeObject(lessonDao.findAll());
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int num = in.readInt();
    Lesson l = lessonDao.findByNo(num); 
    if (l == null) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
    out.writeObject(l);
  }

  private void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    Lesson lesson = (Lesson) in.readObject();
    
    if (lessonDao.update(lesson) == 0) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
  }

  private void delete() throws Exception {
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
