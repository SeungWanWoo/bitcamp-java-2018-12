// 11단계 : AbstractService 상속받기
package com.eomcs.lms.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<E> {

  String filepath;
  List<E> list;

  @SuppressWarnings("unchecked")
  public void loadData() throws Exception {
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream(this.filepath)))) {
      
      list = (List<E>) in.readObject();
      
    } catch (Exception e) {
      list = new ArrayList<E>();
      throw new RuntimeException("데이터 파일 로딩 오류! " + e);
    }
  }

  public void saveData() throws Exception{
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream(this.filepath)))) {
      
      out.writeObject(list);
      
    } catch (IOException e) {
      throw new RuntimeException("데이터 파일 저장 오류! " + e);
    }
  }
}
