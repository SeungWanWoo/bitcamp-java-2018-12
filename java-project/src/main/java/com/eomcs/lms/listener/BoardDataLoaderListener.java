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
import com.eomcs.lms.domain.Board;

public class BoardDataLoaderListener implements ApplicationListener {

  @Override
  public void startApplication(HashMap<String, Object> context) {
    System.out.println("데이터를 읽어오는 중입니다.");
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream("board2.data")))) {
      context.put("boardList", in.readObject());
    } catch (Exception e) {
      System.out.println("게시판 데이터 로딩 완료");
      context.put("boardList",  new ArrayList<Board>());
    }
  }

  @Override
  public void endApplication(HashMap<String, Object> context) {
    System.out.println("데이터를 저장하는 중입니다.");
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream("board2.data")))) {
      out.writeObject(context.get("boardList"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
