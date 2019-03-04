package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;

public class PhotoFile implements Serializable {

  private static final long serialVersionUID = 1L;
  private int no;
  private int photoBoardNo;
  private String filePath;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getPhotoBoardNo() {
    return photoBoardNo;
  }
  public void setPhotoBoardNo(int photoBoardNo) {
    this.photoBoardNo = photoBoardNo;
  }
  public String getFilePath() {
    return filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
