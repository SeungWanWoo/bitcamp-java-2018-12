package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import com.eomcs.lms.dao.MemberDao;

public class MemberDeleteCommand implements Command {
  MemberDao memberDao;
  
  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(BufferedReader in, PrintWriter out) {

    try {
      out.println("번호? ");
      out.println("!{}!");
      out.flush();
      int no = Integer.parseInt(in.readLine());
      
      memberDao.delete(no);
      out.println("회원을 삭제했습니다.");
      
    } catch (Exception e) {
      out.printf("회원 정보 삭제 오류! : %s\n", e.getMessage());
    }
  }
}
