package com.eomcs.lms.listener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import com.eomcs.lms.domain.Member;

public class MemberDataLoaderListener implements ApplicationListener {

  @Override
  public void startApplication(HashMap<String, Object> context) {
    System.out.println("데이터를 읽어오는 중입니다.");
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream("member2.data")))) {
      context.put("memberList", in.readObject());
    } catch (Exception e) {
      System.out.println("회원 데이터 로딩 완료");
      context.put("memberList", new LinkedList<Member>());
    }
  }

  @Override
  public void endApplication(HashMap<String, Object> context) {
    System.out.println("데이터를 저장하는 중입니다.");
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream("member2.data")))){
      out.writeObject(context.get("memberList"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
