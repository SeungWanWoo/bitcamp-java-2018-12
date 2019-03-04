package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoFile;

public interface PhotoFileDao {
  void insert(PhotoFile photoFile);
  List<PhotoFile> findByPhotoFileNo(int photoFileNo);
  int deleteByPhotoBoardNo(int photoBoardNo);
}
