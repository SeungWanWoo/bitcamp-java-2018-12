package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

public interface PhotoBoardDao {
  void insert(PhotoBoard photoBoard);
  List<PhotoBoard> findAll();
  PhotoBoard findByNo(int num);
  int update(PhotoBoard PhotoBoard);
  int delete(int no);
}
