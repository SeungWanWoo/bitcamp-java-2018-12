package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand implements Command {
  MemberDao memberDao;
  
  public MemberUpdateCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(BufferedReader in, PrintWriter out) {

    try {
      out.println("번호? ");
      out.println("!{}!");
      out.flush();
      int no = Integer.parseInt(in.readLine());
      
      Member member = memberDao.findByNo(no);
      
      Member temp = member.clone();
      
      out.printf("이름(%s)? \n", member.getName());
      out.println("!{}!");
      out.flush();
      String input = in.readLine();
      if (input.length() > 0) 
        temp.setName(input);
      
      out.printf("이메일(%s)? \n", member.getEmail());
      out.println("!{}!");
      out.flush();
      if ((input = in.readLine()).length() > 0)
        temp.setEmail(input);
      
      out.printf("암호(새 암호를 입력하세요)? \n");
      out.println("!{}!");
      out.flush();
      if ((input = in.readLine()).length() > 0)
        temp.setPassword(input);
      
      out.printf("사진(%s)? \n", member.getPhoto());
      out.println("!{}!");
      out.flush();
      if ((input = in.readLine()).length() > 0)
        temp.setPhoto(input);
      
      out.printf("전화(%s)? \n", member.getTel());
      out.println("!{}!");
      out.flush();
      if ((input = in.readLine()).length() > 0)
        temp.setTel(input);
      
      temp.setRegisteredDate(new Date(System.currentTimeMillis()));
      
      memberDao.update(temp);
      out.println("회원을 변경했습니다.");
      
    } catch (Exception e) {
      out.printf("회원 정보 변경 오류! : %s\n", e.getMessage());
    }
  }
}
