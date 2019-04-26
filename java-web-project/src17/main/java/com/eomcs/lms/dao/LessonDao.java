package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Lesson;

public interface LessonDao {
  int insert(Lesson lesson);
  List<Lesson> findAll(Map<String,Object> params);
  Lesson findByNo(int no);
  int update(Lesson lesson);
  int delete(int no);
  int countAll();
}
