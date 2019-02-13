// 8단계 : 클라이언트 요청을 처리하는 클래스에 대해 리팩토링 수행
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;

public class MemberService {

  ObjectInputStream in;
  ObjectOutputStream out;

  ArrayList<Member> members = new ArrayList<>();

  public MemberService(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }
  
  public void execute(String request) throws Exception {
    switch (request) {
      case "/member/add":
        add();
        break;
      case "/member/list":
        list();
        break;
      case "/member/detail":
        detail();
        break;
      case "/member/update":
        update();
        break;
      case "/member/delete":
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
    members.add((Member) in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    out.writeObject(members);
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    
    int num = in.readInt();

    for (Member m : members) {
      if (m.getNo() == num) {
        out.writeUTF("OK");
        out.writeObject(m);
        return;
      }
    }
    out.writeUTF("FAIL");
  }

  private void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    Member member = (Member) in.readObject();

    int index = 0;
    for (Member m : members) {
      if (m.getNo() == member.getNo()) {
        members.set(index, member);
        out.writeUTF("OK");
        return;
      }
      index++;
    }
    out.writeUTF("FAIL");
  }

  private void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int num = in.readInt();

    int index = 0;
    for (Member m : members) {
      if (m.getNo() == num) {
        members.remove(index);
        out.writeUTF("OK");
        return;
      }
      index++;
    }
    out.writeUTF("FAIL");
  }
}
