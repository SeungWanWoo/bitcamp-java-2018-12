package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoFile;

public interface PhotoFileDao {
  int insert(List<PhotoFile> photoFiles);
  List<PhotoFile> findByPhotoBoardNo(int photoFileNo);
  int deleteByPhotoBoardNo(int photoBoardNo);
}
