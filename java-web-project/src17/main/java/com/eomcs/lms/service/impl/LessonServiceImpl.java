package com.eomcs.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

  LessonDao lessonDao;
  PhotoFileDao photoFileDao;
  PhotoBoardDao photoBoardDao;

  public LessonServiceImpl(
      LessonDao lessonDao,
      PhotoFileDao photoFileDao,
      PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
    this.lessonDao = lessonDao;
    this.photoFileDao = photoFileDao;
  }

  // 비지니스 객체에서 메서드 이름은 가능한 업무 용어를 사용한다.
  @Override
  public List<Lesson> list(int pageNo, int pageSize) {
    HashMap<String,Object> params = new HashMap<>();
    params.put("size", pageSize);
    params.put("rowNo", (pageNo - 1) * pageSize);
    return lessonDao.findAll(params); 
  }
  //
  @Override
  public int add(Lesson lesson) {
    return lessonDao.insert(lesson);
  }
  @Override
  public Lesson get(int no) {
    return lessonDao.findByNo(no);
  }

  @Override
  public int update(Lesson lesson) {
    if (lesson.getTitle() != null
        || lesson.getContents() != null
        || lesson.getStartDate() != null
        || lesson.getEndDate() != null
        || lesson.getDayHours() > 0
        || lesson.getTotalHours() > 0) {
      return lessonDao.update(lesson);
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    HashMap<String, Object> params = new HashMap<>();
    params.put("lessonNo", no);

    List<PhotoBoard> boards = photoBoardDao.findAll(params);
    for (PhotoBoard board : boards) {
      photoFileDao.deleteByPhotoBoardNo(board.getNo());
      photoBoardDao.delete(board.getNo());
    }
    return lessonDao.delete(no);
  }

  @Override
  public int size() {
    return lessonDao.countAll();
  }
}