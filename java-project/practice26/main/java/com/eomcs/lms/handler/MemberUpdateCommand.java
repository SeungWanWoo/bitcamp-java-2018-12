package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand implements Command {
  
  Scanner keyboard;
  List<Member> list;
  
  public MemberUpdateCommand(Scanner keyboard, List<Member> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index)) 
      return;
    
    Member member = list.get(index);
    Member temp = new Member();
    
    temp.setNo(member.getNo());
    
    System.out.printf("이름(%s)? ", member.getName());
    String input = keyboard.nextLine();
    temp.setName(input.length() > 0 ? input : member.getName());
    
    System.out.printf("이메일(%s)? ", member.getEmail());
    input = keyboard.nextLine();
    temp.setEmail(input.length() > 0 ? input : member.getEmail());
    
    System.out.printf("암호(%s)? ", member.getPassword());
    input = keyboard.nextLine();
    temp.setPassword(input.length() > 0 ? input : member.getPassword());
    
    System.out.printf("사진(%s)? ", member.getPhoto());
    input = keyboard.nextLine();
    temp.setPhoto(input.length() > 0 ? input : member.getPhoto());
  
    System.out.printf("전화(%s)? ", member.getTel());
    input = keyboard.nextLine();
    temp.setTel(input.length() > 0 ? input : member.getTel());
  
    temp.setRegisteredDate(new Date(System.currentTimeMillis()));
    
    list.set(index, temp);
    System.out.println("회원 정보를 변경하였습니다.");
  }

  private boolean validate(int index) {
    if (index == -1)
      return false;
    
    return true;
  }

  private int indexOf(int index) {
    Member[] member = list.toArray(new Member[0]);
    for (int i = 0; i < list.size(); i++) {
      if (member[i].getNo() == index)
        return i;
    }
    return -1;
  }

  private int prompt() {
    System.out.print("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }


}
