// 11단계 : AbstractService 상속받기
package com.eomcs.lms.service;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberService extends AbstractService<Member>{
  MemberDao memberDao;
  
  public MemberService(MemberDao memberDao) {
    this.memberDao = memberDao;
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
    memberDao.insert((Member) in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    out.writeObject(memberDao.findAll());
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int num = in.readInt();
    Member m = memberDao.findByNo(num);
    if (m == null) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
    out.writeObject(m);
  }

  private void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    Member member = (Member) in.readObject();
    if (memberDao.update(member) == 0) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
  }

  private void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int num = in.readInt();
    
    if (memberDao.delete(num) == 0) {
      out.writeUTF("FAIL");
      return;
    }
    out.writeUTF("OK");
  }
}
